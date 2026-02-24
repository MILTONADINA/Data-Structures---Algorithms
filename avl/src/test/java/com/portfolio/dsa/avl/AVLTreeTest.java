package com.portfolio.dsa.avl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void testBasicOperations() {
        AVLTree<Integer, String> tree = new AVLTree<>();

        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(30, "C"); // Should trigger rotation

        assertEquals("A", tree.search(10));
        assertEquals("B", tree.search(20));
        assertEquals("C", tree.search(30));

        // Height should be 2 (root is 20, children 10 and 30) - wait, height is
        // 1-indexed?
        // Node height starts at 1. Leaf is 1. Root of 3 nodes is 2.
        assertEquals(2, tree.height());

        assertEquals("C", tree.remove(30));
        assertNull(tree.search(30));

        assertEquals("A", tree.remove(10));
        assertNull(tree.search(10));
    }
}
