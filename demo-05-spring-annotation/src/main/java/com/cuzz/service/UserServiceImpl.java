package com.cuzz.service;

public class UserServiceImpl implements UserService {

    @Override
    public void add() {
        System.out.println("添加一个user");
    }

    @Override
    public void delete() {
        System.out.println("删除一个user");
    }

    @Override
    public void update() {
        System.out.println("更新一个user");
    }

    @Override
    public void get() {
        System.out.println("查询一个user");
    }
}