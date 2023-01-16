package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.insertion_sort;

import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArrayInsertionSortTheory extends AlgorithmTheory {
    public ArrayInsertionSortTheory() {
        super.theory = "Insertion sort is a simple sorting algorithm. The array is virtually split into a sorted and an unsorted part. Values from the unsorted part are picked and placed at the correct position in the sorted part.";
        super.algorithm =   "1) If the element is the first element, assume that it is already sorted. Return 1.\n" +
                "\n" +
                "2) Pick the next element, and store it separately in a key.\n" +
                "\n" +
                "3) Now, compare the key with all elements in the sorted array.\n" +
                "\n" +
                "4) If the element in the sorted array is smaller than the current element, then move to the next element. Else, shift greater elements in the array towards the right.\n" +
                "\n" +
                "5) Insert the value.\n" +
                "\n" +
                "6) Repeat until the array is sorted.";
        super.code = "int main()\n" +
                "{\n" +
                "    int n = 5;\n" +
                "    int arr[]{5, 4, 3, 2, 1};\n" +
                "    int i, j, temp;\n" +
                "    for (i = 1; i < n; i++)\n" +
                "    {\n" +
                "        temp = arr[i];\n" +
                "        j = i - 1;\n" +
                "\n" +
                "        while (j >= 0 && temp <= arr[j])\n" +
                "        {\n" +
                "            arr[j + 1] = arr[j];\n" +
                "            j = j - 1;\n" +
                "        }\n" +
                "        arr[j + 1] = temp;\n" +
                "    }\n" +
                "    for (int i = 0; i < n; i++)\n" +
                "    {\n" +
                "        cout << arr[i] << \" \";\n" +
                "    }\n" +
                "    return 0;\n" +
                "}";
        super.worstCase = "N<sup>2</sup>";
        super.averageCase = "N<sup>2</sup>";
        super.bestCase = "N";
    }
}
