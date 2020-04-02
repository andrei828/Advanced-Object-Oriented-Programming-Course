package com.bankservice;

import java.text.DecimalFormat;
import java.util.*;

public class Bank {

    private static long staticId = 0;

    private long id;
    private String name;

    private List<Account> accountList;
    private Map<String, Double> offers;
    private List<Employee> employeeList;
    private Map<Client, Employee> clientEmployeeMap;
    static BankServicesFactory servicesFactory = new BankServicesFactory();
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public Bank(String name) {
        this.id = Bank.staticId++;
        this.name = name;

        offers = new HashMap<>();
        accountList = new ArrayList<>();
        employeeList = new ArrayList<>();
        clientEmployeeMap = new HashMap<>();

        offers.put("deposit", Bank.generateRandomDoubleRange(0.1, 0.2));
        offers.put("mortgage", Bank.generateRandomDoubleRange(0.1, 0.2));
        offers.put("debit card", Bank.generateRandomDoubleRange(0.01, 0.1));
        offers.put("credit card", Bank.generateRandomDoubleRange(0.01, 0.2));
    }

    public double getOffers(String type) {
        return offers.get(type);
    }

    public static double generateRandomDoubleRange(double min, double max){
        return (Math.random() * ((max - min) + 1)) + min;
    }

    public void addClientEmployeeMap(Client client, Employee employee) {
        clientEmployeeMap.put(client, employee);
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public Account getAccount(int id) {
        for (Account account: accountList)
            if (account.getId() == id)
                return account;
        return null;
    }

    public Account getAccount(String name) {
        for (Account account: accountList)
            if (name.equals(account.getName()))
                return account;
        return null;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Employee getAnUnoccupiedEmployee() {
        for (Employee employee: employeeList) {
            if (!employee.isOccupied()) {
                employee.setOccupied(true);
                return employee;
            }
        }
        System.out.println(employeeList);
        for (Employee employee: employeeList) {
            employee.setOccupied(false);
        }

        if (employeeList.size() > 0) {
            employeeList.get(0).setOccupied(true);
            return employeeList.get(0);
        }

        System.err.print("There are no employees at the bank");
        return null;
    }


    // returns the employee with most
    // clients with accounts to the bank
    public Employee getBestEmployee() {

        int maxFreq = -1;
        Employee result = null;
        HashMap<Employee, Integer> freq = new HashMap<Employee, Integer>();

        for (Employee employee: clientEmployeeMap.values()) {
            if (freq.containsKey(employee)) {
                freq.replace(employee, freq.get(employee) + 1);
            } else {
                freq.put(employee, 1);
            }

            if (freq.get(employee) > maxFreq) {
                maxFreq = freq.get(employee);
                result = employee;
            }
        }
        return result;
    }

    public String getDepositOffer() {
        return this.name + " offers a deposit with recurring fees of " +
                decimalFormat.format(offers.get("deposit")) + "%";
    }

    public String getMortgageOffer() {
        return this.name + " offers a mortgage with interest rate of " +
                decimalFormat.format(offers.get("mortgage")) + "%";
    }

    public String getDebitCardOffer() {
        return this.name + " offers a debit card with recurring fees of " +
                decimalFormat.format(offers.get("debit card")) + "%";
    }

    public String getCreditCardOffer() {
        return this.name + " offers a credit card with interest rate of " +
                decimalFormat.format(offers.get("credit card")) + "%";
    }

    public String toString() {
        return "(" + Long.toString(id) + ")" + name;
    }
}
