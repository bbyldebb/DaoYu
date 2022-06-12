package com.daoyu.controller;

import com.alibaba.fastjson.JSON;
import com.daoyu.service.PostService;
import com.daoyu.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//返回 数据库中 所有话题
@WebServlet("/getAllTags")
public class getAllTags extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(TagService.getAllTags()));
    }
}
