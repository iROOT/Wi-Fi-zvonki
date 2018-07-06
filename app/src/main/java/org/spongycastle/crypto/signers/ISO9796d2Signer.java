package org.spongycastle.crypto.signers;

import java.util.Hashtable;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.SignerWithRecovery;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public class ISO9796d2Signer implements SignerWithRecovery {
    private static Hashtable a = new Hashtable();
    private Digest b;
    private AsymmetricBlockCipher c;
    private int d;
    private int e;
    private byte[] f;
    private byte[] g;
    private int h;
    private boolean i;
    private byte[] j;
    private byte[] k;
    private byte[] l;

    static {
        a.put("RIPEMD128", Integers.a(13004));
        a.put("RIPEMD160", Integers.a(12748));
        a.put("SHA-1", Integers.a(13260));
        a.put("SHA-256", Integers.a(13516));
        a.put("SHA-384", Integers.a(14028));
        a.put("SHA-512", Integers.a(13772));
        a.put("Whirlpool", Integers.a(14284));
    }

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean z) {
        this.c = asymmetricBlockCipher;
        this.b = digest;
        if (z) {
            this.d = 188;
            return;
        }
        Integer num = (Integer) a.get(digest.a());
        if (num != null) {
            this.d = num.intValue();
            return;
        }
        throw new IllegalArgumentException("no valid trailer for digest");
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        this.c.a(z, rSAKeyParameters);
        this.e = rSAKeyParameters.b().bitLength();
        this.f = new byte[((this.e + 7) / 8)];
        if (this.d == 188) {
            this.g = new byte[((this.f.length - this.b.b()) - 2)];
        } else {
            this.g = new byte[((this.f.length - this.b.b()) - 3)];
        }
        b();
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        boolean z;
        boolean z2 = true;
        int i;
        if (this.h > this.g.length) {
            if (this.g.length > bArr2.length) {
                z2 = false;
            }
            z = z2;
            for (i = 0; i != this.g.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    z = false;
                }
            }
        } else {
            if (this.h != bArr2.length) {
                z2 = false;
            }
            z = z2;
            for (i = 0; i != bArr2.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    z = false;
                }
            }
        }
        return z;
    }

    private void b(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) 0;
        }
    }

    public void a(byte b) {
        this.b.a(b);
        if (this.h < this.g.length) {
            this.g[this.h] = b;
        }
        this.h++;
    }

    public void a(byte[] bArr, int i, int i2) {
        while (i2 > 0 && this.h < this.g.length) {
            a(bArr[i]);
            i++;
            i2--;
        }
        this.b.a(bArr, i, i2);
        this.h += i2;
    }

    public void b() {
        this.b.c();
        this.h = 0;
        b(this.g);
        if (this.j != null) {
            b(this.j);
        }
        this.j = null;
        this.i = false;
        if (this.k != null) {
            this.k = null;
            b(this.l);
            this.l = null;
        }
    }

    public byte[] a() {
        int i;
        int length;
        byte[] bArr;
        int b = this.b.b();
        if (this.d == 188) {
            i = 8;
            length = (this.f.length - b) - 1;
            this.b.a(this.f, length);
            this.f[this.f.length - 1] = (byte) -68;
        } else {
            i = 16;
            length = (this.f.length - b) - 2;
            this.b.a(this.f, length);
            this.f[this.f.length - 2] = (byte) (this.d >>> 8);
            this.f[this.f.length - 1] = (byte) this.d;
        }
        i = ((i + ((b + this.h) * 8)) + 4) - this.e;
        if (i > 0) {
            b = this.h - ((i + 7) / 8);
            i = 96;
            length -= b;
            System.arraycopy(this.g, 0, this.f, length, b);
            b = length;
        } else {
            i = 64;
            length -= this.h;
            System.arraycopy(this.g, 0, this.f, length, this.h);
            b = length;
        }
        if (b - 1 > 0) {
            for (length = b - 1; length != 0; length--) {
                this.f[length] = (byte) -69;
            }
            bArr = this.f;
            b--;
            bArr[b] = (byte) (bArr[b] ^ 1);
            this.f[0] = (byte) 11;
            bArr = this.f;
            bArr[0] = (byte) (i | bArr[0]);
        } else {
            this.f[0] = (byte) 10;
            bArr = this.f;
            bArr[0] = (byte) (i | bArr[0]);
        }
        bArr = this.c.a(this.f, 0, this.f.length);
        b(this.g);
        b(this.f);
        return bArr;
    }

    public boolean a(byte[] bArr) {
        Object a;
        if (this.k == null) {
            try {
                a = this.c.a(bArr, 0, bArr.length);
            } catch (Exception e) {
                return false;
            }
        } else if (Arrays.a(this.k, bArr)) {
            Object obj = this.l;
            this.k = null;
            this.l = null;
            a = obj;
        } else {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        }
        if (((a[0] & 192) ^ 64) != 0) {
            return c(a);
        }
        if (((a[a.length - 1] & 15) ^ 12) != 0) {
            return c(a);
        }
        int i;
        int i2;
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
        i2 = 0;
        while (i2 != a.length && ((a[i2] & 15) ^ 10) != 0) {
            i2++;
        }
        int i3 = i2 + 1;
        byte[] bArr2 = new byte[this.b.b()];
        int length = (a.length - i) - bArr2.length;
        if (length - i3 <= 0) {
            return c(a);
        }
        boolean z;
        int i4;
        if ((a[0] & 32) == 0) {
            this.i = true;
            if (this.h > length - i3) {
                return c(a);
            }
            this.b.c();
            this.b.a(a, i3, length - i3);
            this.b.a(bArr2, 0);
            z = true;
            for (i = 0; i != bArr2.length; i++) {
                i4 = length + i;
                a[i4] = (byte) (a[i4] ^ bArr2[i]);
                if (a[length + i] != (byte) 0) {
                    z = false;
                }
            }
            if (!z) {
                return c(a);
            }
            this.j = new byte[(length - i3)];
            System.arraycopy(a, i3, this.j, 0, this.j.length);
        } else {
            this.i = false;
            this.b.a(bArr2, 0);
            z = true;
            for (i = 0; i != bArr2.length; i++) {
                i4 = length + i;
                a[i4] = (byte) (a[i4] ^ bArr2[i]);
                if (a[length + i] != (byte) 0) {
                    z = false;
                }
            }
            if (!z) {
                return c(a);
            }
            this.j = new byte[(length - i3)];
            System.arraycopy(a, i3, this.j, 0, this.j.length);
        }
        if (this.h != 0 && !a(this.g, this.j)) {
            return c(a);
        }
        b(this.g);
        b(a);
        return true;
    }

    private boolean c(byte[] bArr) {
        b(this.g);
        b(bArr);
        return false;
    }
}
