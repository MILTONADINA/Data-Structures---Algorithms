package com.portfolio.dsa.hashtable.hashtableapp;

import com.portfolio.dsa.hashtable.hashtable.HashTableDH;
import com.portfolio.dsa.hashtable.hashtable.HashTableLP;
import com.portfolio.dsa.hashtable.hashtable2.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void testLinearProbing() {
        HashTableLP<Student> tableLP = new HashTableLP<>(100);
        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");

        tableLP.put(s1.getId(), s1);
        tableLP.put(s2.getId(), s2);

        assertEquals("Alice", tableLP.get(1).getName());
        assertEquals("Bob", tableLP.get(2).getName());

        tableLP.remove(1);
        assertNull(tableLP.get(1));
    }

    @Test
    void testDoubleHashing() {
        HashTableDH<Student> tableDH = new HashTableDH<>(100);
        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");

        tableDH.put(s1.getId(), s1);
        tableDH.put(s2.getId(), s2);

        assertEquals("Alice", tableDH.get(1).getName());
        assertEquals("Bob", tableDH.get(2).getName());

        tableDH.remove(1);
        assertNull(tableDH.get(1));
    }
}
