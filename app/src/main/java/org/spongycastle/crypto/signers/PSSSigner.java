package org.spongycastle.crypto.signers;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSABlindingParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class PSSSigner implements Signer {
    private Digest a;
    private Digest b;
    private AsymmetricBlockCipher c;
    private SecureRandom d;
    private int e;
    private int f;
    private int g;
    private int h;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte l;

    public PSSSigner(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, int i, byte b) {
        this.c = asymmetricBlockCipher;
        this.a = digest;
        this.b = digest2;
        this.e = digest.b();
        this.f = digest2.b();
        this.g = i;
        this.i = new byte[i];
        this.j = new byte[((i + 8) + this.e)];
        this.l = b;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters b;
        RSAKeyParameters a;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            b = parametersWithRandom.b();
            this.d = parametersWithRandom.a();
        } else {
            if (z) {
                this.d = new SecureRandom();
            }
            b = cipherParameters;
        }
        this.c.a(z, b);
        if (b instanceof RSABlindingParameters) {
            a = ((RSABlindingParameters) b).a();
        } else {
            a = (RSAKeyParameters) b;
        }
        this.h = a.b().bitLength() - 1;
        if (this.h < ((this.e * 8) + (this.g * 8)) + 9) {
            throw new IllegalArgumentException("key too small for specified hash and salt lengths");
        }
        this.k = new byte[((this.h + 7) / 8)];
        b();
    }

    private void b(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) 0;
        }
    }

    public void a(byte b) {
        this.a.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public void b() {
        this.a.c();
    }

    public byte[] a() {
        this.a.a(this.j, (this.j.length - this.e) - this.g);
        if (this.g != 0) {
            this.d.nextBytes(this.i);
            System.arraycopy(this.i, 0, this.j, this.j.length - this.g, this.g);
        }
        Object obj = new byte[this.e];
        this.a.a(this.j, 0, this.j.length);
        this.a.a(obj, 0);
        this.k[(((this.k.length - this.g) - 1) - this.e) - 1] = (byte) 1;
        System.arraycopy(this.i, 0, this.k, ((this.k.length - this.g) - this.e) - 1, this.g);
        byte[] a = a(obj, 0, obj.length, (this.k.length - this.e) - 1);
        for (int i = 0; i != a.length; i++) {
            byte[] bArr = this.k;
            bArr[i] = (byte) (bArr[i] ^ a[i]);
        }
        byte[] bArr2 = this.k;
        bArr2[0] = (byte) (bArr2[0] & (255 >> ((this.k.length * 8) - this.h)));
        System.arraycopy(obj, 0, this.k, (this.k.length - this.e) - 1, this.e);
        this.k[this.k.length - 1] = this.l;
        bArr2 = this.c.a(this.k, 0, this.k.length);
        b(this.k);
        return bArr2;
    }

    public boolean a(byte[] bArr) {
        this.a.a(this.j, (this.j.length - this.e) - this.g);
        try {
            Object a = this.c.a(bArr, 0, bArr.length);
            System.arraycopy(a, 0, this.k, this.k.length - a.length, a.length);
            if (this.k[this.k.length - 1] != this.l) {
                b(this.k);
                return false;
            }
            int i;
            byte[] a2 = a(this.k, (this.k.length - this.e) - 1, this.e, (this.k.length - this.e) - 1);
            for (i = 0; i != a2.length; i++) {
                byte[] bArr2 = this.k;
                bArr2[i] = (byte) (bArr2[i] ^ a2[i]);
            }
            byte[] bArr3 = this.k;
            bArr3[0] = (byte) (bArr3[0] & (255 >> ((this.k.length * 8) - this.h)));
            for (i = 0; i != ((this.k.length - this.e) - this.g) - 2; i++) {
                if (this.k[i] != (byte) 0) {
                    b(this.k);
                    return false;
                }
            }
            if (this.k[((this.k.length - this.e) - this.g) - 2] != (byte) 1) {
                b(this.k);
                return false;
            }
            System.arraycopy(this.k, ((this.k.length - this.g) - this.e) - 1, this.j, this.j.length - this.g, this.g);
            this.a.a(this.j, 0, this.j.length);
            this.a.a(this.j, this.j.length - this.e);
            int length = (this.k.length - this.e) - 1;
            for (i = this.j.length - this.e; i != this.j.length; i++) {
                if ((this.k[length] ^ this.j[i]) != 0) {
                    b(this.j);
                    b(this.k);
                    return false;
                }
                length++;
            }
            b(this.j);
            b(this.k);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
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
