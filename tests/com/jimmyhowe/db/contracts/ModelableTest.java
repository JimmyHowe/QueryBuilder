package com.jimmyhowe.db.contracts;

import com.jimmyhowe.db.table.columns.ColumnsCollection;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class ModelableTest
{
    @Test
    public void it_can_true_if_fillable() throws Exception
    {
        ModelableMock modelableMock = new ModelableMock();

        assertTrue(modelableMock.isAllowedToFill("name"));
    }

    @Test
    public void it_can_when_not_fillable() throws Exception
    {
        ModelableMock modelableMock = new ModelableMock();

        assertFalse(modelableMock.isAllowedToFill("id"));
    }

}

class ModelableMock implements Modelable
{
    @Override
    public ColumnsCollection getOriginal()
    {
        return null;
    }

    @Override
    public void setOriginal(ColumnsCollection data)
    {

    }

    @Override
    public ColumnsCollection getAttributes()
    {
        return null;
    }

    @Override
    public void setAttributes(ColumnsCollection data)
    {

    }

    @Override
    public String getPrimaryKey()
    {
        return null;
    }

    @Override
    public String fillable()
    {
        return "name, email";
    }
}