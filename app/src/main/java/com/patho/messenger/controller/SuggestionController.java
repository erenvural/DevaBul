package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.DiseaseSuggestRetrieveAndPostRequest;
import com.patho.messenger.model.DiseaseSuggestion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by eren on 2.05.2017.
 */

public class SuggestionController extends AppCompatActivity{

    public void getSuggest(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = DiseaseSuggestRetrieveAndPostRequest.SUGGEST_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public static void showJSONfromSuggestResponse(String response){
        int id;
        String suggestName="";
        String suggestType="";
        String suggestDescription="";
        String suggestOwner="";
        int suggestConfirm;
        DiseaseSuggestion.suggestionList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(DiseaseSuggestRetrieveAndPostRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                id = collegeData.getInt(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_ID);
                suggestName = collegeData.getString(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_NAME).toString();
                suggestType = collegeData.getString(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_TYPE).toString();;
                suggestDescription = collegeData.getString(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_DESCRIPTION).toString();;
                suggestOwner = collegeData.getString(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_OWNER);
                String dateStr = collegeData.getString(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_DATE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                suggestConfirm = collegeData.getInt(DiseaseSuggestRetrieveAndPostRequest.KEY_SUGGEST_CONFIRM);
                DiseaseSuggestion diseaseSuggestion = new DiseaseSuggestion(id,suggestName,suggestType,suggestDescription,suggestOwner,dateStr,suggestConfirm);
                DiseaseSuggestion.suggestionList.add(diseaseSuggestion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


