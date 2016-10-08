package com.jimmyhowe.db.models;

import com.jimmyhowe.app.adapters.DefaultAdapter;
import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.exceptions.ModelException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jimmy on 11/08/2016.
 */
public class ModelTest
{
    private TestModel testModel;
    private TestModelWithOverrides testModelWithOverrides;

    public ModelTest()
    {
        DB.setConnector(new DefaultAdapter("jimmyhowe_com", "root", null));
    }

    @Before
    public void setUp() throws Exception
    {
        testModel = new TestModel();
        testModelWithOverrides = new TestModelWithOverrides();
    }

    @Test
    public void it_can_auto_resolve_table_name() throws Exception
    {
        assertEquals("test_models", this.testModel.getTableName());
    }

    @Test
    public void it_can_override_table_name() throws Exception
    {
        assertEquals("users", this.testModelWithOverrides.getTableName());
    }

    @Test
    public void it_can_override_the_primary_key() throws Exception
    {
        assertEquals("test_id", this.testModelWithOverrides.getPrimaryKey());
    }

    @Test
    public void it_can_tell_if_the_model_is_new() throws Exception
    {
        assertTrue(this.testModel.isNew());
    }

    @Test
    public void it_can_auto_resolve_the_column_names() throws Exception
    {
        this.testModelWithOverrides.getAttributeColumns();

        assertEquals("id", this.testModelWithOverrides.getAttributes().data(0).getField());
    }

    @Test
    public void it_can_add_to_new_model() throws Exception
    {
        DB.setConnector(new DefaultAdapter("jimmyhowe_com", "root", null));

        Model.disableBoot();

        User user = new User();

        user.getAttributeColumns();

        user.put("name", "Jimmy");

        assertEquals("Jimmy", user.get("name"));
    }

    @Test(expected=ModelException.class)
    public void it_throws_exception_when_put_is_no_in_fillable_fields() throws Exception
    {
        DB.setConnector(new DefaultAdapter("jimmyhowe_com", "root", null));

        Model.disableBoot();

        User user = new User();

        user.getAttributeColumns();

        user.put("id", "Jimmy");
    }

    @Test
    public void it_can_boot() throws Exception
    {
        DB.setConnector(new DefaultAdapter("jimmyhowe_com", "root", null));

        DB.map("users", new User());

        User newUser = new User();
//        newUser.stats();

        User dbUser = DB.table("users").first();

//        newUser.stats();
    }
}

class TestModel extends Model
{
    @Override
    public String fillable()
    {
        return null;
    }
}

class TestModelWithOverrides extends Model
{
    @Override
    public String getPrimaryKey()
    {
        return "test_id";
    }

    @Override
    public String fillable()
    {
        return null;
    }

    @Override
    public String getTableName()
    {
        return "users";
    }
}