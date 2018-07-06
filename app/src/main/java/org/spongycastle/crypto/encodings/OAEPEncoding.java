package org.spongycastle.crypto.encodings;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class OAEPEncoding implements AsymmetricBlockCipher {
    private byte[] a;
    private Digest b;
    private AsymmetricBlockCipher c;
    private SecureRandom d;
    private boolean e;

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, byte[] bArr) {
        this(asymmetricBlockCipher, digest, digest, bArr);
    }

    public OAEPEncoding(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, Digest digest2, byte[] bArr) {
        this.c = asymmetricBlockCipher;
        this.b = digest2;
        this.a = new byte[digest.b()];
        digest.c();
        if (bArr != null) {
            digest.a(bArr, 0, bArr.length);
        }
        digest.a(this.a, 0);
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            this.d = ((ParametersWithRandom) cipherParameters).a();
        } else {
            this.d = new SecureRandom();
        }
        this.c.a(z, cipherParameters);
        this.e = z;
    }

    public int a() {
        int a = this.c.a();
        if (this.e) {
            return (a - 1) - (this.a.length * 2);
        }
        return a;
    }

    public int b() {
        int b = this.c.b();
        return this.e ? b : (b - 1) - (this.a.length * 2);
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        if (this.e) {
            return b(bArr, i, i2);
        }
        return c(bArr, i, i2);
    }

    public byte[] b(byte[] bArr, int i, int i2) {
        int length;
        Object obj = new byte[((a() + 1) + (this.a.length * 2))];
        System.arraycopy(bArr, i, obj, obj.length - i2, i2);
        obj[(obj.length - i2) - 1] = (byte) 1;
        System.arraycopy(this.a, 0, obj, this.a.length, this.a.length);
        Object obj2 = new byte[this.a.length];
        this.d.nextBytes(obj2);
        byte[] a = a(obj2, 0, obj2.length, obj.length - this.a.length);
        for (length = this.a.length; length != obj.length; length++) {
            obj[length] = (byte) (obj[length] ^ a[length - this.a.length]);
        }
        System.arraycopy(obj2, 0, obj, 0, this.a.length);
        byte[] a2 = a(obj, this.a.length, obj.length - this.a.length, this.a.length);
        for (length = 0; length != this.a.length; length++) {
            obj[length] = (byte) (obj[length] ^ a2[length]);
        }
        return this.c.a(obj, 0, obj.length);
    }

    public byte[] c(byte[] bArr, int i, int i2) {
        Object obj;
        Object a = this.c.a(bArr, i, i2);
        if (a.length < this.c.b()) {
            obj = new byte[this.c.b()];
            System.arraycopy(a, 0, obj, obj.length - a.length, a.length);
        } else {
            obj = a;
        }
        if (obj.length < (this.a.length * 2) + 1) {
            throw new InvalidCipherTextException("data too short");
        }
        int i3;
        byte[] a2 = a(obj, this.a.length, obj.length - this.a.length, this.a.length);
        for (i3 = 0; i3 != this.a.length; i3++) {
            obj[i3] = (byte) (obj[i3] ^ a2[i3]);
        }
        a2 = a(obj, 0, this.a.length, obj.length - this.a.length);
        for (i3 = this.a.length; i3 != obj.length; i3++) {
            obj[i3] = (byte) (obj[i3] ^ a2[i3 - this.a.length]);
        }
        int i4 = 0;
        for (i3 = 0; i3 != this.a.length; i3++) {
            if (this.a[i3] != obj[this.a.length + i3]) {
                i4 = 1;
            }
        }
        if (i4 != 0) {
            throw new InvalidCipherTextException("data hash wrong");
        }
        i3 = this.a.length * 2;
        while (i3 != obj.length && obj[i3] == (byte) 0) {
            i3++;
        }
        if (i3 >= obj.length - 1 || obj[i3] != (byte) 1) {
            throw new InvalidCipherTextException("data start wrong " + i3);
        }
        i3++;
        Object obj2 = new byte[(obj.length - i3)];
        System.arraycopy(obj, i3, obj2, 0, obj2.length);
        return obj2;
    }

    private void a(int i, byte[] bArr) {
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 0);
    }

    private byte[] a(byte[] bArr, int i, int i2, int i3) {
        Object obj = new byte[i3];
        Object obj2 = new byte[this.b.b()];
        byte[] bArr2 = new byte[4];
        this.b.c();
        int i4 = 0;
        while (i4 < i3 / obj2.length) {
            a(i4, bArr2);
            this.b.a(bArr, i, i2);
            this.b.a(bArr2, 0, bArr2.length);
            this.b.a(obj2, 0);
            System.arraycopy(obj2, 0, obj, obj2.length * i4, obj2.length);
            i4++;
        }
        if (obj2.length * i4 < i3) {
            a(i4, bArr2);
            this.b.a(bArr, i, i2);
            this.b.a(bArr2, 0, bArr2.length);
            this.b.a(obj2, 0);
            System.arraycopy(obj2, 0, obj, obj2.length * i4, obj.length - (i4 * obj2.length));
        }
        return obj;
    }
}
