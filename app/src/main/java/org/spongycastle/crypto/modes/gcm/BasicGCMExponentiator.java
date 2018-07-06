package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

public class BasicGCMExponentiator implements GCMExponentiator {
    private int[] a;

    public void a(byte[] bArr) {
        this.a = GCMUtil.a(bArr);
    }

    public void a(long j, byte[] bArr) {
        int[] a = GCMUtil.a();
        if (j > 0) {
            int[] b = Arrays.b(this.a);
            do {
                if ((1 & j) != 0) {
                    GCMUtil.a(a, b);
                }
                GCMUtil.a(b, b);
                j >>>= 1;
            } while (j > 0);
        }
        GCMUtil.a(a, bArr);
    }
}
