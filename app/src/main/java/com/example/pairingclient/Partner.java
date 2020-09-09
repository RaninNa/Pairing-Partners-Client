package com.example.pairingclient;

public class Partner {
    String name;
    String course;
    String email;
    String phone_number;

    public Partner(String name, String course, String email, String phone_number) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
