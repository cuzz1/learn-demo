package com.cuzz.jvm.classloader;

public class MySample {
    public MySample () {
        System.out.println("MySample is loaded by: " + this.getClass().getClassLoader());
        new MyCat ();
        System.out.println(MyCat.class);
    }
}