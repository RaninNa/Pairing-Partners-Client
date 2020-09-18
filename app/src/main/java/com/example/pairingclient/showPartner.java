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
        String partners_string = "";
        if(Globals.typePair == 0)
        {
            partners_string += "קורס: " + Globals.itemDetails.getCourse() + "\nמטלה: " + Globals.itemDetails.getWorktype() + "\nשותף/ה: " + Globals.itemDetails.getPairName() + "\nמס טלפון: "
                    + Globals.itemDetails.getPhoneOfPair() + "\nאימייל: " + Globals.itemDetails.getEmailOfPair() + "\n------\n";
        }
        else
        {
            partners_string += "קורס: " + Globals.itemDetails.getCourse() + "\nמטלה: " + Globals.itemDetails.getWorktype() + "\nשותף/ה: " + Globals.itemDetails.getName() + "\nמס טלפון: "
                    + Globals.itemDetails.getPhone() + "\nאימייל: " + Globals.itemDetails.getEmail() + "\n------\n";
        }
        partnersText.setText(partners_string);

    }
}