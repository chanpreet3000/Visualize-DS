package com.chanpreet.visualizeds.utils;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.classes.Difficulty;
import com.chanpreet.visualizeds.data_structure_algorithms.BIT.basics.BITBasicsActivity.BITBasicsActivity;
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
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.deletion.BSTDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.deletion.BSTDeletionTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.insertion.BSTInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.insertion.BSTInsertionTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.inorder.BSTInorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.inorder.BSTInorderTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.level_order.BSTLevelOrderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.level_order.BSTLevelOrderTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.postorder.BSTPostorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.postorder.BSTPostorderTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.preorder.BSTPreorderActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.preorder.BSTPreorderTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.deletion.DLLDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.deletion.DLLDeletionTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.insertion.DLLInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.insertion.DLLInsertionTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.traversal.DLLTraversalActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.traversal.DLLTraversalTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.deletion.LLDeletionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.deletion.LLDeletionTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.insertion.LLInsertionActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.insertion.LLInsertionTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.traversal.LLTraversalActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.traversal.LLTraversalTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.bfs.PFBFSActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.bfs.PFBFSTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.dfs.PFDFSActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.path_finding.basics.dfs.PFDFSTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.queue.basics.push_pop.QueuePushPopActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.queue.basics.push_pop.QueuePushPopTheory;
import com.chanpreet.visualizeds.data_structure_algorithms.stack.basics.push_pop.StackPushPopActivity;
import com.chanpreet.visualizeds.data_structure_algorithms.stack.basics.push_pop.StackPushPopTheory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {

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
                                                    new DataStructureAlgorithm("Array Linear Search", ArrayLinearSearchActivity.class, new ArrayLinearSearchTheory(), Difficulty.BASIC, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Array Binary Search", ArrayBinarySearchActivity.class, new ArrayBinarySearchTheory(), Difficulty.EASY, R.drawable.ic_binary_search)
                                            )), Difficulty.EASY, R.drawable.ic_search),
                                    new DataStructureTopic("Sorting",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Array Bubble Sort", ArrayBubbleSortActivity.class, new ArrayBubbleSortTheory(), Difficulty.BASIC, R.drawable.ic_bubble_sort),
                                                    new DataStructureAlgorithm("Array Selection Sort", ArraySelectionSortActivity.class, new ArraySelectionSortTheory(), Difficulty.BASIC, R.drawable.ic_selection_sort),
                                                    new DataStructureAlgorithm("Array Insertion Sort", ArrayInsertionSortActivity.class, new ArrayInsertionSortTheory(), Difficulty.EASY, R.drawable.ic_insertion_sort),
                                                    new DataStructureAlgorithm("Array Merge Sort", null, new ArrayMergeSortTheory(), Difficulty.MEDIUM, R.drawable.ic_merge_sort),
                                                    new DataStructureAlgorithm("Array Quick Sort", null, new ArrayQuickSortTheory(), Difficulty.MEDIUM, R.drawable.ic_merge_sort)
                                            )), Difficulty.EASY, R.drawable.ic_sorting)
                            )), Difficulty.EASY, R.drawable.ic_array_24),
                    new DataStructure("Linked List",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Linked List Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("LL Insertion", LLInsertionActivity.class, new LLInsertionTheory(), Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("LL Traversal/Searching", LLTraversalActivity.class, new LLTraversalTheory(), Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("LL Deletion", LLDeletionActivity.class, new LLDeletionTheory(), Difficulty.EASY, R.drawable.ic_deletion)
                                            )), Difficulty.EASY, R.drawable.ic_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_linked_list),
                    new DataStructure("Doubly Linked List",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Doubly Linked List Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("DLL Insertion", DLLInsertionActivity.class, new DLLInsertionTheory(), Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("DLL Traversal/Searching", DLLTraversalActivity.class, new DLLTraversalTheory(), Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("DLL Deletion", DLLDeletionActivity.class, new DLLDeletionTheory(), Difficulty.EASY, R.drawable.ic_deletion)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Stack",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Stack Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Stack PUSH/POP Operations", StackPushPopActivity.class, new StackPushPopTheory(), Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Queue",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Queue Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Stack PUSH/POP Operations", QueuePushPopActivity.class, new QueuePushPopTheory(), Difficulty.EASY, R.drawable.ic_add)
                                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list)
                            )), Difficulty.EASY, R.drawable.ic_doubly_linked_list),
                    new DataStructure("Binary Search Tree",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("BST Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("BST Insertion", BSTInsertionActivity.class, new BSTInsertionTheory(), Difficulty.EASY, R.drawable.ic_add),
                                                    new DataStructureAlgorithm("BST Deletion", BSTDeletionActivity.class, new BSTDeletionTheory(), Difficulty.MEDIUM, R.drawable.ic_deletion)
                                            )), Difficulty.MEDIUM, R.drawable.ic_tree),
                                    new DataStructureTopic("BST Traversals",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("BST In Order Traversal", BSTInorderActivity.class, new BSTInorderTheory(), Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("BST Pre Order Traversal", BSTPreorderActivity.class, new BSTPreorderTheory(), Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("BST Post Order Traversal", BSTPostorderActivity.class, new BSTPostorderTheory(), Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("BST Level Order Traversal", BSTLevelOrderActivity.class, new BSTLevelOrderTheory(), Difficulty.MEDIUM, R.drawable.ic_linear_search)
                                            )), Difficulty.EASY, R.drawable.ic_linear_search)
                            )), Difficulty.MEDIUM, R.drawable.ic_tree),
                    new DataStructure("Path Finding",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Path Finding Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Path Finding BFS", PFBFSActivity.class, new PFBFSTheory(), Difficulty.EASY, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Path Finding BFS", PFDFSActivity.class, new PFDFSTheory(), Difficulty.EASY, R.drawable.ic_linear_search)
                                            )), Difficulty.EASY, R.drawable.ic_linear_search)
                            )), Difficulty.EASY, R.drawable.ic_baseline_grid_on_24),
                    new DataStructure("Binary Indexed Tree/Fenwick Tree",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("BIT Basics",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("BIT Insertion/Range Query", BITBasicsActivity.class, new PFBFSTheory(), Difficulty.HARD, R.drawable.ic_linear_search)
                                            )), Difficulty.HARD, R.drawable.ic_linear_search)
                            )), Difficulty.HARD, R.drawable.ic_linear_search)

            ));
}
