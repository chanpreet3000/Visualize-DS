package com.chanpreet.visualizeds.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.chanpreet.visualizeds.classes.StepCard;
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

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.visualizeButton.setOnClickListener(v -> {
            hideKeyboard();
            visualizeButtonClicked();
        });
        binding.leftStepBtn.setOnClickListener(v -> visualizePreviousStep());
        binding.rightStepBtn.setOnClickListener(v -> visualizeNextStep());

        adapter = new StepCardAdapter(getApplicationContext());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(4);

        binding.holderLinearLayout.setVisibility(View.VISIBLE);
        StepCard initialStepCard = new StepCard();
        initialStepCard.setTitle("No data Available!");
        initialStepCard.setDescription("Please enter some data!");
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

        binding.backgroundCardView2.setImageResource(dataStructureAlgorithm.getIcon());

        binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());

//        Setting title
        setTitle(dataStructureAlgorithm.getName() + " Visualizer");
        binding.textView1.setText(String.format("Want to learn more about %s?", dataStructureAlgorithm.getName()));
        binding.textView2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureTheoryActivity.class);
            intent.putExtra("data", dataStructureAlgorithm);
            startActivity(intent);
        });
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

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}