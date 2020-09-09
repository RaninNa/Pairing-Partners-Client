package com.example.pairingclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_info);

        final EditText ETname = (EditText) findViewById(R.id.editTextTextPersonName);
        final EditText ETage = (EditText) findViewById(R.id.editTextTextAge);
        final EditText ETphone = (EditText) findViewById(R.id.editTextTextPhone);
        final EditText ETemail = (EditText) findViewById(R.id.editTextTextEmail);
        final Spinner spinnerStudyYear = (Spinner) findViewById(R.id.spinnerStudYear);
        final EditText ETAverage = (EditText) findViewById(R.id.editTextTextGPA);

        final CheckBox CBiLocation = (CheckBox) findViewById(R.id.checkBoxLocation);
        final CheckBox CBiGrade = (CheckBox) findViewById(R.id.checkBoxGrade);


        final Spinner spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
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

        /*
        final Spinner spinnerLocation = (Spinner) findViewById(R.id.spinnerLocation);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        spinnerLocation.setAdapter(adapter2);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        */
        final Spinner spinnerWorkingWay = (Spinner) findViewById(R.id.spinnerWorkingWay);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.working_way_array, android.R.layout.simple_spinner_item);
        spinnerWorkingWay.setAdapter(adapter3);
        spinnerWorkingWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner spinnerMeetings = (Spinner) findViewById(R.id.spinnerMeetings);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.meetings_array, android.R.layout.simple_spinner_item);
        spinnerMeetings.setAdapter(adapter4);
        spinnerMeetings.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner spinnerPreferredGender = (Spinner) findViewById(R.id.spinnerPreferredGender);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.preffered_gender_array, android.R.layout.simple_spinner_item);
        spinnerPreferredGender.setAdapter(adapter5);
        spinnerPreferredGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final String[] hours = {"שעות עבודה", "בוקר", "צהוריים", "ערב"};

        final Spinner spinnerWorkingTime = (Spinner) findViewById(R.id.spinnerWorkingTime);

        ArrayList<ItemSpinner> listhours = new ArrayList<>();

        for (int i = 0; i < hours.length; i++) {
            ItemSpinner itemSpinner = new ItemSpinner();
            itemSpinner.setTitle(hours[i]);
            itemSpinner.setSelected(false);
            listhours.add(itemSpinner);
        }
        MyAdapter myAdapter = new MyAdapter(RegisterInfoActivity.this, 0,
                listhours);
        spinnerWorkingTime.setAdapter(myAdapter);




/*
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this,
                R.array.working_time_array, android.R.layout.simple_spinner_item);
        spinnerWorkingTime.setAdapter(adapter6);
        spinnerWorkingTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getContext(), "this is " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
            */









        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this,
                R.array.study_year_array, android.R.layout.simple_spinner_item);
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


        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        Button btnPickUp = (Button) findViewById(R.id.btnPickLocation);
        btnPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterInfoActivity.this, ActivityLocation.class);
                RegisterInfoActivity.this.startActivity(intent);
            }
        });

        //Toast.makeText(getApplicationContext(), "lat: " + Globals.Location.latitude + " long: " + Globals.Location.longitude, Toast.LENGTH_LONG).show();




        btnRegister.setOnClickListener(new View.OnClickListener() {
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
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterInfoActivity.this);
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

                if (ETname.getText().toString().equals("") || ETphone.getText().toString().equals("") || ETemail.getText().toString().equals("")
                        || ETAverage.getText().toString().equals("") || ETage.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "חובה למלא את כל הפרטים", Toast.LENGTH_LONG).show();
                    return;
                }
                if (Globals.Location == null) {
                    Toast.makeText(getApplicationContext(), "חובה לבחור מקום", Toast.LENGTH_LONG).show();
                    return;
                }
                if (spinnerGender.getSelectedItemPosition() == 0 /*  || spinnerLocation.getSelectedItemPosition() == 0 */ || spinnerStudyYear.getSelectedItemPosition() == 0
                        || spinnerWorkingWay.getSelectedItemPosition() == 0 || spinnerMeetings.getSelectedItemPosition() == 0 || spinnerPreferredGender.getSelectedItemPosition() == 0
                        || spinnerPreferredGender.getSelectedItemPosition() == 0 ) {
                    Toast.makeText(getApplicationContext(), "חובה למלא את כל הפרטים", Toast.LENGTH_LONG).show();
                    return;
                }

                String user_name = Globals.global_user_name;
                String name = ETname.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();
                String location = Globals.Location.latitude + "@" + Globals.Location.longitude;//spinnerLocation.getSelectedItem().toString();
                int age = Integer.parseInt(ETage.getText().toString());
                String phone = ETphone.getText().toString();
                String email = ETemail.getText().toString();
                String year = spinnerStudyYear.getSelectedItem().toString();
                String gradeAverage = ETAverage.getText().toString();
                String workPlan = spinnerWorkingWay.getSelectedItem().toString();
                String meeting = spinnerMeetings.getSelectedItem().toString();
                String prefGen = spinnerPreferredGender.getSelectedItem().toString();

                String workHours = "";// = spinnerWorkingTime.getSelectedItem().toString();
                for (int c = 1; c < 4; c++) {
                    if (((ItemSpinner) (spinnerWorkingTime.getItemAtPosition(c))).isSelected()) {
                        workHours += ((ItemSpinner) (spinnerWorkingTime.getItemAtPosition(c))).isSelected() + "@";
                    }
                }
                if(workHours.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "חובה למלא את כל הפרטים", Toast.LENGTH_LONG).show();
                    return;
                }
                workHours = workHours.substring(0, workHours.length() - 1);
                Boolean iLocation = CBiLocation.isChecked();
                Boolean iGrade = CBiGrade.isChecked();
                String faculty = Globals.faculty;
                String course = Globals.course;
                String workType = Globals.workType;


                RegisterUserReq registerRequest = new RegisterUserReq(user_name, name, gender, location, age, phone, email, year, gradeAverage, workPlan,
                        meeting, prefGen, workHours, iLocation, iGrade, faculty, course, workType, "id14702484_clients", "id14702484_pairingapp", "Pairing2020YR!", responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterInfoActivity.this);
                queue.add(registerRequest);


            }
        });
    }

}
