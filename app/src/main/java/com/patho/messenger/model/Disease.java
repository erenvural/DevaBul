package com.patho.messenger.model;

import java.util.ArrayList;

/**
 * Created by eren on 9.03.2017.
 */

public class Disease {

    private int id;
    private String diseaseName;
    private String diseaseType;
    private String diseaseDescription;

    //DiseaseList
    public static ArrayList<Disease> diseaseList = new ArrayList<>();

    public Disease(){
    }

    public Disease(int id,String diseaseName,String diseaseType,String diseaseDescription){
        this.id = id;
        this.diseaseName=diseaseName;
        this.diseaseType=diseaseType;
        this.diseaseDescription=diseaseDescription;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public static ArrayList<Disease> getDiseaseList() {
        return diseaseList;
    }

    public static void setDiseaseList(ArrayList<Disease> diseaseList) {
        Disease.diseaseList = diseaseList;
    }
}
