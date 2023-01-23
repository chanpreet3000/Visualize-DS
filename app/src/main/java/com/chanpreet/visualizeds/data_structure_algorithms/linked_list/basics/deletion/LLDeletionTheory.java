package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.deletion;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class LLDeletionTheory extends AlgorithmTheory {
    public LLDeletionTheory() {
        super.theory = TextBuilder.makeBulletList(
                "Deleting a node from the beginning of the list is the simplest operation of all.",
                "It just need a few adjustments in the node pointers.",
                "Since the first node of the list is to be deleted, therefore, we just need to make the head, point to the next of the head.");

        super.algorithm =
                TextBuilder.makeOrderedList("If the head node has the given key, make the head node points to the second node and free its memory.",
                        "Otherwise, From the current node, check whether the next node has the given key",
                        "if yes, make the current->next = current->next->next and free the memory.",
                        "else, update the current node to the next and do the above process (from step 2) till the last node.");

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
                "Node *Deletion(Node *head, int target)\n" +
                "{\n" +
                "    if (head == NULL)\n" +
                "        return head;\n" +
                "    if (head->data == target)\n" +
                "        return head = head->next;\n" +
                "    Node *currentPtr = head;\n" +
                "    while (currentPtr->next != NULL)\n" +
                "    {\n" +
                "        if (currentPtr->next->data == target)\n" +
                "        {\n" +
                "            currentPtr->next = currentPtr->next->next;\n" +
                "            return head;\n" +
                "        }\n" +
                "        currentPtr = currentPtr->next;\n" +
                "    }\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "1";
    }
}
