package com.dugstudio;

import java.util.HashMap;
import java.util.concurrent.*;

public class Unit_3_2 {
    public static void main(String[] args) {
        ThreadPoolExecutor tl=new ThreadPoolExecutor(4,5,1000,TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
        Future<HashMap>future=tl.submit(new Callable<HashMap>() {
            @Override
            public HashMap call() throws Exception {
                return null;
            }
        });
    }
}
