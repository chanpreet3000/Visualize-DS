package com.chanpreet.visualizeds.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chanpreet.visualizeds.adapter.PreviousVisualizationsAdapter;
import com.chanpreet.visualizeds.classes.DataManager;
import com.chanpreet.visualizeds.classes.VisualizationInfo;
import com.chanpreet.visualizeds.databinding.ActivityPreviousVisualizationsBinding;

import java.util.List;
import java.util.Objects;

public class PreviousVisualizationsActivity extends AppCompatActivity {

    private ActivityPreviousVisualizationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreviousVisualizationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        List<VisualizationInfo> visualizationInfoList = DataManager.getInstance().getUserInfo().getVisualizationInfoList();

        initRecyclerView(visualizationInfoList);
    }

    private void initRecyclerView(List<VisualizationInfo> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
        PreviousVisualizationsAdapter adapter = new PreviousVisualizationsAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}