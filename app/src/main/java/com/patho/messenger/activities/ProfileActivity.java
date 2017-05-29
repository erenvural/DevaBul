package com.patho.messenger.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.ProfileSettingsController;
import com.patho.messenger.controller.SubjectConnectController;
import com.patho.messenger.controller.UserConnectController;
import com.patho.messenger.controller.UserSessionManager;
import com.patho.messenger.model.Disease;
import com.patho.messenger.model.Message;
import com.patho.messenger.model.ProfileImage;
import com.patho.messenger.model.Subject;
import com.patho.messenger.model.SubjectConnection;
import com.patho.messenger.model.User;
import com.patho.messenger.model.UserConnection;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by eren on 21.02.2017.
 */

public class ProfileActivity extends AppCompatActivity  {

    UserSessionManager session;
    String profile_status, username, relatedDisease, downloadImageUrl, otherUserEmail, otherUserGender,
            otherUserRelatedDisease, otherUserProfileStatus, followingUserName="deneme";
    int followStatus=0;
    ArrayList<String> followingUsers = new ArrayList<>();
    ArrayList<String> followerUsers = new ArrayList<>();
    ArrayList<String> openedSubjects = new ArrayList<>();
    ArrayList<String> followingSubjects = new ArrayList<>();
    final Context context = this;
    ProfileSettingsController psc;
    FindUsersActivity findUsersActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView sessionMail = (TextView) findViewById(R.id.sessionEmail);
        final TextView sessionBirthday = (TextView) findViewById(R.id.sessionBirthday);
        final TextView sessionGender = (TextView) findViewById(R.id.sessionGender);
        final TextView tv_relatedDisease = (TextView) findViewById(R.id.tv_relatedDisease);
        final TextView tv_userName = (TextView) findViewById(R.id.tv_userName);
        final TextView tv_uploadImage = (TextView) findViewById(R.id.tv_uploadImage);
        final Switch sessionProfileStatus = (Switch) findViewById(R.id.switchProfilStatus);
        final FloatingActionButton bt_findUsers = (FloatingActionButton) findViewById(R.id.fab_findUsers);
        final ListView lv_followings = (ListView) findViewById(R.id.lv_followings);
        final ListView lv_followers = (ListView) findViewById(R.id.lv_followers);
        final ListView lv_openedSubjects = (ListView) findViewById(R.id.lv_openedSubjects);
        final ListView lv_profileFollowSubjects = (ListView) findViewById(R.id.lv_profileFollowSubjects);
        final ImageView iv_profilePicture = (ImageView) findViewById(R.id.iv_profilePicture);
        FloatingActionButton fabNextImageScreen = (FloatingActionButton) findViewById(R.id.nextImageButton);

