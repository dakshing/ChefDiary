package com.mydiaries.chefdiary.common;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class APIError {
    private HttpStatus status;
    private List<String> messages;
    private String errorTrace;

    public APIError(HttpStatus status, List<String> messages, String errorTrace) {
        this.status = status;
        this.messages = messages;
        this.errorTrace = errorTrace;
    }

    public APIError(HttpStatus status, String error, String errorTrace) {
        this.status = status;
        this.messages = Collections.singletonList(error);
        this.errorTrace = errorTrace;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public String getErrorTrace() {
        return errorTrace;
    }

    public void setErrorTrace(String errorTrace) {
        this.errorTrace = errorTrace;
    }
}
