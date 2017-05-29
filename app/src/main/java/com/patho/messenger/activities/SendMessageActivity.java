package com.patho.messenger.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.dao.MessageRetrieveAndPostRequest;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 26.03.2017.
 */

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener{

    String receiverName, senderName, subjectTitle;
    private EditText et_messageContent;
    private Button bt_sendMessage,bt_cancelMessage;
    MessageRetrieveAndPostRequest mr;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        Bundle extras = getIntent().getExtras();
        receiverName = extras.getString("receiverName");
        senderName = extras.getString("senderName");
        subjectTitle=extras.getString("subjectTitle");

        final TextView tv_receiverName = (TextView) findViewById(R.id.tv_receiverName);
        et_messageContent = (EditText) findViewById(R.id.et_messageContent);
        et_messageContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                enableButton();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                enableButton();
            }
        });
        bt_cancelMessage = (Button) findViewById(R.id.bt_cancelMessage);
        bt_sendMessage = (Button) findViewById(R.id.bt_sendMessage);
        bt_sendMessage.setEnabled(false);
        bt_sendMessage.setOnClickListener(this);
        bt_cancelMessage.setOnClickListener(this);
        tv_receiverName.setText("Alıcı:"+receiverName);
    }

    private void sendMessage(String sender, String  receiver) throws JSONException {
        final String messageContent = et_messageContent.getText().toString();
        final String senderName = sender;
        final String receiverName = receiver;

        final Date date = new Date();
        final String dtStart = sdf.format(date);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, mr.MESSAGE_CREATE_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SendMessageActivity.this,"Başarıyla Mesajınız Yollandı!",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            volleyError = error;
                        }
                        Log.v("Subject alınan hata",volleyError.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(mr.KEY_MESSAGE_SENDER, senderName);
                params.put(mr.KEY_MESSAGE_RECEIVER, receiverName);
                params.put(mr.KEY_MESSAGE_CONTENT, messageContent);
                params.put(mr.KEY_MESSAGE_DATE, dtStart);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
        Intent subjectActivity = new Intent(SendMessageActivity.this, SubjectActivity.class);
        subjectActivity.putExtra("subjectTitle",subjectTitle);
        SendMessageActivity.this.startActivity(subjectActivity);
    }

    @Override
    public void onClick(View v) {
        if(v == bt_sendMessage){
            try {
                sendMessage(senderName,receiverName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(v == bt_cancelMessage){
            Intent subjectActivity = new Intent(SendMessageActivity.this, SubjectActivity.class);
            subjectActivity.putExtra("subjectTitle",subjectTitle);
            SendMessageActivity.this.startActivity(subjectActivity);
        }
    }

    public void enableButton() {
        boolean isReady;
        if (et_messageContent.getText().toString().length()!=0){
            isReady=true;
        }else{
            isReady=false;
        }
        bt_sendMessage.setEnabled(isReady);
    }
}
