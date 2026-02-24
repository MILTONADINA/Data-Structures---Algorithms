package com.portfolio.dsa.bst;

public class BinaryTree<K extends Comparable<K>, V> {

    // Internal class
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> parent;// node refs
        Entry<K, V> left;
        Entry<K, V> right;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }

    private Entry<K, V> root; // 1st node

    public BinaryTree() {
        root = null;
    }

    public void insert(K key, V value) {
        java.util.Objects.requireNonNull(key, "Key cannot be null");
        if (root == null) {
            root = new Entry<>(key, value, null);
            return;
        }

        Entry<K, V> current = root;
        Entry<K, V> parent = null;
        int cmp;
        while (current != null) {
            parent = current;
            cmp = key.compareTo(current.key);

            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                // Key exists, update value
                current.value = value;
                return;
            }
        }

        Entry<K, V> newEntry = new Entry<>(key, value, parent);
        cmp = key.compareTo(parent.key);

        if (cmp < 0) {
            parent.left = newEntry;
        } else {
            parent.right = newEntry;
        }
    }

    public V search(K key) {
        java.util.Objects.requireNonNull(key, "Key cannot be null");
        Entry<K, V> entry = findEntry(key);
        return (entry != null) ? entry.value : null;
    }

    // Helper
    private Entry<K, V> findEntry(K key) {
        Entry<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current; // Key found
            }
        }
        return null; // Key not found
    }

    public int height() {
        return calculateHeight(root);
    }

    // helper for height calculation
    private int calculateHeight(Entry<K, V> node) {
        if (node == null) {
            return -1; // Height of an empty subtree
        }
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    // returns removed value
    public V remove(K key) {
        java.util.Objects.requireNonNull(key, "Key cannot be null");
        Entry<K, V> entryToRemove = findEntry(key);
        if (entryToRemove == null) {
            return null;
        }

        V removedValue = entryToRemove.value;
        deleteEntry(entryToRemove);
        return removedValue;
    }

    // Helpers 4 delete
    private void deleteEntry(Entry<K, V> p) {
        // Case 1: p has no right child
        if (p.right == null) {
            transplant(p, p.left);
        }
        // Case 2: p's right child has no left child successor is pright
        else if (p.right.left == null) {
            p.right.left = p.left;
            if (p.left != null) {
                p.left.parent = p.right;
            }
            transplant(p, p.right);
        }
        // Case 3: p's successor is deeper in the right subtree
        else {
            Entry<K, V> successor = getMinimum(p.right);

            // If successor is not p's direct right child
            if (successor.parent != p) {
                transplant(successor, successor.right); // Replace successor with its right child
                successor.right = p.right; // Link successor to p's right child
                successor.right.parent = successor;
            }

            // Replace p with successor
            transplant(p, successor);
            successor.left = p.left; // Link successor to p's left child
            if (successor.left != null) {
                successor.left.parent = successor;
            }
        }
    }

    // 1 child
    private void transplant(Entry<K, V> u, Entry<K, V> v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    // finding successor
    private Entry<K, V> getMinimum(Entry<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
