package com.dugstudio;

import java.util.HashMap;
import java.util.concurrent.*;

public class Unit_3_2 {
    public static void main(String[] args) {
        ThreadPoolExecutor tl=new ThreadPoolExecutor(4,5,1000,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        System.out.println("提交的时间是:"+System.currentTimeMillis());
        Future<String>future=tl.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "现在才调用："+System.currentTimeMillis();
            }
        });
        System.out.println("可以继续执行");
        for(int i=0;i<1000;i++){
           ;
        }
        System.out.println("---"+System.currentTimeMillis());
        try {
            //Thread.sleep(100);
            String s= future.get();
            System.out.println(s);
            System.out.println(future.isDone());
            tl.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
