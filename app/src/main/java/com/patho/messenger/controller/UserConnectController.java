package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.UserConnectionRequest;
import com.patho.messenger.model.UserConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by eren on 18.03.2017.
 */

public class UserConnectController extends AppCompatActivity{

    public void getUserConnect(Response.Listener<String> listener,
                        Response.ErrorListener errlsn) {
        String url = UserConnectionRequest.USER_CONNECT_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void parseUserConnectJSON(String response){
        String followingUserName="";
        String followedUserName="";
        UserConnection.userConnectionList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(UserConnectionRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                followingUserName = collegeData.getString(UserConnectionRequest.KEY_FOLLOWING_USER_NAME).toString();
                followedUserName = collegeData.getString(UserConnectionRequest.KEY_FOLLOWED_USER_NAME).toString();
                UserConnection userConnection = new UserConnection(followingUserName,followedUserName);
                UserConnection.userConnectionList.add(userConnection);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
