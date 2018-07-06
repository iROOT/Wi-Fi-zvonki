package org.spongycastle.crypto.modes.gcm;

import java.util.Vector;
import org.spongycastle.util.Arrays;

public class Tables1kGCMExponentiator implements GCMExponentiator {
    private Vector a;

    public void a(byte[] bArr) {
        int[] a = GCMUtil.a(bArr);
        if (this.a == null || !Arrays.a(a, (int[]) this.a.elementAt(0))) {
            this.a = new Vector(8);
            this.a.addElement(a);
        }
    }

    public void a(long j, byte[] bArr) {
        int[] a = GCMUtil.a();
        int i = 0;
        while (j > 0) {
            if ((1 & j) != 0) {
                a(i);
                GCMUtil.a(a, (int[]) this.a.elementAt(i));
            }
            j >>>= 1;
            i++;
        }
        GCMUtil.a(a, bArr);
    }

    private void a(int i) {
        int size = this.a.size();
        if (size <= i) {
            int[] iArr = (int[]) this.a.elementAt(size - 1);
            do {
                iArr = Arrays.b(iArr);
                GCMUtil.a(iArr, iArr);
                this.a.addElement(iArr);
                size++;
            } while (size <= i);
        }
    }
}
