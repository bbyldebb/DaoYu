package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daoyu.service.CommentService;
import com.daoyu.service.PostService;
import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//发布评论
@WebServlet("/addComment")
public class addComment extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        //获取参数
        String userID = req.getParameter("userID");
        String postID = req.getParameter("postID");
        String content = req.getParameter("content");
        //获取待返回的commentID
        int commentID = CommentService.addComment(userID, Integer.parseInt(postID), content);
        //制造JSON对象
        JSONObject commentJSON = new JSONObject();
        commentJSON.put("commentID", commentID);
        //返回
        resp.getWriter().write(JSON.toJSONString(commentJSON));
    }
}
