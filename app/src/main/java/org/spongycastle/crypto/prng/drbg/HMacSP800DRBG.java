package org.spongycastle.crypto.prng.drbg;

import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.util.Arrays;

public class HMacSP800DRBG implements SP80090DRBG {
    private byte[] a;
    private byte[] b;
    private long c;
    private EntropySource d;
    private Mac e;

    public HMacSP800DRBG(Mac mac, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        if (i > Utils.a(mac)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        } else if (entropySource.b() < i) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else {
            this.d = entropySource;
            this.e = mac;
            byte[] a = Arrays.a(entropySource.a(), bArr2, bArr);
            this.a = new byte[mac.b()];
            this.b = new byte[this.a.length];
            Arrays.a(this.b, (byte) 1);
            b(a);
            this.c = 1;
        }
    }

    private void b(byte[] bArr) {
        a(bArr, (byte) 0);
        if (bArr != null) {
            a(bArr, (byte) 1);
        }
    }

    private void a(byte[] bArr, byte b) {
        this.e.a(new KeyParameter(this.a));
        this.e.a(this.b, 0, this.b.length);
        this.e.a(b);
        if (bArr != null) {
            this.e.a(bArr, 0, bArr.length);
        }
        this.e.a(this.a, 0);
        this.e.a(new KeyParameter(this.a));
        this.e.a(this.b, 0, this.b.length);
        this.e.a(this.b, 0);
    }

    public int a(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        if (length > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        } else if (this.c > 140737488355328L) {
            return -1;
        } else {
            if (z) {
                a(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                b(bArr2);
            }
            Object obj = new byte[bArr.length];
            int length2 = bArr.length / this.b.length;
            this.e.a(new KeyParameter(this.a));
            for (int i = 0; i < length2; i++) {
                this.e.a(this.b, 0, this.b.length);
                this.e.a(this.b, 0);
                System.arraycopy(this.b, 0, obj, this.b.length * i, this.b.length);
            }
            if (this.b.length * length2 < obj.length) {
                this.e.a(this.b, 0, this.b.length);
                this.e.a(this.b, 0);
                System.arraycopy(this.b, 0, obj, this.b.length * length2, obj.length - (length2 * this.b.length));
            }
            b(bArr2);
            this.c++;
            System.arraycopy(obj, 0, bArr, 0, bArr.length);
            return length;
        }
    }

    public void a(byte[] bArr) {
        b(Arrays.d(this.d.a(), bArr));
        this.c = 1;
    }
}
