package com.example.pairingclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
        FixLayoutAspects();
        Button createBtn = (Button) findViewById(R.id.createBtn);

        final EditText user_nameT = (EditText) findViewById(R.id.NewUserName);
        final EditText passwordT = (EditText) findViewById(R.id.NewUserPassword);
        final EditText repasswordT = (EditText) findViewById(R.id.RePassword);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            boolean taken = jsonResponse.getBoolean("taken");
                            if(taken)
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserName.this);
                                builder.setMessage("חשבון כבר קיים")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                                return;
                            }
                            String id = jsonResponse.getString("id");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "שליחה התבצעה", Toast.LENGTH_LONG).show();
                                finish();
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
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                if (user_nameT.getText().toString().equals("") || passwordT.getText().toString().equals("") || repasswordT.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "חובה למלא את כל הפרטים", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!repasswordT.getText().toString().equals(passwordT.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "שתי הסיסמאות לא זהות", Toast.LENGTH_LONG).show();
                    return;
                }

                String user_name = user_nameT.getText().toString();
                String password = passwordT.getText().toString();


                RegisterUserReq registerRequest = new RegisterUserReq(user_name, password,"u747931869_FindPair", "u747931869_yuosifhanna", "V!5:Eg0H~", responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterUserName.this);
                queue.add(registerRequest);

            }
        });
    }



    void FixLayoutAspects()
    {

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.RLRegisterUser);
        int childCount = rl.getChildCount();

        ViewGroup.LayoutParams LPR = (ViewGroup.LayoutParams) rl.getLayoutParams();


        if(LPR.width>0)
            LPR.width = (int) (LPR.width * Globals.scaleDP);
        if(LPR.height>0)
            LPR.height = (int) (LPR.height * Globals.scaleDP);
        rl.setLayoutParams(LPR);
        for (int i = 0; i < childCount; i++) {
            View view = rl.getChildAt(i);
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (view.getLayoutParams().width * Globals.scaleDP),
            //        (int) (view.getLayoutParams().height * Globals.scaleDP));
            RelativeLayout.LayoutParams LP = (RelativeLayout.LayoutParams) view.getLayoutParams();
            //layoutParams.setMargins((int) (LP.leftMargin * Globals.scaleDP), (int) (LP.topMargin * Globals.scaleDP),
            //        (int) (LP.rightMargin * Globals.scaleDP), (int) (LP.bottomMargin * Globals.scaleDP));
            RelativeLayout.LayoutParams NewLP = new RelativeLayout.LayoutParams(LP);
            int[] rules = LP.getRules();
            for (int verb = 0; verb < rules.length; verb++) {
                int subject = rules[verb];
                NewLP.addRule(verb, subject);
            }
            NewLP.setMargins((int) (LP.leftMargin * Globals.scaleDP), (int) (LP.topMargin * Globals.scaleDP),
                    (int) (LP.rightMargin * Globals.scaleDP), (int) (LP.bottomMargin * Globals.scaleDP));
            if (NewLP.height > 0 )
                NewLP.height = (int) (LP.height * Globals.scaleDP);
            if(NewLP.width > 0)
                NewLP.width = (int) (LP.width * Globals.scaleDP);

            if (view instanceof Button) {
                Button button = (Button) view;
                float size = button.getTextSize();
                button.setTextSize((button.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            }
            else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                float size = textView.getTextSize();
                textView.setTextSize((textView.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            }  else if (view instanceof EditText) {
                EditText editText = (EditText) view;
                float size = editText.getTextSize();
                editText.setTextSize((editText.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            } else if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                float height = imageView.getHeight();
                float width = imageView.getWidth();
                //view.setLayoutParams(NewLP);

                //imageView.setTextSize((imageView.getTextSize() * Globals.scaleDP)/ Globals.DP);
            } else if (view instanceof Spinner) {
                Spinner spinner = (Spinner) view;
                if(Globals.ActualWidth / (float)(Globals.ActualHeight) > 9.0f /16.0f)
                    NewLP.topMargin = (int)(((NewLP.topMargin / Globals.DP)-15)*Globals.DP) ;
                //view.setLayoutParams(NewLP);
            }

            view.setLayoutParams(NewLP);

            //view.setX(location[0]);
            //view.setY(location[1]);

            // Do something with v.
            // …


        }



        rl = findViewById(R.id.RLCardView);

        LPR = (ViewGroup.LayoutParams) rl.getLayoutParams();
        if (LPR.width > 0)
            LPR.width = (int) (LPR.width * Globals.scaleDP);
        if (LPR.height > 0)
            LPR.height = (int) (LPR.height * Globals.scaleDP);
        if(Globals.Ratio >17f / 9f ) {
            LPR.height = (int) (LPR.height * 1.3f);
        }
        rl.setLayoutParams(LPR);

        CardView c1 = (CardView) findViewById(R.id.CardViewRegisterUser);


        childCount = c1.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = c1.getChildAt(i);
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (view.getLayoutParams().width * Globals.scaleDP),
            //        (int) (view.getLayoutParams().height * Globals.scaleDP));
            FrameLayout.LayoutParams LP = (FrameLayout.LayoutParams) view.getLayoutParams();
            //layoutParams.setMargins((int) (LP.leftMargin * Globals.scaleDP), (int) (LP.topMargin * Globals.scaleDP),
            //        (int) (LP.rightMargin * Globals.scaleDP), (int) (LP.bottomMargin * Globals.scaleDP));
            FrameLayout.LayoutParams NewLP = new FrameLayout.LayoutParams(LP);
            NewLP.gravity=LP.gravity;
            NewLP.topMargin=LP.topMargin;
            NewLP.leftMargin=LP.leftMargin;
            NewLP.bottomMargin=LP.bottomMargin;
            NewLP.rightMargin=LP.rightMargin;



            NewLP.setMargins((int) (LP.leftMargin * Globals.scaleDP), (int) (LP.topMargin * Globals.scaleDP),
                    (int) (LP.rightMargin * Globals.scaleDP), (int) (LP.bottomMargin * Globals.scaleDP));

            if (NewLP.height > 0)
                NewLP.height = (int) (LP.height * Globals.scaleDP);
            if (NewLP.width > 0)
                NewLP.width = (int) (LP.width * Globals.scaleDP);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                float size = textView.getTextSize();
                textView.setTextSize((textView.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            } else if (view instanceof Button) {
                Button button = (Button) view;
                float size = button.getTextSize();
                button.setTextSize((button.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            } else if (view instanceof EditText) {
                EditText editText = (EditText) view;
                float size = editText.getTextSize();
                editText.setTextSize((editText.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            } else if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                float height = imageView.getHeight();
                float width = imageView.getWidth();

                //imageView.setTextSize((imageView.getTextSize() * Globals.scaleDP)/ Globals.DP);
            } else if (view instanceof Spinner) {
                Spinner spinner = (Spinner) view;

            }

            view.setLayoutParams(NewLP);


        }
    }
}