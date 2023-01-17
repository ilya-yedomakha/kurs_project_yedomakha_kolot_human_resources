package com.example.kurs_project_3.presentation.response;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String responseMessage;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamp, String responseMessage) {
        this.timestamp = timestamp;
        this.responseMessage = responseMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
