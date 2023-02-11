package com.chanpreet.visualizeds.data_structure_algorithms.array.searching.linear_search;

import android.text.InputType;
import android.widget.EditText;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ArrayLinearSearchActivity extends VisualizerActivity {
    private EditText arrayEditText;
    private EditText targetEditText;

    @Override
    public void onCreate() {

    }

    @Override
    public void generateInputUI() {
//Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array");
        binding1.editText.setHint("Enter numbers here (with spaces)");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        ItemVisualizeInputCardBinding binding2 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Enter element to be searched");
        binding2.editText.setHint("Enter one Integer.");
        binding2.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
        arrayEditText = binding1.editText;
        targetEditText = binding2.editText;
    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("ARRAY", stringToArray(arrayEditText.getText().toString()));
        map.put("TARGET", stringToArray(targetEditText.getText().toString()));
        return map;
    }

    @Override
    public void visualize() {
        //getting array and target
        List<Integer> arr = stringToArray(arrayEditText.getText().toString().trim());
        int target = Integer.parseInt(targetEditText.getText().toString());
        List<StepCard> stepCardList = new ArrayList<>();

        boolean canContinue = true;
        for (int i = 0; i < arr.size(); i++) {
            if (!canContinue) break;
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", i + 1));

            HashMap<Integer, Integer> map = new HashMap<>();
            if (arr.get(i) == target) {
                canContinue = false;
                map.put(i, ArrayBuilder.COLOR_TARGET_MATCHED);
                stepCard.setDescription(
                        Arrays.asList(String.format(Locale.US, "Index = %d", i),
                                String.format(Locale.US, "%d == %d", arr.get(i), target),
                                String.format(Locale.US, "%d found at index %d", target, i)));
            } else {
                map.put(i, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                stepCard.setDescription(
                        Arrays.asList(String.format(Locale.US, "Index = %d", i),
                                String.format(Locale.US, "%d ≠ %d", arr.get(i), target),
                                "Therefore, we move to the next index"));
            }
            stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
            stepCardList.add(stepCard);
        }
        if (canContinue) {
            StepCard stepCard = new StepCard();
            stepCard.setTitle("Element not found");
            stepCard.setDescription(
                    Arrays.asList(String.format(Locale.US, "Index = %d > the size of the array", arr.size()),
                            String.format(Locale.US, "Therefore, %d was not present in the array.", target)));
            stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, new HashMap<>()));
            stepCardList.add(stepCard);
        }
        adapter.setStepCardList(stepCardList);
    }
}