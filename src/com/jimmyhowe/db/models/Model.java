package com.jimmyhowe.db.models;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.table.columns.ColumnsCollection;
import com.jimmyhowe.db.contracts.Modelable;
import com.jimmyhowe.db.exceptions.ModelException;
import com.jimmyhowe.helpers.Str;
import org.atteo.evo.inflector.English;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract Model
 */
public abstract class Model implements Modelable
{
    /**
     * Default boot behaviour
     */
    private static boolean autoBoot = true;

    /**
     * The generated table name
     */
    protected String tableName = null;

    /**
     * The tables primary key
     */
    protected String primaryKey = "id";

    /**
     * The original attributes stored in the database
     */
    private ColumnsCollection original = new ColumnsCollection();

    /**
     * The current models attributes
     */
    private ColumnsCollection attributes = new ColumnsCollection();

    /**
     * Build the Model and Boot
     *
     * @should boot the model
     *
     * @throws ModelException Cant auto assign model table name
     */
    public Model()
    {
        // TODO: Fix autoBoot method not setting adapter

        if ( autoAssignTableName() == null )
        {
            throw new ModelException("Cant auto assign table name to model: " + this.getClass().getSimpleName());
        }

        if( autoBoot ) boot();
    }

    /**
     * Disables the boot on all models
     */
    public static void disableBoot()
    {
        Model.autoBoot = false;
    }

    /**
     * Boot the model, and associate the attributes with the columnNames in the
     * corresponding database table
     */
    private void boot()
    {
//        System.out.println("Booting Model... " + this.getClass().getSimpleName());

        /*
            Attempt to auto assign the table name
         */

        /*
            Load the attribute columns
         */
        this.getAttributeColumns();
    }

    public static <T> T create(Class<T> modelClass)
    {
        try
        {
            return modelClass.newInstance();
        } catch ( InstantiationException | IllegalAccessException e )
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Tries to automatically assign the table name, first using pluralization
     * then by checking the DB mapped models, then if not throws an error
     */
    private String autoAssignTableName()
    {
        this.tableName = English.plural(
                Str.CamelCaseToSnakeCase(this.getClass().getSimpleName())
        );

        return this.tableName;
    }

    /**
     * Loads the attributes with the proper columns
     */
    public void getAttributeColumns()
    {
        ColumnsCollection collection = DB.table(this.getTableName()).columns();

        for ( int i = 0; i < collection.data().size(); i++ )
        {
            this.attributes.add(collection.data(i));
        }
    }

    /**
     * Return the table name
     */
    public String getTableName()
    {
        return tableName;
    }

    /**
     * Returns the primary key
     */
    public String getPrimaryKey()
    {
        return primaryKey;
    }

    /**
     * Returns the Original Database Values
     */
    public ColumnsCollection getOriginal()
    {
//        System.out.println(this.original);

        return this.original;
    }

    /**
     * Sets the Original List
     *
     * @param data
     */
    @Override
    public void setOriginal(ColumnsCollection data)
    {
//        System.out.println(data);

        this.original = data;

//        System.out.println(this.original);
    }

    /**
     * Gets the current attributes list
     */
    public ColumnsCollection getAttributes()
    {
        return attributes;
    }

    /**
     * Sets the attributes list
     *
     * @param data
     */
    @Override
    public void setAttributes(ColumnsCollection data)
    {
        this.attributes = data;
    }

    /**
     * Gets a value from the original data set
     *
     * @param key
     * @param <T>
     */
    public <T> T get(String key)
    {
        for ( int i = 0; i < this.attributes.count(); i++ )
        {
            if ( Objects.equals(key, this.attributes.data(i).getField()) )
            {
                return (T) this.attributes.data(i).getValue();
            }
        }

        return null;
    }

    /**
     * Gets the object type in the get method
     *
     * @param key
     */
    public String getType(String key)
    {
        if ( this.get(key) != null )
        {
            return this.get(key).getClass().getName();
        }

        return null;
    }

    /**
     * Is the Model new or has it been saved to the database?
     */
    public boolean isNew()
    {
        return this.original.data().isEmpty();
    }

    public void put(String field, Object value) throws ModelException
    {
        if ( ! isAllowedToFill(field) )
        {
            // System.out.println("'" + field + "' is not fillable!");
            throw new ModelException("Field '" + field + "' is not fillable!!!");
        }

        if ( this.attributes.hasField(field) )
        {
            this.attributes.put(field, value);

            return;
        }

        throw new ModelException("Field '" + field + "' does not exist!");
    }

    public static String identify()
    {
        return Model.class.getSimpleName();
    }

    public static <T> T first()
    {
        System.out.println(identify());

        return (T) new Object();
    }

    /**
     * Output the model to string
     */
    @Override
    public String toString()
    {
        return this.buildOutput();
    }

    private String buildOutput()
    {
        String output = "";

        List<String> attributes = new ArrayList();

        for ( int i = 0; i < this.getAttributes().count(); i++ )
        {
            output += "[" + this.getAttributes().data(i).getField() + " = " + this.getAttributes().data(i).getValue() + "]\n";
        }

        return output;
    }
}
