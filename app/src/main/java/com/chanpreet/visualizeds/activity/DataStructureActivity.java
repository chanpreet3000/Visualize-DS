package com.chanpreet.visualizeds.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.adapter.DataStructureAdapter;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.databinding.ActivityDataStructureBinding;
import com.chanpreet.visualizeds.utils.Util;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DataStructureActivity extends AppCompatActivity {

    private ActivityDataStructureBinding binding;
    private List<DataStructure> dataStructures;

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView(dataStructures);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataStructures = Util.dataStructures;

        binding.myProfileCardView.setOnClickListener(v -> startActivity(new Intent(DataStructureActivity.this, MyProfileActivity.class)));
    }

    private void initRecyclerView(List<DataStructure> list) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataStructureAdapter adapter = new DataStructureAdapter(getApplicationContext(), list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(position -> {
            Intent intent = new Intent(getApplicationContext(), DataStructureTopicActivity.class);
            intent.putExtra("data", dataStructures.get(position));
            startActivity(intent);
        });
        binding.recyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_fall_down));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.sign_out_btn) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finishAffinity();
        } else if (item.getItemId() == R.id.about_us_menu) {
            startActivity(new Intent(this, AboutUsActivity.class));
        } else if (item.getItemId() == R.id.rate_us_menu) {
            goToPlayStore();
        } else if (item.getItemId() == R.id.previous_visualization_menu) {
            startActivity(new Intent(DataStructureActivity.this, PreviousVisualizationsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    private void goToPlayStore() {
        try {
            Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
            appStoreIntent.setPackage("com.android.vending");
            startActivity(appStoreIntent);
        } catch (android.content.ActivityNotFoundException exception) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }
}