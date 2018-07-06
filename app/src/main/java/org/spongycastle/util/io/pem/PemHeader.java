package org.spongycastle.util.io.pem;

public class PemHeader {
    private String a;
    private String b;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int hashCode() {
        return a(this.a) + (a(this.b) * 31);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PemHeader)) {
            return false;
        }
        PemHeader pemHeader = (PemHeader) obj;
        if (pemHeader == this || (a(this.a, pemHeader.a) && a(this.b, pemHeader.b))) {
            return true;
        }
        return false;
    }

    private int a(String str) {
        if (str == null) {
            return 1;
        }
        return str.hashCode();
    }

    private boolean a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }
}
