package com.portfolio.dsa.hashtable.hashtable;

public class HashTableLP<V> {

    // Inner class
    private static class Entry<V> {
        int key;       
        V value;    
        boolean isActive; 

        Entry(int key, V value) {
            this.key = key;
            this.value = value;
            this.isActive = true; 
        }
    }

    private Entry<V>[] table;        
    
    private int totalCollisions;    
    private final Entry<V> AVAILABLE; 

    // Constructor 
    public HashTableLP(int tableSize) {
        
        table = (Entry<V>[]) new Entry[tableSize]; // Create underlying
       
        totalCollisions = 0;

        // Initialize AVAILABLE marker
        AVAILABLE = new Entry<>(-1, null);
        AVAILABLE.isActive = false;
    }


    // Primary hash function 
    private int hash(int key) {
        int index = ( (3 * key) + 5 ) % table.length;
        return (index < 0) ? index + table.length : index;
    }

    //Inserts a key-value pair using linear probing.
     
    public int put(int key, V value) { 
        int collisions = 0;
        int index = hash(key);
        int startIndex = index;
        int firstAvailable = -1;

        // Linear Probing Loop 
        while (table[index] != null) {
            if (table[index] != AVAILABLE && table[index].key == key) {
                table[index].value = value;
                table[index].isActive = true;
                return collisions;
            }
            if (table[index] == AVAILABLE && firstAvailable == -1) {
                firstAvailable = index;
            }
            collisions++;
            index = (index + 1) % table.length;
          //prevent loop
            if (index == startIndex) {
                if (firstAvailable == -1) {
                     
                     return collisions; 
                } else {
                     break; // Use AVAILABLE slot found
                }
            }
        }

        int insertIndex = (firstAvailable != -1) ? firstAvailable : index;
        table[insertIndex] = new Entry<>(key, value); 
       
        totalCollisions += collisions;
        return collisions;
    }


    // Retrieves the value associated with the given key.
     
    public V get(int key) { 
        int index = hash(key);
        int startIndex = index;

        // Linear Probing Search Loop 
        while (table[index] != null) {
            if (table[index] != AVAILABLE && table[index].isActive && table[index].key == key) {
                return table[index].value; // Returns object 
            }
            index = (index + 1) % table.length;
            //loop prevention
            if (index == startIndex) {
                break;
            }
        }
        return null; // Return null if not found 
    }


    //Removes the entry associated with the given key 
     
    public V remove(int key) { 
        int index = hash(key);
        int startIndex = index;

        // Linear Probing Search Loop 
        while (table[index] != null) {
            if (table[index] != AVAILABLE && table[index].isActive && table[index].key == key) {
                V oldValue = table[index].value;
                table[index] = AVAILABLE; // Mark as available 
                //currentSize--;
                return oldValue; // Returns object 
            }
            index = (index + 1) % table.length;
            // Keep loop prevention
            if (index == startIndex) {
                break;
            }
        }
        return null; // Return null if not found
    }


    //Gets the total number of collisions.
     
    public int getNumberOfTotalCollisions() {
        return totalCollisions;
    }
}

