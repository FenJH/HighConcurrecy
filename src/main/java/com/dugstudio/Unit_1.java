package com.dugstudio;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

class Mythread extends Thread {
    HashMap<String,Integer> map=new HashMap<>();
    ConcurrentHashMap<String,Integer> cm=new ConcurrentHashMap<>();

    Unit_1 u=new Unit_1();
    @Override
    public   void run() {
        super.run();
        //  u.testHashMap(Collections.synchronizedMap(map));
         u.testHashMap(cm);


    }
    public void print(){
        Set<Map.Entry<String,Integer>>set=map.entrySet();
        for (Map.Entry<String,Integer> m:set){
            System.out.println(Thread.currentThread().getName()+" "+m.getKey()+" "+m.getValue());
        }
    }
    public void cprint(){
        Set<Map.Entry<String,Integer>>set=cm.entrySet();
        for (Map.Entry<String,Integer> m:set){
            System.out.println(Thread.currentThread().getName()+" "+m.getKey()+" "+m.getValue());
        }
    }
}
public class Unit_1 {
    public  void add(Map<String,Integer> map,String key) {
        synchronized (map) {//concurrentHashMap的get和put单独使用时都是安全的，
            // 但是一起使用就会出现调用间隙而结果不正确
            Integer value = map.get(key);
            if (value != null) {
                map.put(key, value + 1);
            } else {
                map.put(key, 1);
            }
            // System.out.println(Thread.currentThread().getName()+" "+key+" "+map.get(key));

        }
    }
    public  void testHashMap(Map<String,Integer> map){
        for (int i=0;i<10000;i++){
            add(map,i+" ");
        }


    }

    public static void main(String[] args) throws InterruptedException {
        Mythread my=new Mythread();
        long st=System.currentTimeMillis();
        for (int i=0;i<100;i++){
            new Thread(my,"hashmap"+i).start();
        }
        Thread.sleep(1000);
        my.cprint();
        System.out.println(System.currentTimeMillis()-st);
    }


}
