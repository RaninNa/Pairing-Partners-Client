package com.example.pairingclient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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

public class RegisterUserName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_name);

        Button createBtn = (Button) findViewById(R.id.createBtn);

        final EditText user_nameT = (EditText) findViewById(R.id.NewUserName);
        final EditText passwordT = (EditText) findViewById(R.id.NewUserPassword);

        createBtn.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(getApplicationContext(), "שליחה התבצעה", Toast.LENGTH_LONG).show();
                                //Intent intent = new Intent();
                                //getActivity().startActivity(intent);
                                //Intent intent = new Intent(AuthenticateUser.this, RegisterEventActivity.class);
                                //AuthenticateUser.this.startActivity(intent);

                                try {
                                    //if (AuthenticateUser.this != null)
                                    //hideSoftKeyboard(AuthenticateUser.this);
                                } catch (Exception e) {

                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "שליחה נכשלה", Toast.LENGTH_LONG).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserName.this);
                                builder.setMessage("Register Failed")
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


                RegisterUserReq registerRequest = new RegisterUserReq(user_name, password,"id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterUserName.this);
                queue.add(registerRequest);

            }
        });
    }
}