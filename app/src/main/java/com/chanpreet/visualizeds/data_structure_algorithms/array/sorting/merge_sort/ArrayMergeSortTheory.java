package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.merge_sort;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArrayMergeSortTheory extends AlgorithmTheory {
    public ArrayMergeSortTheory() {
        super.theory =
                TextBuilder.makeBulletList(
                        "Merge Sort is one of the most popular sorting algorithms that is based on the principle of Divide and Conquer Algorithm.",
                        "Here, a problem is divided into multiple sub-problems. Each sub-problem is solved individually. Finally, sub-problems are combined to form the final solution.");
        super.algorithm =
                TextBuilder.makeOrderedList("The MergeSort function repeatedly divides the array into two halves until we reach a stage where we try to perform MergeSort on a sub array of size 1.",
                        "After that, the merge function comes into play and combines the sorted arrays into larger arrays until the whole array is merged.");

        super.code = "void merge(int arr[], int p, int q, int r) {\n" +
                "  \n" +
                "  int n1 = q - p + 1;\n" +
                "  int n2 = r - q;\n" +
                "\n" +
                "  int L[n1], M[n2];\n" +
                "\n" +
                "  for (int i = 0; i < n1; i++)\n" +
                "    L[i] = arr[p + i];\n" +
                "  for (int j = 0; j < n2; j++)\n" +
                "    M[j] = arr[q + 1 + j];\n" +
                "\n" +
                "  int i, j, k;\n" +
                "  i = 0;\n" +
                "  j = 0;\n" +
                "  k = p;\n" +
                "\n" +
                "  while (i < n1 && j < n2) {\n" +
                "    if (L[i] <= M[j]) {\n" +
                "      arr[k] = L[i];\n" +
                "      i++;\n" +
                "    } else {\n" +
                "      arr[k] = M[j];\n" +
                "      j++;\n" +
                "    }\n" +
                "    k++;\n" +
                "  }\n" +
                "\n" +
                "  while (i < n1) {\n" +
                "    arr[k] = L[i];\n" +
                "    i++;\n" +
                "    k++;\n" +
                "  }\n" +
                "\n" +
                "  while (j < n2) {\n" +
                "    arr[k] = M[j];\n" +
                "    j++;\n" +
                "    k++;\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "void mergeSort(int arr[], int l, int r) {\n" +
                "  if (l < r) {\n" +
                "    int m = l + (r - l) / 2;\n" +
                "\n" +
                "    mergeSort(arr, l, m);\n" +
                "    mergeSort(arr, m + 1, r);\n" +
                "\n" +
                "    merge(arr, l, m, r);\n" +
                "  }\n" +
                "}";
        super.worstCase = "N * Log N";
        super.averageCase = "N * Log N";
        super.bestCase = "N * Log N";
    }
}
