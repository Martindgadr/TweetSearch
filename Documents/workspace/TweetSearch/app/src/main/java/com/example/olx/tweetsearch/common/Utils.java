package com.example.olx.tweetsearch.common;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by Martin De Girolamo on 12/14/16.
 */

public class Utils {
    public static String getBase64String(String value) throws UnsupportedEncodingException {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }
}
