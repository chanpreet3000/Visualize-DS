package com.chanpreet.visualizeds.data_structure_algorithms.linked_list.basics.insertion;

import com.chanpreet.visualizeds.builder.TextBuilder;
import com.chanpreet.visualizeds.classes.AlgorithmTheory;

public class LLInsertionTheory extends AlgorithmTheory {
    public LLInsertionTheory() {
        super.theory =
                TextBuilder.makeBulletList(
                        "There are three possible positions where we can enter a new node in a linked list :- <br>"+
                        "\t\tInsertion at beginning<br>"+
                        "\t\tInsertion after nth position<br>"+
                        "\t\tInsertion at end",
                        "Generally by definition, if a new node is added it is added at the beginning of the linked list and not the end. So, we do not need to traverse the list every time for insertion");
        super.algorithm = TextBuilder.makeOrderedList(
                "Create a new node and assign its data value and its next node to NULL.",
                "If the list is empty, change the head node to the new node.",
                "If not then traverse till the last node.",
                "Assign the last node’s next pointer to this new node.",
                "Now, the new node has become the last node.");
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
                "Node *Insert(Node *head, int data)\n" +
                "{\n" +
                "    Node *newNode = new Node(data);\n" +
                "    if (head == NULL)\n" +
                "        return head = newNode;\n" +
                "    Node *temp = head;\n" +
                "    while (temp->next != NULL)\n" +
                "        temp = temp->next;\n" +
                "    temp->next = newNode;\n" +
                "    return head;\n" +
                "}";
        super.worstCase = "N";
        super.averageCase = "N";
        super.bestCase = "1";
    }
}
