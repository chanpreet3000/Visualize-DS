package com.example.visualizeds.data_structure.classes;

import android.util.Log;
import android.view.View;

public class BinaryTreeNode {
    public final BinaryTreeNode parentNode;
    public final int data;
    public BinaryTreeNode leftNode;
    public BinaryTreeNode rightNode;
    public View nodeView;

    public BinaryTreeNode(BinaryTreeNode parentNode, Integer data) {
        this.data = data;
        this.parentNode = parentNode;
        this.leftNode = null;
        this.rightNode = null;
        this.nodeView = null;
    }

    public static BinaryTreeNode insertNode(BinaryTreeNode root, Integer data) {
        if (root == null)
            return new BinaryTreeNode(null, data);

        if (data < root.data) {
            if (root.leftNode == null)
                root.leftNode = new BinaryTreeNode(root, data);
            else
                root.leftNode = insertNode(root.leftNode, data);
        } else if (data > root.data) {
            if (root.rightNode == null)
                root.rightNode = new BinaryTreeNode(root, data);
            else
                root.rightNode = insertNode(root.rightNode, data);
        }
        return root;
    }

    public static void inOrderTraversal(BinaryTreeNode root) {
        if (root == null) return;
        inOrderTraversal(root.leftNode);
        Log.d("HELLOWORLD", "inOrderTraversal: " + root.data);
        inOrderTraversal(root.rightNode);
    }

}