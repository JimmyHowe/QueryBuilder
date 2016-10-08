package com.jimmyhowe.db.query.statements;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.objects.ValueStore;
import com.jimmyhowe.db.proccessor.ResultSetProcessor;
import com.jimmyhowe.db.query.QueryBuilder;

/**
 * The Insert Statement
 */
public class InsertStatement extends Statement
{
    private ValueStore columns = new ValueStore();
    private ValueStore values = new ValueStore();

    public InsertStatement(Connector connector, QueryBuilder queryBuilder, ResultSetProcessor processor)
    {
        super(connector, queryBuilder, processor);

        for ( String column : this.queryBuilder.columns )
        {
            this.columns.add(column);
        }
    }

    public String values(String... values)
    {
        for ( String value : values )
        {
            this.values.add(value);
        }

        return this.toSql();
    }

    @Override
    String compile()
    {
        String sql = "INSERT INTO ";

        sql = sql + this.queryBuilder.getTableName() + " ";

        sql = sql + this.columns.toWrappedInBraces();

        sql = sql + " VALUES " + this.values.addQuotes().toWrappedInBraces();

        DB.log(sql);

        return sql;
    }
}
