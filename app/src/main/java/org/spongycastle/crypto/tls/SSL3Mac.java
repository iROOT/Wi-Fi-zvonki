package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class SSL3Mac implements Mac {
    static final byte[] a = a((byte) 54, 48);
    static final byte[] b = a((byte) 92, 48);
    private Digest c;
    private byte[] d;
    private int e;

    public SSL3Mac(Digest digest) {
        this.c = digest;
        if (digest.b() == 20) {
            this.e = 40;
        } else {
            this.e = 48;
        }
    }

    public String a() {
        return this.c.a() + "/SSL3MAC";
    }

    public void a(CipherParameters cipherParameters) {
        this.d = Arrays.b(((KeyParameter) cipherParameters).a());
        c();
    }

    public int b() {
        return this.c.b();
    }

    public void a(byte b) {
        this.c.a(b);
    }

    public void a(byte[] bArr, int i, int i2) {
        this.c.a(bArr, i, i2);
    }

    public int a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.c.b()];
        this.c.a(bArr2, 0);
        this.c.a(this.d, 0, this.d.length);
        this.c.a(b, 0, this.e);
        this.c.a(bArr2, 0, bArr2.length);
        int a = this.c.a(bArr, i);
        c();
        return a;
    }

    public void c() {
        this.c.c();
        this.c.a(this.d, 0, this.d.length);
        this.c.a(a, 0, this.e);
    }

    private static byte[] a(byte b, int i) {
        byte[] bArr = new byte[i];
        Arrays.a(bArr, b);
        return bArr;
    }
}
