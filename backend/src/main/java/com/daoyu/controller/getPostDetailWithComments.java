package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daoyu.entity.Comment;
import com.daoyu.entity.Post;
import com.daoyu.entity.Tag;
import com.daoyu.entity.User;
import com.daoyu.service.CommentService;
import com.daoyu.service.PostService;
import com.daoyu.service.TagService;
import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//获取帖子详情+所有评论
@WebServlet("/getPostDetailWithComments")
public class getPostDetailWithComments extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        //读取参数
        String postID = req.getParameter("postID");
        //获取post对象
        Post post = PostService.getPostDetail(Integer.parseInt(postID));
        //获取comments对象数组
        ArrayList<Integer> commentIDs = post.getComments();
        if (commentIDs == null) commentIDs = new ArrayList<Integer>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        for (int commentID : commentIDs) {
            Comment comment = CommentService.findComment(commentID);
            comments.add(comment);
        }
        //获取user对象
        String userID = post.getUserID();
        User user = UserService.getUserInfo(userID);
        //获取tagList
        ArrayList<Integer> tagIDs = post.getTags();
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        for (int tagID : tagIDs) {
            tagList.add(TagService.findTagByID(tagID));
        }
        //制造返回结果
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("post",post);
        jsonObject.put("comments",comments);
        jsonObject.put("user",user);
        jsonObject.put("tagList",tagList);
        //返回
        resp.getWriter().write(String.valueOf(jsonObject));
    }
}


