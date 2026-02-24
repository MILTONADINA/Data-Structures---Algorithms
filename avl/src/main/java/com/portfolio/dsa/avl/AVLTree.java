package com.portfolio.dsa.avl;

public class AVLTree<K extends Comparable<K>, E> {

    // Inner nod class
    private static class Node<K, E> {
        K key;
        E element;
        Node<K, E> parent;
        Node<K, E> leftChild;
        Node<K, E> rightChild;
        int height;

        Node(K key, E element, Node<K, E> parent) {
            this.key = key;
            this.element = element;
            this.parent = parent;
            this.leftChild = null;
            this.rightChild = null;
            this.height = 1;
        }
    }

    private Node<K, E> root;

    public AVLTree() {
        root = null;

    }

    // Height and Balance
    private int getHeight(Node<K, E> node) {

        return (node == null) ? 0 : node.height;// 0 for calculations
    }

    // Returns height
    public int height() {

        return getHeight(root);// returns the height stored root
    }

    private void updateHeight(Node<K, E> node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
        }
    }

    // Calculates balance
    private int getBalance(Node<K, E> node) {

        return (node == null) ? 0 : getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    // --- Rotations (Essential for AVL) ---
    private Node<K, E> rightRotate(Node<K, E> y) {
        Node<K, E> x = y.leftChild;
        Node<K, E> T2 = x.rightChild;

        // Rotation
        x.rightChild = y;
        y.leftChild = T2;

        // Update
        x.parent = y.parent; // x takes y's old parent
        y.parent = x; // y's new parent is x
        if (T2 != null)
            T2.parent = y; // T2's new parent is y

        // Link new subtree root (x) to its parent
        if (x.parent == null) {
            this.root = x; // x is the new root of the whole tree
        } else if (y == x.parent.leftChild) {
            x.parent.leftChild = x;
        } else {
            x.parent.rightChild = x;
        }

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x; // Return new root
    }

    private Node<K, E> leftRotate(Node<K, E> x) {
        Node<K, E> y = x.rightChild;
        Node<K, E> T2 = y.leftChild;

        // Rotation
        y.leftChild = x;
        x.rightChild = T2;

        // Update
        y.parent = x.parent; // y takes x's old parent
        x.parent = y; // x's new parent is y
        if (T2 != null)
            T2.parent = x; // T2's new parent is x

        // Link new subtree root (y) to its parent
        if (y.parent == null) {
            this.root = y; // y is the new root of the whole tree
        } else if (x == y.parent.leftChild) {
            y.parent.leftChild = y;
        } else {
            y.parent.rightChild = y;
        }

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y; // Return new root
    }

    // Rebalancing

    private void rebalance(Node<K, E> node) {
        while (node != null) {
            updateHeight(node);
            int balance = getBalance(node);
            Node<K, E> parent = node.parent; // Store parent before potential rotation

            if (balance > 1) {
                // Left Left Case
                if (getBalance(node.leftChild) >= 0) {
                    rightRotate(node);
                }
                // Left Right Case
                else {
                    node.leftChild = leftRotate(node.leftChild);
                    rightRotate(node);
                }
            }

            else if (balance < -1) {
                // Right Right Case
                if (getBalance(node.rightChild) <= 0) {
                    leftRotate(node);
                }
                // Right Left Case
                else {
                    node.rightChild = rightRotate(node.rightChild);
                    leftRotate(node);
                }
            }
            node = parent; // Move up to original parent for next check
        }
    }

    // Insert Method
    public void insert(K key, E element) {
        java.util.Objects.requireNonNull(key, "Key cannot be null");
        if (root == null) {
            root = new Node<>(key, element, null);

            return;
        }

        Node<K, E> current = root;
        Node<K, E> parent = null;
        while (true) { // until spot or existing key
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.leftChild;
                if (current == null) { // spot founf
                    parent.leftChild = new Node<>(key, element, parent);

                    rebalance(parent); // Rebalance tree
                    return;
                }
            } else if (cmp > 0) {
                current = current.rightChild;
                if (current == null) { // found spot
                    parent.rightChild = new Node<>(key, element, parent);

                    rebalance(parent); // Rebalance
                    return;
                }
            } else {

                current.element = element;
                return; // already there
            }
        }
    }

    // Search

    private Node<K, E> searchNode(K key) {
        Node<K, E> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return null; // Key not found
    }

    // Search key, return object
    public E search(K key) {
        java.util.Objects.requireNonNull(key, "Key cannot be null");
        Node<K, E> node = searchNode(key);
        return (node != null) ? node.element : null;
    }

    // Remove Method
    // find the minimum node ie successor
    private Node<K, E> findMin(Node<K, E> node) {
        while (node.leftChild != null) {
            node = node.leftChild;
        }
        return node;
    }

    public E remove(K key) {
        java.util.Objects.requireNonNull(key, "Key cannot be null");
        Node<K, E> nodeToRemove = searchNode(key);
        if (nodeToRemove == null) {
            return null; // Key not found
        }

        E elementToReturn = nodeToRemove.element;
        Node<K, E> nodeToRebalance; // where rebalancing should start

        // Node has 0 or 1 child
        if (nodeToRemove.leftChild == null || nodeToRemove.rightChild == null) {
            Node<K, E> child = (nodeToRemove.leftChild != null) ? nodeToRemove.leftChild : nodeToRemove.rightChild;
            nodeToRebalance = nodeToRemove.parent; // where Rebalance starts from parent

            // If removing root
            if (nodeToRemove == root) {
                root = child;
                if (child != null)
                    child.parent = null;
            } else { // Not removing root
                if (nodeToRemove == nodeToRemove.parent.leftChild) {
                    nodeToRemove.parent.leftChild = child;
                } else {
                    nodeToRemove.parent.rightChild = child;
                }
                if (child != null)
                    child.parent = nodeToRemove.parent;
            }
            // Detach the removed node
            nodeToRemove.parent = nodeToRemove.leftChild = nodeToRemove.rightChild = null;

        }
        // Node has two children
        else {
            Node<K, E> successor = findMin(nodeToRemove.rightChild); // Findsuccessor
            nodeToRebalance = successor.parent; // Rebalancing starts from successor's original parent

            // Replace nodeToRemove's data with successor's data
            nodeToRemove.key = successor.key;
            nodeToRemove.element = successor.element;

            // remove the successor node with 0 or 1 right child
            Node<K, E> successorChild = successor.rightChild;

            // Detach successor from its parent
            if (successor == successor.parent.leftChild) {
                successor.parent.leftChild = successorChild;
            } else {
                successor.parent.rightChild = successorChild;
            }
            if (successorChild != null) {
                successorChild.parent = successor.parent;
            }

            // if successor's parent was the node removed

            // rebalance should start from the successor itself (in its new position).
            if (nodeToRebalance == nodeToRemove) {
                nodeToRebalance = successor;
            }

            // Detach the original successor node
            successor.parent = successor.leftChild = successor.rightChild = null;

        }

        // Rebalance
        if (nodeToRebalance != null) {
            rebalance(nodeToRebalance);
        } else if (root != null) {

            // rebalance from the new root
            rebalance(root);
        }

        return elementToReturn;
    }

}
