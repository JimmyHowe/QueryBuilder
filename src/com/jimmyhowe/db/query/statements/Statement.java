package com.jimmyhowe.db.query.statements;

import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.proccessor.ResultSetProcessor;
import com.jimmyhowe.db.query.QueryBuilder;

/**
 * Abstract MySQL Statement
 */
public abstract class Statement
{
    protected final Connector connector;
    final QueryBuilder queryBuilder;
    final ResultSetProcessor processor;

    Statement(Connector connector, QueryBuilder queryBuilder, ResultSetProcessor processor)
    {
        this.connector = connector;
        this.queryBuilder = queryBuilder;
        this.processor = processor;
    }

    public Statement(Connector connector, QueryBuilder queryBuilder)
    {
        this.connector = connector;
        this.queryBuilder = queryBuilder;
        this.processor = null;
    }

    /**
     * Compiles the Statement
     */
    abstract String compile();

    public String toSql()
    {
        return this.compile();
    }
}
