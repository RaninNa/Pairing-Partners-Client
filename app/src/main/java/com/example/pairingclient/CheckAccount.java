package com.example.pairingclient;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class CheckAccount extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://pairingapp.000webhostapp.com/checkUserName.php";
    private Map<String, String> params;


    public CheckAccount(String user_name, String password, String dbname, String dbuser, String dbpass, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dbname", dbname);
        params.put("dbuser", dbuser);
        params.put("dbpass", dbpass);
        params.put("user_name", user_name);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
