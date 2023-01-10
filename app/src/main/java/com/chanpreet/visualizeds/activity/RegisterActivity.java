package com.chanpreet.visualizeds.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chanpreet.visualizeds.R;
import com.chanpreet.visualizeds.classes.UserInfo;
import com.chanpreet.visualizeds.builder.LoaderBuilder;
import com.chanpreet.visualizeds.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", Pattern.CASE_INSENSITIVE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        binding.signUpBtn.setOnClickListener(v -> registerUser());
        binding.maleGenderBtn.setChecked(true);
        binding.lessThanBtn.setChecked(true);

        binding.termsText.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void registerUser() {
        String fullName = Objects.requireNonNull(binding.nameEditText.getText()).toString().trim().toLowerCase(Locale.ROOT);
        String email = Objects.requireNonNull(binding.emailEditText.getText()).toString().trim().toLowerCase(Locale.ROOT);
        String password = Objects.requireNonNull(binding.passwordEditText.getText()).toString();
        String confirmPassword = Objects.requireNonNull(binding.confirmPasswordEditText.getText()).toString();

        int genderID = binding.genderGroup.getCheckedRadioButtonId();
        String gender;
        if (genderID == R.id.male_gender_btn) {
            gender = "MALE";
        } else if (genderID == R.id.female_gender_btn) {
            gender = "FEMALE";
        } else {
            gender = "OTHER";
        }

        int ageID = binding.ageGroup.getCheckedRadioButtonId();
        String age;
        if (ageID == R.id.less_than_btn) {
            age = "<18";
        } else {
            age = ">=18";
        }

        if (fullName.isEmpty()) {
            binding.nameEditTextLayout.setError("Please enter a valid name.");
            return;
        } else {
            binding.nameEditTextLayout.setError("");
        }

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        if (email.isEmpty() || !matcher.find()) {
            binding.emailEditTextLayout.setError("Invalid Email ID");
            return;
        } else {
            binding.emailEditTextLayout.setError("");
        }

        matcher = VALID_PASSWORD_REGEX.matcher(password);
        if (password.isEmpty() || !matcher.find()) {
            binding.passwordEditTextLayout.setError("A Password must be of 8 characters including at least one lower case, upper case, number and a special character");
            return;
        } else {
            binding.passwordEditTextLayout.setError("");
        }

        if (!password.equals(confirmPassword)) {
            binding.confirmPasswordEditTextLayout.setError("Passwords do not match.");
            return;
        } else {
            binding.confirmPasswordEditTextLayout.setError("");
        }

        Dialog loader = LoaderBuilder.build(this, "Registering User");
        loader.show();

        String finalGender = gender;
        String finalAge = age;
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Dialog loader2 = LoaderBuilder.build(this, "Storing Information!");
                    loader2.show();
                    UserInfo userInfo = new UserInfo(fullName, email, finalGender, finalAge);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("USERS")
                            .document(Objects.requireNonNull(authResult.getUser()).getUid())
                            .set(userInfo)
                            .addOnCompleteListener(task -> {
                                Toast.makeText(RegisterActivity.this, "Email successfully registered!", Toast.LENGTH_SHORT).show();
                                loader2.hide();
                                finish();
                            });
                })
                .addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(RegisterActivity.this, "Email already registered!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error Occurred!\n" + e, Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnCompleteListener(v -> loader.hide());
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}