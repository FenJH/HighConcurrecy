package com.dugstudio;

import java.util.concurrent.Exchanger;

public class Unit_3_1 {
    public static void main(String[] args) {
        final Exchanger<Integer> exchanger=new Exchanger<>();
        new Thread(){
            @Override
            public void run() {
                try {
                    int x1=2;
                    x1=exchanger.exchange(x1);
                    System.out.println("x1:"+x1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                int x2=4;
                try {
                    x2=exchanger.exchange(x2);
                    System.out.println("x2:"+x2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //只会交换两个线程，第三个线程会等待
        new Thread(){
            @Override
            public void run() {
                int x3=3;
                 try {
                    x3=exchanger.exchange(x3);
                     System.out.println("x3:"+x3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
