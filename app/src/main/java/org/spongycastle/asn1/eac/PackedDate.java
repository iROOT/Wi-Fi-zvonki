package org.spongycastle.asn1.eac;

import org.spongycastle.util.Arrays;

public class PackedDate {
    private byte[] a;

    public int hashCode() {
        return Arrays.a(this.a);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PackedDate)) {
            return false;
        }
        return Arrays.a(this.a, ((PackedDate) obj).a);
    }

    public String toString() {
        char[] cArr = new char[this.a.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) ((this.a[i] & 255) + 48);
        }
        return new String(cArr);
    }
}
