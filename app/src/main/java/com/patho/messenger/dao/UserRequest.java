package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 17.03.2017.
 */

public class UserRequest extends StringRequest {

    public static final String USER_REQUEST_URL = "*/User.php";
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_PROFILE_STATUS= "profile_status";
    public static final String KEY_USER_USERNAME = "username";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_BIRTHDAY = "birthday";
    public static final String KEY_USER_GENDER = "gender";
    public static final String KEY_USER_RELATED_DISEASE= "relatedDisease";
    public static final String RESULT_ARRAY = "users";

    public UserRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(USER_REQUEST_URL, listener, errorListener);
    }
}
