package com.turkninja.petshop.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    /**
     * Returns root cause of exception.
     *
     * @param e
     * @return
     */
    public static Throwable getRootCause(Throwable e) {
        if (e == null)
            return null;

        Throwable cause = e.getCause();
        Throwable result = null;

        while (cause != null && (result != cause)) {
            result = cause;
            cause = cause.getCause();
        }

        return result;
    }

    /**
     * Returns stack trace of exception
     *
     * @param e
     * @return
     */
    public static String getStackTraceAsString(Throwable e) {
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        return writer.toString();
    }
}