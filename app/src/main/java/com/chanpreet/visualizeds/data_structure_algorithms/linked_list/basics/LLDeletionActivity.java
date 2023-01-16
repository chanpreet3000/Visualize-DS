package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics;

import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.LinkedListBuilder;
import com.chanpreet.visualizeds.classes.data_structure_containers.LinkedListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class LLDeletionActivity extends VisualizerActivity {

    private EditText arrayEditText;
    private LinkedListNode head = null;

    @Override
    public void onCreate() {
        super.onCreate();

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
        stepCard.setDescription("");
        stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, new HashMap<>()));
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
//Creating UI
        ItemVisualizeInputCardBinding binding1 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding1.textView.setText("Enter an element to be deleted!");
        binding1.editText.setHint("Enter a value");
        binding1.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding1.getRoot());

        //caching UI
        arrayEditText = binding1.editText;
    }

    @Override
    public void visualizeButtonClicked() {
        int steps = 0;
        //getting array and target
        int target;
        try {
            target = Integer.parseInt(arrayEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }
        boolean found = false;
        List<StepCard> stepCardList = new ArrayList<>();
        LinkedListNode temp = head;
        LinkedListNode prev = null;
        while (temp != null) {
            if (found) break;
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<LinkedListNode, Integer> map = new HashMap<>();
            if (temp.data == target) {
                stepCard.setDescription("Found the element to be deleted!.");
                map.put(temp, LinkedListBuilder.COLOR_TARGET_MATCHED);
                stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
                if (prev == null) {
                    head = head.next;
                } else {
                    prev.next = temp.next;
                }
                found = true;
            } else {
                stepCard.setDescription("It is not the target!");
                map.put(temp, LinkedListBuilder.COLOR_TARGET_MATCHED);
                stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
            }
            stepCardList.add(stepCard);
            prev = temp;
            temp = temp.next;
        }
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Final Linked List!");
        HashMap<LinkedListNode, Integer> map = new HashMap<>();
        if (found) {
            stepCard.setDescription("Linked List after deletion!");
            stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
        } else {
            stepCard.setDescription("Target was not found in the linked list!");
        }
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}