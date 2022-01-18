package 西二在线第二轮;

public abstract class Cat {
    protected String name;
    protected int age;
    protected char gender;
    protected double price;

    public Cat(){
    }

    //全参构造方法
    public Cat(String name,int age,char gender,double price){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public char getGender(){
        return gender;
    }
    public double getPrice(){
        return price;
    }

    //抽象的toString方法
    public abstract String toString();

}


