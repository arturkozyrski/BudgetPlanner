package com.kozyrski.budgetPlanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.sql.SQLException;


public class Chart extends GetData {


    private DefaultPieDataset dataset = new DefaultPieDataset();

    public Chart() throws SQLException {
        super();
    }

    public PieDataset getDataset() {
        return dataset;
    }

    public void setDataset(double incomeValue, double expensesValue) {
        this.dataset.setValue("com.kozyrski.budgetPlanner.Income", Math.floor(incomeValue));
        this.dataset.setValue("com.kozyrski.budgetPlanner.Expenses", Math.floor((Math.abs(expensesValue))));
    }

    public static JPanel createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "com.kozyrski.budgetPlanner.Chart of your spendings",  // chart title
                dataset,             // data
                false,               // include legend
                true,
                false

        );
        TextTitle t = chart.getTitle();
        t.setFont(new Font("Constantia", Font.BOLD, 26));
        PiePlot plot = (PiePlot) chart.getPlot();
        chart.setBackgroundPaint(new Color(244, 244, 244));
        plot.setLabelFont(new Font("Constantia", Font.PLAIN, 19));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.00);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelBackgroundPaint(Color.white);
        plot.setSectionPaint("com.kozyrski.budgetPlanner.Income", new GradientPaint(new Point(0, 0),

                new Color(113, 159, 1), new Point(0, 300), new Color(196, 248, 70)));
        chart.setBackgroundPaint(new Color(244, 244, 244));
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        plot.setShadowXOffset(3);
        plot.setShadowYOffset(20);

        plot.setSectionPaint("com.kozyrski.budgetPlanner.Expenses", new GradientPaint(new Point(0, 0),

                new Color(225, 53, 0), new Point(0, 300), new Color(167, 39, 0)));
        chart.setBackgroundPaint(new Color(244, 244, 244));
        return new ChartPanel(chart);

    }

    private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
        Point2D center = new Point2D.Float(0, 0);
        float radius = 200;
        float[] dist = {0.0f, 1.0f};
        return new RadialGradientPaint(center, radius, dist,
                new Color[]{c1, c2});
    }

}

