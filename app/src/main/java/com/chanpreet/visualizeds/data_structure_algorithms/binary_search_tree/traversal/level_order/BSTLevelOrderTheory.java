package com.chanpreet.visualizeds.data_structure_algorithms.binary_search_tree.traversal.level_order;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class BSTLevelOrderTheory extends AlgorithmTheory {
    public BSTLevelOrderTheory() {
        super.theory =
                TextBuilder.makeBulletList(
                        "Linear data structures such as stack, array, queue, etc., only have one way to traverse the data.",
                        "But in hierarchical data structures such as tree, there are multiple ways to traverse the data.",
                        "Here we will discuss another way to traverse the tree data structure, i.e., Level Order traversal.");

        super.algorithm = TextBuilder.makeOrderedList("Create an empty queue q and push root in q.",
                "Run While loop until q is not empty.",
                "Initialize temp_node = q.front() and print temp_node->data.",
                "Push temp_node’s children i.e. temp_node -> left then temp_node -> right to q",
                "Pop front node from q.",
                "Repeat from step 2");

        super.code = "#include <bits/stdc++.h>\n" +
                "using namespace std;\n" +
                "\n" +
                "class TreeNode\n" +
                "{\n" +
                "public:\n" +
                "    int data;\n" +
                "    TreeNode *left, *right;\n" +
                "    TreeNode(int _data)\n" +
                "    {\n" +
                "        left = right = NULL;\n" +
                "        data = _data;\n" +
                "    }\n" +
                "};\n" +
                "\n" +
                "void LevelOrderTraversal(TreeNode *root)\n" +
                "{\n" +
                "    queue<TreeNode *> queue;\n" +
                "    while (queue.empty())\n" +
                "    {\n" +
                "        int n = queue.size();\n" +
                "        for (int i = 0; i < n; i++)\n" +
                "        {\n" +
                "            TreeNode *front = queue.front();\n" +
                "            queue.pop();\n" +
                "\n" +
                "            cout << front->data << \", \";\n" +
                "            \n" +
                "            if (front->left != NULL)\n" +
                "            {\n" +
                "                queue.push(front->left);\n" +
                "            }\n" +
                "            \n" +
                "            if (front->right != NULL)\n" +
                "            {\n" +
                "                queue.push(front->right);\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "N";
    }
}