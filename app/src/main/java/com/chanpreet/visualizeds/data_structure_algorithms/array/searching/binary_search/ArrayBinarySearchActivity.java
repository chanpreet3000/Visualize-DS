package com.chanpreet.visualizeds.data_structure_algorithms.array.searching.binary_search;

import android.text.InputType;
import android.widget.EditText;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ArrayBinarySearchActivity extends VisualizerActivity {

    private EditText arrayEditText;
    private EditText targetEditText;

    @Override
    public void onCreate() {

    }

    @Override
    public void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter your array (Sorted Array)");
        binding1.editText.setHint("Enter numbers here (with spaces)");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        ItemVisualizeInputCardBinding binding2 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Enter element to be searched");
        binding2.editText.setHint("Enter number to be searched");
        binding2.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        ItemSuccessAlertCardBinding binding3 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding3.textView.setText("The Array entered must be sorted for binary search to work as intended");
        //adding UI
        binding.inputLinearLayout.addView(binding3.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
        binding.inputLinearLayout.addView(binding2.getRoot());

        arrayEditText = binding1.editText;
        targetEditText = binding2.editText;
    }

    @Override
    public Map<String, Object> getVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("ARRAY", stringToArray(arrayEditText.getText().toString()));
        map.put("TARGET", stringToArray(targetEditText.getText().toString()));
        return map;
    }

    @Override
    public void visualize() {
        int target;
        List<Integer> arr = stringToArray(arrayEditText.getText().toString().trim());
        target = Integer.parseInt(targetEditText.getText().toString());


        int start = 0, end = arr.size() - 1;
        int steps = 0;
        List<StepCard> stepCardList = new ArrayList<>();
        boolean canContinue = true;
        while (start <= end) {
            if (!canContinue) break;
            int mid = start + (end - start) / 2;
            //generating Step Card
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));

            HashMap<Integer, Integer> map = new HashMap<>();
            String description;
            if (arr.get(mid) == target) {
                canContinue = false;
                map.put(mid, ArrayBuilder.COLOR_TARGET_MATCHED);
                description = TextBuilder.makeBulletList(
                        String.format(Locale.US, "start = %d, end = %d, mid = %d", start, end, mid),
                        String.format(Locale.US, "arr[%d] == %d", mid, target),
                        String.format(Locale.US, "Element found at index %d", mid));
            } else {
                map.put(mid, ArrayBuilder.COLOR_TARGET_NOT_MATCHED);
                if (arr.get(mid) > target) {
                    description = TextBuilder.makeBulletList(
                            String.format(Locale.US, "start = %d, end = %d, mid = %d", start, end, mid),
                            String.format(Locale.US, "arr[%d] > %d", mid, target),
                            "Target is smaller than element at the middle position.",
                            "Therefore we eliminate right side from the middle.",
                            String.format(Locale.US, "end = %d", end));
                    end = mid - 1;
                } else {
                    description = TextBuilder.makeBulletList(
                            String.format(Locale.US, "start = %d, end = %d, mid = %d", start, end, mid),
                            String.format(Locale.US, "arr[%d] < %d", mid, target),
                            "Target is greater than element at the middle position.",
                            "Therefore we eliminate left side from the middle.",
                            String.format(Locale.US, "start = %d", start));
                    start = mid + 1;
                }
            }
            stepCard.setDescription(description);
            stepCard.setData(ArrayBuilder.build(getApplicationContext(), arr, map));
            stepCardList.add(stepCard);
        }
        adapter.setStepCardList(stepCardList);
    }
}