# Build

## To build and test

- Model check for database based on class name
- DB.facade returns Row data so that we can do joins etc
    or simple arrays, so that they can convert easily to 
    json etc
- Queryable Interface for where and order buys
- Possibly splitting DB.tableName and DB.get(Model) into two different classes

Possible apis to get around static complications 

    Models

    User user = DB.get(new User()).where("name", "Jimmy").orderBy("name").first();
    
    Collections
    
    Collection<User> users = DB.get(new User()).where("name", "Jimmy").orderBy("name").get();
    Collection<User> users = DB.get(new User()).where("name", "Jimmy").orderBy("name").all();
    
    DB.get(new User())      Returns QueryBuilder
    .where("name", "Jimmy") Returns QueryBuilder
    .orderBy("name")        Returns QueryBuilder
    .all()                  Returns Collection<User>
    
    DB
    
    DB.select("id", "name", "email").from("users").where("id", 1).first()
    
    RULES
    
    first() Returns 1
    get()   Returns Many