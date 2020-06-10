package com.bankservice;

import java.sql.*;
import java.util.Random;

public class MySqlLoader {

    private static MySqlLoader instance;
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    public static boolean connectionSuccessful;

    private MySqlLoader() {
        try {
            connectionSuccessful = true;
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "...");
            stmt = conn.createStatement();
            stmt.executeQuery("USE mysql");

        } catch(ClassNotFoundException classNotFound) {
            connectionSuccessful = false;
            System.err.println("Couldn't find the JDBC driver");
        } catch (SQLException sqlException) {
            connectionSuccessful = false;
            System.err.println("Couldn't connect to the database. Falling to local data from CSV");
        }
    }

    static public MySqlLoader getInstance() {

        if (instance == null) {
            instance = new MySqlLoader();
        }

        return instance;
    }

    void getBanksFromSQL(PackageServices packageServices) {
        try {

            rs = stmt.executeQuery("select * from banks");

            while (rs.next())
                packageServices.addBank(
                    new Bank(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4))
                    );

        } catch (SQLException e) {
            System.out.println("Couldn't load banks from database");
        }
    }

    void getClientsFromSQL(PackageServices packageServices) {
        try {

            rs = stmt.executeQuery("select * from users");

            while (rs.next())
                packageServices.addClient(
                    new Person(
                        rs.getString(2),
                        rs.getString(3))
                );

        } catch (SQLException e) {
            System.out.println("Couldn't load clients from database");
        }
    }

    void getBusinessesFromSQL(PackageServices packageServices) {
        try {

            rs = stmt.executeQuery("select * from businesses");

            while (rs.next())
                packageServices.addClient(
                    new Business(
                        rs.getString(2),
                        rs.getString(3))
                );

        } catch (SQLException e) {
            System.out.println("Couldn't load businesses from database");
        }
    }

    void getEmployeesFromSQL(PackageServices packageServices) {
        try {

            rs = stmt.executeQuery("select * from employees");
            Random rand = new Random();
            while (rs.next())
                packageServices.addEmployee(
                    new Employee(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        packageServices.getBankList().get(
                            rand.nextInt(packageServices.getBankList().size())
                        ))
                );

        } catch (SQLException e) {
            System.out.println("Couldn't load employees from database");
        }
    }
}
