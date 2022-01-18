package 西二在线第二轮;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int rua;
    private LocalDate date=LocalDate.now();//获得当前日期的一个对象

    public Customer(String name,int rua){
        this.name = name;
        this.rua = rua;
    }

    //返回顾客的信息
    public String getName(){
        return name;
    }
    public int getRua(){
        return rua;
    }
    public LocalDate getDate(){
        return date;
    }

    public String toString(){
        String s = "";
        s = s+"顾客的名字："+name+"\n";
        s = s+"顾客rua猫的次数："+rua+"\n";
        s = s+"顾客到店的时间："+date+"\n";
        return s;
    }
}
