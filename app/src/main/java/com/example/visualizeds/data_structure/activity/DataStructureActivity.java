package com.example.visualizeds.data_structure.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.visualizeds.data_structure.adapter.DataStructureAdapter;
import com.example.visualizeds.data_structure.classes.DataStructure;
import com.example.visualizeds.data_structure.utils.DataStructureUtil;
import com.example.visualizeds.databinding.ActivityDataStructureBinding;

import java.util.List;

public class DataStructureActivity extends AppCompatActivity {

    private ActivityDataStructureBinding binding;
    private DataStructureAdapter adapter;
    private List<DataStructure> dataStructures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataStructures = DataStructureUtil.dataStructures;
        initRecyclerView(dataStructures);
    }

    private void initRecyclerView(List<DataStructure> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataStructureAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureTopicActivity.class);
            intent.putExtra("data", dataStructures.get(position));
            startActivity(intent);
        });
    }
}