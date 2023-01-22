package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.quick_sort;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArrayQuickSortTheory extends AlgorithmTheory {
    public ArrayQuickSortTheory() {
        super.theory = TextBuilder.makeBulletList(
                "Quicksort is the widely used sorting algorithm that makes n log n comparisons in average case for sorting an array of n elements.",
                "It is a faster and highly efficient sorting algorithm. This algorithm follows the divide and conquer approach.",
                "Divide and conquer is a technique of breaking down the algorithms into subproblems, then solving the subproblems, and combining the results back together to solve the original problem.");

        super.algorithm =
                TextBuilder.makeOrderedList("Quicksort picks an element as pivot, and then it partitions the given array around the picked pivot element.",
                        "In quick sort, a large array is divided into two arrays in which one holds values that are smaller than the specified value (Pivot), and another array holds the values that are greater than the pivot.",
                        "After that, left and right sub-arrays are also partitioned using the same approach.",
                        "It will continue until the single element remains in the sub-array.");

        super.code = "int partition (int arr[], int low, int high)\n" +
                "{\n" +
                "    int pivot = arr[high]; // pivot\n" +
                "    int i = (low - 1);\n" +
                " \n" +
                "    for (int j = low; j <= high - 1; j++)\n" +
                "    {\n" +
                "        if (arr[j] < pivot)\n" +
                "        {\n" +
                "            i++;\n" +
                "            swap(&arr[i], &arr[j]);\n" +
                "        }\n" +
                "    }\n" +
                "    swap(arr[i + 1], arr[high]);\n" +
                "    return (i + 1);\n" +
                "}\n" +
                " \n" +
                "void quickSort(int arr[], int low, int high)\n" +
                "{\n" +
                "    if (low < high)\n" +
                "    {\n" +
                "        int pi = partition(arr, low, high);\n" +
                " \n" +
                "        quickSort(arr, low, pi - 1);\n" +
                "        quickSort(arr, pi + 1, high);\n" +
                "    }\n" +
                "}";
        super.worstCase = "N<sup>2</sup>";
        super.averageCase = "N * Log N";
        super.bestCase = "N * Log N";
    }
}
