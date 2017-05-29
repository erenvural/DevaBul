package com.patho.messenger.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.CommentConnectController;
import com.patho.messenger.controller.CommentController;
import com.patho.messenger.controller.SubjectConnectController;
import com.patho.messenger.controller.SubjectController;
import com.patho.messenger.controller.UserConnectController;
import com.patho.messenger.controller.UserSessionManager;
import com.patho.messenger.dao.CommentRetrieveAndPostRequest;
import com.patho.messenger.dao.SubjectRetrieveAndPostRequest;
import com.patho.messenger.dao.UserConnectionRequest;
import com.patho.messenger.model.Comment;
import com.patho.messenger.model.CommentConnection;
import com.patho.messenger.model.Subject;
import com.patho.messenger.model.SubjectConnection;
import com.patho.messenger.model.UserConnection;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 13.03.2017.
 */

public class SubjectActivity extends AppCompatActivity  {

    String subjectTitle, subjectDisease, newSubjectTitle, newSubjectDisease, newSubjectOwner,newSubjectContent,username,likeStatus;
    ArrayList<String> sampleComments = new ArrayList<>();
    ArrayList<String> subjectFollowersNumber = new ArrayList<>();
    final Context context = this;
    CommentRetrieveAndPostRequest cr;
    SubjectRetrieveAndPostRequest sr;
    UserSessionManager session;
    UserConnectionRequest ucr;
    String selectedText;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date commentDate;
    int commentId, commentTotalLike;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        Bundle extras = getIntent().getExtras();
        subjectTitle = extras.getString("subjectTitle");
        newSubjectTitle = extras.getString("newSubjectTitle");
        newSubjectDisease = extras.getString("newSubjectDisease");
        newSubjectOwner = extras.getString("newSubjectOwner");
        newSubjectContent = extras.getString("newSubjectContent");

        //Session Kontrol
        session = new UserSessionManager(getApplicationContext());
        session.checkLogin();
        final HashMap<String, String> user = session.getUserDetails();
        username = user.get(UserSessionManager.KEY_NAME);

        //Kullanıcı Takip-Takipçi İlişkisi Çekilme Yeri
        final UserConnectController userConnectController = new UserConnectController();
        userConnectController.getUserConnect(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        userConnectController.parseUserConnectJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        refreshSubjectConnections();

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

        final TextView tv_subjectTitle = (TextView) findViewById(R.id.tv_subjectTitle);
        final TextView tv_subjectOwner = (TextView) findViewById(R.id.tv_subjectOwner);
        final TextView tv_subjectDisease = (TextView) findViewById(R.id.tv_subjectDisease);
        final TextView tv_commentNumbers = (TextView) findViewById(R.id.tv_commentNumbers);
        final TextView tv_totalFollowers = (TextView) findViewById(R.id.tv_totalFollowers);
        final EditText et_subjectContent = (EditText) findViewById(R.id.et_subjectContent);
        final Button bt_displayAllSubjects = (Button) findViewById(R.id.bt_displayAllSubjects);
        final Button bt_followSubject = (Button) findViewById(R.id.bt_followSubject);
        final ListView lv_comments = (ListView) findViewById(R.id.lv_comments);
        final FloatingActionButton commentFab = (FloatingActionButton) findViewById(R.id.fb_addComment);

        if (subjectTitle==null){
            subjectTitle=newSubjectTitle;
        }
        tv_subjectTitle.setText(subjectTitle);
        for (int i = 0; i< Subject.subjectList.size(); i++){
            if (subjectTitle.equals(Subject.subjectList.get(i).getSubjectTitle())){
                tv_subjectOwner.setText("Konu Sahibi: "+Subject.subjectList.get(i).getSubjectOwner());
                if(Subject.subjectList.get(i).getSubjectOwner().equals(username)){
                    bt_followSubject.setVisibility(View.GONE);
                }
                et_subjectContent.setText(Subject.subjectList.get(i).getSubjectContent());
                et_subjectContent.setEnabled(false);
                subjectDisease = Subject.subjectList.get(i).getSubjectDisease();
                tv_subjectDisease.setText("Hastalık: "+subjectDisease);
            }
        }

        for (int i = 0; i< Comment.commentList.size(); i++){
            if (subjectTitle.equals(Comment.commentList.get(i).getCommentSubject().toString())){
                sampleComments.add("Kullanıcı: "+Comment.commentList.get(i).getCommentOwner().toString()+
                        "("+Comment.commentList.get(i).getCommentDate()+")\n"+
                Comment.commentList.get(i).getCommentContent().toString());
            }
        }

        for (int i = 0; i< SubjectConnection.subjectConnectionList.size(); i++){
            if (subjectTitle.equals(SubjectConnection.subjectConnectionList.get(i).getSubjectTitle())){
                subjectFollowersNumber.add(SubjectConnection.subjectConnectionList.get(i).getUsername());
            }
        }

        if(checkUserFollowStatus(username)==true){
            bt_followSubject.setText("Takipten Çık");
        }else{
            bt_followSubject.setText("Takip Et");
        }
        tv_totalFollowers.setText(subjectFollowersNumber.size()+" Konu Takibi");
        tv_commentNumbers.setText(sampleComments.size()+" Yorum");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, sampleComments);
        lv_comments.setAdapter(adapter);

