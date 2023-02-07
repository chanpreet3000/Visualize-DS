package com.chanpreet.visualizeds.classes.data_structure_containers;

import java.util.ArrayList;
import java.util.List;

public class LinkedListNode {
    public int data;
    public LinkedListNode next;

    public LinkedListNode(int data) {
        this.data = data;
    }

    public static List<Integer> getList(LinkedListNode head) {
        LinkedListNode temp = head;
        List<Integer> arr = new ArrayList<>();
        while (temp != null) {
            arr.add(temp.data);
            temp = temp.next;
        }
        return arr;
    }
}
