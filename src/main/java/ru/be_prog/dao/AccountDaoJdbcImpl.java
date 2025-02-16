package ru.be_prog.dao;

import ru.be_prog.Account;
import ru.be_prog.util.DatabaseJdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountDaoJdbcImpl implements AccountDao {

    @Override
    public void createAccountTable() {
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            System.out.println("Create table in Db");
            Statement statement = connection.createStatement();
            String SQL = """
                    CREATE TABLE IF NOT EXISTS Account
                    (id UUID not NULL,login VARCHAR(50),name VARCHAR (50),lastName VARCHAR (50),email VARCHAR (50),PRIMARY KEY (id))
                    """;
            statement.executeUpdate(SQL);
            System.out.println("Table is created");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropAccountTable() {
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            System.out.println("Delete table from Db");
            Statement statement = connection.createStatement();
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
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Delete all account in Db");
            statement.executeUpdate("DELETE from account");
            System.out.println("All account in Db is Deleted");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAccount(Account account) {
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            System.out.println("Add account in Db");
            String sql = "INSERT INTO account (id,login,name,lastname,email) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, account.getId());
            preparedStatement.setString(2, account.getLogin());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            String sql = "UPDATE account SET id=?, login=?, name=?, lastName=?, email=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, account.getId());
            preparedStatement.setString(2, account.getLogin());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getLastName());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setObject(6, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(UUID id) {
        int delete = 0;
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            Statement statement = connection.createStatement();
            System.out.println("Find account in Db for DELETE " + id);
            delete = statement.executeUpdate("DELETE FROM account WHERE id=" + "'" + id + "'");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (delete != 0) {
            System.out.println("Account " + id + " is Deleted");
        } else {
            System.out.println("ID not found for DELETE");
        }
    }

    @Override
    public Account findAccount(UUID id) {
        Account accountFirst = resultSetForAccount("SELECT * from account WHERE id=" + "'" + id + "'").getFirst();
        if (accountFirst.getId() != null) {
            System.out.println("UUID in Db is found");
            return accountFirst;
        } else {
            System.out.println("ID not found");
            return null;
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        List<Account> accountsAll = resultSetForAccount("SELECT * from account");
        if (accountsAll.isEmpty()) {
            System.out.println("Accounts not found");
        }
        return accountsAll;
    }

    public List<Account> resultSetForAccount(String result) {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DatabaseJdbcUtil.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(result);
            while (resultSet.next()) {
                Account account = new Account
                        (UUID.fromString(resultSet.getString(1)),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5));
                accounts.add(account);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
