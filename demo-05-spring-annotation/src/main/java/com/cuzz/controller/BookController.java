package com.cuzz.controller;

import com.cuzz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: cuzz
 * @Date: 2018/9/23 13:00
 * @Description:
 */
@Controller
public class BookController {

    @Autowired
    BookService bookService;
}
