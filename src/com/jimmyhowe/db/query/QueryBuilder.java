package com.jimmyhowe.db.query;

import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.proccessor.ResultSetProcessor;
import com.jimmyhowe.db.query.statements.InsertStatement;
import com.jimmyhowe.db.query.statements.SelectStatement;
import com.jimmyhowe.db.query.statements.UpdateStatement;
import com.jimmyhowe.db.query.statements.components.OrderBy;
import com.jimmyhowe.db.query.statements.components.Where;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the Building of Queries
 */
public class QueryBuilder
{
    private Connector connector;

    private final String tableName;

    public String[] columns = { "*" };

    public List<Where> wheres = new ArrayList<>();
    public List<OrderBy> orderBys = new ArrayList<>();

    public int limit;

    public QueryBuilder(String tableName)
    {
        this.tableName = tableName;
    }

    /**
     * Returns the tableName name
     * @return
     */
    public String getTableName()
    {
        return tableName;
    }

    public QueryBuilder select(String... columns)
    {
        this.columns = columns;

        return this;
    }

    /**
     * Returns Get Query
     */
    public Object get()
    {
        return new SelectStatement(this.connector, this, new ResultSetProcessor(this)).toSql();
    }

    /**
     * Get with Columns
     *
     * @param columns
     */
    public String get(String... columns)
    {
        this.columns = columns;

        return new SelectStatement(this.connector, this, new ResultSetProcessor(this)).toSql();
    }

    /**
     * Returns First Query
     */
    public String first()
    {
        this.limit = 1;

        return new SelectStatement(this.connector, this, new ResultSetProcessor(this)).toSql();
    }

    /**
     * Returns first query with columns
     *
     * @param columns
     */
    public String first(String... columns)
    {
        this.columns = columns;
        this.limit = 1;

        return new SelectStatement(this.connector, this, new ResultSetProcessor(this)).toSql();
    }

    /**
     * Return a list of ID, and Column Name
     *
     * @param column
     */
    public String list(String column)
    {
        return this.list(column, "id");
    }

    /**
     * List with primary key
     *
     * @param column
     * @param primaryKey
     */
    public String list(String column, String primaryKey)
    {
        return this.get("id", column);
    }

    /**
     * Where Statement
     *
     * @param left
     * @param right
     */
    public QueryBuilder where(String left, Object right)
    {
        this.wheres.add(new Where(left, right));

        return this;
    }

    /**
     * Where Statement
     *
     * @param column
     * @param value
     */
    public QueryBuilder where(String column, String operator, Object value)
    {
        this.wheres.add(new Where(column, operator, value));

        return this;
    }

    /**
     * Order By
     *
     * @param column
     * @param direction
     */
    public QueryBuilder orderBy(String column, String direction)
    {
        this.orderBys.add(new OrderBy(column, direction));

        return this;
    }

    /**
     * Order By
     *
     * @param column
     */
    public QueryBuilder orderBy(String column)
    {
        return this.orderBy(column, "ASC");
    }

    /**
     * Insert Statement
     *
     * @param columns
     */
    public InsertStatement insert(String... columns)
    {
        this.columns = columns;

        return new InsertStatement(this.connector, this, new ResultSetProcessor(this));
    }

    public UpdateStatement update(String... columns)
    {
        this.columns = columns;

        return new UpdateStatement(this.connector, this, new ResultSetProcessor(this));
    }
}
