package com.example.pairingclient;

import android.content.SharedPreferences;
import android.util.DisplayMetrics;

import com.google.android.gms.maps.model.LatLng;

public class Globals {
    public static String faculty;
    public static String course;
    public static String workType;
    public static String global_user_name;
    public static Partner[] partners;
    public static LatLng Location;
    public static ListItem itemDetails;
    public static int typePair;

    //screen size parameters
    public static int ScreenWidth = 0;
    public static int ScreenHeight = 0;

    public static int ActualWidth = 0;
    public static int ActualHeight = 0;
    public static DisplayMetrics metrics;
    public static DisplayMetrics metrics2;
    public static float scaleDP;
    public static float scaleS= 1;
    public static float DP;
    public static float Ratio;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
}
