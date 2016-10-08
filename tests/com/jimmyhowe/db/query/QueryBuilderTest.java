package com.jimmyhowe.db.query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest
{
    QueryBuilder queryBuilder;

    @Before
    public void setUp() throws Exception
    {
        this.queryBuilder = new QueryBuilder("test");
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void it_can_build_table_name() throws Exception
    {
        assertEquals("test", this.queryBuilder.getTableName());
    }

    @Test
    public void it_can_build_a_simple_select_all() throws Exception
    {
        assertEquals("SELECT * FROM test", this.queryBuilder.get());
    }

    @Test
    public void it_can_build_a_simple_select_with_columns() throws Exception
    {
        assertEquals("SELECT id, name, email FROM test", this.queryBuilder.get("id", "name", "email"));
    }

    @Test
    public void it_can_build_a_simple_select_first() throws Exception
    {
        assertEquals("SELECT * FROM test LIMIT 1", this.queryBuilder.first());
    }

    @Test
    public void it_can_build_a_simple_select_first_with_columns() throws Exception
    {
        assertEquals("SELECT id, name, email FROM test LIMIT 1", this.queryBuilder.first("id", "name", "email"));
    }

    @Test
    public void it_can_build_a_list_using_default_primary_key() throws Exception
    {
        assertEquals("SELECT id, name FROM test", this.queryBuilder.list("name"));
    }

    @Test
    public void it_can_build_a_list_using_primary_key() throws Exception
    {
        assertEquals("SELECT id, name FROM test", this.queryBuilder.list("name", "id"));
    }

    @Test
    public void it_can_build_a_simple_insert_statement() throws Exception
    {
        String expected = "INSERT INTO test (name, email) VALUES ('Jimmy', 'jimmyhowe@live.co.uk')";
        String actual = this.queryBuilder.insert("name", "email").values("Jimmy", "jimmyhowe@live.co.uk");

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_build_a_simple_update_statement() throws Exception
    {
        String expected = "UPDATE test SET name='Jurij', email='jurij@live.co.uk' WHERE id = '1'";
        String actual = this.queryBuilder.update("name", "email").where("id", 1).values("Jurij", "jurij@live.co.uk");

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_build_a_select_all_with_a_where_statement() throws Exception
    {
        assertEquals("SELECT * FROM test WHERE name = 'Jimmy'", this.queryBuilder.where("name", "Jimmy").get());
    }

    @Test
    public void it_can_build_a_select_all_with_a_where_not_statement() throws Exception
    {
        assertEquals("SELECT * FROM test WHERE name != 'Jimmy'", this.queryBuilder.where("name", "!=", "Jimmy").get());
    }

    @Test
    public void it_can_build_a_select_all_with_an_order_statement() throws Exception
    {
        assertEquals("SELECT * FROM test ORDER BY name ASC", this.queryBuilder.orderBy("name").get());
    }

    @Test
    public void it_can_build_a_select_all_with_an_order_desc_statement() throws Exception
    {
        assertEquals("SELECT * FROM test ORDER BY name DESC", this.queryBuilder.orderBy("name", "DESC").get());
    }

    @Test
    public void it_can_select_all_with_an_order_by_and_a_where_statement() throws Exception
    {
        String expected = "SELECT * FROM test WHERE name = 'Jimmy' ORDER BY name DESC";
        Object actual = this.queryBuilder.where("name", "Jimmy").orderBy("name", "DESC").get();

        assertEquals(expected, actual);
    }

    @Test
    public void it_can_select_one_with_an_order_by_and_a_where_statement() throws Exception
    {
        String expected = "SELECT * FROM test WHERE name = 'Jimmy' ORDER BY name DESC LIMIT 1";
        Object actual = this.queryBuilder.where("name", "Jimmy").orderBy("name", "DESC").first();

        assertEquals(expected, actual);
    }
}