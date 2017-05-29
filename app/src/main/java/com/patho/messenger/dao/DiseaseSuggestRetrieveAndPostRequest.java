package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 2.05.2017.
 */

public class DiseaseSuggestRetrieveAndPostRequest extends StringRequest{

    public static final String SUGGEST_REQUEST_URL = "https://evural06.000webhostapp.com/Suggestion.php";
    public static final String SUGGEST_CREATE_REQUEST_URL = "https://evural06.000webhostapp.com/SuggestPostRequest.php";
    public static final String COMMENT_LIKE_REQUEST_URL = "https://evural06.000webhostapp.com/CommentConfirmRequest.php";
    public static final String KEY_SUGGEST_ID = "id";
    public static final String KEY_SUGGEST_NAME= "suggestName";
    public static final String KEY_SUGGEST_TYPE = "suggestType";
    public static final String KEY_SUGGEST_DESCRIPTION= "suggestDescription";
    public static final String KEY_SUGGEST_OWNER = "suggestOwner";
    public static final String KEY_SUGGEST_DATE = "suggestDate";
    public static final String KEY_SUGGEST_CONFIRM = "suggestConfirm";
    public static final String RESULT_ARRAY = "suggestions";
    /*public static final String KEY_COMMENT_LIKE_ID = "commentId";
    public static final String KEY_COMMENT_LIKE_VOTER = "commentVoter";
    public static final String KEY_COMMENT_LIKE_VOTE_STATUS = "voteStatus";
    public static final String RESULT_COMMENT_CONNECT_ARRAY = "comment_connections";*/


    public DiseaseSuggestRetrieveAndPostRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(SUGGEST_REQUEST_URL, listener, errorListener);
    }
}
