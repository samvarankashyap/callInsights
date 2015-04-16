package com.samvarankashyap.callinsightsapp;

/**
 * Created by samvarankashyap on 4/14/2015.
 */
public class CallRecord {
    private int id;
    private String callerId;
    private String callType;
    private String date;
    private String callTime;
    private String callDuration;
    public CallRecord(){}
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCallTime() {
        return callTime;
    }
    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }
    public CallRecord(int id, String callerId, String callType,String date, String callDuration, String wcallTime) {
        this.id = id;
        this.callerId = callerId;
        this.callType = callType;
        this.callDuration = callDuration;
        this.date = date;
        this.callTime = callTime;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCallerId() {
        return callerId;
    }
    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }
    public String getCallType() {
        return callType;
    }
    public void setCallType(String callType) {
        this.callType = callType;
    }
    public String getCallDuration() {
        return callDuration;
    }
    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

}