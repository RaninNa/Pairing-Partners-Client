package com.example.pairingclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button logInBtn =  (Button) findViewById(R.id.logInBtn);
        Button registerBtn =  (Button) findViewById(R.id.registerBtn);
        final EditText user_nameT = (EditText) findViewById(R.id.UserName);
        final EditText passwordT = (EditText) findViewById(R.id.UserPassword);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Globals.global_user_name = user_nameT.getText().toString();
                Intent intent = new Intent(LogIn.this, MainActivity.class);
                LogIn.this.startActivity(intent);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, RegisterUserName.class);
                LogIn.this.startActivity(intent);
            }
        });
    }
}