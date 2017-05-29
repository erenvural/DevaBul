package com.patho.messenger.dao;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "*/RegisterRequest.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String email, String password, String birthday, String gender, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("email", email);
        params.put("password", password);
        params.put("birthday", birthday);
        params.put("gender", gender);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
