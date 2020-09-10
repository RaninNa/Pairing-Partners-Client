package com.example.pairingclient;

import android.content.Context;

public class ListItem {
    private int id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private int agreed1;
    private String faculty;
    private String course;
    private String worktype;
    private String Pair_UN;
    private String pairName;
    private String emailOfPair;
    private String phoneOfPair;
    private int agreed2;
    private Context context;

    public ListItem(int id,  String username, String name, String email, String phone, int agreed1, String faculty, String course, String worktype, String Pair_UN, String pairName, String emailOfPair, String phoneOfPair, int agreed2, Context context) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.agreed1 = agreed1;
        this.faculty = faculty;
        this.course = course;
        this.worktype = worktype;
        this.Pair_UN = Pair_UN;
        this.pairName = pairName;
        this.emailOfPair = emailOfPair;
        this.phoneOfPair = phoneOfPair;
        this.agreed2 = agreed2;
        this.context = context;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getEmailOfPair() {
        return emailOfPair;
    }

    public void setEmailOfPair(String emailOfPair) {
        this.emailOfPair = emailOfPair;
    }

    public String getPhoneOfPair() {
        return phoneOfPair;
    }

    public void setPhoneOfPair(String phoneOfPair) {
        this.phoneOfPair = phoneOfPair;
    }

    public int getAgreed1() {
        return agreed1;
    }

    public void setAgreed1(int agreed) {
        this.agreed1 = agreed;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getPair_UN() {
        return Pair_UN;
    }

    public void setPair_UN(String pair_UN) {
        Pair_UN = pair_UN;
    }

    public int getAgreed2() {
        return agreed2;
    }

    public void setAgreed2(int agreed2) {
        this.agreed2 = agreed2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPairName() {
        return pairName;
    }

    public void setPairName(String pairName) {
        this.pairName = pairName;
    }
}
