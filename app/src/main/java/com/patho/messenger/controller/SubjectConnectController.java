package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.SubjectRetrieveAndPostRequest;
import com.patho.messenger.model.SubjectConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by eren on 20.03.2017.
 */

public class SubjectConnectController extends AppCompatActivity {

    public void getSubjectConnect(Response.Listener<String> listener,
                               Response.ErrorListener errlsn) {
        String url = SubjectRetrieveAndPostRequest.SUBJECT_CONNECTION_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void parseSubjectConnectJSON(String response){
        String subjectTitle="";
        String username="";
        SubjectConnection.subjectConnectionList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(SubjectRetrieveAndPostRequest.RESULT_SUBJECT_CONNECT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                subjectTitle = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_TITLE).toString();
                username = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_FOLLOWER).toString();
                SubjectConnection subjectConnection = new SubjectConnection(subjectTitle,username);
                SubjectConnection.subjectConnectionList.add(subjectConnection);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
}
}