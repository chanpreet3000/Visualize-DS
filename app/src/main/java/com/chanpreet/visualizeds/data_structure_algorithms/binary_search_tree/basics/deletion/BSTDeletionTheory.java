package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.basics.deletion;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class BSTDeletionTheory extends AlgorithmTheory {
    public BSTDeletionTheory() {
        super.theory = TextBuilder.makeBulletList(
                "Delete function is used to delete the specified node from a binary search tree. However, we must delete a node from a binary search tree in such a way, that the property of binary search tree doesn't violate. There are three situations of deleting a node from binary search tree.",
                "The node to be deleted is a leaf node.",
                "The node to be deleted has only one child.",
                "The node to be deleted has two children.");
        super.algorithm =
                TextBuilder.makeOrderedList("We first traverse the BST to find the targeted node.",
                        "Once the node has been found we look at the no of the node's Chi",
                        "If the node has exactly 0 Children then the node is deleted directly and the memory is freed.",
                        "If the node has exactly 1 Children then the node is deleted directly and the memory is freed and the children is directly attached to the parent node.",
                        "If the node has exactly 2 Children then the node cannot deleted directly. We find the inorder successor of the node to be deleted.",
                        "We store the inorder successor value in the targeted node. ",
                        "We call the delete function recursively in the left subtree of the targeted node.");
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
                "TreeNode *deletion(TreeNode *root, int target)\n" +
                "{\n" +
                "    if (root == NULL)\n" +
                "        return root;\n" +
                "    if (target == root->data)\n" +
                "    {\n" +
                "        if (root->left == NULL && root->right == NULL)\n" +
                "        {\n" +
                "            delete root;\n" +
                "        }\n" +
                "        else if (root->left != NULL && root->right == NULL)\n" +
                "        {\n" +
                "            TreeNode *temp = root;\n" +
                "            root = root->left;\n" +
                "            delete temp;\n" +
                "        }\n" +
                "        else if (root->left == NULL && root->right != NULL)\n" +
                "        {\n" +
                "            TreeNode *temp = root;\n" +
                "            root = root->right;\n" +
                "            delete temp;\n" +
                "        }\n" +
                "        else if (root->left != NULL && root->right != NULL)\n" +
                "        {\n" +
                "            TreeNode *temp = root->left;\n" +
                "            while (temp->right != NULL)\n" +
                "            {\n" +
                "                temp = temp->right;\n" +
                "            }\n" +
                "            root->data = temp->data;\n" +
                "            root->left = deletion(root->left, root->data);\n" +
                "        }\n" +
                "    }\n" +
                "    else if (target < root->data)\n" +
                "    {\n" +
                "        root->left = deletion(root->left, target);\n" +
                "    }\n" +
                "    else\n" +
                "    {\n" +
                "        root->right = deletion(root->right, target);\n" +
                "    }\n" +
                "    return root;\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "Log N";
        super.bestCase = "1";
    }
}
