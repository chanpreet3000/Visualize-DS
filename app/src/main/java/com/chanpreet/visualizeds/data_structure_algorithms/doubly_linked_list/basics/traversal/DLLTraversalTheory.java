package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.traversal;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class DLLTraversalTheory extends AlgorithmTheory {
    public DLLTraversalTheory() {
        super.theory =
                TextBuilder.makeBulletList("A Doubly linked list is used in navigation systems or to represent a classic deck of cards.",
                        "A Doubly linked list is a bidirectional linked list; i.e., you can traverse it from head to tail node or tail to head node.",
                        "Unlike singly-linked lists, its node has an extra pointer that points at the last node.");

        super.algorithm =
                TextBuilder.makeOrderedList("Initialize temp = head.",
                        "If temp is NULL we stop the traversal (EXIT).",
                        "If temp is not NULL we now go to the next node by setting temp = next pointer of the current temp ( temp = temp->next).",
                        "Repeat Step 2.");
        super.code = "class Node\n" +
                "{\n" +
                "public:\n" +
                "    int data;\n" +
                "    Node *next, *prev;\n" +
                "    Node(int _data)\n" +
                "    {\n" +
                "        data = _data;\n" +
                "        next = NULL;\n" +
                "        prev = NULL;\n" +
                "    }\n" +
                "};\n" +
                "\n" +
                "void Traversal(Node *head)\n" +
                "{\n" +
                "    Node *temp = head;\n" +
                "    while (temp != NULL)\n" +
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
