package dev.rynwllngtn.daos;

import dev.rynwllngtn.daos.account.AccountDao;
import dev.rynwllngtn.daos.account.AccountDaoImplementation;
import dev.rynwllngtn.daos.user.UserDao;
import dev.rynwllngtn.daos.user.UserDaoImplementation;
import dev.rynwllngtn.utils.DatabaseUtil;

public class DaoFactory {

    public static UserDao createUserDao() {
        return new UserDaoImplementation(DatabaseUtil.getConnection());
    }

    public static AccountDao createAccountDao() {
        return new AccountDaoImplementation();
    }

}