package com.chanpreet.visualizeds.topics.binary_search_tree.traversal.prefix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.chanpreet.visualizeds.builder.BinarySearchTreeBuilder;
import com.chanpreet.visualizeds.builder.StepCardBuilder;
import com.chanpreet.visualizeds.classes.BinaryTreeNode;
import com.chanpreet.visualizeds.classes.DataStructureAlgorithm;
import com.chanpreet.visualizeds.databinding.ActivityBinarySearchTreePrefixVisualizerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BinarySearchPrefixTreeVisualizerActivity extends AppCompatActivity {

    private ActivityBinarySearchTreePrefixVisualizerBinding binding;
    private List<Integer> arr;
    private int steps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinarySearchTreePrefixVisualizerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //filling header information
        DataStructureAlgorithm dataStructureAlgorithm = (DataStructureAlgorithm) getIntent().getSerializableExtra("data");
        binding.titleTextView.setText(dataStructureAlgorithm.getName());
        binding.difficultyTextView.setText(dataStructureAlgorithm.getDifficulty().toString());
        binding.iconImageView.setImageResource(dataStructureAlgorithm.getIcon());

        //Setting title
        setTitle(dataStructureAlgorithm.getName() + " Visualizer");
        BinaryTreeNode root = null;
        //Binary Tree
        for (int i = 0; i < 5; i++) {
            int rand = new Random().nextInt() % 500;
            root = BinaryTreeNode.insertNode(root, rand);
        }
        initialView(root);


        //button click listener
        BinaryTreeNode finalRoot = root;
        binding.visualizeButton.setOnClickListener(v -> {
            //clear all views of the linear Layout
            clearLayout();
            initialView(finalRoot);

            arr = new ArrayList<>();
            steps = 0;

            prefixHelper(finalRoot, finalRoot);
        });
    }

    private void clearLayout() {
        binding.holderLinearLayout.removeAllViews();
    }


    private void prefixHelper(BinaryTreeNode finalRoot, BinaryTreeNode root) {
        if (root == null) {
            //Generating Visuals
            StepCardBuilder stepCardBuilder = new StepCardBuilder(getApplicationContext());
            stepCardBuilder.setCardTitle(String.format("Step %d", ++steps));
            //Description
            stepCardBuilder.setCardDescription("We reach a null point.\nTherefore we move back to the parent node");
            binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());
            return;
        }

        // NODE VISUAL + CALL
        {
            //adding the middle value
            arr.add(root.data);
            //Generating Visuals
            StepCardBuilder stepCardBuilder = new StepCardBuilder(getApplicationContext());
            stepCardBuilder.setCardTitle(String.format("Step %d", ++steps));
            //Adding view to the holder of the Step Card
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCardBuilder.getDataNodeHolder().addView(binarySearchTreeBuilder.generateBinarySearchTree(finalRoot, root.data));
            //Description
            stepCardBuilder.setCardDescription(String.format("%d is now added to the traversed list.\nCurrent list : %s \n\nNow we move to the left subtree.", root.data, arr.toString()));
            binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());

        }


        //LEFT SUBTREE VISUAL + CALL
        {
            //RECURSIVE LEFT SUBTREE CALL
            prefixHelper(finalRoot, root.leftNode);

            //Generating Visuals
            StepCardBuilder stepCardBuilder = new StepCardBuilder(getApplicationContext());
            stepCardBuilder.setCardTitle(String.format("Step %d", ++steps));
            //Adding view to the holder of the Step Card
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCardBuilder.getDataNodeHolder().addView(binarySearchTreeBuilder.generateBinarySearchTree(finalRoot, root.data));
            //Description
            stepCardBuilder.setCardDescription(String.format("We move to the right subtree.\nCurrent list : %s", arr.toString()));
            binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());
        }


        //RIGHT SUBTREE VISUAL + CALL
        {
            //RECURSIVE RIGHT SUBTREE CALL
            prefixHelper(finalRoot, root.rightNode);
        }
    }

    private void initialView(BinaryTreeNode root) {
        StepCardBuilder stepCardBuilder = new StepCardBuilder(getApplicationContext());
        stepCardBuilder.setCardTitle("Initial Binary Search Tree");
        stepCardBuilder.setCardDescription("This is the initial Binary Search Tree.");

        //Generating Tree for Step Card
        BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
        stepCardBuilder.getDataNodeHolder().addView(binarySearchTreeBuilder.generateBinarySearchTree(root, Integer.MAX_VALUE));

        //Adding view to the holder of the Step Card
        binding.holderLinearLayout.addView(stepCardBuilder.getStepCard());
    }
}