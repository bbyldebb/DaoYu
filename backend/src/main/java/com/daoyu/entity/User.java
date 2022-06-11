package com.daoyu.entity;

import java.util.ArrayList;

public class User {
    String userID;
    String nickName;
    String avatarImg;
    String phoneNum;
    String address;
    String birthday;
    String introduction;
    int gender; //1：男   2：女
    ArrayList<Integer> posts;
    ArrayList<Integer> followPosts;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public ArrayList<Integer> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Integer> posts) {
        this.posts = posts;
    }

    public ArrayList<Integer> getFollowPosts() {
        return followPosts;
    }

    public void setFollowPosts(ArrayList<Integer> followPosts) {
        this.followPosts = followPosts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarImg='" + avatarImg + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", introduction='" + introduction + '\'' +
                ", gender=" + gender +
                ", posts=" + posts +
                ", followPosts=" + followPosts +
                '}';
    }
}
