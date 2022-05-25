package com.example.visualizeds.data_structure.builder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
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
        constraintLayout.setLayoutParams(layoutParams);
    }

    public ConstraintLayout generateBinarySearchTree(BinaryTreeNode root) {
        root = generateBinarySearchTreeHelper(root);
        root = generateBinarySearchTreeConstraintHelper(root);
        constraintLayout.addView(root.nodeView);
        return constraintLayout;
    }

    private BinaryTreeNode generateBinarySearchTreeHelper(BinaryTreeNode root) {
        if (root == null) return null;
        //Generating a view of the BST Node.
        root.nodeView = LayoutInflater.from(context).inflate(R.layout.item_binary_search_tree_node, null);

        //recursive fn for left and right subtree.
        root.leftNode = generateBinarySearchTreeHelper(root.leftNode);
        root.rightNode = generateBinarySearchTreeHelper(root.rightNode);
        return root;
    }

    private BinaryTreeNode generateBinarySearchTreeConstraintHelper(BinaryTreeNode root) {
        if (root == null) return null;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        if (root.leftNode != null) {
            root.leftNode.nodeView.setLayoutParams(params);
            ((LinearLayout) root.nodeView.findViewById(R.id.nodeHolder)).addView(root.leftNode.nodeView);
        }
        if (root.rightNode != null) {
            root.rightNode.nodeView.setLayoutParams(params);
            ((LinearLayout) root.nodeView.findViewById(R.id.nodeHolder)).addView(root.rightNode.nodeView);
        }

        //adding values to the BST Node UI.
        ((TextView) root.nodeView.findViewById(R.id.dataNodeDataTextView)).setText(String.valueOf(root.data));
        if (root.leftNode == null)
            ((ImageView) root.nodeView.findViewById(R.id.nodeLeftPointer)).setVisibility(View.INVISIBLE);

        if (root.rightNode == null)
            ((ImageView) root.nodeView.findViewById(R.id.nodeRightPointer)).setVisibility(View.INVISIBLE);

        if(root.rightNode == null || root.leftNode == null){
            root.nodeView.findViewById(R.id.divider).setVisibility(View.GONE);
        }


        //Recursive calls
        root.leftNode = generateBinarySearchTreeConstraintHelper(root.leftNode);
        root.rightNode = generateBinarySearchTreeConstraintHelper(root.rightNode);
        return root;
    }

}
