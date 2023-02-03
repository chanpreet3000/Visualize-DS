package com.chanpreet.visualizeds.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.chanpreet.visualizeds.databinding.ActivitySplashScreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SplashScreen extends AppCompatActivity {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private static final String VERSION_CODE_KEY = "version_code";
    private AlertDialog updateDialog;

    @Override
    protected void onResume() {
        super.onResume();
        initRemoteConfig();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        com.chanpreet.visualizeds.databinding.ActivitySplashScreenBinding binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        binding.versionCodeTextView.setText(String.format(Locale.US, "Version v%d", getCurrentVersionCode()));
    }

    private void initRemoteConfig() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        HashMap<String, Object> firebaseDefaultMap = new HashMap<>();
        firebaseDefaultMap.put(VERSION_CODE_KEY, getCurrentVersionCode());
        mFirebaseRemoteConfig.setDefaultsAsync(firebaseDefaultMap);
        mFirebaseRemoteConfig.setConfigSettingsAsync(
                new FirebaseRemoteConfigSettings.Builder()
                        .setMinimumFetchIntervalInSeconds(TimeUnit.HOURS.toSeconds(0))
                        .build());
        mFirebaseRemoteConfig.fetch().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mFirebaseRemoteConfig.activate().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        final int latestAppVersion = (int) mFirebaseRemoteConfig.getDouble(VERSION_CODE_KEY);
                        if (checkForUpdate(latestAppVersion)) {
                            try {
                                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                                    Intent intent = new Intent(this, DataStructureActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                Intent intent = new Intent(this, LoginActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            }
                        }
                    }
                });
            } else {
                Toast.makeText(this, "An error occurred. Please check your connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkForUpdate(int latestAppVersion) {
        if (latestAppVersion > getCurrentVersionCode()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Update");
            builder.setMessage("New Version Available.\n" + "Please Update App to v" + latestAppVersion);
            builder.setPositiveButton("Update", (dialog, which) -> {
                goToPlayStore();
                updateDialog.dismiss();
            });
            builder.setCancelable(false);
            builder.setNegativeButton("Cancel", (dialog, which) -> finish());
            updateDialog = builder.create();
            updateDialog.show();
            return false;
        }
        return true;
    }

    private int getCurrentVersionCode() {
        int versionCode = 1;
        try {
            final PackageInfo pInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                versionCode = (int) pInfo.getLongVersionCode();
            } else {
                versionCode = pInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
//log exception
        }
        return versionCode;
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