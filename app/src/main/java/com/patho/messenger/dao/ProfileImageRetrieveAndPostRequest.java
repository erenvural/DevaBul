package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 20.03.2017.
 */

public class ProfileImageRetrieveAndPostRequest extends StringRequest {

    public static final String PROFILE_IMAGE_REQUEST_URL = "*/RetrieveImageRequest.php";
    public static final String IMAGE_UPLOAD_REQUEST_URL = "*/UploadImageRequest.php";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_PROFILE_IMAGE_ID = "id";
    public static final String KEY_IMAGE_URL = "image_url";
    public static final String KEY_USER_NAME = "username";
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final String RESULT_ARRAY = "images";


    public ProfileImageRetrieveAndPostRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(IMAGE_UPLOAD_REQUEST_URL, listener, errorListener);
    }
}
