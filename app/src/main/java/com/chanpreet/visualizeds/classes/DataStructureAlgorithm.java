package com.chanpreet.visualizeds.classes;

import java.io.Serializable;

//Algorithms like linear search and bubble sort
public class DataStructureAlgorithm implements Serializable {
    private final String name;
    private final Class<?> visualizeClass;
    private final AlgorithmTheory algorithmTheory;
    private final Difficulty difficulty;
    private final Integer icon;

    public DataStructureAlgorithm(String name, Class<?> visualizeClass, AlgorithmTheory algorithmTheory, Difficulty difficulty, Integer icon) {
        this.name = name;
        this.visualizeClass = visualizeClass;
        this.algorithmTheory = algorithmTheory;
        this.difficulty = difficulty;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Class<?> getVisualizeClass() {
        return visualizeClass;
    }

    public AlgorithmTheory getAlgorithmTheory() {
        return algorithmTheory;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Integer getIcon() {
        return icon;
    }

}
