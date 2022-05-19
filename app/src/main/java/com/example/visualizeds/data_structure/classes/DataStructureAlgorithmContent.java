package com.example.visualizeds.data_structure.classes;

import java.io.Serializable;

public class DataStructureAlgorithmContent implements Serializable {
    private final String theory;
    private final String algorithm;
    private final String code;
    private final Integer worstCase;
    private final Integer averageCase;
    private final Integer bestCase;

    public DataStructureAlgorithmContent(String theory, String algorithm, String code, Integer worstCase, Integer averageCase, Integer bestCase) {
        this.theory = theory;
        this.algorithm = algorithm;
        this.code = code;
        this.worstCase = worstCase;
        this.averageCase = averageCase;
        this.bestCase = bestCase;
    }

    public String getTheory() {
        return theory;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getCode() {
        return code;
    }

    public Integer getWorstCase() {
        return worstCase;
    }

    public Integer getAverageCase() {
        return averageCase;
    }

    public Integer getBestCase() {
        return bestCase;
    }
}
