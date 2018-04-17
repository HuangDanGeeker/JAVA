package com.wang.Thread;


/**
 * Created by HuangDanGeeker on 2018/4/17.
 *
 * Deom of using multi-Thread to calculate Pi
 */
public class Demo_CalculatePi extends Thread{

    private double result; //记录当前线程计算的结果
    private double from; //开始计算的项
    private double interval; //项数间隔
    private double accuracy; //计算的精度
    private double sign; //符号

    public Demo_CalculatePi(double from, double interval, double accuracy) {
        this.from = from;
        this.interval = interval * 2;
        this.accuracy = accuracy;
        result = 0;
        sign = (from - 1)  / 2 % 2 == 0 ? 1 : -1;
    }

    @Override
    public void run() {
        double i = sign / from;
        while(Math.abs(i) >= accuracy){ //精度控制
            result += i;
            from += interval;
            sign = (from - 1)  / 2 % 2 == 0 ? 1 : -1;
            i = sign / from;
        }
    }

    /**
     * 取出该线程计算的结果
     * @return
     */
    public double getResult() {
        return result;
    }



    public static void main(String[] args) {
        double PI;
        long t1;
        long t2;

        System.out.println("单线程");
        t1 = System.currentTimeMillis(); //记录开始时间
        PI = createThread(1, 1e-9);
        System.out.println("PI = " + PI);
        t2 = System.currentTimeMillis();  //记录结束时间
        System.out.println("单线程时间： " + (1.0 * (t2 - t1) / 1000 + "秒"));
        System.out.println();

        System.out.println("2线程");
        t1 = System.currentTimeMillis();
        PI = createThread(2, 1e-9);
        System.out.println("PI = " + PI);
        t2 = System.currentTimeMillis();
        System.out.println("2线程时间： " + (1.0 * (t2 - t1) / 1000 + "秒"));
        System.out.println();

        System.out.println("4线程");
        t1 = System.currentTimeMillis();
        PI = createThread(4, 1e-9);
        System.out.println("PI = " + PI);
        t2 = System.currentTimeMillis();
        System.out.println("4线程时间： " + (1.0 * (t2 - t1) / 1000) + "秒\n");

        System.out.println("8线程");
        t1 = System.currentTimeMillis();
        PI = createThread(8, 1e-9);
        System.out.println("PI = " + PI);
        t2 = System.currentTimeMillis();
        System.out.println("8线程时间： " + (1.0 * (t2 - t1) / 1000) + "秒\n");

        System.out.println("16线程");
        t1 = System.currentTimeMillis();
        PI = createThread(16, 1e-9);
        System.out.println("PI = " + PI);
        t2 = System.currentTimeMillis();
        System.out.println("16线程时间： " + (1.0 * (t2 - t1) / 1000) + "秒");
    }

    private static double createThread(int threadCount, double accuracy) {
        double PI = 0;
        Demo_CalculatePi[] threads = new Demo_CalculatePi[threadCount];
        //准备线程
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Demo_CalculatePi(2 * i + 1, threadCount, accuracy);
        }
        //启动线程
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        try {
            for (int i = 0; i < threadCount; i++) {
                //等待多线程计算，获取计算结果
                threads[i].join();
                PI += threads[i].getResult();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PI *= 4;
        return PI;
    }

}
