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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.UserSessionManager;
import com.patho.messenger.dao.SubjectRetrieveAndPostRequest;
import com.patho.messenger.model.Subject;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 14.03.2017.
 */

public class OpenSubjectActivity extends AppCompatActivity implements View.OnClickListener {

    UserSessionManager session;
    String activeUser, diseaseName;
    SubjectRetrieveAndPostRequest sr;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date subjectDate;

    private EditText etTopicTitle, etTopicContent;
    private Button bt_CompleteTopic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_subject);

        //Açılan Konuyu kişiyi Bulmak İçin
        session = new UserSessionManager(getApplicationContext());
        session.checkLogin();
        final HashMap<String, String> user = session.getUserDetails();
        activeUser = user.get(UserSessionManager.KEY_NAME);

        Bundle extras = getIntent().getExtras();
        diseaseName = extras.getString("diseaseName");
        bt_CompleteTopic = (Button) findViewById(R.id.bt_CompleteTopic);
        bt_CompleteTopic.setEnabled(false);
        etTopicTitle = (EditText) findViewById(R.id.et_topicTitle);
        etTopicTitle.addTextChangedListener(new TextWatcher() {
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

        etTopicContent = (EditText) findViewById(R.id.et_TopicContent);
        etTopicContent.addTextChangedListener(new TextWatcher() {
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
        bt_CompleteTopic.setOnClickListener(this);

    }

    private void addSubject(String activeUser ) throws JSONException {
        final String topicDisease=diseaseName;
        final String topicTitle = etTopicTitle.getText().toString();
        final String topicContent = etTopicContent.getText().toString();
        final String username = activeUser;

        final Date date = new Date();
        final String dtStart = sdf.format(date);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
             subjectDate = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        StringRequest stringRequest = new StringRequest(Request.Method.POST, sr.SUBJECT_OPENING_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(OpenSubjectActivity.this,"Başarıyla Konu Açtınız !",Toast.LENGTH_LONG).show();
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
                params.put(sr.KEY_SUBJECT_DISEASE, topicDisease);
                params.put(sr.KEY_SUBJECT_TITLE, topicTitle);
                params.put(sr.KEY_SUBJECT_CONTENT, topicContent);
                params.put(sr.KEY_SUBJECT_OWNER, username);
                params.put(sr.KEY_SUBJECT_DATE, dtStart);
                return params;
            }
        };

        Subject sbj = new Subject(Subject.subjectList.size()+1,topicDisease,topicTitle,topicContent,activeUser, dtStart);
        Subject.subjectList.add(sbj);
        AppController.getInstance().addToRequestQueue(stringRequest);
        Intent diseaseTopicActivity = new Intent(OpenSubjectActivity.this, DiseaseActivity.class);
        diseaseTopicActivity.putExtra("diseaseName",topicDisease);
        diseaseTopicActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        OpenSubjectActivity.this.startActivity(diseaseTopicActivity);

    }
    @Override
    public void onClick(View v) {
        if(v == bt_CompleteTopic){
            try {
                addSubject(activeUser);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void enableButton() {
        boolean isReady;
        if (etTopicTitle.getText().toString().length()!=0 && etTopicContent.getText().toString().length()!=0){
            isReady=true;
        }else{
            isReady=false;
        }
        bt_CompleteTopic.setEnabled(isReady);
    }
}

