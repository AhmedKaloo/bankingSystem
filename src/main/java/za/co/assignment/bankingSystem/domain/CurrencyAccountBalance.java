package za.co.assignment.bankingSystem.domain;

public class CurrencyAccountBalance {
    private String clientAccountNumber;
    private String currencyCode;
    private String displayBalance;
    private String rate;
    private String randBalance;

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(String displayBalance) {
        this.displayBalance = displayBalance;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRandBalance() {
        return randBalance;
    }

    public void setRandBalance(String randBalance) {
        this.randBalance = randBalance;
    }
}
