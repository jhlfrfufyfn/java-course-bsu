package com.bsu;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {
            StringBuilder textBuilder = new StringBuilder();

            System.out.println("Enter the text: ");
            String line;
            while (!(line = cin.nextLine()).isEmpty()) {
                textBuilder.append(line).append('\n');
            }

            String text = StringProcessor.deleteNumbers(textBuilder.toString());

            System.out.println('\'' + text + '\'');
        } catch (Exception ex) {
            System.out.println("Error while reading: " + ex);
        }
    }
}