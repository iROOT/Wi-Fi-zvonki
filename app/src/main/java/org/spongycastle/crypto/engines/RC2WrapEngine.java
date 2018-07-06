package org.spongycastle.crypto.engines;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class RC2WrapEngine implements Wrapper {
    private static final byte[] i = new byte[]{(byte) 74, (byte) -35, (byte) -94, (byte) 44, (byte) 121, (byte) -24, (byte) 33, (byte) 5};
    Digest a = new SHA1Digest();
    byte[] b = new byte[20];
    private CBCBlockCipher c;
    private CipherParameters d;
    private ParametersWithIV e;
    private byte[] f;
    private boolean g;
    private SecureRandom h;

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters b;
        this.g = z;
        this.c = new CBCBlockCipher(new RC2Engine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.h = parametersWithRandom.a();
            b = parametersWithRandom.b();
        } else {
            this.h = new SecureRandom();
            b = cipherParameters;
        }
        if (b instanceof ParametersWithIV) {
            this.e = (ParametersWithIV) b;
            this.f = this.e.a();
            this.d = this.e.b();
            if (!this.g) {
                throw new IllegalArgumentException("You should not supply an IV for unwrapping");
            } else if (this.f == null || this.f.length != 8) {
                throw new IllegalArgumentException("IV is not 8 octets");
            } else {
                return;
            }
        }
        this.d = b;
        if (this.g) {
            this.f = new byte[8];
            this.h.nextBytes(this.f);
            this.e = new ParametersWithIV(this.d, this.f);
        }
    }

    public String a() {
        return "RC2";
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.g) {
            int i4 = i2 + 1;
            if (i4 % 8 != 0) {
                i4 += 8 - (i4 % 8);
            }
            Object obj = new byte[i4];
            obj[0] = (byte) i2;
            System.arraycopy(bArr, i, obj, 1, i2);
            Object obj2 = new byte[((obj.length - i2) - 1)];
            if (obj2.length > 0) {
                this.h.nextBytes(obj2);
                System.arraycopy(obj2, 0, obj, i2 + 1, obj2.length);
            }
            obj2 = a(obj);
            Object obj3 = new byte[(obj.length + obj2.length)];
            System.arraycopy(obj, 0, obj3, 0, obj.length);
            System.arraycopy(obj2, 0, obj3, obj.length, obj2.length);
            obj2 = new byte[obj3.length];
            System.arraycopy(obj3, 0, obj2, 0, obj3.length);
            int length = obj3.length / this.c.b();
            if (obj3.length % this.c.b() != 0) {
                throw new IllegalStateException("Not multiple of block length");
            }
            this.c.a(true, this.e);
            for (i4 = 0; i4 < length; i4++) {
                int b = this.c.b() * i4;
                this.c.a(obj2, b, obj2, b);
            }
            obj3 = new byte[(this.f.length + obj2.length)];
            System.arraycopy(this.f, 0, obj3, 0, this.f.length);
            System.arraycopy(obj2, 0, obj3, this.f.length, obj2.length);
            byte[] bArr2 = new byte[obj3.length];
            for (i4 = 0; i4 < obj3.length; i4++) {
                bArr2[i4] = obj3[obj3.length - (i4 + 1)];
            }
            this.c.a(true, new ParametersWithIV(this.d, i));
            while (i3 < length + 1) {
                i4 = this.c.b() * i3;
                this.c.a(bArr2, i4, bArr2, i4);
                i3++;
            }
            return bArr2;
        }
        throw new IllegalStateException("Not initialized for wrapping");
    }

    public byte[] b(byte[] bArr, int i, int i2) {
        if (this.g) {
            throw new IllegalStateException("Not set for unwrapping");
        } else if (bArr == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        } else if (i2 % this.c.b() != 0) {
            throw new InvalidCipherTextException("Ciphertext not multiple of " + this.c.b());
        } else {
            int i3;
            int b;
            this.c.a(false, new ParametersWithIV(this.d, i));
            Object obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            for (i3 = 0; i3 < obj.length / this.c.b(); i3++) {
                b = this.c.b() * i3;
                this.c.a(obj, b, obj, b);
            }
            Object obj2 = new byte[obj.length];
            for (i3 = 0; i3 < obj.length; i3++) {
                obj2[i3] = obj[obj.length - (i3 + 1)];
            }
            this.f = new byte[8];
            Object obj3 = new byte[(obj2.length - 8)];
            System.arraycopy(obj2, 0, this.f, 0, 8);
            System.arraycopy(obj2, 8, obj3, 0, obj2.length - 8);
            this.e = new ParametersWithIV(this.d, this.f);
            this.c.a(false, this.e);
            obj = new byte[obj3.length];
            System.arraycopy(obj3, 0, obj, 0, obj3.length);
            for (i3 = 0; i3 < obj.length / this.c.b(); i3++) {
                b = this.c.b() * i3;
                this.c.a(obj, b, obj, b);
            }
            byte[] bArr2 = new byte[(obj.length - 8)];
            byte[] bArr3 = new byte[8];
            System.arraycopy(obj, 0, bArr2, 0, obj.length - 8);
            System.arraycopy(obj, obj.length - 8, bArr3, 0, 8);
            if (!a(bArr2, bArr3)) {
                throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
            } else if (bArr2.length - ((bArr2[0] & 255) + 1) > 7) {
                throw new InvalidCipherTextException("too many pad bytes (" + (bArr2.length - ((bArr2[0] & 255) + 1)) + ")");
            } else {
                obj = new byte[bArr2[0]];
                System.arraycopy(bArr2, 1, obj, 0, obj.length);
                return obj;
            }
        }
    }

    private byte[] a(byte[] bArr) {
        Object obj = new byte[8];
        this.a.a(bArr, 0, bArr.length);
        this.a.a(this.b, 0);
        System.arraycopy(this.b, 0, obj, 0, 8);
        return obj;
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        return Arrays.b(a(bArr), bArr2);
    }
}
