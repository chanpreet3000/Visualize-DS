package com.chanpreet.visualizeds.data_structure_algorithms.array.searching.binary_search;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ArrayBinarySearchActivity extends VisualizerActivity {

    private EditText arrayEditText;
    private EditText targetEditText;

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

        //caching UI
        arrayEditText = binding1.editText;
        targetEditText = binding2.editText;
    }

    @Override
    public void visualize() {
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);
        //getting array and target
        List<Integer> arr = Util.stringToArray(arrayEditText.getText().toString().trim());
        int target;
        try {
            target = Integer.parseInt(targetEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }

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