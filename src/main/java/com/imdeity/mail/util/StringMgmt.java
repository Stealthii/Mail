package com.imdeity.mail.util;

//~--- JDK imports ------------------------------------------------------------

import java.util.List;

/**
 * Useful functions related to strings, or arrays of them.
 */
public class StringMgmt {

    /**
     * Shortens the string to fit in the specified size with an elipse "..." at
     * the end.
     *
     * @return the shortened string
     */
    public static String maxLength(String str, int length) {
        if (str.length() < length) {
            return str;
        } else if (length > 3) {
            return str.substring(0, length - 3) + "...";
        } else {
            throw new UnsupportedOperationException("Minimum length of 3 characters.");
        }
    }
}
