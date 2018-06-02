package com.dugstudio.jvm;

import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader=new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    System.out.println(Thread.currentThread().getName());
                    String fileName=name.substring(name.lastIndexOf(".")+1)+".class";
                    System.out.println(this.getParent()+"--"+this.getParent().getParent()+"-"+fileName);
                    ClassLoader loader=this.getParent();
                    while (loader!=null){
                        System.out.print(loader+"--");
                        loader=loader.getParent();
                    }

                    InputStream is=getClass().getResourceAsStream(fileName);
                    if (is==null){
                        System.out.println(" null"+this.getParent().getParent());
                        return super.loadClass(name);
                    }
                    byte[]b=new byte[is.available()];
                    is.read(b);
                    System.out.println(b.length);
                    return defineClass(name,b,0,b.length);
                }catch (IOException e){
                throw  new ClassNotFoundException(name);
                                }
            }
        };
        Object obj=myClassLoader.loadClass("com.dugstudio.jvm.MyClassLoader").newInstance();
        System.out.println(obj.getClass());
        //System.out.println(obj instanceof com.dugstudio.jvm.MyClassLoader);

    }
}
