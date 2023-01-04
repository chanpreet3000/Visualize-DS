package com.chanpreet.visualizeds.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithmContent;
import com.chanpreet.visualizeds.databinding.ActivityDataStructureTheoryBinding;

import java.util.Objects;

public class DataStructureTheoryActivity extends AppCompatActivity {

    private ActivityDataStructureTheoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureTheoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        if (dataStructureAlgorithm.getIcon() != null)
            binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());


        DataStructureAlgorithmContent dataStructureAlgorithmContent = dataStructureAlgorithm.getDataStructureAlgorithmContent();
        binding.theoryTextView.setText(dataStructureAlgorithmContent.getTheory());
        binding.algorithmTextView.setText(dataStructureAlgorithmContent.getAlgorithm());
        binding.codeTextView.setText(dataStructureAlgorithmContent.getCode());
        binding.worstCaseComplexityTextView.setText(formatTimeComplexity(dataStructureAlgorithmContent.getWorstCase()));
        binding.averageCaseComplexityTextView.setText(formatTimeComplexity(dataStructureAlgorithmContent.getAverageCase()));
        binding.bestCaseComplexityTextView.setText(formatTimeComplexity(dataStructureAlgorithmContent.getBestCase()));

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    Spanned formatTimeComplexity(String s) {
        return Html.fromHtml(String.format("O(%s)", s));
    }
}