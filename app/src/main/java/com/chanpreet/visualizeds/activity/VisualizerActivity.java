package com.chanpreet.visualizeds.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.VisualizationInfo;
import com.chanpreet.visualizeds.databinding.ActivityVisualizerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class VisualizerActivity extends AppCompatActivity {

    public ActivityVisualizerBinding binding;
    public StepCardAdapter adapter;
    private DataStructureAlgorithm dataStructureAlgorithm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        //getting Information from Intent.
        dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        fillHeaderInformation();

        //
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

        //
        binding.visualizeButton.setOnClickListener(v -> this.visualizeBtnClicked());
        binding.leftStepBtn.setOnClickListener(v -> visualizePreviousStep());
        binding.rightStepBtn.setOnClickListener(v -> visualizeNextStep());
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.leftStepBtn.setEnabled(true);
                binding.rightStepBtn.setEnabled(true);
                if (position == 0) {
                    binding.leftStepBtn.setEnabled(false);
                }
                if (position == adapter.getItemCount() - 1) {
                    binding.rightStepBtn.setEnabled(false);
                }
            }
        });

        this.generateInputUI();
        this.onCreate();
    }


    private void fillHeaderInformation() {
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
            finish();
        });
    }

    private void visualizeBtnClicked() {
        try {
            this.visualize();
            this.postVisualize();
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void postVisualize() {
        //
        Map<String, Object> map = getVisualizationInformation();
        StringBuilder information = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            information.append(entry.getKey()).append("\t :\t").append(entry.getValue().toString()).append("\n");
        }
        VisualizationInfo visualizationInfo = new VisualizationInfo(System.currentTimeMillis(), dataStructureAlgorithm.getName(), information.toString());

        //
        this.visualize();
        //
        binding.viewPager.setCurrentItem(0);
        hideKeyboard();
    }

    public abstract void onCreate();

    public abstract void generateInputUI();

    public abstract Map<String, Object> getVisualizationInformation();

    public abstract void visualize();

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @NonNull
    public List<Integer> stringToArray(@NonNull String s) {
        StringBuilder c = new StringBuilder();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                try {
                    int number = Integer.parseInt(c.toString());
                    arr.add(number);
                    c = new StringBuilder();
                } catch (Exception ignored) {
                }

            } else {
                c.append(s.charAt(i));
            }
            if (i == s.length() - 1) {
                int number = Integer.parseInt(c.toString());
                arr.add(number);
                c = new StringBuilder();
            }
        }
        return arr;
    }
}