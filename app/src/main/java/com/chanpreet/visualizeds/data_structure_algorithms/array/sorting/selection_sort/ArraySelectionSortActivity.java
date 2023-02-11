package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.selection_sort;

import android.text.InputType;
import android.widget.EditText;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ArraySelectionSortActivity extends VisualizerActivity {

    private EditText arrayEditText;

    @Override
    public void onCreate() {

    }

    @Override
    public void generateInputUI() {
        //Creating UI
        com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding binding1 = com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array.");
        binding1.editText.setHint("Enter numbers here (with spaces)");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());

        //caching UI
        arrayEditText = binding1.editText;
    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("ARRAY", stringToArray(arrayEditText.getText().toString()));
        return map;
    }

    @Override
    public void visualize() {
        //getting array and target
        List<Integer> arr = stringToArray(arrayEditText.getText().toString().trim());

        List<StepCard> stepCardList = new ArrayList<>();
        //Selection Sort
        int steps = 0;
        for (int i = 0; i < arr.size(); i++) {
            int pos = i;
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(
                    String.format(Locale.US, "Assuming %d to be the smallest element", arr.get(i)),
                    String.format(Locale.US, "Smallest no index = %d", pos)));
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(pos, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard.setData(ArrayBuilder.build(this, arr, map));
            stepCardList.add(stepCard);

            for (int j = i + 1; j < arr.size(); j++) {
                map.clear();
                stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                if (arr.get(j) < arr.get(pos)) {
                    stepCard.setDescription(Arrays.asList(
                            String.format(Locale.US, "Index = %d, Smallest no index = %d", j, pos),
                            String.format(Locale.US, "%d is smaller than %d", arr.get(j), arr.get(pos)),
                            String.format(Locale.US, "Now, smallest value is %d", arr.get(j))));

                    map.put(pos, ArrayBuilder.COLOR_TARGET_MATCHED);
                    map.put(j, ArrayBuilder.COLOR_TARGET_MATCHED);
                    pos = j;
                } else {
                    stepCard.setDescription(Arrays.asList(
                            String.format(Locale.US, "Index = %d, Smallest no index = %d", j, pos),
                            String.format(Locale.US, "%d is not smaller than %d", arr.get(j), arr.get(pos)),
                            String.format(Locale.US, "Therefore, smallest value is still %d", arr.get(pos))));
                    map.put(pos, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                    map.put(j, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                }
                stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
                stepCardList.add(stepCard);
            }
            int temp = arr.get(i);
            arr.set(i, arr.get(pos));
            arr.set(pos, temp);

            //
            map.clear();
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Swapping %d and %d at position %d & %d", arr.get(i), arr.get(pos), i, pos)));
            map.put(i, ArrayBuilder.COLOR_TARGET_MATCHED);
            map.put(pos, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard.setData(ArrayBuilder.build(this, arr, map));
            stepCardList.add(stepCard);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList("Array is now sorted!"));
        stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
        stepCardList.add(stepCard);
        //Adapter
        adapter.setStepCardList(stepCardList);
    }
}