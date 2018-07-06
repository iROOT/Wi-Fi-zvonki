package net.hockeyapp.android.c;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import net.hockeyapp.android.a;

public class e implements Serializable {
    private static final long serialVersionUID = 5059651319640956830L;
    private String createdAt;
    private String filename;
    private int id;
    private int messageId;
    private String updatedAt;
    private String url;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int i) {
        this.messageId = i;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public String getCacheId() {
        return "" + this.messageId + this.id;
    }

    public boolean isAvailableInCache() {
        File hockeyAppStorageDir = a.getHockeyAppStorageDir();
        if (!hockeyAppStorageDir.exists() || !hockeyAppStorageDir.isDirectory()) {
            return false;
        }
        File[] listFiles = hockeyAppStorageDir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.equals(e.this.getCacheId());
            }
        });
        if (listFiles == null || listFiles.length != 1) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "\n" + e.class.getSimpleName() + "\n" + "id         " + this.id + "\n" + "message id " + this.messageId + "\n" + "filename   " + this.filename + "\n" + "url        " + this.url + "\n" + "createdAt  " + this.createdAt + "\n" + "updatedAt  " + this.updatedAt;
    }
}
