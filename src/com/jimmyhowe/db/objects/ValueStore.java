/*
 * Copyright (c) 2016. JimmyHowe.com
 */

package com.jimmyhowe.db.objects;

import com.jimmyhowe.db.contracts.Cleanable;
import com.jimmyhowe.db.contracts.Countable;
import com.jimmyhowe.db.exceptions.ValueStoreException;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Values Store
 * <p>
 * Allows to add objects and convert into MySQL VALUES String
 */
public class ValueStore implements Cleanable, Countable
{
    /**
     * Array of Strings
     */
    private List<String> data = new ArrayList<>();

    /**
     * Construct with values
     *
     * @param values
     */
    public ValueStore(Object... values)
    {
        this.add(values);
    }

    /**
     * Construct with List
     *
     * @param strings
     */
    public ValueStore(List<String> strings)
    {
        this.data = strings;
    }

    /**
     * Add Array of Objects
     *
     * @param values
     */
    public ValueStore add(Object... values)
    {
        for ( Object object : values )
        {
            this.data.add(object.toString());
        }

        return this;
    }

    /**
     * Convert String Array to CSV Format
     */
    public String toCsv()
    {
        StringJoiner stringJoiner = new StringJoiner(", ");

        this.data.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }

    public ValueStore addQuotes()
    {
        for ( int i = 0; i < this.data.size(); i++ )
        {
            String value = "'" + data.get(i) + "'";
            this.data.set(i, value);
        }

        return this;
    }

    /**
     * Convert String Array to CSV Format
     */
    public String toWrappedInBraces()
    {
        StringJoiner stringJoiner = new StringJoiner(", ", "(", ")");

        this.data.forEach(stringJoiner::add);

        return stringJoiner.toString();
    }

    /**
     * Clean Up
     */
    public void cleanUp()
    {
        this.data = new ArrayList<>();
    }

    /**
     * Get Object by Index
     *
     * @param i
     */
    public Object get(int i)
    {
        return this.data.get(i);
    }

    public static String UpdateThing(ValueStore columns, ValueStore values)
    {
        ValueStore vs = new ValueStore();

        if(columns.count() != values.count())
        {
            throw new ValueStoreException("Some thing shit itself");
        }

        for ( int i = 0; i < columns.count(); i++ )
        {
            vs.add(columns.get(i) + "='" + values.get(i) + "'");
        }

        return vs.toCsv();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int count()
    {
        return this.data.size();
    }
}