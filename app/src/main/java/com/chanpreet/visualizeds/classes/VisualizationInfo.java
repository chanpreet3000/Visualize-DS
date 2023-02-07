package com.chanpreet.visualizeds.classes;

import java.io.Serializable;
import java.util.Map;

public class VisualizationInfo implements Serializable {
    private Long time;
    private String name;
    private Map<String, Object> information;

    public VisualizationInfo() {
    }

    public VisualizationInfo(Long time, String name, Map<String, Object> information) {
        this.time = time;
        this.name = name;
        this.information = information;
    }

    public Long getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getInformation() {
        return information;
    }
}
