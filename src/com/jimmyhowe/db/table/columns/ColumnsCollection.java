package com.jimmyhowe.db.table.columns;

import com.jimmyhowe.support.collections.GeneralisedCollection;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class ColumnsCollection extends GeneralisedCollection<Column>
{
    public boolean hasField(String field)
    {
        for ( Column column : this.data() )
        {
            if ( column.getField().equals(field) ) return true;
        }

        return false;
    }

    public void put(String field, Object value)
    {
        for ( int i = 0; i < this.data().size(); i++ )
        {
            if ( this.data(i).getField().equals(field) )
            {
                this.data(i).setValue(value);
            }
        }
    }
}
