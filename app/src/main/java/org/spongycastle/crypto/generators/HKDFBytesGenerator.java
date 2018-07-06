package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.HKDFParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class HKDFBytesGenerator implements DerivationFunction {
    private HMac a;
    private int b;
    private byte[] c;
    private byte[] d;
    private int e;

    public void a(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof HKDFParameters) {
            HKDFParameters hKDFParameters = (HKDFParameters) derivationParameters;
            if (hKDFParameters.b()) {
                this.a.a(new KeyParameter(hKDFParameters.a()));
            } else {
                this.a.a(a(hKDFParameters.c(), hKDFParameters.a()));
            }
            this.c = hKDFParameters.d();
            this.e = 0;
            this.d = new byte[this.b];
            return;
        }
        throw new IllegalArgumentException("HKDF parameters required for HKDFBytesGenerator");
    }

    private KeyParameter a(byte[] bArr, byte[] bArr2) {
        this.a.a(new KeyParameter(bArr2));
        if (bArr == null) {
            this.a.a(new KeyParameter(new byte[this.b]));
        } else {
            this.a.a(new KeyParameter(bArr));
        }
        this.a.a(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.b];
        this.a.a(bArr3, 0);
        return new KeyParameter(bArr3);
    }

    private void a() {
        int i = (this.e / this.b) + 1;
        if (i >= 256) {
            throw new DataLengthException("HKDF cannot generate more than 255 blocks of HashLen size");
        }
        if (this.e != 0) {
            this.a.a(this.d, 0, this.b);
        }
        this.a.a(this.c, 0, this.c.length);
        this.a.a((byte) i);
        this.a.a(this.d, 0);
    }

    public int a(byte[] bArr, int i, int i2) {
        if (this.e + i2 > this.b * 255) {
            throw new DataLengthException("HKDF may only be used for 255 * HashLen bytes of output");
        }
        if (this.e % this.b == 0) {
            a();
        }
        int i3 = this.e % this.b;
        int min = Math.min(this.b - (this.e % this.b), i2);
        System.arraycopy(this.d, i3, bArr, i, min);
        this.e += min;
        i3 = i2 - min;
        min += i;
        while (i3 > 0) {
            a();
            int min2 = Math.min(this.b, i3);
            System.arraycopy(this.d, 0, bArr, min, min2);
            this.e += min2;
            i3 -= min2;
            min += min2;
        }
        return i2;
    }
}
