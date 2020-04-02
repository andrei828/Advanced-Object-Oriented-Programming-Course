package com.bankservice;


import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*
account default structure: id (type) | sold: value
 */
public abstract class Account implements IAccount {

    private static long staticId = 0;

    protected long value;

    protected String name;

    protected long id;

    protected LinkedList<Invoice> invoiceHistory;

    protected double interestRate;


    Account() {
        this.id = Account.staticId++;
        this.name = Long.toString(id);
        this.invoiceHistory = new LinkedList<Invoice>();
    }

    Account(String name) {
        this.invoiceHistory = new LinkedList<Invoice>();
        this.id = Account.staticId++;
        this.name = name;
    }

    @Override
    public Invoice sendInvoice() {
        for (Invoice invoice: invoiceHistory) {
            if (!invoice.didClientReceiveInvoice() || !invoice.didClientPayInvoice()) {
                invoice.setClientReceivedInvoice(true);
                return invoice;
            }
        }

        System.out.println("No invoice available");
        return new Invoice();
    }

    @Override
    public Invoice createInvoice() {
        return null;
    }

    @Override
    public long getSold() {
        return value;
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
    public Invoice getLatestUnpaidInvoice() {
        return sendInvoice();
    }

    public void generateInvoices() {
        int numOfInvoices = Account.generateRandomInt(10);
        for (int i = 0; i < numOfInvoices; i++) {
            addInvoice(generateRandomInvoice());
        }
    }

    private static int generateRandomInt(int upperRange){
        Random random = new Random();
        return random.nextInt(upperRange);
    }


    private static int generateRandomIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static Date dateBetween(Date startInclusive, Date endExclusive) {
        return new Date(ThreadLocalRandom.current()
                .nextLong(startInclusive.getTime(), endExclusive.getTime()));
    }

    private static Invoice generateRandomInvoice() {
        return new Invoice(
                Account.generateRandomIntRange(100, 1000),
                dateBetween(
                        new Date(Long.parseLong("1515535200000")),   // 2018 somewhere
                        new Date(Long.parseLong("1610229600000"))),  // 2021 somewhere
                "mail");
    }

    public String getAccountInfo() {
        return name + " | Sold: " + value;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " | Sold: " + value;
    }

    public void addInvoice(Invoice invoice) {
        invoiceHistory.add(invoice);
        Collections.sort(invoiceHistory, Comparator.comparing(Invoice::getDueDate));
    }

    @Override
    public String getInstallmentsInfo() {
        return "Interest rate is " +
                this.interestRate * 10 +
                "% and the client has a recurring payment of " +
                this.value * this.interestRate;
    }
}

