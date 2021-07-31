package com.turkninja.petshop.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class TypeUtils {

    public static boolean isLong(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Long.parseLong(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isInteger(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isFloat(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isBoolean(String value) {
        if (isEmpty(value)) {
            return false;
        }
        try {
            Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static Long toLong(String value) {
        if (isEmpty(value)) {
            return null;
        }
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer toInteger(String value) {
        if (isEmpty(value)) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Double toDouble(String value) {
        if (isEmpty(value)) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Float toFloat(String value) {
        if (isEmpty(value)) {
            return null;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Boolean toBoolean(String value) {
        if (isEmpty(value)) {
            return null;
        }
        try {
            return Boolean.parseBoolean(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static InetAddress toInetAddress(String value) {
        if (isEmpty(value)) {
            return null;
        }
        try {
            return InetAddress.getByName(value);
        } catch (UnknownHostException e) {
            return null;
        }
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

    public static boolean isEmpty(String value) {
        return value == null || "".equals(value);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean not(boolean value) {
        return !value;
    }

    public static String join(String[] strs, String joinChar) {
        if (isNotEmpty(strs) && isNotEmpty(joinChar)) {
            return join(Arrays.asList(strs), joinChar);
        }
        return "";
    }

    public static String join(Collection<String> strs, String joinChar) {
        if (isNotEmpty(strs) && isNotEmpty(joinChar)) {
            StringBuilder result = new StringBuilder();
            for (String str : strs) {
                result.append(str);
                result.append(joinChar);
            }
            return result.substring(0, result.length() - joinChar.length());
        }
        return "";
    }
}

