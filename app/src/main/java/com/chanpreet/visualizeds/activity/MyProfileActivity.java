package com.chanpreet.visualizeds.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.builder.LoaderBuilder;
import com.chanpreet.visualizeds.classes.DataManager;
import com.chanpreet.visualizeds.classes.UserInfo;
import com.chanpreet.visualizeds.classes.VisualizationInfo;
import com.chanpreet.visualizeds.databinding.ActivityMyProfileBinding;
import com.chanpreet.visualizeds.databinding.ItemDeleteAccountBinding;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyProfileActivity extends AppCompatActivity {

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.chanpreet.visualizeds.databinding.ActivityMyProfileBinding binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        UserInfo userInfo = DataManager.getInstance().getUserInfo();

        binding.layout11.textView.setText("NAME");
        binding.layout11.cardView.setCardBackgroundColor(ColorStateList.valueOf(getColor(R.color.dark_red)));
        binding.layout12.textView.setText(userInfo.getFullName());

        binding.layout21.textView.setText("EMAIL");
        binding.layout21.cardView.setCardBackgroundColor(ColorStateList.valueOf(getColor(R.color.dark_red)));
        binding.layout22.textView.setText(userInfo.getEmail());

        binding.layout31.textView.setText("GENDER");
        binding.layout31.cardView.setCardBackgroundColor(ColorStateList.valueOf(getColor(R.color.dark_red)));
        binding.layout32.textView.setText(userInfo.getGender());

        binding.layout41.textView.setText("AGE");
        binding.layout41.cardView.setCardBackgroundColor(ColorStateList.valueOf(getColor(R.color.dark_red)));
        binding.layout42.textView.setText(userInfo.getAge());

        binding.layout51.textView.setText("VISUALIZATIONS");
        binding.layout51.cardView.setCardBackgroundColor(ColorStateList.valueOf(getColor(R.color.dark_red)));
        binding.layout52.textView.setText(String.valueOf(userInfo.getVisualizationInfoList().size()));


        Map<String, Integer> map = new HashMap<>();
        for (VisualizationInfo info :
                userInfo.getVisualizationInfoList()) {
            if (map.containsKey(info.getName())) {
                map.put(info.getName(), map.get(info.getName()) + 1);
            } else {
                map.put(info.getName(), 1);
            }
        }
        Integer _max = 0;
        String ans = "NO DATA";

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > _max) {
                _max = entry.getValue();
                ans = entry.getKey();
            }
        }
        binding.layout61.textView.setText("MOST VISUALIZED");
        binding.layout61.cardView.setCardBackgroundColor(ColorStateList.valueOf(getColor(R.color.dark_red)));
        binding.layout62.textView.setText(ans);


        binding.deleteProfileBtn.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MyProfileActivity.this);

            ItemDeleteAccountBinding binding1 = ItemDeleteAccountBinding.inflate(getLayoutInflater());
            dialog.setContentView(binding1.getRoot());
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();


            binding1.deleteBtn.setOnClickListener(v2 -> {
                String password = binding1.passwordEditText.getText().toString();
                Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
                if (password.isEmpty() || !matcher.find()) {
                    binding1.passwordEditTextLayout.setError("A Password must be of 8 characters including at least one lower case, upper case, number and a special character(@, #, $, %, ^, &, +, =, !)");
                    return;
                } else {
                    binding1.passwordEditTextLayout.setError("");
                }
                AuthCredential credential = EmailAuthProvider
                        .getCredential(FirebaseAuth.getInstance().getCurrentUser().getEmail(), password);

                Dialog loader = LoaderBuilder.build(MyProfileActivity.this, "Authorising...");
                loader.show();
                FirebaseAuth.getInstance().getCurrentUser()
                        .reauthenticate(credential)
                        .addOnSuccessListener(unused -> new AlertDialog.Builder(MyProfileActivity.this)
                                .setTitle("Delete Profile?")
                                .setMessage("Deleting Profile cannot be reverted!")
                                .setNegativeButton("No", (dialog1, which) -> dialog1.dismiss())
                                .setPositiveButton("Yes", (dialog1, which) -> {
                                    Dialog loader1 = LoaderBuilder.build(MyProfileActivity.this, "Deleting Account..");
                                    loader1.show();
                                    //
                                    FirebaseAuth.getInstance().getCurrentUser()
                                            .delete()
                                            .addOnSuccessListener(unused2 -> {
                                                Toast.makeText(MyProfileActivity.this, "User Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(MyProfileActivity.this, LoginActivity.class));
                                                finishAffinity();
                                            })
                                            .addOnFailureListener(e -> Toast.makeText(MyProfileActivity.this, "Task Failed " + e, Toast.LENGTH_SHORT).show())
                                            .addOnCompleteListener(task -> loader1.hide());
                                })
                                .show()).addOnFailureListener(e -> Toast.makeText(MyProfileActivity.this, "Re-Authentication failed " + e, Toast.LENGTH_SHORT).show()).addOnCompleteListener(task -> loader.hide());

            });


        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}