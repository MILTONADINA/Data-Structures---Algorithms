package com.portfolio.dsa.postfix;

// Generic SinglyLinkedList class
class SinglyLinkedList<E> {

    // Nested static class representing a node in the linked list
    private static class Node<E> {
        E element;  // Data stored in the node
        Node<E> next;  // Reference to the next node

        // Constructor to initialize a node with data and next reference
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<E> head = null; // Reference to the first node of the stack
    private int size = 0; // Tracks the number of elements in the list

    // Returns the number of elements in the list
    public int size() { 
        return size; 
    }

    // Checks if the list is empty
    public boolean isEmpty() { 
        return size == 0; 
    }

    // Adds an element at the beginning of the list (push operation for stack)
    public void addFirst(E element) {
        head = new Node<>(element, head); // New node points to current head
        size++; // Increase the size of the list
    }

    // Removes and returns the first element of the list (pop operation for stack)
    public E removeFirst() {
        if (isEmpty()) throw new IllegalStateException("empty stack"); // Prevents removal from an empty stack
        E element = head.element; // Retrieve the first element
        head = head.next; // Move head to the next node
        size--; // Decrease size
        return element; // Return the removed element
    }

    // Returns the first element without removing it (peek operation for stack)
    public E first() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty"); // Prevents peeking into an empty stack
        return head.element; // Return the first element
    }
}

