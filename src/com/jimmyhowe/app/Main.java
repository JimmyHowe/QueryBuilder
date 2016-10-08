package com.jimmyhowe.app;

import com.jimmyhowe.app.adapters.DefaultAdapter;
import com.jimmyhowe.app.repositories.UserRepository;
import com.jimmyhowe.db.DB;
import com.jimmyhowe.db.collections.ModelCollection;
import com.jimmyhowe.db.models.Project;
import com.jimmyhowe.db.models.User;

/**
 * Created by Jimmy on 12/08/2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        DB.setConnector(new DefaultAdapter("jimmyhowe_com", "root", null));

        DB.map("users", new User());
        DB.map("projects", new Project());

        UserRepository userRepository = new UserRepository();

        User user = userRepository.find(1);

        ModelCollection<User> users = userRepository.oldFolk();

        System.out.println(users);
    }
}
