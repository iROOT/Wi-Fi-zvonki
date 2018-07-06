package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.Arrays;

public class GMSSPrivateKeyParameters extends GMSSKeyParameters {
    private int[] A;
    private int B;
    private Digest C;
    private int D;
    private GMSSRandom E;
    private int[] F;
    private int[] b;
    private byte[][] c;
    private byte[][] d;
    private byte[][][] e;
    private byte[][][] f;
    private Treehash[][] g;
    private Treehash[][] h;
    private Vector[] i;
    private Vector[] j;
    private Vector[][] k;
    private Vector[][] l;
    private byte[][][] m;
    private GMSSLeaf[] n;
    private GMSSLeaf[] o;
    private GMSSLeaf[] p;
    private int[] q;
    private GMSSParameters r;
    private byte[][] s;
    private GMSSRootCalc[] t;
    private byte[][] u;
    private GMSSRootSig[] v;
    private GMSSDigestProvider w;
    private boolean x;
    private int[] y;
    private int[] z;

    public GMSSPrivateKeyParameters(byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, byte[][] bArr5, byte[][] bArr6, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        this(null, bArr, bArr2, bArr3, bArr4, (byte[][][]) null, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr4, null, null, null, null, bArr5, null, bArr6, null, gMSSParameters, gMSSDigestProvider);
    }

    public GMSSPrivateKeyParameters(int[] iArr, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, byte[][][] bArr5, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] iArr2, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        int i;
        super(true, gMSSParameters);
        this.x = false;
        this.C = gMSSDigestProvider.a();
        this.D = this.C.b();
        this.r = gMSSParameters;
        this.z = gMSSParameters.c();
        this.A = gMSSParameters.d();
        this.y = gMSSParameters.b();
        this.B = this.r.a();
        if (iArr == null) {
            this.b = new int[this.B];
            for (i = 0; i < this.B; i++) {
                this.b[i] = 0;
            }
        } else {
            this.b = iArr;
        }
        this.c = bArr;
        this.d = bArr2;
        this.e = bArr3;
        this.f = bArr4;
        if (bArr5 == null) {
            this.m = new byte[this.B][][];
            for (int i2 = 0; i2 < this.B; i2++) {
                this.m[i2] = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{(int) Math.floor((double) (this.y[i2] / 2)), this.D});
            }
        } else {
            this.m = bArr5;
        }
        if (vectorArr == null) {
            this.i = new Vector[this.B];
            for (i = 0; i < this.B; i++) {
                this.i[i] = new Vector();
            }
        } else {
            this.i = vectorArr;
        }
        if (vectorArr2 == null) {
            this.j = new Vector[(this.B - 1)];
            for (i = 0; i < this.B - 1; i++) {
                this.j[i] = new Vector();
            }
        } else {
            this.j = vectorArr2;
        }
        this.g = treehashArr;
        this.h = treehashArr2;
        this.k = vectorArr3;
        this.l = vectorArr4;
        this.s = bArr6;
        this.w = gMSSDigestProvider;
        if (gMSSRootCalcArr == null) {
            this.t = new GMSSRootCalc[(this.B - 1)];
            for (i = 0; i < this.B - 1; i++) {
                this.t[i] = new GMSSRootCalc(this.y[i + 1], this.A[i + 1], this.w);
            }
        } else {
            this.t = gMSSRootCalcArr;
        }
        this.u = bArr7;
        this.F = new int[this.B];
        for (i = 0; i < this.B; i++) {
            this.F[i] = 1 << this.y[i];
        }
        this.E = new GMSSRandom(this.C);
        if (this.B <= 1) {
            this.n = new GMSSLeaf[0];
        } else if (gMSSLeafArr == null) {
            this.n = new GMSSLeaf[(this.B - 2)];
            for (i = 0; i < this.B - 2; i++) {
                this.n[i] = new GMSSLeaf(gMSSDigestProvider.a(), this.z[i + 1], this.F[i + 2], this.d[i]);
            }
        } else {
            this.n = gMSSLeafArr;
        }
        if (gMSSLeafArr2 == null) {
            this.o = new GMSSLeaf[(this.B - 1)];
            for (i = 0; i < this.B - 1; i++) {
                this.o[i] = new GMSSLeaf(gMSSDigestProvider.a(), this.z[i], this.F[i + 1], this.c[i]);
            }
        } else {
            this.o = gMSSLeafArr2;
        }
        if (gMSSLeafArr3 == null) {
            this.p = new GMSSLeaf[(this.B - 1)];
            for (i = 0; i < this.B - 1; i++) {
                this.p[i] = new GMSSLeaf(gMSSDigestProvider.a(), this.z[i], this.F[i + 1]);
            }
        } else {
            this.p = gMSSLeafArr3;
        }
        if (iArr2 == null) {
            this.q = new int[(this.B - 1)];
            for (i = 0; i < this.B - 1; i++) {
                this.q[i] = -1;
            }
        } else {
            this.q = iArr2;
        }
        Object obj = new byte[this.D];
        byte[] bArr8 = new byte[this.D];
        if (gMSSRootSigArr == null) {
            this.v = new GMSSRootSig[(this.B - 1)];
            for (i = 0; i < this.B - 1; i++) {
                System.arraycopy(bArr[i], 0, obj, 0, this.D);
                this.E.a(obj);
                byte[] a = this.E.a(obj);
                this.v[i] = new GMSSRootSig(gMSSDigestProvider.a(), this.z[i], this.y[i + 1]);
                this.v[i].a(a, bArr6[i]);
            }
            return;
        }
        this.v = gMSSRootSigArr;
    }

    public boolean c() {
        return this.x;
    }

    public void d() {
        this.x = true;
    }

    public int[] e() {
        return this.b;
    }

    public int a(int i) {
        return this.b[i];
    }

    public byte[][] f() {
        return Arrays.a(this.c);
    }

    public byte[][][] g() {
        return Arrays.a(this.e);
    }

    public byte[] b(int i) {
        return this.u[i];
    }

    public int c(int i) {
        return this.F[i];
    }
}
