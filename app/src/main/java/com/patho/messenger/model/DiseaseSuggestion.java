package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 2.05.2017.
 */

public class DiseaseSuggestion {

    private int id;
    private String suggestName;
    private String suggestType;
    private String suggestDescription;
    private String suggestOwner;
    private String suggestDate;
    private int suggestConfirm;

    //SuggestList
    public static ArrayList<DiseaseSuggestion> suggestionList = new ArrayList<>();

    public DiseaseSuggestion (){

    }

    public DiseaseSuggestion(int id, String suggestName, String suggestType, String suggestDescription, String suggestOwner, String suggestDate, int suggestConfirm) {
        this.id = id;
        this.suggestName = suggestName;
        this.suggestType = suggestType;
        this.suggestDescription = suggestDescription;
        this.suggestOwner = suggestOwner;
        this.suggestDate = suggestDate;
        this.suggestConfirm = suggestConfirm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuggestName() {
        return suggestName;
    }

    public void setSuggestName(String suggestName) {
        this.suggestName = suggestName;
    }

    public String getSuggestType() {
        return suggestType;
    }

    public void setSuggestType(String suggestType) {
        this.suggestType = suggestType;
    }

    public String getSuggestDescription() {
        return suggestDescription;
    }

    public void setSuggestDescription(String suggestDescription) {
        this.suggestDescription = suggestDescription;
    }

    public String getSuggestOwner() {
        return suggestOwner;
    }

    public void setSuggestOwner(String suggestOwner) {
        this.suggestOwner = suggestOwner;
    }

    public String getSuggestDate() {
        return suggestDate;
    }

    public void setSuggestDate(String suggestDate) {
        this.suggestDate = suggestDate;
    }

    public int getSuggestConfirm() {
        return suggestConfirm;
    }

    public void setSuggestConfirm(int suggestConfirm) {
        this.suggestConfirm = suggestConfirm;
    }

    public static ArrayList<DiseaseSuggestion> getSuggestionList() {
        return suggestionList;
    }

    public static void setSuggestionList(ArrayList<DiseaseSuggestion> suggestionList) {
        DiseaseSuggestion.suggestionList = suggestionList;
    }
}