        refreshUserConnections();
        //Kullanıcı Konu Takip Bilgisi Çekilme Yeri
        final SubjectConnectController subjectConnectController = new SubjectConnectController();
        subjectConnectController.getSubjectConnect(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        subjectConnectController.parseSubjectConnectJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Session Kontrol ve Kullanımı
        session = new UserSessionManager(getApplicationContext());
        session.checkLogin();
        final HashMap<String, String> user = session.getUserDetails();
        username = user.get(UserSessionManager.KEY_NAME);
        final String email = user.get(UserSessionManager.KEY_EMAIL);
        final String birthday = user.get(UserSessionManager.KEY_BIRTHDAY);
        final String gender = user.get(UserSessionManager.KEY_GENDER);
        final Button bt_changeRelatedDisease = (Button) findViewById(R.id.bt_changeRelatedDisease);
        tv_userName.setText(" " + username);
        profile_status  = user.get(UserSessionManager.KEY_PROFILE_STATUS);
        relatedDisease = user.get(UserSessionManager.KEY_RELATED_DISEASE);
        tv_relatedDisease.setText(relatedDisease);

        //Resim Url Çekilme ve İndirme Yeri
        for (int i=0;i<ProfileImage.profileImageList.size();i++){
            if (ProfileImage.profileImageList.get(i).getUsername().equals(username)){
                downloadImageUrl = ProfileImage.profileImageList.get(i).getImage_url();
            }
        }

         ImageRequest ir = new ImageRequest("https://lh4.ggpht.com/4LMa0Z5OcQQ7F1pY2rVdU5jRjRzni9RlCuo5l6AIbhp4UXgIjtRrTPBI4Z2BAYTpQW7i=w300", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_profilePicture.setImageBitmap(response);
            }
        }, 0, 0, null, null);

        ImageRequest ir1 = new ImageRequest(downloadImageUrl, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv_profilePicture.setImageBitmap(response);
            }
        }, 0, 0, null, null);

        AppController.getInstance().addToRequestQueue(ir);

        //UI display settings
        sessionMail.setText(email);
        sessionGender.setText(gender);
        sessionBirthday.setText(birthday);
        sessionProfileStatus.setTextOff("Kapalı");
        sessionProfileStatus.setTextOn("Açık");

        if(!profile_status.equals("Normal")){
            sessionProfileStatus.setChecked(true);
        }

        //Listeners
        sessionProfileStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                Toast.makeText(ProfileActivity.this,"Gizli oturum özelliğiniz başarıyla güncellendi !",Toast.LENGTH_LONG).show();
                try {
                    final ProfileSettingsController profileSettingsController = new ProfileSettingsController();
                profileSettingsController.changeProfileStatus(username,profile_status,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
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
                if(isChecked){
                    session.createLoginSession(username, email, birthday, gender, "Hidden",relatedDisease);
                }
                else{
                    session.createLoginSession(username, email, birthday, gender, "Normal",relatedDisease);
                }
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                ProfileActivity.this.startActivity(intent);
                finish();

            }
        });

        bt_changeRelatedDisease.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ShowAlertDialogWithListview();
            }
        });

        bt_findUsers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, FindUsersActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("relatedDisease",relatedDisease);
                ProfileActivity.this.startActivity(intent);
            }
        });

        fabNextImageScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent uploadImageIntent = new Intent(ProfileActivity.this, UploadImageActivity.class);
                uploadImageIntent.putExtra("username",username);
                ProfileActivity.this.startActivity(uploadImageIntent);
            }
        });

        //Takip Edilenleri Listeleme
        for (int i = 0; i< UserConnection.userConnectionList.size(); i++){
            if (UserConnection.userConnectionList.get(i).getFollowingUserName().equals(username)){
                followingUsers .add(UserConnection.userConnectionList.get(i).getFollowedUserName());
            }
        }

        //Takip Edenleri Listeleme
        for (int i = 0; i< UserConnection.userConnectionList.size(); i++){
            if (UserConnection.userConnectionList.get(i).getFollowedUserName().equals(username)){
                followerUsers.add(UserConnection.userConnectionList.get(i).getFollowingUserName());
            }
        }

        //Açılan Konuları Listeleme
        for (int i = 0; i< Subject.subjectList.size(); i++){
            if (Subject.subjectList.get(i).getSubjectOwner().equals(username)){
                openedSubjects.add(Subject.subjectList.get(i).getSubjectTitle());
            }
        }

        //Takip Konu Listesini Doldurma
        for (int i = 0; i< SubjectConnection.subjectConnectionList.size(); i++){
            if (SubjectConnection.subjectConnectionList.get(i).getUsername().equals(username)){
                if(!openedSubjects.contains(SubjectConnection.subjectConnectionList.get(i).getSubjectTitle())){
                    followingSubjects.add(SubjectConnection.subjectConnectionList.get(i).getSubjectTitle());
                }
            }
        }

        //Tabhost Ayarları
        TabHost host = (TabHost)findViewById(R.id.tabUsers);
        host.setup();
        TabHost.TabSpec spec = host.newTabSpec("TAKİP("+followingUsers.size()+")");
        spec.setContent(R.id.tab1);
        spec.setIndicator("TAKİP("+followingUsers.size()+")");
        host.addTab(spec);
        spec = host.newTabSpec("TAKİPÇİ ("+followerUsers.size()+")");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAKİPÇİ ("+followerUsers.size()+")");
        host.addTab(spec);
        spec = host.newTabSpec("KONULAR("+openedSubjects.size()+")");
        spec.setContent(R.id.tab4);
        spec.setIndicator("KONULAR("+openedSubjects.size()+")");
        host.addTab(spec);
        spec = host.newTabSpec("TAKİP KONU("+followingSubjects.size()+")");
        spec.setContent(R.id.tab3);
        spec.setIndicator("TAKİP KONU("+followingSubjects.size()+")");
        host.addTab(spec);

        final ArrayAdapter<String> adapterFollowings = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, followingUsers);
        adapterFollowings.notifyDataSetChanged();
        lv_followings.setAdapter(adapterFollowings);

        final ArrayAdapter<String> adapterFollowers = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, followerUsers);
        adapterFollowers.notifyDataSetChanged();
        lv_followers.setAdapter(adapterFollowers);

        final ArrayAdapter<String> adapterOpenedSubjects = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, openedSubjects);
        adapterOpenedSubjects.notifyDataSetChanged();
        lv_openedSubjects.setAdapter(adapterOpenedSubjects);


        final ArrayAdapter<String> adapterSubjectList = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, followingSubjects);
        adapterSubjectList.notifyDataSetChanged();
        lv_profileFollowSubjects.setAdapter(adapterSubjectList);

        lv_followers.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String  selectedUserName    = (String) lv_followers.getItemAtPosition(position);
                followingUserName = selectedUserName;
                for (int i = 0; i< User.userList.size(); i++){
                    if(User.userList.get(i).getUsername().equals(selectedUserName)){
                        otherUserEmail = User.userList.get(i).getEmail();
                        otherUserGender = User.userList.get(i).getGender();
                        otherUserRelatedDisease = User.userList.get(i).getRelatedDisease();
                        otherUserProfileStatus=User.userList.get(i).getProfile_status();
                    }
                }
                for(int i=0;i<UserConnection.userConnectionList.size();i++){
                    if(UserConnection.userConnectionList.get(i).getFollowedUserName().equals(selectedUserName)
                            && UserConnection.userConnectionList.get(i).getFollowingUserName().equals(username)){
                        followStatus=1;
                    }
                }

                int totalMessage=0;
                for(int i = 0; i< Message.messageList.size(); i++){
                    if(Message.messageList.get(i).getSender().equals(selectedUserName)
                            || Message.messageList.get(i).getReceiver().equals(selectedUserName)){
                        totalMessage++;
                    }
                }

                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.profile_display_part, null);
                final TextView tv_other_email = (TextView) promptsView.findViewById(R.id.tv_other_profile_email);
                final TextView tv_other_gender = (TextView) promptsView.findViewById(R.id.tv_other_profile_gender);
                final TextView tv_other_profile_disease = (TextView) promptsView.findViewById(R.id.tv_other_profile_disease);
                final TextView tv_message_number = (TextView) promptsView.findViewById(R.id.tv_message_number);
                final Button bt_follow_status = (Button) promptsView.findViewById(R.id.bt_follow_status);
                final Button bt_unfollow_status = (Button) promptsView.findViewById(R.id.bt_unfollow_status);
                final Button bt_messageUser = (Button) promptsView.findViewById(R.id.bt_messageUser);

                bt_messageUser.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ProfileActivity.this, MessageActivity.class);
                        intent.putExtra("username",username);
                        ProfileActivity.this.startActivity(intent);
                    }
                });
                String positiveButtonText;
                if (followStatus==1){
                    bt_unfollow_status.setVisibility(View.GONE);
                    positiveButtonText="Takipten Çık";
                }else {
                    bt_follow_status.setVisibility(View.GONE);
                    positiveButtonText="Takip Et";
                }

                if (otherUserProfileStatus.equals("Normal")){
                    tv_other_email.setText(otherUserEmail);
                    tv_other_gender.setText(otherUserGender);
                    tv_other_profile_disease.setText(otherUserRelatedDisease);
                }else{
                    tv_other_email.setText("*Gizli Profil*");
                    tv_other_gender.setText("*Gizli Profil*");
                    tv_other_profile_disease.setText("*Gizli Profil*");
                }

                tv_message_number.setText(totalMessage + " mesaj");

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setTitle("Kullanıcı: "+selectedUserName);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(positiveButtonText,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        if (followStatus==1){

                                        }
                                        else{
                                            findUsersActivity.setCurrentUser(username);
                                            findUsersActivity.setFollowedUser(selectedUserName);
                                            try {
                                                findUsersActivity.followUser();
                                                Toast.makeText(ProfileActivity.this,"Başarıyla takip ettiniz !",Toast.LENGTH_LONG).show();
                                                adapterFollowings.notifyDataSetChanged();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    }
                                })
                        .setNegativeButton("Devam Et",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        lv_followings.setOnItemClickListener (new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String  selectedUserName = (String) lv_followings.getItemAtPosition(position);
                for (int i = 0; i< User.userList.size(); i++){
                    if(User.userList.get(i).getUsername().equals(selectedUserName)){
                        otherUserEmail = User.userList.get(i).getEmail();
                        otherUserGender = User.userList.get(i).getGender();
                        otherUserRelatedDisease = User.userList.get(i).getRelatedDisease();
                        otherUserProfileStatus = User.userList.get(i).getProfile_status();
                    }
                }
                for(int i=0;i<UserConnection.userConnectionList.size();i++){
                    if(UserConnection.userConnectionList.get(i).getFollowedUserName().equals(selectedUserName)
                            && UserConnection.userConnectionList.get(i).getFollowingUserName().equals(username)){
                        followStatus=1;
                    }
                }

                int totalMessage=0;
                for(int i = 0; i< Message.messageList.size(); i++){
                    if(Message.messageList.get(i).getSender().equals(selectedUserName)
                            || Message.messageList.get(i).getReceiver().equals(selectedUserName)){
                        totalMessage++;
                    }
                }


                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.profile_display_part, null);
                final TextView tv_other_email = (TextView) promptsView.findViewById(R.id.tv_other_profile_email);
                final TextView tv_other_gender = (TextView) promptsView.findViewById(R.id.tv_other_profile_gender);
                final TextView tv_other_profile_disease = (TextView) promptsView.findViewById(R.id.tv_other_profile_disease);
                final TextView tv_message_number = (TextView) promptsView.findViewById(R.id.tv_message_number);
                final Button bt_follow_status = (Button) promptsView.findViewById(R.id.bt_follow_status);
                final Button bt_unfollow_status = (Button) promptsView.findViewById(R.id.bt_unfollow_status);
                String positiveButtonText="Takipten Çık";
                if (followStatus==1){
                    bt_unfollow_status.setVisibility(View.GONE);
                }else {
                    bt_follow_status.setVisibility(View.GONE);
                }
                    if (otherUserProfileStatus.equals("Normal")){
                    tv_other_email.setText(otherUserEmail);
                    tv_other_gender.setText(otherUserGender);
                    tv_other_profile_disease.setText(otherUserRelatedDisease);
                }else{
                    tv_other_email.setText("*Gizli Profil*");
                    tv_other_gender.setText("*Gizli Profil*");
                    tv_other_profile_disease.setText("*Gizli Profil*");}

                tv_message_number.setText(totalMessage + " mesaj");

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setTitle("Kullanıcı: "+selectedUserName);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(positiveButtonText,
                                new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    findUsersActivity.setCurrentUser(username);
                    findUsersActivity.setFollowedUser(selectedUserName);
                    try {
                        findUsersActivity.unfollowUser();
                        refreshUserConnections();
                        followingUsers.clear();
                        //Takip Edilenleri Listeleme
                        for (int i = 0; i< UserConnection.userConnectionList.size(); i++){
                            if (UserConnection.userConnectionList.get(i).getFollowingUserName().equals(username)){
                                followingUsers .add(UserConnection.userConnectionList.get(i).getFollowedUserName());
                            }
                        }
                        Toast.makeText(ProfileActivity.this,"Kullanıcıyı takip etmeyi bıraktınız !",Toast.LENGTH_LONG).show();
                        adapterFollowings.notifyDataSetChanged();
                        //lv_followings.invalidateViews();
                        lv_followings.invalidate();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.v("JSON Error",e.toString());
                    }
                }
            })
                    .setNegativeButton("Devam Et",
                                               new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                }
            });
            android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
        }
        });

        lv_openedSubjects.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String  selectedSubjectName    = (String) lv_openedSubjects.getItemAtPosition(position);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(selectedSubjectName+" ");
                alertDialogBuilder.setMessage("başlıklı konunuza gitmek ister misiniz?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Git",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Intent intent = new Intent(ProfileActivity.this, SubjectActivity.class);
                                        intent.putExtra("subjectTitle",selectedSubjectName);
                                        ProfileActivity.this.startActivity(intent);
                                    }
                                })
                        .setNegativeButton("Profilde Kal",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        lv_profileFollowSubjects.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String  selectedSubjectName    = (String) lv_profileFollowSubjects.getItemAtPosition(position);
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
                alertDialogBuilder.setTitle(selectedSubjectName);
                alertDialogBuilder.setMessage("başlıklı takip listenizde bulunan konuya gitmek ister misiniz?");
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Git",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        Intent intent = new Intent(ProfileActivity.this, SubjectActivity.class);
                                        intent.putExtra("subjectTitle",selectedSubjectName);
                                        ProfileActivity.this.startActivity(intent);
                                    }
                                })
                        .setNegativeButton("Profilde Kal",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
    public void ShowAlertDialogWithListview()
    {
        List<String> diseaseList = new ArrayList<String>();
        for (int i = 0; i< Disease.diseaseList.size(); i++){
            diseaseList.add(Disease.diseaseList.get(i).getDiseaseName().toString());
        }
        final CharSequence[] Diseases = diseaseList.toArray(new String[diseaseList.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("İlgilendiğiniz Hastalığı Seçiniz!");
        dialogBuilder.setItems(Diseases, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = Diseases[item].toString();
                final ProfileSettingsController profileSettingsController = new ProfileSettingsController();
                try {
                    profileSettingsController.changeRelatedDisease(username,selectedText.toString(),new Response.Listener<String>() {
                        @Override
                        public void onResponse(String  response) {
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                Toast.makeText(ProfileActivity.this,"İlgilendiğiniz hastalık "+selectedText.toString()+" olarak değişti !",Toast.LENGTH_LONG).show();
                session = new UserSessionManager(getApplicationContext());
                HashMap<String, String> user = session.getUserDetails();
                String email = user.get(UserSessionManager.KEY_EMAIL);
                String birthday = user.get(UserSessionManager.KEY_BIRTHDAY);
                String gender = user.get(UserSessionManager.KEY_GENDER);
                session.createLoginSession(username, email, birthday, gender, profile_status,selectedText.toString());
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                ProfileActivity.this.startActivity(intent);
            }
        });
        AlertDialog alertDialogObject = dialogBuilder.create();
        alertDialogObject.show();
    }

    public void refreshUserConnections(){
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
    }
}
