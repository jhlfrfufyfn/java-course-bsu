package com.bsu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class Logic_Parser {
    static boolean parseStringExpression(String[] words, String correctName, String specificName) {
        List<Integer> linkers = new ArrayList<Integer>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("OR")) {
                linkers.add(i);
            }
        }
        linkers.add(words.length);

        List<Boolean> linkerResults = new ArrayList<Boolean>();
        for (int i = 0; i < linkers.size(); i++) {
            linkerResults.add(false);
        }

        for (int i = 0; i < linkers.size(); i++) {
            Integer index = linkers.get(i); ///index in words array

            String argumentString = "";

            int startIndex = ((i - 1) >= 0) ? (linkers.get(i - 1) + 1) : 0; ///finding the first word before current linker
            boolean correctBlock = false;
            boolean foundEqualsSign = false;
            for (int j = startIndex; j < index; j++) {
                if (!words[j].equalsIgnoreCase(specificName)
                        && Character.isLetterOrDigit(words[j].charAt(0))) {
                    correctBlock = true;
                    argumentString = words[j];
                } else if (words[j].equals("==")) {
                    foundEqualsSign = true;
                }
            }
            if (!correctBlock) {
                throw new InputMismatchException("Error: wrong logical expression in WHERE");
            }
            if (foundEqualsSign) {
                linkerResults.set(i, argumentString.equalsIgnoreCase(correctName));
            } else {
                linkerResults.set(i, !argumentString.equalsIgnoreCase(correctName));
            }
        }
        for (boolean b : linkerResults) {
            if (b)
                return true;
        }
        return false;
    }

    static boolean parseNumberExpression(String[] words, int correctNumber, String specificName) {
        List<Integer> linkers = new ArrayList<Integer>();

        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("AND")) {
                linkers.add(i);
            }
        }


        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("OR")) {
                linkers.add(i);
            }
        }
        linkers.add(words.length);

        List<Boolean> linkerResults = new ArrayList<Boolean>();
        for (int i = 0; i < linkers.size(); i++) {
            linkerResults.add(false);
        }

        for (int i = 0; i < linkers.size(); i++) {
            Integer index = linkers.get(i); ///index in words array

            String argumentString = "";

            int startIndex = ((i - 1) >= 0) ? (linkers.get(i - 1) + 1) : 0; ///finding the first word before current linker
            boolean correctBlock = false;
            boolean foundEqualsSign = false;
            boolean foundLessSign = false;
            boolean foundMoreSign = false;
            boolean foundLessEqualSign = false;
            boolean foundMoreEqualSign = false;
            for (int j = startIndex; j < index; j++) {
                if (!words[j].equalsIgnoreCase(specificName)
                        && Character.isDigit(words[j].charAt(0))) {
                    correctBlock = true;
                    argumentString = words[j];
                } else if (words[j].equals("==")) {
                    foundEqualsSign = true;
                } else if (words[j].equals("<=")) {
                    foundLessEqualSign = true;
                } else if (words[j].equals(">=")) {
                    foundMoreEqualSign = true;
                } else if (words[j].equals(">")) {
                    foundMoreSign = true;
                } else if (words[j].equals("<")) {
                    foundLessSign = true;
                }
            }
            if (!correctBlock) {
                throw new InputMismatchException("Error: wrong logical expression in WHERE");
            }
            if (foundEqualsSign) {
                linkerResults.set(i, correctNumber == Integer.parseInt(argumentString));
            } else if (foundLessEqualSign) {
                linkerResults.set(i, correctNumber <= Integer.parseInt(argumentString));
            } else if (foundMoreEqualSign) {
                linkerResults.set(i, correctNumber >= Integer.parseInt(argumentString));
            } else if (foundMoreSign) {
                linkerResults.set(i, correctNumber > Integer.parseInt(argumentString));
            } else if (foundLessSign) {
                linkerResults.set(i, correctNumber < Integer.parseInt(argumentString));
            } else {
                linkerResults.set(i, correctNumber != Integer.parseInt(argumentString));
            }
        }

        boolean andFound = false;
        for (String word : words) {
            if (word.equalsIgnoreCase("and")) {
                andFound = true;
            }
        }
        if (andFound) {
            for (boolean b : linkerResults) {
                if (!b) {
                    return false;
                }
            }
            return true;
        } else {
            for (boolean b : linkerResults) {
                if (b) {
                    return true;
                }
            }
            return false;
        }
    }
}
