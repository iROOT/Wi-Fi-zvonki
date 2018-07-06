package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class TlsAEADCipher implements TlsCipher {
    protected TlsContext a;
    protected int b;
    protected int c;
    protected AEADBlockCipher d;
    protected AEADBlockCipher e;
    protected byte[] f;
    protected byte[] g;

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int i, int i2) {
        if (TlsUtils.c(tlsContext)) {
            this.a = tlsContext;
            this.b = i2;
            this.c = 8;
            int i3 = (i * 2) + 8;
            byte[] a = TlsUtils.a(tlsContext, i3);
            KeyParameter keyParameter = new KeyParameter(a, 0, i);
            int i4 = 0 + i;
            KeyParameter keyParameter2 = new KeyParameter(a, i4, i);
            i4 += i;
            byte[] a2 = Arrays.a(a, i4, i4 + 4);
            i4 += 4;
            a = Arrays.a(a, i4, i4 + 4);
            if (i4 + 4 != i3) {
                throw new TlsFatalAlert((short) 80);
            }
            if (tlsContext.e()) {
                this.d = aEADBlockCipher2;
                this.e = aEADBlockCipher;
                this.f = a;
                this.g = a2;
            } else {
                this.d = aEADBlockCipher;
                this.e = aEADBlockCipher2;
                this.f = a2;
                this.g = a;
                KeyParameter keyParameter3 = keyParameter2;
                keyParameter2 = keyParameter;
                keyParameter = keyParameter3;
            }
            byte[] bArr = new byte[(4 + this.c)];
            this.d.a(true, new AEADParameters(keyParameter2, i2 * 8, bArr));
            this.e.a(false, new AEADParameters(keyParameter, i2 * 8, bArr));
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }

    public int a(int i) {
        return (i - this.b) - this.c;
    }

    public byte[] a(long j, short s, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(this.f.length + this.c)];
        System.arraycopy(this.f, 0, bArr2, 0, this.f.length);
        TlsUtils.a(j, bArr2, this.f.length);
        byte[] bArr3 = new byte[(this.d.b(i2) + this.c)];
        System.arraycopy(bArr2, this.f.length, bArr3, 0, this.c);
        int i3 = this.c;
        try {
            this.d.a(true, new AEADParameters(null, this.b * 8, bArr2, a(j, s, i2)));
            int a = this.d.a(bArr, i, i2, bArr3, i3) + i3;
            if (a + this.d.a(bArr3, a) == bArr3.length) {
                return bArr3;
            }
            throw new TlsFatalAlert((short) 80);
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public byte[] b(long j, short s, byte[] bArr, int i, int i2) {
        if (a(i2) < 0) {
            throw new TlsFatalAlert((short) 50);
        }
        Object obj = new byte[(this.g.length + this.c)];
        System.arraycopy(this.g, 0, obj, 0, this.g.length);
        System.arraycopy(bArr, i, obj, this.g.length, this.c);
        int i3 = i + this.c;
        int i4 = i2 - this.c;
        int b = this.e.b(i4);
        byte[] bArr2 = new byte[b];
        try {
            this.e.a(false, new AEADParameters(null, this.b * 8, obj, a(j, s, b)));
            int a = this.e.a(bArr, i3, i4, bArr2, 0) + 0;
            if (a + this.e.a(bArr2, a) == bArr2.length) {
                return bArr2;
            }
            throw new TlsFatalAlert((short) 80);
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 20);
        }
    }

    protected byte[] a(long j, short s, int i) {
        byte[] bArr = new byte[13];
        TlsUtils.a(j, bArr, 0);
        TlsUtils.a(s, bArr, 8);
        TlsUtils.a(this.a.d(), bArr, 9);
        TlsUtils.a(i, bArr, 11);
        return bArr;
    }
}
