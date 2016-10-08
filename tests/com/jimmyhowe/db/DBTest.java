package com.jimmyhowe.db;

import com.jimmyhowe.app.adapters.DefaultAdapter;
import com.jimmyhowe.db.collections.ModelCollection;
import com.jimmyhowe.db.query.QueryBuilder;
import com.jimmyhowe.db.table.TableManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Jimmy on 05/08/2016.
 */
public class DBTest
{
    public DBTest()
    {
        DB.setConnector(new DefaultAdapter("jimmyhowe_com", "root", ""));
    }

    @Before
    public void setUp() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

//    @Test
//    public void it_can_map_by_string() throws Exception
//    {
//        DB.map("users", "com.jimmyhowe.db.models.User");
//
//        assertEquals("com.jimmyhowe.db.models.User", DB.map().get("users"));
//    }

//    @Test
//    public void it_can_map_by_passing_object() throws Exception
//    {
//        DB.map("users", new com.jimmyhowe.db.models.User());
//
//        assertEquals("com.jimmyhowe.db.models.User", DB.map().get("users"));
//    }

    @Test
    public void query() throws Exception
    {
        assertTrue(DB.query("test") instanceof QueryBuilder);
    }

    @Test
    public void table() throws Exception
    {
        assertTrue(DB.table("test") instanceof TableManager);
    }

    @Test
    public void it_can_return_a_table_row() throws Exception
    {
        DB.map("users", new User());

        ModelCollection<User> collection = DB.table("users").get();
    }

    @Test
    public void log() throws Exception
    {
//        DB.log("HELLO");
//
//        assertEquals("HELLO", DB.getLog().get(1).toString());
    }
}

