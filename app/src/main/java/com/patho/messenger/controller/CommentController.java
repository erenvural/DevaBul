package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.CommentRetrieveAndPostRequest;
import com.patho.messenger.model.Comment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eren on 16.03.2017.
 */

public class CommentController extends AppCompatActivity {

    public void getComment(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = CommentRetrieveAndPostRequest.COMMENT_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void showJSONfromCommentResponse(String response){
        int commentId;
        String commentSubject="";
        String commentOwner="";
        String commentContent="";
        int commentTotalLikes;
        Date commentDate;
        Comment.commentList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(CommentRetrieveAndPostRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                commentId = collegeData.getInt(CommentRetrieveAndPostRequest.KEY_COMMENT_ID);
                commentSubject = collegeData.getString(CommentRetrieveAndPostRequest.KEY_COMMENT_SUBJECT).toString();
                commentOwner = collegeData.getString(CommentRetrieveAndPostRequest.KEY_COMMENT_OWNER).toString();;
                commentContent = collegeData.getString(CommentRetrieveAndPostRequest.KEY_COMMENT_CONTENT).toString();;
                commentTotalLikes = collegeData.getInt(CommentRetrieveAndPostRequest.KEY_COMMENT_TOTAL_LIKES);;
                String dateStr = collegeData.getString(CommentRetrieveAndPostRequest.KEY_COMMENT_DATE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                commentDate = sdf.parse(dateStr);
                Comment comment = new Comment(commentId, commentSubject,commentOwner,commentContent,commentTotalLikes,dateStr);
                Comment.commentList.add(comment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
