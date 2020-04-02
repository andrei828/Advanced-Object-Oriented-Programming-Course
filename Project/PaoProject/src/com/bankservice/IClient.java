package com.bankservice;

import java.util.List;

public interface IClient {

    Account createAccount(Bank bank, String type, String name, long value);

    Account createAccount(Bank bank, String type, long value);

    Account createAccount(Bank bank, String type);

    String getAccountInfo(String type);

    Account selectAccount(String name);

    Account selectAccount(long id);

    String getAccountInfo(long id);

    List<Account> getAllAccounts();

    void printAllAccounts();
}
