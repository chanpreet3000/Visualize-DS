package com.chanpreet.visualizeds.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfo implements Serializable {
    private String fullName, email, gender, age;
    private List<VisualizationInfo> visualizationInfoList;

    public UserInfo() {
        fullName = email = gender = age = "INVALID DATA";
        visualizationInfoList = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<VisualizationInfo> getVisualizationInfoList() {
        return visualizationInfoList;
    }

    public void setVisualizationInfoList(List<VisualizationInfo> visualizationInfoList) {
        this.visualizationInfoList = visualizationInfoList;
    }
}
