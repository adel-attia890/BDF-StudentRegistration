package com.aa.bdf.studentregistration.courseapi.entity;


import java.sql.Timestamp;
import java.util.Date;

public class Response {
    Date timeStamp;
    boolean errorFlag;
    String message;
    Object result;

    public Response(boolean errorFlag, String message, Object result) {
        this.timeStamp = new Timestamp(new Date().getTime());
        this.errorFlag = errorFlag;
        this.message = message;
        this.result = result;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(boolean errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
