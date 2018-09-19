package com.cuzz.demo.controller;

import com.cuzz.springmvc.anotation.Controller;
import com.cuzz.springmvc.anotation.RequestMapping;

/**
 * @Author: cuzz
 * @Date: 2018/9/18 17:28
 * @Description:
 */
@Controller
@RequestMapping("/cuzz")
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
