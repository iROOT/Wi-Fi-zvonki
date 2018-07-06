package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.encoders.Hex;

public class GMSSRootCalc {
    private int a;
    private int b = this.j.b();
    private Treehash[] c;
    private Vector[] d;
    private byte[] e;
    private byte[][] f;
    private int g;
    private Vector h;
    private Vector i;
    private Digest j;
    private GMSSDigestProvider k;
    private int[] l;
    private boolean m;
    private boolean n;
    private int o;
    private int p;

    public GMSSRootCalc(int i, int i2, GMSSDigestProvider gMSSDigestProvider) {
        this.a = i;
        this.k = gMSSDigestProvider;
        this.j = gMSSDigestProvider.a();
        this.g = i2;
        this.l = new int[i];
        this.f = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i, this.b});
        this.e = new byte[this.b];
        this.d = new Vector[(this.g - 1)];
        for (int i3 = 0; i3 < i2 - 1; i3++) {
            this.d[i3] = new Vector();
        }
    }

    public void a(Vector vector) {
        int i;
        this.c = new Treehash[(this.a - this.g)];
        for (i = 0; i < this.a - this.g; i++) {
            this.c[i] = new Treehash(vector, i, this.k.a());
        }
        this.l = new int[this.a];
        this.f = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.a, this.b});
        this.e = new byte[this.b];
        this.h = new Vector();
        this.i = new Vector();
        this.m = true;
        this.n = false;
        for (i = 0; i < this.a; i++) {
            this.l[i] = -1;
        }
        this.d = new Vector[(this.g - 1)];
        for (i = 0; i < this.g - 1; i++) {
            this.d[i] = new Vector();
        }
        this.o = 3;
        this.p = 0;
    }

    public void a(byte[] bArr) {
        if (this.n) {
            System.out.print("Too much updates for Tree!!");
        } else if (this.m) {
            int[] iArr = this.l;
            iArr[0] = iArr[0] + 1;
            if (this.l[0] == 1) {
                System.arraycopy(bArr, 0, this.f[0], 0, this.b);
            } else if (this.l[0] == 3 && this.a > this.g) {
                this.c[0].b(bArr);
            }
            if ((this.l[0] - 3) % 2 == 0 && this.l[0] >= 3 && this.a == this.g) {
                this.d[0].insertElementAt(bArr, 0);
            }
            if (this.l[0] == 0) {
                this.h.addElement(bArr);
                this.i.addElement(Integers.a(0));
                return;
            }
            Object obj = new byte[this.b];
            Object obj2 = new byte[(this.b << 1)];
            System.arraycopy(bArr, 0, obj, 0, this.b);
            int i = 0;
            Object obj3 = obj;
            while (this.h.size() > 0 && i == ((Integer) this.i.lastElement()).intValue()) {
                System.arraycopy(this.h.lastElement(), 0, obj2, 0, this.b);
                this.h.removeElementAt(this.h.size() - 1);
                this.i.removeElementAt(this.i.size() - 1);
                System.arraycopy(obj3, 0, obj2, this.b, this.b);
                this.j.a(obj2, 0, obj2.length);
                obj3 = new byte[this.j.b()];
                this.j.a(obj3, 0);
                int i2 = i + 1;
                if (i2 < this.a) {
                    int[] iArr2 = this.l;
                    iArr2[i2] = iArr2[i2] + 1;
                    if (this.l[i2] == 1) {
                        System.arraycopy(obj3, 0, this.f[i2], 0, this.b);
                    }
                    if (i2 >= this.a - this.g) {
                        if (i2 == 0) {
                            System.out.println("M���P");
                        }
                        if ((this.l[i2] - 3) % 2 == 0 && this.l[i2] >= 3) {
                            this.d[i2 - (this.a - this.g)].insertElementAt(obj3, 0);
                            i = i2;
                        }
                    } else if (this.l[i2] == 3) {
                        this.c[i2].b(obj3);
                        i = i2;
                    }
                }
                i = i2;
            }
            this.h.addElement(obj3);
            this.i.addElement(Integers.a(i));
            if (i == this.a) {
                this.n = true;
                this.m = false;
                this.e = (byte[]) this.h.lastElement();
            }
        } else {
            System.err.println("GMSSRootCalc not initialized!");
        }
    }

    public void a(byte[] bArr, int i) {
        this.c[i].a(bArr);
    }

    public boolean a() {
        return this.n;
    }

    public byte[][] b() {
        return GMSSUtils.a(this.f);
    }

    public Treehash[] c() {
        return GMSSUtils.a(this.c);
    }

    public Vector[] d() {
        return GMSSUtils.a(this.d);
    }

    public byte[] e() {
        return Arrays.b(this.e);
    }

    public byte[][] f() {
        int i;
        int i2;
        if (this.h == null) {
            i = 0;
        } else {
            i = this.h.size();
        }
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{(this.a + 1) + i, 64});
        bArr[0] = this.e;
        for (i2 = 0; i2 < this.a; i2++) {
            bArr[i2 + 1] = this.f[i2];
        }
        for (i2 = 0; i2 < i; i2++) {
            bArr[(this.a + 1) + i2] = (byte[]) this.h.elementAt(i2);
        }
        return bArr;
    }

    public int[] g() {
        int i;
        int i2;
        if (this.h == null) {
            i = 0;
        } else {
            i = this.h.size();
        }
        int[] iArr = new int[((this.a + 8) + i)];
        iArr[0] = this.a;
        iArr[1] = this.b;
        iArr[2] = this.g;
        iArr[3] = this.o;
        iArr[4] = this.p;
        if (this.n) {
            iArr[5] = 1;
        } else {
            iArr[5] = 0;
        }
        if (this.m) {
            iArr[6] = 1;
        } else {
            iArr[6] = 0;
        }
        iArr[7] = i;
        for (i2 = 0; i2 < this.a; i2++) {
            iArr[i2 + 8] = this.l[i2];
        }
        for (i2 = 0; i2 < i; i2++) {
            iArr[(this.a + 8) + i2] = ((Integer) this.i.elementAt(i2)).intValue();
        }
        return iArr;
    }

    public String toString() {
        int i;
        int i2 = 0;
        String str = "";
        if (this.h == null) {
            i = 0;
        } else {
            i = this.h.size();
        }
        String str2 = str;
        for (int i3 = 0; i3 < (this.a + 8) + i; i3++) {
            str2 = str2 + g()[i3] + " ";
        }
        while (i2 < (this.a + 1) + i) {
            str2 = str2 + new String(Hex.a(f()[i2])) + " ";
            i2++;
        }
        return str2 + "  " + this.k.a().b();
    }
}
