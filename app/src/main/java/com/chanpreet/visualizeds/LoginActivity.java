package com.chanpreet.visualizeds;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.Loader;

import com.chanpreet.visualizeds.activity.DataStructureActivity;
import com.chanpreet.visualizeds.builder.LoaderBuilder;
import com.chanpreet.visualizeds.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

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
        if(loader != null)loader.hide();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            updateUI(firebaseAuth.getCurrentUser());
        }


        binding.loginBtn.setOnClickListener(v -> loginUser());

        binding.signUpBtn.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));

        binding.forgotPasswordText.setOnClickListener(v -> forgotPassword());
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
                .addOnSuccessListener(authResult -> updateUI(authResult.getUser()))
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

    private void updateUI(FirebaseUser user) {
        Intent intent = new Intent(this, DataStructureActivity.class);
        intent.putExtra("data", user);
        startActivity(intent);
        finishAffinity();
    }
}