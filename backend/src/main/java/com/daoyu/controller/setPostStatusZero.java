package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.daoyu.service.PostService;
import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//修改帖子的status为 已解决
@WebServlet("/setPostStatusZero")
public class setPostStatusZero extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String postID = req.getParameter("postID");
        PostService.setPostStatusZero(Integer.parseInt(postID));
    }
}
