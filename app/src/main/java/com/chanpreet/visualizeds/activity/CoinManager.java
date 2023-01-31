package com.chanpreet.visualizeds.activity;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.chanpreet.visualizeds.builder.LoaderBuilder;
import com.chanpreet.visualizeds.classes.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class CoinManager {
    public static void deductDataCoins(Context context, int amount, CoinManagerInterface coinManagerInterface) {
        Dialog loader2 = LoaderBuilder.build(context, "Please wait");
        loader2.show();
        FirebaseFirestore
                .getInstance()
                .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .document("USER_INFORMATION")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    UserInfo userInfo = documentSnapshot.toObject(UserInfo.class);
                    assert userInfo != null;
                    if (userInfo.getDataCoins() < amount) {
                        coinManagerInterface.OnFailureListener("Low on Data Coins.");
                        return;
                    }
                    userInfo.setDataCoins(userInfo.getDataCoins() - amount);

                    loader2.show();
                    FirebaseFirestore
                            .getInstance()
                            .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .document("USER_INFORMATION")
                            .set(userInfo)
                            .addOnCompleteListener(task -> {
                                coinManagerInterface.OnSuccessListener(userInfo.getDataCoins());
                                loader2.hide();
                            });
                }).addOnFailureListener(e -> coinManagerInterface.OnFailureListener(e.toString()))
                .addOnSuccessListener(documentSnapshot -> loader2.hide());
    }

    public static void creditDailyDataCoins(Context context, CoinManagerInterface coinManagerInterface) {
        Dialog loader = LoaderBuilder.build(context, "Collecting Information");
        loader.show();

        FirebaseFirestore
                .getInstance()
                .collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .document("USER_INFORMATION")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    UserInfo userInfo = documentSnapshot.toObject(UserInfo.class);
                    assert userInfo != null;
                    long prevTime = userInfo.getLastDataCoinTime();
                    long diffTime = System.currentTimeMillis() - prevTime;

                    diffTime = 1000 * 60 * 60 * 24 - diffTime;

                    if (diffTime <= 0) {
                        userInfo.setDataCoins(userInfo.getDataCoins() + 5);
                        userInfo.setLastDataCoinTime(System.currentTimeMillis());

                        Dialog loader2 = LoaderBuilder.build(context, "Crediting Coins...");
                        FirebaseFirestore.getInstance().
                                collection(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .document("USER_INFORMATION")
                                .set(userInfo)
                                .addOnCompleteListener(task -> {
                                    loader2.hide();
                                    Toast.makeText(context, "Received 5 coins", Toast.LENGTH_SHORT).show();
                                    coinManagerInterface.OnSuccessListener(userInfo.getDataCoins());
                                });
                    } else {
                        long seconds = (long) (diffTime / 1000) % 60;
                        long minutes = (long) ((diffTime / (1000 * 60)) % 60);
                        long hours = (long) ((diffTime / (1000 * 60 * 60)) % 24);
                        Toast.makeText(context,
                                String.format(Locale.US, "Come back after %02dh %02dm %02ds.", hours, minutes, seconds), Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> coinManagerInterface.OnFailureListener(e.toString()))
                .addOnCompleteListener(task -> loader.hide());

    }

    public interface CoinManagerInterface {
        void OnSuccessListener(long newDataCoins);

        void OnFailureListener(String s);
    }
}
