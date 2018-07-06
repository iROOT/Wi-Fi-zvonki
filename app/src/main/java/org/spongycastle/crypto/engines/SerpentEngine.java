package org.spongycastle.crypto.engines;

import android.support.v4.app.NotificationCompat;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public class SerpentEngine implements BlockCipher {
    private boolean a;
    private int[] b;
    private int c;
    private int d;
    private int e;
    private int f;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.a = z;
            this.b = a(((KeyParameter) cipherParameters).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Serpent init - " + cipherParameters.getClass().getName());
    }

    public String a() {
        return "Serpent";
    }

    public int b() {
        return 16;
    }

    public final int a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.b == null) {
            throw new IllegalStateException("Serpent not initialised");
        } else if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.a) {
                b(bArr, i, bArr2, i2);
            } else {
                c(bArr, i, bArr2, i2);
            }
            return 16;
        }
    }

    public void c() {
    }

    private int[] a(byte[] bArr) {
        Object obj = new int[16];
        int length = bArr.length - 4;
        int i = 0;
        while (length > 0) {
            int i2 = i + 1;
            obj[i] = a(bArr, length);
            length -= 4;
            i = i2;
        }
        if (length == 0) {
            i2 = i + 1;
            obj[i] = a(bArr, 0);
            if (i2 < 8) {
                obj[i2] = 1;
            }
            Object obj2 = new int[132];
            for (i = 8; i < 16; i++) {
                obj[i] = a(((((obj[i - 8] ^ obj[i - 5]) ^ obj[i - 3]) ^ obj[i - 1]) ^ -1640531527) ^ (i - 8), 11);
            }
            System.arraycopy(obj, 8, obj2, 0, 8);
            for (i = 8; i < 132; i++) {
                obj2[i] = a(((((obj2[i - 8] ^ obj2[i - 5]) ^ obj2[i - 3]) ^ obj2[i - 1]) ^ -1640531527) ^ i, 11);
            }
            g(obj2[0], obj2[1], obj2[2], obj2[3]);
            obj2[0] = this.c;
            obj2[1] = this.d;
            obj2[2] = this.e;
            obj2[3] = this.f;
            e(obj2[4], obj2[5], obj2[6], obj2[7]);
            obj2[4] = this.c;
            obj2[5] = this.d;
            obj2[6] = this.e;
            obj2[7] = this.f;
            c(obj2[8], obj2[9], obj2[10], obj2[11]);
            obj2[8] = this.c;
            obj2[9] = this.d;
            obj2[10] = this.e;
            obj2[11] = this.f;
            a(obj2[12], obj2[13], obj2[14], obj2[15]);
            obj2[12] = this.c;
            obj2[13] = this.d;
            obj2[14] = this.e;
            obj2[15] = this.f;
            o(obj2[16], obj2[17], obj2[18], obj2[19]);
            obj2[16] = this.c;
            obj2[17] = this.d;
            obj2[18] = this.e;
            obj2[19] = this.f;
            m(obj2[20], obj2[21], obj2[22], obj2[23]);
            obj2[20] = this.c;
            obj2[21] = this.d;
            obj2[22] = this.e;
            obj2[23] = this.f;
            k(obj2[24], obj2[25], obj2[26], obj2[27]);
            obj2[24] = this.c;
            obj2[25] = this.d;
            obj2[26] = this.e;
            obj2[27] = this.f;
            i(obj2[28], obj2[29], obj2[30], obj2[31]);
            obj2[28] = this.c;
            obj2[29] = this.d;
            obj2[30] = this.e;
            obj2[31] = this.f;
            g(obj2[32], obj2[33], obj2[34], obj2[35]);
            obj2[32] = this.c;
            obj2[33] = this.d;
            obj2[34] = this.e;
            obj2[35] = this.f;
            e(obj2[36], obj2[37], obj2[38], obj2[39]);
            obj2[36] = this.c;
            obj2[37] = this.d;
            obj2[38] = this.e;
            obj2[39] = this.f;
            c(obj2[40], obj2[41], obj2[42], obj2[43]);
            obj2[40] = this.c;
            obj2[41] = this.d;
            obj2[42] = this.e;
            obj2[43] = this.f;
            a(obj2[44], obj2[45], obj2[46], obj2[47]);
            obj2[44] = this.c;
            obj2[45] = this.d;
            obj2[46] = this.e;
            obj2[47] = this.f;
            o(obj2[48], obj2[49], obj2[50], obj2[51]);
            obj2[48] = this.c;
            obj2[49] = this.d;
            obj2[50] = this.e;
            obj2[51] = this.f;
            m(obj2[52], obj2[53], obj2[54], obj2[55]);
            obj2[52] = this.c;
            obj2[53] = this.d;
            obj2[54] = this.e;
            obj2[55] = this.f;
            k(obj2[56], obj2[57], obj2[58], obj2[59]);
            obj2[56] = this.c;
            obj2[57] = this.d;
            obj2[58] = this.e;
            obj2[59] = this.f;
            i(obj2[60], obj2[61], obj2[62], obj2[63]);
            obj2[60] = this.c;
            obj2[61] = this.d;
            obj2[62] = this.e;
            obj2[63] = this.f;
            g(obj2[64], obj2[65], obj2[66], obj2[67]);
            obj2[64] = this.c;
            obj2[65] = this.d;
            obj2[66] = this.e;
            obj2[67] = this.f;
            e(obj2[68], obj2[69], obj2[70], obj2[71]);
            obj2[68] = this.c;
            obj2[69] = this.d;
            obj2[70] = this.e;
            obj2[71] = this.f;
            c(obj2[72], obj2[73], obj2[74], obj2[75]);
            obj2[72] = this.c;
            obj2[73] = this.d;
            obj2[74] = this.e;
            obj2[75] = this.f;
            a(obj2[76], obj2[77], obj2[78], obj2[79]);
            obj2[76] = this.c;
            obj2[77] = this.d;
            obj2[78] = this.e;
            obj2[79] = this.f;
            o(obj2[80], obj2[81], obj2[82], obj2[83]);
            obj2[80] = this.c;
            obj2[81] = this.d;
            obj2[82] = this.e;
            obj2[83] = this.f;
            m(obj2[84], obj2[85], obj2[86], obj2[87]);
            obj2[84] = this.c;
            obj2[85] = this.d;
            obj2[86] = this.e;
            obj2[87] = this.f;
            k(obj2[88], obj2[89], obj2[90], obj2[91]);
            obj2[88] = this.c;
            obj2[89] = this.d;
            obj2[90] = this.e;
            obj2[91] = this.f;
            i(obj2[92], obj2[93], obj2[94], obj2[95]);
            obj2[92] = this.c;
            obj2[93] = this.d;
            obj2[94] = this.e;
            obj2[95] = this.f;
            g(obj2[96], obj2[97], obj2[98], obj2[99]);
            obj2[96] = this.c;
            obj2[97] = this.d;
            obj2[98] = this.e;
            obj2[99] = this.f;
            e(obj2[100], obj2[101], obj2[102], obj2[103]);
            obj2[100] = this.c;
            obj2[101] = this.d;
            obj2[102] = this.e;
            obj2[103] = this.f;
            c(obj2[104], obj2[105], obj2[106], obj2[107]);
            obj2[104] = this.c;
            obj2[105] = this.d;
            obj2[106] = this.e;
            obj2[107] = this.f;
            a(obj2[108], obj2[109], obj2[110], obj2[111]);
            obj2[108] = this.c;
            obj2[109] = this.d;
            obj2[110] = this.e;
            obj2[111] = this.f;
            o(obj2[112], obj2[113], obj2[114], obj2[115]);
            obj2[112] = this.c;
            obj2[113] = this.d;
            obj2[114] = this.e;
            obj2[115] = this.f;
            m(obj2[116], obj2[117], obj2[118], obj2[119]);
            obj2[116] = this.c;
            obj2[117] = this.d;
            obj2[118] = this.e;
            obj2[119] = this.f;
            k(obj2[120], obj2[121], obj2[122], obj2[123]);
            obj2[120] = this.c;
            obj2[121] = this.d;
            obj2[122] = this.e;
            obj2[123] = this.f;
            i(obj2[124], obj2[125], obj2[126], obj2[127]);
            obj2[124] = this.c;
            obj2[125] = this.d;
            obj2[126] = this.e;
            obj2[127] = this.f;
            g(obj2[NotificationCompat.FLAG_HIGH_PRIORITY], obj2[129], obj2[130], obj2[131]);
            obj2[NotificationCompat.FLAG_HIGH_PRIORITY] = this.c;
            obj2[129] = this.d;
            obj2[130] = this.e;
            obj2[131] = this.f;
            return obj2;
        }
        throw new IllegalArgumentException("key must be a multiple of 4 bytes");
    }

    private int a(int i, int i2) {
        return (i << i2) | (i >>> (-i2));
    }

    private int b(int i, int i2) {
        return (i >>> i2) | (i << (-i2));
    }

    private int a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8)) | (bArr[i + 3] & 255);
    }

    private void a(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) i;
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2] = (byte) (i >>> 24);
    }

    private void b(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f = a(bArr, i);
        this.e = a(bArr, i + 4);
        this.d = a(bArr, i + 8);
        this.c = a(bArr, i + 12);
        a(this.b[0] ^ this.c, this.b[1] ^ this.d, this.b[2] ^ this.e, this.b[3] ^ this.f);
        d();
        c(this.b[4] ^ this.c, this.b[5] ^ this.d, this.b[6] ^ this.e, this.b[7] ^ this.f);
        d();
        e(this.b[8] ^ this.c, this.b[9] ^ this.d, this.b[10] ^ this.e, this.b[11] ^ this.f);
        d();
        g(this.b[12] ^ this.c, this.b[13] ^ this.d, this.b[14] ^ this.e, this.b[15] ^ this.f);
        d();
        i(this.b[16] ^ this.c, this.b[17] ^ this.d, this.b[18] ^ this.e, this.b[19] ^ this.f);
        d();
        k(this.b[20] ^ this.c, this.b[21] ^ this.d, this.b[22] ^ this.e, this.b[23] ^ this.f);
        d();
        m(this.b[24] ^ this.c, this.b[25] ^ this.d, this.b[26] ^ this.e, this.b[27] ^ this.f);
        d();
        o(this.b[28] ^ this.c, this.b[29] ^ this.d, this.b[30] ^ this.e, this.b[31] ^ this.f);
        d();
        a(this.b[32] ^ this.c, this.b[33] ^ this.d, this.b[34] ^ this.e, this.b[35] ^ this.f);
        d();
        c(this.b[36] ^ this.c, this.b[37] ^ this.d, this.b[38] ^ this.e, this.b[39] ^ this.f);
        d();
        e(this.b[40] ^ this.c, this.b[41] ^ this.d, this.b[42] ^ this.e, this.b[43] ^ this.f);
        d();
        g(this.b[44] ^ this.c, this.b[45] ^ this.d, this.b[46] ^ this.e, this.b[47] ^ this.f);
        d();
        i(this.b[48] ^ this.c, this.b[49] ^ this.d, this.b[50] ^ this.e, this.b[51] ^ this.f);
        d();
        k(this.b[52] ^ this.c, this.b[53] ^ this.d, this.b[54] ^ this.e, this.b[55] ^ this.f);
        d();
        m(this.b[56] ^ this.c, this.b[57] ^ this.d, this.b[58] ^ this.e, this.b[59] ^ this.f);
        d();
        o(this.b[60] ^ this.c, this.b[61] ^ this.d, this.b[62] ^ this.e, this.b[63] ^ this.f);
        d();
        a(this.b[64] ^ this.c, this.b[65] ^ this.d, this.b[66] ^ this.e, this.b[67] ^ this.f);
        d();
        c(this.b[68] ^ this.c, this.b[69] ^ this.d, this.b[70] ^ this.e, this.b[71] ^ this.f);
        d();
        e(this.b[72] ^ this.c, this.b[73] ^ this.d, this.b[74] ^ this.e, this.b[75] ^ this.f);
        d();
        g(this.b[76] ^ this.c, this.b[77] ^ this.d, this.b[78] ^ this.e, this.b[79] ^ this.f);
        d();
        i(this.b[80] ^ this.c, this.b[81] ^ this.d, this.b[82] ^ this.e, this.b[83] ^ this.f);
        d();
        k(this.b[84] ^ this.c, this.b[85] ^ this.d, this.b[86] ^ this.e, this.b[87] ^ this.f);
        d();
        m(this.b[88] ^ this.c, this.b[89] ^ this.d, this.b[90] ^ this.e, this.b[91] ^ this.f);
        d();
        o(this.b[92] ^ this.c, this.b[93] ^ this.d, this.b[94] ^ this.e, this.b[95] ^ this.f);
        d();
        a(this.b[96] ^ this.c, this.b[97] ^ this.d, this.b[98] ^ this.e, this.b[99] ^ this.f);
        d();
        c(this.b[100] ^ this.c, this.b[101] ^ this.d, this.b[102] ^ this.e, this.b[103] ^ this.f);
        d();
        e(this.b[104] ^ this.c, this.b[105] ^ this.d, this.b[106] ^ this.e, this.b[107] ^ this.f);
        d();
        g(this.b[108] ^ this.c, this.b[109] ^ this.d, this.b[110] ^ this.e, this.b[111] ^ this.f);
        d();
        i(this.b[112] ^ this.c, this.b[113] ^ this.d, this.b[114] ^ this.e, this.b[115] ^ this.f);
        d();
        k(this.b[116] ^ this.c, this.b[117] ^ this.d, this.b[118] ^ this.e, this.b[119] ^ this.f);
        d();
        m(this.b[120] ^ this.c, this.b[121] ^ this.d, this.b[122] ^ this.e, this.b[123] ^ this.f);
        d();
        o(this.b[124] ^ this.c, this.b[125] ^ this.d, this.b[126] ^ this.e, this.b[127] ^ this.f);
        a(this.b[131] ^ this.f, bArr2, i2);
        a(this.b[130] ^ this.e, bArr2, i2 + 4);
        a(this.b[129] ^ this.d, bArr2, i2 + 8);
        a(this.b[NotificationCompat.FLAG_HIGH_PRIORITY] ^ this.c, bArr2, i2 + 12);
    }

    private void c(byte[] bArr, int i, byte[] bArr2, int i2) {
        this.f = this.b[131] ^ a(bArr, i);
        this.e = this.b[130] ^ a(bArr, i + 4);
        this.d = this.b[129] ^ a(bArr, i + 8);
        this.c = this.b[NotificationCompat.FLAG_HIGH_PRIORITY] ^ a(bArr, i + 12);
        p(this.c, this.d, this.e, this.f);
        this.c ^= this.b[124];
        this.d ^= this.b[125];
        this.e ^= this.b[126];
        this.f ^= this.b[127];
        e();
        n(this.c, this.d, this.e, this.f);
        this.c ^= this.b[120];
        this.d ^= this.b[121];
        this.e ^= this.b[122];
        this.f ^= this.b[123];
        e();
        l(this.c, this.d, this.e, this.f);
        this.c ^= this.b[116];
        this.d ^= this.b[117];
        this.e ^= this.b[118];
        this.f ^= this.b[119];
        e();
        j(this.c, this.d, this.e, this.f);
        this.c ^= this.b[112];
        this.d ^= this.b[113];
        this.e ^= this.b[114];
        this.f ^= this.b[115];
        e();
        h(this.c, this.d, this.e, this.f);
        this.c ^= this.b[108];
        this.d ^= this.b[109];
        this.e ^= this.b[110];
        this.f ^= this.b[111];
        e();
        f(this.c, this.d, this.e, this.f);
        this.c ^= this.b[104];
        this.d ^= this.b[105];
        this.e ^= this.b[106];
        this.f ^= this.b[107];
        e();
        d(this.c, this.d, this.e, this.f);
        this.c ^= this.b[100];
        this.d ^= this.b[101];
        this.e ^= this.b[102];
        this.f ^= this.b[103];
        e();
        b(this.c, this.d, this.e, this.f);
        this.c ^= this.b[96];
        this.d ^= this.b[97];
        this.e ^= this.b[98];
        this.f ^= this.b[99];
        e();
        p(this.c, this.d, this.e, this.f);
        this.c ^= this.b[92];
        this.d ^= this.b[93];
        this.e ^= this.b[94];
        this.f ^= this.b[95];
        e();
        n(this.c, this.d, this.e, this.f);
        this.c ^= this.b[88];
        this.d ^= this.b[89];
        this.e ^= this.b[90];
        this.f ^= this.b[91];
        e();
        l(this.c, this.d, this.e, this.f);
        this.c ^= this.b[84];
        this.d ^= this.b[85];
        this.e ^= this.b[86];
        this.f ^= this.b[87];
        e();
        j(this.c, this.d, this.e, this.f);
        this.c ^= this.b[80];
        this.d ^= this.b[81];
        this.e ^= this.b[82];
        this.f ^= this.b[83];
        e();
        h(this.c, this.d, this.e, this.f);
        this.c ^= this.b[76];
        this.d ^= this.b[77];
        this.e ^= this.b[78];
        this.f ^= this.b[79];
        e();
        f(this.c, this.d, this.e, this.f);
        this.c ^= this.b[72];
        this.d ^= this.b[73];
        this.e ^= this.b[74];
        this.f ^= this.b[75];
        e();
        d(this.c, this.d, this.e, this.f);
        this.c ^= this.b[68];
        this.d ^= this.b[69];
        this.e ^= this.b[70];
        this.f ^= this.b[71];
        e();
        b(this.c, this.d, this.e, this.f);
        this.c ^= this.b[64];
        this.d ^= this.b[65];
        this.e ^= this.b[66];
        this.f ^= this.b[67];
        e();
        p(this.c, this.d, this.e, this.f);
        this.c ^= this.b[60];
        this.d ^= this.b[61];
        this.e ^= this.b[62];
        this.f ^= this.b[63];
        e();
        n(this.c, this.d, this.e, this.f);
        this.c ^= this.b[56];
        this.d ^= this.b[57];
        this.e ^= this.b[58];
        this.f ^= this.b[59];
        e();
        l(this.c, this.d, this.e, this.f);
        this.c ^= this.b[52];
        this.d ^= this.b[53];
        this.e ^= this.b[54];
        this.f ^= this.b[55];
        e();
        j(this.c, this.d, this.e, this.f);
        this.c ^= this.b[48];
        this.d ^= this.b[49];
        this.e ^= this.b[50];
        this.f ^= this.b[51];
        e();
        h(this.c, this.d, this.e, this.f);
        this.c ^= this.b[44];
        this.d ^= this.b[45];
        this.e ^= this.b[46];
        this.f ^= this.b[47];
        e();
        f(this.c, this.d, this.e, this.f);
        this.c ^= this.b[40];
        this.d ^= this.b[41];
        this.e ^= this.b[42];
        this.f ^= this.b[43];
        e();
        d(this.c, this.d, this.e, this.f);
        this.c ^= this.b[36];
        this.d ^= this.b[37];
        this.e ^= this.b[38];
        this.f ^= this.b[39];
        e();
        b(this.c, this.d, this.e, this.f);
        this.c ^= this.b[32];
        this.d ^= this.b[33];
        this.e ^= this.b[34];
        this.f ^= this.b[35];
        e();
        p(this.c, this.d, this.e, this.f);
        this.c ^= this.b[28];
        this.d ^= this.b[29];
        this.e ^= this.b[30];
        this.f ^= this.b[31];
        e();
        n(this.c, this.d, this.e, this.f);
        this.c ^= this.b[24];
        this.d ^= this.b[25];
        this.e ^= this.b[26];
        this.f ^= this.b[27];
        e();
        l(this.c, this.d, this.e, this.f);
        this.c ^= this.b[20];
        this.d ^= this.b[21];
        this.e ^= this.b[22];
        this.f ^= this.b[23];
        e();
        j(this.c, this.d, this.e, this.f);
        this.c ^= this.b[16];
        this.d ^= this.b[17];
        this.e ^= this.b[18];
        this.f ^= this.b[19];
        e();
        h(this.c, this.d, this.e, this.f);
        this.c ^= this.b[12];
        this.d ^= this.b[13];
        this.e ^= this.b[14];
        this.f ^= this.b[15];
        e();
        f(this.c, this.d, this.e, this.f);
        this.c ^= this.b[8];
        this.d ^= this.b[9];
        this.e ^= this.b[10];
        this.f ^= this.b[11];
        e();
        d(this.c, this.d, this.e, this.f);
        this.c ^= this.b[4];
        this.d ^= this.b[5];
        this.e ^= this.b[6];
        this.f ^= this.b[7];
        e();
        b(this.c, this.d, this.e, this.f);
        a(this.f ^ this.b[3], bArr2, i2);
        a(this.e ^ this.b[2], bArr2, i2 + 4);
        a(this.d ^ this.b[1], bArr2, i2 + 8);
        a(this.c ^ this.b[0], bArr2, i2 + 12);
    }

    private void a(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ i5;
        int i7 = i2 ^ i6;
        this.f = (i & i4) ^ i7;
        i5 = (i5 & i2) ^ i;
        this.e = i7 ^ (i3 | i5);
        i7 = this.f & (i6 ^ i5);
        this.d = (i6 ^ -1) ^ i7;
        this.c = (i5 ^ -1) ^ i7;
    }

    private void b(int i, int i2, int i3, int i4) {
        int i5 = i ^ -1;
        int i6 = i ^ i2;
        int i7 = (i5 | i6) ^ i4;
        int i8 = i3 ^ i7;
        this.e = i6 ^ i8;
        i5 ^= i6 & i4;
        this.d = (this.e & i5) ^ i7;
        this.f = (i & i7) ^ (this.d | i8);
        this.c = (i5 ^ i8) ^ this.f;
    }

    private void c(int i, int i2, int i3, int i4) {
        int i5 = (i ^ -1) ^ i2;
        int i6 = (i | i5) ^ i3;
        this.e = i4 ^ i6;
        int i7 = (i4 | i5) ^ i2;
        i5 ^= this.e;
        this.f = (i6 & i7) ^ i5;
        i7 ^= i6;
        this.d = this.f ^ i7;
        this.c = (i5 & i7) ^ i6;
    }

    private void d(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = (i2 & i5) ^ i;
        int i7 = i5 ^ i6;
        this.f = i3 ^ i7;
        i5 = (i5 & i6) ^ i2;
        this.d = i6 ^ (this.f | i5);
        i6 = this.d ^ -1;
        i5 ^= this.f;
        this.c = i6 ^ i5;
        this.e = (i5 | i6) ^ i7;
    }

    private void e(int i, int i2, int i3, int i4) {
        int i5 = i ^ -1;
        int i6 = i2 ^ i4;
        this.c = (i3 & i5) ^ i6;
        int i7 = i3 ^ i5;
        int i8 = (this.c ^ i3) & i2;
        this.f = i7 ^ i8;
        this.e = ((i7 | this.c) & (i8 | i4)) ^ i;
        this.d = ((i5 | i4) ^ this.e) ^ (i6 ^ this.f);
    }

    private void f(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = i5 ^ -1;
        int i7 = i ^ i3;
        int i8 = i3 ^ i5;
        this.c = (i2 & i8) ^ i7;
        this.f = i5 ^ (((i6 | i) ^ i4) | i7);
        i5 = i8 ^ -1;
        i6 = this.c | this.f;
        this.d = i5 ^ i6;
        this.e = (i5 & i4) ^ (i6 ^ i7);
    }

    private void g(int i, int i2, int i3, int i4) {
        int i5 = i ^ i2;
        int i6 = i | i4;
        int i7 = i3 ^ i4;
        int i8 = (i & i3) | (i5 & i6);
        this.e = i7 ^ i8;
        i8 ^= i6 ^ i2;
        this.c = i5 ^ (i7 & i8);
        i5 = this.e & this.c;
        this.d = i8 ^ i5;
        this.f = (i5 ^ i7) ^ (i2 | i4);
    }

    private void h(int i, int i2, int i3, int i4) {
        int i5 = i | i2;
        int i6 = i2 ^ i3;
        int i7 = (i2 & i6) ^ i;
        int i8 = i3 ^ i7;
        int i9 = i4 | i7;
        this.c = i6 ^ i9;
        i6 = (i6 | i9) ^ i4;
        this.e = i8 ^ i6;
        i5 ^= i6;
        this.f = (this.c & i5) ^ i7;
        this.d = (i5 ^ this.c) ^ this.f;
    }

    private void i(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = (i4 & i5) ^ i3;
        int i7 = i2 | i6;
        this.f = i5 ^ i7;
        int i8 = i2 ^ -1;
        this.c = (i5 | i8) ^ i6;
        i5 ^= i8;
        this.e = (i7 & i5) ^ (this.c & i);
        this.d = (i5 & this.e) ^ (i6 ^ i);
    }

    private void j(int i, int i2, int i3, int i4) {
        int i5 = ((i3 | i4) & i) ^ i2;
        int i6 = (i & i5) ^ i3;
        this.d = i4 ^ i6;
        int i7 = i ^ -1;
        this.f = (i6 & this.d) ^ i5;
        i6 = (this.d | i7) ^ i4;
        this.c = this.f ^ i6;
        this.e = (i5 & i6) ^ (this.d ^ i7);
    }

    private void k(int i, int i2, int i3, int i4) {
        int i5 = i ^ -1;
        int i6 = i ^ i2;
        int i7 = i ^ i4;
        this.c = (i3 ^ i5) ^ (i6 | i7);
        int i8 = this.c & i4;
        this.d = (this.c ^ i6) ^ i8;
        i5 = (i5 | this.c) ^ i7;
        this.e = (i6 | i8) ^ i5;
        this.f = (i5 & this.d) ^ (i2 ^ i8);
    }

    private void l(int i, int i2, int i3, int i4) {
        int i5 = i3 ^ -1;
        int i6 = (i2 & i5) ^ i4;
        int i7 = i & i6;
        this.f = (i2 ^ i5) ^ i7;
        int i8 = this.f | i2;
        this.d = i6 ^ (i & i8);
        i6 = i | i4;
        this.c = (i5 ^ i8) ^ i6;
        this.e = (i2 & i6) ^ ((i ^ i3) | i7);
    }

    private void m(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i2 ^ i5;
        int i7 = ((i ^ -1) | i5) ^ i3;
        this.d = i2 ^ i7;
        i5 = (i5 | this.d) ^ i4;
        this.e = (i7 & i5) ^ i6;
        i5 ^= i7;
        this.c = this.e ^ i5;
        this.f = (i7 ^ -1) ^ (i5 & i6);
    }

    private void n(int i, int i2, int i3, int i4) {
        int i5 = i ^ -1;
        int i6 = i ^ i2;
        int i7 = i3 ^ i6;
        int i8 = (i3 | i5) ^ i4;
        this.d = i7 ^ i8;
        i6 ^= i7 & i8;
        this.f = i8 ^ (i2 | i6);
        i8 = this.f | i2;
        this.c = i6 ^ i8;
        this.e = (i5 & i4) ^ (i7 ^ i8);
    }

    private void o(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i3;
        int i6 = (i3 & i5) ^ i4;
        int i7 = i ^ i6;
        this.d = ((i4 | i5) & i7) ^ i2;
        int i8 = this.d | i6;
        this.f = i5 ^ (i & i7);
        i5 = i7 ^ i8;
        this.e = i6 ^ (this.f & i5);
        this.c = (i5 ^ -1) ^ (this.f & this.e);
    }

    private void p(int i, int i2, int i3, int i4) {
        int i5 = (i & i2) | i3;
        int i6 = (i | i2) & i4;
        this.f = i5 ^ i6;
        i6 ^= i2;
        this.d = (((i4 ^ -1) ^ this.f) | i6) ^ i;
        this.c = (i6 ^ i3) ^ (this.d | i4);
        this.e = (i5 ^ this.d) ^ (this.c ^ (this.f & i));
    }

    private void d() {
        int a = a(this.c, 13);
        int a2 = a(this.e, 3);
        int i = (this.f ^ a2) ^ (a << 3);
        this.d = a((this.d ^ a) ^ a2, 1);
        this.f = a(i, 7);
        this.c = a((a ^ this.d) ^ this.f, 5);
        this.e = a((this.f ^ a2) ^ (this.d << 7), 22);
    }

    private void e() {
        int b = (b(this.e, 22) ^ this.f) ^ (this.d << 7);
        int b2 = (b(this.c, 5) ^ this.d) ^ this.f;
        int b3 = b(this.f, 7);
        int b4 = b(this.d, 1);
        this.f = (b3 ^ b) ^ (b2 << 3);
        this.d = (b4 ^ b2) ^ b;
        this.e = b(b, 3);
        this.c = b(b2, 13);
    }
}
