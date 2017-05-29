package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 16.03.2017.
 */

public class CommentRetrieveAndPostRequest extends StringRequest{

    public static final String COMMENT_REQUEST_URL = "https://evural06.000webhostapp.com/Comment.php";
    public static final String COMMENT_CREATE_REQUEST_URL = "https://evural06.000webhostapp.com/CommentPostRequest.php";
    public static final String COMMENT_LIKE_REQUEST_URL = "https://evural06.000webhostapp.com/CommentLikeRequest.php";
    public static final String COMMENT_CONNECTION_REQUEST_URL = "https://evural06.000webhostapp.com/CommentConnect.php";
    public static final String KEY_COMMENT_ID = "id";
    public static final String KEY_COMMENT_SUBJECT = "commentSubject";
    public static final String KEY_COMMENT_OWNER = "commentOwner";
    public static final String KEY_COMMENT_CONTENT = "commentContent";
    public static final String KEY_COMMENT_TOTAL_LIKES = "commentTotalLikes";
    public static final String KEY_COMMENT_DATE = "commentDate";
    public static final String RESULT_ARRAY = "comments";
    public static final String KEY_COMMENT_LIKE_ID = "commentId";
    public static final String KEY_COMMENT_LIKE_VOTER = "commentVoter";
    public static final String RESULT_COMMENT_CONNECT_ARRAY = "comment_connections";


    public CommentRetrieveAndPostRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(COMMENT_REQUEST_URL, listener, errorListener);
    }
}
