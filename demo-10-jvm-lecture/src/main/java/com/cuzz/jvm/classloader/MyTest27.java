package com.cuzz.jvm.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Author: cuzz
 * @Date: 2019/2/1 16:27
 * @Description:
 */
public class MyTest27 {
    public static void main(String[] args) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/3306/test");
    }
}
