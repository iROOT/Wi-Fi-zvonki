package net.hockeyapp.android.c;

public enum h {
    DONT_SHOW(0),
    OPTIONAL(1),
    REQUIRED(2);
    
    private final int value;

    private h(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
