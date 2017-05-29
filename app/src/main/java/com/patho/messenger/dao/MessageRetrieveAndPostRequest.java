package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 26.03.2017.
 */

public class MessageRetrieveAndPostRequest extends StringRequest  {


    public static final String MESSAGE_REQUEST_URL = "*/Message.php";
    public static final String MESSAGE_CREATE_REQUEST_URL = "*/MessagePostRequest.php";
    public static final String KEY_MESSAGE_ID = "id";
    public static final String KEY_MESSAGE_SENDER = "sender";
    public static final String KEY_MESSAGE_RECEIVER = "receiver";
    public static final String KEY_MESSAGE_CONTENT = "messageContent";
    public static final String KEY_MESSAGE_DATE = "messageDate";
    public static final String RESULT_ARRAY = "messages";

    public MessageRetrieveAndPostRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(MESSAGE_REQUEST_URL, listener, errorListener);
    }
}
