package com.portfolio.dsa.postfix;

// Stack interface defining the basic stack operations
interface Stack<E> {

    // Pushes an element onto the top of the stack
    void push(E e);

    // Removes and returns the top element of the stack
    E pop();

    // Returns (but does not remove) the top element of the stack
    E peek();

    // Checks if the stack is empty
    boolean isEmpty();
}

