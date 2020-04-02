package com.bankservice;

/*
This class return the specified type of account
*/

public class BankServicesFactory {

    Account getService(String type) {
        // no break necessary because of return keyword
        switch(type) {
            case "deposit":
            case "Deposit":
                return new Deposit();

            case "mortgage":
            case "Mortgage":
                return new Mortgage();

            case "credit":
            case "Credit":
            case "creditcard":
            case "Creditcard":
            case "CreditCard":
            case "credit card":
            case "credit Card":
            case "Credit card":
            case "Credit Card":
                return new CreditCard();

            case "debit":
            case "Debit":
            case "debitcard":
            case "Debitcard":
            case "DebitCard":
            case "debit card":
            case "debit Card":
            case "Debit card":
            case "Debit Card":
                return new DebitCard();

            default:
                System.out.print("TESTING");
                return null;
        }
    }

    Account getService(String type,long value) {
        System.out.println(value);
        // no break necessary because of return keyword
        switch(type) {
            case "deposit":
            case "Deposit":
                return new Deposit(value);

            case "mortgage":
            case "Mortgage":
                return new Mortgage(value);

            case "credit":
            case "Credit":
            case "creditcard":
            case "Creditcard":
            case "CreditCard":
            case "credit card":
            case "Credit card":
            case "Credit Card":
                return new CreditCard(value);

            case "debit":
            case "Debit":
            case "debitcard":
            case "Debitcard":
            case "DebitCard":
            case "debit card":
            case "Debit card":
            case "Debit Card":
                return new DebitCard(value);

            default:
                return null;
        }
    }

    Account getService(String type, String name) {
    System.out.println(name);
        // no break necessary because of return keyword
        switch(type) {
            case "deposit":
            case "Deposit":
                return new Deposit(name);

            case "mortgage":
            case "Mortgage":
                return new Mortgage(name);

            case "credit":
            case "Credit":
            case "creditcard":
            case "Creditcard":
            case "CreditCard":
            case "credit card":
            case "Credit card":
            case "Credit Card":
                return new CreditCard(name);

            case "debit":
            case "Debit":
            case "debitcard":
            case "Debitcard":
            case "DebitCard":
            case "debit card":
            case "Debit card":
            case "Debit Card":
                return new DebitCard(name);

            default:
                return null;
        }
    }

    Account getService(String type, String name, long value) {

        // no break necessary because of return keyword
        switch(type) {
            case "deposit":
            case "Deposit":
                return new Deposit(name, value);

            case "mortgage":
            case "Mortgage":
                return new Mortgage(name, value);

            case "credit":
            case "Credit":
            case "creditcard":
            case "Creditcard":
            case "CreditCard":
            case "credit card":
            case "Credit card":
            case "Credit Card":
                return new CreditCard(name, value);

            case "debit":
            case "Debit":
            case "debitcard":
            case "Debitcard":
            case "DebitCard":
            case "debit card":
            case "Debit card":
            case "Debit Card":
                return new DebitCard(name, value);

            default:
                return null;
        }
    }
}
