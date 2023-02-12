package com.chanpreet.visualizeds.data_structure_algorithms.array.array_operations.min;

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

public class ArrayMinActivity extends VisualizerActivity {

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
        int MIN = Integer.MAX_VALUE;
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Initially the MIN value is set to %d", MIN)));
        stepCard.setData(ArrayBuilder.build(this, arr, new HashMap<>()));
        stepCardList.add(stepCard);

        for (int i = 0; i < arr.size(); i++) {

            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Index = %d", i),
                    String.format(Locale.US, "MIN = min(%d, %d)", MIN, arr.get(i)),
                    String.format(Locale.US, "MIN = %d", Math.min(MIN, arr.get(i)))));
            stepCard.setData(ArrayBuilder.build(this, arr, map));
            stepCardList.add(stepCard);
            //
            MIN = Math.min(MIN, arr.get(i));
        }
        stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "MIN VALUE = %d", MIN)));
        stepCard.setData(ArrayBuilder.build(this, arr, new HashMap<>()));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }
}