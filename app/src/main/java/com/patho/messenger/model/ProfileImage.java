package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 20.03.2017.
 */

public class ProfileImage {

    private int id;
    private String username;
    private String image_url;

    //Profile Images List
    public static ArrayList<ProfileImage> profileImageList = new ArrayList<>();

    public ProfileImage(){
    }

    public ProfileImage(int id, String username, String image_url) {
        this.id = id;
        this.username = username;
        this.image_url = image_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public static ArrayList<ProfileImage> getProfileImageList() {
        return profileImageList;
    }

    public static void setProfileImageList(ArrayList<ProfileImage> profileImageList) {
        ProfileImage.profileImageList = profileImageList;
    }
}
