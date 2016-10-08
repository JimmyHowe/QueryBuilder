package com.jimmyhowe.db.table;

import com.jimmyhowe.db.table.columns.ColumnsCollection;
import com.jimmyhowe.db.collections.ModelCollection;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.models.Model;
import com.jimmyhowe.db.proccessor.ResultSetProcessor;
import com.jimmyhowe.db.query.QueryBuilder;
import com.jimmyhowe.db.query.statements.MetaStatement;
import com.jimmyhowe.db.query.statements.SelectStatement;

/**
 * The Table Manager Object
 */
public class TableManager
{
    private Connector connector;
    private QueryBuilder queryBuilder;

    public TableManager(Connector connector, String from)
    {
        this.connector = connector;
        this.queryBuilder = new QueryBuilder(from);
    }

    /**
     * Returns a collection of models
     */
    public ModelCollection get()
    {
        return new SelectStatement(this.connector, this.queryBuilder, new ResultSetProcessor(queryBuilder)).get();
    }

    /**
     * Returns the first row from DB as a model
     *
     * @param <T>
     */
    public <T extends Model> T first()
    {
        this.queryBuilder.limit = 1;

        return new SelectStatement(this.connector, queryBuilder, new ResultSetProcessor(queryBuilder)).first();
    }

    /**
     * Returns an array of columnNames
     */
    public String[] columnNames()
    {
        return new MetaStatement(this.connector, this.queryBuilder).columnNames();
    }

    public ColumnsCollection columns()
    {
        return new MetaStatement(this.connector, this.queryBuilder).columns();
    }

    public TableManager where(String column, int value)
    {
        this.queryBuilder.where(column, value);

        return this;
    }

    public TableManager orderBy(String column)
    {
        this.queryBuilder.orderBy(column);

        return this;
    }

//    public InsertStatement insert(String... columns)
//    {
//        return new InsertStatement(connector, queryBuilder, new ResultSetProcessor(queryBuilder)).columns(columns);
//    }
}
