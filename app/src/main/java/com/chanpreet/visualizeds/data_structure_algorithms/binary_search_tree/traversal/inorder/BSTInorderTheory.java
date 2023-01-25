package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.inorder;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class BSTInorderTheory extends AlgorithmTheory {
    public BSTInorderTheory() {
        super.theory = TextBuilder.makeBulletList(
                "Linear data structures such as stack, array, queue, etc., only have one way to traverse the data.",
                "But in hierarchical data structures such as tree, there are multiple ways to traverse the data.",
                "Here we will discuss another way to traverse the tree data structure, i.e., inorder traversal.");

        super.algorithm = TextBuilder.makeOrderedList("Visit all the nodes in the left subtree.",
                "Visit the root node.",
                "Visit all the nodes in the right subtree.");
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
                "void InfixTraversal(TreeNode *root)\n" +
                "{\n" +
                "    if (root == NULL)\n" +
                "        return;\n" +
                "    InfixTraversal(root->left);\n" +
                "    cout << root->data << \" \";\n" +
                "    InfixTraversal(root->right);\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "N";
    }
}
