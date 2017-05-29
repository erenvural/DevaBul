package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 13.04.2017.
 */

public class CommentConnection {

    String commentId, commentVoter, voteStatus;

    //CommentConnectionList
    public static ArrayList<CommentConnection> commentConnectionList = new ArrayList<>();

    public CommentConnection(){
    }

    public CommentConnection(String commentId, String commentVoter) {
        this.commentId = commentId;
        this.commentVoter = commentVoter;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentVoter() {
        return commentVoter;
    }

    public void setCommentVoter(String commentVoter) {
        this.commentVoter = commentVoter;
    }


    public static ArrayList<CommentConnection> getCommentConnectionList() {
        return commentConnectionList;
    }

    public static void setCommentConnectionList(ArrayList<CommentConnection> commentConnectionList) {
        CommentConnection.commentConnectionList = commentConnectionList;
    }
}
