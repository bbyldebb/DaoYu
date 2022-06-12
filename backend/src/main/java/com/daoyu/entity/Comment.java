package com.daoyu.entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    int commentID;
    String userID;
    int postID;
    String content;
    Timestamp time;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    //计算字段timeStr，不存储，需要时计算
    public String getTimeStr() {
        return convertTimestamp2Date(time.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", userID='" + userID + '\'' +
                ", postID=" + postID +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", timeStr=" + getTimeStr() +
                '}';
    }

    public static String convertTimestamp2Date(Long timestamp, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(timestamp));
    }
}
