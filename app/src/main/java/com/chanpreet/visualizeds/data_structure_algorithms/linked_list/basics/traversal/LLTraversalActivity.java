package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.traversal;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.LinkedListBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.LinkedListNode;
import com.chanpreet.visualizeds.databinding.ItemSuccessAlertCardBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class LLTraversalActivity extends VisualizerActivity {

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
        stepCard.setDescription("");
        stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
//Creating UI
        ItemSuccessAlertCardBinding binding2 = ItemSuccessAlertCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Traversal of linked list is similar to printing all the elements of the Linked List.");

        //adding UI
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
    }

    @Override
    public Map<String, Object> getVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("LINKED LIST", LinkedListNode.getList(head));
        return map;
    }

    @Override
    public void visualize() {
        int steps = 0;
        //getting array and target

        List<StepCard> stepCardList = new ArrayList<>();

        List<Integer> arr = new ArrayList<>();
        LinkedListNode temp = head;
        while (temp != null) {
            arr.add(temp.data);
            //
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<LinkedListNode, Integer> map = new HashMap<>();
            map.put(temp, LinkedListBuilder.COLOR_TARGET_MATCHED);
            stepCard.setDescription(
                    TextBuilder.makeBulletList(String.format(Locale.US, "Current node is %d", temp.data),
                            "Array List = " + arr,
                            "Now, we move to the next node."));
            stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
            stepCardList.add(stepCard);
            //
            temp = temp.next;
        }
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Linked List Traversed");
        stepCard.setDescription(TextBuilder.makeBulletList("We reached a NULL Node",
                "That means this is the end of Linked List",
                "Array List = " + arr));
        stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}