package com.flow.platform.client;

/**
 * Created by gy@fir.im on 12/05/2017.
 *
 * @copyright fir.im
 */
public class ClientLogging {

    static void info(String message) {
        System.out.println(message);
    }

    static void err(Exception e, String description) {
        System.out.println(description);
        System.out.println(e.getMessage());
    }
}
