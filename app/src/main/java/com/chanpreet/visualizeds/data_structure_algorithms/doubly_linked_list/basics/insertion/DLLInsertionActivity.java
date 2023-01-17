package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.insertion;

import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.DoublyLinkedListBuilder;
import com.chanpreet.visualizeds.classes.data_structure_containers.DoublyLinkedListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DLLInsertionActivity extends VisualizerActivity {

    private EditText arrayEditText;
    private DoublyLinkedListNode head = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Random random = new Random();
        DoublyLinkedListNode temp = head;
        for (int i = 0; i < 2; i++) {
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
        stepCard.setDescription("");
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
        List<StepCard> stepCardList = new ArrayList<>();
        DoublyLinkedListNode temp = head;
        DoublyLinkedListNode prev = head;
        while (temp != null) {
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();

            stepCard.setDescription("This is not the end!.\nTherefore we move to the next Node.");
            map.put(temp, DoublyLinkedListBuilder.COLOR_TARGET_NOT_MATCHED);

            stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
            stepCardList.add(stepCard);

            prev = temp;
            temp = temp.next;
        }

        DoublyLinkedListNode node = new DoublyLinkedListNode(target);
        assert prev != null;
        prev.next = node;
        node.prev = prev;

        StepCard stepCard = new StepCard();
        stepCard.setTitle("Final Doubly Linked List");
        HashMap<DoublyLinkedListNode, Integer> map = new HashMap<>();
        map.put(head, DoublyLinkedListBuilder.COLOR_TARGET_MATCHED);
        stepCard.setData(DoublyLinkedListBuilder.build(getApplicationContext(), head, map));
        stepCard.setDescription("After insertion Doubly Linked List looks like this!.");
        stepCardList.add(stepCard);
        adapter.setStepCardList(stepCardList);
    }
}