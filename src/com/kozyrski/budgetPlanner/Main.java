package com.kozyrski.budgetPlanner;

import javax.swing.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        DatabaseMetaData dbm = ConnectionFactory.getConnection().getMetaData();
        ResultSet tableIncome = dbm.getTables(null, null, "income", null);
        ResultSet tableExpenses = dbm.getTables(null, null, "expenses", null);
        ResultSet tableGoals = dbm.getTables(null,null,"goals",null);
        if (tableIncome.next() && tableExpenses.next() && tableGoals.next()) {
            GUI gui = new GUI();
            gui.createAndShowGUI();
        } else {
            CreateTable createTable = new CreateTable();
            createTable.createTableExpenses();
            createTable.createTableIncome();
            createTable.createTableGoals();
            GUI gui = new GUI();
            gui.createAndShowGUI();
        }


    }
}


