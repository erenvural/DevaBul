package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 16.03.2017.
 */

public class Comment {

    private int id;
    private String commentSubject;
    private String commentOwner;
    private String commentContent;
    private int commentTotalLikes;
    private String commentDate;

    //CommentList
    public static ArrayList<Comment> commentList = new ArrayList<>();

    public Comment (){

    }
    public Comment(int id, String commentSubject,String commentOwner,String commentContent,int commentTotalLikes, String commentDate){
        this.id=id;
        this.commentSubject=commentSubject;
        this.commentOwner=commentOwner;
        this.commentContent=commentContent;
        this.commentTotalLikes=commentTotalLikes;
        this.commentDate = commentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentSubject() {
        return commentSubject;
    }

    public void setCommentSubject(String commentSubject) {
        this.commentSubject = commentSubject;
    }

    public String getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(String commentOwner) {
        this.commentOwner = commentOwner;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentTotalLikes() {
        return commentTotalLikes;
    }

    public void setCommentTotalLikes(int commentTotalLikes) {
        this.commentTotalLikes = commentTotalLikes;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public static ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public static void setCommentList(ArrayList<Comment> commentList) {
        Comment.commentList = commentList;
    }
}
