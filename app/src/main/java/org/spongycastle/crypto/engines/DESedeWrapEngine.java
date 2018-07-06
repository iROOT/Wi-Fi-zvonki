package org.spongycastle.crypto.engines;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.modes.CBCBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class DESedeWrapEngine implements Wrapper {
    private static final byte[] h = new byte[]{(byte) 74, (byte) -35, (byte) -94, (byte) 44, (byte) 121, (byte) -24, (byte) 33, (byte) 5};
    Digest a = new SHA1Digest();
    byte[] b = new byte[20];
    private CBCBlockCipher c;
    private KeyParameter d;
    private ParametersWithIV e;
    private byte[] f;
    private boolean g;

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters cipherParameters2;
        SecureRandom a;
        this.g = z;
        this.c = new CBCBlockCipher(new DESedeEngine());
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            CipherParameters b = parametersWithRandom.b();
            cipherParameters2 = b;
            a = parametersWithRandom.a();
        } else {
            a = new SecureRandom();
            cipherParameters2 = cipherParameters;
        }
        if (cipherParameters2 instanceof KeyParameter) {
            this.d = (KeyParameter) cipherParameters2;
            if (this.g) {
                this.f = new byte[8];
                a.nextBytes(this.f);
                this.e = new ParametersWithIV(this.d, this.f);
            }
        } else if (cipherParameters2 instanceof ParametersWithIV) {
            this.e = (ParametersWithIV) cipherParameters2;
            this.f = this.e.a();
            this.d = (KeyParameter) this.e.b();
            if (!this.g) {
                throw new IllegalArgumentException("You should not supply an IV for unwrapping");
            } else if (this.f == null || this.f.length != 8) {
                throw new IllegalArgumentException("IV is not 8 octets");
            }
        }
    }

    public String a() {
        return "DESede";
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.g) {
            Object obj = new byte[i2];
            System.arraycopy(bArr, i, obj, 0, i2);
            Object a = a(obj);
            Object obj2 = new byte[(obj.length + a.length)];
            System.arraycopy(obj, 0, obj2, 0, obj.length);
            System.arraycopy(a, 0, obj2, obj.length, a.length);
            int b = this.c.b();
            if (obj2.length % b != 0) {
                throw new IllegalStateException("Not multiple of block length");
            }
            this.c.a(true, this.e);
            Object obj3 = new byte[obj2.length];
            for (int i4 = 0; i4 != obj2.length; i4 += b) {
                this.c.a(obj2, i4, obj3, i4);
            }
            obj = new byte[(this.f.length + obj3.length)];
            System.arraycopy(this.f, 0, obj, 0, this.f.length);
            System.arraycopy(obj3, 0, obj, this.f.length, obj3.length);
            byte[] b2 = b(obj);
            this.c.a(true, new ParametersWithIV(this.d, h));
            while (i3 != b2.length) {
                this.c.a(b2, i3, b2, i3);
                i3 += b;
            }
            return b2;
        }
        throw new IllegalStateException("Not initialized for wrapping");
    }

    public byte[] b(byte[] bArr, int i, int i2) {
        if (this.g) {
            throw new IllegalStateException("Not set for unwrapping");
        } else if (bArr == null) {
            throw new InvalidCipherTextException("Null pointer as ciphertext");
        } else {
            int b = this.c.b();
            if (i2 % b != 0) {
                throw new InvalidCipherTextException("Ciphertext not multiple of " + b);
            }
            int i3;
            this.c.a(false, new ParametersWithIV(this.d, h));
            byte[] bArr2 = new byte[i2];
            for (i3 = 0; i3 != i2; i3 += b) {
                this.c.a(bArr, i + i3, bArr2, i3);
            }
            Object b2 = b(bArr2);
            this.f = new byte[8];
            Object obj = new byte[(b2.length - 8)];
            System.arraycopy(b2, 0, this.f, 0, 8);
            System.arraycopy(b2, 8, obj, 0, b2.length - 8);
            this.e = new ParametersWithIV(this.d, this.f);
            this.c.a(false, this.e);
            Object obj2 = new byte[obj.length];
            for (i3 = 0; i3 != obj2.length; i3 += b) {
                this.c.a(obj, i3, obj2, i3);
            }
            byte[] bArr3 = new byte[(obj2.length - 8)];
            byte[] bArr4 = new byte[8];
            System.arraycopy(obj2, 0, bArr3, 0, obj2.length - 8);
            System.arraycopy(obj2, obj2.length - 8, bArr4, 0, 8);
            if (a(bArr3, bArr4)) {
                return bArr3;
            }
            throw new InvalidCipherTextException("Checksum inside ciphertext is corrupted");
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

    private static byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[bArr.length - (i + 1)];
        }
        return bArr2;
    }
}
