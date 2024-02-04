package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void testAddInteger() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();

        assertTrue(myArrayList.add(123));
        assertEquals(1, myArrayList.size());
        assertEquals(123,myArrayList.get(0));
    }

    @Test
    void testAddString() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        assertTrue(myArrayList.add("123"));
        assertEquals(1, myArrayList.size());
        assertEquals("123",myArrayList.get(0));
    }

    @Test
    void testAddNull() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        assertTrue(myArrayList.add(null));
        assertEquals(1, myArrayList.size());
        assertNull(myArrayList.get(0));
    }

    @Test
    void testAddIndex() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("1");
        myArrayList.add("3");
        assertTrue(myArrayList.add(1,"2"));

        assertEquals("2", myArrayList.get(1));
    }

    @Test
    void testGet() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");

        assertEquals("1", myArrayList.get(0));
        assertEquals("2", myArrayList.get(1));
        assertEquals("3", myArrayList.get(2));

        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.get(10));
    }

    @Test
    void testRemove() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");

        assertTrue(myArrayList.remove(1));
        assertEquals(2,myArrayList.size());
        assertEquals("3",myArrayList.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.remove(10));
    }

    @Test
    void testRemoveFirst() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("1");
        myArrayList.add("4");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.add("4");

        assertTrue(myArrayList.removeFirst("4"));
        assertEquals(4,myArrayList.size());
        assertEquals("4", myArrayList.get(3));
    }

    @Test
    void testRemoveFirstFromEmptyCollection() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        assertFalse(myArrayList.removeFirst("element"));
    }

    @Test
    void testRemoveAll() {
        MyArrayList<String> myArrayList = new MyArrayList<>();

        myArrayList.add("1");
        myArrayList.add("2");
        myArrayList.add("3");
        myArrayList.removeAll();
        assertEquals(0, myArrayList.size());

        myArrayList.add("4");
        myArrayList.add("5");
        myArrayList.add("6");
        assertEquals("6", myArrayList.get(2));
        assertEquals(3, myArrayList.size());
    }

    @Test
    void testSize() {
        MyArrayList<String> myArrayList = new MyArrayList<>(20);

        assertEquals(0, myArrayList.size());

        myArrayList.add("1");
        assertEquals(1, myArrayList.size());
    }

    @Test
    void testQuickSort() {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        myArrayList.add(3);
        myArrayList.add(1);
        myArrayList.add(4);
        myArrayList.add(1);
        myArrayList.add(5);

        myArrayList.quickSort();

        assertEquals(1, myArrayList.get(0));
        assertEquals(1, myArrayList.get(1));
        assertEquals(3, myArrayList.get(2));
        assertEquals(4, myArrayList.get(3));
        assertEquals(5, myArrayList.get(4));
    }

}