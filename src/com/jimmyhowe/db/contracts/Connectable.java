package com.jimmyhowe.db.contracts;

/**
 * Contract for DB Connections
 */
public interface Connectable
{
    /**
     * Returns the Database
     */
    String getDatabase();

    /**
     * Returns the Connection URL
     */
    String getConnectionUrl();
}
