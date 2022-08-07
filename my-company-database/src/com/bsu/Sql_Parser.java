package com.bsu;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Sql_Parser {
    public void ParseSqlCommand(String cmd) {

    }

    static void printInfo(FileWriter fw, List<Company> c, List<String> fields) throws IOException, ParseException {
        for (Company company : c) {
            String ans = "";
            if (fields.contains(company.getNameKey())) {
                ans += company.getName() + ";";
            }
            if (fields.contains(company.getShortNameKey())) {
                ans += company.getShortName() + ";";
            }
            if (fields.contains(company.getActualizationDateKey())) {
                ans += company.getActualizationDate() + ";";
            }
            if (fields.contains(company.getAddressKey())) {
                ans += company.getAddress() + ";";
            }
            if (fields.contains(company.getFoundationDateKey())) {
                ans += company.getFoundationDate() + ";";
            }
            if (fields.contains(company.getEmployeeNumberKey())) {
                ans += company.getEmployeeNumber() + ";";
            }
            if (fields.contains(company.getAuditorKey())) {
                ans += company.getAuditor() + ";";
            }
            if (fields.contains(company.getPhoneNumberKey())) {
                ans += company.getPhoneNumber() + ";";
            }
            if (fields.contains(company.getEMailKey())) {
                ans += company.getEMailKey() + ";";
            }
            if (fields.contains(company.getBranchKey())) {
                ans += company.getBranch() + ";";
            }
            if (fields.contains(company.getActivityTypeKey())) {
                ans += company.getActivityType() + ";";
            }
            if (fields.contains(company.getWebPageKey())) {
                ans += company.getWebPage() + ";";
            }
            ans = ans.substring(0, ans.length() - 1);
            ans += System.lineSeparator();
            fw.write(ans);
        }
    }

    public static void processQuery(String cmd, List<Company> records, FileWriter fw) throws IOException, ParseException {
        String[] words = cmd.split(" ");
        Vector<String> vec = new Vector<String>(Arrays.asList(words));
        ArrayList<Integer> indexToRemove = new ArrayList<>();
        for (int i = 0; i < vec.size(); i++) {
            if (vec.get(i).equals("") || vec.get(i).equals(" ")) {
                indexToRemove.add(i);
            }
        }
        for (int i = indexToRemove.size() - 1; i >= 0; i--) {
            vec.remove(indexToRemove.get(i).intValue());
        }

        if (!words[0].equals("SELECT")) {
            throw new IOException("Error: not a SELECT query");
        }

        int columnRange = 1;
        while (!words[columnRange].equals("FROM")) {
            columnRange++;
        }
        if (columnRange == 1) {
            throw new IOException("Error: no columns in a select query");
        }

        List<String> fields = new ArrayList<>();

        if (columnRange == 2 && words[1].equals("*")) {
            fields = Arrays.asList(Company.FIELD_LIST);
        } else {
            for (int i = 1; i < columnRange; i++) {
                for (String it : Company.FIELD_LIST) {
                    if (words[i].equalsIgnoreCase(it)) {
                        fields.add(it);
                    }
                }
            }
        }

        columnRange++;
        if (!words[columnRange].equals("company_table")) {
            throw new InputMismatchException("Error: wrong table name.");
        }

        columnRange++;

        if (!words[columnRange].equalsIgnoreCase("WHERE")) {
            throw new InputMismatchException("Error: where doesn't exist");
        }

        columnRange++;
        List<String> logicalExpression = new ArrayList<>(Arrays.asList(words).subList(columnRange, words.length));
        for (String str : logicalExpression) {
            str = str.replaceAll("[()]", "");
        }

        int requestType = 0;
        for (String word : logicalExpression) {
            if (word.equalsIgnoreCase("shortName")) {
                requestType = 1;
            } else if (word.equalsIgnoreCase("employeeNumber")) {
                requestType = 2;
            } else if (word.equalsIgnoreCase("activityType")) {
                requestType = 3;
            }
        }
        String[] logicalExpArray = new String[logicalExpression.size()];
        logicalExpArray = logicalExpression.toArray(logicalExpArray);
        List<Company> result = new ArrayList<>();
        for (Company comp : records) {
            if (requestType == 1) {
                if (Logic_Parser.parseStringExpression(logicalExpArray, comp.shortName, "shortName")) {
                    result.add(comp);
                }
            }
            if (requestType == 2) {
                if (Logic_Parser.parseNumberExpression(logicalExpArray, comp.employeeNumber, "employeeNumber")) {
                    result.add(comp);
                }
            }
            if (requestType == 3) {
                if (Logic_Parser.parseStringExpression(logicalExpArray, comp.activityType, "activityType")) {
                    result.add(comp);
                }
            }
        }
        printInfo(fw, result, fields);
    }
}
