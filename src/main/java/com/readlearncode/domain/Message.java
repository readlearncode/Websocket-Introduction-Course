package com.readlearncode.domain;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alex Theedom www.readlearncode.com
 * @version 1.0
 */
public class Message {

    private String content;
    private String sender;
    private Date received;

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

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
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
