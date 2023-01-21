package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.preorder;

import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class BSTPreorderTheory extends AlgorithmTheory {
    public BSTPreorderTheory() {
        super.theory = "Linear data structures such as stack, array, queue, etc., only have one way to traverse the data. But in hierarchical data structures such as tree, there are multiple ways to traverse the data. Here we will discuss another way to traverse the tree data structure, i.e., pre order traversal.";

        super.algorithm = "1) Visit the root node.\n" +
                "\n" +
                "2) Visit all the nodes in the left subtree.\n" +
                "\n" +
                "3) Visit all the nodes in the right subtree.";

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
                "void PrefixTraversal(TreeNode *root)\n" +
                "{\n" +
                "    if (root == NULL)\n" +
                "        return;\n" +
                "    cout << root->data << \" \";\n" +
                "    PrefixTraversal(root->left);\n" +
                "    PrefixTraversal(root->right);\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "N";
    }
}
