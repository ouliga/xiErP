package 西二在线第二轮;

public class BlackCat extends Cat{

    public BlackCat(String name,int age,char gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = 350;
    }

    public String toString(){
        String s = "";
        s = s+"猫的名字："+getName()+"\n";
        s = s+"猫的年龄："+getAge()+"\n";
        s = s+"猫的性别："+getGender()+"\n";
        s = s+"猫的价格："+getPrice()+"\n";
        return s;
    }
}
