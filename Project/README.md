## First checkpoint for PAO project

> **Note:**  The project is an API for a banking system. The UML diagram is displayed below.

### Modeling the system
1) There are multiple actions/querys possible. For example, selecting a user, creating an account, receiving an invoice, paying an invoice, inspecting multiple accounts, receive the bank best suited for a deposit / mortgage / debit card / credit card, inspect the account of a selected user etc.

2) The are multiple classes present in the project: IClient, IAccount, Client, Account, Bank, BankServicesFactory, Business, CreditCard, DebitCard, Employee, Invoice, Mortgage, PackageServices, Person

### Implementation
All requirements are present in the application above.

## UML Design
![alt text](https://github.com/andrei828/PAO/blob/master/Project/UML_PAO.png)

## Query code samples for API

Create instance
```
PackageServices bankService = new PackageServices();
```

Query for initialisation
```
bankService
        .createBanks()
        .createClients()
        .createEmployees()
        .init();
```
Queries for showing all clients, banks and employees
```
bankService
        .printAllUsers()
        .printAllBanks()
        .printAllEmployees();
```
Query for creating a new account for client
```
print(
    bankService
        .selectClient("Andrei")
        .createAccount(
                bankService.getBestBankForDeposit(),
                "credit card")
        .addToSold(1500)
        .getAccountInfo()
);
```
Query for information about an account
```
print(
    bankService
        .selectClient("Andrei")
        .getAccountInfo(0)
);
```

Query to show all accounts for a client
```
print(
    bankService
        .selectClient("Andrei")
        .getAllAccounts()
        .toString()
);
```
Query to pay a client's invoice
```
print(
    bankService
        .selectClient("Andrei")
        .selectAccount(0)
        .getLatestUnpaidInvoice()
        .payInvoice()
);
```
Query for information about installments
```
print(
    bankService
        .selectClient("Andrei")
        .selectAccount(0)
        .getInstallmentsInfo()
);
```
Query for best bank for a credit card account
```
print(
    bankService
        .getBestBankForCreditCard()
        .getDepositOffer()
);
```
Query for best bank for a mortgage account
```
print(
    bankService
        .getBestBankForMortgage()
        .getMortgageOffer()
);
```
Query for best bank for a debit card account
```
print(
    bankService
        .getBestBankForDebitCard()
        .getDebitCardOffer()
);
```
Query to get all accounts of user with most accounts
```
print(
    bankService
        .getClientWithMostAccounts()
        .getAllAccounts()
        .toString()
);
```