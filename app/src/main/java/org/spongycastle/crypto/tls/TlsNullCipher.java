package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;

public class TlsNullCipher implements TlsCipher {
    protected TlsContext a;
    protected TlsMac b;
    protected TlsMac c;

    public TlsNullCipher(TlsContext tlsContext, Digest digest, Digest digest2) {
        int i;
        TlsMac tlsMac = null;
        int i2 = 1;
        if (digest == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (digest2 != null) {
            i2 = 0;
        }
        if (i != i2) {
            throw new TlsFatalAlert((short) 80);
        }
        this.a = tlsContext;
        if (digest != null) {
            int b = digest.b() + digest2.b();
            byte[] a = TlsUtils.a(tlsContext, b);
            tlsMac = new TlsMac(tlsContext, digest, a, 0, digest.b());
            int b2 = 0 + digest.b();
            TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, a, b2, digest2.b());
            if (digest2.b() + b2 != b) {
                throw new TlsFatalAlert((short) 80);
            }
        }
        tlsMac2 = null;
        if (tlsContext.e()) {
            this.b = tlsMac2;
            this.c = tlsMac;
            return;
        }
        this.b = tlsMac;
        this.c = tlsMac2;
    }

    public byte[] a(long j, short s, byte[] bArr, int i, int i2) {
        if (this.b == null) {
            return Arrays.a(bArr, i, i + i2);
        }
        Object a = this.b.a(j, s, bArr, i, i2);
        byte[] bArr2 = new byte[(a.length + i2)];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        System.arraycopy(a, 0, bArr2, i2, a.length);
        return bArr2;
    }

    public byte[] b(long j, short s, byte[] bArr, int i, int i2) {
        if (this.c == null) {
            return Arrays.a(bArr, i, i + i2);
        }
        int a = this.c.a();
        if (i2 < a) {
            throw new TlsFatalAlert((short) 50);
        }
        int i3 = i2 - a;
        if (Arrays.b(Arrays.a(bArr, i + i3, i + i2), this.c.a(j, s, bArr, i, i3))) {
            return Arrays.a(bArr, i, i + i3);
        }
        throw new TlsFatalAlert((short) 20);
    }
}
