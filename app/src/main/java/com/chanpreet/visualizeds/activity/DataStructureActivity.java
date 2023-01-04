package com.chanpreet.visualizeds.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.UserInfo;
import com.chanpreet.visualizeds.adapter.DataStructureAdapter;
import com.chanpreet.visualizeds.classes.DataStructure;
import com.chanpreet.visualizeds.utils.DataStructureUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class DataStructureActivity extends AppCompatActivity {

    private ActivityDataStructureBinding binding;
    private List<DataStructure> dataStructures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataStructureBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseUser user = (FirebaseUser) getIntent().getParcelableExtra("data");
        FirebaseFirestore
                .getInstance()
                .collection("USERS")
                .document(user.getUid())
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    UserInfo userInfo = documentSnapshot.toObject(UserInfo.class);
                    assert userInfo != null;
                    String name_text = "Hey, " + userInfo.getFullName();
                    binding.nameTextView.setText(name_text);
                    binding.emailTextView.setText(userInfo.getEmail());
                }).addOnCompleteListener(task -> {
                });

        dataStructures = DataStructureUtil.dataStructures;
        initRecyclerView(dataStructures);
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
        }
        return super.onOptionsItemSelected(item);
    }
}