package com.chanpreet.visualizeds.data_structure_algorithms.array.operations.reverse;

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

public class ArrayReverseActivity extends VisualizerActivity {

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
        StepCard stepCard = new StepCard();

        for (int i = 0; i <= arr.size() / 2; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i, ArrayBuilder.COLOR_TARGET_MATCHED);
            map.put(arr.size() - i - 1, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Front Index = %d & Back Index = %d", i, arr.size() - i - 1),
                    String.format(Locale.US, "swap arr[%d] & arr[%d]", i, arr.size() - i - 1),
                    String.format(Locale.US, "New front index = %d, back index = %d", i + 1, arr.size() - i - 1 - 1)));
            stepCard.setData(ArrayBuilder.build(this, arr, map));
            stepCardList.add(stepCard);
            //
            int temp = arr.get(i);
            arr.set(i, arr.get(arr.size() - i - 1));
            arr.set(arr.size() - i - 1, temp);
        }
        stepCard = new StepCard();
        stepCard.setTitle("Final Reversed array");
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Front Index(%d) > Back Index(%d)", (arr.size() + 1) / 2, arr.size() - ((arr.size() + 1) / 2) - 1),
                "Array has been reversed!"));
        stepCard.setData(ArrayBuilder.build(this, arr, new HashMap<>()));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }
}