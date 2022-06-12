package com.daoyu.service;

import com.daoyu.dao.CommentDao;
import com.daoyu.dao.PostDao;
import com.daoyu.dao.impl.CommentDaoImpl;
import com.daoyu.dao.impl.PostDaoImpl;
import com.daoyu.entity.Comment;

import java.sql.Timestamp;

public class CommentService {
    static CommentDao commentDao = new CommentDaoImpl();
    static PostDao postDao = new PostDaoImpl();

    //发布评论
    static public int addComment(String userID, int postID, String content) {
        //新增一条comment数据
        Comment comment = new Comment();
        comment.setUserID(userID);
        comment.setPostID(postID);
        comment.setContent(content);
        comment.setTime(new Timestamp(System.currentTimeMillis()));
        int commentID = commentDao.addComment(comment);
        //更新对应的post对象
        if (commentID != -1) {
            PostService.appendPostComment(postID, commentID);
        }
        return commentID;
    }

    //根据commentID查找某条评论
    static public Comment findComment(int commentID) {
        return commentDao.findCommentByID(commentID);
    }
}
