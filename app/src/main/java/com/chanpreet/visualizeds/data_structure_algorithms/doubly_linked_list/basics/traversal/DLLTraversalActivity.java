package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.traversal;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.DoublyLinkedListBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.DoublyLinkedListNode;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class DLLTraversalActivity extends VisualizerActivity {

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
        ItemSuccessAlertCardBinding binding2 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Traversal of linked list is similar to printing all the elements of the Doubly Linked List.");

        //adding UI
        binding.inputLinearLayout.addView(binding2.getRoot());

    }

    @Override
    public Map<String, Object> getDefaultVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("DOUBLY LINKED LIST", DoublyLinkedListNode.getList(head));
        return map;
    }

    @Override
    public void visualize() {
        int steps = 0;
        //getting array and target

        List<Integer> arr = new ArrayList<>();
        List<StepCard> stepCardList = new ArrayList<>();
        DoublyLinkedListNode temp = head;

        while (temp != null) {
            arr.add(temp.data);
            //
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();
            map.put(temp, DoublyLinkedListBuilder.COLOR_TARGET_MATCHED);
            stepCard.setDescription(
                    Arrays.asList(String.format(Locale.US, "Current node is %d", temp.data),
                            "Array List = " + arr,
                            "Now, we move to the next node."));
            stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
            stepCardList.add(stepCard);
            //
            temp = temp.next;
        }
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Doubly Linked List Traversed");
        stepCard.setDescription(Arrays.asList("We reached a NULL Node",
                "That means this is the end of Doubly Linked List",
                "Array List = " + arr));
        stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}