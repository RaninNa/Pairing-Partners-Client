package com.example.pairingclient;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class getPartnersReq extends StringRequest {


    private static final String LOGIN_REQUEST_URL =  "https://pairingapp.000webhostapp.com/getPartners.php";
    private Map<String, String> params;

    public getPartnersReq(String user_name, String dbname, String dbuser, String dbpass, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dbname", dbname);
        params.put("dbuser", dbuser);
        params.put("dbpass", dbpass);
        params.put("user_name", user_name);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }


}