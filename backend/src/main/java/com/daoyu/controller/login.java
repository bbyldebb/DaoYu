package com.daoyu.controller;

import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录
@WebServlet("/login")
public class login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String userID = req.getParameter("userID");
        String nickName = req.getParameter("nickName");
        String avatarImg = req.getParameter("avatarImg");
        UserService.logIn(userID, nickName, avatarImg);
    }
}
