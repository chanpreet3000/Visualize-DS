package com.chanpreet.visualizeds.classes;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;

public class DataManager {
    private static volatile DataManager INSTANCE = null;
    private UserInfo userInfo = null;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DataManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataManager();
                }
            }
        }
        return INSTANCE;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo _user) {
        userInfo = _user;
    }

    public void updateUser(Context context, UserInfo _user) {
        //Updating local
        userInfo = _user;
        //Updating Cloud
        FirebaseFirestore
                .getInstance()
                .collection(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .document("USER_INFORMATION")
                .set(userInfo)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}