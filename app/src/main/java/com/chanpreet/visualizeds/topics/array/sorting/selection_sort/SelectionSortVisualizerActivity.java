package com.chanpreet.visualizeds.topics.array.sorting.selection_sort;

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

public class SelectionSortVisualizerActivity extends AppCompatActivity {

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
        //Selection Sort
        int steps = 0;
        for (int i = 0; i < arr.size(); i++) {
            int pos = i;
            for (int j = i; j < arr.size(); j++) {
                HashMap<Integer, Integer> map = new HashMap<>();
                StepCard stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                if (arr.get(j) < arr.get(pos)) {
                    stepCard.setDescription(String.format(Locale.US, "%d is smaller than %d.\nNow, smallest value is %d.", arr.get(j), arr.get(pos), arr.get(j)));
                } else {
                    stepCard.setDescription(String.format(Locale.US, "%d is not smaller than %d.\nTherefore, smallest value is still %d.", arr.get(j), arr.get(pos), arr.get(j)));
                }
                //Selection Sort Condition
                if (arr.get(j) < arr.get(pos)) {
                    map.put(i, ArrayBuilder.COLOR_TARGET_MATCHED);
                    map.put(j, ArrayBuilder.COLOR_TARGET_MATCHED);
                    pos = j;
                } else {
                    map.put(i, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                    map.put(j, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                }
                stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
                stepCardList.add(stepCard);
            }
            int temp = arr.get(i);
            arr.set(i, arr.get(pos));
            arr.set(pos, temp);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription("Array is now sorted!");
        stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
        stepCardList.add(stepCard);
        //Adapter
        adapter.setStepCardList(stepCardList);
    }


    private void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array.");
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