package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.insertion;

import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.DoublyLinkedListBuilder;
import com.chanpreet.visualizeds.classes.data_structure_containers.DoublyLinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class DLLInsertionActivity extends VisualizerActivity {

    private EditText targetEditText;
    private DoublyLinkedListNode head = null;

    @Override
    public void onCreate() {
        Random random = new Random();
        DoublyLinkedListNode temp = head;
        for (int i = 0; i < 3; i++) {
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
//Creating UI
        com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding binding1 = com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter an element to be inserted!");
        binding1.editText.setHint("Enter a value");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());

        //caching UI
        targetEditText = binding1.editText;
    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("DOUBLY LINKED LIST", DoublyLinkedListNode.getList(head));
        map.put("TARGET", targetEditText.getText().toString());
        return map;
    }

    @Override
    public void visualize() {
        int steps = 0;
        //getting array and target
        int target = Integer.parseInt(targetEditText.getText().toString());
        List<StepCard> stepCardList = new ArrayList<>();
        DoublyLinkedListNode temp = head;
        while (temp.next != null) {
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();

            stepCard.setDescription(Arrays.asList("The Next Pointer is not NULL",
                    "Therefore we move to the next node."));
            map.put(temp, DoublyLinkedListBuilder.COLOR_TARGET_NOT_MATCHED);

            stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
            stepCardList.add(stepCard);
            temp = temp.next;
        }

        //
        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();

        map.put(temp, DoublyLinkedListBuilder.COLOR_TARGET_MATCHED);
        stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
        stepCard.setDescription(
                Arrays.asList("The next Pointer is a NULL Node",
                        "Therefore the new node will be mapped to the next pointer of this node."));
        stepCardList.add(stepCard);
        //
        DoublyLinkedListNode node = new DoublyLinkedListNode(target);
        temp.next = node;
        node.prev = temp;

        stepCard = new StepCard();
        stepCard.setTitle("Final Doubly Linked List");
        map.clear();
        map.put(node, DoublyLinkedListBuilder.COLOR_TARGET_MATCHED);
        stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}