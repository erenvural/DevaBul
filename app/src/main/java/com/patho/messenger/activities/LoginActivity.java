package com.patho.messenger.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.CommentController;
import com.patho.messenger.controller.DiseaseController;
import com.patho.messenger.controller.LoginController;
import com.patho.messenger.controller.UserSessionManager;
import com.patho.messenger.dao.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    UserSessionManager session;
    boolean internetConnection;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Hastalıkların Çekildiği Yer
        final DiseaseController diseaseController = new DiseaseController();
        diseaseController.getDisease(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        diseaseController.parseDiseaseJSON(response);
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
        
        if(session.getUserName(LoginActivity.this).length() != 0)
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
            finish();

        }

        session = new UserSessionManager(getApplicationContext());

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        internetConnection = isNetworkConnected();
        final LoginController loginController = new LoginController(LoginActivity.this);
        if(!internetConnection){
            loginController.showInternetSettingsAlert();
        }

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Res:",""+response.toString());
                        try {
                            JSONObject jsonResponse = new JSONObject(response);  //Hata Alınan Yer
                            Log.v("JsRes:",""+jsonResponse.toString());
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String username = jsonResponse.getString("username");
                                String profile_status = jsonResponse.getString("profile_status");
                                String email = jsonResponse.getString("email");
                                String birthday = jsonResponse.getString("birthday");
                                String gender = jsonResponse.getString("gender");
                                String relatedDisease = jsonResponse.getString("relatedDisease");
                                //Session Gönderme
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                session.createLoginSession(username, email, birthday, gender, profile_status,relatedDisease);
                                session.setUserName(LoginActivity.this,username);
                                LoginActivity.this.startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.v("Hata:",e.toString());
                            loginController.showWrongUsernamePaswordDialog();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                AppController.getInstance().addToRequestQueue(loginRequest);

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
