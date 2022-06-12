package com.daoyu.service;

import com.alibaba.fastjson.JSON;

public class testUserService {
    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(UserService.getUserInfo("123456")));
    }
}
