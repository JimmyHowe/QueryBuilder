package com.jimmyhowe.support.collections;

import com.jimmyhowe.db.contracts.Countable;

import java.util.ArrayList;
import java.util.List;

/**
 * Generalized Collection
 *
 * @param <T>
 */
public class GeneralisedCollection<T> implements Countable
{
    private List<T> data = new ArrayList<>();

    /**
     * Returns the Data Object
     */
    public List<T> data()
    {
        return data;
    }

    /**
     * Helper to get data at index
     *
     * @param i
     */
    public T data(int i)
    {
        return this.data.get(i);
    }

    /**
     * Adds an item to the collection
     *
     * @param item
     */
    public void add(T item)
    {
        this.data.add(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count()
    {
        return this.data.size();
    }

    @Override
    public String toString()
    {
        return "Collection [\n" +
                data +
                ']';
    }
}
