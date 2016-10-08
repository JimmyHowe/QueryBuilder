package com.jimmyhowe.db;

import com.jimmyhowe.app.adapters.DefaultAdapter;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.contracts.Connectable;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Tests the Database Connector
 *
 * @can Connect to the Database
 * @can Run SQL queries
 * @can Clean Up Connections etc
 */
public class ConnectorTest
{
    Connectable adapter = new DefaultAdapter("jimmyhowe_com", "root", null);

    Connector connector = new Connector(this.adapter);

    @Test
    public void it_can_connect_manually() throws Exception
    {
        Connection connection = this.connector.connectWithAdapter(this.adapter).getConnection();

        assertNotNull(connection);
    }

    @Test
    public void it_can_run_with_auto_connect() throws Exception
    {
        Connector.autoConnect = true;

        ResultSet results = connector.run("SELECT * FROM users");

        assertNotNull(results);
    }

    @Test
    public void it_can_run_without_auto_connect() throws Exception
    {
        Connector.autoConnect = false;

        connector.connectWithAdapter(this.adapter);

        ResultSet results = connector.run("SELECT * FROM users");

        assertNotNull(results);
    }

    @Test
    public void it_can_clean_up() throws Exception
    {
        Connector.autoClose = false;

        connector.connectWithAdapter(this.adapter);

        connector.run("SELECT * FROM users");

        assertNotNull(connector.getResultSet());
        assertNotNull(connector.getStatement());
        assertNotNull(connector.getConnection());

        connector.cleanUp();

        assertNull(connector.getResultSet());
        assertNull(connector.getStatement());
        assertNull(connector.getConnection());
    }
}