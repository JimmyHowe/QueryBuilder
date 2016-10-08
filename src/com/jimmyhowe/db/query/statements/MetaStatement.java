package com.jimmyhowe.db.query.statements;

import com.jimmyhowe.db.table.columns.ColumnsCollection;
import com.jimmyhowe.db.Connector;
import com.jimmyhowe.db.table.columns.Column;
import com.jimmyhowe.db.query.QueryBuilder;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Container for metaData statements
 */
public class MetaStatement extends Statement
{
    private DatabaseMetaData metaData;

    public MetaStatement(Connector connector, QueryBuilder queryBuilder)
    {
        super(connector, queryBuilder);

        try
        {
            metaData = this.connector.connectWithAdapter(this.connector.getAdapter()).getConnection().getMetaData();
        } catch ( SQLException e )
        {
            e.printStackTrace();
        }
    }

    @Override
    String compile()
    {
        return null;
    }

    public ColumnsCollection columns()
    {
        ColumnsCollection collection = new ColumnsCollection();

        try
        {
            ResultSet resultSet = metaData.getColumns(
                    this.connector.getAdapter().getDatabase(),
                    null,
                    this.queryBuilder.getTableName(),
                    "%"
            );

            while ( resultSet.next() )
            {
                collection.add(new Column(resultSet.getString(4), resultSet.getString(6), null));
            }

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return collection;
    }

    public String[] columnNames()
    {
        List<String> columns = new ArrayList<>();

        try
        {
            ResultSet resultSet = metaData.getColumns(
                    this.connector.getAdapter().getDatabase(),
                    null,
                    this.queryBuilder.getTableName(),
                    "%"
            );

            while ( resultSet.next() )
            {
                columns.add(resultSet.getString(4));
            }

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return columns.toArray(new String[columns.size()]);
    }
}
