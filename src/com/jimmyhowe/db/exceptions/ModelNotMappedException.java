package com.jimmyhowe.db.exceptions;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class ModelNotMappedException extends RuntimeException
{
    public ModelNotMappedException(String message)
    {
        super(message);
    }
}
