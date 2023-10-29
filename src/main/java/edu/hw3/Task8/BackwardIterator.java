package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class BackwardIterator<T> implements Iterator<T> {

    public final List<T> list;

    public int index;

    public BackwardIterator(@NotNull List<T> list) {
        this.list = list;
        this.index = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return (index >= 0);
    }

    @Override
    public T next() {
        return list.get(index--);
    }

}
