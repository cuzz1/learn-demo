package com.cuzz.config;

import com.cuzz.service.BookService;
import lombok.ToString;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 20:01
 * @Description:
 */
public class IOCTestAutowired {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);
    }
}
