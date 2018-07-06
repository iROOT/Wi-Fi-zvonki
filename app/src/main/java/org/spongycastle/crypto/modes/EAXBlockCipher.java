package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.macs.CMac;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class EAXBlockCipher implements AEADBlockCipher {
    private SICBlockCipher a;
    private boolean b;
    private int c;
    private Mac d;
    private byte[] e = new byte[this.d.b()];
    private byte[] f = new byte[this.d.b()];
    private byte[] g = new byte[this.c];
    private int h;
    private byte[] i = new byte[(this.c * 2)];
    private int j;
    private boolean k;
    private byte[] l;

    public EAXBlockCipher(BlockCipher blockCipher) {
        this.c = blockCipher.b();
        this.d = new CMac(blockCipher);
        this.a = new SICBlockCipher(blockCipher);
    }

    public BlockCipher a() {
        return this.a.d();
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        byte[] d;
        CipherParameters a;
        this.b = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            d = aEADParameters.d();
            this.l = aEADParameters.c();
            this.h = aEADParameters.b() / 8;
            a = aEADParameters.a();
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            d = parametersWithIV.a();
            this.l = null;
            this.h = this.d.b() / 2;
            a = parametersWithIV.b();
        } else {
            throw new IllegalArgumentException("invalid parameters passed to EAX");
        }
        byte[] bArr = new byte[this.c];
        this.d.a(a);
        bArr[this.c - 1] = (byte) 0;
        this.d.a(bArr, 0, this.c);
        this.d.a(d, 0, d.length);
        this.d.a(this.e, 0);
        this.a.a(true, new ParametersWithIV(null, this.e));
        b();
    }

    private void c() {
        if (!this.k) {
            this.k = true;
            this.d.a(this.f, 0);
            byte[] bArr = new byte[this.c];
            bArr[this.c - 1] = (byte) 2;
            this.d.a(bArr, 0, this.c);
        }
    }

    private void d() {
        int i = 0;
        byte[] bArr = new byte[this.c];
        this.d.a(bArr, 0);
        while (i < this.g.length) {
            this.g[i] = (byte) ((this.e[i] ^ this.f[i]) ^ bArr[i]);
            i++;
        }
    }

    public void b() {
        a(true);
    }

    private void a(boolean z) {
        this.a.c();
        this.d.c();
        this.j = 0;
        Arrays.a(this.i, (byte) 0);
        if (z) {
            Arrays.a(this.g, (byte) 0);
        }
        byte[] bArr = new byte[this.c];
        bArr[this.c - 1] = (byte) 1;
        this.d.a(bArr, 0, this.c);
        this.k = false;
        if (this.l != null) {
            a(this.l, 0, this.l.length);
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.k) {
            throw new IllegalStateException("AAD data cannot be added after encryption/decryption processing has begun.");
        }
        this.d.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        c();
        int i5 = 0;
        while (i4 != i2) {
            i5 += a(bArr[i + i4], bArr2, i3 + i5);
            i4++;
        }
        return i5;
    }

    public int a(byte[] bArr, int i) {
        c();
        int i2 = this.j;
        Object obj = new byte[this.i.length];
        this.j = 0;
        if (this.b) {
            if (bArr.length < i + i2) {
                throw new DataLengthException("Output buffer too short");
            }
            this.a.a(this.i, 0, obj, 0);
            this.a.a(this.i, this.c, obj, this.c);
            System.arraycopy(obj, 0, bArr, i, i2);
            this.d.a(obj, 0, i2);
            d();
            System.arraycopy(this.g, 0, bArr, i + i2, this.h);
            a(false);
            return i2 + this.h;
        } else if (i2 < this.h) {
            throw new InvalidCipherTextException("data too short");
        } else {
            if (i2 > this.h) {
                this.d.a(this.i, 0, i2 - this.h);
                this.a.a(this.i, 0, obj, 0);
                this.a.a(this.i, this.c, obj, this.c);
                System.arraycopy(obj, 0, bArr, i, i2 - this.h);
            }
            d();
            if (b(this.i, i2 - this.h)) {
                a(false);
                return i2 - this.h;
            }
            throw new InvalidCipherTextException("mac check in EAX failed");
        }
    }

    public int a(int i) {
        int i2 = this.j + i;
        if (!this.b) {
            if (i2 < this.h) {
                return 0;
            }
            i2 -= this.h;
        }
        return i2 - (i2 % this.c);
    }

    public int b(int i) {
        int i2 = this.j + i;
        if (this.b) {
            return i2 + this.h;
        }
        return i2 < this.h ? 0 : i2 - this.h;
    }

    private int a(byte b, byte[] bArr, int i) {
        byte[] bArr2 = this.i;
        int i2 = this.j;
        this.j = i2 + 1;
        bArr2[i2] = b;
        if (this.j != this.i.length) {
            return 0;
        }
        int a;
        if (this.b) {
            a = this.a.a(this.i, 0, bArr, i);
            this.d.a(bArr, i, this.c);
        } else {
            this.d.a(this.i, 0, this.c);
            a = this.a.a(this.i, 0, bArr, i);
        }
        this.j = this.c;
        System.arraycopy(this.i, this.c, this.i, 0, this.c);
        return a;
    }

    private boolean b(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.h; i3++) {
            i2 |= this.g[i3] ^ bArr[i + i3];
        }
        if (i2 == 0) {
            return true;
        }
        return false;
    }
}
