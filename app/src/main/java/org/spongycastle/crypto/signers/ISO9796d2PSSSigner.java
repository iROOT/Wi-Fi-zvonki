package org.spongycastle.crypto.signers;

import java.security.SecureRandom;
import java.util.Hashtable;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.SignerWithRecovery;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSalt;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public class ISO9796d2PSSSigner implements SignerWithRecovery {
    private static Hashtable a = new Hashtable();
    private Digest b;
    private AsymmetricBlockCipher c;
    private SecureRandom d;
    private byte[] e;
    private int f;
    private int g;
    private int h;
    private byte[] i;
    private byte[] j;
    private int k;
    private int l;
    private boolean m;
    private byte[] n;
    private byte[] o;
    private byte[] p;
    private int q;
    private int r;

    static {
        a.put("RIPEMD128", Integers.a(13004));
        a.put("RIPEMD160", Integers.a(12748));
        a.put("SHA-1", Integers.a(13260));
        a.put("SHA-256", Integers.a(13516));
        a.put("SHA-384", Integers.a(14028));
        a.put("SHA-512", Integers.a(13772));
        a.put("Whirlpool", Integers.a(14284));
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        Object obj;
        int i;
        int i2 = this.l;
        RSAKeyParameters rSAKeyParameters;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            rSAKeyParameters = (RSAKeyParameters) parametersWithRandom.b();
            if (z) {
                this.d = parametersWithRandom.a();
            }
            obj = rSAKeyParameters;
            i = i2;
        } else if (cipherParameters instanceof ParametersWithSalt) {
            ParametersWithSalt parametersWithSalt = (ParametersWithSalt) cipherParameters;
            rSAKeyParameters = (RSAKeyParameters) parametersWithSalt.b();
            this.e = parametersWithSalt.a();
            i2 = this.e.length;
            if (this.e.length != this.l) {
                throw new IllegalArgumentException("Fixed salt is of wrong length");
            }
            obj = rSAKeyParameters;
            i = i2;
        } else {
            obj = (RSAKeyParameters) cipherParameters;
            if (z) {
                this.d = new SecureRandom();
            }
            i = i2;
        }
        this.c.a(z, obj);
        this.h = obj.b().bitLength();
        this.i = new byte[((this.h + 7) / 8)];
        if (this.g == 188) {
            this.j = new byte[((((this.i.length - this.b.b()) - i) - 1) - 1)];
        } else {
            this.j = new byte[((((this.i.length - this.b.b()) - i) - 1) - 2)];
        }
        b();
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        boolean z = true;
        if (this.k != bArr2.length) {
            z = false;
        }
        boolean z2 = z;
        for (int i = 0; i != bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                z2 = false;
            }
        }
        return z2;
    }

    private void c(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) 0;
        }
    }

    public void b(byte[] bArr) {
        int i;
        int i2;
        boolean z = true;
        Object a = this.c.a(bArr, 0, bArr.length);
        if (a.length < (this.h + 7) / 8) {
            Object obj = new byte[((this.h + 7) / 8)];
            System.arraycopy(a, 0, obj, obj.length - a.length, a.length);
            c(a);
            a = obj;
        }
        if (((a[a.length - 1] & 255) ^ 188) == 0) {
            i = 1;
        } else {
            i2 = (a[a.length - 1] & 255) | ((a[a.length - 2] & 255) << 8);
            Integer num = (Integer) a.get(this.b.a());
            if (num == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            } else if (i2 != num.intValue()) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
            } else {
                i = 2;
            }
        }
        this.b.a(new byte[this.f], 0);
        byte[] a2 = a(a, (a.length - this.f) - i, this.f, (a.length - this.f) - i);
        for (i2 = 0; i2 != a2.length; i2++) {
            a[i2] = (byte) (a[i2] ^ a2[i2]);
        }
        a[0] = (byte) (a[0] & 127);
        i2 = 0;
        while (i2 != a.length && a[i2] != (byte) 1) {
            i2++;
        }
        i2++;
        if (i2 >= a.length) {
            c(a);
        }
        if (i2 <= 1) {
            z = false;
        }
        this.m = z;
        this.n = new byte[((a2.length - i2) - this.l)];
        System.arraycopy(a, i2, this.n, 0, this.n.length);
        System.arraycopy(this.n, 0, this.j, 0, this.n.length);
        this.o = bArr;
        this.p = a;
        this.q = i2;
        this.r = i;
    }

    public void a(byte b) {
        if (this.o != null || this.k >= this.j.length) {
            this.b.a(b);
            return;
        }
        byte[] bArr = this.j;
        int i = this.k;
        this.k = i + 1;
        bArr[i] = b;
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.o == null) {
            while (i2 > 0 && this.k < this.j.length) {
                a(bArr[i]);
                i++;
                i2--;
            }
        }
        if (i2 > 0) {
            this.b.a(bArr, i, i2);
        }
    }

    public void b() {
        this.b.c();
        this.k = 0;
        if (this.j != null) {
            c(this.j);
        }
        if (this.n != null) {
            c(this.n);
            this.n = null;
        }
        this.m = false;
        if (this.o != null) {
            this.o = null;
            c(this.p);
            this.p = null;
        }
    }

    public byte[] a() {
        Object obj;
        byte[] bArr = new byte[this.b.b()];
        this.b.a(bArr, 0);
        byte[] bArr2 = new byte[8];
        a((long) (this.k * 8), bArr2);
        this.b.a(bArr2, 0, bArr2.length);
        this.b.a(this.j, 0, this.k);
        this.b.a(bArr, 0, bArr.length);
        if (this.e != null) {
            obj = this.e;
        } else {
            obj = new byte[this.l];
            this.d.nextBytes(obj);
        }
        this.b.a(obj, 0, obj.length);
        Object obj2 = new byte[this.b.b()];
        this.b.a(obj2, 0);
        int i = 2;
        if (this.g == 188) {
            i = 1;
        }
        int length = ((((this.i.length - this.k) - obj.length) - this.f) - i) - 1;
        this.i[length] = (byte) 1;
        System.arraycopy(this.j, 0, this.i, length + 1, this.k);
        System.arraycopy(obj, 0, this.i, (length + 1) + this.k, obj.length);
        byte[] a = a(obj2, 0, obj2.length, (this.i.length - this.f) - i);
        for (int i2 = 0; i2 != a.length; i2++) {
            byte[] bArr3 = this.i;
            bArr3[i2] = (byte) (bArr3[i2] ^ a[i2]);
        }
        System.arraycopy(obj2, 0, this.i, (this.i.length - this.f) - i, this.f);
        if (this.g == 188) {
            this.i[this.i.length - 1] = (byte) -68;
        } else {
            this.i[this.i.length - 2] = (byte) (this.g >>> 8);
            this.i[this.i.length - 1] = (byte) this.g;
        }
        bArr = this.i;
        bArr[0] = (byte) (bArr[0] & 127);
        bArr = this.c.a(this.i, 0, this.i.length);
        c(this.j);
        c(this.i);
        this.k = 0;
        return bArr;
    }

    public boolean a(byte[] bArr) {
        byte[] bArr2 = new byte[this.f];
        this.b.a(bArr2, 0);
        if (this.o == null) {
            try {
                b(bArr);
            } catch (Exception e) {
                return false;
            }
        } else if (!Arrays.a(this.o, bArr)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        }
        byte[] bArr3 = this.p;
        int i = this.q;
        int i2 = this.r;
        this.o = null;
        this.p = null;
        byte[] bArr4 = new byte[8];
        a((long) (this.n.length * 8), bArr4);
        this.b.a(bArr4, 0, bArr4.length);
        if (this.n.length != 0) {
            this.b.a(this.n, 0, this.n.length);
        }
        this.b.a(bArr2, 0, bArr2.length);
        this.b.a(bArr3, i + this.n.length, this.l);
        bArr4 = new byte[this.b.b()];
        this.b.a(bArr4, 0);
        i2 = (bArr3.length - i2) - bArr4.length;
        boolean z = true;
        for (int i3 = 0; i3 != bArr4.length; i3++) {
            if (bArr4[i3] != bArr3[i2 + i3]) {
                z = false;
            }
        }
        c(bArr3);
        c(bArr4);
        if (z) {
            if (this.k != 0) {
                if (a(this.j, this.n)) {
                    this.k = 0;
                } else {
                    c(this.j);
                    return false;
                }
            }
            c(this.j);
            return true;
        }
        this.m = false;
        c(this.n);
        return false;
    }

    private void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    private void a(long j, byte[] bArr) {
        bArr[0] = (byte) ((int) (j >>> 56));
        bArr[1] = (byte) ((int) (j >>> 48));
        bArr[2] = (byte) ((int) (j >>> 40));
        bArr[3] = (byte) ((int) (j >>> 32));
        bArr[4] = (byte) ((int) (j >>> 24));
        bArr[5] = (byte) ((int) (j >>> 16));
        bArr[6] = (byte) ((int) (j >>> 8));
        bArr[7] = (byte) ((int) (j >>> 0));
    }

    private byte[] a(byte[] bArr, int i, int i2, int i3) {
        Object obj = new byte[i3];
        Object obj2 = new byte[this.f];
        byte[] bArr2 = new byte[4];
        this.b.c();
        int i4 = 0;
        while (i4 < i3 / this.f) {
            a(i4, bArr2);
            this.b.a(bArr, i, i2);
            this.b.a(bArr2, 0, bArr2.length);
            this.b.a(obj2, 0);
            System.arraycopy(obj2, 0, obj, this.f * i4, this.f);
            i4++;
        }
        if (this.f * i4 < i3) {
            a(i4, bArr2);
            this.b.a(bArr, i, i2);
            this.b.a(bArr2, 0, bArr2.length);
            this.b.a(obj2, 0);
            System.arraycopy(obj2, 0, obj, this.f * i4, obj.length - (i4 * this.f));
        }
        return obj;
    }
}
