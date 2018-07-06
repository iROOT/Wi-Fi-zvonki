package net.hockeyapp.android.c;

import java.io.Serializable;

public class g implements Serializable {
    private static final long serialVersionUID = -1093570359639034766L;
    private d feedback;
    private String status;
    private String token;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public d getFeedback() {
        return this.feedback;
    }

    public void setFeedback(d dVar) {
        this.feedback = dVar;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
