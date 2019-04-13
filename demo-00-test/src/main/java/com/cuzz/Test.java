package com.cuzz;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, InstantiationException {
		// 获取Class对象 包名.类
		Class c = Class.forName("com.cuzz.Person");
		
		// 获取所有构造方法
		// 只包括public
		Constructor[] cons = c.getConstructors();
		// 即包括public也包括private
		Constructor[] conss = c.getDeclaredConstructors();
		
		// 获取一个构造方法
		// public Person() 
		Constructor con1 = c.getConstructor(null);
		System.out.println(con1);
		
		// public Person(String name)
		Constructor con2 = c.getConstructor(String.class);
		System.out.println(con2);
		
		// private Person(String name, int age)
		Constructor con3 = c.getDeclaredConstructor(String.class, int.class);
		System.out.println(con3);
		
		// public Person(String name, int age, String address)
		Constructor con4 = c.getDeclaredConstructor(String.class, int.class, String.class);
		System.out.println(con4);
		Object o = con4.newInstance("cuzz", 22, "武汉");
		System.out.println(o);
	}
}