package com.example.pairingclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class showPartners extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_partners);
        Globals.course = "מבוא למדעי המחשב";
        final TextView partnersText = (TextView) findViewById(R.id.partnersText);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response.indexOf("success") >= 0) {
                        JSONArray jsonResponse = new JSONArray(response);
                        boolean success = jsonResponse.getJSONObject(0).getBoolean("success");
                        response = "[" + response.substring(19);
                        JSONArray jsonData = new JSONArray(response);
                        //jsonData.length()
                        int ArrayPartnersCount = jsonData.length();
                        Globals.partners = new Partner[ArrayPartnersCount];

                        if (success) {
                            int index = 0;
                            if (!jsonData.toString().equals("[null]")) {
                                for (int i = 0; i < jsonData.length(); i++) {
                                    int id = jsonData.getJSONObject(i).getInt("id");
                                    String Name = jsonData.getJSONObject(i).getString("nameOfPair");
                                    String course = jsonData.getJSONObject(i).getString("course");
                                    String Email = jsonData.getJSONObject(i).getString("emailOfPair");
                                    String Phone = jsonData.getJSONObject(i).getString("phoneOfPair");

                                    Partner partner = new Partner(Name, course, Email, Phone);
                                    Globals.partners[i] = partner;
                                }

                                String partners_string = "";
                                for(int i = 0; i < Globals.partners.length ; i++) {
                                    partners_string += "Course: " + Globals.partners[i].getCourse() + "\nPartner: " + Globals.partners[i].getName() + "\nPhone: "
                                            + Globals.partners[i].getPhone_number() + "\nEmail: " + Globals.partners[i].getEmail() + "\n------\n";
                                }

                                partnersText.setText(partners_string);

                            }


                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(showPartners.this);
                            builder.setMessage("you dont have partners yet, come back later")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        getPartnersReq getPartners = new getPartnersReq(Globals.global_user_name,"id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
        RequestQueue queue = Volley.newRequestQueue(showPartners.this);
        queue.add(getPartners);

    }
}