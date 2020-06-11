package com.bankservice;

import java.io.*;
import java.sql.*;
import java.util.Date;

/*
this is a Singleton class
that manages the telemetry for the API
*/

class Telemetry {

    private static File csvOutputFile;

    private static Telemetry instance;
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    private final static String csvFileTelemetryService = "./src/com/bankservice/telemetry.csv";

    static Telemetry getInstance() {
        if (instance == null) {
            instance = new Telemetry();
        }
        return instance;
    }

    private Telemetry() {
        csvOutputFile = new File(csvFileTelemetryService);
        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "...");
            stmt = conn.createStatement();
            stmt.executeQuery("USE mysql");

        } catch(ClassNotFoundException classNotFound) {
            System.err.println("Couldn't find the JDBC driver");
        } catch (SQLException sqlException) {
            System.err.println("Couldn't connect to the database. Falling to local data from CSV for telemetry ");
        }
    }

    void handler(String usedQuery) {

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

        try {

            String query = String
                    .format("INSERT INTO telemetry (actions, t_stamp) VALUES ('%s', '%s')",
                            usedQuery, new Timestamp((new Date()).getTime()).toString());

            stmt.executeUpdate(query);

        } catch (SQLException | NullPointerException e) {
            // falling to CSV because MySQL database is down
            // System.out.println("Couldn't upload telemetry to MySQL");
        }
    }

}
