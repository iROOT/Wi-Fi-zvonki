package org.spongycastle.pqc.crypto.rainbow;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.spongycastle.pqc.crypto.rainbow.util.GF2Field;
import org.spongycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.spongycastle.util.Arrays;

public class Layer {
    private int a;
    private int b;
    private int c;
    private short[][][] d;
    private short[][][] e;
    private short[][] f;
    private short[] g;

    public Layer(byte b, byte b2, short[][][] sArr, short[][][] sArr2, short[][] sArr3, short[] sArr4) {
        this.a = b & 255;
        this.b = b2 & 255;
        this.c = this.b - this.a;
        this.d = sArr;
        this.e = sArr2;
        this.f = sArr3;
        this.g = sArr4;
    }

    public Layer(int i, int i2, SecureRandom secureRandom) {
        int i3;
        int i4 = 0;
        this.a = i;
        this.b = i2;
        this.c = i2 - i;
        this.d = (short[][][]) Array.newInstance(Short.TYPE, new int[]{this.c, this.c, this.a});
        this.e = (short[][][]) Array.newInstance(Short.TYPE, new int[]{this.c, this.a, this.a});
        this.f = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.c, this.b});
        this.g = new short[this.c];
        int i5 = this.c;
        for (i3 = 0; i3 < i5; i3++) {
            int i6;
            for (i6 = 0; i6 < this.c; i6++) {
                int i7;
                for (i7 = 0; i7 < this.a; i7++) {
                    this.d[i3][i6][i7] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (i3 = 0; i3 < i5; i3++) {
            for (i6 = 0; i6 < this.a; i6++) {
                for (i7 = 0; i7 < this.a; i7++) {
                    this.e[i3][i6][i7] = (short) (secureRandom.nextInt() & 255);
                }
            }
        }
        for (i7 = 0; i7 < i5; i7++) {
            for (i6 = 0; i6 < this.b; i6++) {
                this.f[i7][i6] = (short) (secureRandom.nextInt() & 255);
            }
        }
        while (i4 < i5) {
            this.g[i4] = (short) (secureRandom.nextInt() & 255);
            i4++;
        }
    }

    public short[][] a(short[] sArr) {
        int i;
        int i2 = 0;
        short[][] sArr2 = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.c, this.c + 1});
        short[] sArr3 = new short[this.c];
        for (i = 0; i < this.c; i++) {
            int i3;
            for (i3 = 0; i3 < this.a; i3++) {
                int i4;
                for (i4 = 0; i4 < this.a; i4++) {
                    sArr3[i] = GF2Field.a(sArr3[i], GF2Field.b(GF2Field.b(this.e[i][i3][i4], sArr[i3]), sArr[i4]));
                }
            }
        }
        for (i = 0; i < this.c; i++) {
            for (i3 = 0; i3 < this.c; i3++) {
                for (i4 = 0; i4 < this.a; i4++) {
                    sArr2[i][i3] = GF2Field.a(sArr2[i][i3], GF2Field.b(this.d[i][i3][i4], sArr[i4]));
                }
            }
        }
        for (i = 0; i < this.c; i++) {
            for (i3 = 0; i3 < this.a; i3++) {
                sArr3[i] = GF2Field.a(sArr3[i], GF2Field.b(this.f[i][i3], sArr[i3]));
            }
        }
        for (i = 0; i < this.c; i++) {
            for (i3 = this.a; i3 < this.b; i3++) {
                sArr2[i][i3 - this.a] = GF2Field.a(this.f[i][i3], sArr2[i][i3 - this.a]);
            }
        }
        for (i = 0; i < this.c; i++) {
            sArr3[i] = GF2Field.a(sArr3[i], this.g[i]);
        }
        while (i2 < this.c) {
            sArr2[i2][this.c] = sArr3[i2];
            i2++;
        }
        return sArr2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public short[][][] d() {
        return this.d;
    }

    public short[][][] e() {
        return this.e;
    }

    public short[][] f() {
        return this.f;
    }

    public short[] g() {
        return this.g;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Layer)) {
            return false;
        }
        Layer layer = (Layer) obj;
        if (this.a == layer.a() && this.b == layer.b() && this.c == layer.c() && RainbowUtil.a(this.d, layer.d()) && RainbowUtil.a(this.e, layer.e()) && RainbowUtil.a(this.f, layer.f()) && RainbowUtil.a(this.g, layer.g())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((this.a * 37) + this.b) * 37) + this.c) * 37) + Arrays.a(this.d)) * 37) + Arrays.a(this.e)) * 37) + Arrays.a(this.f)) * 37) + Arrays.a(this.g);
    }
}
