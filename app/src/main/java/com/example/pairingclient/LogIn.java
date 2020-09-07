package com.example.pairingclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

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

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            String id = jsonResponse.getString("id");
                            if (success) {
                                Globals.global_user_name = user_nameT.getText().toString();
                                Intent intent = new Intent(LogIn.this, MainActivity.class);
                                LogIn.this.startActivity(intent);

                                try {
                                    //if (AuthenticateUser.this != null)
                                    //hideSoftKeyboard(AuthenticateUser.this);
                                } catch (Exception e) {

                                }

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LogIn.this);
                                builder.setMessage("User Name or password is not correct")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                if (user_nameT.getText().toString().equals("") || passwordT.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "חובה למלא את כל הפרטים", Toast.LENGTH_LONG).show();
                    return;
                }

                String user_name = user_nameT.getText().toString();
                String password = passwordT.getText().toString();


                CheckAccount check = new CheckAccount(user_name, password,"id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                RequestQueue queue = Volley.newRequestQueue(LogIn.this);
                queue.add(check);

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