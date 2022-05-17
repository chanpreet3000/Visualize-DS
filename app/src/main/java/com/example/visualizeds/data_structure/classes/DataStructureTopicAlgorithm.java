package com.example.visualizeds.data_structure.classes;

import java.io.Serializable;

//Algorithms like linear search and bubble sort
public class DataStructureTopicAlgorithm implements Serializable {
    private final String name;
    private final Class theoryClass;
    private final Class visualizeClass;
    private final Difficulty difficulty;
    private final Integer icon;
    private final Integer iconColor;

    public DataStructureTopicAlgorithm(String name, Class theoryClass, Class visualizeClass, Difficulty difficulty, Integer icon, Integer iconColor) {
        this.name = name;
        this.theoryClass = theoryClass;
        this.visualizeClass = visualizeClass;
        this.difficulty = difficulty;
        this.icon = icon;
        this.iconColor = iconColor;
    }

    public String getName() {
        return name;
    }

    public Class getTheoryClass() {
        return theoryClass;
    }

    public Class getVisualizeClass() {
        return visualizeClass;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Integer getIcon() {
        return icon;
    }

    public Integer getIconColor() {
        return iconColor;
    }
}
