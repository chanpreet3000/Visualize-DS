package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chanpreet.visualizeds.classes.data_structure_containers.BinaryTreeNode;
import com.chanpreet.visualizeds.R;

public class BSTBuilder {
    public static View build(Context context, BinaryTreeNode root, int target) {
        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        root = generateBinarySearchTreeHelper(context, root, target);
        root = generateBinarySearchTreeConstraintHelper(root);
        constraintLayout.addView(root.nodeView);
        return constraintLayout;
    }

    private static BinaryTreeNode generateBinarySearchTreeHelper(Context context, BinaryTreeNode root, int target) {
        if (root == null) return null;
        //Generating a view of the BST Node.
        root.nodeView = LayoutInflater.from(context).inflate(R.layout.item_binary_search_tree_node, null);

        //Adding Values to the nodes.
        ((TextView) root.nodeView.findViewById(R.id.data_text_view)).setText(String.valueOf(root.data));
        if (root.rightNode == null || root.leftNode == null)
            root.nodeView.findViewById(R.id.divider).setVisibility(View.GONE);

        if (root.data == target) {
            root.nodeView.findViewById(R.id.card_view).setForeground(AppCompatResources.getDrawable(context, R.drawable.red_border));
        }

        //recursive fn for left and right subtree.
        root.leftNode = generateBinarySearchTreeHelper(context, root.leftNode, target);
        root.rightNode = generateBinarySearchTreeHelper(context, root.rightNode, target);
        return root;
    }

    private static BinaryTreeNode generateBinarySearchTreeConstraintHelper(BinaryTreeNode root) {
        if (root == null) return null;
        //Recursive calls
        root.leftNode = generateBinarySearchTreeConstraintHelper(root.leftNode);
        root.rightNode = generateBinarySearchTreeConstraintHelper(root.rightNode);

        View currentView = root.nodeView;
        LinearLayout linearLayout = currentView.findViewById(R.id.nodeHolder);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        if (root.leftNode != null) {
            linearLayout.addView(root.leftNode.nodeView, params);
        }
        if (root.rightNode != null) {
            linearLayout.addView(root.rightNode.nodeView, params);
        }
        root.nodeView = currentView;
        return root;
    }

}
