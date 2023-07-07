package financeManager.saveLoad;

import financeManager.exception.ModelException;
import financeManager.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

final public class SaveData {
    private static SaveData instance;
    private List<Article> articles = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private List<Transfer> transfers = new ArrayList<>();

    private final Filter filter;
    private Common oldCommon;
    private boolean saved = true;

    private SaveData() {
        load();
        this.filter = new Filter();
    }

    public void load() {
        SaveLoad.load(this);
        sort();
    }

    private void sort() {
        this.articles.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
        this.accounts.sort((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()));
        this.transactions.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        this.transfers.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        this.currencies.sort(new Comparator<Currency>() {
            @Override
            public int compare(Currency o1, Currency o2) {
                if (o1.isBase()) return -1;
                if (o2.isBase()) return 1;
                if (o1.isOn() ^ o2.isOn()) {
                    if (o1.isOn()) return 1;
                    else return -1;
                }
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
    }

    public void save() {
        SaveLoad.save(this);
        saved = true;
    }

    public boolean isSaved() {
        return saved;
    }

    public static SaveData getInstance() {
        if (instance == null) {
            instance = new SaveData();
        }
        return instance;
    }

    public Filter getFilter() {
        return filter;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public Currency getBaseCurrency() {
        for (Currency c : currencies) {
            if (c.isBase()) return c;
        }
        return new Currency();
    }

    public List<Currency> getEnableCurrencies() {
        List<Currency> enableCurrencies = new ArrayList<>();
        for (Currency currency : currencies) {
            if (currency.isOn()) {
                enableCurrencies.add(currency);
            }
        }
        return enableCurrencies;
    }

    public List<Transaction> getFilterTransactions() {
        List<Transaction> list = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (filter.check(transaction.getDate())) list.add(transaction);
        }
        return list;
    }

    public List<Transfer> getFilterTransfers() {
        List<Transfer> list = new ArrayList<>();
        for (Transfer transfer : transfers) {
            if (filter.check(transfer.getDate())) list.add(transfer);
        }
        return list;
    }

    public List<Transaction> getCountOfTransactions(int count) {
        return new ArrayList<>(transactions.subList(0, Math.min(count, transactions.size())));
    }

    public Common getOldCommon() {
        return oldCommon;
    }

    public void add(Common c) throws ModelException {
        List ref = getRef(c);
        if (ref.contains(c)) throw new ModelException(ModelException.IS_EXISTS);
        ref.add(c);
        c.postAdd();
        sort();
        saved = false;
    }

    public void edit(Common oldC, Common newC) throws ModelException {
        List ref = getRef(oldC);
        if (ref.contains(newC) && oldC != ref.get(ref.indexOf(newC))) {
            throw new ModelException(ModelException.IS_EXISTS);
        }
        ref.set(ref.indexOf(oldC), newC);
        oldCommon = oldC;
        newC.postEdit();
        sort();
        saved = false;
    }

    public void remove(Common c) {
        getRef(c).remove(c);
        c.postRemove();
        saved = false;
    }

    private List getRef(Common c) {
        if (c instanceof Account) return accounts;
        else if (c instanceof Article) return articles;
        else if (c instanceof Transaction) return transactions;
        else if (c instanceof Transfer) return transfers;
        else if (c instanceof Currency) return currencies;
        else return null;


    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    @Override
    public String toString() {
        return "SaveData{" +
                "articles=" + articles +
                ", accounts=" + accounts +
                ", currencies=" + currencies +
                ", transactions=" + transactions +
                ", transfers=" + transfers +
                '}';
    }
}
