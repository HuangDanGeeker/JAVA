package com.wang.Thread;

/**
 * Created by HuangDanGeeker on 2018/4/17.
 *
 * This is a independent Demo of Thread
 *
 * 1.使用线程休眠sleep(n millis) 使得三个线程交替执行
 * 2.使用Fork/Join 使得线程printB在线程printA完全执行完之后开始执行
 * 3.线程执行顺序(printA, printNum) -> (printB, printNum)
 */
public class Demo {

    //主函数
    public static void main(String[] args){
        //创建三个线程
        final Thread printA = new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++) {
                    System.out.println(" a ");
                    //使线程休眠 5 millis
                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        final Thread printB = new Thread(){
            @Override
            public void run() {
                //线程printB等待printA执行完成之后，开始执行
                try {
                    printA.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for(int i = 0; i < 100; i++) {
                    System.out.println(" b ");
                    //使线程休眠 2 millis
                    try {
                        sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread printNum = new Thread(){
            @Override
            public void run() {
                for(int i = 0; i < 100; i++){
                    System.out.println(" "+ i +" ");
                    //使线程休眠 10 millis
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        //开启线程
        printA.start();
        printB.start();
        printNum.start();

    }
}
