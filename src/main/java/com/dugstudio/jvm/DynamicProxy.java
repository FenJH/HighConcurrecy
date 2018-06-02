package com.dugstudio.jvm;

import jdk.internal.org.objectweb.asm.Handle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author JH
 * @CreateDate 18-5-28
 * @Description
 */
public class DynamicProxy {
    interface  IHello{
        public  void sayHello();
    }
   static class Hello implements IHello{

        @Override
        public void sayHello() {
            System.out.println("Hello");
        }
    }
    static class Handler implements InvocationHandler{
        Object obj;
        public  Object bind(Object obj){
            this.obj=obj;
            return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("WELCOME");
            return method.invoke(obj,args);
        }
    }

    public static void main(String[] args) {
        IHello h=(IHello)new Handler().bind(new Hello());
        h.sayHello();
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
    }
}
