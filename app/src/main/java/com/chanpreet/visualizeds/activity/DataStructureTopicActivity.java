package com.chanpreet.visualizeds.activity;

import com.chanpreet.visualizeds.adapter.DataStructureTopicAdapter;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.databinding.ActivityDataStructureTopicBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.Objects;

public class DataStructureTopicActivity extends AppCompatActivity {

    private ActivityDataStructureTopicBinding binding;
    private DataStructure dataStructure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureTopicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataStructure = (DataStructure) getIntent().getSerializableExtra("data");

        binding.headingTextView.setText(String.format("%s Topics", dataStructure.getName()));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initRecyclerView(dataStructure.getDataStructureTopics());
    }

    private void initRecyclerView(List<DataStructureTopic> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataStructureTopicAdapter adapter = new DataStructureTopicAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureAlgorithmActivity.class);
            intent.putExtra("data", dataStructure.getDataStructureTopics().get(position));
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}