package com.chanpreet.visualizeds.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.databinding.ActivityAboutUsBinding;

import java.util.Objects;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutUsBinding binding = ActivityAboutUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Us");

        binding.titleTextView.setText(TextBuilder.makeBulletList("Our Android application provides a unique and engaging platform for students and professionals alike to understand and visualize data structures algorithms.",
                "Data structures are a critical part of computer science and software development, and we understand that traditional methods of learning can be challenging and boring. That's why we created an application that makes learning about data structures fun, interactive, and easy to understand.",
                "With our app, users can learn about various data structures such as linked lists, trees, and graphs and see how algorithms work in real-time through interactive visualization. The step-by-step explanations and intuitive user interface make it simple to follow along and deepen their understanding of the material.",
                "We believe that everyone should have access to quality education and strive to make learning data structures accessible to all. Our goal is to help users achieve a deeper understanding of this critical topic and to help them retain this knowledge for years to come."));
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return super.onSupportNavigateUp();
    }
}