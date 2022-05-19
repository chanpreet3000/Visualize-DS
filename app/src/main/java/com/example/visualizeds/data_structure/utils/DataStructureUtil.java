package com.example.visualizeds.data_structure.utils;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.DataStructure;
import com.example.visualizeds.data_structure.classes.DataStructureAlgorithm;
import com.example.visualizeds.data_structure.classes.DataStructureAlgorithmContent;
import com.example.visualizeds.data_structure.classes.DataStructureTopic;
import com.example.visualizeds.data_structure.classes.Difficulty;
import com.example.visualizeds.data_structure.topics.array.searching.binary_search.BinarySearchVisualizerActivity;
import com.example.visualizeds.data_structure.topics.array.searching.linear_search.LinearSearchVisualizerActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStructureUtil {

    public static List<Integer> listOfColors = new ArrayList<>(Arrays.asList(R.color.cedar_orange, R.color.oxford_blue, R.color.dark_green));

    public static List<DataStructure> dataStructures =
            new ArrayList<>(Arrays.asList(
                    new DataStructure("Array",
                            new ArrayList<>(Arrays.asList(
                                    new DataStructureTopic("Searching",
                                            new ArrayList<>(Arrays.asList(
                                                    new DataStructureAlgorithm("Linear Search", LinearSearchVisualizerActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_THEORY,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_CODE,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.LINEAR_SEARCH_BEST_CASE),
                                                            Difficulty.BASIC, R.drawable.ic_linear_search),
                                                    new DataStructureAlgorithm("Binary Search", BinarySearchVisualizerActivity.class,
                                                            new DataStructureAlgorithmContent(
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_THEORY,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_ALGORITHM,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_CODE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_WORST_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_AVERAGE_CASE,
                                                                    DataStructureAlgorithmContentUtil.BINARY_SEARCH_BEST_CASE),
                                                            Difficulty.EASY, R.drawable.ic_binary_search)
                                            )), Difficulty.EASY, R.drawable.ic_search)
                            )), Difficulty.EASY, R.drawable.ic_array_24)
            ));
}
