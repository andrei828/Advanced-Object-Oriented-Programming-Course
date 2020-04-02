package com.bankservice;

import java.util.*;

public class PackageServices {

    private static PackageServices singleton = null;

    public static PackageServices getInstance() {
        if (singleton == null)
            singleton = new PackageServices();
        return singleton;
    }

    private List<Bank> bankList;
    private List<Client> clientList;
    private List<Employee> employeeList;
    private PriorityQueue<Bank> bestDeposit;
    private PriorityQueue<Bank> bestMortgage;
    private PriorityQueue<Bank> bestDebitCard;
    private PriorityQueue<Bank> bestCreditCard;

    private PackageServices() {

        bankList = new ArrayList<>();
        clientList = new ArrayList<>();
        employeeList = new ArrayList<>();

        bestDeposit = new PriorityQueue<>(
            Comparator.comparingDouble(bank -> bank.getOffers("deposit"))
        );

        bestMortgage = new PriorityQueue<>(
           Comparator.comparingDouble(bank -> bank.getOffers("mortgage"))
        );

        bestDebitCard = new PriorityQueue<>(
            Comparator.comparingDouble(bank -> bank.getOffers("debit card"))
        );

        bestCreditCard = new PriorityQueue<>(
            Comparator.comparingDouble(bank -> bank.getOffers("credit card"))
        );


    }

    public PackageServices createClients() {

        addClient(new Person("Andrei"));
        addClient(new Person("Liviul"));
        addClient(new Person("Ionelu"));
        addClient(new Person("George"));
        addClient(new Business("SPAW"));
        addClient(new Business("GOOG"));
        addClient(new Business("AAPL"));

        return this;
    }

    public PackageServices createBanks() {
        addBank(new Bank("ING"));
        addBank(new Bank("BRD"));
        addBank(new Bank("CEC"));
        addBank(new Bank("Idea"));
        addBank(new Bank("Libra"));
        addBank(new Bank("Deutsche"));
        addBank(new Bank("JP Morgan"));
        addBank(new Bank("ProCredit"));
        addBank(new Bank("Raiffeisen"));
        addBank(new Bank("Transilvania"));
        addBank(new Bank("Goldman Sachs"));

        return this;
    }

    public PackageServices createEmployees() {
        addEmployee(new Employee("Liam",   bankList.get(0)));
        addEmployee(new Employee("Noah",   bankList.get(1)));
        addEmployee(new Employee("James",  bankList.get(2)));
        addEmployee(new Employee("Lucas",  bankList.get(3)));
        addEmployee(new Employee("Elijah", bankList.get(4)));
        addEmployee(new Employee("Daniel", bankList.get(5)));
        addEmployee(new Employee("Matthew",bankList.get(6)));
        addEmployee(new Employee("Mason",  bankList.get(7)));
        addEmployee(new Employee("Michael",bankList.get(8)));
        addEmployee(new Employee("Jackson",bankList.get(9)));
        addEmployee(new Employee("Logan",  bankList.get(0)));
        addEmployee(new Employee("Henry",  bankList.get(1)));
        addEmployee(new Employee("Ethan",  bankList.get(2)));
        addEmployee(new Employee("William",bankList.get(3)));
        addEmployee(new Employee("Jacob",  bankList.get(4)));
        addEmployee(new Employee("Aiden",  bankList.get(5)));
        addEmployee(new Employee("Benjamin",   bankList.get(6)));
        addEmployee(new Employee("Sebastian",  bankList.get(7)));
        addEmployee(new Employee("Oliver",     bankList.get(8)));
        addEmployee(new Employee("Alexander",  bankList.get(9)));

        return this;
    }

    public void addBank(Bank bank) {
        bankList.add(bank);
        bestDeposit.add(bank);
        bestMortgage.add(bank);
        bestDebitCard.add(bank);
        bestCreditCard.add(bank);
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void init() {
        System.out.println(clientList.get(0).getName());
    }

    public PackageServices printAllUsers() {
        System.out.println(clientList);
        return this;
    }

    public PackageServices printAllBanks() {
        System.out.println(bankList);
        return this;
    }

    public PackageServices printAllEmployees() {
        System.out.println(employeeList);
        return this;
    }

    public Client selectClient(long id) {
        for (Client client: clientList)
            if (client.getId() == id)
                return client;

        System.err.print("No Client found");
        return null;
    }

    public Client selectClient(String name) {
        for (Client client: clientList)
            if (name.equals(client.getName()))
                return client;

        System.err.print("No Client found");
        return null;
    }

    public Bank selectBank(long id) {
        for (Bank bank: bankList)
            if (bank.getId() == id)
                return bank;

        System.err.print("No Client found");
        return null;
    }

    public Bank selectBank(String name) {
        for (Bank bank: bankList)
            if (name.equals(bank.getName()))
                return bank;

        System.err.print("No Client found");
        return null;
    }

    public Bank getBestBankForDeposit() {
        return bestDeposit.peek();
    }

    public Bank getBestBankForMortgage() {
        return bestMortgage.peek();
    }

    public Bank getBestBankForDebitCard() {
        return bestDebitCard.peek();
    }

    public Bank getBestBankForCreditCard() {
        return bestCreditCard.peek();
    }

    public Client getClientWithMostAccounts() {
        return Collections.max(
                clientList,
                Comparator.comparing(Client::getNumberOfAccounts)
        );
    }
}
