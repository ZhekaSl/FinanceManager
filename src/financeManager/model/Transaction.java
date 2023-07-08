package financeManager.model;

import financeManager.exception.ModelException;
import financeManager.saveLoad.SaveData;

import java.util.Date;

public class Transaction extends Common {
    private Account account;
    private Article article;
    private double sum;
    private String notice;
    private Date date;

    public Transaction() {

    }

    public Transaction(Account account, Article article, double sum, String notice, Date date) throws ModelException {
        if (account == null) throw new ModelException(ModelException.ACCOUNT_EMPTY);
        if (article == null) throw new ModelException(ModelException.ARTICLE_EMPTY);
        this.account = account;
        this.article = article;
        this.sum = sum;
        this.notice = notice;
        this.date = date;
    }

    public Transaction(Account account, Article article, double sum, String notice) throws ModelException {
        this(account, article, sum, notice, new Date());
    }

    public Transaction(Account account, Article article, double sum, Date date) throws ModelException {
        this(account, article, sum, "", date);
    }

    public Transaction(Account account, Article article, double sum) throws ModelException {
        this(account, article, sum, "", new Date());
    }

    @Override
    public void postAdd(SaveData sd) {
        setAmounts(sd);
    }

    @Override
    public void postEdit(SaveData sd) {
        setAmounts(sd);
    }

    @Override
    public void postRemove(SaveData sd) {
        setAmounts(sd);
    }

    private void setAmounts(SaveData sd) {
        for (Account a : sd.getAccounts()) {
            a.setAmountFromTransactionsAndTransfers(sd.getTransactions(), sd.getTransfers());
        }
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "account=" + account +
                ", article=" + article +
                ", sum=" + sum +
                ", notice='" + notice + '\'' +
                ", date=" + date +
                '}';
    }
}
