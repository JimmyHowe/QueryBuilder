package com.jimmyhowe.db.adapters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class AdapterTest
{
    private final Adapter adapter;

    public AdapterTest()
    {
        this.adapter = new Adapter("test_db", "root", null)
        {
            @Override
            public String getConnectionUrl()
            {
                return this.buildUrl();
            }
        };
    }

    @Test
    public void buildUrl() throws Exception
    {
        assertEquals("jdbc:mysql://localhost/test_db?user=root&password=", this.adapter.getConnectionUrl());
    }
}