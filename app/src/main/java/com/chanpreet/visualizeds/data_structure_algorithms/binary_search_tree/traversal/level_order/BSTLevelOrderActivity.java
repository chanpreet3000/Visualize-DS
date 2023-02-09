package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.level_order;

import android.view.View;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.BSTBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BSTNode;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class BSTLevelOrderActivity extends VisualizerActivity {

    BSTNode root = null;
    int steps = 0;

    @Override
    public void onCreate() {
        for (int i = 0; i < 7; i++) {
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
    public void generateInputUI() {
        ItemVisualizeInputCard2Binding binding1 = ItemVisualizeInputCard2Binding.inflate(getLayoutInflater());
        binding1.textView.setText("Generate BST");
        binding1.editText.setVisibility(View.GONE);
        binding1.button.setText("Generate");
        binding1.button.setOnClickListener(v -> {
            root = null;
            this.onCreate();
        });

        ItemErrorAlertCardBinding binding2 = ItemErrorAlertCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Generating new BST will erase previous visualization.");

        binding.inputLinearLayout.addView(binding2.getRoot());
        binding.inputLinearLayout.addView(binding1.getRoot());
    }

    @Override
    public Map<String, Object> getVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("INORDER", BSTNode.inOrder(root));
        return map;
    }

    @Override
    public void visualize() {
        steps = 0;
        //clear all views of the linear Layout

        List<StepCard> stepCardList = new ArrayList<>();

        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);

        StepCard stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setData(BSTBuilder.build(this, root, root.data));
        stepCard.setDescription(
                Arrays.asList(
                        "Adding the root to the queue.",
                        "Queue : " + queue));
        stepCardList.add(stepCard);

        List<Integer> levelOrderList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                BSTNode front = queue.poll();
                assert front != null;
                levelOrderList.add(front.data);

                List<String> description = new ArrayList<>();
                description.add(String.format(Locale.US, "Front Element from queue : %d", front.data));
                if (front.left == null) {
                    description.addAll(Arrays.asList(
                            "Left node is NULL",
                            "Therefore, left node will not be added to the queue"));
                } else {
                    queue.add(front.left);
                    description.addAll(Arrays.asList(
                            "Left node is NOT NULL",
                            String.format(Locale.US, "Therefore, left node (%d) will be added to the queue", front.left.data)));
                }
                if (front.right == null) {
                    description.addAll(Arrays.asList(
                            "Right node is NULL",
                            "Therefore, right node will not be added to the queue"));
                } else {
                    queue.add(front.right);
                    description.addAll(Arrays.asList(
                            "Right node is NOT NULL",
                            String.format(Locale.US, "Therefore, right node (%d) will be added to the queue", front.right.data)));
                }

                description.addAll(Arrays.asList(
                        String.format(Locale.US, "Queue : %s", queue),
                        String.format(Locale.US, "Level Order Traversal : %s", levelOrderList)));

                //
                stepCard = new StepCard();
                stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
                stepCard.setData(BSTBuilder.build(this, root, front.data));
                stepCard.setDescription(description);
                stepCardList.add(stepCard);
            }
        }

        stepCard = new StepCard();
        stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
        stepCard.setData(BSTBuilder.build(this, root, -999));
        stepCard.setDescription(
                Arrays.asList(
                        String.format(Locale.US, "Queue : %s", queue),
                        "Queue is Empty.",
                        "Therefore we traversed the Binary Search Tree.",
                        String.format(Locale.US, "Level Order Traversal : %s", levelOrderList)));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }
}