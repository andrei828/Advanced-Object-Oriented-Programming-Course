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
```
PackageServices bankService = new PackageServices();
    
// query for initialisation
bankService
        .createBanks()
        .createClients()
        .createEmployees()
        .init();

// queries for showing all clients, banks and employees
bankService
        .printAllUsers()
        .printAllBanks()
        .printAllEmployees();

// query for creating a new account for client
print(
    bankService
        .selectClient("Andrei")
        .createAccount(
                bankService.getBestBankForDeposit(),
                "credit card")
        .addToSold(1500)
        .getAccountInfo()
);

// query for information about an account
print(
    bankService
        .selectClient("Andrei")
        .getAccountInfo(0)
);

// query to show all accounts for a client
print(
    bankService
        .selectClient("Andrei")
        .getAllAccounts()
        .toString()
);

// query to pay a client's invoice
print(
    bankService
        .selectClient("Andrei")
        .selectAccount(0)
        .getLatestUnpaidInvoice()
        .payInvoice()
);

// query for information about installments
print(
    bankService
        .selectClient("Andrei")
        .selectAccount(0)
        .getInstallmentsInfo()
);

// query for best bank for a credit card account
print(
    bankService
        .getBestBankForCreditCard()
        .getDepositOffer()
);

// query for best bank for a mortgage account
print(
    bankService
        .getBestBankForMortgage()
        .getMortgageOffer()
);

// query for best bank for a debit card account
print(
    bankService
        .getBestBankForDebitCard()
        .getDebitCardOffer()
);

// query to get all accounts of user with most accounts
print(
    bankService
        .getClientWithMostAccounts()
        .getAllAccounts()
        .toString()
);
```