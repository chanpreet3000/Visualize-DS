package com.example.visualizeds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.visualizeds.R;
import com.example.visualizeds.databinding.ActivityDataStructureAlgorithmBinding;
import com.example.visualizeds.adapter.DataStructureAlgorithmAdapter;
import com.example.visualizeds.classes.DataStructureTopic;
import com.example.visualizeds.classes.DataStructureAlgorithm;

import java.util.List;

public class DataStructureAlgorithmActivity extends AppCompatActivity {

    private ActivityDataStructureAlgorithmBinding binding;
    private DataStructureTopic dataStructureTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureAlgorithmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataStructureTopic = (DataStructureTopic) getIntent().getSerializableExtra("data");

        initRecyclerView(dataStructureTopic.dataStructureAlgorithms());

        binding.headingTextView.setText(String.format("%s Algorithms", dataStructureTopic.getName()));
    }

    private void initRecyclerView(List<DataStructureAlgorithm> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataStructureAlgorithmAdapter adapter = new DataStructureAlgorithmAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
        // Theory Btn Click Listener
        adapter.setTheoryOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureAlgorithmTheoryActivity.class);
            intent.putExtra("data", dataStructureTopic.dataStructureAlgorithms().get(position));
            startActivity(intent);
        });

        // Visualize Btn Click Listener
        adapter.setVisualizeListenerOnClickListener(position -> {
            Class mClass = dataStructureTopic.dataStructureAlgorithms().get(position).getVisualizeClass();
            if (mClass == null) {
                Toast.makeText(getApplicationContext(), getString(R.string.missing_visualize_class), Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), mClass);
            intent.putExtra("data", dataStructureTopic.dataStructureAlgorithms().get(position));
            startActivity(intent);
        });

    }
}