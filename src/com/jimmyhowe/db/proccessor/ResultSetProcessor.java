package com.jimmyhowe.db.proccessor;

import com.jimmyhowe.db.table.columns.ColumnsCollection;
import com.jimmyhowe.db.collections.ModelCollection;
import com.jimmyhowe.db.contracts.Modelable;
import com.jimmyhowe.db.table.columns.Column;
import com.jimmyhowe.db.query.QueryBuilder;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Processes the results of a MySQL ResultSet
 */
public class ResultSetProcessor implements CanResolveModel
{
    /**
     * TYPE for converting MySQL columnNames to Java Objects
     */
    private static Map<String, Class> TYPE;

    static
    {
        TYPE = new HashMap<>();

        TYPE.put("INT UNSIGNED", Long.class);
        TYPE.put("INTEGER", Integer.class);
        TYPE.put("TINYINT", Byte.class);
        TYPE.put("SMALLINT", Short.class);
        TYPE.put("BIGINT", Long.class);
        TYPE.put("REAL", Float.class);
        TYPE.put("FLOAT", Double.class);
        TYPE.put("DOUBLE", Double.class);
        TYPE.put("DECIMAL", BigDecimal.class);
        TYPE.put("NUMERIC", BigDecimal.class);
        TYPE.put("BOOLEAN", Boolean.class);
        TYPE.put("CHAR", String.class);
        TYPE.put("VARCHAR", String.class);
        TYPE.put("LONG VARCHAR", String.class);
        TYPE.put("DATE", Date.class);
        TYPE.put("TIME", Time.class);
        TYPE.put("TIMESTAMP", Timestamp.class);
        TYPE.put("SERIAL", Integer.class);
    }

    private QueryBuilder queryBuilder;

    public ResultSetProcessor(QueryBuilder queryBuilder)
    {
        this.queryBuilder = queryBuilder;
    }

    /**
     * Returns a collection of models from the result set
     *
     * @param resultSet
     */
    public ModelCollection get(ResultSet resultSet)
    {
        try
        {
            return this.resultSetToModelCollection(resultSet);
        } catch ( IllegalAccessException | InstantiationException e )
        {
            e.printStackTrace();
        }

//        System.out.println("Not happened");

        return new ModelCollection();
    }

    /**
     * Returns a single model from the ResultSet
     *
     * @param resultSet
     * @param <T>
     */
    public <T> T first(ResultSet resultSet)
    {
        try
        {
            return this.resultSetToModel(resultSet);
        } catch ( IllegalAccessException | InstantiationException e )
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Processes the result set and returns a model from the mapped database models
     *
     * @param resultSet
     * @param <T>
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private <T> T resultSetToModel(ResultSet resultSet) throws IllegalAccessException, InstantiationException
    {
        Modelable<T> model = resolveModelableInstance();

        ColumnsCollection columnsCollection = new ColumnsCollection();

        try
        {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int numberOfColumns = resultSetMetaData.getColumnCount();

            while ( resultSet.next() )
            {
                for ( int i = 1; i <= numberOfColumns; i++ )
                {
                    String field = resultSetMetaData.getColumnName(i);
                    Object data = resultSet.getObject(i);
                    String sqlType = resultSetMetaData.getColumnTypeName(i);
                    Class castType = TYPE.get(sqlType.toUpperCase());
                    columnsCollection.add(new Column(field, sqlType, castType.cast(data)));
                }
            }

            return model.init(columnsCollection);

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Processes a result set and returns a collection of models
     *
     * @param resultSet
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> ModelCollection resultSetToModelCollection(ResultSet resultSet) throws IllegalAccessException, InstantiationException
    {
        ModelCollection<T> modelCollection = new ModelCollection<>();

        try
        {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int numberOfColumns = resultSetMetaData.getColumnCount();

            while ( resultSet.next() )
            {
                // TODO: NullPointerException when table not found in map()

                Modelable<T> modelInstance = resolveModelableInstance();

                ColumnsCollection columnsCollection = new ColumnsCollection();

                for ( int i = 1; i <= numberOfColumns; i++ )
                {
                    String field = resultSetMetaData.getColumnName(i);
                    Object data = resultSet.getObject(i);
                    String sqlType = resultSetMetaData.getColumnTypeName(i);
                    Class castType = TYPE.get(sqlType.toUpperCase());

                    columnsCollection.add(new Column(field, sqlType, castType.cast(data)));
                }

                modelCollection.add(modelInstance.init(columnsCollection));
            }

            return modelCollection;

        } catch ( SQLException e )
        {
            e.printStackTrace();
        }

        return new ModelCollection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTableName()
    {
        return this.queryBuilder.getTableName();
    }
}
