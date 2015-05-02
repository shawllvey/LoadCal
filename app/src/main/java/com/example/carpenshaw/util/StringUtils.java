package com.example.carpenshaw.util;

/**
 * Created by carpenshaw on 5/1/15.
 */
public class StringUtils {
    public static boolean isBlank(String str) {
        if (null == str || str.trim().length()==0) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
