package za.co.assignment.bankingSystem.domain;

public class ATMBalance {
    private int atmId;
    private double atmBalance;
    private int count;
    private int value;

    public int getAtmId() {
        return atmId;
    }

    public void setAtmId(int atmId) {
        this.atmId = atmId;
    }

    public double getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(double atmBalance) {
        this.atmBalance = atmBalance;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
