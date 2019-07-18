package za.co.assignment.bankingSystem.domain;

public class AggregateFinancialPositionReport {
    private String client;
    private String loanBalance;
    private String transactionalBalance;
    private String netPosition;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(String loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getTransactionalBalance() {
        return transactionalBalance;
    }

    public void setTransactionalBalance(String transactionalBalance) {
        this.transactionalBalance = transactionalBalance;
    }

    public String getNetPosition() {
        return netPosition;
    }

    public void setNetPosition(String netPosition) {
        this.netPosition = netPosition;
    }
}
