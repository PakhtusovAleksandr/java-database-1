package ru.be_prog.dao;

import ru.be_prog.Account;
import ru.be_prog.util.DatabaseJdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountDaoJdbcImpl implements AccountDao {

    @Override
    public void createAccountTable() {

        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            System.out.println("Create table in Db");
            Statement statement = connection.createStatement();
//Создание таблицы, думал через билдер, но так более понятно по лоховски- полный запрос виден
            // Добавил exists чтобы создавалась таблица когда ее еще нет
            String SQL = "CREATE TABLE IF NOT EXISTS Account " +
                    "(id UUID not NULL, " +
                    " login VARCHAR(50), " +
                    " name VARCHAR (50), " +
                    " lastName VARCHAR (50), " +
                    " email VARCHAR (50), " +
                    " PRIMARY KEY (id))";

            statement.executeUpdate(SQL);
            System.out.println("Table is created");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Реализуем создание таблицы здесь
    }

    @Override
    public void dropAccountTable() {
        // Реализуем удаление таблицы здесь


        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {

            System.out.println("Delete table from Db");
            Statement statement = connection.createStatement();
// Добавил в запрос exists, чтобы удалялась таблица когда она создана
            String SQL = "DROP TABLE IF EXISTS Account";
            statement.executeUpdate(SQL);

            System.out.println("Table is deleted");
            statement.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteAllAccounts() {
        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Delete all account in Db");
            statement.executeUpdate("DELETE from account");
            System.out.println("All account in Db is Deleted");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Реализуем удаление всех Account
    }

    @Override
    public void createAccount(Account account) {


        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Add account in Db");
            //Запись полного запроса в билдер
            StringBuilder builder = new StringBuilder();
            builder.append("VALUES ('");
            builder.append(account.getId()).append("','");
            builder.append(account.getLogin()).append("','");
            builder.append(account.getName()).append("','");
            builder.append(account.getLastName()).append("','");
            builder.append(account.getEmail());
            builder.append("')");

            System.out.println(builder);
            statement.executeUpdate("INSERT INTO account (id,login,name,lastname,email) " +
                    builder);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Реализуем создание нового Account
    }

    @Override
    public void updateAccount(Account account) {
        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            //Запись полного запроса
            StringBuilder builder = new StringBuilder();
            builder.append(" id='").append(account.getId()).append("'");
            builder.append(", login='").append(account.getLogin());
            builder.append("', name='").append(account.getName());
            builder.append("', lastName='").append(account.getLastName());
            builder.append("', email='").append(account.getEmail()).append("'");

            System.out.println("Change account " + builder);
            statement.executeUpdate("UPDATE account SET" + builder + " WHERE id= '" + account.getId() + "'");
            System.out.println("Account is changed =  " + builder);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Реализуем обновление Account
    }

    @Override
    public void deleteAccount(UUID id) {
        int delete = 0;
        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Find account UUID in Db for DELETE " + id);
            delete = statement.executeUpdate("DELETE FROM account WHERE id=" + "'" + id + "'");


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (delete != 0) {
            System.out.println("Account " + id + " is Deleted");
        } else {
            System.out.println("ID not found for DELETE");

        }

        // Реализуем удаление Account по id
    }

    @Override
    public Account findAccount(UUID id) {
        UUID findID = null;
        String findLogin = null;
        String findName = null;
        String findLastName = null;
        String findEmail = null;
        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Find account UUID in Db " + id);

            ResultSet resultSet = statement.executeQuery("SELECT * from account WHERE id=" + "'" + id + "'");
            resultSet.next();
            findID = UUID.fromString(resultSet.getString(1));
            findLogin = resultSet.getString(2);
            findName = resultSet.getString(3);
            findLastName = resultSet.getString(4);
            findEmail = resultSet.getString(5);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Реализуем получение Account по id
        if (findID != null) {
            System.out.println("UUID in Db is found");
            return new Account(findID, findLogin, findName, findLastName, findEmail);
        } else {
            System.out.println("ID not found");
            return null;
        }


    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DatabaseJdbcUtil.getMySQLConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Find ALL account in Db ");
            ResultSet resultSet = statement.executeQuery("SELECT * from account");
            while (resultSet.next()) {
                Account account = new Account(UUID.fromString(resultSet.getString(1)), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                accounts.add(account);
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (!accounts.isEmpty()) {
            for (Account account : accounts) {
                System.out.println(account);
            }
        } else {
            System.out.println("Account don't created");
        }

        // Реализуем получение всех account

        return accounts;
    }
}
