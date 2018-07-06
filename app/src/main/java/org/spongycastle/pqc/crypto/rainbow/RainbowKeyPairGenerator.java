package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private boolean a = false;
    private SecureRandom b;
    private RainbowKeyGenerationParameters c;
    private short[][] d;
    private short[][] e;
    private short[] f;
    private short[][] g;
    private short[][] h;
    private short[] i;
    private int j;
    private Layer[] k;
    private int[] l;
    private short[][] m;
    private short[][] n;
    private short[] o;

    public AsymmetricCipherKeyPair b() {
        if (!this.a) {
            c();
        }
        d();
        return new AsymmetricCipherKeyPair(new RainbowPublicKeyParameters(this.l[this.l.length - 1] - this.l[0], this.m, this.n, this.o), new RainbowPrivateKeyParameters(this.e, this.f, this.h, this.i, this.l, this.k));
    }

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.c = (RainbowKeyGenerationParameters) keyGenerationParameters;
        this.b = new SecureRandom();
        this.l = this.c.c().b();
        this.j = this.c.c().a();
        this.a = true;
    }

    private void c() {
        a(new RainbowKeyGenerationParameters(new SecureRandom(), new RainbowParameters()));
    }

    private void d() {
        e();
        f();
        g();
        h();
    }

    private void e() {
        int i = this.l[this.l.length - 1] - this.l[0];
        this.d = (short[][]) Array.newInstance(Short.TYPE, new int[]{i, i});
        this.e = (short[][]) null;
        ComputeInField computeInField = new ComputeInField();
        while (this.e == null) {
            for (int i2 = 0; i2 < i; i2++) {
                int i3;
                for (i3 = 0; i3 < i; i3++) {
                    this.d[i2][i3] = (short) (this.b.nextInt() & 255);
                }
            }
            this.e = computeInField.a(this.d);
        }
        this.f = new short[i];
        for (i3 = 0; i3 < i; i3++) {
            this.f[i3] = (short) (this.b.nextInt() & 255);
        }
    }

    private void f() {
        int i = this.l[this.l.length - 1];
        this.g = (short[][]) Array.newInstance(Short.TYPE, new int[]{i, i});
        this.h = (short[][]) null;
        ComputeInField computeInField = new ComputeInField();
        while (this.h == null) {
            for (int i2 = 0; i2 < i; i2++) {
                int i3;
                for (i3 = 0; i3 < i; i3++) {
                    this.g[i2][i3] = (short) (this.b.nextInt() & 255);
                }
            }
            this.h = computeInField.a(this.g);
        }
        this.i = new short[i];
        for (i3 = 0; i3 < i; i3++) {
            this.i[i3] = (short) (this.b.nextInt() & 255);
        }
    }

    private void g() {
        this.k = new Layer[this.j];
        for (int i = 0; i < this.j; i++) {
            this.k[i] = new Layer(this.l[i], this.l[i + 1], this.b);
        }
    }

    private void h() {
        ComputeInField computeInField = new ComputeInField();
        int i = this.l[this.l.length - 1] - this.l[0];
        int i2 = this.l[this.l.length - 1];
        short[][][] sArr = (short[][][]) Array.newInstance(Short.TYPE, new int[]{i, i2, i2});
        this.n = (short[][]) Array.newInstance(Short.TYPE, new int[]{i, i2});
        this.o = new short[i];
        int i3 = 0;
        short[] sArr2 = new short[i2];
        for (int i4 = 0; i4 < this.k.length; i4++) {
            short[][][] d = this.k[i4].d();
            short[][][] e = this.k[i4].e();
            short[][] f = this.k[i4].f();
            short[] g = this.k[i4].g();
            int length = d[0].length;
            int length2 = e[0].length;
            for (int i5 = 0; i5 < length; i5++) {
                int i6;
                for (i6 = 0; i6 < length; i6++) {
                    int i7;
                    for (i7 = 0; i7 < length2; i7++) {
                        short[] a = computeInField.a(d[i5][i6][i7], this.g[i6 + length2]);
                        sArr[i3 + i5] = computeInField.a(sArr[i3 + i5], computeInField.b(a, this.g[i7]));
                        this.n[i3 + i5] = computeInField.a(computeInField.a(this.i[i7], a), this.n[i3 + i5]);
                        this.n[i3 + i5] = computeInField.a(computeInField.a(this.i[i6 + length2], computeInField.a(d[i5][i6][i7], this.g[i7])), this.n[i3 + i5]);
                        short b = GF2Field.b(d[i5][i6][i7], this.i[i6 + length2]);
                        this.o[i3 + i5] = GF2Field.a(this.o[i3 + i5], GF2Field.b(b, this.i[i7]));
                    }
                }
                for (i6 = 0; i6 < length2; i6++) {
                    for (i7 = 0; i7 < length2; i7++) {
                        a = computeInField.a(e[i5][i6][i7], this.g[i6]);
                        sArr[i3 + i5] = computeInField.a(sArr[i3 + i5], computeInField.b(a, this.g[i7]));
                        this.n[i3 + i5] = computeInField.a(computeInField.a(this.i[i7], a), this.n[i3 + i5]);
                        this.n[i3 + i5] = computeInField.a(computeInField.a(this.i[i6], computeInField.a(e[i5][i6][i7], this.g[i7])), this.n[i3 + i5]);
                        b = GF2Field.b(e[i5][i6][i7], this.i[i6]);
                        this.o[i3 + i5] = GF2Field.a(this.o[i3 + i5], GF2Field.b(b, this.i[i7]));
                    }
                }
                for (i7 = 0; i7 < length2 + length; i7++) {
                    this.n[i3 + i5] = computeInField.a(computeInField.a(f[i5][i7], this.g[i7]), this.n[i3 + i5]);
                    this.o[i3 + i5] = GF2Field.a(this.o[i3 + i5], GF2Field.b(f[i5][i7], this.i[i7]));
                }
                this.o[i3 + i5] = GF2Field.a(this.o[i3 + i5], g[i5]);
            }
            i3 += length;
        }
        short[][][] sArr3 = (short[][][]) Array.newInstance(Short.TYPE, new int[]{i, i2, i2});
        short[][] sArr4 = (short[][]) Array.newInstance(Short.TYPE, new int[]{i, i2});
        short[] sArr5 = new short[i];
        for (i6 = 0; i6 < i; i6++) {
            for (i7 = 0; i7 < this.d.length; i7++) {
                sArr3[i6] = computeInField.a(sArr3[i6], computeInField.a(this.d[i6][i7], sArr[i7]));
                sArr4[i6] = computeInField.a(sArr4[i6], computeInField.a(this.d[i6][i7], this.n[i7]));
                sArr5[i6] = GF2Field.a(sArr5[i6], GF2Field.b(this.d[i6][i7], this.o[i7]));
            }
            sArr5[i6] = GF2Field.a(sArr5[i6], this.f[i6]);
        }
        this.n = sArr4;
        this.o = sArr5;
        a(sArr3);
    }

    private void a(short[][][] sArr) {
        int length = sArr.length;
        int length2 = sArr[0].length;
        this.m = (short[][]) Array.newInstance(Short.TYPE, new int[]{length, ((length2 + 1) * length2) / 2});
        for (int i = 0; i < length; i++) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < length2) {
                int i4 = i3;
                for (i3 = i2; i3 < length2; i3++) {
                    if (i3 == i2) {
                        this.m[i][i4] = sArr[i][i2][i3];
                    } else {
                        this.m[i][i4] = GF2Field.a(sArr[i][i2][i3], sArr[i][i3][i2]);
                    }
                    i4++;
                }
                i2++;
                i3 = i4;
            }
        }
    }

    public void b(KeyGenerationParameters keyGenerationParameters) {
        a(keyGenerationParameters);
    }

    public AsymmetricCipherKeyPair a() {
        return b();
    }
}
