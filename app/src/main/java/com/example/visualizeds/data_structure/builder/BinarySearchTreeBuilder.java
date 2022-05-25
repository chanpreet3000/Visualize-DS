package com.example.visualizeds.data_structure.builder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.visualizeds.R;
import com.example.visualizeds.data_structure.classes.BinaryTreeNode;

import java.util.Random;

public class BinarySearchTreeBuilder {
    private final Context context;
    private final ConstraintLayout constraintLayout;

    @SuppressLint("ResourceType")
    public BinarySearchTreeBuilder(Context context) {
        this.context = context;

        //Generating constraint layout.
        constraintLayout = new ConstraintLayout(context);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        constraintLayout.setId(150);
        constraintLayout.setLayoutParams(layoutParams);
    }

    public ConstraintLayout generateBinarySearchTree(BinaryTreeNode root) {
        root = generateBinarySearchTreeHelper(root);
        generateBinarySearchTreeConstraintHelper(root);
        return constraintLayout;
    }

    private BinaryTreeNode generateBinarySearchTreeHelper(BinaryTreeNode root) {
        if (root == null) return null;

        //Generating a view of the BST Node.
        root.nodeView = LayoutInflater.from(context).inflate(R.layout.item_binary_search_tree_node, constraintLayout, false);
        int id = new Random().nextInt();
        root.nodeView.setId(id % Integer.MAX_VALUE);

        //recursive fn for left and right subtree.
        root.leftNode = generateBinarySearchTreeHelper(root.leftNode);
        root.rightNode = generateBinarySearchTreeHelper(root.rightNode);
        return root;
    }

    private void generateBinarySearchTreeConstraintHelper(BinaryTreeNode root) {
        if (root == null) return;
        ConstraintLayout.LayoutParams childParams = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //Calculating constraints of the view
        if (root.parentNode == null) {
            childParams.startToStart = constraintLayout.getId();
            childParams.endToEnd = constraintLayout.getId();
            childParams.topToTop = constraintLayout.getId();
            childParams.topMargin = 32;
        } else {
            childParams.topToBottom = root.parentNode.nodeView.getId();

            if (root.data < root.parentNode.data) {
                if (root.parentNode.rightNode != null) {
                    childParams.endToStart = root.parentNode.rightNode.nodeView.getId();
                    childParams.rightMargin = 0;
                } else {
                    childParams.endToEnd = root.parentNode.nodeView.getId();
                }
                childParams.startToStart = root.parentNode.nodeView.getId();

//                if (root.parentNode.parentNode == null) {
//                    childParams.rightMargin = 600;
//                } else {
//                    childParams.rightMargin = 350;
//                }
            } else {
                if (root.parentNode.leftNode != null) {
                    childParams.startToEnd = root.parentNode.leftNode.nodeView.getId();
                    childParams.leftMargin = 0;
                } else {
                    childParams.startToStart = root.parentNode.nodeView.getId();
                }
                childParams.endToEnd = root.parentNode.nodeView.getId();
//                if (root.parentNode.parentNode == null) {
//                    childParams.leftMargin = 600;
//                } else {
//                    childParams.leftMargin = 350;
//                }
            }
        }

        //adding values to the BST Node UI.
        ((TextView) root.nodeView.findViewById(R.id.dataNodeDataTextView)).setText(String.valueOf(root.data));
        if (root.leftNode == null)
            ((ImageView) root.nodeView.findViewById(R.id.nodeLeftPointer)).setVisibility(View.INVISIBLE);

        if (root.rightNode == null)
            ((ImageView) root.nodeView.findViewById(R.id.nodeRightPointer)).setVisibility(View.INVISIBLE);


        //Setting the layout parameters
        constraintLayout.addView(root.nodeView, childParams);

        //Recursive fn call
        generateBinarySearchTreeConstraintHelper(root.leftNode);
        generateBinarySearchTreeConstraintHelper(root.rightNode);
    }
}
