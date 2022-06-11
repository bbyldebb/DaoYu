package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daoyu.service.CommentService;
import com.daoyu.service.TagService;
import com.daoyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//创建新话题
@WebServlet("/addTag")
public class addTag extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        //获取参数
        String tagName = req.getParameter("tagName");
        //获取待返回的tagID
        int tagID = TagService.addTag(tagName);
        //制造JSON对象
        JSONObject tagJSON = new JSONObject();
        tagJSON.put("tagID", tagID);
        //返回
        resp.getWriter().write(JSON.toJSONString(tagJSON));
    }
}
