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


    private static String line;
    private static File csvOutputFile;
    private final static String cvsSplitBy = ",";
    private final static String csvFileBanks = "./src/com/bankservice/banks.csv";
    private final static String csvFileClients = "./src/com/bankservice/clients.csv";
    private final static String csvFileEmployees = "./src/com/bankservice/employees.csv";
    private final static String csvFileTelemetryService = "./src/com/bankservice/telemetry.csv";

    private PackageServices() {

        bankList = new ArrayList<>();
        clientList = new ArrayList<>();
        employeeList = new ArrayList<>();
        csvOutputFile = new File(csvFileTelemetryService);

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

    public PackageServices loadBanksFromCSV() {

        telemetryHandler("Imported Banks from CSV");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileBanks))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] bank = line.split(cvsSplitBy);

                addBank(new Bank(bank[1], bank[2], bank[3]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public PackageServices loadClientsFromCSV() {

        telemetryHandler("Imported Clients from CSV");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileClients))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] client = line.split(cvsSplitBy);

                if (client[3].equals("Person")) {
                    addClient(new Person(client[1], client[2]));
                } else if (client[3].equals("Business")) {
                    addClient(new Business(client[1], client[2]));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    public PackageServices loadEmployeesFromCSV() {

        telemetryHandler("Imported Employees from CSV");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileEmployees))) {

            Random rand = new Random();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] employee = line.split(cvsSplitBy);

                addEmployee(new Employee(
                    employee[1], employee[2], employee[3],
                    bankList.get(rand.nextInt(bankList.size()))
                ));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    private void telemetryHandler(String usedQuery) {

        try (FileWriter fileWriter = new FileWriter(csvOutputFile, true)) {

            fileWriter.append(usedQuery);
            fileWriter.append(',');
            fileWriter.append(
                (new Timestamp((new Date()).getTime())).toString()
            );
            fileWriter.append('\n');

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public PackageServices createClients() {

        telemetryHandler("Created clients programmatically");

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

        telemetryHandler("Created banks programmatically");

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

        telemetryHandler("Created employees programmatically");

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

        telemetryHandler("Created a new bank");

        bankList.add(bank);
        bestDeposit.add(bank);
        bestMortgage.add(bank);
        bestDebitCard.add(bank);
        bestCreditCard.add(bank);
    }

    public void addClient(Client client) {

        telemetryHandler("Created a new client");

        clientList.add(client);
    }

    public void addEmployee(Employee employee) {

        telemetryHandler("Created a new employee");

        employeeList.add(employee);
    }

    public void init() {
        System.out.println(clientList.get(0).getName());
    }

    public PackageServices printAllUsers() {

        telemetryHandler("Displayed all users");

        System.out.println(clientList);
        return this;
    }

    public PackageServices printAllBanks() {

        telemetryHandler("Displayed all banks");

        System.out.println(bankList);
        return this;
    }

    public PackageServices printAllEmployees() {

        telemetryHandler("Displayed all employees");

        System.out.println(employeeList);
        return this;
    }

    public Client selectClient(long id) {

        for (Client client: clientList) {
            if (client.getId() == id) {
                telemetryHandler(
                "Queried a client with name " + client.getName()
                );
                return client;
            }
        }

        telemetryHandler("Failed to query a client");
        System.err.print("No Client found");
        return null;
    }

    public Client selectClient(String name) {

        for (Client client : clientList) {
            if (name.equals(client.getName())) {
                telemetryHandler(
                "Queried a client with name " + client.getName()
                );
                return client;
            }
        }

        telemetryHandler("Failed to query a client");
        System.err.print("No Client found");
        return null;
    }

    public Bank selectBank(long id) {
        for (Bank bank : bankList) {
            if (bank.getId() == id) {
                telemetryHandler(
                "Queried a bank with name " + bank.getName()
                );
                return bank;
            }
        }

        telemetryHandler("Failed to query a bank");
        System.err.print("No Client found");
        return null;
    }

    public Bank selectBank(String name) {
        for (Bank bank: bankList) {
            if (name.equals(bank.getName())) {
                telemetryHandler(
                "Queried a bank with name " + bank.getName()
                );
                return bank;
            }
        }

        telemetryHandler("Failed to query a bank");
        System.err.print("No Client found");
        return null;
    }

    public Bank getBestBankForDeposit() {
        telemetryHandler(
            "Queried the system for bank with best deposit"
        );
        return bestDeposit.peek();
    }

    public Bank getBestBankForMortgage() {
        telemetryHandler(
            "Queried the system for bank with best mortgage"
        );
        return bestMortgage.peek();
    }

    public Bank getBestBankForDebitCard() {
        telemetryHandler(
            "Queried the system for bank with best debit card"
        );
        return bestDebitCard.peek();
    }

    public Bank getBestBankForCreditCard() {
        telemetryHandler(
            "Queried the system for bank with best credit card"
        );
        return bestCreditCard.peek();
    }

    public Client getClientWithMostAccounts() {

        telemetryHandler(
            "Queried the system for client with most accounts"
        );

        return Collections.max(
                clientList,
                Comparator.comparing(Client::getNumberOfAccounts)
        );
    }
}
