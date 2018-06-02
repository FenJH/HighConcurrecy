package com.dugstudio;

import org.omg.SendingContext.RunTime;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
class MyThread implements Runnable{
    private Unit_3_3 u=new Unit_3_3();
    private HashMap<String,Integer>map=new HashMap<>();
    private  ReentrantLock lock=new ReentrantLock();
    @Override
    public void run() {
        u.reentrantLockTest(map,lock);
    }
    public void print(){
       /* Set<Map.Entry<String,Integer>> set= map.entrySet();
        for (Map.Entry<String,Integer> map:set){
            System.out.println(map.getKey()+"--"+map.getValue());
        }*/
        System.out.println(map.get(""+1)+" ");
    }
}
public class Unit_3_3 {
    public void add(HashMap<String,Integer> map){
        for (int i=0;i<4000000;i++){
           Integer value= map.get(i+"");
           if (value!=null){
               map.put(i+"",value+1);
           }else {
               map.put(i+"",1);
           }

        }


    }
    public  void reentrantLockTest(HashMap<String,Integer> map,ReentrantLock lock){

        try {
            lock.lock();
            add(map);
            System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis());

        }catch (Exception e){
           e.printStackTrace();
        }  finally{
            lock.unlock();
        }

    }
    public synchronized void synTest(HashMap<String,Integer> map){
        add(map);
        System.out.println(Thread.currentThread().getName()+" "+System.currentTimeMillis());

    }
    public static void main(String[] args) throws InterruptedException {
        long st1=System.currentTimeMillis();
        System.out.println("------------"+st1);
        MyThread thread=new MyThread();
        for (int i=0;i<10;i++){
            new Thread(thread,"--"+i).start();
        }
        //Thread.sleep(9000);
        thread.print();

        //System.out.println(System.currentTimeMillis()-st1);
    }
}
