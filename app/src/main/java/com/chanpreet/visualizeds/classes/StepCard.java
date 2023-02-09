package com.chanpreet.visualizeds.classes;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StepCard {
    private String title;
    private List<String> description;
    private View data;

    public StepCard() {
        title = "";
        description = new ArrayList<>();
        data = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public View getData() {
        return data;
    }

    public void setData(View data) {
        this.data = data;
    }
}
