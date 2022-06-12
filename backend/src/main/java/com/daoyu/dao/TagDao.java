package com.daoyu.dao;

import com.daoyu.entity.Tag;

import java.util.ArrayList;

public interface TagDao {
    //增加Tag对象
    int addTag(Tag tag);

    //根据tagID  删除Tag对象
    void delTag(int tagID);

    //根据tagID  更新Tag对象
    void updateTag(int tagID, Tag tag);

    //根据tagID  查找Tag对象
    Tag findTagByID(int tagID);

    //根据tagName  查找Tag对象
    //前台机制会保证tagName不重复
    Tag findTagByName(String tagName);

    //查找所有Tag对象
    ArrayList<Tag> findAllTags();
}
