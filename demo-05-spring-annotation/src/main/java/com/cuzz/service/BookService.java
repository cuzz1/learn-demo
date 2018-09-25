package com.cuzz.service;

import com.cuzz.dao.BookDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: cuzz
 * @Date: 2018/9/24 19:59
 * @Description:
 */
@Data
@Service
public class BookService {

    @Autowired
    BookDao bookDao;
}
