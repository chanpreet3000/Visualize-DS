package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.selection_sort;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArraySelectionSortTheory extends AlgorithmTheory {
    public ArraySelectionSortTheory() {
        super.theory = TextBuilder.makeBulletList(
                "Selection sort is a simple sorting algorithm. This sorting algorithm is an in-place comparison-based algorithm in which the list is divided into two parts, the sorted part at the left end and the unsorted part at the right end. Initially, the sorted part is empty and the unsorted part is the entire list.",
                "The smallest element is selected from the unsorted array and swapped with the leftmost element, and that element becomes a part of the sorted array. This process continues moving unsorted array boundary by one element to the right.");
        super.algorithm = TextBuilder.makeOrderedList("Set MIN to location 0",
                "Search the minimum element in the list",
                "Swap with value at location MIN",
                "Increment MIN to point to next element",
                "Repeat until list is sorted");
        super.code = "int main()\n" +
                "{\n" +
                "    int n = 5;\n" +
                "    int arr[]{5, 4, 3, 2, 1};\n" +
                "    for (int i = 0; i < n; i++)\n" +
                "    {\n" +
                "        int pos = i;\n" +
                "        for (int j = i; j < n; j++)\n" +
                "        {\n" +
                "            if (arr[j] < arr[pos])\n" +
                "            {\n" +
                "                pos = j;\n" +
                "            }\n" +
                "        }\n" +
                "        swap(arr[pos], arr[i]);\n" +
                "    }\n" +
                "    for (int i = 0; i < n; i++)\n" +
                "    {\n" +
                "        cout << arr[i] << \" \";\n" +
                "    }\n" +
                "    return 0;\n" +
                "}";
        super.worstCase = "N<sup>2</sup>";
        super.averageCase = "N<sup>2</sup>";
        super.bestCase = "N<sup>2</sup>";
    }
}
