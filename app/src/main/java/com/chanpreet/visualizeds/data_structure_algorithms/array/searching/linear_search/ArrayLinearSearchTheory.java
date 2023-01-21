package com.chanpreet.visualizeds.data_structure_algorithms.array.searching.linear_search;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArrayLinearSearchTheory extends AlgorithmTheory {
    public ArrayLinearSearchTheory() {

        super.theory =
                TextBuilder.makeBulletList("A linear search is the simplest method of searching a data set.",
                        "Starting at the beginning of the data set, each item of data is examined until a match is made.",
                        "Once the item is found, the search ends.",
                        "If there is no match, the algorithm must deal with this.");
        super.algorithm =
                TextBuilder.makeOrderedList("Set i = 0, here i refers to the index of the array.",
                        "Check if the element at the ith index is the target element.",
                        "If the element at ith index is the target end the search.",
                        "If the element at ith index is not the target increment i (i = i + 1).",
                        "Repeat from Step 2.",
                        "If i gets greater than the size of the array then the target does not exists.");
        super.code = "int main()\n" +
                "{\n" +
                "    int n = 5, i = 0, target = 5;\n" +
                "    int arr[]{1, 2, 3, 4, 5};\n" +
                "    while (i < n)\n" +
                "    {\n" +
                "        if (arr[i] == target)\n" +
                "        {\n" +
                "            cout << \"Found\" << endl;\n" +
                "            return 0;\n" +
                "        }\n" +
                "        i = i + 1;\n" +
                "    }\n" +
                "    cout << \"Not Found\" << endl;\n" +
                "    return 0;\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "1";
    }
}
