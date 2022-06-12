package com.daoyu.service;

import com.daoyu.dao.TagDao;
import com.daoyu.dao.impl.TagDaoImpl;
import com.daoyu.entity.Tag;

import java.util.ArrayList;

public class TagService {
    static TagDao tagDao = new TagDaoImpl();

    //创建新话题
    static public int addTag(String tagName) {
        //新增一条tag数据
        Tag tag = new Tag();
        tag.setTagName(tagName);
        tag.setPosts(new ArrayList<Integer>());
        int tagID = tagDao.addTag(tag);
        return tagID;
    }

    //返回 数据库中 所有话题
    static public ArrayList<Tag> getAllTags() {
        return tagDao.findAllTags();
    }

    //根据tagID找到某个话题
    static public Tag findTagByID(int tagID) {
        return tagDao.findTagByID(tagID);
    }

    //给某个tag的 posts字段 增加一条
    static public void appendTagPost(int tagID, int postID) {
        Tag tag = tagDao.findTagByID(tagID);
        if (tag != null) {
            ArrayList<Integer> tagPosts = tag.getPosts();
            if (tagPosts == null) {
                tagPosts = new ArrayList<Integer>();
            }
            tagPosts.add(postID);
            tag.setPosts(tagPosts);
            tagDao.updateTag(tagID, tag);
        }
    }
}
