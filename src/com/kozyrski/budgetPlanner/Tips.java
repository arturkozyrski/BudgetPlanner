package com.kozyrski.budgetPlanner;

import java.util.ArrayList;
import java.util.Random;

public class Tips extends GetData {
    private ArrayList<String> tipList = new ArrayList<String>();
    Random random = new Random();

    public Tips() {
        tipList.add("Did you know? Currently you are earning  " + Math.floor(((getSumExpenses().getSum() + getSumIncome().getSum()) / 365) * 100) / 100 + " a day.");
        tipList.add("Did you know? Currently you are earning  " + Math.floor((getSumExpenses().getSum() + getSumIncome().getSum()) / 12) + " a month.");
        tipList.add("Did you know? Currently you are earning : " + Math.floor(((getSumExpenses().getSum() + getSumIncome().getSum()) / 525948.766) * 100) / 100 + " a minute.");
        tipList.add("Did you know? Currently you are earning  " + Math.floor(((getSumExpenses().getSum() + getSumIncome().getSum()) / 31556926) * 100) / 100 + " a second.");
        tipList.add("Did you know? You can afford " + Math.floor(((getSumExpenses().getSum() + getSumIncome().getSum()) / 3) * 100) / 100 + " cans of Tyskie beer");
        tipList.add("Did you know? You can afford " + Math.floor(((getSumExpenses().getSum() + getSumIncome().getSum()) / 3000) * 100) / 100 + " iPhones 6s");
        tipList.add("Did you know? You can afford " + Math.floor(((getSumExpenses().getSum() + getSumIncome().getSum()) / 2) * 100) / 100 + " Snickers bars");

    }

    public String getRandomTip() {

        return tipList.get(random.nextInt(7));
    }

}
