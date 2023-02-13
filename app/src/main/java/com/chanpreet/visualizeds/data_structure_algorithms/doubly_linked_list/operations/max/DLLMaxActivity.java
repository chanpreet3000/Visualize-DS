package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.operations.max;

import android.view.View;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.ArrayBuilder;
import com.chanpreet.visualizeds.builder.DoublyLinkedListBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.DoublyLinkedListNode;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class DLLMaxActivity extends VisualizerActivity {

    private DoublyLinkedListNode head = null;

    @Override
    public void onCreate() {
        Random random = new Random();
        DoublyLinkedListNode temp = head;
        for (int i = 0; i < 5; i++) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(random.nextInt() % 20);
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
        stepCard.setTitle("Initial Doubly Linked List!");
        stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
        ItemVisualizeInputCard2Binding binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.textView.setText("Generate New Doubly Linked List");
        binding1.editText.setVisibility(View.GONE);
        binding1.button.setText("Generate");
        binding1.button.setOnClickListener(v -> {
            head = null;
            this.onCreate();
        });

        ItemErrorAlertCardBinding binding2 = ItemErrorAlertCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Generating new Doubly Linked List will erase previous visualization.");

        binding.inputLinearLayout.addView(binding2.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("Doubly Linked List", DoublyLinkedListNode.getList(head));
        return map;
    }

    @Override
    public void visualize() {
        List<StepCard> stepCardList = new ArrayList<>();
        int steps = 0;
        int MAX = Integer.MIN_VALUE;
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Initially the MAX value is set to %d", MAX)));
        stepCard.setData(DoublyLinkedListBuilder.build(this, head, new HashMap<>()));
        stepCardList.add(stepCard);


        DoublyLinkedListNode temp = head;
        while (temp != null) {
            HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();
            map.put(temp, ArrayBuilder.COLOR_TARGET_MATCHED);
            stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "Current Data = %d", temp.data),
                    String.format(Locale.US, "MAX = MAX(%d, %d)", MAX, temp.data),
                    String.format(Locale.US, "MAX = %d", Math.max(MAX, temp.data))));
            stepCard.setData(DoublyLinkedListBuilder.build(this, head, map));
            stepCardList.add(stepCard);
            //
            MAX = Math.max(MAX, temp.data);
            temp = temp.next;
        }
        stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(String.format(Locale.US, "MAX VALUE = %d", MAX)));
        stepCard.setData(DoublyLinkedListBuilder.build(this, head, new HashMap<>()));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }
}