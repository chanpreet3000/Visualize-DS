package com.chanpreet.visualizeds.classes;

public class UserInfo {
    private final String fullName, email, gender, age;
    private long dataCoins, lastDataCoinTime;

    public UserInfo() {
        lastDataCoinTime = 0;
        fullName = email = gender = age = "INVALID DATA";
        dataCoins = 0;
    }

    public UserInfo(String fullName, String email, String gender, String age) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        dataCoins = 5;
        this.lastDataCoinTime = System.currentTimeMillis();
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

    public long getDataCoins() {
        return dataCoins;
    }

    public long getLastDataCoinTime() {
        return lastDataCoinTime;
    }

    public void setDataCoins(long dataCoins) {
        this.dataCoins = dataCoins;
    }

    public void setLastDataCoinTime(long lastDataCoinTime) {
        this.lastDataCoinTime = lastDataCoinTime;
    }
}
