package com.example.pairingclient;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class RegisterUserReq extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://aarasna.in/RegisterUser.php";
    private static final String REGISTER_USER_REQUEST_URL = "https://aarasna.in/registerUserName.php";
    private Map<String, String> params;

    public RegisterUserReq(String user_name, String name, String gender,String location, int age, String phone, String email, String year, String gradeAverage, String workPlan, String meeting, String prefGen, String workHours, Boolean iLocation,
                           Boolean iGrade, String faculty, String course, String workType, String dbname, String dbuser, String dbpass, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("dbname", dbname);
        params.put("dbuser", dbuser);
        params.put("dbpass", dbpass);
        params.put("user_name", user_name);
        params.put("name", name);
        params.put("gender", gender);
        params.put("location", location);
        params.put("age", age + "");
        params.put("phone", phone);
        params.put("email", email);
        params.put("year", year);
        params.put("gradeAverage", gradeAverage);
        params.put("workPlan", workPlan );
        params.put("meeting", meeting);
        params.put("prefGen", prefGen);
        params.put("workHours", workHours);
        params.put("iLocation", iLocation.toString());
        params.put("iGrade", iGrade.toString());
        params.put("faculty", faculty);
        params.put("course", course);
        params.put("workType", workType);

    }

    public RegisterUserReq(String user_name, String password, String dbname, String dbuser, String dbpass, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_USER_REQUEST_URL, listener, null);
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
