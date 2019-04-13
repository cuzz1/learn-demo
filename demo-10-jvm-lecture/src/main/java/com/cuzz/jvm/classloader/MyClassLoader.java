package com.cuzz.jvm.classloader;

import java.io.*;

/**
 * @Author: cuzz
 * @Date: 2019/1/28 12:39
 * @Description:
 */
public class MyClassLoader extends ClassLoader{
    private String classLoaderName;
    private final String fileExtension = ".class";
    private String path;

    public MyClassLoader(String classLoaderName) {
        super(); // 将系统类加载当做该类加载器的父加载器
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent); // 显示指定该类加载的父加载器
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] data = loadClassData(className);
        return defineClass(className, data,0, data.length);
    }

    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        name = name.replace(".", "\\");
        try {
            is = new FileInputStream(new File(this.path + name + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader("myLoader");
        String path = "C:/Users/my/Desktop/";
        myClassLoader.setPath(path);
        Class<?> clazz = myClassLoader.loadClass("com.cuzz.jvm.classloader.MySample");
        Object object = clazz.newInstance();
    }
}
