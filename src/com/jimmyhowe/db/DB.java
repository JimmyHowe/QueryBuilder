package com.jimmyhowe.db;

import com.jimmyhowe.db.contracts.Connectable;
import com.jimmyhowe.db.models.Model;
import com.jimmyhowe.db.query.QueryBuilder;
import com.jimmyhowe.db.table.TableManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Facade for Query Builder
 */
public class DB
{
    static Connector connector;
    static QueryBuilder queryBuilder;
    private static Connectable adapter;

    //    static ResultSetProcessor resultSetProcessor = new ResultSetProcessor();
    private static Map<String, Model> map = new HashMap<>();

    private static List<String> log = new ArrayList<>();

    /**
     * Gets the current connection, or if none is set creates a new one
     */
    public static Connector getConnector()
    {
        return connector != null ? connector : new Connector(getAdapter());
    }

    /**
     * Sets the Connection to use
     *
     * @param adapter
     */
    public static void setConnector(Connectable adapter)
    {
        DB.connector = new Connector(adapter);
    }

    /**
     * Return the current adapter
     */
    public static Connectable getAdapter()
    {
        return adapter;
    }

    /**
     * Add a mapped model to the map list
     *
     * @param table
     * @param model
     */
    public static void map(String table, Model model)
    {
        DB.map.put(table, model);
    }

    /**
     * Return the map object
     */
    public static Map<String, Model> map()
    {
        return map;
    }

    /**
     * Returns the Query Builder Object
     *
     * @param table
     */
    public static QueryBuilder query(String table)
    {
        return new QueryBuilder(table);
    }

    /**
     * Returns a new Table Object
     *
     * @param table
     */
    public static TableManager table(String table)
    {
        return new TableManager(DB.getConnector(), table);
    }

    /**
     * Log a message to the DB object
     *
     * @param message
     */
    public static void log(String message)
    {
        log.add(message);
    }

    /**
     * Get the log object
     */
    public static List<String> getLog()
    {
        return log;
    }
}
