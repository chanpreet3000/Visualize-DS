package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.operations.min;

import android.view.View;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.builder.LinkedListBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.LinkedListNode;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class LLMinActivity extends VisualizerActivity {

    private LinkedListNode head = null;

    @Override
    public void onCreate() {
        Random random = new Random();
        LinkedListNode temp = head;
        for (int i = 0; i < 5; i++) {
            LinkedListNode node = new LinkedListNode(random.nextInt() % 20);
            if (temp == null) {
                head = node;
                temp = head;
            } else {
                temp.next = node;
                temp = temp.next;
            }
        }

        List<StepCard> stepCardList = new ArrayList<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Initial Linked List!");
        stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
        ItemVisualizeInputCard2Binding binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.textView.setText("Generate New Linked List");
        binding1.editText.setVisibility(View.GONE);
        binding1.button.setText("Generate");
        binding1.button.setOnClickListener(v -> {
            head = null;
            this.onCreate();
        });

        ItemErrorAlertCardBinding binding2 = ItemErrorAlertCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Generating new Linked List will erase previous visualization.");

        binding.inputLinearLayout.addView(binding2.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("LINKED LIST", LinkedListNode.getList(head));
        return map;
    }

    @Override
    public void visualize() {
        List<StepCard> stepCardList = new ArrayList<>();
        int steps = 0;
        int MIN = Integer.MAX_VALUE;
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Initially the MIN value is set to %d", MIN)));
        stepCard.setData(LinkedListBuilder.build(this, head, new HashMap<>()));
        stepCardList.add(stepCard);


        LinkedListNode temp = head;
        while (temp != null) {
            HashMap<LinkedListNode, Integer> map = new HashMap<>();
            map.put(temp, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Current Data = %d", temp.data),
                    String.format(Locale.US, "MIN = MIN(%d, %d)", MIN, temp.data),
                    String.format(Locale.US, "MIN = %d", Math.min(MIN, temp.data))));
            stepCard.setData(LinkedListBuilder.build(this, head, map));
            stepCardList.add(stepCard);
            //
            MIN = Math.min(MIN, temp.data);
            temp = temp.next;
        }
        stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "MIN VALUE = %d", MIN)));
        stepCard.setData(LinkedListBuilder.build(this, head, new HashMap<>()));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }
}