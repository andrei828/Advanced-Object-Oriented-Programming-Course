package com.bankservice;

public class Mortgage extends Account {

    private long value;

    private long totalMortgageValue;

    Mortgage() {
        super();
        this.value = 0;
        super.name += " (mortgage)";
    }

    Mortgage(long value) {
        super();
        super.name += " (mortgage)";
        this.value = value;
    }

    Mortgage(String name) {
        super(name);
        this.value = 0;
    }

    Mortgage(String name, long value) {
        super(name);
        this.value = value;
    }

    // TODO: implement for mortgage

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
        return "Interest rate is " +
                this.interestRate * 10 +
                "% and the client has a recurring payment of " +
                this.totalMortgageValue * this.interestRate +
                "$";
    }
}
