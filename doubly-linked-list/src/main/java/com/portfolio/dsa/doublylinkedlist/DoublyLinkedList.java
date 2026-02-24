package com.portfolio.dsa.doublylinkedlist;

import java.util.ArrayList;

public class DoublyLinkedList<E> {

    // Nested class for Node structure
    private static class Node<E> {
        E element; // The element stored at this node
        Node<E> prev; // A reference to the previous node in the list
        Node<E> next; // A reference to the subsequent node in the list

        // Constructor
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        // Getters and setters
        public E getElement() { return element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> head = null; // The head node of the list
    private Node<E> tail = null; // The tail node of the list
    private int size = 0; // Number of nodes in the list

    // Constructs an initially empty list
    public DoublyLinkedList() {}

    // Returns the number of elements in the list
    public int size() { return size; }

    // Tests whether the list is empty
    public boolean isEmpty() { return size == 0; }

    // Adds element e to the front of the list
    public void addFirst(E e) {
        Node<E> newest = new Node<>(e, null, head);
        if (isEmpty())
            tail = newest;
        else
            head.setPrev(newest);
        head = newest;
        size++;
    }

    // Adds element e to the end of the list
    public void addLast(E e) {
        Node<E> newest = new Node<>(e, tail, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        size++;
    }

    // Removes and returns the first element
    public E removeFirst() {
        if (isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        if (head == null)
            tail = null;
        else
            head.setPrev(null);
        size--;
        return answer;
    }

    // Removes and returns the last element
    public E removeLast() {
        if (isEmpty()) return null;
        E answer = tail.getElement();
        tail = tail.getPrev();
        if (tail == null)
            head = null;
        else
            tail.setNext(null);
        size--;
        return answer;
    }

    // Returns an array list of elements from first to last
    public ArrayList<E> toArrayFromFirst() {
        ArrayList<E> list = new ArrayList<>();
        Node<E> current = head;
        while (current != null) {
            list.add(current.getElement());
            current = current.getNext();
        }
        return list;
    }

    // Returns an array list of elements from last to first
    public ArrayList<E> toArrayFromLast() {
        ArrayList<E> list = new ArrayList<>();
        Node<E> current = tail;
        while (current != null) {
            list.add(current.getElement());
            current = current.getPrev();
        }
        return list;
    }

    // Creates a deep copy of the list and returns it
    public DoublyLinkedList<E> clone() {
        DoublyLinkedList<E> listCopy = new DoublyLinkedList<>();
        Node<E> listNode = head;
        while (listNode != null) {
            listCopy.addLast(listNode.getElement());
            listNode = listNode.getNext();
        }
        return listCopy;
    }
}


