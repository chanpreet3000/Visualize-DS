package com.chanpreet.visualizeds.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chanpreet.visualizeds.builder.LoaderBuilder;
import com.chanpreet.visualizeds.classes.DataManager;
import com.chanpreet.visualizeds.classes.UserInfo;
import com.chanpreet.visualizeds.databinding.ActivityLoginBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    Dialog loader;

    @Override
    protected void onResume() {
        super.onResume();
        if (loader != null) loader.hide();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        firebaseAuth = FirebaseAuth.getInstance();

        binding.loginBtn.setOnClickListener(v -> loginUser());

        binding.signUpBtn.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));

        binding.forgotPasswordText.setOnClickListener(v -> forgotPassword());

        autoLogin();
    }

    private void autoLogin() {
        try {
            if (FirebaseAuth.getInstance().getCurrentUser() != null && FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                successLogin(FirebaseAuth.getInstance().getCurrentUser());
            } else {
                throw new Exception();
            }
        } catch (Exception ignored) {
        }
    }

    private void forgotPassword() {
        String email = Objects.requireNonNull(binding.emailEditText.getText()).toString().trim().toLowerCase(Locale.ROOT);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (email.isEmpty() || !matcher.find()) {
            binding.emailEditTextLayout.setError("Invalid Email ID");
            return;
        } else {
            binding.emailEditTextLayout.setError("");
        }
        loader = LoaderBuilder.build(this, "Sending.");
        loader.show();
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnSuccessListener(unused -> Toast.makeText(LoginActivity.this, "Reset link sent to " + email, Toast.LENGTH_LONG).show())
                .addOnFailureListener(e -> Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show())
                .addOnCompleteListener(task -> loader.hide());

    }

    private void loginUser() {
        String email = Objects.requireNonNull(binding.emailEditText.getText()).toString().trim().toLowerCase(Locale.ROOT);
        String password = Objects.requireNonNull(binding.passwordEditText.getText()).toString();

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (email.isEmpty() || !matcher.find()) {
            binding.emailEditTextLayout.setError("Invalid Email ID");
            return;
        } else {
            binding.emailEditTextLayout.setError("");
        }
        if (password.isEmpty()) {
            binding.passwordEditTextLayout.setError("Enter enter a valid Password.");
            return;
        }

        loader = LoaderBuilder.build(this, "Please wait.");
        loader.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> postLogin(Objects.requireNonNull(authResult.getUser())))
                .addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(this, "Account not registered.", Toast.LENGTH_SHORT).show();
                    } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(this, "Email or Password is incorrect.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }).addOnCompleteListener(task -> loader.hide());

    }

    private void postLogin(FirebaseUser user) {
        if (user.isEmailVerified()) {
            successLogin(user);
        } else {
            Snackbar snackbar = Snackbar.make(binding.getRoot(), "Email id not verified.", Snackbar.LENGTH_LONG)
                    .setAction("SEND EMAIL", v -> user.sendEmailVerification()
                            .addOnSuccessListener(unused -> Toast.makeText(LoginActivity.this, "Link sent successfully", Toast.LENGTH_SHORT).show())
                            .addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Failed!" + e, Toast.LENGTH_SHORT).show()));
            snackbar.show();
        }
    }

    private void successLogin(FirebaseUser user) {
        Dialog loader2 = LoaderBuilder.build(LoginActivity.this, "Fetching Data");
        loader2.show();
        FirebaseFirestore
                .getInstance()
                .collection(user.getUid())
                .document("USER_INFORMATION")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    UserInfo userInfo = documentSnapshot.toObject(UserInfo.class);
                    DataManager.getInstance().setUserInfo(userInfo);

                    //Updating UI to Main Activity
                    updateUI();
                })
                .addOnFailureListener(e -> Toast.makeText(LoginActivity.this, "Error collecting information " + e, Toast.LENGTH_SHORT).show())
                .addOnCompleteListener(task -> loader2.hide());
    }

    private void updateUI() {
        Intent intent = new Intent(this, DataStructureActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}