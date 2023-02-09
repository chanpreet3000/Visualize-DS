package com.chanpreet.visualizeds.data_structure_algorithms.array.sorting.bubble_sort;

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

public class ArrayBubbleSortActivity extends VisualizerActivity {

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
    public Map<String, Object> getVisualizationInformation() {
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
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.size() - i - 1; j++) {
                //Building step card
                StepCard stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));

                List<String> description;
                HashMap<Integer, Integer> map = new HashMap<>();
                if (arr.get(j) > arr.get(j + 1)) {
                    map.put(j, ArrayBuilder.COLOR_TARGET_MATCHED);
                    map.put(j + 1, ArrayBuilder.COLOR_TARGET_MATCHED);
                    stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));

                    description = Arrays.asList(String.format(Locale.US, "Index = %d & %d", j, j + 1),
                            String.format(Locale.US, "%d > %d.", arr.get(j), arr.get(j + 1)),
                            String.format(Locale.US, "Therefore swapping %d & %d.", arr.get(j), arr.get(j + 1)));

                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                } else {
                    map.put(j, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                    map.put(j + 1, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                    stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));

                    description = Arrays.asList(String.format(Locale.US, "Index = %d & %d", j, j + 1),
                            String.format(Locale.US, "%d < %d.", arr.get(j), arr.get(j + 1)),
                            "Therefore no swapping necessary");
                }
                stepCard.setDescription(description);
                stepCardList.add(stepCard);
            }
            //Final stepCard
            StepCard stepCard = new StepCard();
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(arr.size() - i - 1, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "%d is now sorted to its actual position", arr.get(arr.size() - i - 1)),
                    String.format(Locale.US, "Now we traverse from index 0 to %d", arr.size() - i - 2)));
            stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
            stepCardList.add(stepCard);
        }
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Sorted Array!");
        stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

}