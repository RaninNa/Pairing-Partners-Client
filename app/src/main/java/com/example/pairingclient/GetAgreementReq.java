package com.example.pairingclient;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;



public class GetAgreementReq extends StringRequest {


    private static final String LOGIN_REQUEST_URL =  "https://pairingapp.000webhostapp.com/GetAgreement.php";
    private Map<String, String> params;

    public GetAgreementReq(String faculty, String course, String workType, String Pair_UN, String dbname, String dbuser, String dbpass, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dbname", dbname);
        params.put("dbuser", dbuser);
        params.put("dbpass", dbpass);
        params.put("faculty", "" + faculty);
        params.put("course", "" + course);
        params.put("workType", "" + workType);
        params.put("Pair_UN", "" + Pair_UN);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}