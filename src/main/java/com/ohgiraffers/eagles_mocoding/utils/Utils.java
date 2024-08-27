package com.ohgiraffers.eagles_mocoding.utils;

import java.util.regex.Pattern;

public class Utils {
    public static boolean regex(String input) {
        String regex = "^[가-힣]+$";
        boolean result = Pattern.matches(regex, input);
        return result;
    }
}
