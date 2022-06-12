package com.daoyu.controller;

import com.daoyu.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//取关帖子
@WebServlet("/unfollowPost")
public class unfollowPost extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String userID = req.getParameter("userID");
        String postID = req.getParameter("postID");
        PostService.unfollowPost(userID, Integer.parseInt(postID));
    }
}
