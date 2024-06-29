package com.techelevator.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    public static PrintWriter output;
    private static LocalDate date = LocalDate.now();
    private static LocalDateTime now = LocalDateTime.now();
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    private static File logFolder = new File("logs");

    public static void log(String message) {
        if(!logFolder.exists()) {
            logFolder.mkdir();
        }

        String mmDDyyyyHHmmSSa = now.format(format);
        try {
            if(output == null) {
                output = new PrintWriter(new FileOutputStream("logs/" + date + "_Log.txt"));
                output.println(mmDDyyyyHHmmSSa + " " + message);
                output.flush();
            } else {
                output.println(mmDDyyyyHHmmSSa + " " + message);
                output.flush();
            }
        } catch (IOException io) {
            throw new LogException(io.getMessage());
        }
    }

    public static void salesReportLog(String salesReport) {
        if(!logFolder.exists()) {
            logFolder.mkdir();
        }
        String mmDDyyyyHHmmSSa = now.format(format);
        try {
            output = new PrintWriter(new FileOutputStream("logs/" + date + "-SalesReport.txt"));
            output.println(mmDDyyyyHHmmSSa + "\n" + salesReport);
            output.flush();
        } catch (IOException io) {
            throw new LogException(io.getMessage());
        }
    }
}