        lv_comments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                refreshCommentConnections();
                selectedText = (String) lv_comments.getItemAtPosition(position);
                final String selectedUserName = selectedText.substring(selectedText.indexOf(":")+2,selectedText.indexOf("("));
                final String selectedContent =  selectedText.substring(selectedText.indexOf(")")+2,selectedText.length());
                ArrayList<String> followingUserList = new ArrayList<String>();
                for (int i=0;i<UserConnection.userConnectionList.size();i++) {
                    if (UserConnection.userConnectionList.get(i).getFollowingUserName().equals(username)) {
                        followingUserList.add(UserConnection.userConnectionList.get(i).getFollowedUserName());
                    }
                }

                LayoutInflater layoutInflater = LayoutInflater.from(SubjectActivity.this);
                View promptView = layoutInflater.inflate(R.layout.commentlike, null);
                final Button bt_like = (Button) promptView.findViewById(R.id.bt_like);
                final Button bt_unlike = (Button) promptView.findViewById(R.id.bt_unlike);
                final TextView tv_totalLike = (TextView) promptView.findViewById(R.id.tv_comentTotalLike);
                final TextView tv_likeComment = (TextView) promptView.findViewById(R.id.tv_likeComment);
                commentId = findCommentId(selectedUserName,subjectTitle,selectedContent);
                commentTotalLike = findTotalLikeNumber(selectedUserName,subjectTitle,selectedContent);
                tv_totalLike.setText("Yorum Puanı:  " + Integer.toString(commentTotalLike));
                final String idString = Integer.toString(commentId);
                CommentConnectController.setCommentId(idString);
                CommentConnectController.setCommentVoter(username);

