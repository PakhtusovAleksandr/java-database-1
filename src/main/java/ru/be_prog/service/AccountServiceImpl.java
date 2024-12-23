package ru.be_prog.service;

import ru.be_prog.Account;
import ru.be_prog.dao.AccountDao;
import ru.be_prog.dao.AccountDaoJdbcImpl;

import java.util.List;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private final static AccountDao accountDao = new AccountDaoJdbcImpl();

    @Override
    public void createAccountTable() {
        accountDao.createAccountTable();
    }

    @Override
    public void dropAccountTable() {
        accountDao.dropAccountTable();
    }

    @Override
    public void deleteAllAccounts() {
        accountDao.deleteAllAccounts();
    }

    @Override
    public void createAccount(Account account) {
        accountDao.createAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(UUID id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public Account findAccount(UUID id) {
        return accountDao.findAccount(id);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountDao.findAllAccounts();
    }
}
