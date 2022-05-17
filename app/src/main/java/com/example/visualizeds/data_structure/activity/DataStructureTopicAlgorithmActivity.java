package com.example.visualizeds.data_structure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.visualizeds.databinding.ActivityDataStructureTopicAlgorithmBinding;
import com.example.visualizeds.data_structure.adapter.DataStructureTopicAlgorithmAdapter;
import com.example.visualizeds.data_structure.classes.DataStructureTopic;
import com.example.visualizeds.data_structure.classes.DataStructureTopicAlgorithm;

import java.util.List;

public class DataStructureTopicAlgorithmActivity extends AppCompatActivity {

    private ActivityDataStructureTopicAlgorithmBinding binding;
    private DataStructureTopicAlgorithmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureTopicAlgorithmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataStructureTopic dataStructureTopic = (DataStructureTopic) getIntent().getSerializableExtra("data");

        initRecyclerView(dataStructureTopic.getDataStructureTopicAlgorithms());
        adapter.setTheoryOnClickListener(position -> {
        });

        // Theory Btn Click Listener
        adapter.setTheoryOnClickListener(position -> {
            Class mClass = dataStructureTopic.getDataStructureTopicAlgorithms().get(position).getTheoryClass();
            if (mClass == null) {
                Toast.makeText(getApplicationContext(), "No information Available!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), mClass);
            startActivity(intent);
        });

        // Visualize Btn Click Listener
        adapter.setVisualizeListenerOnClickListener(position -> {
            Class mClass = dataStructureTopic.getDataStructureTopicAlgorithms().get(position).getVisualizeClass();
            if (mClass == null) {
                Toast.makeText(getApplicationContext(), "No information Available!", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), mClass);
            startActivity(intent);
        });
    }

    private void initRecyclerView(List<DataStructureTopicAlgorithm> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DataStructureTopicAlgorithmAdapter(list);
        binding.recyclerView.setAdapter(adapter);
    }
}