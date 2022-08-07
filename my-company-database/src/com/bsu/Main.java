package com.bsu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.exit;

public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String SETTINGS_FILENAME = "data/settings.txt";
    private static final String LOG_FILENAME = "data/logfile.txt";
    private static String CSV_IN_FILENAME;
    private static String CSV_OUT_FILENAME;

    static SimpleDateFormat dateFormat;

    public static void main(String[] args) {
        LOGGER.setLevel(Level.FINE);
        File logFile = new File(LOG_FILENAME);
        FileHandler filehandler;

        if (args.length == 2) {
            CSV_IN_FILENAME = args[0];
            CSV_OUT_FILENAME = args[1];
        } else {
            System.out.println("Default filenames: input.csv, output.txt" + System.lineSeparator());
        }

        try {
            boolean isCreated = logFile.createNewFile();
            filehandler = new FileHandler(LOG_FILENAME, true);
            filehandler.setLevel(Level.FINE);
            SimpleFormatter formatter = new SimpleFormatter();
            filehandler.setFormatter(formatter);
            LOGGER.addHandler(filehandler);
            LOGGER.fine("Program started " + System.lineSeparator());
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }

        try (Scanner fin = new Scanner(new File(SETTINGS_FILENAME))) {
            String sDateFormat = fin.nextLine();
            dateFormat = new SimpleDateFormat(sDateFormat);
        } catch (Exception ex) {
            System.out.println("Error: date format in settings.txt is wrong. Program is terminated.");
            exit(1);
        }

        try (Scanner cin = new Scanner(System.in); FileWriter fout = new FileWriter(CSV_OUT_FILENAME)
             ; Scanner sc = new Scanner(new File("data/requests.txt"))) {
            List<Company> records = new ArrayList<>();
            try (Scanner fin = new Scanner(new File(CSV_IN_FILENAME))) {
                while (fin.hasNext()) {
                    String[] data = fin.nextLine().split(";");
                    records.add(new Company(data));
                }
            } catch (Exception ex) {
                System.err.println("Error: " + ex.toString());
            }

            int fileCounter = 1;
            while (sc.hasNext()) {
                FileWriter fw = new FileWriter(new File("data/request" + Integer.toString(fileCounter++)));
                String line = sc.nextLine();
                Sql_Parser.processQuery(line, records, fw);
                fw.close();
            }
            printMenu(System.out);
            queryCycle(cin, fout, records);
        } catch (Exception ex) {
            System.err.println("Error: " + ex.toString());
        }


    }

    private static void queryCycle(Scanner cin, FileWriter fout, List<Company> records) throws IOException, ParseException {
        while (true) {
            System.out.println("Enter the query number(enter 0 if you want to finish the program): ");
            int q;
            q = Integer.parseInt(cin.nextLine());
            if (q == 0) {
                break;
            }
            String catLine;
            String queryInfo;
            List<Company> list;
            Query.RequestType option = Query.RequestType.fromIntValue(q);
            switch (option) {
                case BY_SHORT_NAME:
                    System.out.println("Enter the short name: ");
                    catLine = cin.nextLine();
                    queryInfo = "Query 1, short name: " + catLine;
                    Company ans = Query.findByShortName(catLine, records);
                    if (ans == Company.VOID_COMPANY) {
                        fout.write("Company not found" + System.lineSeparator());
                    } else {
                        ans.print(fout);
                    }
                    writeQueryResultToLogFile(String.format(
                            ("%s, companies found: %s%s"),
                            queryInfo
                            , ((ans != Company.VOID_COMPANY) ? "1" : "0")
                            , System.lineSeparator()
                    ));
                    break;
                case BY_BRANCH:
                    System.out.println("Enter the branch: ");
                    catLine = cin.nextLine();
                    queryInfo = "Query 2, branch: " + catLine;
                    list = Query.findByBranch(catLine, records);
                    printResultingList(fout, list);
                    writeQueryResultToLogFile(String.format(
                            ("%s, companies found: %s%s"),
                            queryInfo
                            , list.size()
                            , System.lineSeparator()
                    ));
                    break;
                case BY_ACTIVITY_TYPE:
                    System.out.println("Enter the activity type: ");
                    catLine = cin.nextLine();
                    queryInfo = "Query 3, activity type: " + catLine;
                    list = Query.findByActType(catLine, records);
                    printResultingList(fout, list);
                    writeQueryResultToLogFile(String.format(
                            ("%s, companies found: %s%s"),
                            queryInfo
                            , list.size()
                            , System.lineSeparator()
                    ));
                    break;
                case BY_FOUNDATION_DATE:
                    System.out.println("Enter the dates in two different lines: ");
                    catLine = cin.nextLine();
                    queryInfo = "Query 4, dates: " + catLine;
                    Date date1 = Main.dateFormat.parse(catLine);
                    String catLine2 = cin.nextLine();
                    Date date2 = Main.dateFormat.parse(catLine2);
                    list = Query.findByFDate(date1, date2, records);
                    printResultingList(fout, list);
                    writeQueryResultToLogFile(String.format(
                            ("%s, companies found: %s%s"),
                            queryInfo
                            , list.size()
                            , System.lineSeparator()
                    ));
                    break;
                case BY_EMPLOYEE_NUMBER:
                    System.out.println("Enter the two numbers: ");
                    catLine = cin.nextLine();
                    String[] nums = catLine.split(" ");
                    if (nums.length != 2) {
                        throw new IllegalArgumentException("Error: wrong number of numbers entered in query 5");
                    }
                    int n1 = Integer.parseInt(nums[0]);
                    int n2 = Integer.parseInt(nums[1]);
                    queryInfo = "Query 5, employee numbers: " + n1 + " , " + n2;
                    list = Query.findByEmplNumber(n1, n2, records);
                    printResultingList(fout, list);
                    writeQueryResultToLogFile(String.format(
                            ("%s, companies found: %s%s"),
                            queryInfo
                            , list.size()
                            , System.lineSeparator())
                    );
                    break;
                case DEFAULT:
                default:
                    System.out.println("Wrong query number. Try again.");
                    break;
            }
        }
    }

    private static void printMenu(PrintStream stream) {
        stream.println("Query types:");
        stream.println("1: Find a company by its short name(enter the short name).");
        stream.println("2: Find the companies by their branch(enter the branch name).");
        stream.println("3: Find the companies by their activity type.(enter the activity type)");
        stream.println("4: Find the companies by their foundation date range."
                + "(enter two dates in a format given in settings.txt)");
        stream.println("5: Find the companies by their number of employees.(enter the number of employees)");
        stream.println("Important: first enter just the query type without the parameters.");
    }

    private static void writeQueryResultToLogFile(String s) {
        LOGGER.fine(s);
    }

    private static void printResultingList(FileWriter fout, List<Company> list) throws IOException {
        fout.write("Companies found: " + System.lineSeparator());
        if (list.isEmpty()) {
            fout.write("NONE" + System.lineSeparator());
        }
        for (Company it : list) {
            it.print(fout);
        }
    }
}
