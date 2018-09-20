package com.cuzz.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: cuzz
 * @Date: 2018/9/20 9:53
 * @Description:
 */
@RestController
public class GirlController {

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = "/girl", method = RequestMethod.GET)
    public String girl() {
        return girlProperties.toString();
    }
}
