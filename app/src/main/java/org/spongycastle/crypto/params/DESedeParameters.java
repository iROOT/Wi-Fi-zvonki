package org.spongycastle.crypto.params;

public class DESedeParameters extends DESParameters {
    public static boolean a(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (DESParameters.a(bArr, i)) {
                return true;
            }
            i += 8;
        }
        return false;
    }
}
