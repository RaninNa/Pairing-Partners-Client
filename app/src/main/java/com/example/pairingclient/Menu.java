package com.example.pairingclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button checkPartnerBtn =  (Button) findViewById(R.id.checkPartnerBtn);
        Button newCourseBtn =  (Button) findViewById(R.id.newCourseBtn);

        checkPartnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, showPartners.class);
                Menu.this.startActivity(intent);
            }
        });

        newCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                Menu.this.startActivity(intent);
            }
        });
    }
}