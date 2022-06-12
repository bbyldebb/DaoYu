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

//关注帖子
@WebServlet("/followPost")
public class followPost extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String userID = req.getParameter("userID");
        String postID = req.getParameter("postID");
        PostService.followPost(userID, Integer.parseInt(postID));
    }
}
