package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.ProfileImageRetrieveAndPostRequest;
import com.patho.messenger.dao.UserRequest;
import com.patho.messenger.model.ProfileImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 17.03.2017.
 */

public class ProfileSettingsController extends AppCompatActivity {

    String username, profile_status, relatedDisease, image_url;

    public void changeProfileStatus(String user_name, String profilestatus, Response.Listener<String> listener, Response.ErrorListener errorListener) throws JSONException {
        String url ="*/LoginStatusChange.php";
        this.username=user_name;
        this.profile_status=profilestatus;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            error.printStackTrace();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(UserRequest.KEY_USER_USERNAME, username);
                params.put(UserRequest.KEY_USER_PROFILE_STATUS, profile_status);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void changeRelatedDisease(String user_name, String related_Disease, Response.Listener<String> listener, Response.ErrorListener errorListener) throws JSONException {
        String url ="*/RelatedDiseaseChange.php";
        this.username=user_name;
        this.relatedDisease=related_Disease;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            error.printStackTrace();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(UserRequest.KEY_USER_USERNAME, username);
                params.put(UserRequest.KEY_USER_RELATED_DISEASE, relatedDisease);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void getProfileImage(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = ProfileImageRetrieveAndPostRequest.PROFILE_IMAGE_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }


    public void parseProfileImageJSON(String response){
        int profileImageId;
        String username="";
        String image_url="";
        //ProfileImage.profileImageList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(ProfileImageRetrieveAndPostRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                profileImageId = collegeData.getInt(ProfileImageRetrieveAndPostRequest.KEY_PROFILE_IMAGE_ID);
                username = collegeData.getString(ProfileImageRetrieveAndPostRequest.KEY_USER_NAME).toString();
                image_url = collegeData.getString(ProfileImageRetrieveAndPostRequest.KEY_IMAGE_URL).toString();
                ProfileImage profileImage = new ProfileImage(profileImageId, username,image_url);
                ProfileImage.profileImageList.add(profileImage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
