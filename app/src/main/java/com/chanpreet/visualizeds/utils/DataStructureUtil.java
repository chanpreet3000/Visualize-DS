package com.chanpreet.visualizeds.utils;

import androidx.annotation.NonNull;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.classes.Difficulty;
import com.chanpreet.visualizeds.data_structure_algorithms.array.searching.binary_search.ArrayBinarySearchActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.searching.binary_search.ArrayBinarySearchTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.array.searching.linear_search.ArrayLinearSearchActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.searching.linear_search.ArrayLinearSearchTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.bubble_sort.ArrayBubbleSortActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.bubble_sort.ArrayBubbleSortTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.insertion_sort.ArrayInsertionSortActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.insertion_sort.ArrayInsertionSortTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.merge_sort.ArrayMergeSortTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.quick_sort.ArrayQuickSortTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.selection_sort.ArraySelectionSortActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.selection_sort.ArraySelectionSortTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.BSTDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.BSTInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.BSTInorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.BSTPostorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.BSTPreorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.DLLDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.DLLInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.DLLTraversalActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.LLDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.LLInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.LLTraversalActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.PFBFSActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.PFDFSActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.queue.basics.QueuePushPopActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.stack.basics.StackPushPopActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStructureUtil {

    public static List<Integer> listOfColors = new ArrayList<>(Arrays.asList(
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6));

    public static List<DataStructure> dataStructures =
            new ArrayList<>(Arrays.asList(
                    new DataStructure("Array",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Searching",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Linear Search", ArrayLinearSearchActivity.class, new ArrayLinearSearchTheory(), Difficulty.BASIC, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Binary Search", ArrayBinarySearchActivity.class, new ArrayBinarySearchTheory(), Difficulty.EASY, R.drawable.ic_binary_search)
                                            )), Difficulty.EASY, R.drawable.ic_search),
                                    new DataStructureTopic("Sorting",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Bubble Sort", ArrayBubbleSortActivity.class, new ArrayBubbleSortTheory(), Difficulty.BASIC, R.drawable.ic_bubble_sort),
                                                    new DataStructureAlgorithm("Selection Sort", ArraySelectionSortActivity.class, new ArraySelectionSortTheory(), Difficulty.BASIC, R.drawable.ic_selection_sort),
                                                    new DataStructureAlgorithm("Insertion Sort", ArrayInsertionSortActivity.class, new ArrayInsertionSortTheory(), Difficulty.EASY, R.drawable.ic_insertion_sort),
                                                    new DataStructureAlgorithm("Merge Sort", null, new ArrayMergeSortTheory(), Difficulty.MEDIUM, R.drawable.ic_merge_sort),
                                                    new DataStructureAlgorithm("Quick Sort", null, new ArrayQuickSortTheory(), Difficulty.MEDIUM, R.drawable.ic_merge_sort)
                                            )), Difficulty.EASY, R.drawable.ic_sorting)
                            )), Difficulty.EASY, R.drawable.ic_array_24),
                    new DataStructure("Linked List",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Linked List Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Insertion", LLInsertionActivity.class, null, Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("Traversal/Searching", LLTraversalActivity.class, null, Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Deletion", LLDeletionActivity.class, null, Difficulty.EASY, R.drawable.ic_deletion)
                                            )), Difficulty.EASY, R.drawable.ic_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_linked_list),
                    new DataStructure("Doubly Linked List",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Doubly Linked List Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Insertion", DLLInsertionActivity.class, null, Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("Traversal/Searching", DLLTraversalActivity.class, null, Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Deletion", DLLDeletionActivity.class, null, Difficulty.EASY, R.drawable.ic_deletion)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Stack",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Stack Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("PUSH/POP Operations", StackPushPopActivity.class, null, Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Queue",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Queue Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("PUSH/POP Operations", QueuePushPopActivity.class, null, Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Binary Search Tree",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Binary Search Tree Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Insertion", BSTInsertionActivity.class, null, Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("Deletion", BSTDeletionActivity.class, null, Difficulty.MEDIUM, R.drawable.ic_deletion)
                                            )), Difficulty.MEDIUM, R.drawable.ic_tree),
                                    new DataStructureTopic("Binary Search Tree Traversals",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Inorder Traversal", BSTInorderActivity.class, null, Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Preorder Traversal", BSTPreorderActivity.class, null, Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Postorder Traversal", BSTPostorderActivity.class, null, Difficulty.EASY, R.drawable.ic_linear_search)
                                            )), Difficulty.EASY, R.drawable.ic_linear_search)
                            )), Difficulty.MEDIUM, R.drawable.ic_tree),
                    new DataStructure("Path Finding",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("BFS", PFBFSActivity.class, null, Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("DFS", PFDFSActivity.class, null, Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)

            ));


    @NonNull
    public static List<Integer> stringToArray(@NonNull String s) {
        StringBuilder c = new StringBuilder();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                try {
                    int number = Integer.parseInt(c.toString());
                    arr.add(number);
                    c = new StringBuilder();
                } catch (Exception ignored) {
                }

            } else {
                c.append(s.charAt(i));
            }
            if (i == s.length() - 1) {
                try {
                    int number = Integer.parseInt(c.toString());
                    arr.add(number);
                    c = new StringBuilder();
                } catch (Exception ignored) {
                }
            }
        }
        return arr;
    }
}
