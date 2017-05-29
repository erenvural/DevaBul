package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by eren on 9.03.2017.
 */

public class DiseaseRequest extends StringRequest {

    public static final String DISEASE_REQUEST_URL = "https://evural06.000webhostapp.com/Disease.php";
    public static final String KEY_DISEASE_ID = "id";
    public static final String KEY_DISEASE_NAME = "diseaseName";
    public static final String KEY_DISAEASE_TYPE = "diseaseType";
    public static final String KEY_DISAEASE_DESCRIPTION = "diseaseDescription";
    public static final String RESULT_ARRAY = "diseases";

    public DiseaseRequest(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(DISEASE_REQUEST_URL, listener, errorListener);
    }
}
