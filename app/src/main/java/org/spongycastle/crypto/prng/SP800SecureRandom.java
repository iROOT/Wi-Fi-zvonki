package org.spongycastle.crypto.prng;

import java.security.SecureRandom;
import org.spongycastle.crypto.prng.drbg.SP80090DRBG;

public class SP800SecureRandom extends SecureRandom {
    private final DRBGProvider a;
    private final boolean b;
    private final SecureRandom c;
    private final EntropySource d;
    private SP80090DRBG e;

    public void setSeed(byte[] bArr) {
        synchronized (this) {
            if (this.c != null) {
                this.c.setSeed(bArr);
            }
        }
    }

    public void setSeed(long j) {
        synchronized (this) {
            if (this.c != null) {
                this.c.setSeed(j);
            }
        }
    }

    public void nextBytes(byte[] bArr) {
        synchronized (this) {
            if (this.e == null) {
                this.e = this.a.a(this.d);
            }
            if (this.e.a(bArr, null, this.b) < 0) {
                this.e.a(this.d.a());
                this.e.a(bArr, null, this.b);
            }
        }
    }

    public byte[] generateSeed(int i) {
        byte[] bArr = new byte[i];
        nextBytes(bArr);
        return bArr;
    }
}
