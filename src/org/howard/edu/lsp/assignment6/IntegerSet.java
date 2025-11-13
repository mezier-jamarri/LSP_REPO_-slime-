package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mathematical set of unique integers implemented using ArrayList.
 * supports standard set operations and includes no duplicate values.
 */
public class IntegerSet  {
    private List<Integer> set = new ArrayList<Integer>();

    /** clears the internal list. */
    public void clear() {
        set.clear();
    }

    /** returns the number of elements. */
    public int length() {
        return set.size();
    }

    /**
     * returns true if this set contains the same elements as another set,
     * regardless of order.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;

        IntegerSet other = (IntegerSet) o;

        if (this.length() != other.length()) return false;

        List<Integer> a = new ArrayList<>(this.set);
        List<Integer> b = new ArrayList<>(other.set);
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }

    /** true if the set contains the value. */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /** returns largest element or throws exception if empty */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
        return Collections.max(set);
    }

    /** returns smallest element or throws exception if empty */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
        return Collections.min(set);
    }

    /** adds an item if not already present. */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /** removes an element if present. */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /** modifies this set to contain the union of this and other. */
    public void union(IntegerSet other) {
        for (int val : other.set) {
            if (!set.contains(val)) {
                set.add(val);
            }
        }
    }

    /** modifies this set to contain only intersection with other. */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /** removes all elements found in the other set. */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /** becomes (other \ this). */
    public void complement(IntegerSet other) {
        List<Integer> result = new ArrayList<>(other.set);
        result.removeAll(this.set);
        this.set = result;
    }

    /** true if empty. */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /** returns formatted string representation of set. */
    @Override
    public String toString() {
        return set.toString();
    }
}