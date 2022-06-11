package com.daoyu.dao;

import com.daoyu.entity.Post;

import java.util.ArrayList;

public interface PostDao {
    //增加Post对象
    int addPost(Post post);

    //根据postID  删除Post对象
    void delPost(int postID);

    //根据postID  更新Post对象
    void updatePost(int postID, Post post);

    //根据postID  查找Post对象
    Post findPostByID(int postID);

    //根据关键词   查找Post对象
    ArrayList<Post> findPostByKeyword(String keyword);

    //查找所有Post对象
    ArrayList<Post> findAllPosts();
}
