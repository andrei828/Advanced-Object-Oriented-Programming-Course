package com.bankservice;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;

/*
this is a Singleton class
that manages the telemetry for the API
*/

class Telemetry {

    private static File csvOutputFile;

    private static Telemetry instance;

    private final static String csvFileTelemetryService = "./src/com/bankservice/telemetry.csv";

    static Telemetry getInstance() {
        if (instance == null) {
            instance = new Telemetry();
        }
        return instance;
    }

    private Telemetry() {
        csvOutputFile = new File(csvFileTelemetryService);
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

    }

}
