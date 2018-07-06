package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageSigner;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.pqc.crypto.gmss.util.GMSSUtil;
import org.spongycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.spongycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.spongycastle.util.Arrays;

public class GMSSSigner implements MessageSigner {
    GMSSKeyParameters a;
    private GMSSUtil b;
    private byte[] c;
    private Digest d;
    private int e;
    private int f;
    private Digest g;
    private WinternitzOTSignature h;
    private GMSSDigestProvider i;
    private int[] j;
    private byte[][][] k;
    private byte[][] l;
    private GMSSParameters m;
    private GMSSRandom n;
    private SecureRandom o;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.a = (GMSSPublicKeyParameters) cipherParameters;
            b();
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.o = parametersWithRandom.a();
            this.a = (GMSSPrivateKeyParameters) parametersWithRandom.b();
            a();
        } else {
            this.o = new SecureRandom();
            this.a = (GMSSPrivateKeyParameters) cipherParameters;
            a();
        }
    }

    private void a() {
        this.d.c();
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = (GMSSPrivateKeyParameters) this.a;
        if (gMSSPrivateKeyParameters.c()) {
            throw new IllegalStateException("Private key already used");
        } else if (gMSSPrivateKeyParameters.a(0) >= gMSSPrivateKeyParameters.c(0)) {
            throw new IllegalStateException("No more signatures can be generated");
        } else {
            int i;
            this.m = gMSSPrivateKeyParameters.b();
            this.f = this.m.a();
            byte[] bArr = new byte[this.e];
            Object obj = new byte[this.e];
            System.arraycopy(gMSSPrivateKeyParameters.f()[this.f - 1], 0, obj, 0, this.e);
            this.h = new WinternitzOTSignature(this.n.a(obj), this.i.a(), this.m.c()[this.f - 1]);
            byte[][][] g = gMSSPrivateKeyParameters.g();
            this.k = new byte[this.f][][];
            for (int i2 = 0; i2 < this.f; i2++) {
                this.k[i2] = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{g[i2].length, this.e});
                for (i = 0; i < g[i2].length; i++) {
                    System.arraycopy(g[i2][i], 0, this.k[i2][i], 0, this.e);
                }
            }
            this.j = new int[this.f];
            System.arraycopy(gMSSPrivateKeyParameters.e(), 0, this.j, 0, this.f);
            this.l = new byte[(this.f - 1)][];
            for (i = 0; i < this.f - 1; i++) {
                obj = gMSSPrivateKeyParameters.b(i);
                this.l[i] = new byte[obj.length];
                System.arraycopy(obj, 0, this.l[i], 0, obj.length);
            }
            gMSSPrivateKeyParameters.d();
        }
    }

    public byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[this.e];
        Object a = this.h.a(bArr);
        Object a2 = this.b.a(this.k[this.f - 1]);
        Object a3 = this.b.a(this.j[this.f - 1]);
        Object obj = new byte[((a3.length + a.length) + a2.length)];
        System.arraycopy(a3, 0, obj, 0, a3.length);
        System.arraycopy(a, 0, obj, a3.length, a.length);
        System.arraycopy(a2, 0, obj, a.length + a3.length, a2.length);
        a2 = new byte[0];
        for (int i = (this.f - 1) - 1; i >= 0; i--) {
            a3 = this.b.a(this.k[i]);
            Object a4 = this.b.a(this.j[i]);
            Object obj2 = new byte[a2.length];
            System.arraycopy(a2, 0, obj2, 0, a2.length);
            a2 = new byte[(((obj2.length + a4.length) + this.l[i].length) + a3.length)];
            System.arraycopy(obj2, 0, a2, 0, obj2.length);
            System.arraycopy(a4, 0, a2, obj2.length, a4.length);
            System.arraycopy(this.l[i], 0, a2, obj2.length + a4.length, this.l[i].length);
            System.arraycopy(a3, 0, a2, (a4.length + obj2.length) + this.l[i].length, a3.length);
        }
        a = new byte[(obj.length + a2.length)];
        System.arraycopy(obj, 0, a, 0, obj.length);
        System.arraycopy(a2, 0, a, obj.length, a2.length);
        return a;
    }

    private void b() {
        this.d.c();
        GMSSPublicKeyParameters gMSSPublicKeyParameters = (GMSSPublicKeyParameters) this.a;
        this.c = gMSSPublicKeyParameters.c();
        this.m = gMSSPublicKeyParameters.b();
        this.f = this.m.a();
    }

    public boolean a(byte[] bArr, byte[] bArr2) {
        this.g.c();
        int i = this.f - 1;
        int i2 = 0;
        while (i >= 0) {
            WinternitzOTSVerify winternitzOTSVerify = new WinternitzOTSVerify(this.i.a(), this.m.c()[i]);
            int a = winternitzOTSVerify.a();
            int a2 = this.b.a(bArr2, i2);
            i2 += 4;
            Object obj = new byte[a];
            System.arraycopy(bArr2, i2, obj, 0, a);
            a += i2;
            obj = winternitzOTSVerify.a(bArr, obj);
            if (obj == null) {
                System.err.println("OTS Public Key is null in GMSSSignature.verify");
                return false;
            }
            byte[][] bArr3 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.m.b()[i], this.e});
            i2 = a;
            for (Object arraycopy : bArr3) {
                Object arraycopy2;
                System.arraycopy(bArr2, i2, arraycopy2, 0, this.e);
                i2 += this.e;
            }
            byte[] bArr4 = new byte[this.e];
            a = (1 << bArr3.length) + a2;
            Object obj2 = obj;
            int i3 = a;
            for (a = 0; a < bArr3.length; a++) {
                arraycopy2 = new byte[(this.e << 1)];
                if (i3 % 2 == 0) {
                    System.arraycopy(obj2, 0, arraycopy2, 0, this.e);
                    System.arraycopy(bArr3[a], 0, arraycopy2, this.e, this.e);
                    i3 /= 2;
                } else {
                    System.arraycopy(bArr3[a], 0, arraycopy2, 0, this.e);
                    System.arraycopy(obj2, 0, arraycopy2, this.e, obj2.length);
                    i3 = (i3 - 1) / 2;
                }
                this.d.a(arraycopy2, 0, arraycopy2.length);
                obj2 = new byte[this.d.b()];
                this.d.a(obj2, 0);
            }
            i--;
            Object bArr5 = obj2;
        }
        if (Arrays.a(this.c, bArr5)) {
            return true;
        }
        return false;
    }
}
