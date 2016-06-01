package com.kozyrski.budgetPlanner;

import java.sql.*;

public class SetData {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStmt;
    public SetData() {

    }

    public Expenses setExpenses(Date date, String category, double amount) {
        String command = "INSERT INTO expenses (Category, Date ,Amount ) VALUES(?,?,?)";
        ResultSet rs = null;
        Expenses expenses = null;

        String sql = "INSERT INTO expenses (ID,Category, Date, Amount) values(?, ?, ?, ?)";

        try {
            expenses = new Expenses();
            connection = ConnectionFactory.getConnection();
            preparedStmt = connection.prepareStatement(command);
            preparedStmt.setString(1, category);
            preparedStmt.setDate(2, date);
            preparedStmt.setDouble(3, amount);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return expenses;
    }

    public Income setIncome(String category, Date date, double amount) {

        String command = "INSERT INTO income (Category ,Date ,Amount ) VALUES(?,?,?)";
        ResultSet rs = null;
        Income income = null;

        try {
            income = new Income();
            connection = ConnectionFactory.getConnection();
            preparedStmt = connection.prepareStatement(command);
            preparedStmt.setString(1, category);
            preparedStmt.setDate(2, date);
            preparedStmt.setDouble(3, amount);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return income;
    }


    public void deleteIncomeOrExpense(boolean isExpense,String category, Date date, Double amount) {
        String command="";
if(isExpense){
     command = "DELETE FROM expenses WHERE Category= ? AND Date= ? AND Amount= ?";
}
else {
     command = "DELETE FROM income WHERE Category= ? AND Date= ? AND Amount= ?";
}
        ResultSet rs = null;
        Income income = null;
        PreparedStatement preparedStatement = null;
        try {
            income = new Income();
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, category);
            preparedStatement.setDate(2, date);
            preparedStatement.setDouble(3, amount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
    }

}
