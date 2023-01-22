package com.chanpreet.visualizeds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.adapter.TheoryCardAdapter;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ActivityDataStructureTheoryBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DataStructureTheoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataStructureTheoryBinding binding = ActivityDataStructureTheoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        if (dataStructureAlgorithm.getIcon() != null)
            binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());

        getSupportActionBar().setTitle(dataStructureAlgorithm.getName() + " Theory");

        initViewPager(binding, dataStructureAlgorithm);

        binding.textView1.setText(String.format("Want to Visualize %s?", dataStructureAlgorithm.getName()));
        binding.textView2.setOnClickListener(v -> {
            if (dataStructureAlgorithm.getVisualizeClass() == null) {
                Toast.makeText(this, getString(R.string.missing_visualize_class), Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), dataStructureAlgorithm.getVisualizeClass());
            intent.putExtra("data", dataStructureAlgorithm);
            startActivity(intent);
            finish();
        });
    }

    private void initViewPager(ActivityDataStructureTheoryBinding binding, DataStructureAlgorithm dataStructureAlgorithm) {
        AlgorithmTheory algorithmTheory = dataStructureAlgorithm.getAlgorithmTheory();

        String timeComplexity = "Worst Case\t\t\t\t\t:  " + formatTimeComplexity(algorithmTheory.getWorstCase()).toString() + "\n\n" +
                "Average Case\t\t\t:  " + formatTimeComplexity(algorithmTheory.getAverageCase()).toString() + "\n\n" +
                "Best Case\t\t\t\t\t\t\t:  " + formatTimeComplexity(algorithmTheory.getAverageCase()).toString();
        List<String> titleList = new ArrayList<>(Arrays.asList("Theory", "Algorithm", "Code", "Time Complexity"));
        List<String> descriptionList = new ArrayList<>(Arrays.asList(algorithmTheory.getTheory(), algorithmTheory.getAlgorithm(), algorithmTheory.getCode(), timeComplexity));
        List<Integer> imageList = new ArrayList<>(
                Arrays.asList(
                        R.drawable.prop1,
                        R.drawable.prop1,
                        R.drawable.prop1,
                        R.drawable.prop1));

        TheoryCardAdapter adapter = new TheoryCardAdapter(this, titleList, descriptionList, imageList);
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                adapter.notifyDataSetChanged();
            }
        });

        binding.viewPager2.setAdapter(adapter);
        binding.circleIndicator.setViewPager(binding.viewPager2);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Spanned formatTimeComplexity(String s) {
        return Html.fromHtml(String.format("O(%s)", s));
    }
}