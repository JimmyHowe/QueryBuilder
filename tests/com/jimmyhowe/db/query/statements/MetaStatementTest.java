package com.jimmyhowe.db.query.statements;

import com.jimmyhowe.app.adapters.DefaultAdapter;
import com.jimmyhowe.db.table.columns.ColumnsCollection;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.query.QueryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class MetaStatementTest
{
    private MetaStatement metaStatement;

    @Before
    public void setUp() throws Exception
    {
        this.metaStatement = new MetaStatement(
                new Connector(new DefaultAdapter("jimmyhowe_com", "root", null)),
                new QueryBuilder("users")
//                new ResultSetProcessor(new QueryBuilder("users"))
        );
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void columns() throws Exception
    {
        ColumnsCollection collection = this.metaStatement.columns();

        assertEquals("id", collection.data(0).getField());
    }

    @Test
    public void columnNames() throws Exception
    {
        String[] columnNames = this.metaStatement.columnNames();

        assertEquals("id", columnNames[0]);
    }
}