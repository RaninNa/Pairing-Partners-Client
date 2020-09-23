package com.example.pairingclient;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class CheckRegistration extends StringRequest {

    private static final String LOGIN_REQUEST_URL =  "https://pairingapp.000webhostapp.com/CheckRegisteration.php";
    private Map<String, String> params;

    public CheckRegistration(String username, String faculty, String course, String worktype, String dbname, String dbuser, String dbpass, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dbname", dbname);
        params.put("dbuser", dbuser);
        params.put("dbpass", dbpass);
        params.put("username", "" + username);
        params.put("faculty", "" + faculty);
        params.put("course", "" + course);
        params.put("workType", "" + worktype);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }


}