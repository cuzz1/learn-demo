package com.cuzz.test02;

import com.cuzz.test02.IHello;

/**
 * @Author: cuzz
 * @Date: 2019/2/24 20:42
 * @Description:
 */
public class Hello implements IHello {
    @Override
    public void sayHello() {
        System.out.println("com.cuzz.test02.Hello world!");
    }
}
