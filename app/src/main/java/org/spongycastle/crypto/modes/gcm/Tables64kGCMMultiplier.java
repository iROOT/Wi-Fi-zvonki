package org.spongycastle.crypto.modes.gcm;

import android.support.v4.app.NotificationCompat;
import java.lang.reflect.Array;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Arrays;

public class Tables64kGCMMultiplier implements GCMMultiplier {
    private byte[] a;
    private int[][][] b;

    public void a(byte[] bArr) {
        int i;
        if (this.b == null) {
            this.b = (int[][][]) Array.newInstance(Integer.TYPE, new int[]{16, 256, 4});
        } else if (Arrays.a(this.a, bArr)) {
            return;
        }
        this.a = Arrays.b(bArr);
        GCMUtil.a(bArr, this.b[0][NotificationCompat.FLAG_HIGH_PRIORITY]);
        for (i = 64; i >= 1; i >>= 1) {
            GCMUtil.b(this.b[0][i + i], this.b[0][i]);
        }
        i = 0;
        while (true) {
            for (int i2 = 2; i2 < 256; i2 += i2) {
                int i3;
                for (i3 = 1; i3 < i2; i3++) {
                    GCMUtil.a(this.b[i][i2], this.b[i][i3], this.b[i][i2 + i3]);
                }
            }
            i++;
            if (i != 16) {
                for (i3 = NotificationCompat.FLAG_HIGH_PRIORITY; i3 > 0; i3 >>= 1) {
                    GCMUtil.c(this.b[i - 1][i3], this.b[i][i3]);
                }
            } else {
                return;
            }
        }
    }

    public void b(byte[] bArr) {
        int[] iArr = new int[4];
        for (int i = 15; i >= 0; i--) {
            int[] iArr2 = this.b[i][bArr[i] & 255];
            iArr[0] = iArr[0] ^ iArr2[0];
            iArr[1] = iArr[1] ^ iArr2[1];
            iArr[2] = iArr[2] ^ iArr2[2];
            iArr[3] = iArr2[3] ^ iArr[3];
        }
        Pack.a(iArr, bArr, 0);
    }
}
