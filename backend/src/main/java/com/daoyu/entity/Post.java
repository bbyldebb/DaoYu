package com.daoyu.entity;

import com.daoyu.utils.HotNumCalculator;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Post {
    int postID;
    String userID;
    String content;
    String image;
    Timestamp time;
    String address;
    ArrayList<Integer> tags;
    int status;
    int followNum;
    int commentNum;
    ArrayList<Integer> comments;

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Integer> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Integer> tags) {
        this.tags = tags;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public ArrayList<Integer> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
    }

    //计算字段hotNum，不存储，需要时计算
    public int getHotNum() {
        return HotNumCalculator.calHotNum(followNum, commentNum, status, time);
    }

    //计算字段timeStr，不存储，需要时计算
    public String getTimeStr() {
        return convertTimestamp2Date(time.getTime(), "yyyy-MM-dd HH:mm:ss");
    }


    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", userID='" + userID + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", time=" + time +
                ", address='" + address + '\'' +
                ", tags=" + tags +
                ", status=" + status +
                ", followNum=" + followNum +
                ", commentNum=" + commentNum +
                ", comments=" + comments +
                ", hotNum=" + getHotNum() +
                ", timeStr=" + getTimeStr() +
                '}';
    }

    public static String convertTimestamp2Date(Long timestamp, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timestamp));
    }
}
