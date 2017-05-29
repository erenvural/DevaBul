package com.patho.messenger.controller;

import android.support.v7.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.dao.MessageRetrieveAndPostRequest;
import com.patho.messenger.model.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eren on 26.03.2017.
 */

public class MessageController extends AppCompatActivity{

    public void getMessage(Response.Listener<String> listener,
                           Response.ErrorListener errlsn) {
        String url = MessageRetrieveAndPostRequest.MESSAGE_REQUEST_URL;
        StringRequest stringRequest = new StringRequest (url, listener, errlsn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public void parseJSONfromMessageResponse(String response){
        int messageId;
        String sender="";
        String receiver="";
        String messageContent="";
        Date subjectDate;
        Message.messageList.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(MessageRetrieveAndPostRequest.RESULT_ARRAY);
            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                messageId = collegeData.getInt(MessageRetrieveAndPostRequest.KEY_MESSAGE_ID);
                sender = collegeData.getString(MessageRetrieveAndPostRequest.KEY_MESSAGE_SENDER).toString();
                receiver = collegeData.getString(MessageRetrieveAndPostRequest.KEY_MESSAGE_RECEIVER).toString();;
                messageContent = collegeData.getString(MessageRetrieveAndPostRequest.KEY_MESSAGE_CONTENT).toString();;
                String dateStr = collegeData.getString(MessageRetrieveAndPostRequest.KEY_MESSAGE_DATE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                subjectDate = sdf.parse(dateStr);
                Message message = new Message(messageId, sender,receiver,messageContent,dateStr);
                Message.messageList.add(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
