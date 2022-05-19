package com.example.visualizeds.data_structure.utils;

public class DataStructureAlgorithmContentUtil {

    //Linear Search
    public static final String LINEAR_SEARCH_THEORY =
            "A linear search is the simplest method of searching a data set.\n"
                    + "\n"
                    + "Starting at the beginning of the data set, each item of data is examined until a match is made. Once the item is found, the search ends. If there is no match, the algorithm must deal with this.";
    public static final String LINEAR_SEARCH_ALGORITHM =
            "1) Set i = 0, here i refers to the index of the array.\n" +
                    "\n" +
                    "2) Check if the element at the ith index is the target element.\n" +
                    "\n" +
                    "3) If the element at ith index is the target end the search.\n" +
                    "\n" +
                    "4) If the element at ith index is not the target increment i (i = i + 1).\n" +
                    "\n" +
                    "5) Repeat from Step 2.\n" +
                    "\n" +
                    "6) If i gets greater than the size of the array then the target does not exists.";
    public static final String LINEAR_SEARCH_CODE =
            "int main()\n" +
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
    public static final Integer LINEAR_SEARCH_WORST_CASE = 1;
    public static final Integer LINEAR_SEARCH_AVERAGE_CASE = 1;
    public static final Integer LINEAR_SEARCH_BEST_CASE = 0;

}
