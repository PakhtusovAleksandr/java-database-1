package ru.be_prog;


import ru.be_prog.service.AccountService;
import ru.be_prog.service.AccountServiceImpl;

import java.util.UUID;


public class Application {

    public static void main(String[] args) {
        UUID ACCOUNT_ID = UUID.randomUUID();
        UUID ACCOUNT_ID_2 = UUID.randomUUID();
        UUID ACCOUNT_ID_3 = UUID.randomUUID();
        UUID ACCOUNT_ID_4 = UUID.randomUUID();
        String ACCOUNT_LOGIN = "test_login";
        String ACCOUNT_LOGIN_2 = "test_login_2";
        String ACCOUNT_LOGIN_3 = "test_login_3";
        String ACCOUNT_LOGIN_4 = "test_login_4";
        String ACCOUNT_NAME = "test_name";
        String ACCOUNT_NAME_2 = "test_name_2";
        String ACCOUNT_NAME_3 = "test_name_3";
        String ACCOUNT_NAME_4 = "test_name_4";
        String ACCOUNT_LAST_NAME = "test_last_name";
        String ACCOUNT_LAST_NAME_2 = "test_last_name_2";
        String ACCOUNT_LAST_NAME_3 = "test_last_name_3";
        String ACCOUNT_LAST_NAME_4 = "test_last_name_4";
        String ACCOUNT_EMAIL = "test_email@test.com";
        String ACCOUNT_EMAIL_2 = "test_email@test.com_2";
        String ACCOUNT_EMAIL_3 = "test_email@test.com_3";
        String ACCOUNT_EMAIL_4 = "test_email@test.com_4";


        Account accountFirst = new Account(
                ACCOUNT_ID,
                ACCOUNT_LOGIN,
                ACCOUNT_NAME,
                ACCOUNT_LAST_NAME,
                ACCOUNT_EMAIL
        );
        Account accountSecond = new Account(
                ACCOUNT_ID_2,
                ACCOUNT_LOGIN_2,
                ACCOUNT_NAME_2,
                ACCOUNT_LAST_NAME_2,
                ACCOUNT_EMAIL_2
        );
        Account accountThird = new Account(
                ACCOUNT_ID_3,
                ACCOUNT_LOGIN_3,
                ACCOUNT_NAME_3,
                ACCOUNT_LAST_NAME_3,
                ACCOUNT_EMAIL_3
        );
        Account accountFourth = new Account(
                ACCOUNT_ID_4,
                ACCOUNT_LOGIN_4,
                ACCOUNT_NAME_4,
                ACCOUNT_LAST_NAME_4,
                ACCOUNT_EMAIL_4
        );

        Account accountSecondUpdate = new Account(
                ACCOUNT_ID_2,
                ACCOUNT_LOGIN_4,
                ACCOUNT_NAME_4,
                ACCOUNT_LAST_NAME_4,
                ACCOUNT_EMAIL_4
        );

        Account accountThirdUpdate = new Account(
                ACCOUNT_ID_3,
                ACCOUNT_LOGIN,
                ACCOUNT_NAME,
                ACCOUNT_LAST_NAME,
                ACCOUNT_EMAIL
        );
        AccountService accountService = new AccountServiceImpl();

        //Create DB and 4 accounts
        accountService.createAccountTable();
        accountService.createAccount(accountFirst);
        accountService.createAccount(accountSecond);
        accountService.createAccount(accountThird);
        accountService.createAccount(accountFourth);
        //Update 2 accounts
        accountService.updateAccount(accountSecondUpdate);
        accountService.updateAccount(accountThirdUpdate);
        //Display all accounts
        accountService.findAllAccounts();
        //Delete 2 accountFirst for UUID
        accountService.deleteAccount(accountFirst.getId());
        accountService.deleteAccount(accountThird.getId());
        //Display all accounts
        accountService.findAllAccounts();
        //Delete all accounts and DB
        accountService.deleteAllAccounts();
        accountService.dropAccountTable();
    }
}
