package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.util.Arrays;

public class GMSSParameters {
    private int a;
    private int[] b;
    private int[] c;
    private int[] d;

    public GMSSParameters(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        a(i, iArr, iArr2, iArr3);
    }

    private void a(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        Object obj = 1;
        String str = "";
        this.a = i;
        if (!(this.a == iArr2.length && this.a == iArr.length && this.a == iArr3.length)) {
            str = "Unexpected parameterset format";
            obj = null;
        }
        Object obj2 = obj;
        String str2 = str;
        int i2 = 0;
        while (i2 < this.a) {
            if (iArr3[i2] < 2 || (iArr[i2] - iArr3[i2]) % 2 != 0) {
                str2 = "Wrong parameter K (K >= 2 and H-K even required)!";
                obj2 = null;
            }
            if (iArr[i2] < 4 || iArr2[i2] < 2) {
                str2 = "Wrong parameter H or w (H > 3 and w > 1 required)!";
                obj2 = null;
            }
            i2++;
        }
        if (obj2 != null) {
            this.b = Arrays.b(iArr);
            this.c = Arrays.b(iArr2);
            this.d = Arrays.b(iArr3);
            return;
        }
        throw new IllegalArgumentException(str2);
    }

    public int a() {
        return this.a;
    }

    public int[] b() {
        return Arrays.b(this.b);
    }

    public int[] c() {
        return Arrays.b(this.c);
    }

    public int[] d() {
        return Arrays.b(this.d);
    }
}
