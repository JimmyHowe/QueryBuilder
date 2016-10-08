package com.jimmyhowe.db.contracts;

import com.jimmyhowe.db.table.columns.ColumnsCollection;

import java.util.Objects;

/**
 * Interface with default methods for loading Models with Data
 *
 * @param <T>
 */
public interface Modelable<T>
{
    ColumnsCollection getOriginal();

    void setOriginal(ColumnsCollection data);

    ColumnsCollection getAttributes();

    void setAttributes(ColumnsCollection data);

    default T init(ColumnsCollection data)
    {
        this.setOriginal(data);
        this.setAttributes(data);

        return (T) this;
    }

    String getPrimaryKey();

    default boolean isAllowedToFill(String field)
    {
        String[] fields = this.fillable().split(",");

        for ( String fillableField : fields )
        {
            if( Objects.equals(fillableField.trim(), field) )
            {
                return true;
            }
        }

        return false;
    }

    default String fillable()
    {
        return "";
    }
}
