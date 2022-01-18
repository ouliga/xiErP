package 西二在线第二轮;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class MyCatCafe implements CatCafe{
    private double balance;
    private int profit = 0;
    ArrayList<Cat> cat;
    ArrayList<Customer> customer;

    public MyCatCafe(double balance,ArrayList<Cat> cat,ArrayList<Customer> customer){
        this.balance = balance;
        this.cat = cat;
        this.customer = customer;
    }

    //查看余额
    public double getBalance(){
        return balance;
    }

    @Override
    public void buyNewCat(Cat kind) {
        //余额不足时抛出异常
        if(kind.getPrice()>balance){
            throw new InsufficientBalanceException("余额不足");
        }
        else{
            //往动态列表中添加新买入的猫的对象
            System.out.println("买入的猫的信息：");
            System.out.println(kind);
            cat.add(kind);
            balance-=kind.getPrice();
        }
    }

    public void entertainCustomers(Customer customers){
        //往动态列表中添加顾客
        customer.add(customers);
        //猫的数量不足时抛出异常
        if(cat.size()<=0){
            throw new CatNotFoundException("店内无猫可rua");
        }
        else{
            //随机挑选n只猫
            Random r =new Random();
            for(int i=1;i<=customers.getRua();i++){
                int n = r.nextInt(cat.size());
                System.out.println("顾客"+customers.getName()+"第"+i+"次rua的猫是：");
                System.out.println(cat.get(n).toString());
                balance+=15;
                profit+=15;
            }
        }
    }

    public void close(){
        //输出当天光顾的顾客列表信息
        System.out.println("当天光顾的顾客列表信息：");
        for(Customer i:customer){
            //保证输出的是当天顾客的信息
            if(i.getDate().equals(LocalDate.now())){
                System.out.println(i);
            }
        }
        System.out.println("今天的利润："+profit);
    }

}
