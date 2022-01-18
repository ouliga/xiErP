package 西二在线第二轮;

import java.util.Scanner;

public class RegularExpression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入邮箱：");
        String s = sc.nextLine();
        while (!checkEmail(s)){
            System.out.println("请输入正确的邮箱：");
            s = sc.nextLine();
        }
        System.out.println("您已输入正确的邮箱！");
    }
    public static boolean checkEmail(String s){
        String regular = "[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[0-9a-zZ]+";
        return s.matches(regular);
    }
}
