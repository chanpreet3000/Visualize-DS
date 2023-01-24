package com.chanpreet.visualizeds.data_structure_algorithms.doubly_linked_list.basics.deletion;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class DLLDeletionTheory extends AlgorithmTheory {
    public DLLDeletionTheory() {
        super.theory =
                TextBuilder.makeBulletList(
                        "Deletion in a doubly linked list is similar to the singly linked list. The list is traversed to find the element to be deleted.",
                        "Once the element have been found we make the next pointer of the previous node to the next of the node to be deleted and the prev pointer of the next node of the node to be deleted to the previous node of the node to be deleted.");

        super.algorithm = TextBuilder.makeOrderedList(
                "If the head node has the given key, make the head node points to the second node and free its memory.",
                "Otherwise, From the current node, check whether the next node has the given key",
                "if yes, check if current->next->next is not NULL.",
                "If it is not NULL then make current->next->next->prev = current and current->next = current->next->next and free the memory.",
                "If it is NULL just make current->next = current->next->next and free the memory.",
                "else, update the current node to the next and do the above process (from step 2) till the last node.");

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
                "Node *Deletion(Node *head, int target)\n" +
                "{\n" +
                "    if (head == NULL)\n" +
                "        return head;\n" +
                "    if (head->data == target)\n" +
                "        return head = head->next;\n" +
                "    Node *temp = head;\n" +
                "    while (temp->next != NULL)\n" +
                "    {\n" +
                "        if (temp->next->data == target)\n" +
                "        {\n" +
                "            if (temp->next->next != NULL)\n" +
                "                temp->next->next->prev = temp;\n" +
                "            temp->next = temp->next->next;\n" +
                "        }\n" +
                "        temp = temp->next;\n" +
                "    }\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "1";
    }
}