                tv_likeComment.setText("Yorumu Beğen");
                bt_unlike.setVisibility(View.GONE);
                bt_like.setVisibility(View.VISIBLE);
                for(int i = 0; i< CommentConnection.commentConnectionList.size(); i++){
                    if (CommentConnection.commentConnectionList.get(i).getCommentVoter().equals(username)&&
                            CommentConnection.commentConnectionList.get(i).getCommentId().equals(idString)){
                            tv_likeComment.setText("Yorumu Beğenmekten Vazgeç");
                            bt_like.setVisibility(View.GONE);
                            bt_unlike.setVisibility(View.VISIBLE);
                    }
                }
                bt_like.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            CommentConnectController.changeCommentLikeStatus();
                            tv_likeComment.setText("Yorumu Beğenmekten Vazgeç");
                            bt_like.setVisibility(View.GONE);
                            bt_unlike.setVisibility(View.VISIBLE);
                            refreshCommentConnections();
                            tv_totalLike.setText("Yorum Puanı:  " + Integer.toString(findTotalLikeNumber(selectedUserName,subjectTitle,selectedContent)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

                bt_unlike.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            CommentConnectController.changeCommentLikeStatus();
                            bt_unlike.setVisibility(View.GONE);
                            bt_like.setVisibility(View.VISIBLE);
                            tv_likeComment.setText("Yorumu Beğen");
                            refreshCommentConnections();
                            tv_totalLike.setText("Yorum Puanı:  " + Integer.toString(findTotalLikeNumber(selectedUserName,subjectTitle,selectedContent)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(
                        context);
                String buildMessage="", posivitiveButton="", negativeButton="";
                    if (followingUserList.contains(selectedUserName)){
                        alertDialogBuilder.setTitle("Kullanıcı: "+selectedUserName);
                        buildMessage="Bu kullanıcıyı takip ediyorsunuz !";
                        posivitiveButton="Mesaj Yolla";
                        negativeButton="Devam Et";
                        alertDialogBuilder
                                .setMessage(buildMessage)
                                .setPositiveButton(posivitiveButton,new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Intent sendMessageActivity = new Intent(SubjectActivity.this, SendMessageActivity.class);
                                        sendMessageActivity.putExtra("senderName",username);
                                        sendMessageActivity.putExtra("receiverName",selectedUserName);
                                        sendMessageActivity.putExtra("subjectTitle",subjectTitle);
                                        SubjectActivity.this.startActivity(sendMessageActivity);
                                    }
                                })
                                .setNegativeButton(negativeButton,new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                        }else if(username.equals(selectedUserName)){
                        alertDialogBuilder.setTitle("Kullanıcı: "+selectedUserName);
                        buildMessage="Yorum Size Ait !";
                        negativeButton="Devam Et";
                        alertDialogBuilder
                                .setMessage(buildMessage)
                                .setCancelable(false)
                                .setNegativeButton(negativeButton,new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                                });

                    }
                        else if (!followingUserList.contains(selectedUserName)&& !username.equals(selectedUserName)){
                        alertDialogBuilder.setTitle("Kullanıcı: "+selectedUserName);
                        buildMessage="Bu kullanıcıyı takip etmek ister misiniz?";
                            posivitiveButton="Evet";
                            negativeButton="Hayır";
                            alertDialogBuilder
                                    .setMessage(buildMessage)
                                    .setPositiveButton(posivitiveButton,new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            try {
                                                followUser(selectedUserName.toString());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    })
                                    .setNegativeButton(negativeButton,new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            dialog.cancel();
                                        }
                                    });
                        }
                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setView(promptView);
                alertDialog.show();
            }
        });


        bt_displayAllSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToDiseaseActivity = new Intent(SubjectActivity.this, DiseaseActivity.class);
                backToDiseaseActivity.putExtra("diseaseName",subjectDisease);
                SubjectActivity.this.startActivity(backToDiseaseActivity);
            }
        });

        bt_followSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bt_followSubject.getText().equals("Takip Et")) {
                    likeStatus=SubjectRetrieveAndPostRequest.KEY_LIKE;
                }
                else{
                    likeStatus=SubjectRetrieveAndPostRequest.KEY_UNLIKE;
                }
                final SubjectController subjectController = new SubjectController();
                try {
                    subjectController.changeLikeSubjectStatus(subjectTitle, likeStatus, username, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                refreshSubjectConnections();
                Intent intent = new Intent(SubjectActivity.this, SubjectActivity.class);
                intent.putExtra("subjectTitle",subjectTitle);
                SubjectActivity.this.startActivity(intent);
            }
        });


        commentFab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.comment_part, null);

         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);

         final EditText userInput = (EditText) promptsView.findViewById(R.id.et_Comment);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Yolla",
                                                   new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog,int id) {
            try {
                if (userInput.getText().toString().length()!=0){
                    addComment(userInput.getText().toString());
                    Intent intent = new Intent(SubjectActivity.this, SubjectActivity.class);
                    intent.putExtra("subjectTitle",subjectTitle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SubjectActivity.this,"Yorum Gönderilemedi !\nYorum içeriği boş...",Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    })
            .setNegativeButton("İptal Et",
                                       new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog,int id) {
            dialog.cancel();
        }
    });
    AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

}
        });

    }
    public void addComment(String content) throws JSONException {
        final String commentSubject=subjectTitle;
        final String commentOwner = username;
        final String commentContent=content;

        final Date date = new Date();
        final String dtStart = sdf.format(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            commentDate = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, cr.COMMENT_CREATE_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                        Toast.makeText(SubjectActivity.this,"Başarıyla Yorum Gönderildi!",Toast.LENGTH_LONG).show();
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
                params.put(cr.KEY_COMMENT_SUBJECT, commentSubject);
                params.put(cr.KEY_COMMENT_OWNER, commentOwner);
                params.put(cr.KEY_COMMENT_CONTENT, commentContent);
                params.put(cr.KEY_COMMENT_DATE, dtStart);

                return params;
            }
        };

        Comment cmn = new Comment(Comment.commentList.size()+1,commentSubject,username,commentContent,0,dtStart);
        Comment.commentList.add(cmn);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private boolean checkUserFollowStatus(String username){
        refreshSubjectConnections();
        for (int i=0;i< SubjectConnection.subjectConnectionList.size();i++){
           if (SubjectConnection.subjectConnectionList.get(i).getUsername().equals(username)&&
                SubjectConnection.subjectConnectionList.get(i).getSubjectTitle().equals(subjectTitle)){
                return true;
           }
        }
        return false;
    }

    private void followUser(final String followingUser) throws JSONException {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ucr.USER_FOLLOW_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SubjectActivity.this,"Başarıyla takip ettiniz !",Toast.LENGTH_LONG).show();
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
                params.put(ucr.KEY_FOLLOWING_USER_NAME, username);
                params.put(ucr.KEY_FOLLOWED_USER_NAME, followingUser);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
        Intent subjectActivity = new Intent(SubjectActivity.this, SubjectActivity.class);
        subjectActivity.putExtra("subjectTitle",subjectTitle);
        SubjectActivity.this.startActivity(subjectActivity);
    }

    public int findCommentId(String selectedUser, String currentSubject, String select_content){
        int Id = 0;
        for (int i=0;i<Comment.commentList.size();i++){
            if(Comment.commentList.get(i).getCommentOwner().equals(selectedUser)
                    &&Comment.commentList.get(i).getCommentSubject().equals(currentSubject)
                    &&Comment.commentList.get(i).getCommentContent().equals(select_content)){
                Id = Comment.commentList.get(i).getId();
            }
        }
        return Id;
    }

    public int findTotalLikeNumber(String selectedUser, String currentSubject, String select_content){
        int totalLikeNumber = 0;
        for (int i=0;i<Comment.commentList.size();i++){
            if(Comment.commentList.get(i).getCommentOwner().equals(selectedUser)
                    &&Comment.commentList.get(i).getCommentSubject().equals(currentSubject)
                    &&Comment.commentList.get(i).getCommentContent().equals(select_content)){
                totalLikeNumber = Comment.commentList.get(i).getCommentTotalLikes();
            }
        }
        return totalLikeNumber;
    }

    public void refreshCommentConnections(){
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
    }

    public void refreshSubjectConnections() {
        //Kullanıcı Konu Takip Bilgisi Çekilme Yeri
        final SubjectConnectController subjectConnectController = new SubjectConnectController();
        subjectConnectController.getSubjectConnect(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        subjectConnectController.parseSubjectConnectJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
    }
}