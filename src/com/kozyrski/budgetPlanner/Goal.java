package com.kozyrski.budgetPlanner;

import java.sql.*;

public class Goal {
    private int id;
    private String name;
    private int timeInDays;
    private Date startingDate;
    private Date endDate;
    private double amount;
    private int reward;
    private int progress;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStmt;
    private String[] rank = {"Beginner", "Amateur", "Getting there", "Persistent"};

    public Goal(String name, int timeInDays, double amount) {
        this.name = name;
        this.amount = amount;
        setNewGoal(name, timeInDays, amount);
    }

    public Goal() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeInDays() {
        return timeInDays;
    }

    public void setTimeInDays(int timeInDays) {
        this.timeInDays = timeInDays;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public Income setNewGoal(String name, int timeInDays, double amount) {
        String command = "INSERT INTO goals (Name ,Time ,Amount ) VALUES(?,?,?)";
        ResultSet rs = null;
        Income income = null;

        try {
            income = new Income();
            connection = ConnectionFactory.getConnection();
            preparedStmt = connection.prepareStatement(command);
            preparedStmt.setString(1, name);
            preparedStmt.setInt(2, timeInDays);
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

    public Goal getGoal(int name) {
        String query = "SELECT * FROM goals WHERE ID = " + name;
        ResultSet rs = null;
        Goal goal = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next()) {
                goal = new Goal();
                goal.setId(rs.getInt("ID"));
                goal.setName(rs.getString("Name"));
                goal.setAmount(rs.getInt("Time"));
                goal.setAmount(rs.getDouble("Amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Util.close(rs);
            Util.close(statement);
            Util.close(connection);
        }
        return goal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress() {

        return progress;
    }

    public String[] getRank() {
        return rank;
    }

    public void setRank(String[] rank) {
        this.rank = rank;
    }
}

