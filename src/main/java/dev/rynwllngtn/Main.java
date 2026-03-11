package dev.rynwllngtn;

import dev.rynwllngtn.daos.DaoFactory;
import dev.rynwllngtn.daos.user.UserDao;
import dev.rynwllngtn.entities.user.User;
import dev.rynwllngtn.utils.DatabaseUtil;

public class Main {

    static void main() {

        UserDao userDao = DaoFactory.createUserDao();

        User user = userDao.findById("a1b2c3d4-1111-4a5b-8c9d-0e1f2a3b4c5d");
        IO.println(user);

        DatabaseUtil.closeConnection();
    }

}