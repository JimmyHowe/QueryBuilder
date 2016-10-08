package com.jimmyhowe.db.connections;

import com.jimmyhowe.db.adapters.Adapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 11/08/2016.
 */
public class AdapterTest
{
    private TestingAdapter connection;

    @Before
    public void setUp() throws Exception
    {
        this.connection = new TestingAdapter("jimmyhowe_com", "root", null);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void buildUrl() throws Exception
    {
        assertEquals("jdbc:mysql://localhost/jimmyhowe_com?user=root&password=", this.connection.buildUrl());
    }

}

class TestingAdapter extends Adapter
{

    public TestingAdapter(String driver, String database, String user, String password)
    {
        super(driver, database, user, password);
    }

    public TestingAdapter(String database, String user, String password)
    {
        super(database, user, password);
    }

    @Override
    public String getConnectionUrl()
    {
        return this.buildUrl();
    }
}