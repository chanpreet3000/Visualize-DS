package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.insertion;

import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class DLLInsertionTheory extends AlgorithmTheory {
    public DLLInsertionTheory() {
        super.theory = "Doubly linked list is a complex type of linked list in which a node contains a pointer to the previous as well as the next node in the sequence. Therefore, in a doubly linked list, a node consists of three parts: node data, pointer to the next node in sequence (next pointer) , pointer to the previous node (previous pointer).";

        super.algorithm =     "1) Create a new node and assign its data value and its next node to NULL.\n" +
                "\n" +
                "2) If the list is empty, change the head node to the new node.\n" +
                "\n" +
                "3) If not then traverse till the last node.\n" +
                "\n" +
                "4) Assign the last node’s next pointer to this new node.\n" +
                "\n" +
                "5) Now, the new node has become the last node.";
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
                "Node *Insertion(Node *head, int data)\n" +
                "{\n" +
                "    Node *newNode = new Node(data);\n" +
                "    if (head == NULL)\n" +
                "        return head = newNode;\n" +
                "\n" +
                "    Node *temp = head;\n" +
                "    while (temp->next != NULL)\n" +
                "    {\n" +
                "        temp = temp->next;\n" +
                "    }\n" +
                "    temp->next = newNode;\n" +
                "    newNode->prev = temp;\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "1";
    }
}
