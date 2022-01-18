package 西二在线第二轮;
import java.util.Scanner;

public class Multithreading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //将输入的字符串转化为数组
        System.out.println("请输入第一个数组：(就像“1,2,3”)");
        String s = sc.nextLine();
        String [] s1 = s.split(",");
        int [] nums1 = new int[s1.length];
        for(int i=0;i<s1.length;i++){
            nums1[i] = Integer.parseInt(s1[i]);
        }

        System.out.println("请输入第二个数组：(就像“1,2,3”)");
        String ss = sc.nextLine();
        String [] s2 = ss.split(",");
        int [] nums2 = new int[s2.length];
        for(int i=0;i<s2.length;i++){
            nums2[i] = Integer.parseInt(s2[i]);
        }

        sc.close();

        multithreading(nums1,nums2);
    }
    //多线程输出的方法
    public static void multithreading(int [] nums1,int [] nums2){
        waitnotify w = new waitnotify(1,false);
        System.out.println("交替输出的结果为：");
        new Thread(()->{//线程一
            w.print(nums1,1,2);
        }).start();
        new Thread(()->{//线程二
            w.print(nums2,2,1);
        }).start();
    }

    public static class waitnotify {
        //利用flag，当flag=1时启动线程一打印第一个数组，当flag=2时启动线程二打印第二个数组
        private int flag;
        //当两个数组不一样长时，短的数组输出完后，长的数组接着输出
        private boolean stop;
        //设置初始的flag值
        public waitnotify(int flag,boolean stop){
            this.flag = flag;
            this.stop = stop;
        }

        public void print(int [] nums,int waitflag,int nextflag){
            for(int i:nums){
                //同步代码块一次只允许一个线程进入该代码块
                synchronized (this){
                    if(!stop){
                        while(flag!=waitflag){
                            try{
                                wait();
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                        flag = nextflag;
                    }
                    System.out.print(i+" ");
                    //将所有在该对象上等待被notify的所有线程统统退出wait的状态
                    notifyAll();
                }
            }
            //当一个线程停下时，保证flag不再变化，从而可以继续输出较长的那个数组剩下的元素
            stop = true;
        }
    }

}
