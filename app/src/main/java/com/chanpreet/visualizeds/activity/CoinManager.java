package com.chanpreet.visualizeds.activity;

import android.app.Dialog;
import android.content.Context;

import com.chanpreet.visualizeds.builder.LoaderBuilder;
import com.chanpreet.visualizeds.classes.UserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CoinManager {


    public static void deductDataCoins(Context context, int amount, CoinManagerInterface coinManagerInterface) {
        Dialog loader2 = LoaderBuilder.build(context, "Please wait");
        loader2.show();
        FirebaseFirestore
                .getInstance()
                .collection("USERS")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
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
                    FirebaseFirestore.getInstance()
                            .collection("USERS")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .set(userInfo)
                            .addOnCompleteListener(task -> {
                                coinManagerInterface.OnSuccessListener(userInfo.getDataCoins());
                                loader2.hide();
                            });
                }).addOnFailureListener(e -> coinManagerInterface.OnFailureListener(e.toString()))
                .addOnSuccessListener(documentSnapshot -> loader2.hide());
    }

    public interface CoinManagerInterface {
        void OnSuccessListener(long newDataCoins);

        void OnFailureListener(String s);
    }
}
