package ru.be_prog.service;

import ru.be_prog.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    void createAccountTable();
    void dropAccountTable();
    void deleteAllAccounts();
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(UUID id);
    Account findAccount(UUID id);
    List<Account> findAllAccounts();
}
