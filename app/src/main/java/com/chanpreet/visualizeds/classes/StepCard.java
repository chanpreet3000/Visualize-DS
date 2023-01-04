package com.chanpreet.visualizeds.classes;

import android.view.View;

public class StepCard {
    private String title;
    private String description;
    private View data;

    public StepCard() {
        title = "NO TITLE SPECIFIED!";
        description = "NO DESCRIPTION SPECIFIED!";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public View getData() {
        return data;
    }

    public void setData(View data) {
        this.data = data;
    }
}
