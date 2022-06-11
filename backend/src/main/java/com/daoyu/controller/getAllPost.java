package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daoyu.entity.Post;
import com.daoyu.entity.Tag;
import com.daoyu.entity.User;
import com.daoyu.service.PostService;
import com.daoyu.service.TagService;
import com.daoyu.service.UserService;
import com.mysql.cj.xdevapi.JsonString;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//返回 数据库中 所有帖子，根据热度排序
@WebServlet("/getAllPost")
public class getAllPost extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        //制造返回结果
        JSONArray res = new JSONArray();
        //获取所有post对象
        ArrayList<Post> posts = PostService.getAllPost();
        //每个post，和对应的user、tagList封装在一个对象中
        for (Post post : posts) {
            //获取user
            String userID = post.getUserID();
            User user = UserService.getUserInfo(userID);
            //获取tagList
            ArrayList<Integer> tagIDs = post.getTags();
            ArrayList<Tag> tagList = new ArrayList<Tag>();
            for (int tagID : tagIDs) {
                tagList.add(TagService.findTagByID(tagID));
            }
            //封装在一个对象中
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("post", post);
            jsonObject.put("user", user);
            jsonObject.put("tagList", tagList);
            res.add(jsonObject);
        }
        resp.getWriter().write(String.valueOf(res));
    }
}
