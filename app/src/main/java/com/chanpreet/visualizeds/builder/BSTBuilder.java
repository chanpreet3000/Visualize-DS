package com.chanpreet.visualizeds.builder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chanpreet.visualizeds.classes.data_structure_containers.BSTNode;
import com.chanpreet.visualizeds.R;

public class BSTBuilder {
    public static View build(Context context, BSTNode root, int target) {
        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        root = generateBinarySearchTreeHelper(context, root, target);
        root = generateBinarySearchTreeConstraintHelper(root);
        constraintLayout.addView(root.nodeView);
        return constraintLayout;
    }

    private static BSTNode generateBinarySearchTreeHelper(Context context, BSTNode root, int target) {
        if (root == null) return null;
        //Generating a view of the BST Node.
        root.nodeView = LayoutInflater.from(context).inflate(R.layout.item_binary_search_tree_node, null);

        //Adding Values to the nodes.
        ((TextView) root.nodeView.findViewById(R.id.data_text_view)).setText(String.valueOf(root.data));
        if (root.right == null || root.left == null)
            root.nodeView.findViewById(R.id.divider).setVisibility(View.GONE);

        if (root.data == target) {
            root.nodeView.findViewById(R.id.card_view).setForeground(AppCompatResources.getDrawable(context, R.drawable.red_border));
        }

        //recursive fn for left and right subtree.
        root.left = generateBinarySearchTreeHelper(context, root.left, target);
        root.right = generateBinarySearchTreeHelper(context, root.right, target);
        return root;
    }

    private static BSTNode generateBinarySearchTreeConstraintHelper(BSTNode root) {
        if (root == null) return null;
        //Recursive calls
        root.left = generateBinarySearchTreeConstraintHelper(root.left);
        root.right = generateBinarySearchTreeConstraintHelper(root.right);

        View currentView = root.nodeView;
        LinearLayout linearLayout = currentView.findViewById(R.id.nodeHolder);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        if (root.left != null) {
            linearLayout.addView(root.left.nodeView, params);
        }
        if (root.right != null) {
            linearLayout.addView(root.right.nodeView, params);
        }
        root.nodeView = currentView;
        return root;
    }

}
