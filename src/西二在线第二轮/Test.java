package 西二在线第二轮;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请初始化猫咖的余额：");
        double balance = sc.nextDouble();

        ArrayList<Cat> cat = new ArrayList<>();
        ArrayList<Customer> customer = new ArrayList<>();
        //初始化猫咖
        MyCatCafe catCafe = new MyCatCafe(balance,cat,customer);

        //测试招待顾客异常
//        String empty = sc.nextLine();
//        System.out.println("请输入顾客的名字：");
//        String name2 = sc.nextLine();
//        System.out.println("请输入顾客rua猫的次数：");
//        int rua2 = sc.nextInt();
//
//        Customer c1 = new Customer(name2,rua2);
//        catCafe.entertainCustomers(c1);

        //测试买猫余额不足异常
//        OrangeCat cat1 = new OrangeCat("Joe",1,'F',false);
//        catCafe.buyNewCat(cat1);
//        System.out.println("买完一只猫后的余额："+catCafe.getBalance());

        //测试买入猫
        //购入一只橘猫
        OrangeCat cat1 = new OrangeCat("Joe",1,'F',false);
        catCafe.buyNewCat(cat1);
        //购入一只黑猫
        BlackCat cat2 = new BlackCat("Steven",2,'F');
        catCafe.buyNewCat(cat2);
        //测试招待多位顾客
        Customer customer1 = new Customer("Ben",3);
        catCafe.entertainCustomers(customer1);

        Customer customer2 = new Customer("Benson",1);
        catCafe.entertainCustomers(customer2);

        //测试歇业
        catCafe.close();



    }
}
