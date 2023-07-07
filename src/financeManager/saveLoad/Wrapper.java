package financeManager.saveLoad;

import financeManager.model.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "data")
public class Wrapper {
    private List<Article> articles;
    private List<Account> accounts;
    private List<Transaction> transactions;
    private List<Transfer> transfers;
    private List<Currency> currencies;

    @XmlElement(name = "articles")
    public List<Article> getArticles() {
        return articles;
    }

    @XmlElement(name = "accounts")
    public List<Account> getAccounts() {
        return accounts;
    }

    @XmlElement(name = "transactions")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @XmlElement(name = "transfers")
    public List<Transfer> getTransfers() {
        return transfers;
    }

    @XmlElement(name = "currencies")
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
