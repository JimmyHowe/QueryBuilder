package com.jimmyhowe.helpers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 11/08/2016.
 */
public class StrTest
{
    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void camelCaseToWords() throws Exception
    {
        assertEquals("Test One", Str.CamelCaseToWords("TestOne"));
    }

    @Test
    public void camelCaseToLowerCaseWords() throws Exception
    {
        assertEquals("test one", Str.CamelCaseToLowerCaseWords("TestOne"));
    }

    @Test
    public void camelCaseToSnakeCase() throws Exception
    {
        assertEquals("test_one", Str.CamelCaseToSnakeCase("TestOne"));
    }
}