package org.spongycastle.pqc.crypto.ntru;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUSigningKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final NTRUSigningKeyGenerationParameters a = new NTRUSigningKeyGenerationParameters(439, k.DIALOG_POSITIVE_BUTTON_ID, 146, 1, 1, 0.165d, 400.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters b = new NTRUSigningKeyGenerationParameters(439, k.DIALOG_POSITIVE_BUTTON_ID, 9, 8, 5, 1, 1, 0.165d, 400.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters c = new NTRUSigningKeyGenerationParameters(743, k.DIALOG_POSITIVE_BUTTON_ID, 248, 1, 1, 0.127d, 405.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters d = new NTRUSigningKeyGenerationParameters(743, k.DIALOG_POSITIVE_BUTTON_ID, 11, 11, 15, 1, 1, 0.127d, 405.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters e = new NTRUSigningKeyGenerationParameters(157, 256, 29, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters f = new NTRUSigningKeyGenerationParameters(157, 256, 5, 5, 8, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
    public int A;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    double n;
    public double o;
    double p;
    public double q;
    public int r = 100;
    double s;
    public double t;
    public boolean u;
    public int v;
    int w = 6;
    public boolean x;
    public int y;
    public Digest z;

    public /* synthetic */ Object clone() {
        return d();
    }

    public NTRUSigningKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, double d, double d2, double d3, boolean z, boolean z2, int i6, Digest digest) {
        super(new SecureRandom(), i);
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.m = i4;
        this.v = i5;
        this.n = d;
        this.p = d2;
        this.s = d3;
        this.u = z;
        this.x = z2;
        this.y = i6;
        this.z = digest;
        this.A = 0;
        e();
    }

    public NTRUSigningKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, double d, double d2, double d3, boolean z, boolean z2, int i8, Digest digest) {
        super(new SecureRandom(), i);
        this.g = i;
        this.h = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
        this.m = i6;
        this.v = i7;
        this.n = d;
        this.p = d2;
        this.s = d3;
        this.u = z;
        this.x = z2;
        this.y = i8;
        this.z = digest;
        this.A = 1;
        e();
    }

    private void e() {
        this.o = this.n * this.n;
        this.q = this.p * this.p;
        this.t = this.s * this.s;
    }

    public NTRUSigningParameters c() {
        return new NTRUSigningParameters(this.g, this.h, this.i, this.m, this.n, this.p, this.z);
    }

    public NTRUSigningKeyGenerationParameters d() {
        if (this.A == 0) {
            return new NTRUSigningKeyGenerationParameters(this.g, this.h, this.i, this.m, this.v, this.n, this.p, this.s, this.u, this.x, this.y, this.z);
        }
        return new NTRUSigningKeyGenerationParameters(this.g, this.h, this.j, this.k, this.l, this.m, this.v, this.n, this.p, this.s, this.u, this.x, this.y, this.z);
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((((this.m + 31) * 31) + this.g) * 31) + this.v;
        long doubleToLongBits = Double.doubleToLongBits(this.n);
        i2 = (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.o);
        int i3 = ((((((((((((i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.w) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + this.l) * 31;
        if (this.z == null) {
            i2 = 0;
        } else {
            i2 = this.z.a().hashCode();
        }
        i2 = ((i2 + i3) * 31) + this.y;
        doubleToLongBits = Double.doubleToLongBits(this.s);
        i2 = (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.t);
        i2 = (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.p);
        i2 = (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        doubleToLongBits = Double.doubleToLongBits(this.q);
        i2 = ((((((this.u ? 1231 : 1237) + (((((i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.A) * 31)) * 31) + this.h) * 31) + this.r) * 31;
        if (!this.x) {
            i = 1237;
        }
        return i2 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NTRUSigningKeyGenerationParameters)) {
            return false;
        }
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = (NTRUSigningKeyGenerationParameters) obj;
        if (this.m != nTRUSigningKeyGenerationParameters.m) {
            return false;
        }
        if (this.g != nTRUSigningKeyGenerationParameters.g) {
            return false;
        }
        if (this.v != nTRUSigningKeyGenerationParameters.v) {
            return false;
        }
        if (Double.doubleToLongBits(this.n) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.n)) {
            return false;
        }
        if (Double.doubleToLongBits(this.o) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.o)) {
            return false;
        }
        if (this.w != nTRUSigningKeyGenerationParameters.w) {
            return false;
        }
        if (this.i != nTRUSigningKeyGenerationParameters.i) {
            return false;
        }
        if (this.j != nTRUSigningKeyGenerationParameters.j) {
            return false;
        }
        if (this.k != nTRUSigningKeyGenerationParameters.k) {
            return false;
        }
        if (this.l != nTRUSigningKeyGenerationParameters.l) {
            return false;
        }
        if (this.z == null) {
            if (nTRUSigningKeyGenerationParameters.z != null) {
                return false;
            }
        } else if (!this.z.a().equals(nTRUSigningKeyGenerationParameters.z.a())) {
            return false;
        }
        if (this.y != nTRUSigningKeyGenerationParameters.y) {
            return false;
        }
        if (Double.doubleToLongBits(this.s) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.s)) {
            return false;
        }
        if (Double.doubleToLongBits(this.t) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.t)) {
            return false;
        }
        if (Double.doubleToLongBits(this.p) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.p)) {
            return false;
        }
        if (Double.doubleToLongBits(this.q) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.q)) {
            return false;
        }
        if (this.A != nTRUSigningKeyGenerationParameters.A) {
            return false;
        }
        if (this.u != nTRUSigningKeyGenerationParameters.u) {
            return false;
        }
        if (this.h != nTRUSigningKeyGenerationParameters.h) {
            return false;
        }
        if (this.r != nTRUSigningKeyGenerationParameters.r) {
            return false;
        }
        if (this.x != nTRUSigningKeyGenerationParameters.x) {
            return false;
        }
        return true;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder stringBuilder = new StringBuilder("SignatureParameters(N=" + this.g + " q=" + this.h);
        if (this.A == 0) {
            stringBuilder.append(" polyType=SIMPLE d=" + this.i);
        } else {
            stringBuilder.append(" polyType=PRODUCT d1=" + this.j + " d2=" + this.k + " d3=" + this.l);
        }
        stringBuilder.append(" B=" + this.m + " basisType=" + this.v + " beta=" + decimalFormat.format(this.n) + " normBound=" + decimalFormat.format(this.p) + " keyNormBound=" + decimalFormat.format(this.s) + " prime=" + this.u + " sparse=" + this.x + " keyGenAlg=" + this.y + " hashAlg=" + this.z + ")");
        return stringBuilder.toString();
    }
}
