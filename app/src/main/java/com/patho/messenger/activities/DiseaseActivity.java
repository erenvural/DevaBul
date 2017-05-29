package com.patho.messenger.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.patho.messenger.R;
import com.patho.messenger.controller.CommentConnectController;
import com.patho.messenger.controller.CommentController;
import com.patho.messenger.controller.SubjectController;
import com.patho.messenger.model.Disease;
import com.patho.messenger.model.Subject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by eren on 13.03.2017.
 */

public class DiseaseActivity extends AppCompatActivity {

    String diseaseName;
    String diseaseDescription;
    ArrayList<String> sampleSubjects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        Bundle extras = getIntent().getExtras();
        diseaseName = extras.getString("diseaseName");

        //Konuların Çekildiği Yer
        final SubjectController subjectController = new SubjectController();
        subjectController.getSubject(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        subjectController.parseJSONfromDiseaseResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Yorum Çekilme Yeri
        final CommentController commentController = new CommentController();
        commentController.getComment(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        commentController.showJSONfromCommentResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Yorum Beğenme İlişkisi Çekilme Yeri
        final CommentConnectController commentConnectController = new CommentConnectController();
        commentConnectController.getCommentConnect(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        commentConnectController.parseCommentConnectJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        for (int i=0;i< Subject.subjectList.size();i++){
            if (diseaseName.contains(Subject.subjectList.get(i).getSubjectDisease())){
                sampleSubjects.add(Subject.subjectList.get(i).getSubjectTitle().toString()+
                "\nKonu Sahibi: "+ Subject.subjectList.get(i).getSubjectOwner().toString()+" ("+
                        Subject.subjectList.get(i).getSubjectDate().toString()+")");
            }
        }
        Collections.reverse(sampleSubjects);

        final Button bt_openTopic = (Button) findViewById(R.id.bt_openSubject);
        final TextView tv_diseaseNameOnDA = (TextView) findViewById(R.id.tv_diseaseNameOnDA);
        final TextView tv_subjectNumber = (TextView) findViewById(R.id.tv_subjectNumber);
        final EditText et_diseaseDescription = (EditText) findViewById(R.id.et_diseaseDescription);
        final ListView listView = (ListView) findViewById(R.id.lv_subjectList);
        tv_subjectNumber.setText(sampleSubjects.size()+" Konu");

                et_diseaseDescription.setEnabled(false);
        tv_diseaseNameOnDA.setText(diseaseName);
        for (int i=0;i< Disease.diseaseList.size();i++){
            if (diseaseName.contains(Disease.diseaseList.get(i).getDiseaseName())){
                diseaseDescription = Disease.diseaseList.get(i).getDiseaseDescription();
            }
        }
        et_diseaseDescription.setText(diseaseDescription);

       final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, sampleSubjects);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String  itemValue    = (String) listView.getItemAtPosition(position);
                itemValue = itemValue.substring(0,itemValue.indexOf("\n"));
                Intent diseaseTopicActivity = new Intent(DiseaseActivity.this, SubjectActivity.class);
                diseaseTopicActivity.putExtra("subjectTitle",itemValue);
                DiseaseActivity.this.startActivity(diseaseTopicActivity);
            }
        });

        bt_openTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTopicActivityIntent = new Intent(DiseaseActivity.this, OpenSubjectActivity.class);
                openTopicActivityIntent.putExtra("diseaseName",diseaseName);
                DiseaseActivity.this.startActivity(openTopicActivityIntent);
            }
        });

    }
}
