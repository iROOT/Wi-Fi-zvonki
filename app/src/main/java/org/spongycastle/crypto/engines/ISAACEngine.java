package org.spongycastle.crypto.engines;

import android.support.v4.app.NotificationCompat;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.util.Pack;

public class ISAACEngine implements StreamCipher {
    private final int a = 8;
    private final int b = 256;
    private int[] c = null;
    private int[] d = null;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private byte[] i = new byte[k.FEEDBACK_FAILED_TITLE_ID];
    private byte[] j = null;
    private boolean k = false;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            a(((KeyParameter) cipherParameters).a());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + cipherParameters.getClass().getName());
    }

    public byte a(byte b) {
        if (this.h == 0) {
            c();
            this.i = Pack.a(this.d);
        }
        byte b2 = (byte) (this.i[this.h] ^ b);
        this.h = (this.h + 1) & 1023;
        return b2;
    }

    public void a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.k) {
            throw new IllegalStateException(a() + " not initialised");
        } else if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        } else if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.h == 0) {
                    c();
                    this.i = Pack.a(this.d);
                }
                bArr2[i4 + i3] = (byte) (this.i[this.h] ^ bArr[i4 + i]);
                this.h = (this.h + 1) & 1023;
            }
        }
    }

    public String a() {
        return "ISAAC";
    }

    public void b() {
        a(this.j);
    }

    private void a(byte[] bArr) {
        int i;
        this.j = bArr;
        if (this.c == null) {
            this.c = new int[256];
        }
        if (this.d == null) {
            this.d = new int[256];
        }
        for (i = 0; i < 256; i++) {
            int[] iArr = this.c;
            this.d[i] = 0;
            iArr[i] = 0;
        }
        this.g = 0;
        this.f = 0;
        this.e = 0;
        this.h = 0;
        Object obj = new byte[(bArr.length + (bArr.length & 3))];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        for (i = 0; i < obj.length; i += 4) {
            this.d[i >>> 2] = Pack.c(obj, i);
        }
        int[] iArr2 = new int[8];
        for (i = 0; i < 8; i++) {
            iArr2[i] = -1640531527;
        }
        for (i = 0; i < 4; i++) {
            a(iArr2);
        }
        int i2 = 0;
        while (i2 < 2) {
            for (int i3 = 0; i3 < 256; i3 += 8) {
                for (int i4 = 0; i4 < 8; i4++) {
                    iArr2[i4] = (i2 < 1 ? this.d[i3 + i4] : this.c[i3 + i4]) + iArr2[i4];
                }
                a(iArr2);
                for (i = 0; i < 8; i++) {
                    this.c[i3 + i] = iArr2[i];
                }
            }
            i2++;
        }
        c();
        this.k = true;
    }

    private void c() {
        int i = this.f;
        int i2 = this.g + 1;
        this.g = i2;
        this.f = i + i2;
        for (i = 0; i < 256; i++) {
            i2 = this.c[i];
            switch (i & 3) {
                case 0:
                    this.e ^= this.e << 13;
                    break;
                case 1:
                    this.e ^= this.e >>> 6;
                    break;
                case 2:
                    this.e ^= this.e << 2;
                    break;
                case 3:
                    this.e ^= this.e >>> 16;
                    break;
                default:
                    break;
            }
            this.e += this.c[(i + NotificationCompat.FLAG_HIGH_PRIORITY) & 255];
            int i3 = (this.c[(i2 >>> 2) & 255] + this.e) + this.f;
            this.c[i] = i3;
            int[] iArr = this.d;
            i2 += this.c[(i3 >>> 10) & 255];
            this.f = i2;
            iArr[i] = i2;
        }
    }

    private void a(int[] iArr) {
        iArr[0] = iArr[0] ^ (iArr[1] << 11);
        iArr[3] = iArr[3] + iArr[0];
        iArr[1] = iArr[1] + iArr[2];
        iArr[1] = iArr[1] ^ (iArr[2] >>> 2);
        iArr[4] = iArr[4] + iArr[1];
        iArr[2] = iArr[2] + iArr[3];
        iArr[2] = iArr[2] ^ (iArr[3] << 8);
        iArr[5] = iArr[5] + iArr[2];
        iArr[3] = iArr[3] + iArr[4];
        iArr[3] = iArr[3] ^ (iArr[4] >>> 16);
        iArr[6] = iArr[6] + iArr[3];
        iArr[4] = iArr[4] + iArr[5];
        iArr[4] = iArr[4] ^ (iArr[5] << 10);
        iArr[7] = iArr[7] + iArr[4];
        iArr[5] = iArr[5] + iArr[6];
        iArr[5] = iArr[5] ^ (iArr[6] >>> 4);
        iArr[0] = iArr[0] + iArr[5];
        iArr[6] = iArr[6] + iArr[7];
        iArr[6] = iArr[6] ^ (iArr[7] << 8);
        iArr[1] = iArr[1] + iArr[6];
        iArr[7] = iArr[7] + iArr[0];
        iArr[7] = iArr[7] ^ (iArr[0] >>> 9);
        iArr[2] = iArr[2] + iArr[7];
        iArr[0] = iArr[0] + iArr[1];
    }
}
