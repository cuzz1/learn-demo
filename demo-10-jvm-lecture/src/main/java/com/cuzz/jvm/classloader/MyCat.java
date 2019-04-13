package com.cuzz.jvm.classloader;

public class MyCat {
    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());
        System.out.println(MySample.class);
    }
}