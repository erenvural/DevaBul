package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 18.03.2017.
 */

public class UserConnectionRequest extends StringRequest {

    public static final String USER_CONNECT_REQUEST_URL = "*/UserConnect.php";
    public static final String USER_FOLLOW_REQUEST_URL = "*/UserConnectRequest.php";
    public static final String USER_UNFOLLOW_REQUEST_URL = "*/UserDisconnectRequest.php";
    public static final String KEY_FOLLOWING_USER_NAME = "followingUserName";
    public static final String KEY_FOLLOWED_USER_NAME= "followedUserName";
    public static final String RESULT_ARRAY = "user_connections";

    public UserConnectionRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(USER_CONNECT_REQUEST_URL, listener, errorListener);
    }
}
