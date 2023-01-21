package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.level_order;

import android.view.View;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.BSTBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BSTNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Random;

public class BSTLevelOrderActivity extends VisualizerActivity {

    BSTNode root = null;
    int steps = 0;

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
        steps = 0;
        //clear all views of the linear Layout
        binding.holderLinearLayout.setVisibility(View.VISIBLE);


        List<StepCard> stepCardList = new ArrayList<>();


        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);

        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setData(BSTBuilder.build(this, root, root.data));
        stepCard.setDescription("Adding the root to the queue.\nQueue : " + queue);
        stepCardList.add(stepCard);

        List<Integer> levelOrderList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                BSTNode front = queue.poll();
                assert front != null;
                levelOrderList.add(front.data);

                StringBuilder description = new StringBuilder(String.format(Locale.US, "Getting front element from the queue.\nAdding %d to the Level Order List.", front.data));

                if (front.left == null) {
                    description.append("\nLeft node is null therefore we wont add it to the queue");
                } else {
                    queue.add(front.left);
                    description.append(String.format(Locale.US, "\nLeft node is NOT null therefore we add %d to the queue", front.left.data));
                }

                if (front.right == null) {
                    description.append("\nRight node is null therefore we wont add it to the queue");
                } else {
                    queue.add(front.right);
                    description.append(String.format(Locale.US, "\nRight node is NOT null therefore we add %d to the queue", front.right.data));
                }

                description.append("\nCurrent Queue : ").append(queue);
                description.append("\nCurrent Level Order List : ").append(levelOrderList);

                stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                stepCard.setData(BSTBuilder.build(this, root, front.data));
                stepCard.setDescription(description.toString());
                stepCardList.add(stepCard);
            }
        }

        stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setData(BSTBuilder.build(this, root, -999));
        stepCard.setDescription("Queue : " + queue + "\n\nQueue is Empty. Therefore we traversed the Binary Search Tree.\n\nLevel Order Traversal is " + levelOrderList);
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }

    @Override
    public void generateInputUI() {
    }
}