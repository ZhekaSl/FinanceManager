package financeManager.exception;

import financeManager.settings.Text;

public class ModelException extends Exception{
    private final int code;

    public static final int TITLE_EMPTY = 1;
    public static final int IS_EXISTS = 2;
    public static final int DATE_FORMAT = 3;
    public static final int CODE_EMPTY = 4;
    public static final int CURRENCY_EMPTY = 5;
    public static final int ARTICLE_EMPTY = 6;
    public static final int ACCOUNT_EMPTY = 7;
    public static final int RATE_INCORRECT = 8;
    public static final int AMOUNT_FORMAT = 9;
    public static final int NO_BASE_CURRENCY = 10;
    public ModelException(int code) {
        this.code = code;
    }

    public String getMessage() {
        return switch (code) {
            case TITLE_EMPTY -> Text.get("ERROR_TITLE_EMPTY");
            case IS_EXISTS -> Text.get("ERROR_IS_EXISTS");
            case DATE_FORMAT -> Text.get("ERROR_DATE_FORMAT");
            case CODE_EMPTY -> Text.get("ERROR_CODE_EMPTY");
            case CURRENCY_EMPTY -> Text.get("ERROR_CURRENCY_EMPTY");
            case ARTICLE_EMPTY -> Text.get("ERROR_ARTICLE_EMPTY");
            case ACCOUNT_EMPTY -> Text.get("ERROR_ACCOUNT_EMPTY");
            case RATE_INCORRECT -> Text.get("ERROR_RATE_INCORRECT");
            case AMOUNT_FORMAT -> Text.get("ERROR_AMOUNT_FORMAT");
            case NO_BASE_CURRENCY -> Text.get("ERROR_NO_BASE_CURRENCY");
            default -> "";
        };
    }
}
