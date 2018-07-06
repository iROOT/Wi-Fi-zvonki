package org.spongycastle.pqc.crypto.ntru;

import android.support.v4.app.NotificationCompat;
import java.security.SecureRandom;
import java.util.Arrays;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;

public class NTRUEncryptionKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final NTRUEncryptionKeyGenerationParameters a = new NTRUEncryptionKeyGenerationParameters(1087, k.DIALOG_POSITIVE_BUTTON_ID, 120, 120, 256, 13, 25, 14, true, new byte[]{(byte) 0, (byte) 6, (byte) 3}, true, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters b = new NTRUEncryptionKeyGenerationParameters(1171, k.DIALOG_POSITIVE_BUTTON_ID, 106, 106, 256, 13, 20, 15, true, new byte[]{(byte) 0, (byte) 6, (byte) 4}, true, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters c = new NTRUEncryptionKeyGenerationParameters(1499, k.DIALOG_POSITIVE_BUTTON_ID, 79, 79, 256, 13, 17, 19, true, new byte[]{(byte) 0, (byte) 6, (byte) 5}, true, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters d = new NTRUEncryptionKeyGenerationParameters(439, k.DIALOG_POSITIVE_BUTTON_ID, 146, 130, NotificationCompat.FLAG_HIGH_PRIORITY, 9, 32, 9, true, new byte[]{(byte) 0, (byte) 7, (byte) 101}, true, false, new SHA256Digest());
    public static final NTRUEncryptionKeyGenerationParameters e = new NTRUEncryptionKeyGenerationParameters(439, k.DIALOG_POSITIVE_BUTTON_ID, 9, 8, 5, 130, NotificationCompat.FLAG_HIGH_PRIORITY, 9, 32, 9, true, new byte[]{(byte) 0, (byte) 7, (byte) 101}, true, true, new SHA256Digest());
    public static final NTRUEncryptionKeyGenerationParameters f = new NTRUEncryptionKeyGenerationParameters(743, k.DIALOG_POSITIVE_BUTTON_ID, 248, 220, 256, 10, 27, 14, true, new byte[]{(byte) 0, (byte) 7, (byte) 105}, false, false, new SHA512Digest());
    public static final NTRUEncryptionKeyGenerationParameters g = new NTRUEncryptionKeyGenerationParameters(743, k.DIALOG_POSITIVE_BUTTON_ID, 11, 11, 15, 220, 256, 10, 27, 14, true, new byte[]{(byte) 0, (byte) 7, (byte) 105}, false, true, new SHA512Digest());
    public int A;
    public int B;
    public boolean C;
    public byte[] D;
    public boolean E;
    public boolean F;
    public int G = 1;
    public Digest H;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    int s;
    public int t;
    public int u;
    public int v;
    int w;
    public int x;
    public int y;
    public int z;

    public /* synthetic */ Object clone() {
        return d();
    }

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        super(new SecureRandom(), i5);
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.u = i5;
        this.x = i4;
        this.z = i6;
        this.A = i7;
        this.B = i8;
        this.C = z;
        this.D = bArr;
        this.E = z2;
        this.F = z3;
        this.H = digest;
        e();
    }

    public NTRUEncryptionKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z, byte[] bArr, boolean z2, boolean z3, Digest digest) {
        super(new SecureRandom(), i7);
        this.h = i;
        this.i = i2;
        this.k = i3;
        this.l = i4;
        this.m = i5;
        this.u = i7;
        this.x = i6;
        this.z = i8;
        this.A = i9;
        this.B = i10;
        this.C = z;
        this.D = bArr;
        this.E = z2;
        this.F = z3;
        this.H = digest;
        e();
    }

    private void e() {
        this.n = this.j;
        this.o = this.k;
        this.p = this.l;
        this.q = this.m;
        this.r = this.h / 3;
        this.s = 1;
        this.t = (((((this.h * 3) / 2) / 8) - this.s) - (this.u / 8)) - 1;
        this.v = (((((this.h * 3) / 2) + 7) / 8) * 8) + 1;
        this.w = this.h - 1;
        this.y = this.u;
    }

    public NTRUEncryptionParameters c() {
        if (this.G == 0) {
            return new NTRUEncryptionParameters(this.h, this.i, this.j, this.x, this.u, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.H);
        }
        return new NTRUEncryptionParameters(this.h, this.i, this.k, this.l, this.m, this.x, this.u, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.H);
    }

    public NTRUEncryptionKeyGenerationParameters d() {
        if (this.G == 0) {
            return new NTRUEncryptionKeyGenerationParameters(this.h, this.i, this.j, this.x, this.u, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.H);
        }
        return new NTRUEncryptionKeyGenerationParameters(this.h, this.i, this.k, this.l, this.m, this.x, this.u, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.H);
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int hashCode = ((this.H == null ? 0 : this.H.a().hashCode()) + (((this.F ? 1231 : 1237) + ((((((((((((((((((((((((((((((this.h + 31) * 31) + this.v) * 31) + this.w) * 31) + this.z) * 31) + this.u) * 31) + this.j) * 31) + this.k) * 31) + this.l) * 31) + this.m) * 31) + this.r) * 31) + this.x) * 31) + this.n) * 31) + this.o) * 31) + this.p) * 31) + this.q) * 31)) * 31)) * 31;
        if (this.C) {
            i = 1231;
        } else {
            i = 1237;
        }
        i = (((((((((((((((((i + hashCode) * 31) + this.s) * 31) + this.t) * 31) + this.B) * 31) + this.A) * 31) + Arrays.hashCode(this.D)) * 31) + this.y) * 31) + this.G) * 31) + this.i) * 31;
        if (!this.E) {
            i2 = 1237;
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NTRUEncryptionKeyGenerationParameters nTRUEncryptionKeyGenerationParameters = (NTRUEncryptionKeyGenerationParameters) obj;
        if (this.h != nTRUEncryptionKeyGenerationParameters.h) {
            return false;
        }
        if (this.v != nTRUEncryptionKeyGenerationParameters.v) {
            return false;
        }
        if (this.w != nTRUEncryptionKeyGenerationParameters.w) {
            return false;
        }
        if (this.z != nTRUEncryptionKeyGenerationParameters.z) {
            return false;
        }
        if (this.u != nTRUEncryptionKeyGenerationParameters.u) {
            return false;
        }
        if (this.j != nTRUEncryptionKeyGenerationParameters.j) {
            return false;
        }
        if (this.k != nTRUEncryptionKeyGenerationParameters.k) {
            return false;
        }
        if (this.l != nTRUEncryptionKeyGenerationParameters.l) {
            return false;
        }
        if (this.m != nTRUEncryptionKeyGenerationParameters.m) {
            return false;
        }
        if (this.r != nTRUEncryptionKeyGenerationParameters.r) {
            return false;
        }
        if (this.x != nTRUEncryptionKeyGenerationParameters.x) {
            return false;
        }
        if (this.n != nTRUEncryptionKeyGenerationParameters.n) {
            return false;
        }
        if (this.o != nTRUEncryptionKeyGenerationParameters.o) {
            return false;
        }
        if (this.p != nTRUEncryptionKeyGenerationParameters.p) {
            return false;
        }
        if (this.q != nTRUEncryptionKeyGenerationParameters.q) {
            return false;
        }
        if (this.F != nTRUEncryptionKeyGenerationParameters.F) {
            return false;
        }
        if (this.H == null) {
            if (nTRUEncryptionKeyGenerationParameters.H != null) {
                return false;
            }
        } else if (!this.H.a().equals(nTRUEncryptionKeyGenerationParameters.H.a())) {
            return false;
        }
        if (this.C != nTRUEncryptionKeyGenerationParameters.C) {
            return false;
        }
        if (this.s != nTRUEncryptionKeyGenerationParameters.s) {
            return false;
        }
        if (this.t != nTRUEncryptionKeyGenerationParameters.t) {
            return false;
        }
        if (this.B != nTRUEncryptionKeyGenerationParameters.B) {
            return false;
        }
        if (this.A != nTRUEncryptionKeyGenerationParameters.A) {
            return false;
        }
        if (!Arrays.equals(this.D, nTRUEncryptionKeyGenerationParameters.D)) {
            return false;
        }
        if (this.y != nTRUEncryptionKeyGenerationParameters.y) {
            return false;
        }
        if (this.G != nTRUEncryptionKeyGenerationParameters.G) {
            return false;
        }
        if (this.i != nTRUEncryptionKeyGenerationParameters.i) {
            return false;
        }
        if (this.E != nTRUEncryptionKeyGenerationParameters.E) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("EncryptionParameters(N=" + this.h + " q=" + this.i);
        if (this.G == 0) {
            stringBuilder.append(" polyType=SIMPLE df=" + this.j);
        } else {
            stringBuilder.append(" polyType=PRODUCT df1=" + this.k + " df2=" + this.l + " df3=" + this.m);
        }
        stringBuilder.append(" dm0=" + this.x + " db=" + this.u + " c=" + this.z + " minCallsR=" + this.A + " minCallsMask=" + this.B + " hashSeed=" + this.C + " hashAlg=" + this.H + " oid=" + Arrays.toString(this.D) + " sparse=" + this.E + ")");
        return stringBuilder.toString();
    }
}
