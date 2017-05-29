package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 13.03.2017.
 */

public class SubjectRetrieveAndPostRequest extends StringRequest {

    public static final String SUBJECT_REQUEST_URL = "*/Subject.php";
    public static final String SUBJECT_OPENING_REQUEST_URL = "*/SubjectPostRequest.php";
    public static final String SUBJECT_LIKE_REQUEST_URL = "*/SubjectLikeRequest.php";
    public static final String SUBJECT_CONNECTION_REQUEST_URL = "*/SubjectConnect.php";
    public static final String KEY_SUBJECT_ID = "id";
    public static final String KEY_SUBJECT_DISEASE = "subjectDisease";
    public static final String KEY_SUBJECT_TITLE = "subjectTitle";
    public static final String KEY_SUBJECT_CONTENT = "subjectContent";
    public static final String KEY_SUBJECT_OWNER= "subjectOwner";
    public static final String KEY_SUBJECT_DATE= "subjectDate";
    public static final String KEY_LIKE_STATUS = "likeStatus";
    public static final String KEY_LIKE = "Follow";
    public static final String KEY_UNLIKE = "Unfollow";
    public static final String KEY_SUBJECT_FOLLOWER= "username";
    public static final String RESULT_ARRAY = "subjects";
    public static final String RESULT_SUBJECT_CONNECT_ARRAY = "subject_connections";



    public SubjectRetrieveAndPostRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(SUBJECT_REQUEST_URL, listener, errorListener);
    }
}
