package com.jimmyhowe.db.objects;

import com.jimmyhowe.db.contracts.Countable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Row of Columns
 *
 * @param <Column>
 */
public class Row__OLD<Column> implements Iterable<Column>, Countable
{
    protected List<Column> columns = new ArrayList<>();

    /**
     * Add a column to the row
     *
     * @param item
     */
    public void add(Column item)
    {
        this.columns.add(item);
    }

    /**
     * Iterator for foreach loops
     */
    @Override
    public Iterator<Column> iterator()
    {
        return this.columns.iterator();
    }

    /**
     * Returns the Count
     */
    @Override
    public int count()
    {
        return this.columns.size();
    }

    @Override
    public String toString()
    {
        return "Row{" +
                "columnNames=" + columns +
                '}';
    }
}
