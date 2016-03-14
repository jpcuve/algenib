package com.darts.algenib.bean;

import javax.activation.DataSource;

/**
 * Created by jpc on 11/4/14.
 */
public class MailEvent {
    private String to;
    private String bcc;
    private String subject;
    private DataSource[] dataSources;

    public MailEvent() {
    }

    public MailEvent(String to, String bcc, DataSource... dataSources) {
        this.to = to;
        this.bcc = bcc;
        this.dataSources = dataSources;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public DataSource[] getDataSources() {
        return dataSources;
    }

    public void setDataSources(DataSource[] dataSources) {
        this.dataSources = dataSources;
    }
}
