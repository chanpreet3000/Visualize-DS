package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.deletion;

import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.DoublyLinkedListBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.DoublyLinkedListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class DLLDeletionActivity extends VisualizerActivity {

    private EditText targetEditText;
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
//Creating UI
        com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding binding1 = com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter an element to be deleted!");
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
        int target;
        try {
            target = Integer.parseInt(targetEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }
        boolean found = false;
        List<StepCard> stepCardList = new ArrayList<>();
        DoublyLinkedListNode temp = head;
        DoublyLinkedListNode prev = null;
        while (temp != null) {
            if (found) break;
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();
            if (temp.data == target) {
                stepCard.setDescription(
                        Arrays.asList("Found the element to be deleted!",
                                "If the element is the head of the linked list head is set to the next of the head.",
                                "If not the previous node's next ptr is set to the next ptr of the target node."));
                map.put(temp, DoublyLinkedListBuilder.COLOR_TARGET_MATCHED);
                stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
                if (prev == null) {
                    head = head.next;
                } else {
                    prev.next = temp.next;
                }
                found = true;
            } else {
                stepCard.setDescription(
                        Arrays.asList(String.format(Locale.US, "%d ≠ %d", temp.data, target),
                                "Now, move to the next node."));
                map.put(temp, DoublyLinkedListBuilder.COLOR_TARGET_NOT_MATCHED);
                stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
            }
            stepCardList.add(stepCard);
            prev = temp;
            temp = temp.next;
        }
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Final Doubly Linked List!");
        HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();
        if (found) {
            stepCard.setDescription(
                    Arrays.asList(String.format(Locale.US, "Doubly Linked List after deleting %d", target)));
            stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
        } else {
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "%d was not found in the Doubly Linked List.", target)));
        }
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}