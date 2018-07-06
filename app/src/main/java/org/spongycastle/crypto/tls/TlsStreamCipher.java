package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class TlsStreamCipher implements TlsCipher {
    private static boolean f = false;
    protected TlsContext a;
    protected StreamCipher b;
    protected StreamCipher c;
    protected TlsMac d;
    protected TlsMac e;

    public TlsStreamCipher(TlsContext tlsContext, StreamCipher streamCipher, StreamCipher streamCipher2, Digest digest, Digest digest2, int i) {
        boolean e = tlsContext.e();
        this.a = tlsContext;
        this.b = streamCipher;
        this.c = streamCipher2;
        int b = ((i * 2) + digest.b()) + digest2.b();
        byte[] a = TlsUtils.a(tlsContext, b);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, a, 0, digest.b());
        int b2 = 0 + digest.b();
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, a, b2, digest2.b());
        int b3 = b2 + digest2.b();
        KeyParameter keyParameter = new KeyParameter(a, b3, i);
        int i2 = b3 + i;
        CipherParameters keyParameter2 = new KeyParameter(a, i2, i);
        if (i2 + i != b) {
            throw new TlsFatalAlert((short) 80);
        }
        CipherParameters cipherParameters;
        if (e) {
            this.d = tlsMac2;
            this.e = tlsMac;
            this.b = streamCipher2;
            this.c = streamCipher;
            cipherParameters = keyParameter;
        } else {
            this.d = tlsMac;
            this.e = tlsMac2;
            this.b = streamCipher;
            this.c = streamCipher2;
            cipherParameters = keyParameter2;
            Object keyParameter22 = keyParameter;
        }
        this.b.a(true, keyParameter22);
        this.c.a(false, cipherParameters);
    }

    public byte[] a(long j, short s, byte[] bArr, int i, int i2) {
        Object obj = new byte[(this.d.a() + i2)];
        this.b.a(bArr, i, i2, obj, 0);
        if (f) {
            Object a = this.d.a(j, s, obj, 0, i2);
            System.arraycopy(a, 0, obj, i2, a.length);
        } else {
            byte[] a2 = this.d.a(j, s, bArr, i, i2);
            this.b.a(a2, 0, a2.length, obj, i2);
        }
        return obj;
    }

    public byte[] b(long j, short s, byte[] bArr, int i, int i2) {
        int a = this.e.a();
        if (i2 < a) {
            throw new TlsFatalAlert((short) 50);
        }
        int i3 = i2 - a;
        if (f) {
            int i4 = i + i2;
            a(j, s, bArr, i4 - a, i4, bArr, i, i3);
            byte[] bArr2 = new byte[i3];
            this.c.a(bArr, i, i3, bArr2, 0);
            return bArr2;
        }
        byte[] bArr3 = new byte[i2];
        this.c.a(bArr, i, i2, bArr3, 0);
        a(j, s, bArr3, i3, i2, bArr3, 0, i3);
        return Arrays.a(bArr3, 0, i3);
    }

    private void a(long j, short s, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        if (!Arrays.b(Arrays.a(bArr, i, i2), this.e.a(j, s, bArr2, i3, i4))) {
            throw new TlsFatalAlert((short) 20);
        }
    }
}
