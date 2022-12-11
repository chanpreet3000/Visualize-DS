package com.chanpreet.visualizeds.topics.linked_list.basics;

import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.StepCard;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;
import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.LinkedListBuilder;
import com.chanpreet.visualizeds.classes.LinkedListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TraversalActivity extends VisualizerActivity {

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
        binding1.textView.setText("Enter an element to be searched!");
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
        while (temp != null) {
            if (found) break;
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            HashMap<LinkedListNode, Integer> map = new HashMap<>();
            if (temp.data == target) {
                stepCard.setDescription("We found the element to be searched.");
                map.put(temp, LinkedListBuilder.COLOR_TARGET_MATCHED);
                found = true;
            } else {
                stepCard.setDescription("This node is not the equal to the search target.\nTherefore we move to the next node.");
                map.put(temp, LinkedListBuilder.COLOR_TARGET_NOT_MATCHED);
            }
            stepCard.setData(LinkedListBuilder.build(getApplicationContext(), head, map));
            stepCardList.add(stepCard);
            temp = temp.next;
        }
        if(!found){
            StepCard stepCard = new StepCard();
            stepCard.setTitle("Target not Present!");
            stepCard.setDescription("");
            stepCardList.add(stepCard);
        }
        adapter.setStepCardList(stepCardList);
    }
}