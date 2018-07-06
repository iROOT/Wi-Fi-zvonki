package org.spongycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageSigner;
import org.spongycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowSigner implements MessageSigner {
    int a;
    RainbowKeyParameters b;
    private SecureRandom c;
    private short[] d;
    private ComputeInField e = new ComputeInField();

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.b = (RainbowPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.c = parametersWithRandom.a();
            this.b = (RainbowPrivateKeyParameters) parametersWithRandom.b();
        } else {
            this.c = new SecureRandom();
            this.b = (RainbowPrivateKeyParameters) cipherParameters;
        }
        this.a = this.b.b();
    }

    private short[] a(Layer[] layerArr, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        short[] b = this.e.b(((RainbowPrivateKeyParameters) this.b).d(), this.e.a(((RainbowPrivateKeyParameters) this.b).c(), sArr));
        for (int i = 0; i < layerArr[0].a(); i++) {
            this.d[i] = (short) this.c.nextInt();
            this.d[i] = (short) (this.d[i] & 255);
        }
        return b;
    }

    public byte[] a(byte[] bArr) {
        Layer[] g = ((RainbowPrivateKeyParameters) this.b).g();
        int length = g.length;
        this.d = new short[((RainbowPrivateKeyParameters) this.b).f().length];
        byte[] bArr2 = new byte[g[length - 1].b()];
        short[] b = b(bArr);
        Object obj;
        do {
            try {
                short[] sArr;
                short[] a = a(g, b);
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    short[] sArr2 = new short[g[i].c()];
                    sArr = new short[g[i].c()];
                    int i3 = i2;
                    for (i2 = 0; i2 < g[i].c(); i2++) {
                        sArr2[i2] = a[i3];
                        i3++;
                    }
                    sArr2 = this.e.a(g[i].a(this.d), sArr2);
                    if (sArr2 == null) {
                        throw new Exception("LES is not solveable!");
                    }
                    for (i2 = 0; i2 < sArr2.length; i2++) {
                        this.d[g[i].a() + i2] = sArr2[i2];
                    }
                    i++;
                    i2 = i3;
                }
                sArr = this.e.b(((RainbowPrivateKeyParameters) this.b).f(), this.e.a(((RainbowPrivateKeyParameters) this.b).e(), this.d));
                for (i2 = 0; i2 < bArr2.length; i2++) {
                    bArr2[i2] = (byte) sArr[i2];
                }
                i2 = 1;
                continue;
            } catch (Exception e) {
                obj = null;
                continue;
            }
        } while (obj == null);
        return bArr2;
    }

    public boolean a(byte[] bArr, byte[] bArr2) {
        int i;
        short[] sArr = new short[bArr2.length];
        for (i = 0; i < bArr2.length; i++) {
            sArr[i] = (short) (((short) bArr2[i]) & 255);
        }
        short[] b = b(bArr);
        short[] a = a(sArr);
        if (b.length != a.length) {
            return false;
        }
        i = 0;
        boolean z = true;
        while (i < b.length) {
            if (z && b[i] == a[i]) {
                z = true;
            } else {
                z = false;
            }
            i++;
        }
        return z;
    }

    private short[] a(short[] sArr) {
        short[][] c = ((RainbowPublicKeyParameters) this.b).c();
        short[][] d = ((RainbowPublicKeyParameters) this.b).d();
        short[] e = ((RainbowPublicKeyParameters) this.b).e();
        short[] sArr2 = new short[c.length];
        int length = d[0].length;
        for (int i = 0; i < c.length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                int i4 = i3;
                for (i3 = i2; i3 < length; i3++) {
                    sArr2[i] = GF2Field.a(sArr2[i], GF2Field.b(c[i][i4], GF2Field.b(sArr[i2], sArr[i3])));
                    i4++;
                }
                sArr2[i] = GF2Field.a(sArr2[i], GF2Field.b(d[i][i2], sArr[i2]));
                i2++;
                i3 = i4;
            }
            sArr2[i] = GF2Field.a(sArr2[i], e[i]);
        }
        return sArr2;
    }

    private short[] b(byte[] bArr) {
        int i = 0;
        short[] sArr = new short[this.a];
        int i2 = 0;
        while (i < bArr.length) {
            sArr[i] = (short) bArr[i2];
            sArr[i] = (short) (sArr[i] & 255);
            i2++;
            i++;
            if (i >= sArr.length) {
                break;
            }
        }
        return sArr;
    }
}
