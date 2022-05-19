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
    public static final String LINEAR_SEARCH_WORST_CASE = "N";
    public static final String LINEAR_SEARCH_AVERAGE_CASE = "N";
    public static final String LINEAR_SEARCH_BEST_CASE = "1";


    //Binary Search
    public static final String BINARY_SEARCH_THEORY =
            "Binary Search is a searching algorithm used in a sorted array by repeatedly dividing the search interval in half. \n" +
                    "\n" +
                    "The idea of binary search is to use the information that the array is sorted and reduce the time complexity to O(Log n). ";
    public static final String BINARY_SEARCH_ALGORITHM =
            "1) Initialize start = 0 and end = n-1 where n is the size and start and end is the interval of the array. \n" +
                    "\n" +
                    "2) If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half (that is end = mid - 1).\n" +
                    "\n" +
                    "3) If the value of the search key is greater than the item in the middle of the interval, narrow the interval to the upper half (that is start = mid + 1).\n" +
                    "\n" +
                    "4) Repeat from Step 2 until the element is found at the middle of the interval.\n" +
                    "\n" +
                    "5) if start becomes equal to end that means the array has been fully traversed and the element is not present in the array.";
    public static final String BINARY_SEARCH_CODE =
            "int main()\n" +
                    "{\n" +
                    "    int n = 5, target = 5;\n" +
                    "    int arr[]{1, 2, 3, 4, 5};\n" +
                    "    int start = 0, end = n - 1;\n" +
                    "    while (start <= end)\n" +
                    "    {\n" +
                    "        int mid = (start + end) / 2;\n" +
                    "        if (arr[mid] == target)\n" +
                    "        {\n" +
                    "            cout << \"Found\" << endl;\n" +
                    "            return 0;\n" +
                    "        }\n" +
                    "        else if (arr[mid] < target)\n" +
                    "        {\n" +
                    "            start = mid + 1;\n" +
                    "        }\n" +
                    "        else\n" +
                    "        {\n" +
                    "            end = mid - 1;\n" +
                    "        }\n" +
                    "    }\n" +
                    "    cout << \"Not Found\" << endl;\n" +
                    "    return 0;\n" +
                    "}";
    public static final String BINARY_SEARCH_WORST_CASE = "Log N";
    public static final String BINARY_SEARCH_AVERAGE_CASE = "Log N";
    public static final String BINARY_SEARCH_BEST_CASE = "1";

}
