package com.example.visualizeds.data_structure.classes;

import java.io.Serializable;
import java.util.List;

//For topics like searching and sorting
public class DataStructureTopic implements Serializable {
    private final String name;
    private final List<DataStructureTopicAlgorithm> dataStructureTopicAlgorithms;
    private final Difficulty difficulty;
    private final Integer icon;
    private final Integer iconColor;

    public DataStructureTopic(String name, List<DataStructureTopicAlgorithm> dataStructureTopicAlgorithms, Difficulty difficulty, Integer icon, Integer iconColor) {
        this.name = name;
        this.dataStructureTopicAlgorithms = dataStructureTopicAlgorithms;
        this.difficulty = difficulty;
        this.icon = icon;
        this.iconColor = iconColor;
    }

    public String getName() {
        return name;
    }

    public List<DataStructureTopicAlgorithm> getDataStructureTopicAlgorithms() {
        return dataStructureTopicAlgorithms;
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
