package com.atelier.sunny.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class URLUtils {
    private static final Logger logger = LoggerFactory.getLogger(URLUtils.class);

    public static boolean isValid(String url)
    {
        /* Try creating a valid URL */
        try {
            new URL(url);
            return true;
        }
        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            logger.error("Unknown link: "+ url, e.getCause());
            return false;
        }
    }
}
