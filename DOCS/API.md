# API

## DB Facade

Setting up the table facade

    DB.setConnection(new Connectable())
    
## The Table Object

The table object is used to run MySQL querys against the database, it is accessed like this...

    DB.table("test")

### Selects

To get all records from a table

    DB.table("test").get();

To get the first row of a table

    DB.table("test").first()

To get a listing from a table, this will return the id and the column specified

    DB.table("test").list("field");
    
### Wheres

Where clauses can be used from the table object, and can chain on select functions

    DB.table("test").where("field", "John").get();
    DB.table("test").where("field", "John").first();
    a
    DB.table("test").where("field", "!=", "John").get();
    DB.table("test").where("field", "!=", "John").first();

   
### Ordering
    
To order results by a column use the orderBy methods

    DB.table("test").orderBy("field").get()
    DB.table("test").orderByDesc("field").get()
    
    DB.table("test").where("age", ">=", 21).orderBy("field").get()
    
### Notes