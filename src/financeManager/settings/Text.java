package financeManager.settings;

import java.util.HashMap;

final public class Text {
    private static HashMap<String, String> data = new HashMap<>();

    public static void init() {
        data.put("PROGRAM_NAME", "Домашняя бухгалтерия");
        data.put("MENU_FILE", "Файл");
        data.put("MENU_EDIT", "Правка");
        data.put("MENU_VIEW", "Вид");
        data.put("MENU_HELP", "Помощь");

        data.put("JANUARY", "Январь");
        data.put("FEBRUARY", "Февраль");
        data.put("MARCH", "Март");
        data.put("APRIL", "Апрель");
        data.put("MAY", "Май");
        data.put("JUNE", "Июнь");
        data.put("JULY", "Июль");
        data.put("AUGUST", "Август");
        data.put("SEPTEMBER", "Сентябрь");
        data.put("OCTOBER", "Октябрь");
        data.put("NOVEMBER", "Ноябрь");
        data.put("DECEMBER", "Декабрь");

        data.put("ERROR_NO_BASE_CURRENCY", "Необходима базовая валюта!");
        data.put("ERROR_AMOUNT_FORMAT", "Некорректный формат суммы");
        data.put("ERROR_RATE_INCORRECT", "Некорректное значение курса!");
        data.put("ERROR_ACCOUNT_EMPTY", "Вы не выбрали счёт!");
        data.put("ERROR_ARTICLE_EMPTY", "Вы не выбрали статью!");
        data.put("ERROR_CURRENCY_EMPTY", "Вы не выбрали валюту!");
        data.put("ERROR_CODE_EMPTY", "Вы не указали код!");
        data.put("ERROR_DATE_FORMAT", "Вы не выбрали дату!");
        data.put("ERROR_IS_EXISTS", "Такая запись уже существует!");
        data.put("ERROR_TITLE_EMPTY", "Вы не ввели название!");

        data.put("YES", "Да");
        data.put("NO", "Нет");
    }

    public static String[] getMonths() {
        String[] months = new String[12];
        months[0] = get("JANUARY");
        months[1] = get("FEBRUARY");
        months[2] = get("MARCH");
        months[3] = get("APRIL");
        months[4] = get("MAY");
        months[5] = get("JUNE");
        months[6] = get("JULY");
        months[7] = get("AUGUST");
        months[8] = get("SEPTEMBER");
        months[9] = get("OCTOBER");
        months[10] = get("NOVEMBER");
        months[11] = get("DECEMBER");
        return months;
    }

    public static String get(String key) {
        if (data.containsKey(key))
            return data.get(key);
        System.out.println("Такого ключа не существует!");
        return "";
    }
}
