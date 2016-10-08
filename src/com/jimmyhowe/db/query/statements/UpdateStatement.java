package com.jimmyhowe.db.query.statements;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.objects.ValueStore;
import com.jimmyhowe.db.proccessor.ResultSetProcessor;
import com.jimmyhowe.db.query.QueryBuilder;
import com.jimmyhowe.db.query.statements.components.Where;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class UpdateStatement extends Statement
{
    private ValueStore columns = new ValueStore();
    private ValueStore wheres = new ValueStore();
    private ValueStore values = new ValueStore();

    public UpdateStatement(Connector connector, QueryBuilder queryBuilder, ResultSetProcessor processor)
    {
        super(connector, queryBuilder, processor);

        for ( String column : this.queryBuilder.columns )
        {
            this.columns.add(column);
        }
    }

    public String values(Object... values)
    {
        for ( Object value : values )
        {
            this.values.add(value);
        }

        return this.toSql();
    }

    @Override
    String compile()
    {
        String sql = "UPDATE ";

        sql = sql + this.queryBuilder.getTableName() + " SET ";

        sql = sql + ValueStore.UpdateThing(this.columns, this.values);

        sql = sql + " " + this.wheres.get(0);

        DB.log(sql);

        return sql;
    }

    public UpdateStatement where(String column, Object value)
    {
        this.wheres.add(new Where(column, value));

        return this;
    }
}
