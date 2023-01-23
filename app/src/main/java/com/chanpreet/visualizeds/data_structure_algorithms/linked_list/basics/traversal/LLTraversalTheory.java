package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.traversal;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class LLTraversalTheory extends AlgorithmTheory {
    public LLTraversalTheory() {
        super.theory = TextBuilder.makeBulletList(
                "Traversing is the most common operation that is performed in almost every scenario of singly linked list.",
                "Traversing means visiting each node of the list once in order to perform some operation on that.");

        super.algorithm =
                TextBuilder.makeOrderedList("Initialize temp = head.",
                "If temp is NULL we stop the traversal (EXIT)." ,
                "If temp is not NULL we now go to the next node by setting temp = next pointer of the current temp ( temp = temp->next).",
                "Repeat Step 2.");
        super.code = "class Node\n" +
                "{\n" +
                "public:\n" +
                "    int data;\n" +
                "    Node *next;\n" +
                "    Node(int _data)\n" +
                "    {\n" +
                "        data = _data;\n" +
                "        next = NULL;\n" +
                "    }\n" +
                "};\n" +
                "\n" +
                "void *Traversal(Node *head)\n" +
                "{\n" +
                "    // prints all nodes in the Linked List.\n" +
                "    Node *temp = head;\n" +
                "    while (temp->next != NULL)\n" +
                "    {\n" +
                "        cout << temp->data << \" \";\n" +
                "        temp = temp->next;\n" +
                "    }\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "N";
    }
}
