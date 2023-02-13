package com.chanpreet.visualizeds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.adapter.DataStructureTopicAdapter;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.databinding.ActivityDataStructureTopicBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import java.util.List;
import java.util.Objects;

public class DataStructureTopicActivity extends AppCompatActivity {

    private ActivityDataStructureTopicBinding binding;
    private DataStructure dataStructure;

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView(dataStructure.getDataStructureTopics());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureTopicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataStructure = (DataStructure) getIntent().getSerializableExtra("data");

        binding.headingTextView.setText(String.format("%s Topics", dataStructure.getName()));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Topics");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, initializationStatus -> {
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
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
        binding.recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_fall_down));
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}
