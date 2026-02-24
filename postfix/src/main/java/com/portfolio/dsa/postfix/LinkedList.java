package com.portfolio.dsa.postfix;


class LinkedStack<E> implements Stack<E> {// Stack implementation using a linked list

    // Internal singly linked list to store stack elements
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();

    // Pushes an element onto the stack 
    public void push(E e) { 
        list.addFirst(e); // Adds element at the head (top of the stack)
    }

    // Removes and returns the top element of the stack
    public E pop() { 
        return list.removeFirst(); // Removes and returns the first element of the linked list
    }

    // Returns the top element of the stack without removing it
    public E peek() { 
        return list.first(); // Gets the first element of the linked list without removing it
    }

    // Checks if the stack is empty
    public boolean isEmpty() { 
        return list.isEmpty(); // Returns true if there are no elements in the stack
    }
}

