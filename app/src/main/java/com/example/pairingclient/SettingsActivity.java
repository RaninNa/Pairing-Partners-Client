package com.example.pairingclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class SettingsActivity extends AppCompatActivity {
    JSONObject response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText ETname = (EditText) findViewById(R.id.editTextTextPersonName);
        final EditText ETage = (EditText) findViewById(R.id.editTextTextAge);
        final EditText ETphone = (EditText) findViewById(R.id.editTextTextPhone);
        final EditText ETemail = (EditText) findViewById(R.id.editTextTextEmail);
        final Spinner spinnerStudyYear = (Spinner) findViewById(R.id.spinnerStudYear);
        final EditText ETAverage = (EditText) findViewById(R.id.editTextTextGPA);
        final CheckBox AutoCompletion =(CheckBox) findViewById(R.id.checkBoxAutoCompletion);
        final Spinner spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        final Button btnSaveInfo = (Button) findViewById(R.id.btnSaveInfo);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item_blue);
        spinnerGender.setAdapter(adapter1);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.study_year_array, R.layout.spinner_item);
        adapter7.setDropDownViewResource(R.layout.spinner_item_blue);
        spinnerStudyYear.setAdapter(adapter7);
        spinnerStudyYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnPickUp = (Button) findViewById(R.id.btnPickLocation);
        btnPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ActivityLocation.class);
                SettingsActivity.this.startActivity(intent);
            }
        });
        String JsonString = Globals.sharedPreferences.getString("SaveInfo","null");


        if (!JsonString.equals("null")) {
            try {
                response = new JSONObject(JsonString);
                ETname.setText(response.getString("name"));
                ETage.setText(response.getString("age"));
                ETphone.setText(response.getString("phone"));
                ETemail.setText(response.getString("email"));
                ETAverage.setText(response.getString("gradeAverage"));
                spinnerStudyYear.setSelection(response.getInt("year"));
                spinnerGender.setSelection(response.getInt("gender"));
                JSONObject jsonLocation = new JSONObject(response.getString("location"));
                Globals.Location = new LatLng(jsonLocation.getDouble("latitude"), jsonLocation.getDouble("longitude"));

            } catch (JSONException e) {

            }
        }
        boolean JsonAuto = Globals.sharedPreferences.getBoolean("AutoCompletion", false);
        AutoCompletion.setChecked(JsonAuto);


        FixLayoutAspects();


        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (response != null) {
                    final JSONObject SaveInfo = new JSONObject();
                    try {
                        SaveInfo.put("user_name", response.getString("user_name"));
                        SaveInfo.put("name", ETname.getText());
                        SaveInfo.put("gender", spinnerGender.getSelectedItemPosition());
                        JSONObject locJson = new JSONObject();
                        locJson.put("latitude", Globals.Location.latitude);
                        locJson.put("longitude", Globals.Location.longitude);
                        SaveInfo.put("location", locJson.toString());
                        SaveInfo.put("age", ETage.getText());
                        SaveInfo.put("phone", ETphone.getText());
                        SaveInfo.put("email", ETemail.getText());
                        SaveInfo.put("year", spinnerStudyYear.getSelectedItemPosition());
                        SaveInfo.put("gradeAverage", ETAverage.getText());
                        SaveInfo.put("workPlan", response.getString("workPlan"));
                        SaveInfo.put("meeting", response.getString("meeting"));
                        SaveInfo.put("prefGen", response.getString("prefGen"));
                        SaveInfo.put("workHours", response.getString("workHours"));
                        SaveInfo.put("iLocation", response.getString("iLocation"));
                        SaveInfo.put("iGrade", response.getString("iGrade"));
                        SaveInfo.put("faculty", response.getString("faculty"));
                        SaveInfo.put("course", response.getString("course"));
                        SaveInfo.put("workType", response.getString("workType"));
                        Globals.editor = Globals.sharedPreferences.edit();
                        Globals.editor.putString("SaveInfo", SaveInfo.toString());
                        Globals.editor.commit();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                Globals.editor = Globals.sharedPreferences.edit();
                Globals.editor.putBoolean("AutoCompletion", AutoCompletion.isChecked());
                Globals.editor.commit();

                finish();

            }
        });



    }

    void FixLayoutAspects() {

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.RLSettings);
        int childCount = rl.getChildCount();

        ViewGroup.LayoutParams LPR = (ViewGroup.LayoutParams) rl.getLayoutParams();


        if (LPR.width > 0)
            LPR.width = (int) (LPR.width * Globals.scaleDP);
        if (LPR.height > 0)
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
            if (NewLP.height > 0)
                NewLP.height = (int) (LP.height * Globals.scaleDP);
            if (NewLP.width > 0)
                NewLP.width = (int) (LP.width * Globals.scaleDP);

            if (view instanceof Button) {
                Button button = (Button) view;
                float size = button.getTextSize();
                button.setTextSize((button.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                float size = textView.getTextSize();
                textView.setTextSize((textView.getTextSize() * Globals.scaleDP * Globals.scaleS) / Globals.DP);
            } else if (view instanceof EditText) {
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
                if (Globals.ActualWidth / (float) (Globals.ActualHeight) > 9.0f / 16.0f)
                    NewLP.topMargin = (int) (((NewLP.topMargin / Globals.DP) - 15) * Globals.DP);
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
        if (Globals.Ratio > 17f / 9f) {
            LPR.height = (int) (LPR.height * 1.3f);
        }
        rl.setLayoutParams(LPR);

        CardView c1 = (CardView) findViewById(R.id.CardViewSettings);


        childCount = c1.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = c1.getChildAt(i);
            //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (view.getLayoutParams().width * Globals.scaleDP),
            //        (int) (view.getLayoutParams().height * Globals.scaleDP));
            FrameLayout.LayoutParams LP = (FrameLayout.LayoutParams) view.getLayoutParams();
            //layoutParams.setMargins((int) (LP.leftMargin * Globals.scaleDP), (int) (LP.topMargin * Globals.scaleDP),
            //        (int) (LP.rightMargin * Globals.scaleDP), (int) (LP.bottomMargin * Globals.scaleDP));
            FrameLayout.LayoutParams NewLP = new FrameLayout.LayoutParams(LP);
            NewLP.gravity = LP.gravity;
            NewLP.topMargin = LP.topMargin;
            NewLP.leftMargin = LP.leftMargin;
            NewLP.bottomMargin = LP.bottomMargin;
            NewLP.rightMargin = LP.rightMargin;


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