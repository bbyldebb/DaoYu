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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//返回 某个用户 关注 的所有帖子
@WebServlet("/getAllMyFollowPost")
public class getAllMyFollowPost extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        //制造返回结果
        JSONArray res = new JSONArray();
        //获取参数
        String userID = req.getParameter("userID");
        //获取所有post对象
        ArrayList<Post> posts = PostService.getAllMyFollowPost(userID);
        //每个post，和对应的user、tagList封装在一个对象中
        for (Post post : posts) {
            //获取user对象
            String userID1 = post.getUserID();
            User user = UserService.getUserInfo(userID1);
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
