package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.preorder;

import android.view.View;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.BSTBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BSTNode;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BSTPreorderActivity extends VisualizerActivity {

    BSTNode root = null;
    int steps = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        steps = 0;
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

    public void helper(List<StepCard> stepCardList, List<Integer> arr, BSTNode root, BSTNode finalRoot) {
        if (root == null) {
            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            //Description
            stepCard.setDescription(TextBuilder.makeBulletList("We reach a null node.",
                    "Therefore we move back to the parent node"));
            stepCardList.add(stepCard);
            return;
        }

        // NODE VISUAL + CALL
        {
            //adding the middle value
            arr.add(root.data);
            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            //Adding view to the holder of the Step Card
            stepCard.setData(BSTBuilder.build(getApplicationContext(), finalRoot, root.data));
            stepCard.setDescription(TextBuilder.makeBulletList(
                    "Node Traversed          : ✅",
                    "Left Subtree Traversed  : ❌",
                    "Right Subtree Traversed : ❌",
                    String.format(Locale.US, "%d is now added to the traversed list.", root.data),
                    "We move to the left subtree",
                    String.format(Locale.US, "Preorder Traversal : %s", arr)));
            //Description
            stepCardList.add(stepCard);
        }


        //LEFT SUBTREE VISUAL + CALL
        {
            //RECURSIVE LEFT SUBTREE CALL
            helper(stepCardList, arr, root.left, finalRoot);

            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            //Adding view to the holder of the Step Card
            stepCard.setData(BSTBuilder.build(getApplicationContext(), finalRoot, root.data));
            //Description
            stepCard.setDescription(TextBuilder.makeBulletList(
                    "Node Traversed          : ✅",
                    "Left Subtree Traversed  : ✅",
                    "Right Subtree Traversed : ❌",
                    "Left Subtree is now fully traversed.",
                    "Now, We move to the left subtree",
                    String.format(Locale.US, "Preorder Traversal : %s", arr)));
            stepCardList.add(stepCard);
        }


        //RIGHT SUBTREE VISUAL + CALL
        {
            //RECURSIVE RIGHT SUBTREE CALL
            helper(stepCardList, arr, root.right, finalRoot);

            //
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            //Adding view to the holder of the Step Card
            stepCard.setData(BSTBuilder.build(getApplicationContext(), finalRoot, root.data));
            //Description
            stepCard.setDescription(TextBuilder.makeBulletList(
                    "Node Traversed          : ✅",
                    "Left Subtree Traversed  : ✅",
                    "Right Subtree Traversed : ✅",
                    "Left and Right Subtree are now fully traversed.",
                    "Now, We move back to the parent node.",
                    String.format(Locale.US, "Preorder Traversal : %s", arr)));
            stepCardList.add(stepCard);
        }
    }

    @Override
    public void visualizeButtonClicked() {
        steps = 0;
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);

        List<Integer> arr = new ArrayList<>();
        List<StepCard> stepCardList = new ArrayList<>();
        helper(stepCardList, arr, root, root);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
    }
}