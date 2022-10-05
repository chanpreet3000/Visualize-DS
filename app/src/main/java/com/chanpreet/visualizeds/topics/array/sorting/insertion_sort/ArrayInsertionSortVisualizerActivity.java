package com.chanpreet.visualizeds.topics.array.sorting.insertion_sort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.chanpreet.visualizeds.StepCard;
import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.databinding.ActivityVisualizerBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.topics.VisualizerActivity;
import com.chanpreet.visualizeds.topics.array.ArrayBuilder;
import com.chanpreet.visualizeds.utils.DataStructureUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ArrayInsertionSortVisualizerActivity extends VisualizerActivity {
    private EditText arrayEditText;
    @Override
    public void visualizeButtonClicked() {
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);

        //getting array and target
        List<Integer> arr = DataStructureUtil.stringToArray(arrayEditText.getText().toString().trim());

        List<StepCard> stepCardList = new ArrayList<>();
        //Insertion Sort
        int steps = 0;
        for (int i = 1; i < arr.size(); i++) {
            int temp = arr.get(i);
            int j = i - 1;
            while (j >= 0 && temp <= arr.get(j)) {
                HashMap<Integer, Integer> map = new HashMap<>();
                //Building step card
                StepCard stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                stepCard.setDescription(String.format(Locale.US, "%d is greater than %d.\nTherefore, moving array element right.", arr.get(j), temp));
                //
                map.put(j, ArrayBuilder.COLOR_TARGET_MATCHED);

                //Generating Data for Step Card
                stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
                stepCardList.add(stepCard);

                //Insertion Sort condition
                arr.set(j + 1, arr.get(j));
                arr.set(j, temp);
                j = j - 1;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(String.format(Locale.US, "Array is now sorted!"));
        stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
        stepCardList.add(stepCard);
        //Adapter
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
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