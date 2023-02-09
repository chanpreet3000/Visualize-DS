package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.insertion;

import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.LinkedListBuilder;
import com.chanpreet.visualizeds.classes.data_structure_containers.LinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class LLInsertionActivity extends VisualizerActivity {

    private EditText targetEditText;
    private LinkedListNode head = null;

    @Override
    public void onCreate() {
        Random random = new Random();
        LinkedListNode temp = head;
        for (int i = 0; i < 3; i++) {
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
//Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter an element to be inserted!");
        binding1.editText.setHint("Enter a value");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());

        //caching UI
        targetEditText = binding1.editText;
    }

    @Override
    public Map<String, Object> getVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("LINKED LIST", LinkedListNode.getList(head));
        map.put("TARGET", targetEditText.getText().toString());
        return map;
    }

    @Override
    public void visualize() {
        int steps = 0;
        //getting array and target
        int target;
        try {
            target = Integer.parseInt(targetEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }
        List<StepCard> stepCardList = new ArrayList<>();
        LinkedListNode temp = head;
        while (temp.next != null) {
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            stepCard.setDescription(
                    Arrays.asList(
                            "The Next Address is not NULL.",
                            "So this is not the last node in the Linked List",
                            "We move to the next node"));
            HashMap<LinkedListNode, Integer> map = new HashMap<>();
            map.put(temp, LinkedListBuilder.COLOR_TARGET_NOT_MATCHED);
            stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
            stepCardList.add(stepCard);
            temp = temp.next;
        }


        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setDescription(Arrays.asList(
                "The Next Address is NULL.",
                "So this is the last node in the Linked List",
                "We insert the node to the next pointer"));
        HashMap<LinkedListNode, Integer> map = new HashMap<>();
        map.put(temp, LinkedListBuilder.COLOR_TARGET_MATCHED);
        stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
        stepCardList.add(stepCard);

        LinkedListNode newNode = new LinkedListNode((target));
        temp.next = newNode;


        stepCard = new StepCard();
        stepCard.setTitle("Final Linked List!");
        map.clear();
        map.put(newNode, LinkedListBuilder.COLOR_TARGET_MATCHED);
        stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
        stepCardList.add(stepCard);

        //

        adapter.setStepCardList(stepCardList);
    }
}