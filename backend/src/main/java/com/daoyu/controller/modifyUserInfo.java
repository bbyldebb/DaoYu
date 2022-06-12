package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//修改用户信息
@WebServlet("/modifyUserInfo")
public class modifyUserInfo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String userID = req.getParameter("userID");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phoneNum = req.getParameter("phoneNum");
        String address = req.getParameter("address");
        String introduction = req.getParameter("introduction");
        UserService.modifyUserInfo(userID, Integer.parseInt(gender), birthday, phoneNum, address, introduction);
    }
}
