package com.readlearncode.dukechat.utils;

import javax.json.Json;

/**
 * @author Alex Theedom
 * @version 1.0
 */
public class JsonUtil {

    public static String formatMessage(String message, String user) {
        return Json.createObjectBuilder()
                .add("message", message)
                .add("sender", user)
                .add("received", "")
                .build().toString();
    }
}
