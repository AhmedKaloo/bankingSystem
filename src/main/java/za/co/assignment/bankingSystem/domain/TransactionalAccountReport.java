package za.co.assignment.bankingSystem.domain;

public class TransactionalAccountReport {
    private int clientId;
    private String surname;
    private String clientAccountNumber;
    private String description;
    private String displayBalance;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayBalance() {
        return displayBalance;
    }

    public void setDisplayBalance(String displayBalance) {
        this.displayBalance = displayBalance;
    }
}
