package com.jimmyhowe.db.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Collection Object
 *
 * @param <T>
 */
public class MyCollection<T> implements Iterable<T>
{

    protected List<T> data = new ArrayList<>();

    public void add(T object)
    {
        this.data.add(object);
    }

    public int size()
    {
        return this.data.size();
    }

    public T get(int i)
    {
        return this.data.get(i);
    }

    public Iterator<T> iterator()
    {
        return new MyIterator();
    }

    class MyIterator implements Iterator<T>
    {

        private int index = 0;

        public boolean hasNext()
        {
            return index < size();
        }

        public T next()
        {
            return get(index++);
        }

        public void remove()
        {
            throw new UnsupportedOperationException("not supported yet");
        }
    }
}