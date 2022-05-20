package main;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private T array[];
    private int pos = 0;

    public ArrayIterator(T array[]) {
        this.array = array;
    }

    public boolean hasNext() {
        return pos < array.length;
    }

    public T next() throws NoSuchElementException {
        if (hasNext())
            return array[pos++];
        else
            throw new NoSuchElementException();
    }

    public boolean hasPrevious() {
        return pos > 0;
    }

    public T previous() throws NoSuchElementException {
        if (hasPrevious())
            return array[pos--];
        else
            throw new NoSuchElementException();
    }

    public int nextIndex() throws NoSuchElementException {
        if (hasNext()) {
            int temp = pos + 1;
            return temp;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int previousIndex() throws NoSuchElementException {
        if(hasPrevious()) {
            int temp = pos - 1;
            return temp;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T first() {
        pos = 0;
        return array[pos];
    }
}
