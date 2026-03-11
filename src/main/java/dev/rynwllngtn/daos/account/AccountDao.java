package dev.rynwllngtn.daos.account;

import dev.rynwllngtn.entities.account.Account;

import java.util.List;

public interface AccountDao {

    void insert(Account account);
    void update(Account account);
    void deleteById(int id);
    Account findById(int id);
    List<Account> findAll();

}