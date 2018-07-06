package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.digests.LongDigest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class TlsMac {
    protected TlsContext a;
    protected byte[] b;
    protected Mac c;
    protected int d;
    protected int e;
    protected int f;

    public TlsMac(TlsContext tlsContext, Digest digest, byte[] bArr, int i, int i2) {
        this.a = tlsContext;
        CipherParameters keyParameter = new KeyParameter(bArr, i, i2);
        this.b = Arrays.b(keyParameter.a());
        if (digest instanceof LongDigest) {
            this.d = NotificationCompat.FLAG_HIGH_PRIORITY;
            this.e = 16;
        } else {
            this.d = 64;
            this.e = 8;
        }
        if (TlsUtils.a(tlsContext)) {
            this.c = new SSL3Mac(digest);
            if (digest.b() == 20) {
                this.e = 4;
            }
        } else {
            this.c = new HMac(digest);
        }
        this.c.a(keyParameter);
        this.f = this.c.b();
        if (tlsContext.b().j) {
            this.f = Math.min(this.f, 10);
        }
    }

    public int a() {
        return this.f;
    }

    public byte[] a(long j, short s, byte[] bArr, int i, int i2) {
        ProtocolVersion d = this.a.d();
        boolean d2 = d.d();
        byte[] bArr2 = new byte[(d2 ? 11 : 13)];
        TlsUtils.a(j, bArr2, 0);
        TlsUtils.a(s, bArr2, 8);
        if (!d2) {
            TlsUtils.a(d, bArr2, 9);
        }
        TlsUtils.a(i2, bArr2, bArr2.length - 2);
        this.c.a(bArr2, 0, bArr2.length);
        this.c.a(bArr, i, i2);
        bArr2 = new byte[this.c.b()];
        this.c.a(bArr2, 0);
        return a(bArr2);
    }

    public byte[] a(long j, short s, byte[] bArr, int i, int i2, int i3, byte[] bArr2) {
        byte[] a = a(j, s, bArr, i, i2);
        int i4 = TlsUtils.a(this.a) ? 11 : 13;
        i4 = a(i4 + i3) - a(i4 + i2);
        while (true) {
            i4--;
            if (i4 >= 0) {
                this.c.a(bArr2, 0, this.d);
            } else {
                this.c.a(bArr2[0]);
                this.c.c();
                return a;
            }
        }
    }

    protected int a(int i) {
        return (this.e + i) / this.d;
    }

    protected byte[] a(byte[] bArr) {
        return bArr.length <= this.f ? bArr : Arrays.a(bArr, this.f);
    }
}
