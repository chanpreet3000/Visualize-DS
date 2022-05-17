package com.example.visualizeds.data_structure.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.Difficulty;
import com.example.visualizeds.data_structure.classes.DataStructure;
import com.example.visualizeds.data_structure.classes.DataStructureTopic;
import com.example.visualizeds.data_structure.classes.DataStructureTopicAlgorithm;
import com.example.visualizeds.data_structure.adapter.DataStructureAdapter;
import com.example.visualizeds.data_structure.topics.array.sorting.bubble_sort.BubbleSortVisualizerActivity;
import com.example.visualizeds.databinding.ActivityDataStructureBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataStructureActivity extends AppCompatActivity {

    private ActivityDataStructureBinding binding;
    private DataStructureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Integer initialColor = R.color.baby_pink;

        List<DataStructure> dataStructures =
                new ArrayList<>(Arrays.asList(
                        new DataStructure("Array",
                                new ArrayList<>(Arrays.asList(
                                        new DataStructureTopic("Searching", new ArrayList<>(Arrays.asList(
                                                new DataStructureTopicAlgorithm("Linear Search", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Binary Search", null, null, Difficulty.BASIC, null, initialColor)
                                        )), Difficulty.BASIC, R.drawable.ic_arrow_right_24, initialColor),
                                        new DataStructureTopic("Sorting", new ArrayList<>(Arrays.asList(
                                                new DataStructureTopicAlgorithm("Bubble Sort", null, BubbleSortVisualizerActivity.class, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Selection Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Insertion Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Merge Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Quick Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Radix Sort", null, null, Difficulty.BASIC, null, initialColor)
                                        )), Difficulty.BASIC, null, initialColor)
                                )), Difficulty.BASIC, null, initialColor),
                        new DataStructure("Linked List",
                                new ArrayList<>(Arrays.asList(
                                        new DataStructureTopic("Searching", new ArrayList<>(Arrays.asList(
                                                new DataStructureTopicAlgorithm("Linear Search", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Binary Search", null, null, Difficulty.BASIC, null, initialColor)
                                        )), Difficulty.BASIC, R.drawable.ic_arrow_right_24, initialColor),
                                        new DataStructureTopic("Sorting", new ArrayList<>(Arrays.asList(
                                                new DataStructureTopicAlgorithm("Bubble Sort", null, BubbleSortVisualizerActivity.class, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Selection Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Insertion Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Merge Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Quick Sort", null, null, Difficulty.BASIC, null, initialColor),
                                                new DataStructureTopicAlgorithm("Radix Sort", null, null, Difficulty.BASIC, null, initialColor)
                                        )), Difficulty.BASIC, R.drawable.ic_arrow_right_24, initialColor)
                                )), Difficulty.BASIC, null, initialColor)

                ));
        initRecyclerView(dataStructures);
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureTopicActivity.class);
            intent.putExtra("data", dataStructures.get(position));
            startActivity(intent);
        });
    }

    private void initRecyclerView(List<DataStructure> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataStructureAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);


    }
}