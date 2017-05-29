package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 15.02.2017.
 */


public class User {

    private int id;
    String profile_status, username, email, password, birthday, gender, relatedDisease;
    //UserList
    public static ArrayList<User> userList = new ArrayList<>();

    public User(){
    }

    public User(int id,String profile_status,String username,String email,String birthday,String gender, String relatedDisease){
        this.id = id;
        this.profile_status=profile_status;
        this.username=username;
        this.email=email;
        this.birthday=birthday;
        this.gender=gender;
        this.relatedDisease=relatedDisease;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_status() {
        return profile_status;
    }

    public void setProfile_status(String profile_status) {
        this.profile_status = profile_status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelatedDisease() {
        return relatedDisease;
    }

    public void setRelatedDisease(String relatedDisease) {
        this.relatedDisease = relatedDisease;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<User> userList) {
        User.userList = userList;
    }
}

