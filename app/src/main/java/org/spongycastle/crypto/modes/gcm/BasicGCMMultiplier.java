package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Arrays;

public class BasicGCMMultiplier implements GCMMultiplier {
    private byte[] a;

    public void a(byte[] bArr) {
        this.a = Arrays.b(bArr);
    }

    public void b(byte[] bArr) {
        GCMUtil.a(bArr, this.a);
    }
}
