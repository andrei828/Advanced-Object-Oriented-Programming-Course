package com.bankservice;

public class CreditCard extends Account {

    private long value;

    private long totalCreditValue;

    CreditCard() {
        super();
        this.value = 0;
        this.name += " (credit card)";
    }

    CreditCard(long value) {
        super();
        this.name += " (credit card)";
        this.value = value;
    }

    CreditCard(String name) {
        super(name);
        this.value = 0;
    }

    CreditCard(String name, long value) {
        super(name);
        this.value = value;
    }

    // TODO: implement for credit card

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
                this.totalCreditValue * this.interestRate +
                "$";
    }
}
