package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.deletion;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.builder.BSTBuilder;
import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BSTNode;
import com.chanpreet.visualizeds.databinding.ItemErrorAlertCardBinding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCard2Binding;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class BSTDeletionActivity extends VisualizerActivity {

    private EditText targetEditText;
    BSTNode root = null;

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
        {
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
        //Creating UI
        ItemVisualizeInputCardBinding binding2 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Enter element to be deleted");
        binding2.editText.setHint("Enter element to be deleted");
        binding2.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
        targetEditText = binding2.editText;

    }

    @Override
    public Map<String, Object> getVisualizationInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("INORDER", BSTNode.inOrder(root));
        map.put("TARGET", stringToArray(targetEditText.getText().toString()));
        return map;
    }

    @Override
    public void visualize() {
        //getting array and target
        int target = Integer.parseInt(targetEditText.getText().toString());

        List<StepCard> stepCardList = new ArrayList<>();
        int steps = 0;
        BSTNode temp = root;
        boolean found = false;
        while (temp != null) {
            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));

            //Adding view to the holder of the Step Card
            stepCard.setData(BSTBuilder.build(getApplicationContext(), root, temp.data));

            //Description
            if (target == temp.data) {
                stepCard.setDescription(Arrays.asList(String.format(Locale.US, "%d == %d", target, temp.data),
                        "The element to be deleted is found."));
            } else if (target < temp.data) {
                stepCard.setDescription(Arrays.asList(String.format(Locale.US, "%d < %d", target, temp.data),
                        "Therefore we move to the left subtree."));
            } else {
                stepCard.setDescription(Arrays.asList(String.format(Locale.US, "%d > %d", target, temp.data),
                        "Therefore we move to the right subtree."));
            }
            stepCardList.add(stepCard);

            //BST properties.
            if (target == temp.data) {
                found = true;
                break;
            } else if (target < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        StepCard stepCard = new StepCard();
        if (!found) {
            //Final Step Card
            stepCard.setTitle("Binary Search Tree After Deletion.");
            stepCard.setDescription(Arrays.asList(
                    String.format(Locale.US, "The Binary Search Tree does not contain %d.", target),
                    "Therefore, No need for deletion."));
            //Generating Tree for Step Card
            //Adding view to the holder of the Step Card
        } else {
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            if (temp.left == null && temp.right == null) {
                stepCard.setDescription(Arrays.asList("There exists no left and right subtree.",
                        "Therefore the node can be directly deleted."));
            } else if (temp.left != null && temp.right == null) {
                stepCard.setDescription(Arrays.asList("There exists no right subtree.",
                        "Therefore the node can be directly deleted and the left subtree can be directly attached to the node."));
            } else if (temp.left == null) {
                stepCard.setDescription(Arrays.asList("There exists no left subtree.",
                        "Therefore the node can be directly deleted and the right subtree can be directly attached to the node."));
            } else {
                stepCard.setDescription(Arrays.asList("There exists both left and right subtree.",
                        "Therefore the node cannot be directly deleted.",
                        "Targeted node will be replaced by the Inorder Successor or Predecessor of the targeted node and the Inorder Successor or Predecessor will be deleted."));
            }
            stepCard.setData(BSTBuilder.build(this, root, -999));
            stepCardList.add(stepCard);
            this.root = deletionBST(root, target);

            //printing final tree.
            stepCard = new StepCard();
            stepCard.setTitle("Binary Search Tree After Deletion.");
            stepCard.setDescription(Arrays.asList(String.format(Locale.US, "This is the final Binary Search Tree after deleting %d.", target)));
            //Generating Tree for Step Card
            //Adding view to the holder of the Step Card
        }
        stepCard.setData(BSTBuilder.build(getApplicationContext(), root, target));
        stepCardList.add(stepCard);

        adapter.setStepCardList(stepCardList);
    }

    private BSTNode deletionBST(BSTNode finalRoot, int target) {
        if (finalRoot == null) return null;
        if (finalRoot.data == target) {
            if (finalRoot.left == null && finalRoot.right == null) {
                finalRoot = null;
            } else if (finalRoot.left != null && finalRoot.right == null) {
                finalRoot = finalRoot.left;
            } else if (finalRoot.left == null) {
                finalRoot = finalRoot.right;
            } else {
                BSTNode temp = finalRoot.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                finalRoot.data = temp.data;
                finalRoot.left = deletionBST(finalRoot.left, temp.data);
            }
        } else if (target < finalRoot.data) {
            finalRoot.left = deletionBST(finalRoot.left, target);
        } else {
            finalRoot.right = deletionBST(finalRoot.right, target);
        }
        return finalRoot;
    }
}