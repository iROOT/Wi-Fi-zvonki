package org.spongycastle.crypto.digests;

import org.spongycastle.util.Memoable;

public class MD5Digest extends GeneralDigest {
    private int a;
    private int b;
    private int c;
    private int d;
    private int[] e;
    private int f;

    public MD5Digest() {
        this.e = new int[16];
        c();
    }

    public MD5Digest(MD5Digest mD5Digest) {
        super(mD5Digest);
        this.e = new int[16];
        a(mD5Digest);
    }

    private void a(MD5Digest mD5Digest) {
        super.a((GeneralDigest) mD5Digest);
        this.a = mD5Digest.a;
        this.b = mD5Digest.b;
        this.c = mD5Digest.c;
        this.d = mD5Digest.d;
        System.arraycopy(mD5Digest.e, 0, this.e, 0, mD5Digest.e.length);
        this.f = mD5Digest.f;
    }

    public String a() {
        return "MD5";
    }

    public int b() {
        return 16;
    }

    protected void b(byte[] bArr, int i) {
        int[] iArr = this.e;
        int i2 = this.f;
        this.f = i2 + 1;
        iArr[i2] = (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
        if (this.f == 16) {
            g();
        }
    }

    protected void a(long j) {
        if (this.f > 14) {
            g();
        }
        this.e[14] = (int) (-1 & j);
        this.e[15] = (int) (j >>> 32);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public int a(byte[] bArr, int i) {
        f();
        a(this.a, bArr, i);
        a(this.b, bArr, i + 4);
        a(this.c, bArr, i + 8);
        a(this.d, bArr, i + 12);
        c();
        return 16;
    }

    public void c() {
        super.c();
        this.a = 1732584193;
        this.b = -271733879;
        this.c = -1732584194;
        this.d = 271733878;
        this.f = 0;
        for (int i = 0; i != this.e.length; i++) {
            this.e[i] = 0;
        }
    }

    private int a(int i, int i2) {
        return (i << i2) | (i >>> (32 - i2));
    }

    private int a(int i, int i2, int i3) {
        return (i & i2) | ((i ^ -1) & i3);
    }

    private int b(int i, int i2, int i3) {
        return (i & i3) | ((i3 ^ -1) & i2);
    }

    private int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    private int d(int i, int i2, int i3) {
        return ((i3 ^ -1) | i) ^ i2;
    }

    protected void g() {
        int i = this.a;
        int i2 = this.b;
        int i3 = this.c;
        int i4 = this.d;
        i = a(((i + a(i2, i3, i4)) + this.e[0]) - 680876936, 7) + i2;
        i4 = a(((i4 + a(i, i2, i3)) + this.e[1]) - 389564586, 12) + i;
        i3 = a(((i3 + a(i4, i, i2)) + this.e[2]) + 606105819, 17) + i4;
        i2 = a(((i2 + a(i3, i4, i)) + this.e[3]) - 1044525330, 22) + i3;
        i = a(((i + a(i2, i3, i4)) + this.e[4]) - 176418897, 7) + i2;
        i4 = a(((i4 + a(i, i2, i3)) + this.e[5]) + 1200080426, 12) + i;
        i3 = a(((i3 + a(i4, i, i2)) + this.e[6]) - 1473231341, 17) + i4;
        i2 = a(((i2 + a(i3, i4, i)) + this.e[7]) - 45705983, 22) + i3;
        i = a(((i + a(i2, i3, i4)) + this.e[8]) + 1770035416, 7) + i2;
        i4 = a(((i4 + a(i, i2, i3)) + this.e[9]) - 1958414417, 12) + i;
        i3 = a(((i3 + a(i4, i, i2)) + this.e[10]) - 42063, 17) + i4;
        i2 = a(((i2 + a(i3, i4, i)) + this.e[11]) - 1990404162, 22) + i3;
        i = a(((i + a(i2, i3, i4)) + this.e[12]) + 1804603682, 7) + i2;
        i4 = a(((i4 + a(i, i2, i3)) + this.e[13]) - 40341101, 12) + i;
        i3 = a(((i3 + a(i4, i, i2)) + this.e[14]) - 1502002290, 17) + i4;
        i2 = a(((i2 + a(i3, i4, i)) + this.e[15]) + 1236535329, 22) + i3;
        i = a(((i + b(i2, i3, i4)) + this.e[1]) - 165796510, 5) + i2;
        i4 = a(((i4 + b(i, i2, i3)) + this.e[6]) - 1069501632, 9) + i;
        i3 = a(((i3 + b(i4, i, i2)) + this.e[11]) + 643717713, 14) + i4;
        i2 = a(((i2 + b(i3, i4, i)) + this.e[0]) - 373897302, 20) + i3;
        i = a(((i + b(i2, i3, i4)) + this.e[5]) - 701558691, 5) + i2;
        i4 = a(((i4 + b(i, i2, i3)) + this.e[10]) + 38016083, 9) + i;
        i3 = a(((i3 + b(i4, i, i2)) + this.e[15]) - 660478335, 14) + i4;
        i2 = a(((i2 + b(i3, i4, i)) + this.e[4]) - 405537848, 20) + i3;
        i = a(((i + b(i2, i3, i4)) + this.e[9]) + 568446438, 5) + i2;
        i4 = a(((i4 + b(i, i2, i3)) + this.e[14]) - 1019803690, 9) + i;
        i3 = a(((i3 + b(i4, i, i2)) + this.e[3]) - 187363961, 14) + i4;
        i2 = a(((i2 + b(i3, i4, i)) + this.e[8]) + 1163531501, 20) + i3;
        i = a(((i + b(i2, i3, i4)) + this.e[13]) - 1444681467, 5) + i2;
        i4 = a(((i4 + b(i, i2, i3)) + this.e[2]) - 51403784, 9) + i;
        i3 = a(((i3 + b(i4, i, i2)) + this.e[7]) + 1735328473, 14) + i4;
        i2 = a(((i2 + b(i3, i4, i)) + this.e[12]) - 1926607734, 20) + i3;
        i = a(((i + c(i2, i3, i4)) + this.e[5]) - 378558, 4) + i2;
        i4 = a(((i4 + c(i, i2, i3)) + this.e[8]) - 2022574463, 11) + i;
        i3 = a(((i3 + c(i4, i, i2)) + this.e[11]) + 1839030562, 16) + i4;
        i2 = a(((i2 + c(i3, i4, i)) + this.e[14]) - 35309556, 23) + i3;
        i = a(((i + c(i2, i3, i4)) + this.e[1]) - 1530992060, 4) + i2;
        i4 = a(((i4 + c(i, i2, i3)) + this.e[4]) + 1272893353, 11) + i;
        i3 = a(((i3 + c(i4, i, i2)) + this.e[7]) - 155497632, 16) + i4;
        i2 = a(((i2 + c(i3, i4, i)) + this.e[10]) - 1094730640, 23) + i3;
        i = a(((i + c(i2, i3, i4)) + this.e[13]) + 681279174, 4) + i2;
        i4 = a(((i4 + c(i, i2, i3)) + this.e[0]) - 358537222, 11) + i;
        i3 = a(((i3 + c(i4, i, i2)) + this.e[3]) - 722521979, 16) + i4;
        i2 = a(((i2 + c(i3, i4, i)) + this.e[6]) + 76029189, 23) + i3;
        i = a(((i + c(i2, i3, i4)) + this.e[9]) - 640364487, 4) + i2;
        i4 = a(((i4 + c(i, i2, i3)) + this.e[12]) - 421815835, 11) + i;
        i3 = a(((i3 + c(i4, i, i2)) + this.e[15]) + 530742520, 16) + i4;
        i2 = a(((i2 + c(i3, i4, i)) + this.e[2]) - 995338651, 23) + i3;
        i = a(((i + d(i2, i3, i4)) + this.e[0]) - 198630844, 6) + i2;
        i4 = a(((i4 + d(i, i2, i3)) + this.e[7]) + 1126891415, 10) + i;
        i3 = a(((i3 + d(i4, i, i2)) + this.e[14]) - 1416354905, 15) + i4;
        i2 = a(((i2 + d(i3, i4, i)) + this.e[5]) - 57434055, 21) + i3;
        i = a(((i + d(i2, i3, i4)) + this.e[12]) + 1700485571, 6) + i2;
        i4 = a(((i4 + d(i, i2, i3)) + this.e[3]) - 1894986606, 10) + i;
        i3 = a(((i3 + d(i4, i, i2)) + this.e[10]) - 1051523, 15) + i4;
        i2 = a(((i2 + d(i3, i4, i)) + this.e[1]) - 2054922799, 21) + i3;
        i = a(((i + d(i2, i3, i4)) + this.e[8]) + 1873313359, 6) + i2;
        i4 = a(((i4 + d(i, i2, i3)) + this.e[15]) - 30611744, 10) + i;
        i3 = a(((i3 + d(i4, i, i2)) + this.e[6]) - 1560198380, 15) + i4;
        i2 = a(((i2 + d(i3, i4, i)) + this.e[13]) + 1309151649, 21) + i3;
        i = a(((i + d(i2, i3, i4)) + this.e[4]) - 145523070, 6) + i2;
        i4 = a(((i4 + d(i, i2, i3)) + this.e[11]) - 1120210379, 10) + i;
        i3 = a(((i3 + d(i4, i, i2)) + this.e[2]) + 718787259, 15) + i4;
        i2 = a(((i2 + d(i3, i4, i)) + this.e[9]) - 343485551, 21) + i3;
        this.a = i + this.a;
        this.b += i2;
        this.c += i3;
        this.d += i4;
        this.f = 0;
        for (i = 0; i != this.e.length; i++) {
            this.e[i] = 0;
        }
    }

    public Memoable e() {
        return new MD5Digest(this);
    }

    public void a(Memoable memoable) {
        a((MD5Digest) memoable);
    }
}
