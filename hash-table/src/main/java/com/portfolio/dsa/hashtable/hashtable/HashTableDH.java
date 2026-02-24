package com.portfolio.dsa.hashtable.hashtable;

public class HashTableDH<V> {

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
    private int currentSize;
    private int totalPutCollisions;
    private final Entry<V> AVAILABLE;

    // Constr
    public HashTableDH(int tableSize) {
 
        table = (Entry<V>[]) new Entry[tableSize];
        currentSize = 0;
        totalPutCollisions = 0;
        AVAILABLE = new Entry<>(-1, null); 
        AVAILABLE.isActive = false;
    }

    // Primary hash 
    private int hash1(int key) {
        int index = ( (3 * key) + 5 ) % table.length;
        return (index < 0) ? index + table.length : index;
    }

    // Secondary hash
    private int hash2(int key) {
        int hashVal = 7 - (key % 7);
        return (hashVal == 0) ? 1 : hashVal; 
    }

    
     // Insert key
     
    public int put(int key, V value) { // Generics
        int collisions = 0;
        int index = hash1(key);
        int step = 0;
        int firstAvailable = -1;

        // Loop
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
            step++;
            // Double hashing
            index = (hash1(key) + step * hash2(key)) % table.length;
            index = (index < 0) ? index + table.length : index;
            // loop prevent
            if (step >= table.length) {
                 
    if (firstAvailable != -1) {
                     index = firstAvailable;
                     break;
                 } else {
                     return collisions; 
                 }
            }
        }

        int insertIndex = (firstAvailable != -1) ? firstAvailable : index;
        table[insertIndex] = new Entry<>(key, value); 
        currentSize++;
        totalPutCollisions += collisions;
        return collisions;
    }

    //Retrieves 
     
    public V get(int key) { 
        int index = hash1(key);
        int step = 0;

        // Dh search 
        while (table[index] != null) {
            if (table[index] != AVAILABLE && table[index].isActive && table[index].key == key) {
                return table[index].value; 
            }
            step++;
            index = (hash1(key) + step * hash2(key)) % table.length;
            index = (index < 0) ? index + table.length : index;
            
            if (step >= table.length) {
                break;
            }
        }
        return null; //if not found
    }

    
       //Remove funct
      
    public V remove(int key) { 
        int index = hash1(key);
        int step = 0;

        // dh Search Loop 
        while (table[index] != null) {
            if (table[index] != AVAILABLE && table[index].isActive && table[index].key == key) {
                V oldValue = table[index].value;
                table[index] = AVAILABLE; 
                currentSize--;
                return oldValue; // Returns object
            }
            step++;
            index = (hash1(key) + step * hash2(key)) % table.length;
            index = (index < 0) ? index + table.length : index;
            // Keep loop prevention
            if (step >= table.length) {
                break;
            }
        }
        return null; // if not found
    }

    //total colisions.
     
     public int getNumberOfTotalPutCollisions() {
         return totalPutCollisions;
     }
}

