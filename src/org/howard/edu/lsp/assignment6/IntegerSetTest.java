package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 * JUnit tests for IntegerSet.
 */
public class IntegerSetTest {
    @Test
    public void testClearAndLength() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.clear();
        assertEquals(0, set.length());
        assertTrue(set.isEmpty());
    }

    @Test
    public void testEquals() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2);
        b.add(2); b.add(1);

        assertTrue(a.equals(b));
        b.add(3);
        assertFalse(a.equals(b));
    }

    @Test
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        assertTrue(set.contains(5));
        assertFalse(set.contains(10));
    }

    @Test
    public void testLargest() {
        IntegerSet set = new IntegerSet();
        set.add(3); set.add(9); set.add(1);
        assertEquals(9, set.largest());
    }

    @Test
    public void testLargestThrows() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::largest);
    }

    @Test
    public void testSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(3); set.add(9); set.add(1);
        assertEquals(1, set.smallest());
    }

    @Test
    public void testSmallestThrows() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::smallest);
    }

    @Test
    public void testAddNoDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1);
        assertEquals(1, set.length());
    }

    @Test
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.remove(1);
        assertFalse(set.contains(1));
        assertTrue(set.contains(2));
    }

    @Test
    public void testUnion() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2);
        b.add(2); b.add(3);

        a.union(b);

        assertTrue(a.contains(1));
        assertTrue(a.contains(2));
        assertTrue(a.contains(3));
        assertEquals(3, a.length());
    }

    @Test
    public void testIntersect() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2); a.add(3);
        b.add(2); b.add(4);

        a.intersect(b);

        assertEquals(1, a.length());
        assertTrue(a.contains(2));
    }

    @Test
    public void testDiff() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2); a.add(3);
        b.add(2); b.add(10);

        a.diff(b);

        assertEquals(2, a.length());
        assertFalse(a.contains(2));
    }

    @Test
    public void testComplement() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1); a.add(2);
        b.add(1); b.add(2); b.add(3);

        a.complement(b);

        assertEquals(1, a.length());
        assertTrue(a.contains(3));
    }

    @Test
    public void testIsEmpty() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());
        set.add(10);
        assertFalse(set.isEmpty());
    }

    @Test
    public void testToString() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        assertEquals("[1, 2]", set.toString());
    }
}