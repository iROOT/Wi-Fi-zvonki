package net.hockeyapp.android.c;

public class b {
    private String userDescription;
    private String userEmail;
    private String userID;

    public String getUserDescription() {
        return this.userDescription;
    }

    public void setUserDescription(String str) {
        this.userDescription = str;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String str) {
        this.userEmail = str;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public String toString() {
        return "\n" + b.class.getSimpleName() + "\n" + "userDescription " + this.userDescription + "\n" + "userEmail       " + this.userEmail + "\n" + "userID          " + this.userID;
    }
}
