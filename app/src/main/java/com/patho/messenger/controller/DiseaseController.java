package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.DiseaseRequest;
import com.patho.messenger.model.Disease;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by eren on 16.03.2017.
 */

public class DiseaseController extends AppCompatActivity {

    public void getDisease(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = DiseaseRequest.DISEASE_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void parseDiseaseJSON(String response){
        int diseaseId;
        String diseaseName="";
        String diseaseType="";
        String diseaseDescription="";
        Disease.diseaseList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(DiseaseRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                diseaseId = collegeData.getInt(DiseaseRequest.KEY_DISEASE_ID);
                diseaseName = collegeData.getString(DiseaseRequest.KEY_DISEASE_NAME).toString();
                diseaseType = collegeData.getString(DiseaseRequest.KEY_DISAEASE_TYPE).toString();
                diseaseDescription = collegeData.getString(DiseaseRequest.KEY_DISAEASE_DESCRIPTION).toString();
                Disease disease = new Disease(diseaseId, diseaseName,diseaseType,diseaseDescription);
                Disease.diseaseList.add(disease);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
