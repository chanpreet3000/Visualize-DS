package com.chanpreet.visualizeds.classes.data_structure_containers;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedListNode {
    public int data;
    public DoublyLinkedListNode next, prev;

    public DoublyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public static List<Integer> getDLL(DoublyLinkedListNode head) {
        DoublyLinkedListNode temp = head;
        List<Integer> arr = new ArrayList<>();
        while (temp != null) {
            arr.add(temp.data);
            temp = temp.next;
        }
        return arr;
    }
}
