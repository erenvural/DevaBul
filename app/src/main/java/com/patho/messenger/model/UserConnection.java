package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 18.03.2017.
 */

public class UserConnection {

    String followingUserName, followedUserName;
    //UserConnectionList
    public static ArrayList<UserConnection> userConnectionList = new ArrayList<>();

    public UserConnection(){

    }
    public UserConnection(String followingUserName, String followedUserName) {
        this.followingUserName = followingUserName;
        this.followedUserName = followedUserName;
    }

    public String getFollowingUserName() {
        return followingUserName;
    }

    public void setFollowingUserName(String followingUserName) {
        this.followingUserName = followingUserName;
    }

    public String getFollowedUserName() {
        return followedUserName;
    }

    public void setFollowedUserName(String followedUserName) {
        this.followedUserName = followedUserName;
    }

    public static ArrayList<UserConnection> getUserConnectionList() {
        return userConnectionList;
    }

    public static void setUserConnectionList(ArrayList<UserConnection> userConnectionList) {
        UserConnection.userConnectionList = userConnectionList;
    }
}
