package com.kozyrski.budgetPlanner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GetData {

    private Connection connection;
    private Statement statement;

    public GetData() {

    }

    public Expenses getExpenses(int id) {
        String query = "SELECT * FROM expenses WHERE ID=" + id;
        ResultSet rs = null;
        ResultSet rs2 = null;
        Expenses expenses = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);


            if (rs.next()) {
                expenses = new Expenses();
                expenses.setId(rs.getInt("ID"));

                expenses.setDate(rs.getDate("Date"));
                expenses.setCategory(rs.getString("Category"));
                expenses.setAmount(rs.getDouble("Amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return expenses;
    }

    public Expenses getPositionsWithSpecifiedDateExpenses(int id) {
        id = id + 1;
        String query = "SELECT SUM(Amount)as Amount FROM expenses WHERE MONTH(Date) =" + id;
        ResultSet rs = null;
        Expenses expenses = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);


            if (rs.next()) {
                expenses = new Expenses();
                expenses.setSum(rs.getDouble("Amount"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return expenses;
    }

    public Income getPositionsWithSpecifiedDateIncome(int month) {
        month = month + 1;
        String query = "SELECT SUM(Amount)as Amount FROM income WHERE MONTH(Date) =" + month;
        ResultSet rs = null;
        Income income = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);


            if (rs.next()) {
                income = new Income();
                income.setSum(rs.getDouble("Amount"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return income;
    }

    public ArrayList<Income> getIncomeByDate(int month) {
        ArrayList<Income> incomeList = new ArrayList<>();
        month = month + 1;
        String query = "SELECT * FROM income WHERE MONTH(Date) =" + month;
        ResultSet rs = null;
        Income income ;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);


            while (rs.next()) {
                income = new Income();
                income.setId(rs.getInt("ID"));
                income.setDate(rs.getDate("Date"));
                income.setCategory(rs.getString("Category"));
                income.setAmount(rs.getDouble("Amount"));
                income.setSum(rs.getDouble("Amount"));
                incomeList.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return incomeList;
    }
    public ArrayList<Expenses> getExpensesByDate(int month) {
        ArrayList<Expenses> expensesList= new ArrayList<>();
        month = month + 1;
        String query = "SELECT * FROM expenses WHERE MONTH(Date) =" + month;
        ResultSet rs = null;
        Expenses expenses;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);


            while (rs.next()) {
                expenses = new Expenses();
                expenses.setId(rs.getInt("ID"));
                expenses.setDate(rs.getDate("Date"));
                expenses.setCategory(rs.getString("Category"));
                expenses.setAmount(rs.getDouble("Amount"));
                expenses.setSum(rs.getDouble("Amount"));
                expensesList.add(expenses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return expensesList;
    }


    public Income getIncome(int id) {
        String query = "SELECT * FROM income WHERE ID=" + id;
        ResultSet rs = null;
        Income income = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                income = new Income();
                income.setId(rs.getInt("ID"));
                income.setDate(rs.getDate("Date"));
                income.setCategory(rs.getString("Category"));
                income.setAmount(rs.getDouble("Amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return income;
    }


    public Expenses getSumExpenses() {
        String query = "SELECT SUM(Amount)as Amount FROM expenses";
        ResultSet rs = null;

        Expenses expenses = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                expenses = new Expenses();
                expenses.setSum(rs.getDouble("Amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return expenses;
    }

    public Income getSumIncome() {
        String query = "SELECT SUM(Amount)as Amount FROM income";
        ResultSet rs = null;

        Income income = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                income = new Income();
                income.setSum(rs.getDouble("Amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return income;
    }

    public Income getNoOfPositionsIncome() {
        String query = "SELECT COUNT(Id) FROM income";
        ResultSet rs = null;
        Income income = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                income = new Income();
                income.setLastId(rs.getDouble("Id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return income;
    }

    public Expenses getNoOfPositionsExpense() {
        String query = "SELECT COUNT(Id) FROM expenses";
        ResultSet rs = null;

        Expenses expenses = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                expenses = new Expenses();
                expenses.setLastId(rs.getDouble("Id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return expenses;
    }
}