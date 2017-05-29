package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.CommentRetrieveAndPostRequest;
import com.patho.messenger.model.CommentConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 20.03.2017.
 */

public class CommentConnectController extends AppCompatActivity {

    CommentRetrieveAndPostRequest crp;
    public static String commentId;
    public static String commentVoter;
    public void getCommentConnect(Response.Listener<String> listener,
                               Response.ErrorListener errlsn) {
        String url = CommentRetrieveAndPostRequest.COMMENT_CONNECTION_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void parseCommentConnectJSON(String response){
        String commentId="";
        String commentVoter="";
        CommentConnection.commentConnectionList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(CommentRetrieveAndPostRequest.RESULT_COMMENT_CONNECT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                commentId = collegeData.getString(CommentRetrieveAndPostRequest.KEY_COMMENT_LIKE_ID).toString();
                commentVoter = collegeData.getString(CommentRetrieveAndPostRequest.KEY_COMMENT_LIKE_VOTER).toString();
                CommentConnection commentConnection = new CommentConnection(commentId,commentVoter);
                CommentConnection.commentConnectionList.add(commentConnection);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void changeCommentLikeStatus() throws JSONException {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, CommentRetrieveAndPostRequest.COMMENT_LIKE_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("response",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            Log.v("response error",error.toString());
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(CommentRetrieveAndPostRequest.KEY_COMMENT_LIKE_ID, commentId);
                params.put(CommentRetrieveAndPostRequest.KEY_COMMENT_LIKE_VOTER, commentVoter);
                Log.v("ASD", "ID: "+commentId + " Voter: "+ commentVoter);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public static String getCommentId() {
        return commentId;
    }

    public static void setCommentId(String commentId) {
        CommentConnectController.commentId = commentId;
    }

    public static String getCommentVoter() {
        return commentVoter;
    }

    public static void setCommentVoter(String commentVoter) {
        CommentConnectController.commentVoter = commentVoter;
    }

}