package com.jimmyhowe.db.query.statements;

import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.collections.ModelCollection;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.objects.ValueStore;
import com.jimmyhowe.db.proccessor.ResultSetProcessor;
import com.jimmyhowe.db.query.QueryBuilder;

/**
 * The Select Statement Object
 */
public class SelectStatement extends Statement
{
    private ValueStore columns = new ValueStore();

    /**
     * @param connector
     * @param queryBuilder
     * @param processor
     */
    public SelectStatement(Connector connector, QueryBuilder queryBuilder, ResultSetProcessor processor)
    {
        super(connector, queryBuilder, processor);

        for ( String column : this.queryBuilder.columns )
        {
            this.columns.add(column);
        }
    }

    /**
     * Gets all records from the select statement
     */
    public ModelCollection get()
    {
        return this.processor.get(this.connector.run(this.toSql()));
    }

    /**
     * Gets the first row of the select statement
     *
     * @param <T>
     */
    public <T> T first()
    {
        // TODO: null pointer exception because connection is not set

        return this.processor.first(this.connector.run(this.toSql()));
    }

    /**
     * Compiles the SQL
     */
    String compile()
    {
        String sql = "SELECT ";

        sql = sql + this.columns.toCsv();

        sql = sql + " FROM " + this.queryBuilder.getTableName();

        if ( ! this.queryBuilder.wheres.isEmpty() )
        {
            sql += " " + this.queryBuilder.wheres.get(0).toString();
        }

        if ( ! this.queryBuilder.orderBys.isEmpty() )
        {
            sql += " " + this.queryBuilder.orderBys.get(0).toString();
        }

        if ( this.queryBuilder.limit > 0 )
        {
            sql += " LIMIT " + this.queryBuilder.limit;
        }

        DB.log(sql);

        return sql;
    }
}
