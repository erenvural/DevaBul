package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.SubjectRetrieveAndPostRequest;
import com.patho.messenger.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 16.03.2017.
 */

public class SubjectController extends AppCompatActivity{

    String subjectTitle, likeStatus, username;
    public void getSubject(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = SubjectRetrieveAndPostRequest.SUBJECT_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void parseJSONfromDiseaseResponse(String response){
        int subjectId;
        String subjectDisease="";
        String subjectTitle="";
        String subjectContent="";
        String subjectOwner="";
        Date subjectDate;
        Subject.subjectList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(SubjectRetrieveAndPostRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                subjectId = collegeData.getInt(SubjectRetrieveAndPostRequest.KEY_SUBJECT_ID);
                subjectDisease = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_DISEASE).toString();
                subjectTitle = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_TITLE).toString();;
                subjectContent = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_CONTENT).toString();;
                subjectOwner = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_OWNER).toString();;
                String dateStr = collegeData.getString(SubjectRetrieveAndPostRequest.KEY_SUBJECT_DATE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                subjectDate = sdf.parse(dateStr);
                Subject subject = new Subject(subjectId, subjectDisease,subjectTitle,subjectContent,subjectOwner,dateStr);
                Subject.subjectList.add(subject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void changeLikeSubjectStatus(String subject_Title, final String like_Status, final String user_name, Response.Listener<String> listener, Response.ErrorListener errorListener) throws JSONException {
        String url = SubjectRetrieveAndPostRequest.SUBJECT_LIKE_REQUEST_URL;
        this.subjectTitle = subject_Title;
        this.likeStatus = like_Status;
        this.username = user_name;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Controller Response: ",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            Log.v("Control Volley Error: ",error.toString());
                            error.printStackTrace();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(SubjectRetrieveAndPostRequest.KEY_LIKE_STATUS, like_Status );
                params.put(SubjectRetrieveAndPostRequest.KEY_SUBJECT_TITLE, subjectTitle);
                params.put(SubjectRetrieveAndPostRequest.KEY_SUBJECT_FOLLOWER,username);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
