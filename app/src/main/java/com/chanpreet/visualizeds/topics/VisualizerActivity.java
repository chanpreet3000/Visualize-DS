package com.chanpreet.visualizeds.topics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ActivityVisualizerBinding;

public abstract class VisualizerActivity extends AppCompatActivity {

    private ActivityVisualizerBinding binding;
    private StepCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fillHeaderInformation();

        binding.visualizeButton.setOnClickListener(v -> visualizeButtonClicked());
    }

    private void fillHeaderInformation() {
        //getting Information from Intent.
        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");

        //Filling the header Information.
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());
        binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());

        //Setting title
        setTitle(dataStructureAlgorithm.getName() + " Visualizer");
    }

    public abstract void generateUI();

    public abstract void visualizeButtonClicked();
}