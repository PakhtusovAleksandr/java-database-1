package ru.be_prog.dao;

import ru.be_prog.Account;
import ru.be_prog.util.DatabaseJdbcUtil;

import java.util.List;
import java.util.UUID;

public class AccountDaoJdbcImpl implements AccountDao {

    private static final DatabaseJdbcUtil DATABASE_JDBC_UTIL = new DatabaseJdbcUtil();

    @Override
    public void createAccountTable() {
        // Реализуем создание таблицы здесь
    }

    @Override
    public void dropAccountTable() {
        // Реализуем удаление таблицы здесь
    }

    @Override
    public void deleteAllAccounts() {
        // Реализуем удаление всех Account
    }

    @Override
    public void createAccount(Account account) {
        // Реализуем создание нового Account
    }

    @Override
    public void updateAccount(Account account) {
        // Реализуем обновление Account
    }

    @Override
    public void deleteAccount(UUID id) {
        // Реализуем удаление Account по id
    }

    @Override
    public Account findAccount(UUID id) {
        // Реализуем получение Account по id
        return null;
    }

    @Override
    public List<Account> findAllAccounts() {
        // Реализуем получение всех Account
        return List.of();
    }
}
