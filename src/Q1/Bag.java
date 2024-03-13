package Q1;
//-----------------------------------------------------
// Title: Bag implementation class
// Author: Ceyda Kuşçuoğlu

// Description: This class implements the bag structure
//-----------------------------------------------------
import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {

    private Item[] items;
    private int n;

    public Bag() {
        items = (Item[]) new Object[2];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(Item item) {
        if (n == items.length) {
            resize(2 * items.length);
        }
        items[n++] = item;
    }

    public boolean contains(Item item) {
        for (int i = 0; i < n; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void remove(Item item) {
        for (int i = 0; i < n; i++) {
            if (items[i].equals(item)) {
                items[i] = items[n - 1];
                n--;
                break;
            }
        }
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private void resize(int newCapacity) {
        Item[] temp = (Item[]) new Object[newCapacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (i == n) {
                System.out.println("There is no such a element");
            }
            return items[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
