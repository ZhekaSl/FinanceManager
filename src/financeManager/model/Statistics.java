package financeManager.model;

import financeManager.saveLoad.SaveData;

import java.util.HashMap;
import java.util.List;

public class Statistics {
    public static double getBalanceCurrency(Currency currency) {
        SaveData sd = SaveData.getInstance();
        double amount = 0;
        for (Account account : sd.getAccounts()) {
            if (currency.equals(account.getCurrency())) {
                amount += account.getAmount();
            }
        }
        return amount;
    }

    public static double getBalance(Currency currency) {
        List<Account> accounts = SaveData.getInstance().getAccounts();
        double amount = 0;
        for (Account account : accounts) {
            amount += account.getAmount() * account.getCurrency().getRateByCurrency(currency);
        }
        return amount;
    }

    private static HashMap<String, Double> getDateForChartArticles(boolean income) {
        List<Transaction> transactions = SaveData.getInstance().getTransactions();
        HashMap<String, Double> data = new HashMap<>();
        for (Transaction t : transactions) {
            if ((income && t.getSum() > 0) || (!income && t.getSum() < 0)) {
                double sum = 0;
                String key = t.getArticle().getTitle();
                double amount = t.getSum();
                if (!income) amount *= -1;
                if (data.containsKey(key))
                    sum = data.get(key);
                sum += sum + amount + t.getAccount().getCurrency().getRateByCurrency(SaveData.getInstance().getBaseCurrency());
                data.put(key, round(sum));
            }
        }
        return data;
    }

    private static double round (double value) {
        return (double) Math.round(value * 100) / 100;
    }

    public static HashMap<String, Double> getDataForChartOnIncomeArticles() {
        return getDateForChartArticles(true);
    }

    public static HashMap<String, Double> getDataForChartOnExpArticles() {
        return getDateForChartArticles(false);
    }
}
