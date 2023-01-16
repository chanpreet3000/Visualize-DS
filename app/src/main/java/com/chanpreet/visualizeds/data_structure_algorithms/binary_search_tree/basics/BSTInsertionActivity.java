package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.BSTBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BSTNode;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BSTInsertionActivity extends VisualizerActivity {
    private EditText targetEditText;
    BSTNode root = null;

    @Override
    public void onCreate() {
        super.onCreate();

        for (int i = 0; i < 5; i++) {
            int rand = new Random().nextInt(200) - 100;
            root = BSTNode.insertNode(root, rand);
        }
        List<StepCard> stepCardList = new ArrayList<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Initial Binary Search Tree");

        stepCard.setData(BSTBuilder.build(getApplicationContext(), root, -300));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void visualizeButtonClicked() {
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);
        //getting array and target
        int target;
        try {
            target = Integer.parseInt(targetEditText.getText().toString());
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            return;
        }

        BSTNode temp = root;
        List<StepCard> stepCardList = new ArrayList<>();
        //Generating in Between layout
        int steps = 0;
        while (temp != null) {
            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));

            //Adding view to the holder of the Step Card
            stepCard.setData(BSTBuilder.build(getApplicationContext(), root, temp.data));

            //Description
            if (target == temp.data) {
                stepCard.setDescription("The element to be inserted is already present in the Binary Search Tree.");
            } else if (target < temp.data) {
                stepCard.setDescription(String.format(Locale.US, "%d is less than %d.\nTherefore we move to the left subtree.", target, temp.data));
            } else {
                stepCard.setDescription(String.format(Locale.US, "%d is greater than %d.\nTherefore we move to the right subtree.", target, temp.data));
            }
            stepCardList.add(stepCard);

            //BST properties.
            if (target == temp.data) {
                return;
            } else if (target < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
            if (temp == null) {
                StepCard stepCard1 = new StepCard();
                stepCard1.setTitle(String.format(Locale.US, "Step %d", ++steps));
                stepCard1.setTitle("We reached a null node so we will insert the node here.");
                //Adding view to the holder of the Step Card
                stepCardList.add(stepCard1);
            }
        }
        //Final Step Card
        root = BSTNode.insertNode(root, target);
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Binary Search Tree After Insertion");
        stepCard.setDescription("This is the Binary Search Tree after Insertion.");
        stepCard.setData(BSTBuilder.build(getApplicationContext(), root, target));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding2 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Enter element to be inserted");
        binding2.editText.setHint("Enter element to be inserted");
        binding2.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
        targetEditText = binding2.editText;
    }
}
