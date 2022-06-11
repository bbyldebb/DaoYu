package com.daoyu.dao.impl;

import com.daoyu.dao.TagDao;
import com.daoyu.entity.Tag;
import com.daoyu.utils.Parse;
import com.daoyu.utils.connectUtil;

import java.sql.*;
import java.util.ArrayList;

public class TagDaoImpl implements TagDao {
    static Connection conn;

    static {
        conn = connectUtil.getConnection();
    }

    @Override
    public int addTag(Tag tag) {
        int id = -1;
        try {
            String sql = "insert into tag values(null,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tag.getTagName());
            stmt.setString(2, Parse.listToString(tag.getPosts()));
            stmt.executeUpdate();
            //获取自增的tagID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("addTag失败");
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void delTag(int tagID) {
        try {
            String sql = "delete from tag where tagID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tagID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("delTag失败");
            e.printStackTrace();
        }
    }

    @Override
    public void updateTag(int tagID, Tag tag) {
        try {
            String sql = "update tag " +
                    "set tagName = ? ,posts = ? " +
                    "WHERE tagID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tag.getTagName());
            stmt.setString(2, Parse.listToString(tag.getPosts()));
            stmt.setInt(3, tagID);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("updateTag失败");
            e.printStackTrace();
        }
    }

    @Override
    public Tag findTagByID(int tagID) {
        Tag tag = null;
        try {
            String sql = "select * from tag where tagID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, tagID);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                tag = new Tag();
                tag.setTagID(res.getInt("tagID"));
                tag.setTagName(res.getString("tagName"));
                tag.setPosts(Parse.stringToList(res.getString("posts")));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findTagByID失败");
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    //前台机制会保证tagName不重复
    public Tag findTagByName(String tagName) {
        Tag tag = null;
        try {
            String sql = "select * from tag where tagName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tagName);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                tag = new Tag();
                tag.setTagID(res.getInt("tagID"));
                tag.setTagName(res.getString("tagName"));
                tag.setPosts(Parse.stringToList(res.getString("posts")));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findTagByName失败");
            e.printStackTrace();
        }
        return tag;
    }

    @Override
    public ArrayList<Tag> findAllTags() {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            String sql = "select * from tag";
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                Tag tag = new Tag();
                tag.setTagID(res.getInt("tagID"));
                tag.setTagName(res.getString("tagName"));
                tag.setPosts(Parse.stringToList(res.getString("posts")));
                tags.add(tag);
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println("findAllTags失败");
            e.printStackTrace();
        }
        return tags;
    }
}
