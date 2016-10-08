package com.jimmyhowe.app.adapters;

import com.jimmyhowe.db.adapters.Adapter;

/**
 * Default Adapter for the application
 */
public class DefaultAdapter extends Adapter
{
    public DefaultAdapter(String database, String user, String password)
    {
        super(database, user, password);
    }

    @Override
    public String getConnectionUrl()
    {
        return this.buildUrl();
    }
}
