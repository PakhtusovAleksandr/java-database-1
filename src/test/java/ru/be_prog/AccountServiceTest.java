package ru.be_prog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.be_prog.service.AccountService;
import ru.be_prog.service.AccountServiceImpl;

import java.util.List;
import java.util.UUID;

public class AccountServiceTest {
    private final AccountService accountService = new AccountServiceImpl();

    private static final UUID ACCOUNT_ID = UUID.randomUUID();
    private static final UUID ACCOUNT_ID_2 = UUID.randomUUID();
    private static final String ACCOUNT_LOGIN = "test_login";
    private static final String ACCOUNT_LOGIN_2 = "test_login_2";
    private static final String ACCOUNT_NAME = "test_name";
    private static final String ACCOUNT_NAME_2 = "test_name_2";
    private static final String ACCOUNT_LAST_NAME = "test_last_name";
    private static final String ACCOUNT_LAST_NAME_2 = "test_last_name_2";
    private static final String ACCOUNT_EMAIL = "test_email@test.com";
    private static final String ACCOUNT_EMAIL_2 = "test_email@test.com_2";

    @Test
    void testCreateTable() {
        try {
            accountService.dropAccountTable();
            accountService.createAccountTable();
        } catch (Exception e) {
            Assertions.fail("При создании таблицы произошла ошибка", e);
        }
    }

    @Test
    void testDoubleCreateTable() {
        try {
            accountService.createAccountTable();
            accountService.createAccountTable();
        } catch (Exception e) {
            Assertions.fail("При повторном создании таблицы произошла ошибка", e);
        }
    }

    @Test
    void testDoubleDropTable() {
        try {
//            accountService.createAccountTable(); - исходник, повторяет пред тест, поэтому поправил
//            accountService.createAccountTable();
            accountService.dropAccountTable();
            accountService.dropAccountTable();
        } catch (Exception e) {
            Assertions.fail("При повторном удалении таблицы произошла ошибка", e);
        }
    }

    @Test
    void testCreateAccount() {
        try {
            accountService.dropAccountTable();
            accountService.createAccountTable();

            Account account = new Account(
                    ACCOUNT_ID,
                    ACCOUNT_LOGIN,
                    ACCOUNT_NAME,
                    ACCOUNT_LAST_NAME,
                    ACCOUNT_EMAIL
            );
            accountService.createAccount(account);
            Account savedAccount = accountService.findAccount(ACCOUNT_ID);
            if (!account.equals(savedAccount)) {
                Assertions.fail("Некорректно добавлен Account, проверьте правильность запроса");
            }
        } catch (Exception e) {
            Assertions.fail("При создании аккаунта произошла ошибка", e);
        }
    }

    @Test
    void testUpdateAccount() {
        try {
            accountService.dropAccountTable();
            accountService.createAccountTable();

            Account newAccount = new Account(
                    ACCOUNT_ID,
                    ACCOUNT_LOGIN_2,
                    ACCOUNT_NAME_2,
                    ACCOUNT_LAST_NAME_2,
                    ACCOUNT_EMAIL_2
            );
            accountService.createAccount(newAccount);

            Account updatedAccount = new Account(
                    ACCOUNT_ID,
                    ACCOUNT_LOGIN,
                    ACCOUNT_NAME,
                    ACCOUNT_LAST_NAME,
                    ACCOUNT_EMAIL
            );
            accountService.updateAccount(updatedAccount);
            Account savedAccount = accountService.findAccount(ACCOUNT_ID);
            if (!updatedAccount.equals(savedAccount)) {
                Assertions.fail("Некорректно обновлен Account, проверьте правильность запроса");
            }
        } catch (Exception e) {
            Assertions.fail("При обновлении аккаунта произошла ошибка", e);
        }
    }

    @Test
    void testDeleteAccount() {
        try {
            accountService.dropAccountTable();
            accountService.createAccountTable();

            Account account1 = new Account(
                    ACCOUNT_ID,
                    ACCOUNT_LOGIN,
                    ACCOUNT_NAME,
                    ACCOUNT_LAST_NAME,
                    ACCOUNT_EMAIL
            );
            Account account2 = new Account(
                    ACCOUNT_ID_2,
                    ACCOUNT_LOGIN_2,
                    ACCOUNT_NAME_2,
                    ACCOUNT_LAST_NAME_2,
                    ACCOUNT_EMAIL_2
            );
            accountService.createAccount(account1);
            accountService.createAccount(account2);

            accountService.deleteAccount(ACCOUNT_ID);

            Account anotherAccount = accountService.findAllAccounts().getFirst();

            if (!anotherAccount.equals(account2)) {
                Assertions.fail("Некорректно реализовано удаление Account, был удален другой аккаунт, проверьте правильность запроса");
            }
        } catch (Exception e) {
            Assertions.fail("При удалении аккаунта произошла ошибка", e);
        }
    }

    @Test
    void testDeleteAllAccount() {
        try {
            accountService.dropAccountTable();
            accountService.createAccountTable();

            Account account1 = new Account(
                    ACCOUNT_ID,
                    ACCOUNT_LOGIN,
                    ACCOUNT_NAME,
                    ACCOUNT_LAST_NAME,
                    ACCOUNT_EMAIL
            );
            Account account2 = new Account(
                    ACCOUNT_ID_2,
                    ACCOUNT_LOGIN_2,
                    ACCOUNT_NAME_2,
                    ACCOUNT_LAST_NAME_2,
                    ACCOUNT_EMAIL_2
            );
            accountService.createAccount(account1);
            accountService.createAccount(account2);

            accountService.deleteAllAccounts();

            List<Account> allAccounts = accountService.findAllAccounts();
            if (!allAccounts.isEmpty()) {
                Assertions.fail("Некорректно реализовано удаление всех Account, должны удалится все аккаунты, проверьте правильность запроса");
            }
        } catch (Exception e) {
            Assertions.fail("При удалении аккаунта произошла ошибка", e);
        }
    }
}
