package com.example.visualizeds.data_structure.activity;

import com.example.visualizeds.databinding.ActivityDataStructureTopicBinding;
import com.example.visualizeds.data_structure.classes.DataStructure;
import com.example.visualizeds.data_structure.classes.DataStructureTopic;
import com.example.visualizeds.data_structure.adapter.DataStructureTopicAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class DataStructureTopicActivity extends AppCompatActivity {

    private ActivityDataStructureTopicBinding binding;
    private DataStructureTopicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureTopicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DataStructure dataStructure = (DataStructure) getIntent().getSerializableExtra("data");

        initRecyclerView(dataStructure.getDataStructureTopics());
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureTopicAlgorithmActivity.class);
            intent.putExtra("data", dataStructure.getDataStructureTopics().get(position));
            startActivity(intent);
        });
    }

    private void initRecyclerView(List<DataStructureTopic> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataStructureTopicAdapter(list);
        binding.recyclerView.setAdapter(adapter);
    }
}