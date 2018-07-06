package org.spongycastle.pqc.crypto.ntru;

import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;

public class NTRUSigningParameters implements Cloneable {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    double h;
    public double i;
    double j;
    public double k;
    public int l = 100;
    int m = 6;
    public Digest n;

    public /* synthetic */ Object clone() {
        return a();
    }

    public NTRUSigningParameters(int i, int i2, int i3, int i4, double d, double d2, Digest digest) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.g = i4;
        this.h = d;
        this.j = d2;
        this.n = digest;
        b();
    }

    private void b() {
        this.i = this.h * this.h;
        this.k = this.j * this.j;
    }

    public NTRUSigningParameters a() {
        return new NTRUSigningParameters(this.a, this.b, this.c, this.g, this.h, this.j, this.n);
    }

    public int hashCode() {
        int i = ((this.g + 31) * 31) + this.a;
        long doubleToLongBits = Double.doubleToLongBits(this.h);
        i = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.i);
        i = (this.n == null ? 0 : this.n.a().hashCode()) + (((((((((((((i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.m) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31);
        doubleToLongBits = Double.doubleToLongBits(this.j);
        i = (i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.k);
        return (((((i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.b) * 31) + this.l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = (NTRUSigningParameters) obj;
        if (this.g != nTRUSigningParameters.g) {
            return false;
        }
        if (this.a != nTRUSigningParameters.a) {
            return false;
        }
        if (Double.doubleToLongBits(this.h) != Double.doubleToLongBits(nTRUSigningParameters.h)) {
            return false;
        }
        if (Double.doubleToLongBits(this.i) != Double.doubleToLongBits(nTRUSigningParameters.i)) {
            return false;
        }
        if (this.m != nTRUSigningParameters.m) {
            return false;
        }
        if (this.c != nTRUSigningParameters.c) {
            return false;
        }
        if (this.d != nTRUSigningParameters.d) {
            return false;
        }
        if (this.e != nTRUSigningParameters.e) {
            return false;
        }
        if (this.f != nTRUSigningParameters.f) {
            return false;
        }
        if (this.n == null) {
            if (nTRUSigningParameters.n != null) {
                return false;
            }
        } else if (!this.n.a().equals(nTRUSigningParameters.n.a())) {
            return false;
        }
        if (Double.doubleToLongBits(this.j) != Double.doubleToLongBits(nTRUSigningParameters.j)) {
            return false;
        }
        if (Double.doubleToLongBits(this.k) != Double.doubleToLongBits(nTRUSigningParameters.k)) {
            return false;
        }
        if (this.b != nTRUSigningParameters.b) {
            return false;
        }
        if (this.l != nTRUSigningParameters.l) {
            return false;
        }
        return true;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder stringBuilder = new StringBuilder("SignatureParameters(N=" + this.a + " q=" + this.b);
        stringBuilder.append(" B=" + this.g + " beta=" + decimalFormat.format(this.h) + " normBound=" + decimalFormat.format(this.j) + " hashAlg=" + this.n + ")");
        return stringBuilder.toString();
    }
}
