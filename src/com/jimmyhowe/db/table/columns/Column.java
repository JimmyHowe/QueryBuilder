package com.jimmyhowe.db.table.columns;

/**
 * Column
 */
public class Column
{
    private final String field;
    private final String type;

    private Object value;

    public Column(String field, String type, Object value)
    {
        this.field = field;
        this.type = type;
        this.value = value;
    }

    public String getField()
    {
        return field;
    }

    public String getType()
    {
        return type;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }
}
