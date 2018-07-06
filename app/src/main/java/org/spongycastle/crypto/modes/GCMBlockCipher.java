package org.spongycastle.crypto.modes;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.modes.gcm.GCMExponentiator;
import org.spongycastle.crypto.modes.gcm.GCMMultiplier;
import org.spongycastle.crypto.modes.gcm.Tables1kGCMExponentiator;
import org.spongycastle.crypto.modes.gcm.Tables8kGCMMultiplier;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Arrays;

public class GCMBlockCipher implements AEADBlockCipher {
    private BlockCipher a;
    private GCMMultiplier b;
    private GCMExponentiator c;
    private boolean d;
    private int e;
    private byte[] f;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private int p;
    private long q;
    private byte[] r;
    private int s;
    private long t;
    private long u;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.b() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        if (gCMMultiplier == null) {
            gCMMultiplier = new Tables8kGCMMultiplier();
        }
        this.a = blockCipher;
        this.b = gCMMultiplier;
    }

    public BlockCipher a() {
        return this.a;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        int b;
        this.d = z;
        this.k = null;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            this.f = aEADParameters.d();
            this.g = aEADParameters.c();
            b = aEADParameters.b();
            if (b < 96 || b > NotificationCompat.FLAG_HIGH_PRIORITY || b % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + b);
            }
            this.e = b / 8;
            CipherParameters a = aEADParameters.a();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.f = parametersWithIV.a();
            this.g = null;
            this.e = 16;
            Object a2 = (KeyParameter) parametersWithIV.b();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        }
        if (z) {
            b = 16;
        } else {
            b = this.e + 16;
        }
        this.j = new byte[b];
        if (this.f == null || this.f.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (a2 != null) {
            this.a.a(true, a2);
            this.h = new byte[16];
            this.a.a(this.h, 0, this.h, 0);
            this.b.a(this.h);
            this.c = null;
        }
        this.i = new byte[16];
        if (this.f.length == 12) {
            System.arraycopy(this.f, 0, this.i, 0, this.f.length);
            this.i[15] = (byte) 1;
        } else {
            b(this.i, this.f, this.f.length);
            byte[] bArr = new byte[16];
            Pack.a(((long) this.f.length) * 8, bArr, 8);
            a(this.i, bArr);
        }
        this.l = new byte[16];
        this.m = new byte[16];
        this.n = new byte[16];
        this.r = new byte[16];
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.o = Arrays.b(this.i);
        this.p = 0;
        this.q = 0;
        if (this.g != null) {
            a(this.g, 0, this.g.length);
        }
    }

    public int b(int i) {
        int i2 = this.p + i;
        if (this.d) {
            return i2 + this.e;
        }
        return i2 < this.e ? 0 : i2 - this.e;
    }

    public int a(int i) {
        int i2 = this.p + i;
        if (!this.d) {
            if (i2 < this.e) {
                return 0;
            }
            i2 -= this.e;
        }
        return i2 - (i2 % 16);
    }

    public void a(byte b) {
        this.r[this.s] = b;
        int i = this.s + 1;
        this.s = i;
        if (i == 16) {
            a(this.m, this.r);
            this.s = 0;
            this.t += 16;
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.r[this.s] = bArr[i + i3];
            int i4 = this.s + 1;
            this.s = i4;
            if (i4 == 16) {
                a(this.m, this.r);
                this.s = 0;
                this.t += 16;
            }
        }
    }

    private void c() {
        if (this.t > 0) {
            System.arraycopy(this.m, 0, this.n, 0, 16);
            this.u = this.t;
        }
        if (this.s > 0) {
            a(this.n, this.r, 0, this.s);
            this.u += (long) this.s;
        }
        if (this.u > 0) {
            System.arraycopy(this.n, 0, this.l, 0, 16);
        }
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            this.j[this.p] = bArr[i + i5];
            int i6 = this.p + 1;
            this.p = i6;
            if (i6 == this.j.length) {
                b(bArr2, i3 + i4);
                i4 += 16;
            }
        }
        return i4;
    }

    private void b(byte[] bArr, int i) {
        if (this.q == 0) {
            c();
        }
        a(this.j, bArr, i);
        if (this.d) {
            this.p = 0;
            return;
        }
        System.arraycopy(this.j, 16, this.j, 0, this.e);
        this.p = this.e;
    }

    public int a(byte[] bArr, int i) {
        if (this.q == 0) {
            c();
        }
        int i2 = this.p;
        if (!this.d) {
            if (i2 < this.e) {
                throw new InvalidCipherTextException("data too short");
            }
            i2 -= this.e;
        }
        if (i2 > 0) {
            b(this.j, 0, i2, bArr, i);
        }
        this.t += (long) this.s;
        if (this.t > this.u) {
            if (this.s > 0) {
                a(this.m, this.r, 0, this.s);
            }
            if (this.u > 0) {
                c(this.m, this.n);
            }
            long j = ((this.q * 8) + 127) >>> 7;
            byte[] bArr2 = new byte[16];
            if (this.c == null) {
                this.c = new Tables1kGCMExponentiator();
                this.c.a(this.h);
            }
            this.c.a(j, bArr2);
            b(this.m, bArr2);
            c(this.l, this.m);
        }
        byte[] bArr3 = new byte[16];
        Pack.a(this.t * 8, bArr3, 0);
        Pack.a(this.q * 8, bArr3, 8);
        a(this.l, bArr3);
        Object obj = new byte[16];
        this.a.a(this.i, 0, obj, 0);
        c(obj, this.l);
        this.k = new byte[this.e];
        System.arraycopy(obj, 0, this.k, 0, this.e);
        if (this.d) {
            System.arraycopy(this.k, 0, bArr, this.p + i, this.e);
            i2 += this.e;
        } else {
            bArr3 = new byte[this.e];
            System.arraycopy(this.j, i2, bArr3, 0, this.e);
            if (!Arrays.b(this.k, bArr3)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        a(false);
        return i2;
    }

    public void b() {
        a(true);
    }

    private void a(boolean z) {
        this.a.c();
        this.l = new byte[16];
        this.m = new byte[16];
        this.n = new byte[16];
        this.r = new byte[16];
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.o = Arrays.b(this.i);
        this.p = 0;
        this.q = 0;
        if (this.j != null) {
            Arrays.a(this.j, (byte) 0);
        }
        if (z) {
            this.k = null;
        }
        if (this.g != null) {
            a(this.g, 0, this.g.length);
        }
    }

    private void a(byte[] bArr, byte[] bArr2, int i) {
        Object d = d();
        c(d, bArr);
        System.arraycopy(d, 0, bArr2, i, 16);
        byte[] bArr3 = this.l;
        if (this.d) {
            bArr = d;
        }
        a(bArr3, bArr);
        this.q += 16;
    }

    private void b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        Object d = d();
        b(d, bArr, i, i2);
        System.arraycopy(d, 0, bArr2, i3, i2);
        byte[] bArr3 = this.l;
        if (this.d) {
            bArr = d;
        }
        a(bArr3, bArr, 0, i2);
        this.q += (long) i2;
    }

    private void b(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2 += 16) {
            a(bArr, bArr2, i2, Math.min(i - i2, 16));
        }
    }

    private void a(byte[] bArr, byte[] bArr2) {
        c(bArr, bArr2);
        this.b.b(bArr);
    }

    private void a(byte[] bArr, byte[] bArr2, int i, int i2) {
        b(bArr, bArr2, i, i2);
        this.b.b(bArr);
    }

    private byte[] d() {
        for (int i = 15; i >= 12; i--) {
            byte b = (byte) ((this.o[i] + 1) & 255);
            this.o[i] = b;
            if (b != (byte) 0) {
                break;
            }
        }
        byte[] bArr = new byte[16];
        this.a.a(this.o, 0, bArr, 0);
        return bArr;
    }

    private static void b(byte[] bArr, byte[] bArr2) {
        byte[] b = Arrays.b(bArr);
        Object obj = new byte[16];
        for (int i = 0; i < 16; i++) {
            byte b2 = bArr2[i];
            for (int i2 = 7; i2 >= 0; i2--) {
                int i3;
                if (((1 << i2) & b2) != 0) {
                    c(obj, b);
                }
                if ((b[15] & 1) != 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                a(b);
                if (i3 != 0) {
                    b[0] = (byte) (b[0] ^ -31);
                }
            }
        }
        System.arraycopy(obj, 0, bArr, 0, 16);
    }

    private static void a(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = bArr[i2] & 255;
            bArr[i2] = (byte) (i | (i3 >>> 1));
            i2++;
            if (i2 != 16) {
                i = (i3 & 1) << 7;
            } else {
                return;
            }
        }
    }

    private static void c(byte[] bArr, byte[] bArr2) {
        for (int i = 15; i >= 0; i--) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    private static void b(byte[] bArr, byte[] bArr2, int i, int i2) {
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                bArr[i3] = (byte) (bArr[i3] ^ bArr2[i + i3]);
                i2 = i3;
            } else {
                return;
            }
        }
    }
}
