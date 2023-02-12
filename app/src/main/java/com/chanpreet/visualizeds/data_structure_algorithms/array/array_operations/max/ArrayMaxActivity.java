package com.chanpreet.visualizeds.data_structure_algorithms.array.array_operations.max;

import android.text.InputType;
import android.widget.EditText;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ArrayMaxActivity extends VisualizerActivity {

    private EditText arrayEditText;

    @Override
    public void onCreate() {

    }

    @Override
    public void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array!");
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
        //Bubble Sort
        int steps = 0;
        int MAX = Integer.MIN_VALUE;
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Initially the MAX value is set to %d", MAX)));
        stepCard.setData(ArrayBuilder.build(this, arr, new HashMap<>()));
        stepCardList.add(stepCard);

        for (int i = 0; i < arr.size(); i++) {

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Index = %d", i),
                    String.format(Locale.US, "MAX = max(%d, %d)", MAX, arr.get(i)),
                    String.format(Locale.US, "MAX = %d", Math.max(MAX, arr.get(i)))));
            stepCard.setData(ArrayBuilder.build(this, arr, map));
            stepCardList.add(stepCard);
            //
            MAX = Math.max(MAX, arr.get(i));
        }
        stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "MAX VALUE = %d", MAX)));
        stepCard.setData(ArrayBuilder.build(this, arr, new HashMap<>()));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }
}