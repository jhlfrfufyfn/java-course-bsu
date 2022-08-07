package com.bsu;

class StringProcessor {
    static String deleteNumbers(String text) {
        String[] words = text.split("[.,\\s]+");
        boolean[] flags = new boolean[words.length];

        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            int countDigits = 0;
            String word = words[wordIndex];
            for (int i = 0; i < word.length(); i++) {
                if (Character.isDigit(word.charAt(i))) {
                    countDigits++;
                }
            }
            if (countDigits == word.length()) {
                flags[wordIndex] = true;
            }
        }

        for (int i = 0; i < flags.length; i++) {
            if (flags[i]) {
                text = text.replaceFirst("([\\s.,]|^)(" + words[i] + ")([\\s.,]|$)", " ");
            }
        }
        return text;
    }
}