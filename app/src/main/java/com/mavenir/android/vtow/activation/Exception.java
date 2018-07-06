package com.mavenir.android.vtow.activation;

public class Exception {
    private int mExceptionId;
    private String mExceptionMsg;
    private String mExceptionUrl;

    public Exception(int i, String str, String str2) {
        this.mExceptionId = i;
        this.mExceptionMsg = str;
        this.mExceptionUrl = str2;
    }

    public int getExceptionId() {
        return this.mExceptionId;
    }

    public void setExceptionId(int i) {
        this.mExceptionId = i;
    }

    public String getExceptionMsg() {
        return this.mExceptionMsg;
    }

    public void setExceptionMsg(String str) {
        this.mExceptionMsg = str;
    }

    public String getExceptionUrl() {
        return this.mExceptionUrl;
    }

    public void setExceptionUrl(String str) {
        this.mExceptionUrl = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Exception { ");
        stringBuilder.append("id: " + this.mExceptionId);
        stringBuilder.append(", msg: " + this.mExceptionMsg);
        stringBuilder.append(", URL: " + this.mExceptionUrl);
        stringBuilder.append(" }");
        return super.toString();
    }
}
