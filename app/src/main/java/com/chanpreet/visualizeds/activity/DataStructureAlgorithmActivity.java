package com.chanpreet.visualizeds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.databinding.ActivityDataStructureAlgorithmBinding;
import com.chanpreet.visualizeds.adapter.DataStructureAlgorithmAdapter;
import com.chanpreet.visualizeds.classes.DataStructureTopic;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.utils.Util;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import java.util.List;
import java.util.Objects;

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
        Objects.requireNonNull(getSupportActionBar()).setTitle("Algorithms");
        binding.headingTextView.setText(String.format("%s Algorithms", dataStructureTopic.getName()));

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        UnityAds.initialize(getApplicationContext(), Util.UNITY_GAME_ID, Util.TEST_MODE, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {
                binding.bannerLayout.removeAllViews();
                BannerView bannerView = new BannerView(DataStructureAlgorithmActivity.this, Util.DS_ALGORITHM_BANNER, new UnityBannerSize(320, 50));
                bannerView.load();
                binding.bannerLayout.addView(bannerView);
            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {
            }
        });
    }

    private void initRecyclerView(List<DataStructureAlgorithm> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataStructureAlgorithmAdapter adapter = new DataStructureAlgorithmAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
        // Theory Btn Click Listener
        adapter.setTheoryOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureTheoryActivity.class);
            intent.putExtra("data", dataStructureTopic.dataStructureAlgorithms().get(position));
            startActivity(intent);
        });

        // Visualize Btn Click Listener
        adapter.setVisualizeListenerOnClickListener(position -> {
            Class<?> mClass = dataStructureTopic.dataStructureAlgorithms().get(position).getVisualizeClass();
            if (mClass == null) {
                Toast.makeText(getApplicationContext(), getString(R.string.missing_visualize_class), Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), mClass);
            intent.putExtra("data", dataStructureTopic.dataStructureAlgorithms().get(position));
            startActivity(intent);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}