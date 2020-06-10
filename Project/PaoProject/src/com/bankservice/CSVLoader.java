package com.bankservice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

class CSVLoader {

    private static CSVLoader instance;

    private static String line;
    private final static String cvsSplitBy = ",";
    private final static String csvFileBanks = "./src/com/bankservice/banks.csv";
    private final static String csvFileClients = "./src/com/bankservice/clients.csv";
    private final static String csvFileEmployees = "./src/com/bankservice/employees.csv";
    private final static String csvFileBusinesses = "./src/com/bankservice/businesses.csv";

    private CSVLoader() {}

    static CSVLoader getInstance() {

        if (instance == null) {
            instance = new CSVLoader();
        }

        return instance;
    }

    void getBanksFromCSV(PackageServices packageServices) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileBanks))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] bank = line.split(cvsSplitBy);
                packageServices.addBank(new Bank(bank[1], bank[2], bank[3]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void getClientsFromCSV(PackageServices packageServices) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileClients))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] client = line.split(cvsSplitBy);
                packageServices.addClient(new Person(client[1], client[2]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void getBusinessesFromCSV(PackageServices packageServices) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFileBusinesses))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] client = line.split(cvsSplitBy);
                packageServices.addClient(new Business(client[1], client[2]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void getEmployeesFromCSV(PackageServices packageServices) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileEmployees))) {

            Random rand = new Random();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] employee = line.split(cvsSplitBy);

                packageServices.addEmployee(new Employee(
                        employee[1], employee[2], employee[3],
                        packageServices.getBankList().get(
                            rand.nextInt(packageServices.getBankList().size())
                        )
                ));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
