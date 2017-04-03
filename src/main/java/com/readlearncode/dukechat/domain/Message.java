package com.readlearncode.dukechat.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Alex Theedom
 * @version 1.0
 */
public class Message {

    private String content;
    private String sender;
    private LocalDate received;

    public Message() {
    }

    public Message(String content, String sender) {
        this(content, sender, LocalDate.now());
    }

    public Message(String content, String sender, LocalDate received) {
        this.content = content;
        this.sender = sender;
        this.received = received;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDate getReceived() {
        return received;
    }

    public void setReceived(LocalDate received) {
        this.received = received;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(content, message.content) &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(received, message.received);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, sender, received);
    }
}
