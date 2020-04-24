package com.bankservice;

public class Employee {

    private static long staticId = 0;

    private final long id;

    private String name;

    private String address;

    private String position;

    private boolean occupied;

    private Bank workingAtBank;

    public Employee(String name, Bank bank) {
        this.id = Employee.staticId++;
        this.name = name;
        this.occupied = false;
        this.workingAtBank = bank;
        bank.addEmployee(this);
    }

    public Employee(String name, String address, String position, Bank bank) {
        this.id = Employee.staticId++;
        this.name = name;
        this.occupied = false;
        this.address = address;
        this.position = position;
        this.workingAtBank = bank;
        bank.addEmployee(this);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String toString() {
        return "("+ this.id + ") " + this.name;
    }
}
