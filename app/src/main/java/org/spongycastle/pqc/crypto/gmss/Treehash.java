package org.spongycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.encoders.Hex;

public class Treehash {
    private int a;
    private Vector b;
    private Vector c;
    private byte[] d = null;
    private byte[] e;
    private byte[] f;
    private int g;
    private int h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private Digest l;

    public Treehash(Vector vector, int i, Digest digest) {
        this.b = vector;
        this.a = i;
        this.l = digest;
        this.f = new byte[this.l.b()];
        this.e = new byte[this.l.b()];
    }

    public void a(byte[] bArr) {
        System.arraycopy(bArr, 0, this.f, 0, this.l.b());
        this.k = true;
    }

    public void a() {
        if (this.k) {
            this.c = new Vector();
            this.g = 0;
            this.d = null;
            this.h = -1;
            this.i = true;
            System.arraycopy(this.f, 0, this.e, 0, this.l.b());
            return;
        }
        System.err.println("Seed " + this.a + " not initialized");
    }

    public void b(byte[] bArr) {
        if (!this.i) {
            a();
        }
        this.d = bArr;
        this.h = this.a;
        this.j = true;
    }

    public byte[][] b() {
        byte[][] bArr = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.g + 3, this.l.b()});
        bArr[0] = this.d;
        bArr[1] = this.e;
        bArr[2] = this.f;
        for (int i = 0; i < this.g; i++) {
            bArr[i + 3] = (byte[]) this.b.elementAt(i);
        }
        return bArr;
    }

    public int[] c() {
        int[] iArr = new int[(this.g + 6)];
        iArr[0] = this.a;
        iArr[1] = this.g;
        iArr[2] = this.h;
        if (this.j) {
            iArr[3] = 1;
        } else {
            iArr[3] = 0;
        }
        if (this.i) {
            iArr[4] = 1;
        } else {
            iArr[4] = 0;
        }
        if (this.k) {
            iArr[5] = 1;
        } else {
            iArr[5] = 0;
        }
        for (int i = 0; i < this.g; i++) {
            iArr[i + 6] = ((Integer) this.c.elementAt(i)).intValue();
        }
        return iArr;
    }

    public String toString() {
        int i = 0;
        String str = "Treehash    : ";
        for (int i2 = 0; i2 < this.g + 6; i2++) {
            str = str + c()[i2] + " ";
        }
        while (i < this.g + 3) {
            if (b()[i] != null) {
                str = str + new String(Hex.a(b()[i])) + " ";
            } else {
                str = str + "null ";
            }
            i++;
        }
        return str + "  " + this.l.b();
    }
}
