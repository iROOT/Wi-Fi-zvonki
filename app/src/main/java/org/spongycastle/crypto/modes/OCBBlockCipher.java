package org.spongycastle.crypto.modes;

import android.support.v4.app.NotificationCompat;
import java.util.Vector;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class OCBBlockCipher implements AEADBlockCipher {
    private BlockCipher a;
    private BlockCipher b;
    private boolean c;
    private int d;
    private byte[] e;
    private Vector f;
    private byte[] g;
    private byte[] h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private int l;
    private int m;
    private long n;
    private long o;
    private byte[] p;
    private byte[] q;
    private byte[] r;
    private byte[] s;
    private byte[] t;

    public OCBBlockCipher(BlockCipher blockCipher, BlockCipher blockCipher2) {
        if (blockCipher == null) {
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        } else if (blockCipher.b() != 16) {
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        } else if (blockCipher2 == null) {
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        } else if (blockCipher2.b() != 16) {
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        } else if (blockCipher.a().equals(blockCipher2.a())) {
            this.a = blockCipher;
            this.b = blockCipher2;
        } else {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        }
    }

    public BlockCipher a() {
        return this.b;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        Object d;
        int b;
        int i;
        this.c = z;
        this.t = null;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            d = aEADParameters.d();
            this.e = aEADParameters.c();
            b = aEADParameters.b();
            if (b < 64 || b > NotificationCompat.FLAG_HIGH_PRIORITY || b % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + b);
            }
            this.d = b / 8;
            CipherParameters a = aEADParameters.a();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            Object a2 = parametersWithIV.a();
            this.e = null;
            this.d = 16;
            Object obj = a2;
            a2 = (KeyParameter) parametersWithIV.b();
            d = obj;
        } else {
            throw new IllegalArgumentException("invalid parameters passed to OCB");
        }
        this.j = new byte[16];
        if (z) {
            i = 16;
        } else {
            i = this.d + 16;
        }
        this.k = new byte[i];
        if (d == null) {
            d = new byte[0];
        }
        if (d.length > 15) {
            throw new IllegalArgumentException("IV must be no more than 15 bytes");
        }
        int length;
        Object obj2;
        Object obj3;
        if (a == null) {
            this.a.a(true, a);
            this.b.a(z, a);
            this.g = new byte[16];
            this.a.a(this.g, 0, this.g, 0);
            this.h = c(this.g);
            this.f = new Vector();
            this.f.addElement(c(this.h));
            a2 = new byte[16];
            System.arraycopy(d, 0, a2, a2.length - d.length, d.length);
            a2[0] = (byte) (this.d << 4);
            length = 15 - d.length;
            a2[length] = (byte) (a2[length] | 1);
            i = a2[15] & 63;
            obj2 = new byte[16];
            a2[15] = (byte) (a2[15] & 192);
            this.a.a(a2, 0, obj2, 0);
            obj3 = new byte[24];
            System.arraycopy(obj2, 0, obj3, 0, 16);
        } else {
            this.a.a(true, a);
            this.b.a(z, a);
            this.g = new byte[16];
            this.a.a(this.g, 0, this.g, 0);
            this.h = c(this.g);
            this.f = new Vector();
            this.f.addElement(c(this.h));
            a2 = new byte[16];
            System.arraycopy(d, 0, a2, a2.length - d.length, d.length);
            a2[0] = (byte) (this.d << 4);
            length = 15 - d.length;
            a2[length] = (byte) (a2[length] | 1);
            i = a2[15] & 63;
            obj2 = new byte[16];
            a2[15] = (byte) (a2[15] & 192);
            this.a.a(a2, 0, obj2, 0);
            obj3 = new byte[24];
            System.arraycopy(obj2, 0, obj3, 0, 16);
        }
        for (length = 0; length < 8; length++) {
            obj3[length + 16] = (byte) (obj2[length] ^ obj2[length + 1]);
        }
        this.i = new byte[16];
        int i2 = i % 8;
        length = i / 8;
        if (i2 == 0) {
            System.arraycopy(obj3, length, this.i, 0, 16);
        } else {
            b = length;
            for (length = 0; length < 16; length++) {
                i = obj3[b] & 255;
                b++;
                this.i[length] = (byte) ((i << i2) | ((obj3[b] & 255) >>> (8 - i2)));
            }
        }
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = new byte[16];
        this.q = new byte[16];
        this.r = Arrays.b(this.i);
        this.s = new byte[16];
        if (this.e != null) {
            a(this.e, 0, this.e.length);
        }
    }

    public int b(int i) {
        int i2 = this.m + i;
        if (this.c) {
            return i2 + this.d;
        }
        return i2 < this.d ? 0 : i2 - this.d;
    }

    public int a(int i) {
        int i2 = this.m + i;
        if (!this.c) {
            if (i2 < this.d) {
                return 0;
            }
            i2 -= this.d;
        }
        return i2 - (i2 % 16);
    }

    public void a(byte[] bArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.j[this.l] = bArr[i + i3];
            int i4 = this.l + 1;
            this.l = i4;
            if (i4 == this.j.length) {
                b();
            }
        }
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            this.k[this.m] = bArr[i + i5];
            int i6 = this.m + 1;
            this.m = i6;
            if (i6 == this.k.length) {
                b(bArr2, i3 + i4);
                i4 += 16;
            }
        }
        return i4;
    }

    public int a(byte[] bArr, int i) {
        int i2;
        byte[] bArr2 = null;
        if (!this.c) {
            if (this.m < this.d) {
                throw new InvalidCipherTextException("data too short");
            }
            this.m -= this.d;
            bArr2 = new byte[this.d];
            System.arraycopy(this.k, this.m, bArr2, 0, this.d);
        }
        if (this.l > 0) {
            c(this.j, this.l);
            b(this.g);
        }
        if (this.m > 0) {
            if (this.c) {
                c(this.k, this.m);
                b(this.s, this.k);
            }
            b(this.r, this.g);
            byte[] bArr3 = new byte[16];
            this.a.a(this.r, 0, bArr3, 0);
            b(this.k, bArr3);
            System.arraycopy(this.k, 0, bArr, i, this.m);
            if (!this.c) {
                c(this.k, this.m);
                b(this.s, this.k);
            }
        }
        b(this.s, this.r);
        b(this.s, this.h);
        this.a.a(this.s, 0, this.s, 0);
        b(this.s, this.q);
        this.t = new byte[this.d];
        System.arraycopy(this.s, 0, this.t, 0, this.d);
        int i3 = this.m;
        if (this.c) {
            System.arraycopy(this.t, 0, bArr, i + i3, this.d);
            i2 = this.d + i3;
        } else if (Arrays.b(this.t, bArr2)) {
            i2 = i3;
        } else {
            throw new InvalidCipherTextException("mac check in OCB failed");
        }
        a(false);
        return i2;
    }

    protected void a(byte[] bArr) {
        if (bArr != null) {
            Arrays.a(bArr, (byte) 0);
        }
    }

    protected byte[] c(int i) {
        while (i >= this.f.size()) {
            this.f.addElement(c((byte[]) this.f.lastElement()));
        }
        return (byte[]) this.f.elementAt(i);
    }

    protected void b() {
        long j = this.n + 1;
        this.n = j;
        b(c(a(j)));
        this.l = 0;
    }

    protected void b(byte[] bArr, int i) {
        if (this.c) {
            b(this.s, this.k);
            this.m = 0;
        }
        byte[] bArr2 = this.r;
        long j = this.o + 1;
        this.o = j;
        b(bArr2, c(a(j)));
        b(this.k, this.r);
        this.b.a(this.k, 0, this.k, 0);
        b(this.k, this.r);
        System.arraycopy(this.k, 0, bArr, i, 16);
        if (!this.c) {
            b(this.s, this.k);
            System.arraycopy(this.k, 16, this.k, 0, this.d);
            this.m = this.d;
        }
    }

    protected void a(boolean z) {
        this.a.c();
        this.b.c();
        a(this.j);
        a(this.k);
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        a(this.p);
        a(this.q);
        System.arraycopy(this.i, 0, this.r, 0, 16);
        a(this.s);
        if (z) {
            this.t = null;
        }
        if (this.e != null) {
            a(this.e, 0, this.e.length);
        }
    }

    protected void b(byte[] bArr) {
        b(this.p, bArr);
        b(this.j, this.p);
        this.a.a(this.j, 0, this.j, 0);
        b(this.q, this.j);
    }

    protected static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) ((135 >>> ((1 - a(bArr, bArr2)) << 3)) ^ bArr2[15]);
        return bArr2;
    }

    protected static void c(byte[] bArr, int i) {
        bArr[i] = Byte.MIN_VALUE;
        while (true) {
            i++;
            if (i < 16) {
                bArr[i] = (byte) 0;
            } else {
                return;
            }
        }
    }

    protected static int a(long j) {
        if (j == 0) {
            return 64;
        }
        int i = 0;
        while ((1 & j) == 0) {
            i++;
            j >>= 1;
        }
        return i;
    }

    protected static int a(byte[] bArr, byte[] bArr2) {
        int i = 16;
        int i2 = 0;
        while (true) {
            i--;
            if (i < 0) {
                return i2;
            }
            int i3 = bArr[i] & 255;
            bArr2[i] = (byte) (i2 | (i3 << 1));
            i2 = (i3 >>> 7) & 1;
        }
    }

    protected static void b(byte[] bArr, byte[] bArr2) {
        for (int i = 15; i >= 0; i--) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }
}
