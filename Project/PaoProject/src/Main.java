import com.bankservice.*;

public class Main {

    public static void main(String[] args) {

        PackageServices bankService = PackageServices.getInstance();

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
    }

    private static void print(String string){
        System.out.println(string);
    }
}
