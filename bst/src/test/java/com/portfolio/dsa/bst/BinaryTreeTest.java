package com.portfolio.dsa.bst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    void testBasicOperations() {
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        // Test insert and search
        tree.insert(10, "A");
        tree.insert(5, "B");
        tree.insert(15, "C");

        assertEquals("A", tree.search(10));
        assertEquals("B", tree.search(5));
        assertEquals("C", tree.search(15));
        assertNull(tree.search(99));

        // Test height
        assertEquals(1, tree.height());

        // Test remove
        assertEquals("A", tree.remove(10));
        assertNull(tree.search(10));

        // Verify others still exist
        assertEquals("B", tree.search(5));
        assertEquals("C", tree.search(15));
    }
}
