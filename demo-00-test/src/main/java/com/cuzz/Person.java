package com.cuzz;

public class Person {
	// 成员变量
	public String name;
	public int age;
	private String address;

	// 构造方法
	public Person() {
		System.out.println("空参数构造方法");
	}
	
	public Person(String name) {
		this.name = name;
		System.out.println("带有String的构造方法");
	}
	// 私有的构造方法
	private Person(String name, int age){
		this.name = name;
		this.age = age;
		System.out.println("带有String，int的构造方法");
	}
	
	public Person(String name, int age, String address){
		this.name = name;
		this.age = age;
		this.address = address;
		System.out.println("带有String, int, String的构造方法");
	}
	
	// 成员方法
	// 没有返回值没有参数的方法
	public void method1(){
		System.out.println("没有返回值没有参数的方法");
	}
	// 没有返回值，有参数的方法
	public void method2(String name){
		System.out.println("没有返回值，有参数的方法 name= "+ name);
	}
	// 有返回值，没有参数
	public int method3(){
		System.out.println("有返回值，没有参数的方法");
		return 123;
	}
	// 有返回值，有参数的方法
	public String method4(String name){
		System.out.println("有返回值，有参数的方法");
		return "哈哈" + name;
	}
	// 私有方法
	private void method5(){
		System.out.println("私有方法");
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address+ "]";
	}
}