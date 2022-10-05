package com.chanpreet.visualizeds.topics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.chanpreet.visualizeds.StepCard;
import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ActivityVisualizerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class VisualizerActivity extends AppCompatActivity {

    public ActivityVisualizerBinding binding;
    public StepCardAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fillHeaderInformation();
        generateInputUI();

        binding.visualizeButton.setOnClickListener(v -> visualizeButtonClicked());
        binding.leftStepBtn.setOnClickListener(v -> visualizePreviousStep());
        binding.rightStepBtn.setOnClickListener(v -> visualizeNextStep());

        adapter = new StepCardAdapter(getApplicationContext());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(4);

        binding.holderLinearLayout.setVisibility(View.VISIBLE);
        StepCard initialStepCard = new StepCard();
        initialStepCard.setTitle("No Data Available!");
        initialStepCard.setDescription("Please Enter some data!");
        List<StepCard> list = new ArrayList<>();
        list.add(initialStepCard);
        adapter.setStepCardList(list);

        this.onCreate();
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

    public abstract void generateInputUI();

    public abstract void visualizeButtonClicked();

    public void visualizeNextStep() {
        int curr = binding.viewPager.getCurrentItem();
        int n = Objects.requireNonNull(binding.viewPager.getAdapter()).getItemCount();
        int next = Math.min(n - 1, curr + 1);
        binding.viewPager.setCurrentItem(next);
    }

    public void visualizePreviousStep() {
        int curr = binding.viewPager.getCurrentItem();
        int next = Math.max(0, curr - 1);
        binding.viewPager.setCurrentItem(next);
    }

    public void onCreate() {
    }
}