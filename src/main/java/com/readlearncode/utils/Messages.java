package com.readlearncode.utils;

import com.readlearncode.domain.Message;

import java.util.Calendar;
import java.util.Date;

import static java.lang.String.format;

/**
 * Source code github.com/readlearncode
 *
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Messages {

    public static final String WELCOME_MESSAGE = "Welcome to Java Chat";
    public static final String ANNOUNCE_NEW_USER = "%s just entered the room.";
    public static final String ANNOUNCE_LEAVER = "%s just left the room. We'll miss you";

    public static String personlize(String message, String... args){
       return format(message, args);
    }

    public static Message objectify(String content, String... args){
        return objectify(content, "Chat Bot", Calendar.getInstance().getTime(), args);
    }

    public static Message objectify(String content, String sender, String... args){
        return objectify(content, sender, Calendar.getInstance().getTime(), args);
    }

    public static Message objectify(String content, String sender, Date received, String... args){
        return new Message(personlize(content, args), sender, received);
    }
}
