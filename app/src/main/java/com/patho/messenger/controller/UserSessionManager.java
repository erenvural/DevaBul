package com.patho.messenger.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.patho.messenger.activities.LoginActivity;

import java.util.HashMap;

/**
 * Created by eren on 2.03.2017.
 */

public class UserSessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "PrefName";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_BIRTHDAY = "birthday";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_PROFILE_STATUS ="profile_status";
    public static final String KEY_RELATED_DISEASE ="relatedDisease";

    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    //Login Session
    public void createLoginSession(String username, String email, String birthday, String gender, String profile_status,String relatedDisease){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_BIRTHDAY, birthday);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_PROFILE_STATUS, profile_status);
        editor.putString(KEY_RELATED_DISEASE,relatedDisease);
        editor.commit();
    }

    //Check Login
    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    //Get User Details
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_BIRTHDAY, pref.getString(KEY_BIRTHDAY, null));
        user.put(KEY_GENDER, pref.getString(KEY_GENDER, null));
        user.put(KEY_PROFILE_STATUS, pref.getString(KEY_PROFILE_STATUS, null));
        user.put(KEY_RELATED_DISEASE, pref.getString(KEY_RELATED_DISEASE, null));

        return user;
    }


    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    static final String PREF_USER_NAME= "username";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    //Change session details
    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }
    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
}
