package com.chanpreet.visualizeds.utils;

import androidx.annotation.NonNull;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithmContent;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.classes.Difficulty;
import com.chanpreet.visualizeds.data_structure_algorithms.array.searching.BinarySearchActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.searching.LinearSearchActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.BubbleSortActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.InsertionSortActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.SelectionSortActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.deletion.BSTDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.insertion.BSTInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.infix.BSTInorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.postfix.BSTPostorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.preorder.BSTPreorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.DeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.InsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.TraversalActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.bfs.BFSActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.dfs.DFSActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.stack.basics.PushPopActivity;

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
                                                    new DataStructureAlgorithm("Linear Search", LinearSearchActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_THEORY,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_CODE,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_BEST_CASE),
                                                            Difficulty.BASIC, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Binary Search", BinarySearchActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_binary_search)
                                            )), Difficulty.EASY, R.drawable.ic_search),
                                    new DataStructureTopic("Sorting",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Bubble Sort", BubbleSortActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BUBBLE_SORT_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BUBBLE_SORT_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BUBBLE_SORT_CODE,
                                                                    DataStructureAlgorithmContentUtil.BUBBLE_SORT_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BUBBLE_SORT_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BUBBLE_SORT_BEST_CASE),
                                                            Difficulty.BASIC, R.drawable.ic_bubble_sort),
                                                    new DataStructureAlgorithm("Selection Sort", SelectionSortActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.SELECTION_SORT_THEORY,
                                                                    DataStructureAlgorithmContentUtil.SELECTION_SORT_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.SELECTION_SORT_CODE,
                                                                    DataStructureAlgorithmContentUtil.SELECTION_SORT_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.SELECTION_SORT_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.SELECTION_SORT_BEST_CASE),
                                                            Difficulty.BASIC, R.drawable.ic_selection_sort),
                                                    new DataStructureAlgorithm("Insertion Sort", InsertionSortActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.INSERTION_SORT_THEORY,
                                                                    DataStructureAlgorithmContentUtil.INSERTION_SORT_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.INSERTION_SORT_CODE,
                                                                    DataStructureAlgorithmContentUtil.INSERTION_SORT_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.INSERTION_SORT_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.INSERTION_SORT_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_insertion_sort),
                                                    new DataStructureAlgorithm("Merge Sort", null,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.MERGE_SORT_THEORY,
                                                                    DataStructureAlgorithmContentUtil.MERGE_SORT_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.MERGE_SORT_CODE,
                                                                    DataStructureAlgorithmContentUtil.MERGE_SORT_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.MERGE_SORT_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.MERGE_SORT_BEST_CASE),
                                                            Difficulty.MEDIUM, R.drawable.ic_merge_sort),
                                                    new DataStructureAlgorithm("Quick Sort", null,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.QUICK_SORT_THEORY,
                                                                    DataStructureAlgorithmContentUtil.QUICK_SORT_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.QUICK_SORT_CODE,
                                                                    DataStructureAlgorithmContentUtil.QUICK_SORT_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.QUICK_SORT_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.QUICK_SORT_BEST_CASE),
                                                            Difficulty.MEDIUM, R.drawable.ic_merge_sort)
                                            )), Difficulty.EASY, R.drawable.ic_sorting)
                            )), Difficulty.EASY, R.drawable.ic_array_24),
                    new DataStructure("Linked List",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Linked List Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Insertion", InsertionActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("Traversal/Searching", TraversalActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_TRAVERSAL_THEORY,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_TRAVERSAL_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_TRAVERSAL_CODE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_TRAVERSAL_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_TRAVERSAL_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_TRAVERSAL_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Deletion", DeletionActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_DELETION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_DELETION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_DELETION_CODE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_DELETION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_DELETION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINKED_LIST_DELETION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_deletion)
                                            )), Difficulty.EASY, R.drawable.ic_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_linked_list),
                    new DataStructure("Doubly Linked List",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Doubly Linked List Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Insertion", com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.InsertionActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("Traversal/Searching", com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.TraversalActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_TRAVERSAL_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_TRAVERSAL_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_TRAVERSAL_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_TRAVERSAL_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_TRAVERSAL_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_TRAVERSAL_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Deletion", com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.DeletionActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_DELETION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_DELETION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_DELETION_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_DELETION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_DELETION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_DELETION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_deletion)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Binary Search Tree",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Binary Search Tree Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Insertion", BSTInsertionActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("Deletion", BSTDeletionActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_DELETION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_DELETION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_DELETION_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_DELETION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_DELETION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_DELETION_BEST_CASE),
                                                            Difficulty.MEDIUM, R.drawable.ic_deletion)
                                            )), Difficulty.MEDIUM, R.drawable.ic_tree),
                                    new DataStructureTopic("Binary Search Tree Traversals",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Inorder Traversal", BSTInorderActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INFIX_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INFIX_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INFIX_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INFIX_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INFIX_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_INFIX_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Preorder Traversal", BSTPreorderActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_PREFIX_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_PREFIX_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_PREFIX_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_PREFIX_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_PREFIX_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_PREFIX_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Postorder Traversal", BSTPostorderActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_POSTFIX_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_POSTFIX_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_POSTFIX_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_POSTFIX_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_POSTFIX_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_TREE_POSTFIX_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_linear_search)
                                            )), Difficulty.EASY, R.drawable.ic_linear_search)
                            )), Difficulty.MEDIUM, R.drawable.ic_tree),
                    new DataStructure("Stack",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Stack Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("PUSH/POP Operations", PushPopActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Queue",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Queue Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("PUSH/POP Operations", com.chanpreet.visualizeds.data_structure_algorithms.queue.basics.PushPopActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Path Finding",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("BFS", BFSActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("DFS", DFSActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_THEORY,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_CODE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.DOUBLY_LINKED_LIST_INSERTION_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_add)
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
