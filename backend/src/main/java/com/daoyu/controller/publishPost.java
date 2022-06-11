package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daoyu.service.PostService;
import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//发布帖子
@WebServlet("/publishPost")
public class publishPost extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        //获取参数
        String userID = req.getParameter("userID");
        String content = req.getParameter("content");
        String image = req.getParameter("image");
        String address = req.getParameter("address");
        String tags = req.getParameter("tags");
        String status = req.getParameter("status");
        //获取待返回的postID
        int postID = PostService.publishPost(userID, content, image, address, tags, Integer.parseInt(status));
        //制造JSON对象
        JSONObject postJSON = new JSONObject();
        postJSON.put("postID", postID);
        //返回
        resp.getWriter().write(JSON.toJSONString(postJSON));
    }
}
