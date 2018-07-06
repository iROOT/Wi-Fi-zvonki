package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.MaxBytesExceededException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Strings;

public class Salsa20Engine implements StreamCipher {
    protected static final byte[] a = Strings.d("expand 32-byte k");
    protected static final byte[] b = Strings.d("expand 16-byte k");
    protected int c;
    protected int[] d;
    protected int[] e;
    private int f;
    private byte[] g;
    private boolean h;
    private int i;
    private int j;
    private int k;

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int i) {
        this.f = 0;
        this.d = new int[16];
        this.e = new int[16];
        this.g = new byte[64];
        this.h = false;
        if (i <= 0 || (i & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.c = i;
    }

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] a = parametersWithIV.a();
            if (a == null || a.length != e()) {
                throw new IllegalArgumentException(a() + " requires exactly " + e() + " bytes of IV");
            } else if (parametersWithIV.b() instanceof KeyParameter) {
                a(((KeyParameter) parametersWithIV.b()).a(), a);
                b();
                this.h = true;
                return;
            } else {
                throw new IllegalArgumentException(a() + " Init parameters must include a key");
            }
        }
        throw new IllegalArgumentException(a() + " Init parameters must include an IV");
    }

    protected int e() {
        return 8;
    }

    public String a() {
        String str = "Salsa20";
        if (this.c != 20) {
            return str + "/" + this.c;
        }
        return str;
    }

    public byte a(byte b) {
        if (g()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        if (this.f == 0) {
            a(this.g);
            c();
        }
        byte b2 = (byte) (this.g[this.f] ^ b);
        this.f = (this.f + 1) & 63;
        return b2;
    }

    protected void c() {
        int[] iArr = this.d;
        int i = iArr[8] + 1;
        iArr[8] = i;
        if (i == 0) {
            iArr = this.d;
            iArr[9] = iArr[9] + 1;
        }
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.h) {
            throw new IllegalStateException(a() + " not initialised");
        } else if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else if (a(i2)) {
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.f == 0) {
                    a(this.g);
                    c();
                }
                bArr2[i4 + i3] = (byte) (this.g[this.f] ^ bArr[i4 + i]);
                this.f = (this.f + 1) & 63;
            }
        }
    }

    public void b() {
        this.f = 0;
        f();
        d();
    }

    protected void d() {
        int[] iArr = this.d;
        this.d[9] = 0;
        iArr[8] = 0;
    }

    protected void a(byte[] bArr, byte[] bArr2) {
        int i = 16;
        if (bArr.length == 16 || bArr.length == 32) {
            byte[] bArr3;
            this.d[1] = Pack.c(bArr, 0);
            this.d[2] = Pack.c(bArr, 4);
            this.d[3] = Pack.c(bArr, 8);
            this.d[4] = Pack.c(bArr, 12);
            if (bArr.length == 32) {
                bArr3 = a;
            } else {
                bArr3 = b;
                i = 0;
            }
            this.d[11] = Pack.c(bArr, i);
            this.d[12] = Pack.c(bArr, i + 4);
            this.d[13] = Pack.c(bArr, i + 8);
            this.d[14] = Pack.c(bArr, i + 12);
            this.d[0] = Pack.c(bArr3, 0);
            this.d[5] = Pack.c(bArr3, 4);
            this.d[10] = Pack.c(bArr3, 8);
            this.d[15] = Pack.c(bArr3, 12);
            this.d[6] = Pack.c(bArr2, 0);
            this.d[7] = Pack.c(bArr2, 4);
            d();
            return;
        }
        throw new IllegalArgumentException(a() + " requires 128 bit or 256 bit key");
    }

    protected void a(byte[] bArr) {
        b(this.c, this.d, this.e);
        Pack.b(this.e, bArr, 0);
    }

    public static void b(int i, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        } else if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        } else if (i % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        } else {
            int i2 = iArr[0];
            int i3 = iArr[1];
            int i4 = iArr[2];
            int i5 = iArr[3];
            int i6 = iArr[4];
            int i7 = iArr[5];
            int i8 = iArr[6];
            int i9 = iArr[7];
            int i10 = iArr[8];
            int i11 = iArr[9];
            int i12 = iArr[10];
            int i13 = iArr[11];
            int i14 = iArr[12];
            int i15 = iArr[13];
            int i16 = iArr[14];
            int i17 = iArr[15];
            while (i > 0) {
                i6 ^= a(i2 + i14, 7);
                i10 ^= a(i6 + i2, 9);
                i14 ^= a(i10 + i6, 13);
                i2 ^= a(i14 + i10, 18);
                i11 ^= a(i7 + i3, 7);
                i15 ^= a(i11 + i7, 9);
                i3 ^= a(i15 + i11, 13);
                i7 ^= a(i3 + i15, 18);
                i16 ^= a(i12 + i8, 7);
                i4 ^= a(i16 + i12, 9);
                i8 ^= a(i4 + i16, 13);
                i12 ^= a(i8 + i4, 18);
                i5 ^= a(i17 + i13, 7);
                i9 ^= a(i5 + i17, 9);
                i13 ^= a(i9 + i5, 13);
                i17 ^= a(i13 + i9, 18);
                i3 ^= a(i2 + i5, 7);
                i4 ^= a(i3 + i2, 9);
                i5 ^= a(i4 + i3, 13);
                i2 ^= a(i5 + i4, 18);
                i8 ^= a(i7 + i6, 7);
                i9 ^= a(i8 + i7, 9);
                i6 ^= a(i9 + i8, 13);
                i7 ^= a(i6 + i9, 18);
                i13 ^= a(i12 + i11, 7);
                i10 ^= a(i13 + i12, 9);
                i11 ^= a(i10 + i13, 13);
                i12 ^= a(i11 + i10, 18);
                i14 ^= a(i17 + i16, 7);
                i15 ^= a(i14 + i17, 9);
                i16 ^= a(i15 + i14, 13);
                i17 ^= a(i16 + i15, 18);
                i -= 2;
            }
            iArr2[0] = i2 + iArr[0];
            iArr2[1] = i3 + iArr[1];
            iArr2[2] = i4 + iArr[2];
            iArr2[3] = i5 + iArr[3];
            iArr2[4] = i6 + iArr[4];
            iArr2[5] = i7 + iArr[5];
            iArr2[6] = i8 + iArr[6];
            iArr2[7] = i9 + iArr[7];
            iArr2[8] = i10 + iArr[8];
            iArr2[9] = i11 + iArr[9];
            iArr2[10] = i12 + iArr[10];
            iArr2[11] = i13 + iArr[11];
            iArr2[12] = i14 + iArr[12];
            iArr2[13] = i15 + iArr[13];
            iArr2[14] = i16 + iArr[14];
            iArr2[15] = i17 + iArr[15];
        }
    }

    protected static int a(int i, int i2) {
        return (i << i2) | (i >>> (-i2));
    }

    private void f() {
        this.i = 0;
        this.j = 0;
        this.k = 0;
    }

    private boolean g() {
        int i = this.i + 1;
        this.i = i;
        if (i != 0) {
            return false;
        }
        i = this.j + 1;
        this.j = i;
        if (i != 0) {
            return false;
        }
        i = this.k + 1;
        this.k = i;
        if ((i & 32) != 0) {
            return true;
        }
        return false;
    }

    private boolean a(int i) {
        this.i += i;
        if (this.i >= i || this.i < 0) {
            return false;
        }
        int i2 = this.j + 1;
        this.j = i2;
        if (i2 != 0) {
            return false;
        }
        i2 = this.k + 1;
        this.k = i2;
        if ((i2 & 32) != 0) {
            return true;
        }
        return false;
    }
}
