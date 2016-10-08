package com.jimmyhowe.db.models;

/**
 * Created by Jimmy on 05/08/2016.
 */
public class User extends Model
{
    public String fillable()
    {
        return "name,email,password";
    }
}
