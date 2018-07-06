package org.spongycastle.crypto.macs;

import android.support.v4.app.NotificationCompat;
import java.util.Hashtable;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Memoable;

public class HMac implements Mac {
    private static Hashtable h = new Hashtable();
    private Digest a;
    private int b;
    private int c;
    private Memoable d;
    private Memoable e;
    private byte[] f;
    private byte[] g;

    static {
        h.put("GOST3411", Integers.a(32));
        h.put("MD2", Integers.a(16));
        h.put("MD4", Integers.a(64));
        h.put("MD5", Integers.a(64));
        h.put("RIPEMD128", Integers.a(64));
        h.put("RIPEMD160", Integers.a(64));
        h.put("SHA-1", Integers.a(64));
        h.put("SHA-224", Integers.a(64));
        h.put("SHA-256", Integers.a(64));
        h.put("SHA-384", Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY));
        h.put("SHA-512", Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY));
        h.put("Tiger", Integers.a(64));
        h.put("Whirlpool", Integers.a(64));
    }

    private static int a(Digest digest) {
        if (digest instanceof ExtendedDigest) {
            return ((ExtendedDigest) digest).d();
        }
        Integer num = (Integer) h.get(digest.a());
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("unknown digest passed: " + digest.a());
    }

    public HMac(Digest digest) {
        this(digest, a(digest));
    }

    private HMac(Digest digest, int i) {
        this.a = digest;
        this.b = digest.b();
        this.c = i;
        this.f = new byte[this.c];
        this.g = new byte[(this.c + this.b)];
    }

    public String a() {
        return this.a.a() + "/HMAC";
    }

    public void a(CipherParameters cipherParameters) {
        this.a.c();
        Object a = ((KeyParameter) cipherParameters).a();
        int length = a.length;
        if (length > this.c) {
            this.a.a(a, 0, length);
            this.a.a(this.f, 0);
            length = this.b;
        } else {
            System.arraycopy(a, 0, this.f, 0, length);
        }
        while (length < this.f.length) {
            this.f[length] = (byte) 0;
            length++;
        }
        System.arraycopy(this.f, 0, this.g, 0, this.c);
        a(this.f, this.c, (byte) 54);
        a(this.g, this.c, (byte) 92);
        if (this.a instanceof Memoable) {
            this.e = ((Memoable) this.a).e();
            ((Digest) this.e).a(this.g, 0, this.c);
        }
        this.a.a(this.f, 0, this.f.length);
        if (this.a instanceof Memoable) {
            this.d = ((Memoable) this.a).e();
        }
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
        this.a.a(this.g, this.c);
        if (this.e != null) {
            ((Memoable) this.a).a(this.e);
            this.a.a(this.g, this.c, this.a.b());
        } else {
            this.a.a(this.g, 0, this.g.length);
        }
        int a = this.a.a(bArr, i);
        for (int i2 = this.c; i2 < this.g.length; i2++) {
            this.g[i2] = (byte) 0;
        }
        if (this.d != null) {
            ((Memoable) this.a).a(this.d);
        } else {
            this.a.a(this.f, 0, this.f.length);
        }
        return a;
    }

    public void c() {
        this.a.c();
        this.a.a(this.f, 0, this.f.length);
    }

    private static void a(byte[] bArr, int i, byte b) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ b);
        }
    }
}
