package com.bankservice;

public class DebitCard extends Account {

    private long value;

    private long fees;

    DebitCard() {
        super();
        this.value = 0;
        super.name += " (debit card)";
    }

    DebitCard(long value) {
        super();
        super.name += " (debit card)";
        this.value = value;
    }

    DebitCard(String name) {
        super(name);
        this.value = 0;
    }

    DebitCard(String name, long value) {
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
