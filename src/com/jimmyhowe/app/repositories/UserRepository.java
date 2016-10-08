package com.jimmyhowe.app.repositories;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.collections.ModelCollection;
import com.jimmyhowe.db.models.User;
import com.jimmyhowe.db.repositories.Repository;

/**
 * Test User Repository
 */
public class UserRepository extends Repository<User>
{
    @Override
    protected User getModel()
    {
        return new User();
    }

    public ModelCollection oldFolk()
    {
        return DB.table(this.getModel().getTableName()).orderBy("id").get();
    }
}
