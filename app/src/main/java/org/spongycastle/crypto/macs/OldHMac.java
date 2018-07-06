package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;

public class OldHMac implements Mac {
    private Digest a;
    private int b;
    private byte[] c = new byte[64];
    private byte[] d = new byte[64];

    public OldHMac(Digest digest) {
        this.a = digest;
        this.b = digest.b();
    }

    public String a() {
        return this.a.a() + "/HMAC";
    }

    public void a(CipherParameters cipherParameters) {
        int i;
        this.a.c();
        Object a = ((KeyParameter) cipherParameters).a();
        if (a.length > 64) {
            this.a.a(a, 0, a.length);
            this.a.a(this.c, 0);
            for (i = this.b; i < this.c.length; i++) {
                this.c[i] = (byte) 0;
            }
        } else {
            System.arraycopy(a, 0, this.c, 0, a.length);
            for (i = a.length; i < this.c.length; i++) {
                this.c[i] = (byte) 0;
            }
        }
        this.d = new byte[this.c.length];
        System.arraycopy(this.c, 0, this.d, 0, this.c.length);
        for (i = 0; i < this.c.length; i++) {
            byte[] bArr = this.c;
            bArr[i] = (byte) (bArr[i] ^ 54);
        }
        for (i = 0; i < this.d.length; i++) {
            bArr = this.d;
            bArr[i] = (byte) (bArr[i] ^ 92);
        }
        this.a.a(this.c, 0, this.c.length);
    }

    public int b() {
        return this.b;
    }

    public void a(byte b) {
        this.a.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.a.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.b];
        this.a.a(bArr2, 0);
        this.a.a(this.d, 0, this.d.length);
        this.a.a(bArr2, 0, bArr2.length);
        int a = this.a.a(bArr, i);
        c();
        return a;
    }

    public void c() {
        this.a.c();
        this.a.a(this.c, 0, this.c.length);
    }
}
