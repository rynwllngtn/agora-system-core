package dev.rynwllngtn;

import dev.rynwllngtn.daos.DaoFactory;
import dev.rynwllngtn.daos.user.UserDao;
import dev.rynwllngtn.entities.user.User;
import dev.rynwllngtn.utils.DatabaseUtil;

import java.text.ParseException;

public class Main {

    static void main() throws ParseException {

        UserDao userDao = DaoFactory.createUserDao();
        User newUser = new User("12312312312", "123123123");

        //Insert
        userDao.insert(newUser);

        //Update
        newUser.setName("Username");
        newUser.setEmail("user.email@teste.com");
        userDao.update(newUser);

        //Find by id
        IO.println(userDao.findById(newUser.getId()) + "\n");

        //Find all
        userDao.findAll().stream().forEach(user -> IO.println(user.getName() + ", " + user.getId()));

        //Delete
        userDao.deleteById(newUser.getId());

        DatabaseUtil.closeConnection();
    }

}