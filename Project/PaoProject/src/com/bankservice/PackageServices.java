package com.bankservice;

import java.io.*;
import java.net.URISyntaxException;
import java.sql.Timestamp;
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

    private static CSVLoader csvLoader;
    private static Telemetry telemetry;
    private static MySqlLoader sqlLoader;

    private PackageServices() {

        bankList = new ArrayList<>();
        clientList = new ArrayList<>();
        employeeList = new ArrayList<>();
        csvLoader = CSVLoader.getInstance();
        telemetry = Telemetry.getInstance();
        sqlLoader = MySqlLoader.getInstance();

        bestDeposit = new PriorityQueue<>(
            Comparator.comparingDouble(
                bank -> bank.getOffers("deposit")
            )
        );

        bestMortgage = new PriorityQueue<>(
           Comparator.comparingDouble(
               bank -> bank.getOffers("mortgage")
           )
        );

        bestDebitCard = new PriorityQueue<>(
            Comparator.comparingDouble(
                bank -> bank.getOffers("debit card")
            )
        );

        bestCreditCard = new PriorityQueue<>(
            Comparator.comparingDouble(
                bank -> bank.getOffers("credit card")
            )
        );

    }

    // Load data from CSV with these four methods
    public PackageServices loadBanksFromCSV() {

        csvLoader.getBanksFromCSV(this);
        telemetry.handler("Imported Banks from CSV");

        return this;
    }

    public PackageServices loadClientsFromCSV() {

        csvLoader.getClientsFromCSV(this);
        telemetry.handler("Imported Clients from CSV");

        return this;
    }

    public PackageServices loadEmployeesFromCSV() {

        csvLoader.getEmployeesFromCSV(this);
        telemetry.handler("Imported Employees from CSV");

        return this;
    }

    public PackageServices loadBusinessesFromCSV() {

        csvLoader.getBusinessesFromCSV(this);
        telemetry.handler("Imported Businesses from CSV");

        return this;
    }


    // Load data from MySql with these four methods
    public PackageServices loadBanksFromSQL() {

        if (MySqlLoader.connectionSuccessful) {

            sqlLoader.getBanksFromSQL(this);
            telemetry.handler("Imported Banks from MySQL");

        } else {
            return loadBanksFromCSV();
        }
        return this;
    }

    public PackageServices loadClientsFromSQL() {

        if (MySqlLoader.connectionSuccessful) {

            sqlLoader.getClientsFromSQL(this);
            telemetry.handler("Imported Clients from MySQL");

        } else {
            return loadClientsFromCSV();
        }

        return this;
    }

    public PackageServices loadEmployeesFromSQL() {

        if (MySqlLoader.connectionSuccessful) {

            sqlLoader.getEmployeesFromSQL(this);
            telemetry.handler("Imported Employees from MySQL");

        } else {
            return loadEmployeesFromCSV();
        }
        return this;
    }

    public PackageServices loadBusinessesFromSQL() {

        if (MySqlLoader.connectionSuccessful) {

            sqlLoader.getBusinessesFromSQL(this);
            telemetry.handler("Imported Businesses from MySQL");

        }else {
            return loadBusinessesFromCSV();
        }
        return this;
    }
    

    public PackageServices createClients() {

        telemetry.handler("Created clients programmatically");

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

        telemetry.handler("Created banks programmatically");

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

        telemetry.handler("Created employees programmatically");

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

        telemetry.handler("Created a new bank");

        bankList.add(bank);
        bestDeposit.add(bank);
        bestMortgage.add(bank);
        bestDebitCard.add(bank);
        bestCreditCard.add(bank);
    }

    public void addClient(Client client) {

        telemetry.handler("Created a new client");

        clientList.add(client);
    }

    public void addEmployee(Employee employee) {

        telemetry.handler("Created a new employee");

        employeeList.add(employee);
    }

    public void init() {
        // prints the first client in the list
        System.out.println(clientList.get(0).getName());
    }

    public PackageServices printAllUsers() {

        telemetry.handler("Displayed all users");

        System.out.println(clientList);
        return this;
    }

    public PackageServices printAllBanks() {

        telemetry.handler("Displayed all banks");

        System.out.println(bankList);
        return this;
    }

    public PackageServices printAllEmployees() {

        telemetry.handler("Displayed all employees");

        System.out.println(employeeList);
        return this;
    }

    public Client selectClient(long id) {

        for (Client client: clientList) {
            if (client.getId() == id) {
                telemetry.handler(
                "Queried a client with name " + client.getName()
                );
                return client;
            }
        }

        telemetry.handler("Failed to query a client");
        System.err.print("No Client found");
        return null;
    }

    public Client selectClient(String name) {

        for (Client client : clientList) {
            if (name.equals(client.getName())) {
                telemetry.handler(
                "Queried a client with name " + client.getName()
                );
                return client;
            }
        }

        telemetry.handler("Failed to query a client");
        System.err.print("No Client found");
        return null;
    }

    public Bank selectBank(long id) {
        for (Bank bank : bankList) {
            if (bank.getId() == id) {
                telemetry.handler(
                "Queried a bank with name " + bank.getName()
                );
                return bank;
            }
        }

        telemetry.handler("Failed to query a bank");
        System.err.print("No Client found");
        return null;
    }

    public Bank selectBank(String name) {
        for (Bank bank: bankList) {
            if (name.equals(bank.getName())) {
                telemetry.handler(
                "Queried a bank with name " + bank.getName()
                );
                return bank;
            }
        }

        telemetry.handler("Failed to query a bank");
        System.err.print("No Client found");
        return null;
    }

    public Bank getBestBankForDeposit() {
        telemetry.handler(
            "Queried the system for bank with best deposit"
        );
        return bestDeposit.peek();
    }

    public Bank getBestBankForMortgage() {
        telemetry.handler(
            "Queried the system for bank with best mortgage"
        );
        return bestMortgage.peek();
    }

    public Bank getBestBankForDebitCard() {
        telemetry.handler(
            "Queried the system for bank with best debit card"
        );
        return bestDebitCard.peek();
    }

    public Bank getBestBankForCreditCard() {
        telemetry.handler(
            "Queried the system for bank with best credit card"
        );
        return bestCreditCard.peek();
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    class CustomComparator<T extends Comparable<T>> implements Comparator<T> {
        public int compare(T a, T b) {
            return a.compareTo(b);
        }
    }

    public Client getClientWithMostAccounts() {

        telemetry.handler(
            "Queried the system for client with most accounts"
        );

        return Collections.max(
                clientList,
                Comparator.comparing(Client::getNumberOfAccounts)
        );
    }
}
