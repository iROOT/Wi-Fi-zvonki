package org.spongycastle.pqc.crypto.rainbow;

import org.spongycastle.crypto.CipherParameters;

public class RainbowParameters implements CipherParameters {
    private final int[] a;
    private int[] b;

    public RainbowParameters() {
        this.a = new int[]{6, 12, 17, 22, 33};
        this.b = this.a;
    }

    public RainbowParameters(int[] iArr) {
        this.a = new int[]{6, 12, 17, 22, 33};
        this.b = iArr;
        try {
            c();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c() {
        if (this.b == null) {
            throw new Exception("no layers defined.");
        } else if (this.b.length > 1) {
            for (int i = 0; i < this.b.length - 1; i++) {
                if (this.b[i] >= this.b[i + 1]) {
                    throw new Exception("v[i] has to be smaller than v[i+1]");
                }
            }
        } else {
            throw new Exception("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
    }

    public int a() {
        return this.b.length - 1;
    }

    public int[] b() {
        return this.b;
    }
}
