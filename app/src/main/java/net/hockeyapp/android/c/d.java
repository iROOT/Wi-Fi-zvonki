package net.hockeyapp.android.c;

import java.io.Serializable;
import java.util.ArrayList;

public class d implements Serializable {
    private static final long serialVersionUID = 2590172806951065320L;
    private String createdAt;
    private String email;
    private int id;
    private ArrayList<f> messages;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public ArrayList<f> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<f> arrayList) {
        this.messages = arrayList;
    }
}
