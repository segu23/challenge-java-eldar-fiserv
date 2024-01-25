package com.github.segu23;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] myArray = new String[]{"FirstWord", "SecondWord", "THIRDWORD"};
        System.out.println(joinArrayAndLowerWithStream(myArray));
        System.out.println(joinArrayAndLowerWithLowLevel(myArray));
    }

    public static String joinArrayAndLowerWithStream(String[] array) {
        return Arrays.stream(array)
                .map(String::toLowerCase)
                .collect(Collectors.joining(" "));
    }

    public static String joinArrayAndLowerWithLowLevel(String[] array) {
        String output = "";
        for (int i = 0; i < array.length; i++) {
            String word = array[i];
            for (int j = 0; j < word.length(); j++) {
                int charCode = word.charAt(j);
                if (charCode >= 65 && charCode <= 90) charCode += 32;

                output += (char) charCode;
            }
            if (i < array.length - 1) output += (char) 32;
        }
        return output;
    }
}