package com.chanpreet.visualizeds.data_structure_algorithms.BIT.basics.BITBasicsActivity;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.BITBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BITBasicsActivity extends VisualizerActivity {

    private EditText insertEditText, rangeEditText;
    private final int N = 9;
    List<Integer> arr = new ArrayList<>();

    @Override
    public void onCreate() {
        for (int i = 0; i < N; i++) {
            arr.add(0);
        }

        List<StepCard> stepCardList = new ArrayList<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Initial Binary Indexed Tree");
        stepCard.setData(BITBuilder.build(this, arr, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
        binding.visualizeButton.setVisibility(View.GONE);
        //Creating UI
        ItemSuccessAlertCardBinding binding1 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Inserting a number (x) in a Binary Indexed Tree is similar to increasing the count of occurrence of a x by 1.");

        ItemVisualizeInputCard2Binding binding2 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding2.textView.setText("Insertion");
        binding2.editText.setHint("Enter a number <= " + (N - 1));
        binding2.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        binding2.button.setText("Insert");
        binding2.button.setOnClickListener(v -> insertBIT());


        ItemSuccessAlertCardBinding binding3 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding3.textView.setText("Range Query of a number (x) in a Binary Indexed Tree is similar to getting sum of occurrences of number less than equal to x.");

        ItemVisualizeInputCard2Binding binding4 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding4.textView.setText("Range Query");
        binding4.editText.setHint("Enter a number <= " + (N - 1));
        binding4.editText.setInputType(InputType.TYPE_CLASS_PHONE);
        binding4.button.setText("Range Query");
        binding4.button.setOnClickListener(v -> rangeBIT());


        ItemErrorAlertCardBinding binding5 = ItemErrorAlertCardBinding.inflate(getLayoutInflater());
        binding5.textView.setText(String.format(Locale.US, "The current size of BIT is %d so the BIT can be used to store information about numbers ranging [1, %d] as 0 is invalid and last index is (%d - 1)", N, N - 1, N));

        //adding UI
        binding.inputLinearLayout.addView(binding5.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
        binding.inputLinearLayout.addView(binding2.getRoot());
        binding.inputLinearLayout.addView(binding3.getRoot());
        binding.inputLinearLayout.addView(binding4.getRoot());

        //caching UI
        insertEditText = binding2.editText;
        rangeEditText = binding4.editText;
    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("INSERT", insertEditText.getText().toString());
        map.put("RANGE", rangeEditText.getText().toString());
        return map;
    }


    String intToBinary(int n) {
        StringBuilder s = new StringBuilder();
        while (n > 0) {
            s.insert(0, ((n % 2 == 0) ? '0' : '1'));
            n /= 2;
        }
        return s.toString();
    }

    private void insertBIT() {
        this.hideKeyboard();
        int target;
        try {
            target = Integer.parseInt(insertEditText.getText().toString());
            if (target >= N) {
                Toast.makeText(this, "Enter a number <= " + (N - 1), Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }
        int steps = 0;
        List<StepCard> stepCardList = new ArrayList<>();
        int i = target;
        while (i < N) {
            arr.set(i, arr.get(i) + 1);
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i, BITBuilder.COLOR_TARGET_MATCHED);
            stepCard.setData(BITBuilder.build(this, arr, map));
            stepCard.setDescription(Arrays.asList(
                    String.format(Locale.US, "Index : %d", i),
                    String.format(Locale.US, "Increasing the element by %d", 1),
                    String.format(Locale.US, "Binary of %d is %s", i, intToBinary(i)),
                    String.format(Locale.US, "Binary of (%d & (-%d)) is %s which is equal to %d", i, i, intToBinary((i & (-i))), (i & (-i))),
                    String.format(Locale.US, "Set Index to (%d + (%d & (-%d))) == %d", i, i, i, i + (i & (-i)))));
            stepCardList.add(stepCard);
            i += (i & (-i));
        }

        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setData(BITBuilder.build(this, arr, new HashMap<>()));
        stepCard.setDescription(Arrays.asList(
                String.format(Locale.US, "Index : %d", i),
                String.format(Locale.US, "Current index >= size of BIT array (%d)", N)));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
        binding.viewPager.setCurrentItem(0);
    }

    private void rangeBIT() {
        this.hideKeyboard();
        int target;
        try {
            target = Integer.parseInt(rangeEditText.getText().toString());
            if (target >= N) {
                Toast.makeText(this, "Enter a number <= " + (N - 1), Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }
        int steps = 0;
        List<StepCard> stepCardList = new ArrayList<>();
        int i = target;
        int ans = 0;
        while (i > 0) {
            ans += arr.get(i);
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(i, BITBuilder.COLOR_TARGET_MATCHED);
            stepCard.setData(BITBuilder.build(this, arr, map));
            stepCard.setDescription(Arrays.asList(
                    String.format(Locale.US, "Index : %d", i),
                    String.format(Locale.US, "Index %d represents the number of occurrences of numbers ranged between [%d, %d] which is %d", i, (i - (i & (-i)) + 1), i, arr.get(i)),
                    String.format(Locale.US, "Increasing the answer by %d", arr.get(i)),
                    String.format(Locale.US, "Current Ans = %d", ans),
                    String.format(Locale.US, "Set Index to (%d - (%d & (-%d))) == %d", i, i, i, i - (i & (-i)))));
            stepCardList.add(stepCard);
            i -= (i & (-i));
        }

        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setData(BITBuilder.build(this, arr, new HashMap<>()));
        stepCard.setDescription(Arrays.asList(
                String.format(Locale.US, "Index : %d", i),
                "Invalid index in case in of BIT.",
                String.format(Locale.US, "Total number of elements in range [1, %d] is %d", target, ans)));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
        binding.viewPager.setCurrentItem(0);
    }

    @Override
    public void visualize() {
    }
}