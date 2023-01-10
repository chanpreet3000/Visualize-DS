package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.preorder;

import android.view.View;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.builder.BinarySearchTreeBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BinaryTreeNode;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BSTPreorderActivity extends VisualizerActivity {

    BinaryTreeNode root = null;
    int steps = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        steps = 0;
        for (int i = 0; i < 5; i++) {
            int rand = new Random().nextInt(200) - 100;
            root = BinaryTreeNode.insertNode(root, rand);
        }
        List<StepCard> stepCardList = new ArrayList<>();
        StepCard stepCard = new StepCard();
        stepCard.setTitle("Initial Binary Search Tree");

        BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
        stepCard.setData(binarySearchTreeBuilder.generateBinarySearchTree(root, -300));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }

    public void helper(List<StepCard> stepCardList, List<Integer> arr, BinaryTreeNode root, BinaryTreeNode finalRoot) {
        if (root == null) {
            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            //Description
            stepCard.setDescription("We reach a null point.\nTherefore we move back to the parent node");
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
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCard.setData(binarySearchTreeBuilder.generateBinarySearchTree(finalRoot, root.data));
            //Description
            stepCard.setDescription(String.format(Locale.US, "%d is now added to the traversed list.\nPreorder list : %s \n\nNow we move to the left subtree.", root.data, arr));
            stepCardList.add(stepCard);
        }


        //LEFT SUBTREE VISUAL + CALL
        {
            //RECURSIVE LEFT SUBTREE CALL
            helper(stepCardList, arr, root.leftNode, finalRoot);

            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            //Adding view to the holder of the Step Card
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCard.setData(binarySearchTreeBuilder.generateBinarySearchTree(finalRoot, root.data));
            //Description
            stepCard.setDescription(String.format("We move to the right subtree.\nPreorder list : %s", arr));
            stepCardList.add(stepCard);
        }


        //RIGHT SUBTREE VISUAL + CALL
        {
            //RECURSIVE RIGHT SUBTREE CALL
            helper(stepCardList, arr, root.rightNode, finalRoot);
        }
    }

    @Override
    public void visualizeButtonClicked() {
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);

        List<Integer> arr = new ArrayList<>();
        List<StepCard> stepCardList = new ArrayList<>();
        helper(stepCardList, arr, root, root);
        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
        //
        adapter = new StepCardAdapter(getApplicationContext());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(4);
    }
}