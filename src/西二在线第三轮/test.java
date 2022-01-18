package 西二在线第三轮;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;

public class test {
    public static void main(String[] args) {
        //获得福州、上海、北京的城市信息
        String [] city = new String[3];
        city[0] = getCity("fuzhou");
        city[1] = getCity("shanghai");
        city[2] = getCity("beijing");
        //获得各自的id
        int fzID = getId(city[0]);
        int shID = getId(city[1]);
        int bjID = getId(city[2]);
        //获得各自的天气
        String [] weather = new String[3];
        weather[0] = getWeather(fzID+"");
        weather[1] = getWeather(shID+"");
        weather[2] = getWeather(bjID+"");
        //通过删除再插入的方法实现数据库的更新
        delete("city");
        //将城市的信息储存在数据库中
        for(String c:city){
            int id = getId(c);
            String name = getName(c);
            double lat = getLat(c);
            double lon = getLon(c);
            addCity(id,name,lat,lon);
        }
        //将天气的信息储存在数据库中
        delete("weather");
        for(int i=0;i<3;i++){
            String w = weather[i];
            while(w.indexOf("fxDate")!=-1){
                int id = getId(city[i]);
                String fxDate = getDate(w);
                int teampMax = getTempMax(w);
                int teampMin = getTempMin(w);
                String textDay = getTextDay(w);
                w = w.substring(w.indexOf("fxDate")+100);
                addWeather(id,fxDate,teampMax,teampMin,textDay);
            }
        }
        //查询功能来不及做了

    }
    //将数据库中的列表内容清除
    public static void delete(String tableName){
        String sql = "delete from "+tableName;
        Connection conn = null;
        PreparedStatement pstms = null;

        try{
            conn = DbUtil.getConnection();
            pstms = (PreparedStatement) conn.prepareStatement(sql);
            pstms.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DbUtil.close(pstms);
            DbUtil.close(conn);
        }
    }
    //在数据库中导入城市的信息
    public static void addCity(int id,String name,double lat,double lon){
        String sql = "insert into city(id,name,lat,lon) values(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstms = null;

        try{
            conn = DbUtil.getConnection();
            pstms = (PreparedStatement) conn.prepareStatement(sql);
            pstms.setInt(1,id);
            pstms.setString(2,name);
            pstms.setDouble(3,lat);
            pstms.setDouble(4,lon);
            pstms.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DbUtil.close(pstms);
            DbUtil.close(conn);
        }
    }

    //在数据库中导入天气的信息
    public static void addWeather(int id,String fxDate,int tempMax,int tempMin,String textDay){
        String sql = "insert into weather(id,fxDate,tempMax,tempMin,textDay) values(?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstms = null;

        try{
            conn = DbUtil.getConnection();
            pstms = (PreparedStatement) conn.prepareStatement(sql);
            pstms.setInt(1,id);
            pstms.setString(2,fxDate);
            pstms.setInt(3,tempMax);
            pstms.setInt(4,tempMin);
            pstms.setString(5,textDay);
            pstms.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DbUtil.close(pstms);
            DbUtil.close(conn);
        }
    }
    //获得城市的api
    public static String getCity(String cityName){
        String s = "https://geoapi.qweather.com/v2/city/lookup?key=";
        return get(s,cityName);
    }
    //获得城市天气的api
    public static String getWeather(String cityId){
        String s = "https://devapi.qweather.com/v7/weather/3d?key=";
        return get(s,cityId);
    }

    public static String get(String s1,String s2){
        String message="";
        try {
            URL url=new URL(s1 + "9ed21ecdeeb04ee19a957bda5e372ef3&location=" + s2);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5*1000);
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            GZIPInputStream gzipInputStream =new GZIPInputStream(inputStream);
            StringBuilder res=new StringBuilder();
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8));
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
            message=res.toString();
            inputStream.close();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
    //得到城市的名字
    public static String getName(String s){
        String name = "";
        int first = s.indexOf("\"name\"");
        int last = s.indexOf("\"id\"");
        name = s.substring(first+8,last-2);
        return name;
    }

    //得到城市的id
    public static int getId(String s){
        String id = "";
        int first = s.indexOf("\"id\"");
        int last = s.indexOf("\"lat\"");
        id = s.substring(first+6,last-2);
        return Integer.parseInt(id);
    }

    //获得维度
    public static double getLat(String s){
        String lat = "";
        int first = s.indexOf("\"lat\"");
        int last = s.indexOf("\"lon\"");
        lat = s.substring(first+7,last-2);
        return Double.parseDouble(lat);
    }

    //获得经度
    public static double getLon(String s){
        String lon = "";
        int first = s.indexOf("\"lon\"");
        int last = s.indexOf("\"adm2\"");
        lon = s.substring(first+7,last-2);
        return Double.parseDouble(lon);
    }

    //获得日期
    public static String getDate(String s){
        String date = "";
        int first = s.indexOf("\"fxDate\"");
        int last = s.indexOf("\"sunrise\"");
        date = s.substring(first+10,last-2);
        return date;
    }

    //获得最高气温
    public static int getTempMax(String s){
        String tempMax = "";
        int first = s.indexOf("\"tempMax\"");
        int last = s.indexOf("\"tempMin\"");
        tempMax = s.substring(first+11,last-2);
        return Integer.parseInt(tempMax);
    }

    //获得最低气温
    public static int getTempMin(String s){
        String tempMin = "";
        int first = s.indexOf("\"tempMin\"");
        int last = s.indexOf("\"iconDay\"");
        tempMin = s.substring(first+11,last-2);
        return Integer.parseInt(tempMin);
    }

    //获得白天天气情况
    public static String getTextDay(String s){
        String textDay = "";
        int first = s.indexOf("\"textDay\"");
        int last = s.indexOf("\"iconNight\"");
        textDay = s.substring(first+11,last-2);
        return textDay;
    }

}
