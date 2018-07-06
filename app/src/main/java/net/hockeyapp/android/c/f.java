package net.hockeyapp.android.c;

import java.io.Serializable;
import java.util.List;

public class f implements Serializable {
    private static final long serialVersionUID = -8773015828853994624L;
    private String appId;
    private String cleanText;
    private String createdAt;
    private List<e> feedbackAttachments;
    private int id;
    private String model;
    private String name;
    private String oem;
    private String osVersion;
    private String subject;
    private String text;
    private String token;
    private String userString;
    private int via;

    public String getSubjec() {
        return this.subject;
    }

    public void setSubjec(String str) {
        this.subject = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getOem() {
        return this.oem;
    }

    public void setOem(String str) {
        this.oem = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public int getVia() {
        return this.via;
    }

    public void setVia(int i) {
        this.via = i;
    }

    public String getUserString() {
        return this.userString;
    }

    public void setUserString(String str) {
        this.userString = str;
    }

    public String getCleanText() {
        return this.cleanText;
    }

    public void setCleanText(String str) {
        this.cleanText = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public List<e> getFeedbackAttachments() {
        return this.feedbackAttachments;
    }

    public void setFeedbackAttachments(List<e> list) {
        this.feedbackAttachments = list;
    }
}
