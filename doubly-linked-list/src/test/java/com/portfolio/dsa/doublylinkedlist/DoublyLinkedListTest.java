package com.portfolio.dsa.doublylinkedlist;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void testBasicOperations() {
        DoublyLinkedList<String> myList = new DoublyLinkedList<>();

        myList.addFirst("USA");
        myList.addLast("Germany");
        myList.addFirst("France");
        myList.addLast("England");
        myList.addFirst("Belgium");

        // The list should be: Belgium, France, USA, Germany, England
        assertEquals("Belgium", myList.removeFirst());
        assertEquals("England", myList.removeLast());

        // The list should now be: France, USA, Germany
        assertEquals("France", myList.removeFirst());
        assertEquals("Germany", myList.removeLast());
        assertEquals("USA", myList.removeFirst());

        assertNull(myList.removeFirst());
    }
}
