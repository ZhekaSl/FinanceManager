package financeManager.settings;

import financeManager.model.Currency;
import financeManager.model.Filter;

import java.io.PrintStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.ZipOutputStream;

public class Format {
    public static String amount(double amount) {
        return String.format(Settings.FORMAT_AMOUNT, amount);
    }

    public static String amount(double amount, Currency currency) {
        return amount(amount) + " " + currency.getCode();
    }

    public static String rate(double rate) {
        return String.format(Settings.FORMAT_RATE, rate);
    }

    public static String rate(double rate, Currency currency) {
        return rate(rate) + " " + currency.getCode();
    }

    public static String date(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE);
    }

    public static String dateMonth(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_MONTH);
    }

    public static String dateYear(Date date) {
        return dateFormat(date, Settings.FORMAT_DATE_YEAR);
    }

    private static String dateFormat(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, new MainDateFormatSymbols());
        return formatter.format(date);
    }

    public static double fromAmountToNumber(String amount) throws NumberFormatException {
        amount = amount.replaceAll(",", ".");
        return Double.parseDouble(amount);
    }

    public static String yesNo(boolean yes) {
        if (yes) return Text.get("YES");
        return Text.get("NO");
    }

    public static String getTitleFilter(Filter filter) {
        Date time = filter.getTo();
        switch (filter.getStep()) {
            case Filter.STEP_DAY -> date(time);
            case Filter.STEP_MONTH -> dateMonth(time);
            case Filter.STEP_YEAR -> dateYear(time);
        }
        return null;
    }

    private static class MainDateFormatSymbols extends DateFormatSymbols {
        @Override
        public String[] getMonths() {
            return Text.getMonths();
        }
    }
}
