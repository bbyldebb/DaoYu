package com.daoyu.entity;

import java.util.ArrayList;

public class Tag {
    int tagID;
    String tagName;
    ArrayList<Integer> posts;

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ArrayList<Integer> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagID=" + tagID +
                ", tagName='" + tagName + '\'' +
                ", posts=" + posts +
                '}';
    }
}
