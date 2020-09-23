package com.example.pairingclient;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;

public class showPartners extends AppCompatActivity {

    ListView listView = null;
    ArrayList<ListItem> listItems = null;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_partners);
        final ListView listView = (ListView) findViewById(R.id.listView);
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
                                for (int i = 0; i < Globals.partners.length; i++) {
                                    partners_string += "Course: " + Globals.partners[i].getCourse() + "\nTask: " + Globals.partners[i].getWorkType() + "\nPartner: " + Globals.partners[i].getName() + "\nPhone: "
                                            + Globals.partners[i].getPairPhone() + "\nEmail: " + Globals.partners[i].getEmail() + "\n------\n";
                                }


                                 */


                                listItems = new ArrayList<>();


                                for (int c = 0; c < Globals.partners.length; c++) {
                                    Partner partner = Globals.partners[c];
                                    if (partner != null) {
                                        ListItem listItem = new ListItem(partner.getId(), partner.getUsername(), partner.getName(), partner.getEmail(), partner.getPhone(), partner.getAgreed1(), partner.getFaculty(), partner.getCourse(), partner.getWorkType(), partner.getPair_UN(), partner.getPairName(), partner.getPairEmail(), partner.getPairPhone(), partner.getAgreed2(), getApplicationContext());
                                        listItems.add(listItem);

                                    }
                                }

                                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), listItems, listView);
                                if (listItems.size() > 0) {
                                    listView.setAdapter(customAdapter);
                                    listView.setVisibility(View.VISIBLE);
                                } else {
                                    listView.setAdapter(null);
                                    listView.setVisibility(View.INVISIBLE);
                                }

                                //partnersText.setText(partners_string);

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


        getPartnersReq getPartners = new getPartnersReq(Globals.global_user_name, "u747931869_FindPair", "u747931869_yuosifhanna", "V!5:Eg0H~", responseListener);
        RequestQueue queue = Volley.newRequestQueue(showPartners.this);
        queue.add(getPartners);



    }



}