package org.spongycastle.crypto.engines;

import net.hockeyapp.android.k;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class HC128Engine implements StreamCipher {
    private int[] a = new int[512];
    private int[] b = new int[512];
    private int c = 0;
    private byte[] d;
    private byte[] e;
    private boolean f;
    private byte[] g = new byte[4];
    private int h = 0;

    private static int a(int i) {
        return (b(i, 7) ^ b(i, 18)) ^ (i >>> 3);
    }

    private static int b(int i) {
        return (b(i, 17) ^ b(i, 19)) ^ (i >>> 10);
    }

    private int a(int i, int i2, int i3) {
        return (b(i, 10) ^ b(i3, 23)) + b(i2, 8);
    }

    private int b(int i, int i2, int i3) {
        return (a(i, 10) ^ a(i3, 23)) + a(i2, 8);
    }

    private static int a(int i, int i2) {
        return (i << i2) | (i >>> (-i2));
    }

    private static int b(int i, int i2) {
        return (i >>> i2) | (i << (-i2));
    }

    private int c(int i) {
        return this.b[i & 255] + this.b[((i >> 16) & 255) + 256];
    }

    private int d(int i) {
        return this.a[i & 255] + this.a[((i >> 16) & 255) + 256];
    }

    private static int e(int i) {
        return i & 1023;
    }

    private static int f(int i) {
        return i & 511;
    }

    private static int c(int i, int i2) {
        return f(i - i2);
    }

    private int c() {
        int f = f(this.c);
        int[] iArr;
        if (this.c < 512) {
            iArr = this.a;
            iArr[f] = iArr[f] + a(this.a[c(f, 3)], this.a[c(f, 10)], this.a[c(f, 511)]);
            f = this.a[f] ^ c(this.a[c(f, 12)]);
        } else {
            iArr = this.b;
            iArr[f] = iArr[f] + b(this.b[c(f, 3)], this.b[c(f, 10)], this.b[c(f, 511)]);
            f = this.b[f] ^ d(this.b[c(f, 12)]);
        }
        this.c = e(this.c + 1);
        return f;
    }

    private void d() {
        if (this.d.length != 16) {
            throw new IllegalArgumentException("The key must be 128 bits long");
        }
        int i;
        this.h = 0;
        this.c = 0;
        Object obj = new int[k.LOGIN_HEADLINE_TEXT_ID];
        for (i = 0; i < 16; i++) {
            int i2 = i >> 2;
            obj[i2] = obj[i2] | ((this.d[i] & 255) << ((i & 3) * 8));
        }
        System.arraycopy(obj, 0, obj, 4, 4);
        i = 0;
        while (i < this.e.length && i < 16) {
            i2 = (i >> 2) + 8;
            obj[i2] = obj[i2] | ((this.e[i] & 255) << ((i & 3) * 8));
            i++;
        }
        System.arraycopy(obj, 8, obj, 12, 4);
        for (i = 16; i < k.LOGIN_HEADLINE_TEXT_ID; i++) {
            obj[i] = (((b(obj[i - 2]) + obj[i - 7]) + a(obj[i - 15])) + obj[i - 16]) + i;
        }
        System.arraycopy(obj, 256, this.a, 0, 512);
        System.arraycopy(obj, k.EXPIRY_INFO_TITLE_ID, this.b, 0, 512);
        for (i = 0; i < 512; i++) {
            this.a[i] = c();
        }
        for (i = 0; i < 512; i++) {
            this.b[i] = c();
        }
        this.c = 0;
    }

    public String a() {
        return "HC-128";
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters b;
        if (cipherParameters instanceof ParametersWithIV) {
            this.e = ((ParametersWithIV) cipherParameters).a();
            b = ((ParametersWithIV) cipherParameters).b();
        } else {
            this.e = new byte[0];
            b = cipherParameters;
        }
        if (b instanceof KeyParameter) {
            this.d = ((KeyParameter) b).a();
            d();
            this.f = true;
            return;
        }
        throw new IllegalArgumentException("Invalid parameter passed to HC128 init - " + cipherParameters.getClass().getName());
    }

    private byte e() {
        if (this.h == 0) {
            int c = c();
            this.g[0] = (byte) (c & 255);
            c >>= 8;
            this.g[1] = (byte) (c & 255);
            c >>= 8;
            this.g[2] = (byte) (c & 255);
            this.g[3] = (byte) ((c >> 8) & 255);
        }
        byte b = this.g[this.h];
        this.h = (this.h + 1) & 3;
        return b;
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.f) {
            throw new IllegalStateException(a() + " not initialised");
        } else if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                bArr2[i3 + i4] = (byte) (bArr[i + i4] ^ e());
            }
        }
    }

    public void b() {
        d();
    }

    public byte a(byte b) {
        return (byte) (e() ^ b);
    }
}
