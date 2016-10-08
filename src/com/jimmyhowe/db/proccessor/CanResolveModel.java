package com.jimmyhowe.db.proccessor;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.contracts.Modelable;
import com.jimmyhowe.db.exceptions.ModelNotMappedException;

/**
 * Can Resolve Model from the DB Map
 */
public interface CanResolveModel
{
    /**
     * Resolves the Model Instance from the DB Mapped List
     *
     * @param <T>
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    default <T> Modelable<T> resolveModelableInstance() throws InstantiationException, IllegalAccessException
    {
        if ( ! DB.map().containsKey(this.getTableName()) )
        {
            throw new ModelNotMappedException("The '" + this.getTableName() + "' table does not have an associated model.");
        }

        return (Modelable<T>) DB.map().get(this.getTableName()).getClass().newInstance();
    }

    /**
     * Returns the Table Name
     */
    String getTableName();
}
