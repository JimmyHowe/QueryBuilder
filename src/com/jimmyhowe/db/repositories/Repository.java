package com.jimmyhowe.db.repositories;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.models.Model;

/**
 * Abstract Repository
 *
 * @param <T>
 */
public abstract class Repository<T extends Model>
{
    public Repository()
    {
        this.getModel();
    }

    protected abstract T getModel();

    /**
     * Find a record by ID
     *
     * @param id
     */
    public T find(int id)
    {
        return DB.table(getModel().getTableName()).where(getModel().getPrimaryKey(), id).first();
    }
}
