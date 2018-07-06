package org.spongycastle.pqc.crypto.rainbow.util;

import java.lang.reflect.Array;

public class ComputeInField {
    short[] a;
    private short[][] b;

    public short[] a(short[][] sArr, short[] sArr2) {
        try {
            if (sArr.length != sArr2.length) {
                throw new RuntimeException("The equation system is not solvable");
            }
            int i;
            this.b = (short[][]) Array.newInstance(Short.TYPE, new int[]{sArr.length, sArr.length + 1});
            this.a = new short[sArr.length];
            for (int i2 = 0; i2 < sArr.length; i2++) {
                for (i = 0; i < sArr[0].length; i++) {
                    this.b[i2][i] = sArr[i2][i];
                }
            }
            for (i = 0; i < sArr2.length; i++) {
                this.b[i][sArr2.length] = GF2Field.a(sArr2[i], this.b[i][sArr2.length]);
            }
            a(false);
            b();
            return this.a;
        } catch (RuntimeException e) {
            return null;
        }
    }

    public short[][] a(short[][] sArr) {
        try {
            this.b = (short[][]) Array.newInstance(Short.TYPE, new int[]{sArr.length, sArr.length * 2});
            if (sArr.length != sArr[0].length) {
                throw new RuntimeException("The matrix is not invertible. Please choose another one!");
            }
            int i;
            int i2;
            for (i = 0; i < sArr.length; i++) {
                for (i2 = 0; i2 < sArr.length; i2++) {
                    this.b[i][i2] = sArr[i][i2];
                }
                for (i2 = sArr.length; i2 < sArr.length * 2; i2++) {
                    this.b[i][i2] = (short) 0;
                }
                this.b[i][this.b.length + i] = (short) 1;
            }
            a(true);
            for (i = 0; i < this.b.length; i++) {
                short a = GF2Field.a(this.b[i][i]);
                for (i2 = i; i2 < this.b.length * 2; i2++) {
                    this.b[i][i2] = GF2Field.b(this.b[i][i2], a);
                }
            }
            a();
            short[][] sArr2 = (short[][]) Array.newInstance(Short.TYPE, new int[]{this.b.length, this.b.length});
            for (i = 0; i < this.b.length; i++) {
                for (int length = this.b.length; length < this.b.length * 2; length++) {
                    sArr2[i][length - this.b.length] = this.b[i][length];
                }
            }
            return sArr2;
        } catch (RuntimeException e) {
            return (short[][]) null;
        }
    }

    private void a(boolean z) {
        int length;
        if (z) {
            length = this.b.length * 2;
        } else {
            length = this.b.length + 1;
        }
        for (int i = 0; i < this.b.length - 1; i++) {
            for (int i2 = i + 1; i2 < this.b.length; i2++) {
                short s = this.b[i2][i];
                short a = GF2Field.a(this.b[i][i]);
                if (a == (short) 0) {
                    throw new RuntimeException("Matrix not invertible! We have to choose another one!");
                }
                for (int i3 = i; i3 < length; i3++) {
                    this.b[i2][i3] = GF2Field.a(this.b[i2][i3], GF2Field.b(s, GF2Field.b(this.b[i][i3], a)));
                }
            }
        }
    }

    private void a() {
        for (int length = this.b.length - 1; length > 0; length--) {
            for (int i = length - 1; i >= 0; i--) {
                short s = this.b[i][length];
                short a = GF2Field.a(this.b[length][length]);
                if (a == (short) 0) {
                    throw new RuntimeException("The matrix is not invertible");
                }
                for (int i2 = length; i2 < this.b.length * 2; i2++) {
                    this.b[i][i2] = GF2Field.a(this.b[i][i2], GF2Field.b(s, GF2Field.b(this.b[length][i2], a)));
                }
            }
        }
    }

    private void b() {
        short a = GF2Field.a(this.b[this.b.length - 1][this.b.length - 1]);
        if (a == (short) 0) {
            throw new RuntimeException("The equation system is not solvable");
        }
        this.a[this.b.length - 1] = GF2Field.b(this.b[this.b.length - 1][this.b.length], a);
        for (int length = this.b.length - 2; length >= 0; length--) {
            short s = this.b[length][this.b.length];
            for (int length2 = this.b.length - 1; length2 > length; length2--) {
                s = GF2Field.a(s, GF2Field.b(this.b[length][length2], this.a[length2]));
            }
            a = GF2Field.a(this.b[length][length]);
            if (a == (short) 0) {
                throw new RuntimeException("Not solvable equation system");
            }
            this.a[length] = GF2Field.b(s, a);
        }
    }

    public short[] b(short[][] sArr, short[] sArr2) {
        if (sArr[0].length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] sArr3 = new short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i] = GF2Field.a(sArr3[i], GF2Field.b(sArr[i][i2], sArr2[i2]));
            }
        }
        return sArr3;
    }

    public short[] a(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[] sArr3 = new short[sArr.length];
        for (int i = 0; i < sArr3.length; i++) {
            sArr3[i] = GF2Field.a(sArr[i], sArr2[i]);
        }
        return sArr3;
    }

    public short[][] b(short[] sArr, short[] sArr2) {
        if (sArr.length != sArr2.length) {
            throw new RuntimeException("Multiplication is not possible!");
        }
        short[][] sArr3 = (short[][]) Array.newInstance(Short.TYPE, new int[]{sArr.length, sArr2.length});
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr2.length; i2++) {
                sArr3[i][i2] = GF2Field.b(sArr[i], sArr2[i2]);
            }
        }
        return sArr3;
    }

    public short[] a(short s, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        for (int i = 0; i < sArr2.length; i++) {
            sArr2[i] = GF2Field.b(s, sArr[i]);
        }
        return sArr2;
    }

    public short[][] a(short s, short[][] sArr) {
        short[][] sArr2 = (short[][]) Array.newInstance(Short.TYPE, new int[]{sArr.length, sArr[0].length});
        for (int i = 0; i < sArr.length; i++) {
            for (int i2 = 0; i2 < sArr[0].length; i2++) {
                sArr2[i][i2] = GF2Field.b(s, sArr[i][i2]);
            }
        }
        return sArr2;
    }

    public short[][] a(short[][] sArr, short[][] sArr2) {
        if (sArr.length == sArr2.length && sArr[0].length == sArr2[0].length) {
            short[][] sArr3 = (short[][]) Array.newInstance(Short.TYPE, new int[]{sArr.length, sArr.length});
            for (int i = 0; i < sArr.length; i++) {
                for (int i2 = 0; i2 < sArr2.length; i2++) {
                    sArr3[i][i2] = GF2Field.a(sArr[i][i2], sArr2[i][i2]);
                }
            }
            return sArr3;
        }
        throw new RuntimeException("Addition is not possible!");
    }
}
