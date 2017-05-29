package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.UserRequest;
import com.patho.messenger.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by eren on 17.03.2017.
 */

public class UserController extends AppCompatActivity {

    public void getUser(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = UserRequest.USER_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void parseUserJSON(String response){
        int userId;
        String profile_status="";
        String username="";
        String email="";
        String birthday;
        String gender;
        String relatedDisease="";
        User.userList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(UserRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                userId = collegeData.getInt(UserRequest.KEY_USER_ID);
                profile_status = collegeData.getString(UserRequest.KEY_USER_PROFILE_STATUS).toString();
                username = collegeData.getString(UserRequest.KEY_USER_USERNAME).toString();
                email = collegeData.getString(UserRequest.KEY_USER_EMAIL).toString();
                birthday = collegeData.getString(UserRequest.KEY_USER_BIRTHDAY).toString();
                gender = collegeData.getString(UserRequest.KEY_USER_GENDER).toString();
                relatedDisease = collegeData.getString(UserRequest.KEY_USER_RELATED_DISEASE).toString();
                User user = new User(userId, profile_status,username,email,birthday,gender,relatedDisease);
                User.userList.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}



