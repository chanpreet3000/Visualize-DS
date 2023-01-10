package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.deletion;

import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chanpreet.visualizeds.activity.VisualizerActivity;
import com.chanpreet.visualizeds.adapter.StepCardAdapter;
import com.chanpreet.visualizeds.builder.BinarySearchTreeBuilder;
import com.chanpreet.visualizeds.classes.StepCard;
import com.chanpreet.visualizeds.classes.data_structure_containers.BinaryTreeNode;
import com.chanpreet.visualizeds.databinding.ItemVisualizeInputCardBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BSTDeletionActivity extends VisualizerActivity {

    private EditText targetEditText;
    BinaryTreeNode root = null;

    @Override
    public void onCreate() {
        super.onCreate();

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

        List<StepCard> stepCardList = new ArrayList<>();
        int steps = 0;
        BinaryTreeNode temp = root;
        boolean found = false;
        while (temp != null) {
            //Generating Visuals
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));

            //Adding view to the holder of the Step Card
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCard.setData(binarySearchTreeBuilder.generateBinarySearchTree(root, temp.data));

            //Description
            if (target == temp.data) {
                stepCard.setDescription("The element to be deleted is found.");
            } else if (target < temp.data) {
                stepCard.setDescription(String.format(Locale.US, "%d is less than %d.\nTherefore we move to the left subtree.", target, temp.data));
            } else {
                stepCard.setDescription(String.format(Locale.US, "%d is greater than %d.\nTherefore we move to the right subtree.", target, temp.data));
            }
            stepCardList.add(stepCard);

            //BST properties.
            if (target == temp.data) {
                found = true;
                break;
            } else if (target < temp.data) {
                temp = temp.leftNode;
            } else {
                temp = temp.rightNode;
            }
        }
        if (!found) {
            //Final Step Card
            StepCard stepCard = new StepCard();
            stepCard.setTitle("Binary Search Tree After Deletion.");
            stepCard.setDescription(String.format(Locale.US, "The Binary Search Tree does not contain %d.\nTherefore, No need for deletion.\nThis is the final Binary Search Tree.", target));
            //Generating Tree for Step Card
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCard.setData(binarySearchTreeBuilder.generateBinarySearchTree(root, target));
            //Adding view to the holder of the Step Card
            stepCardList.add(stepCard);
        } else {
            StepCard stepCard = new StepCard();
            stepCard.setTitle(String.format(Locale.US, "Step %d", ++steps));
            if (temp.leftNode == null && temp.rightNode == null) {
                stepCard.setDescription("There exists no left and right subtree.\nTherefore the node can be directly deleted.");
            } else if (temp.leftNode != null && temp.rightNode == null) {
                stepCard.setDescription("There exists no right subtree.\nTherefore the node can be directly deleted and the left subtree can be directly attached to the node.");
            } else if (temp.leftNode == null) {
                stepCard.setDescription("There exists no left subtree.\nTherefore the node can be directly deleted and the right subtree can be directly attached to the node.");
            } else {
                stepCard.setDescription("There exists both left and right subtree.\nTherefore the node cannot be directly deleted.\nTargeted node will be replaced by the Inorder Successor or Predecessor of the targeted node.");
            }
            stepCardList.add(stepCard);
            this.root = deletionBST(root, target);

            //printing final tree.
            stepCard = new StepCard();
            stepCard.setTitle("Binary Search Tree After Deletion.");
            stepCard.setDescription(String.format(Locale.US, "This is the final Binary Search Tree after deleting %d.", target));
            //Generating Tree for Step Card
            BinarySearchTreeBuilder binarySearchTreeBuilder = new BinarySearchTreeBuilder(this);
            stepCard.setData(binarySearchTreeBuilder.generateBinarySearchTree(this.root, target));
            //Adding view to the holder of the Step Card
            stepCardList.add(stepCard);
        }

        adapter.setStepCardList(stepCardList);
    }

    private BinaryTreeNode deletionBST(BinaryTreeNode finalRoot, int target) {
        if (finalRoot == null) return null;
        if (finalRoot.data == target) {
            if (finalRoot.leftNode == null && finalRoot.rightNode == null) {
                finalRoot = null;
            } else if (finalRoot.leftNode != null && finalRoot.rightNode == null) {
                finalRoot = finalRoot.leftNode;
            } else if (finalRoot.leftNode == null) {
                finalRoot = finalRoot.rightNode;
            } else {
                BinaryTreeNode temp = finalRoot.leftNode;
                while (temp.rightNode != null) {
                    temp = temp.rightNode;
                }
                finalRoot.data = temp.data;
                finalRoot.leftNode = deletionBST(finalRoot.leftNode, temp.data);
            }
        } else if (target < finalRoot.data) {
            finalRoot.leftNode = deletionBST(finalRoot.leftNode, target);
        } else {
            finalRoot.rightNode = deletionBST(finalRoot.rightNode, target);
        }
        return finalRoot;
    }

    @Override
    public void generateInputUI() {
        //Creating UI
        ItemVisualizeInputCardBinding binding2 = ItemVisualizeInputCardBinding.inflate(getLayoutInflater());
        binding2.textView.setText("Enter element to be deleted");
        binding2.editText.setHint("Enter element to be deleted");
        binding2.editText.setInputType(InputType.TYPE_CLASS_PHONE);

        //adding UI
        binding.inputLinearLayout.addView(binding2.getRoot());

        //caching UI
        targetEditText = binding2.editText;
        //
        adapter = new StepCardAdapter(getApplicationContext());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(4);
    }
}