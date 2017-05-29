package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 20.03.2017.
 */

public class SubjectConnection {

    String subjectTitle, username;
    //SubjectConnectionList
    public static ArrayList<SubjectConnection> subjectConnectionList = new ArrayList<>();

    public SubjectConnection(){
    }

    public SubjectConnection(String subjectTitle, String username) {
        this.subjectTitle = subjectTitle;
        this.username = username;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static ArrayList<SubjectConnection> getSubjectConnectionList() {
        return subjectConnectionList;
    }

    public static void setSubjectConnectionList(ArrayList<SubjectConnection> subjectConnectionList) {
        SubjectConnection.subjectConnectionList = subjectConnectionList;
    }
}
