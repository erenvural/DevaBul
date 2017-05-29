package com.patho.messenger.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.UserConnectController;
import com.patho.messenger.dao.UserConnectionRequest;
import com.patho.messenger.model.Message;
import com.patho.messenger.model.User;
import com.patho.messenger.model.UserConnection;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 17.03.2017.
 */

public class FindUsersActivity extends AppCompatActivity {

    ArrayList<String> recommendedUsers = new ArrayList<>();
    ArrayList<String> alreadyFollowingUsers = new ArrayList<>();
    String activeUser, activeUserRelatedDisease, otherUserEmail, otherUserGender, otherUserRelatedDisease,
            otherUserProfileStatus;
    static UserConnectionRequest ucr;
    int followStatus=0;
    final Context context = this;

    public static String currentUser;
    public static String followedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_users);

        Bundle extras = getIntent().getExtras();
        activeUser = extras.getString("username");
        activeUserRelatedDisease = extras.getString("relatedDisease");
        final ListView lv_recommendedUsers = (ListView) findViewById(R.id.lv_recommendedUsers);

        for (int j=0;j< UserConnection.userConnectionList.size();j++){
            if (UserConnection.userConnectionList.get(j).getFollowingUserName().equals(activeUser)){
                alreadyFollowingUsers.add(UserConnection.userConnectionList.get(j).getFollowedUserName());
            }
        }

        for (int i = 0; i< User.userList.size(); i++){
            if (!alreadyFollowingUsers.contains(User.userList.get(i).getUsername().toString())&&
                    !activeUser.equals(User.userList.get(i).getUsername().toString()) &&
                    activeUserRelatedDisease.equals(User.userList.get(i).getRelatedDisease().toString())){
                recommendedUsers.add(User.userList.get(i).getUsername().toString());
            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, recommendedUsers);
        adapter.notifyDataSetChanged();
        lv_recommendedUsers.setAdapter(adapter);
        lv_recommendedUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                final String  selectedUserName    = (String) lv_recommendedUsers.getItemAtPosition(position);
                for (int i=0;i<User.userList.size();i++){
                    if(User.userList.get(i).getUsername().equals(selectedUserName)){
                        otherUserEmail = User.userList.get(i).getEmail();
                        otherUserGender = User.userList.get(i).getGender();
                        otherUserRelatedDisease = User.userList.get(i).getRelatedDisease();
                        otherUserProfileStatus=User.userList.get(i).getProfile_status();
                    }
                }
                for(int i=0;i<UserConnection.userConnectionList.size();i++){
                    if(UserConnection.userConnectionList.get(i).getFollowedUserName().equals(selectedUserName)
                            && UserConnection.userConnectionList.get(i).getFollowingUserName().equals(activeUser)){
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
                String positiveButtonText;

                bt_messageUser.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FindUsersActivity.this, MessageActivity.class);
                        intent.putExtra("username",activeUser);
                        FindUsersActivity.this.startActivity(intent);
                    }
                });

                if (followStatus==1){
                    bt_unfollow_status.setVisibility(View.GONE);
                    positiveButtonText="Takip Ediyorsunuz !";
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

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                alertDialogBuilder.setTitle("Kullanıcı: "+selectedUserName);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton(positiveButtonText,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        setCurrentUser(activeUser);
                                        setFollowedUser(selectedUserName);
                                        try {
                                            followUser();
                                            Toast.makeText(FindUsersActivity.this,"Başarıyla takip ettiniz !",Toast.LENGTH_LONG).show();
                                            Intent profileActivityIntent = new Intent(FindUsersActivity.this, ProfileActivity.class);
                                            FindUsersActivity.this.startActivity(profileActivityIntent);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })
                        .setNegativeButton("Devam Et",
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

    public static void followUser() throws JSONException {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ucr.USER_FOLLOW_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            volleyError = error;
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(ucr.KEY_FOLLOWING_USER_NAME, currentUser);
                params.put(ucr.KEY_FOLLOWED_USER_NAME, followedUser);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    public static void unfollowUser() throws JSONException {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ucr.USER_UNFOLLOW_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Response",response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            error.printStackTrace();
                            Log.v("Response Error",error.toString());

                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(ucr.KEY_FOLLOWING_USER_NAME, currentUser);
                params.put(ucr.KEY_FOLLOWED_USER_NAME, followedUser);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(stringRequest);
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


    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        FindUsersActivity.currentUser = currentUser;
    }

    public static String getFollowedUser() {
        return followedUser;
    }

    public static void setFollowedUser(String followedUser) {
        FindUsersActivity.followedUser = followedUser;
    }
}
