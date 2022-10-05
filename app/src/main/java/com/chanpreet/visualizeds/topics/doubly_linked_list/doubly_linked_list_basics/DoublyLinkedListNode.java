package com.chanpreet.visualizeds.topics.doubly_linked_list.doubly_linked_list_basics;

public class DoublyLinkedListNode {
    public int data;
    public DoublyLinkedListNode next, prev;

    public DoublyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
