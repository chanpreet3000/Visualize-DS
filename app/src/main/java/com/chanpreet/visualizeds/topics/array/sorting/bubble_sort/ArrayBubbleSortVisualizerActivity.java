package com.chanpreet.visualizeds.topics.array.sorting.bubble_sort;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.chanpreet.visualizeds.StepCard;
import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.databinding.ActivityVisualizerBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.topics.array.ArrayBuilder;
import com.chanpreet.visualizeds.utils.DataStructureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ArrayBubbleSortVisualizerActivity extends AppCompatActivity {

    private ActivityVisualizerBinding binding;
    private EditText arrayEditText;
    private StepCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //filling header information
        DataStructureUtil.fillHeaderInformation(this, binding);

        //Generating a Input UI
        generateInputUI();

        //button click listener
        binding.visualizeButton.setOnClickListener(v -> visualizeButtonClicked());
        binding.leftStepBtn.setOnClickListener(v -> {
            int curr = binding.viewPager.getCurrentItem();
            int next = Math.max(0, curr - 1);
            binding.viewPager.setCurrentItem(next);
        });
        binding.rightStepBtn.setOnClickListener(v -> {
            int curr = binding.viewPager.getCurrentItem();
            int n = Objects.requireNonNull(binding.viewPager.getAdapter()).getItemCount();
            int next = Math.min(n - 1, curr + 1);
            binding.viewPager.setCurrentItem(next);
        });
    }

    private void visualizeButtonClicked() {
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);

        //getting array and target
        List<Integer> arr = DataStructureUtil.stringToArray(arrayEditText.getText().toString().trim());

        List<StepCard> stepCardList = new ArrayList<>();
        //Bubble Sort
        int steps = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                //Building step card
                StepCard stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                HashMap<Integer, Integer> map = new HashMap<>();
                if (arr.get(j) > arr.get(j + 1)) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    map.put(j, ArrayBuilder.COLOR_TARGET_MATCHED);
                    map.put(j + 1, ArrayBuilder.COLOR_TARGET_MATCHED);
                    stepCard.setDescription(String.format(Locale.US, "%d is greater than %d.\nTherefore swapping %d & %d.", arr.get(j), arr.get(j + 1), arr.get(j), arr.get(j + 1)));
                } else {
                    map.put(j, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                    map.put(j + 1, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                    stepCard.setDescription(String.format(Locale.US, "%d is not greater than %d.\nTherefore no swapping necessary.", arr.get(j), arr.get(j + 1)));
                }
                stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
                stepCardList.add(stepCard);
            }
        }
        adapter.setStepCardList(stepCardList);
    }

    private void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array (Sorted Array)");
        binding1.editText.setHint("Enter numbers here (with spaces)");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());

        //caching UI
        arrayEditText = binding1.editText;

        //
        adapter = new StepCardAdapter(getApplicationContext());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(4);
    }
}