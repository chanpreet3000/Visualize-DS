package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.bubble_sort;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class ArrayBubbleSortTheory extends AlgorithmTheory {
    public ArrayBubbleSortTheory() {
        super.theory =
                TextBuilder.makeBulletList("Bubble sort is a sorting algorithm that compares two adjacent elements and swaps them until they are not in the intended order.",
                        "Just like the movement of air bubbles in the water that rise up to the surface, each element of the array move to the end in each iteration.",
                        "Therefore, it is called a bubble sort.");
        super.algorithm = TextBuilder.makeOrderedList("Starting from the first index, compare the first and the second elements.",
                "If the first element is greater than the second element, they are swapped.",
                "Now, compare the second and the third elements. Swap them if they are not in order.",
                "The above process goes on until the last element.",
                "After this the largest element will come at the end of the array.",
                "Repeat from Step 1 (N - 1) more time to sort all elements.\n");

        super.code = "int main()\n" +
                "{\n" +
                "    int n = 5;\n" +
                "    int arr[]{5, 4, 3, 2, 1};\n" +
                "    int start = 0, end = n - 1;\n" +
                "    for (int i = 0; i < n; i++)\n" +
                "        // swapping if the next element is smaller than the previous elements\n" +
                "        for (int j = 0; j < n - 1; j++)\n" +
                "            if (arr[j] > arr[j + 1])\n" +
                "                swap(arr[j], arr[j + 1]);\n" +
                "\n" +
                "    // printing sorted array\n" +
                "    for (int i = 0; i < n; i++)\n" +
                "        cout << arr[i] << \" \";\n" +
                "    return 0;\n" +
                "}";
        super.worstCase = "N<sup>2</sup>";
        super.averageCase = "N<sup>2</sup>";
        super.bestCase = "N";
    }
}
