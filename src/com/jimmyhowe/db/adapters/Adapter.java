package com.jimmyhowe.db.adapters;

import com.jimmyhowe.db.contracts.Connectable;

/**
 * MySQL Adapter for holding the connection url
 */
public abstract class Adapter implements Connectable
{
    protected String url = "jdbc:mysql://localhost/";

    protected String database = "";

    protected String user = "root";

    protected String password = null;

    /**
     * Empty constructor for using defaults
     */
    public Adapter()
    {

    }

    /**
     * Connect using all parameters
     *
     * @param url
     * @param database
     * @param user
     * @param password
     */
    public Adapter(String url, String database, String user, String password)
    {
        this.url = url;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * Connect using the default driver url
     *
     * @param database
     * @param user
     * @param password
     */
    public Adapter(String database, String user, String password)
    {
        this.database = database;
        this.user = user;
        this.password = password;
    }

    /**
     * @return
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return
     */
    public String getDatabase()
    {
        return database;
    }

    /**
     * @param database
     */
    public void setDatabase(String database)
    {
        this.database = database;
    }

    /**
     * @return
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Builds the URL String
     */
    public String buildUrl()
    {
        return String.format("%s%s?user=%s&password=%s", this.url, this.database, this.user, this.password != null ? this.password : "");
    }
}
