package com.chanpreet.visualizeds.data_structure_algorithms.array.searching.binary_search;

import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArrayBinarySearchTheory extends AlgorithmTheory {
    public ArrayBinarySearchTheory() {
        super.theory = "Binary Search is a searching algorithm used in a sorted array by repeatedly dividing the search interval in half. \n" +
                "\n" +
                "The idea of binary search is to use the information that the array is sorted and reduce the time complexity to O(Log n). ";
        super.algorithm = "1) Initialize start = 0 and end = n-1 where n is the size and start and end is the interval of the array. \n" +
                "\n" +
                "2) If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half (that is end = mid - 1).\n" +
                "\n" +
                "3) If the value of the search key is greater than the item in the middle of the interval, narrow the interval to the upper half (that is start = mid + 1).\n" +
                "\n" +
                "4) Repeat from Step 2 until the element is found at the middle of the interval.\n" +
                "\n" +
                "5) if start becomes equal to end that means the array has been fully traversed and the element is not present in the array.";

        super.code = "int main()\n" +
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
        super.worstCase = "Log N";
        super.averageCase = "Log N";
        super.bestCase = "1";
    }
}
