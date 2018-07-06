package org.spongycastle.util.test;

public class SimpleTestResult implements TestResult {
    private static final String a = System.getProperty("line.separator");
    private String b;

    public String toString() {
        return this.b;
    }
}
