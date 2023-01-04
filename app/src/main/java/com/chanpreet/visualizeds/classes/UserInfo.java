package com.chanpreet.visualizeds.classes;

public class UserInfo {
    public final String fullName, email, gender, age;

    public UserInfo() {
        fullName = email = gender = age = "INVALID DATA";
    }

    public UserInfo(String fullName, String email, String gender, String age) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }
}
