package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.insertion_sort;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ArrayInsertionSortActivity extends VisualizerActivity {
    private EditText arrayEditText;

    @Override
    public void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array");
        binding1.editText.setHint("Enter numbers here (with spaces)");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());

        //caching UI
        arrayEditText = binding1.editText;
    }

    @Override
    public void visualize() {
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);

        //getting array and target
        List<Integer> arr = Util.stringToArray(arrayEditText.getText().toString().trim());

        List<StepCard> stepCardList = new ArrayList<>();

        //Insertion Sort
        int steps = 0;


        //
        for (int i = 1; i < arr.size(); i++) {
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i - 1, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard.setData(ArrayBuilder.build(this, arr, map));
            stepCard.setDescription(TextBuilder.makeBulletList(String.format(Locale.US, "Assuming the elements before and equal to index %d is sorted!", i - 1)));
            stepCardList.add(stepCard);

            int temp = arr.get(i);
            int j = i - 1;
            while (j >= 0 && temp <= arr.get(j)) {
                map.clear();
                //Building step card
                stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                stepCard.setDescription(TextBuilder.makeBulletList(
                        String.format(Locale.US, "Index = %d", j),
                        String.format(Locale.US, "%d < %d", arr.get(j + 1), arr.get(j)),
                        String.format(Locale.US, "Therefore, swapping %d & %d", arr.get(j + 1), arr.get(j))));
                //
                map.put(j, ArrayBuilder.COLOR_TARGET_MATCHED);
                map.put(j + 1, ArrayBuilder.COLOR_TARGET_MATCHED);

                //Generating Data for Step Card
                stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
                stepCardList.add(stepCard);

                //Insertion Sort condition
                arr.set(j + 1, arr.get(j));
                arr.set(j, temp);
                j = j - 1;
            }
            map.clear();
            map.put(j + 1, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(TextBuilder.makeBulletList(
                    String.format(Locale.US, "%d is now at the sorted place.", arr.get(j + 1))));
            stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
            stepCardList.add(stepCard);
        }

        //
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Sorted Array!");
        stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, new HashMap<>()));
        stepCardList.add(stepCard);
        //Adapter
        adapter.setStepCardList(stepCardList);
    }
}