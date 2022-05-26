package com.example.visualizeds.activity;

import com.example.visualizeds.databinding.ActivityDataStructureTopicBinding;
import com.example.visualizeds.classes.DataStructure;
import com.example.visualizeds.classes.DataStructureTopic;
import com.example.visualizeds.adapter.DataStructureTopicAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class DataStructureTopicActivity extends AppCompatActivity {

    private ActivityDataStructureTopicBinding binding;
    private DataStructureTopicAdapter adapter;
    private DataStructure dataStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureTopicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataStructure = (DataStructure) getIntent().getSerializableExtra("data");

        binding.headingTextView.setText(String.format("%s Topics", dataStructure.getName()));

        initRecyclerView(dataStructure.getDataStructureTopics());
    }

    private void initRecyclerView(List<DataStructureTopic> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataStructureTopicAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureAlgorithmActivity.class);
            intent.putExtra("data", dataStructure.getDataStructureTopics().get(position));
            startActivity(intent);
        });
    }
}