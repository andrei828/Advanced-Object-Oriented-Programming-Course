package com.bankservice;

public class Deposit extends Account {

    private long value;

    protected double fees;

    Deposit() {
        super();
        this.value = 0;
        this.name += " (deposit)";
    }

    Deposit(long value) {
        super();
        this.name += " (deposit)";
        this.value = value;
    }

    Deposit(String name) {
        super(name);
        this.value = 0;
    }

    Deposit(String name, long value) {
        super(name);
        this.value = value;
    }

    @Override
    public void updateSold(long value) {
        this.value = value;
    }

    @Override
    public Account addToSold(long value) {
        this.value += value;

        return this;
    }

    @Override
    public String getAccountInfo() {
        return name + " | Sold: " + value;
    }

    @Override
    public String toString() {
        return name + " | Sold: " + value;
    }

    @Override
    public String getInstallmentsInfo() {
        return "The current sold is " +
                this.value * 10 +
                "% and the recurring fees are " +
                this.fees +
                "$";
    }
}
