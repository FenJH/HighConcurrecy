package com.dugstudio;

import java.util.concurrent.*;

class MyThread3 implements  Runnable{
    private  CountDownLatch latch;
    private int[] datas;
    private int start;
    private int end;
    private CyclicBarrier c;
    /*public MyThread3(CountDownLatch cl) {
        this.latch=cl;
    }*/

    public MyThread3(CyclicBarrier c) {
        this.c=c;
    }

    @Override
    public void run() {
        //sort();
        //System.out.println(Thread.currentThread().getName()+"--before:"+latch.getCount());
       System.out.println(Thread.currentThread().getName()+"--"+"--before:"+c.getNumberWaiting());
        try {
            c.await();
        //    System.out.println(Thread.currentThread().getName()+"-------------------------"+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
       // System.out.println(Thread.currentThread().getName()+"--after:"+c.getNumberWaiting());

        // System.out.println(Thread.currentThread().getName()+"--after:"+latch.getCount());
    }
    public  void sort(){
        for (int i=start;i<end;i++){
            for (int j=start+1;j<end;j++){
                if (datas[i]>datas[j]){
                    int temp=datas[i];
                    datas[i]=datas[j];
                    datas[j]=temp;
                }
            }
        }
    }
}
public class Unit_3 {

    public static void main(String[] args) {
        int count = 10;
        CountDownLatch cl = new CountDownLatch(count);
        int[] datas = {};
        // MyThread3 my=new MyThread3(cl);
        CyclicBarrier c = new CyclicBarrier(10);
        MyThread3 m3 = new MyThread3(c);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());

        for (int i = 0; i < 20; i++) {//线程数大于CyclicBarrier的parties数量就会触发nextGeneration,可重用性
            /* new Thread(m3,"countDownLauch"+i).start();*/
            threadPoolExecutor.execute(m3);


            System.out.println(threadPoolExecutor.getActiveCount());
        }
    }
}
