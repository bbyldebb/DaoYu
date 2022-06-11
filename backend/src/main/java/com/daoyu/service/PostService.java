package com.daoyu.service;

import com.daoyu.dao.PostDao;
import com.daoyu.dao.TagDao;
import com.daoyu.dao.UserDao;
import com.daoyu.dao.impl.PostDaoImpl;
import com.daoyu.dao.impl.TagDaoImpl;
import com.daoyu.dao.impl.UserDaoImpl;
import com.daoyu.entity.Comment;
import com.daoyu.entity.Post;
import com.daoyu.entity.Tag;
import com.daoyu.entity.User;
import com.daoyu.utils.Parse;
import com.mysql.cj.xdevapi.JsonString;

import java.sql.Timestamp;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import com.alibaba.fastjson.JSONObject;

import javax.print.attribute.IntegerSyntax;

public class PostService {
    static PostDao postDao = new PostDaoImpl();
    static UserDao userDao = new UserDaoImpl();
    static TagDao tagDao = new TagDaoImpl();

    //返回 数据库中 所有帖子，根据热度排序
    static public ArrayList<Post> getAllPost() {
        ArrayList<Post> lst = postDao.findAllPosts();
        lst.sort(new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                return p2.getHotNum() - p1.getHotNum();
            }
        });
        return lst;
    }

    //返回 某个用户 发布 的所有帖子
    static public ArrayList<Post> getAllMyPost(String userID) {
        User user = userDao.findUserByID(userID);
        ArrayList<Post> myPosts = new ArrayList<Post>();
        if (user != null) {
            ArrayList<Integer> lst = user.getPosts();
            if (lst != null) {
                for (int postID : lst) {
                    Post post = postDao.findPostByID(postID);
                    if (post != null) {
                        myPosts.add(post);
                    }
                }
            }
        }
        return myPosts;
    }

    //返回 某个用户 关注 的所有帖子
    static public ArrayList<Post> getAllMyFollowPost(String userID) {
        User user = userDao.findUserByID(userID);
        ArrayList<Post> myFollowPosts = new ArrayList<Post>();
        if (user != null) {
            ArrayList<Integer> lst = user.getFollowPosts();
            if (lst != null) {
                for (int postID : lst) {
                    Post post = postDao.findPostByID(postID);
                    if (post != null) {
                        myFollowPosts.add(post);
                    }
                }
            }
        }
        return myFollowPosts;
    }

    //展示帖子详情页：返回 某条帖子 的所有具体信息
    static public Post getPostDetail(int postID) {
        return postDao.findPostByID(postID);
    }

    //关注帖子
    static public void followPost(String userID, int postID) {
        User user = userDao.findUserByID(userID);
        Post post = postDao.findPostByID(postID);
        if (user != null && post != null) {
            //先获取已经关注的postID
            ArrayList<Integer> followPosts = user.getFollowPosts();
            if (followPosts == null) followPosts = new ArrayList<Integer>();
            //如果已经关注，即刻返回
            for (int followPostID : followPosts) {
                if (followPostID == postID)
                    return;
            }
            //否则修改user，在followPosts后面添加一条
            UserService.appendUserFollowPost(userID, postID);
            //修改post，使followNum加一
            int followNum = post.getFollowNum();
            followNum += 1;
            post.setFollowNum(followNum);
            postDao.updatePost(postID, post);
        }
    }

    //取关帖子
    static public void unfollowPost(String userID, int postID) {
        User user = userDao.findUserByID(userID);
        Post post = postDao.findPostByID(postID);
        if (user != null && post != null) {
            //先获取已经关注的所有postID
            ArrayList<Integer> followPosts = user.getFollowPosts();
            if (followPosts == null) followPosts = new ArrayList<Integer>();
            //找到该条postID，删除
            for (int i = 0; i < followPosts.size(); i++) {
                if (followPosts.get(i) == postID) {
                    followPosts.remove(i);
                    //修改post，使followNum减一
                    int followNum = post.getFollowNum();
                    followNum -= 1;
                    post.setFollowNum(followNum);
                    postDao.updatePost(postID, post);
                    break;
                }
            }
            userDao.updateUser(userID, user);
        }
    }

    //修改帖子的status为 已解决
    static public void setPostStatusZero(int postID) {
        Post post = postDao.findPostByID(postID);
        if (post != null) {
            post.setStatus(0);
            postDao.updatePost(postID, post);
        }
    }

    //发布帖子
    static public int publishPost(String userID, String content, String image, String address, String tags, int status) {
        //新增一条post数据
        Post post = new Post();
        post.setUserID(userID);
        post.setContent(content);
        post.setImage(image);
        post.setTime(new Timestamp(System.currentTimeMillis()));
        post.setAddress(address);
        ArrayList<Integer> tagLst = Parse.stringToList(tags);
        post.setTags(tagLst);
        post.setStatus(status);
        post.setFollowNum(0);
        post.setCommentNum(0);
        post.setComments(new ArrayList<Integer>());
        int postID = postDao.addPost(post);
        //修改相关的user和tag
        if (postID != -1) {
            //该user的 posts字段 增加一条
            UserService.appendUserPost(userID, postID);
            //tagLst中，每个tag的 posts字段 增加一条
            for (int tagID : tagLst) {
                TagService.appendTagPost(tagID, postID);
            }
        }
        return postID;
    }

    //根据关键词搜索帖子，都按hotNum排序
    static public ArrayList<Post> searchPost(String keyword) {
        ArrayList<Post> res = new ArrayList<Post>();
        //如果有跟keyword一样的tag，就返回该tag的所有帖子
        Tag tag = tagDao.findTagByName(keyword);
        if (tag != null) {
            ArrayList<Integer> posts = tag.getPosts();
            if (posts == null) {
                posts = new ArrayList<Integer>();
            }
            for (int postID : posts) {
                res.add(postDao.findPostByID(postID));
            }
        } else {
            //如果没有对应tag，就按content搜索
            res = postDao.findPostByKeyword(keyword);
        }
        //都按hotNum排序
        res.sort(new Comparator<Post>() {
            @Override
            public int compare(Post p1, Post p2) {
                return p2.getHotNum() - p1.getHotNum();
            }
        });
        return res;
    }

    //comments字段增加一条
    //commentNum加一
    static public void appendPostComment(int postID, int commentID) {
        Post post = postDao.findPostByID(postID);
        if (post != null) {
            //comments字段 增加一条
            ArrayList<Integer> comments = post.getComments();
            if (comments == null) {
                comments = new ArrayList<Integer>();
            }
            comments.add(commentID);
            post.setComments(comments);
            //该post的 commentNum加一
            int commentNum = post.getCommentNum();
            commentNum += 1;
            post.setCommentNum(commentNum);
            //更新
            postDao.updatePost(postID, post);
        }
    }
}
