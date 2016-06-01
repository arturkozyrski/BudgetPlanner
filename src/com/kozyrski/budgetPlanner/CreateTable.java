package com.kozyrski.budgetPlanner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

    private Connection connection;
    private Statement statement;

    public CreateTable() {
    }
    public void createTableIncome() {

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            String createIncomeTable = "CREATE TABLE income(" +
                    "ID MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                    "Category VARCHAR(20), " +
                    "Date DATE, " +
                    "Amount DOUBLE, " +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(createIncomeTable);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(statement);
            Util.close(connection);
        }
    }

    public void createTableExpenses() {

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            String createExpensesTable = "CREATE TABLE expenses(" +
                    "ID MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                    "Category VARCHAR(20), " +
                    "Date DATE, " +
                    "Amount DOUBLE, " +
                    "PRIMARY KEY (id))";

            statement.executeUpdate(createExpensesTable);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(statement);
            Util.close(connection);
        }
    }

    public void createTableGoals() {

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            String createExpensesTable = "CREATE TABLE goals(" +
                    "ID MEDIUMINT NOT NULL AUTO_INCREMENT, " +
                    "Name VARCHAR(20), " +
                    "Time Integer, " +
                    "Amount DOUBLE, " +
                    "PRIMARY KEY (id))";

            statement.executeUpdate(createExpensesTable);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(statement);
            Util.close(connection);
        }
    }
}
