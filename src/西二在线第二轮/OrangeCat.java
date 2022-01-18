package 西二在线第二轮;

public class OrangeCat extends Cat{
    private boolean isFat;

    public OrangeCat(String name,int age,char gender,boolean isFat){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.isFat = isFat;
        this.price = 200;
    }

    public String toString(){
        String s = "";
        s = s+"猫的名字："+getName()+"\n";
        s = s+"猫的年龄："+getAge()+"\n";
        s = s+"猫的性别："+getGender()+"\n";
        s = s+"猫肥不肥胖："+isFat+"\n";
        s = s+"猫的价格："+getPrice()+"\n";
        return s;
    }

}
