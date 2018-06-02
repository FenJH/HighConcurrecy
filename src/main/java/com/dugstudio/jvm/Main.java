package com.dugstudio.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void fun() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

    }
    static  int m = 100;
    static {
        int x=1;
        System.out.println(m);
    }
    static int n=10;
    public static void main(String[] args) {
      fun();
    }
}
