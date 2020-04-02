package com.bankservice;

import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class Client implements IClient {

    private static long staticId = 0;

    private final long id;

    private final String name;

    private List<Account> accounts;

    private String address;


    public Client(String name, String ...address) {
        this.accounts = new ArrayList<>();
        this.id = Client.staticId++;
        this.name = name;

        if (address.length == 1)
            this.address = address[0];
    }

    @Override
    public Account createAccount(Bank bank, String type, String name, long value) {
        Account account = Bank.servicesFactory.getService(type, name, value);
        account.generateInvoices();
        bank.addClientEmployeeMap(this, bank.getAnUnoccupiedEmployee());
        bank.addAccount(account);
        this.accounts.add(account);
        return account;
    }

    @Override
    public Account createAccount(Bank bank, String type, long value) {
        Account account = Bank.servicesFactory.getService(type, value);
        account.generateInvoices();
        bank.addClientEmployeeMap(this, bank.getAnUnoccupiedEmployee());
        bank.addAccount(account);
        this.accounts.add(account);
        return account;
    }

    @Override
    public Account createAccount(Bank bank, String type) {
        Account account = Bank.servicesFactory.getService(type);
        account.generateInvoices();
        bank.addClientEmployeeMap(this, bank.getAnUnoccupiedEmployee());
        bank.addAccount(account);
        this.accounts.add(account);
        return account;
    }

    @Override
    public String getAccountInfo(long id) {
        for (Account account: accounts)
            if (account.getId() == id)
                return account.toString();

        System.err.println("No Account was found");
        return "No Account Found";
    }

    @Override
    public String getAccountInfo(String name) {
        for (Account account: accounts)
            if (name.equals(account.getName()))
                return account.toString();

        System.err.println("No Account was found");
        return "No Account Found";
    }

    @Override
    public void printAllAccounts() {
        System.out.print(accounts);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @Override
    public Account selectAccount(long id) {
        for (Account account: accounts)
            if (account.getId() == id)
                return account;

        System.err.print("No Account Found");
        return null;
    }

    @Override
    public Account selectAccount(String name) {
        for (Account account: accounts)
            if (name.equals(account.getId()))
                return account;

        System.err.print("No Account Found");
        return null;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        if (address != null)
            return address;
        return "No address";
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return "(" + Long.toString(id) + ")" + name;
    }


}
