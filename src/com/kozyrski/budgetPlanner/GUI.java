package com.kozyrski.budgetPlanner;
// Icon made by [flaticon] from www.flaticon.com
//by Designerz Base.
// Icon made by [icomoon] from www.flaticon.com

import org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.*;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class GUI extends Chart {
    Calendar cal = Calendar.getInstance();
    JLabel date = new JLabel("Date");
    JLabel amount = new JLabel("Amount");
    JLabel category = new JLabel("Category");
    int lMonth = cal.get(Calendar.MONTH);
    JFrame frame = new JFrame("My Budget Planner 1.0");
    JLabel expenseLabel;
    JLabel incomeLabel;
    JLabel balanceData;
    ImageIcon icon = new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\icon.png");
    JTabbedPane tabbedPane = new JTabbedPane();
    Tips tips = new Tips();
    Box box = new Box(0);
    GetData getData = new GetData();
    SetData setData = new SetData();
    double balanceValue;
    JTable transactionTable = null;
    JTableHeader tableHeader = null;
    JPanel transactionsPanel = new JPanel();
    Font font = new Font("Constantia", Font.PLAIN, 22);
    JButton incomeButton = new JButton();
    JButton expenseButton = new JButton();
    JPanel monthPanelTable = new JPanel();
    boolean isShowingExpenses;
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout layout = new GridBagLayout();
    Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Goal goal = new Goal();
    Border border;
    JLabel goalName = new JLabel();
    boolean areThereAnyIncome = false;
    boolean areThereAnyExpenses = false;
    JProgressBar progressBar = new JProgressBar();

    private static java.sql.Date convertUtilToSql(java.util.Date sqlDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date sDate = new java.sql.Date(sqlDate.getTime());
        sdf.format(sDate);
        return sDate;
    }

    public String getText(String path) {

        String scores = null;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return scores;
    }

    public GUI() throws SQLException {
        progressBar.setPreferredSize(new Dimension(100, 80));
        java.text.SimpleDateFormat formatMonth = new java.text.SimpleDateFormat("MMMM");
        setDataset(getPositionsWithSpecifiedDateIncome(lMonth).getSum(), getPositionsWithSpecifiedDateExpenses(lMonth).getSum());
        balanceData = new JLabel();

        JPanel spendingPanel = new JPanel();
        JPanel goalsPanel = new JPanel();
        JPanel graphPanel = new JPanel();
        JTextPane tipsText = new JTextPane();
        JPanel allButtonsPanel = new JPanel();
        JPanel tipPanel = new JPanel();

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        balanceValue = getData.getSumExpenses().getSum() + getData.getSumIncome().getSum();
        incomeLabel = new JLabel();
        font = new Font("Constantia", Font.PLAIN, 19);

        incomeLabel.setFont(font);
        refreshIncomeAndBalance();
        expenseLabel = new JLabel();
        expenseLabel.setFont(font);
        refreshExpensesAndBalance();
        balanceData.setFont(font);
        balanceData.setText(String.valueOf(balanceValue));

        JLabel tip = new JLabel();
        tip.setIcon(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\savingIcon.png"));
        tip.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        tip.setText(tips.getRandomTip());
        incomeButton.setIcon(new ImageIcon("com.kozyrski.budgetPlanner.Income.png"));
        incomeButton.setFont(new Font("GeosansLigh", Font.PLAIN, 17));
        incomeButton.setText("com.kozyrski.budgetPlanner.Income");
        incomeButton.setPreferredSize(new Dimension(150, 60));

        expenseButton.setIcon(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\Expense.png"));
        expenseButton.setFont(new Font("GeosansLigh", Font.PLAIN, 17));
        expenseButton.setText("com.kozyrski.budgetPlanner.Expenses");
        expenseButton.setPreferredSize(new Dimension(150, 60));


        JPanel monthPanel = new JPanel();
        monthPanel.setPreferredSize(new Dimension(600, 80));
        monthPanel.setLayout(layout);
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.gridx = 0;
        JButton monthButton = getMonthButton(formatMonth);
        monthPanel.add(getLeftArrowButtonInLandingTab(formatMonth, monthButton), gbc);
        gbc.gridx = 1;
        monthPanel.add(box.createHorizontalStrut(150), gbc);
        gbc.gridx = 2;

        monthPanel.add(monthButton, gbc);
        gbc.gridx = 3;
        monthPanel.add(box.createHorizontalStrut(150), gbc);
        gbc.gridx = 4;

        monthPanel.add(getRightArrowButtonInLandingTab(formatMonth, monthButton), gbc);
        tipPanel.add(tip);

        spendingPanel.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        spendingPanel.add(monthPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        spendingPanel.add(allButtonsPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        spendingPanel.add(tipPanel, gbc);

        allButtonsPanel.setLayout(layout);

        gbc.ipady = 0;
        gbc.insets = new Insets(40, 40, 40, 40);
        gbc.gridx = 0;
        gbc.gridy = 0;
        allButtonsPanel.add(new JLabel(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\IncomeIcon.png")), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        allButtonsPanel.add(incomeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        allButtonsPanel.add(new JLabel(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\ExpenseIcon.png")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        allButtonsPanel.add(expenseLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        allButtonsPanel.add(new JLabel(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\BalanceIcon.png")), gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        allButtonsPanel.add(balanceData, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        allButtonsPanel.add(incomeButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        allButtonsPanel.add(expenseButton, gbc);

        incomeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                getIncomeWindow();
            }
        });
        expenseButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                getExpensesWindow();
            }
        });

        monthPanelTable.setPreferredSize(new Dimension(600, 70));

        monthPanelTable.setLayout(layout);
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.gridx = 0;


        JButton monthButtonPanel = getMonthButtonPanel(formatMonth);
        monthPanelTable.add(getLeftArrowButtonInTableTab(formatMonth, monthButtonPanel), gbc);
        gbc.gridx = 1;
        monthPanelTable.add(box.createHorizontalStrut(150), gbc);
        gbc.gridx = 2;

        monthPanelTable.add(monthButtonPanel, gbc);
        gbc.gridx = 3;
        monthPanelTable.add(box.createHorizontalStrut(150), gbc);
        gbc.gridx = 4;
        monthPanelTable.add(getRightArrowButtonInTableTab(formatMonth, monthButtonPanel), gbc);

        if (getData.getNoOfPositionsExpense().getLastId() > 0) {
            JPanel containerForIncomeExpense = new JPanel();
            containerForIncomeExpense.setLayout(layout);
            incomeButton = new JButton(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\IncomeIcon.png"));
            incomeButton.setOpaque(false);
            incomeButton.setContentAreaFilled(true);
            incomeButton.setPreferredSize(new Dimension(295, 100));
            incomeButton.setBorder(loweredetched);
            expenseButton = new JButton(new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\ExpenseIcon.png"));
            expenseButton.setOpaque(false);
            expenseButton.setContentAreaFilled(true);
            expenseButton.setPreferredSize(new Dimension(295, 100));
            expenseButton.setBorder(loweredetched);
            gbc.gridy = 0;
            gbc.gridx = 0;
            containerForIncomeExpense.add(incomeButton, gbc);
            gbc.gridy = 1;
            containerForIncomeExpense.add(expenseButton);
            transactionsPanel.setBackground(Color.gray);
            transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.PAGE_AXIS));
            transactionsPanel.add(Box.createGlue());
            transactionsPanel.add(containerForIncomeExpense);
            transactionTable = new JTable((Object[][]) populateJTableIncomeByMonth(cal.get(Calendar.MONTH)), columnNames);
            transactionTable.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
            transactionTable.setBorder(loweredetched);
            transactionTable.setFillsViewportHeight(true);
            transactionTable.setShowVerticalLines(false);
            font = new Font("Constantia", Font.PLAIN, 22);
            transactionTable.setFont(font);
            transactionTable.setIntercellSpacing(new Dimension(0, 0));
            transactionTable.setRowHeight(25);
            transactionTable.setBackground(new Color(0, 203, 53));
            transactionsPanel.add(monthPanelTable);
            gbc.gridy = 4;
            tableHeader = transactionTable.getTableHeader();
            tableHeader.addMouseListener(new TableHeaderMouseListener(transactionTable));
            transactionsPanel.add(tableHeader);
            transactionTable.setAutoCreateRowSorter(true);
            transactionTable.addMouseListener(new tableMouseListener(transactionTable));

            gbc.gridy = 6;
            transactionsPanel.add(new JScrollPane(transactionTable));
            SwingUtilities.updateComponentTreeUI(transactionsPanel);

            incomeButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    isShowingExpenses = false;
                    refreshTransactionTable(false);
                }
            });
            expenseButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    isShowingExpenses = true;
                    refreshTransactionTable(true);
                }
            });
        } else {
            JLabel nothing = new JLabel("No transaction to show yet.");
            transactionsPanel.add(nothing);
        }
        tabbedPane.addTab("Spending", new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\Expense.png"), spendingPanel,
                "Tally of your spending and income");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        ImageIcon goalsIcon = new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\Award.png");
        tabbedPane.addTab("Goals", goalsIcon, goalsPanel,
                "Where most of your money goes");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        JLabel headerText = new JLabel();
        JLabel rank = new JLabel();
        JPanel container = new JPanel();
        JButton createNewGoal = new JButton("Create new goal +");
        JPanel setGoalPanel = new JPanel();
        JLabel goalLabel = new JLabel("What you saving for?");
        JLabel costLabel = new JLabel("How much does it cost?");
        JTextField goalNameJText = new JTextField();
        JTextField costJText = new JTextField();
        setGoalPanel.add(goalLabel);
        goalNameJText.setPreferredSize(new Dimension(80, 20));
        setGoalPanel.add(goalNameJText);
        setGoalPanel.add(costLabel);
        costJText.setPreferredSize(new Dimension(80, 20));
        setGoalPanel.add(costJText);
        JPanel emptyContainer = new JPanel();
        createNewGoal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, setGoalPanel,
                        "Set new goal", JOptionPane.OK_CANCEL_OPTION);
                progressBar = new JProgressBar();
                progressBar.setPreferredSize(new Dimension(30, 30));
                goal = new Goal(goalNameJText.getText(), 20, Double.parseDouble(costJText.toString()));
                border = BorderFactory.createTitledBorder(goal.getName());
                //progressBar.setBorder(border);
                progressBar.setValue(((int) getData.getSumIncome().getSum() * 100) / (int) goal.getAmount());
                goalName.setText(goalNameJText.getText());
                goalsPanel.add(progressBar);
                SwingUtilities.updateComponentTreeUI(goalsPanel);

            }
        });
        // headerText.setEditable(false);
        headerText.setBackground(new Color(244, 244, 244));
        headerText.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        headerText.setText("Set up your goals to help you achieve goals in real life");
        //  rank.setEditable(false);
        rank.setBackground(new Color(244, 244, 244));
        // rank.setText();
        rank.setFont(new Font("Constantia", Font.BOLD, 45));
        JLabel currentRankIs = new JLabel("Your current rank is: ");
        currentRankIs.setFont(new Font("Constantia", Font.PLAIN, 15));
        rank.setText(goal.getRank()[1]);
        rank.setPreferredSize(new Dimension(100, 50));
        JPanel emptyContainer1 = new JPanel();
        JPanel emptyContainer2 = new JPanel();
        emptyContainer2.setPreferredSize(new Dimension(360, 360));
        goalsPanel.setLayout(new BoxLayout(goalsPanel, BoxLayout.Y_AXIS));
        headerText.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentRankIs.setAlignmentX(Component.CENTER_ALIGNMENT);
        rank.setAlignmentX(Component.CENTER_ALIGNMENT);
        createNewGoal.setAlignmentX(Component.CENTER_ALIGNMENT);
        goalsPanel.add(headerText);
        emptyContainer1.setPreferredSize(new Dimension(150, 150));
        goalsPanel.add(emptyContainer1);
        goalsPanel.add(currentRankIs);
        goalsPanel.add(rank);
        goalsPanel.add(emptyContainer2);
        createNewGoal.setPreferredSize(new Dimension(80, 50));
        goalsPanel.add(createNewGoal);
        emptyContainer.setPreferredSize(new Dimension(190, 150));

        goalsPanel.add(emptyContainer);

        if (goal.getGoal(1).getAmount() != 0) {
            //  goalName.setText(goal.getGoal(1).getName());
            progressBar.setValue(((int) getData.getSumIncome().getSum() * 100) / (int) goal.getGoal(1).getAmount());
            progressBar.setStringPainted(true);
            border = BorderFactory.createTitledBorder(goal.getGoal(1).getName());
            progressBar.setForeground(Color.blue);
            progressBar.setBorder(border);
            goalsPanel.add(progressBar);
        }
        JPanel emptyContainer3 = new JPanel();
        emptyContainer3.setPreferredSize(new Dimension(50, 50));
        goalsPanel.add(emptyContainer3);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        ImageIcon transactionsIcon = new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\Transactions.png");
        tabbedPane.addTab("Transactions", transactionsIcon, transactionsPanel,
                "Show your transactions to date");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        ImageIcon graphIcon = new ImageIcon("com.kozyrski.budgetPlanner.Graphics\\Graph.png");
        JPanel monthPanelChart = new JPanel();
        monthPanelChart.setPreferredSize(new Dimension(600, 80));
        monthButton.setBorder(loweredetched);
        monthPanelChart.setLayout(layout);
        gbc.insets = new Insets(1, 1, 1, 1);
        gbc.gridx = 0;
        JButton monthButtonChart = getMonthButtonChart(formatMonth);
        monthPanelChart.add(getLeftArrowButtonInCharTab(formatMonth, graphPanel, monthPanelChart, monthButtonChart), gbc);
        gbc.gridx = 1;
        monthPanelChart.add(box.createHorizontalStrut(150), gbc);
        gbc.gridx = 2;
        monthPanelChart.add(monthButtonChart, gbc);
        gbc.gridx = 3;
        monthPanelChart.add(box.createHorizontalStrut(150), gbc);
        gbc.gridx = 4;

        monthPanelChart.add(getRightArrowButtonInChartTab(formatMonth, graphPanel, monthPanelChart, monthButtonChart), gbc);
        graphPanel.add(monthPanelChart);

        setDataset(getPositionsWithSpecifiedDateIncome(lMonth).getSum(), getPositionsWithSpecifiedDateExpenses(lMonth).getSum());
        graphPanel.add(createChart(getDataset()));
        tabbedPane.addTab("Graph", graphIcon, graphPanel,
                "Show graphs");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        tipsText.setBounds(10, 10, 700, 900);
        tipsText.setEditable(false);
        tipsText.setFont(new Font("Constantia", Font.PLAIN, 17));
        tipsText.setText(getText("\\Misc\\tips.txt"));
        ImageIcon savingIcon = new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\savingIcon.png");
        tabbedPane.addTab("Saving tips", savingIcon, new JScrollPane(tipsText), "If you want to know how to save more money and how to invest");
    }

    private JButton getMonthButton(SimpleDateFormat formatMonth) {
        JButton monthButton = getButtonWithCommonProperties(120, 70);
        monthButton.setFont(new Font("GeosansLight", Font.BOLD, 22));
        monthButton.setText(formatMonth.format(cal.getTime()));
        monthButton.setBorder(loweredetched);
        monthButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refreshIncomeAndBalance();
            }
        });
        return monthButton;
    }

    private JButton getMonthButtonPanel(SimpleDateFormat formatMonth) {
        JButton monthButtonPanel = getButtonWithCommonProperties(120, 70);
        monthButtonPanel.setText(formatMonth.format(cal.getTime()));
        monthButtonPanel.setFont(new Font("Constantia", Font.PLAIN, 22));
        monthButtonPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JXDatePicker picker = new JXDatePicker();
                picker.setDate(Calendar.getInstance().getTime());
                picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
            }
        });
        return monthButtonPanel;
    }


    private JButton getMonthButtonChart(SimpleDateFormat formatMonth) {
        JButton monthButtonChart = getButtonWithCommonProperties(120, 70);
        monthButtonChart.setFont(new Font("Constantia", Font.PLAIN, 22));
        monthButtonChart.setText(formatMonth.format(cal.getTime()));
        monthButtonChart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                refreshIncomeAndBalance();
            }
        });
        return monthButtonChart;
    }

    private JButton getButtonWithCommonProperties(int width, int height) {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    private JButton getRightArrowButtonInChartTab(final SimpleDateFormat formatMonth, final JPanel graphPanel, final JPanel monthPanelChart, final JButton monthButtonChart) {
        JButton rightArrowButtonInChartTab = getButtonWithCommonProperties(60, 70);
        rightArrowButtonInChartTab.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\rightIcon.png"));
        rightArrowButtonInChartTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cal.add(Calendar.MONTH, +1);
                lMonth = cal.get(Calendar.MONTH);
                monthButtonChart.setText(formatMonth.format(cal.getTime()));
                graphPanel.removeAll();
                graphPanel.add(monthPanelChart);
                setDataset(getPositionsWithSpecifiedDateIncome(lMonth).getSum(), getPositionsWithSpecifiedDateExpenses(lMonth).getSum());
                graphPanel.add(createChart(getDataset()));
                SwingUtilities.updateComponentTreeUI(graphPanel);
            }
        });
        return rightArrowButtonInChartTab;
    }

    private JButton getRightArrowButtonInTableTab(final SimpleDateFormat formatMonth, final JButton monthButtonPanel) {
        JButton rightArrowButtonInTableTab = getButtonWithCommonProperties(60, 70);
        rightArrowButtonInTableTab.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\rightIcon.png"));
        rightArrowButtonInTableTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cal.add(Calendar.MONTH, +1);
                monthButtonPanel.setText(formatMonth.format(cal.getTime()));
                populateJTableIncomeByMonth(cal.get(Calendar.MONTH));
                refreshTransactionTable(isShowingExpenses);
                SwingUtilities.updateComponentTreeUI(transactionsPanel);

            }
        });
        return rightArrowButtonInTableTab;
    }

    private JButton getRightArrowButtonInLandingTab(final SimpleDateFormat formatMonth, final JButton monthButton) {
        JButton rightArrowButtonInLandingTab = getButtonWithCommonProperties(60, 70);
        rightArrowButtonInLandingTab.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\rightIcon.png"));
        rightArrowButtonInLandingTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cal.add(Calendar.MONTH, +1);
                lMonth = cal.get(Calendar.MONTH);
                monthButton.setText(formatMonth.format(cal.getTime()));
                refreshIncomeAndBalance();
                refreshExpensesAndBalance();

            }
        });
        return rightArrowButtonInLandingTab;
    }

    private JButton getLeftArrowButtonInCharTab(final SimpleDateFormat formatMonth, final JPanel graphPanel, final JPanel monthPanelChart, final JButton monthButtonChart) {
        JButton leftArrowButtonInCharTab = getButtonWithCommonProperties(60, 70);
        leftArrowButtonInCharTab.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\leftIcon.png"));
        leftArrowButtonInCharTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cal.add(Calendar.MONTH, -1);
                lMonth = cal.get(Calendar.MONTH);
                monthButtonChart.setText(formatMonth.format(cal.getTime()));
                graphPanel.removeAll();
                graphPanel.add(monthPanelChart);
                setDataset(getPositionsWithSpecifiedDateIncome(lMonth).getSum(), getPositionsWithSpecifiedDateExpenses(lMonth).getSum());
                graphPanel.add(createChart(getDataset()));
                SwingUtilities.updateComponentTreeUI(graphPanel);

            }
        });
        return leftArrowButtonInCharTab;
    }

    private JButton getLeftArrowButtonInTableTab(final SimpleDateFormat formatMonth, final JButton monthButtonPanel) {
        JButton leftArrowButtonInTableTab = getButtonWithCommonProperties(60, 70);
        leftArrowButtonInTableTab.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\leftIcon.png"));
        leftArrowButtonInTableTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cal.add(Calendar.MONTH, -1);
                monthButtonPanel.setText(formatMonth.format(cal.getTime()));
                populateJTableIncomeByMonth(cal.get(Calendar.MONTH));
                refreshTransactionTable(isShowingExpenses);
                SwingUtilities.updateComponentTreeUI(transactionsPanel);

            }
        });
        return leftArrowButtonInTableTab;
    }

    private JButton getLeftArrowButtonInLandingTab(final SimpleDateFormat formatMonth, final JButton monthButton) {
        JButton leftArrowButtonInLandingTab = getButtonWithCommonProperties(60, 70);
        leftArrowButtonInLandingTab.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\leftIcon.png"));
        leftArrowButtonInLandingTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cal.add(Calendar.MONTH, -1);
                lMonth = cal.get(Calendar.MONTH);
                monthButton.setText(formatMonth.format(cal.getTime()));
                refreshIncomeAndBalance();
                refreshExpensesAndBalance();
            }
        });
        return leftArrowButtonInLandingTab;
    }

    static String[] columnNames = {
            "Category",
            "Date",
            "Amount",
            ""
    };

    public Object populateJTableIncomeByMonth(int month) {
        ArrayList<Income> list = getData.getIncomeByDate(month);

        Object[][] data = new Object[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getCategory();
            data[i][1] = list.get(i).getDate();
            data[i][2] = list.get(i).getAmount();
            data[i][3] = new JButton();

        }
        return data;
    }

    public Object populateJTableExpensesByMonth(int month) {
        ArrayList<Expenses> list = getData.getExpensesByDate(month);
        Object[][] data = new Object[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getCategory();
            data[i][1] = list.get(i).getDate();
            data[i][2] = list.get(i).getAmount();
            data[i][3] = new JButton();
        }
        return data;
    }

    public class TableHeaderMouseListener extends MouseAdapter {

        private JTable table;
        public TableHeaderMouseListener(JTable table) {
            this.table = table;
        }
        public void mouseClicked(MouseEvent event) {
            Point point = event.getPoint();
        }

    }

    public class tableMouseListener extends MouseAdapter {
        private JTable table;

        public tableMouseListener(JTable table) {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            Point point = e.getPoint();
            int row = table.rowAtPoint(point);
            int column = table.columnAtPoint(point);
            Object selectedObject = (Object) table.getModel().getValueAt(row, column);
            int result;

            if (selectedObject instanceof JButton) {
                result = JOptionPane.showConfirmDialog(transactionTable, "Do you really want to delete this positon?");
                if (result == JOptionPane.OK_OPTION) {
                    setData.deleteIncomeOrExpense(isShowingExpenses, table.getModel().getValueAt(row, column - 3).toString(), (Date) table.getModel().getValueAt(row, column - 2), (Double) table.getModel().getValueAt(row, column - 1));
                    refreshTransactionTable(isShowingExpenses);
                    refreshExpensesAndBalance();
                    refreshIncomeAndBalance();
                }
            }

        }
    }

    public void createAndShowGUI() {
        frame.setSize(new Dimension(750, 850));
        tabbedPane.setSize(new Dimension(500, 500));
        tabbedPane.setPreferredSize(new Dimension(500, 500));

        frame.setPreferredSize(new Dimension(500, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(icon.getImage());
        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    public void refreshTransactionTable(boolean isShowingExpenses) {
        if (isShowingExpenses) {
            transactionTable = new JTable((Object[][]) populateJTableExpensesByMonth(cal.get(Calendar.MONTH)), columnNames);
            transactionTable.setGridColor(new Color(224, 52, 0));
            transactionTable.setBackground(new Color(224, 52, 0));

        } else {
            transactionTable = new JTable((Object[][]) populateJTableIncomeByMonth(cal.get(Calendar.MONTH)), columnNames);
            transactionTable.setBackground(new Color(0, 203, 53));
        }
        transactionsPanel.removeAll();
        JPanel containerForIncomeExpense = new JPanel();
        containerForIncomeExpense.setPreferredSize(new Dimension(600, 110));
        containerForIncomeExpense.setLayout(layout);
        gbc.gridy = 0;
        gbc.gridx = 0;
        containerForIncomeExpense.add(incomeButton, gbc);
        gbc.gridy = 1;
        containerForIncomeExpense.add(expenseButton);
        transactionsPanel.setBackground(Color.gray);
        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.PAGE_AXIS));
        transactionsPanel.add(containerForIncomeExpense);
        transactionTable.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
        transactionTable.setBorder(loweredetched);
        transactionTable.setFillsViewportHeight(true);
        transactionTable.setShowVerticalLines(false);
        font = new Font("Segoe UI Symbol", Font.PLAIN, 22);
        transactionTable.setFont(font);
        transactionTable.setIntercellSpacing(new Dimension(0, 0));
        transactionTable.setRowHeight(25);
        transactionsPanel.add(monthPanelTable);
        gbc.gridy = 4;
        tableHeader = transactionTable.getTableHeader();
        tableHeader.addMouseListener(new TableHeaderMouseListener(transactionTable));
        transactionsPanel.add(tableHeader);
        transactionTable.setAutoCreateRowSorter(true);
        transactionTable.addMouseListener(new tableMouseListener(transactionTable));
        gbc.gridy = 6;
        transactionsPanel.add(new JScrollPane(transactionTable));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        SwingUtilities.updateComponentTreeUI(transactionsPanel);

    }

    class ImageRenderer extends DefaultTableCellRenderer {
        JButton lbl = new JButton();
        ImageIcon icon = new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\delete.png");

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            lbl.setIcon(icon);
            lbl.setOpaque(false);
            lbl.setContentAreaFilled(false);
            lbl.setBorderPainted(false);
            return lbl;
        }

    }

    private void getExpensesWindow() {
        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));
        date.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Date.png"));
        amount.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Amount.png"));
        category.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Category.png"));
        JFrame expenseWindow = new JFrame("Add expense");
        JPanel expensePanel = new JPanel();
        JTextField amountInput = new JTextField("0.00 PLN");
        amountInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                amountInput.setText("");
            }
        });
        String[] categoryStrings = {"Car", "Clothes", "Eating Out", "Food", "Coffee", "Bills", "Entertainment", "Gifts", "Holidays", "Kids", "Sports", "Travel"};
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        JComboBox categoryList = new JComboBox(categoryStrings);
        categoryList.setSelectedIndex(5);
        //  petList.addActionListener(super);
        GridBagConstraints gbc = new GridBagConstraints();
        expensePanel.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        expensePanel.add(date, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        expensePanel.add(picker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        expensePanel.add(amount, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;

        expensePanel.add(amountInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;

        expensePanel.add(category, gbc);
        gbc.gridx = 1;
        gbc.gridy = 16;

        expensePanel.add(categoryList, gbc);
        gbc.gridx = 0;
        gbc.gridy = 32;
        expensePanel.add(ok, gbc);
        gbc.gridx = 1;
        gbc.gridy = 32;
        expensePanel.add(cancel, gbc);
        expenseWindow.setIconImage(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Expense.png").getImage());
        expenseWindow.setMinimumSize(new Dimension(350, 350));
        expenseWindow.setLocationRelativeTo(frame);
        expenseWindow.add(expensePanel, BorderLayout.CENTER);
        expenseWindow.setVisible(true);

        ok.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.util.Date data = picker.getDate();
                Date sqlDate = convertUtilToSql(data);
                String sqlCat = categoryList.getSelectedItem().toString();
                double sqlAmount = Double.parseDouble(amountInput.getText()) * -1;
                expenseWindow.setVisible(false);
                setData.setExpenses(sqlDate, sqlCat, sqlAmount);
                areThereAnyExpenses = true;
                refreshExpensesAndBalance();

                refreshTransactionTable(isShowingExpenses);

            }
        });

        cancel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                expenseWindow.setVisible(false);
            }
        });
    }

    private void getIncomeWindow() {
        JXDatePicker picker = new JXDatePicker();
        picker.setDate(Calendar.getInstance().getTime());
        picker.setFormats(new SimpleDateFormat("yyyy.MM.dd"));

        date.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Date.png"));
        amount.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Amount.png"));
        category.setIcon(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\Category.png"));

        JFrame incomeWindow = new JFrame("Add your income");
        JPanel incomePanel = new JPanel();
        JTextField amountInput = new JTextField("0.00 PLN");
        amountInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                amountInput.setText("");
            }
        });
        String[] categoryStrings = {"Salary", "Gift", "Bonus", "Loyalties", "com.kozyrski.budgetPlanner.Tips", "Investments", "Rent", "Stock"};
        JButton ok = new JButton("Ok");
        JButton cancel = new JButton("Cancel");
        JComboBox categoryList = new JComboBox(categoryStrings);
        categoryList.setSelectedIndex(5);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        incomePanel.setLayout(layout);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        incomePanel.add(date, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        incomePanel.add(picker, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        incomePanel.add(amount, gbc);
        gbc.gridx = 1;
        gbc.gridy = 8;
        incomePanel.add(amountInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        incomePanel.add(category, gbc);
        gbc.gridx = 1;
        gbc.gridy = 16;
        incomePanel.add(categoryList, gbc);

        gbc.gridx = 0;
        gbc.gridy = 32;
        incomePanel.add(ok, gbc);
        gbc.gridx = 1;
        gbc.gridy = 32;
        incomePanel.add(cancel, gbc);

        incomeWindow.setIconImage(new ImageIcon("C:\\Users\\Artur\\workspace\\Sql\\src\\com.kozyrski.budgetPlanner.Graphics\\com.kozyrski.budgetPlanner.Income.png").getImage());
        incomeWindow.setMinimumSize(new Dimension(350, 350));
        incomeWindow.setLocationRelativeTo(frame);
        incomeWindow.add(incomePanel, BorderLayout.CENTER);
        incomeWindow.setVisible(true);

        ok.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                java.util.Date date = picker.getDate();
                Date sqlDate = convertUtilToSql(date);
                double sqlAmount = Double.parseDouble(amountInput.getText());
                String sqlCat = categoryList.getSelectedItem().toString();
                incomeWindow.setVisible(false);
                setData.setIncome(sqlCat, sqlDate, sqlAmount);
                refreshIncomeAndBalance();
                refreshTransactionTable(isShowingExpenses);
                areThereAnyIncome = true;

            }
        });

        cancel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                incomeWindow.setVisible(false);
            }
        });
    }

    private void refreshIncomeAndBalance() {
        balanceValue = getData.getSumExpenses().getSum() + getData.getSumIncome().getSum();
        incomeLabel.setText(String.valueOf(getPositionsWithSpecifiedDateIncome(lMonth).getSum()));
        balanceData.setText(String.valueOf(balanceValue));
    }

    private void refreshExpensesAndBalance() {
        balanceValue = getData.getSumExpenses().getSum() + getData.getSumIncome().getSum();
        expenseLabel.setText(String.valueOf(getPositionsWithSpecifiedDateExpenses(lMonth).getSum()));
        balanceData.setText(String.valueOf(balanceValue));
    }
}