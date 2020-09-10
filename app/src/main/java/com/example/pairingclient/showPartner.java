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

public class showPartner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_partner);
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
                                    String user_name = jsonData.getJSONObject(i).getString("user_name");
                                    String name = jsonData.getJSONObject(i).getString("name");
                                    String email = jsonData.getJSONObject(i).getString("email");
                                    String phone = jsonData.getJSONObject(i).getString("phone");
                                    int agreed1 = jsonData.getJSONObject(i).getInt("agreed1");
                                    String faculty = jsonData.getJSONObject(i).getString("faculty");
                                    String course = jsonData.getJSONObject(i).getString("course");
                                    String workType = jsonData.getJSONObject(i).getString("workType");
                                    String pairUserName = jsonData.getJSONObject(i).getString("pairUserName");
                                    String nameOfPair = jsonData.getJSONObject(i).getString("nameOfPair");
                                    String pairEmail = jsonData.getJSONObject(i).getString("emailOfPair");
                                    String pairPhone = jsonData.getJSONObject(i).getString("phoneOfPair");
                                    int agreed2 = jsonData.getJSONObject(i).getInt("agreed2");

                                    Partner partner = new Partner(id, user_name, name, email, phone, agreed1, faculty, course, workType, pairUserName, nameOfPair, pairEmail, pairPhone, agreed2);
                                    Globals.partners[i] = partner;
                                }
                                /*
                                 String partners_string = "";
                                for(int i = 0; i < Globals.partners.length ; i++) {
                                    partners_string += "Course: " + Globals.partners[i].getCourse()  + "\nTask: " + Globals.partners[i].getWorkType() + "\nPartner: " + Globals.partners[i].getName() + "\nPhone: "
                                            + Globals.partners[i].getPairPhone() + "\nEmail: " + Globals.partners[i].getEmail() + "\n------\n";
                                }
                                 */



                                //partnersText.setText(partners_string);

                            }


                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(showPartner.this);
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
        RequestQueue queue = Volley.newRequestQueue(showPartner.this);
        queue.add(getPartners);

    }
}