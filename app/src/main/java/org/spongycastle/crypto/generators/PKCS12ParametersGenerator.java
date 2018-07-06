package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;

public class PKCS12ParametersGenerator extends PBEParametersGenerator {
    private Digest d;
    private int e;
    private int f;

    public PKCS12ParametersGenerator(Digest digest) {
        this.d = digest;
        if (digest instanceof ExtendedDigest) {
            this.e = digest.b();
            this.f = ((ExtendedDigest) digest).d();
            return;
        }
        throw new IllegalArgumentException("Digest " + digest.a() + " unsupported");
    }

    private void a(byte[] bArr, int i, byte[] bArr2) {
        int i2 = ((bArr2[bArr2.length - 1] & 255) + (bArr[(bArr2.length + i) - 1] & 255)) + 1;
        bArr[(bArr2.length + i) - 1] = (byte) i2;
        int i3 = i2 >>> 8;
        for (i2 = bArr2.length - 2; i2 >= 0; i2--) {
            i3 += (bArr2[i2] & 255) + (bArr[i + i2] & 255);
            bArr[i + i2] = (byte) i3;
            i3 >>>= 8;
        }
    }

    private byte[] b(int i, int i2) {
        int i3;
        Object obj;
        Object obj2;
        Object obj3;
        int i4;
        byte[] bArr = new byte[this.f];
        Object obj4 = new byte[i2];
        for (i3 = 0; i3 != bArr.length; i3++) {
            bArr[i3] = (byte) i;
        }
        if (this.b == null || this.b.length == 0) {
            obj = new byte[0];
        } else {
            obj2 = new byte[(this.f * (((this.b.length + this.f) - 1) / this.f))];
            for (i3 = 0; i3 != obj2.length; i3++) {
                obj2[i3] = this.b[i3 % this.b.length];
            }
            obj = obj2;
        }
        if (this.a == null || this.a.length == 0) {
            obj2 = new byte[0];
        } else {
            obj3 = new byte[(this.f * (((this.a.length + this.f) - 1) / this.f))];
            for (i4 = 0; i4 != obj3.length; i4++) {
                obj3[i4] = this.a[i4 % this.a.length];
            }
            obj2 = obj3;
        }
        obj3 = new byte[(obj.length + obj2.length)];
        System.arraycopy(obj, 0, obj3, 0, obj.length);
        System.arraycopy(obj2, 0, obj3, obj.length, obj2.length);
        byte[] bArr2 = new byte[this.f];
        int i5 = ((this.e + i2) - 1) / this.e;
        Object obj5 = new byte[this.e];
        for (i4 = 1; i4 <= i5; i4++) {
            this.d.a(bArr, 0, bArr.length);
            this.d.a(obj3, 0, obj3.length);
            this.d.a(obj5, 0);
            for (i3 = 1; i3 < this.c; i3++) {
                this.d.a(obj5, 0, obj5.length);
                this.d.a(obj5, 0);
            }
            for (i3 = 0; i3 != bArr2.length; i3++) {
                bArr2[i3] = obj5[i3 % obj5.length];
            }
            for (i3 = 0; i3 != obj3.length / this.f; i3++) {
                a(obj3, this.f * i3, bArr2);
            }
            if (i4 == i5) {
                System.arraycopy(obj5, 0, obj4, (i4 - 1) * this.e, obj4.length - ((i4 - 1) * this.e));
            } else {
                System.arraycopy(obj5, 0, obj4, (i4 - 1) * this.e, obj5.length);
            }
        }
        return obj4;
    }

    public CipherParameters a(int i) {
        int i2 = i / 8;
        return new KeyParameter(b(1, i2), 0, i2);
    }

    public CipherParameters a(int i, int i2) {
        int i3 = i / 8;
        int i4 = i2 / 8;
        byte[] b = b(1, i3);
        return new ParametersWithIV(new KeyParameter(b, 0, i3), b(2, i4), 0, i4);
    }

    public CipherParameters b(int i) {
        int i2 = i / 8;
        return new KeyParameter(b(3, i2), 0, i2);
    }
}
