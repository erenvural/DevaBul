package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 13.03.2017.
 */

public class Subject {


    private int id;
    private String subjectDisease;
    private String subjectTitle;
    private String subjectContent;
    private String subjectOwner;
    private String subjectDate;


    //SubjectList
    public static ArrayList<Subject> subjectList = new ArrayList<>();

    public Subject(){

    }

    public Subject (int id, String subjectDisease, String subjectTitle, String subjectContent, String subjectOwner, String subjectDate){
        this.id = id;
        this.subjectDisease=subjectDisease;
        this.subjectTitle=subjectTitle;
        this.subjectContent=subjectContent;
        this.subjectOwner=subjectOwner;
        this.subjectDate=subjectDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectDisease() {
        return subjectDisease;
    }

    public void setSubjectDisease(String subjectDisease) {
        this.subjectDisease = subjectDisease;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    public String getSubjectContent() {
        return subjectContent;
    }

    public void setSubjectContent(String subjectContent) {
        this.subjectContent = subjectContent;
    }

    public String getSubjectOwner() {
        return subjectOwner;
    }

    public void setSubjectOwner(String subjectOwner) {
        this.subjectOwner = subjectOwner;
    }

    public String getSubjectDate() {
        return subjectDate;
    }

    public void setSubjectDate(String subjectDate) {
        this.subjectDate = subjectDate;
    }

    public static ArrayList<Subject> getSubjectList() {
        return subjectList;
    }

    public static void setSubjectList(ArrayList<Subject> subjectList) {
        Subject.subjectList = subjectList;
    }


}
