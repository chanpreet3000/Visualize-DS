package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.insertion;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class BSTInsertionTheory extends AlgorithmTheory {
    public BSTInsertionTheory() {
        super.theory =
                TextBuilder.makeBulletList(
                        "Binary Search Tree is a node-based binary tree data structure which has the following properties:",
                        "The left subtree of a node contains only nodes with keys lesser than the node’s key.",
                        "The right subtree of a node contains only nodes with keys greater than the node’s key.",
                        "The left and right subtree each must also be a binary search tree.",
                        "There must be no duplicate nodes.");

        super.algorithm = TextBuilder.makeOrderedList("Start from the root. ",
                "Compare the searching element with root, if less than root, then recursively call left subtree, else recursively call right subtree.",
                "If the element to search is found anywhere, return true, else return false.",
                "We start searching a key from the root until we hit a leaf node. Once a leaf node is found, the new node is added as a child of the leaf node.",
                "If the key is already present we will not insert the key.");

        super.code = "class TreeNode\n" +
                "{\n" +
                "public:\n" +
                "    int data;\n" +
                "    TreeNode *left, *right;\n" +
                "    TreeNode(int _data)\n" +
                "    {\n" +
                "        data = _data;\n" +
                "        left = NULL;\n" +
                "        right = NULL;\n" +
                "    }\n" +
                "};\n" +
                "\n" +
                "TreeNode *Insertion(TreeNode *root, int target)\n" +
                "{\n" +
                "    if (root == NULL)\n" +
                "        return new TreeNode(target);\n" +
                "    if (target < root->data)\n" +
                "        root->left = Insertion(root->left, target);\n" +
                "    else if (target > root->data)\n" +
                "        root->right = Insertion(root->right, target);\n" +
                "    return root;\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "Log N";
        super.bestCase = "1";
    }
}
