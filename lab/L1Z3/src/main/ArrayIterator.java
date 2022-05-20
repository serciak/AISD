package main;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
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
            return array[--pos];
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

    public void remove() {
        T[] temp = (T[]) new Object[array.length-1];

        if (pos - 1 >= 0)
            System.arraycopy(array, 0, temp, 0, pos - 1);
        if (temp.length - (pos - 1) >= 0)
            System.arraycopy(array, pos, temp, pos - 1, temp.length - (pos - 1));
        array = temp;
    }

    public void zamienObokSiebie() {
        T temp;

        if(hasPrevious()) {
            temp = array[pos];
            array[pos] = array[pos-1];
            array[pos-1] = temp;
        }
    }

    public int currentIndex() {
        return pos;
    }
}
