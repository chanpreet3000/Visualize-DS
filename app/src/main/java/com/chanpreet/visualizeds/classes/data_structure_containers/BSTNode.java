package com.chanpreet.visualizeds.classes.data_structure_containers;

import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BSTNode {
    public int data;
    public BSTNode left, right;
    public View nodeView;

    public BSTNode(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.nodeView = null;
    }

    public static BSTNode insertNode(BSTNode root, Integer data) {
        if (root == null)
            return new BSTNode(data);
        if (data == root.data) {
            return root;
        } else if (data < root.data) {
            root.left = insertNode(root.left, data);
        } else {
            root.right = insertNode(root.right, data);
        }
        return root;
    }

    public static List<Integer> inOrder(BSTNode root) {
        List<Integer> arr = new ArrayList<>();
        inOrderHelper(root, arr);
        return arr;
    }

    private static void inOrderHelper(BSTNode root, List<Integer> arr) {
        if (root == null) return;
        inOrderHelper(root.left, arr);
        arr.add(root.data);
        inOrderHelper(root.right, arr);
    }

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}