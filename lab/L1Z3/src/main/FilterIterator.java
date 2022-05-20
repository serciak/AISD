package main;

import java.util.Iterator;

public class FilterIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private Predicate<T> predykat;
    private T elemNext = null;
    private boolean bHasNext = true;

    public FilterIterator(Iterator<T> iterator, Predicate<T> predykat) {
        super();
        this.iterator = iterator;
        this.predykat = predykat;
        findNextValid();
    }

    private void findNextValid() {
        while (iterator.hasNext()) {
            elemNext = iterator.next();
            if (predykat.accept(elemNext)) {
                return;
            }
        }
        bHasNext=false;
        elemNext=null;
    }

    @Override
    public boolean hasNext() {
        return bHasNext;
    }
    @Override
    public T next() {
        T nextValue = elemNext;
        findNextValid();
        return nextValue;
    }
}

