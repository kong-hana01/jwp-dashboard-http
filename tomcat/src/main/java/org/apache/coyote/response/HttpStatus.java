package org.apache.coyote.response;

public enum HttpStatus {

    OK(200, "OK");

    private final int statusCode;
    private final String status;

    HttpStatus(int statusCode, String status) {
        this.statusCode = statusCode;
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }
}