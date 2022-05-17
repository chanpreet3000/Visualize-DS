package com.example.visualizeds.data_structure.classes;

import java.io.Serializable;
import java.util.List;

//Data structures like Arrays and linked lists
public class DataStructure implements Serializable {
    private final String name;
    private final List<DataStructureTopic> dataStructureTopics;
    private final Difficulty difficulty;
    private final Integer icon;
    private final Integer iconColor;

    public DataStructure(String name, List<DataStructureTopic> dataStructureTopics, Difficulty difficulty, Integer icon, Integer iconColor) {
        this.name = name;
        this.dataStructureTopics = dataStructureTopics;
        this.difficulty = difficulty;
        this.icon = icon;
        this.iconColor = iconColor;
    }

    public String getName() {
        return name;
    }

    public List<DataStructureTopic> getDataStructureTopics() {
        return dataStructureTopics;
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
