package net.hockeyapp.android.c;

public enum a {
    CrashManagerUserInputDontSend(0),
    CrashManagerUserInputSend(1),
    CrashManagerUserInputAlwaysSend(2);
    
    private final int value;

    private a(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
