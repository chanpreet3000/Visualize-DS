package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.deletion;

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
    public void visualize() {
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
                stepCard.setDescription(
                        TextBuilder.makeBulletList("Found the element to be deleted!",
                                "If the element is the head of the linked list head is set to the next of the head.",
                                "If not the previous node's next ptr is set to the next ptr of the target node."));
                map.put(temp, LinkedListBuilder.COLOR_TARGET_MATCHED);
                stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
                if (prev == null) {
                    head = head.next;
                } else {
                    prev.next = temp.next;
                }
                found = true;
            } else {
                stepCard.setDescription(
                        TextBuilder.makeBulletList(String.format(Locale.US, "%d ≠ %d", temp.data, target),
                                "Now, move to the next node."));
                map.put(temp, LinkedListBuilder.COLOR_TARGET_NOT_MATCHED);
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
            stepCard.setDescription(
                    TextBuilder.makeBulletList(String.format(Locale.US, "Linked List after deleting %d", target)));
            stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
        } else {
            stepCard.setDescription(TextBuilder.makeBulletList(String.format(Locale.US, "%d was not found in the linked list.", target)));
        }
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}