package org.spongycastle.crypto.params;

import org.spongycastle.util.Arrays;

public class DSAValidationParameters {
    private int a;
    private byte[] b;
    private int c;

    public DSAValidationParameters(byte[] bArr, int i) {
        this(bArr, i, -1);
    }

    public DSAValidationParameters(byte[] bArr, int i, int i2) {
        this.b = bArr;
        this.c = i;
        this.a = i2;
    }

    public int hashCode() {
        return this.c ^ Arrays.a(this.b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAValidationParameters)) {
            return false;
        }
        DSAValidationParameters dSAValidationParameters = (DSAValidationParameters) obj;
        if (dSAValidationParameters.c == this.c) {
            return Arrays.a(this.b, dSAValidationParameters.b);
        }
        return false;
    }
}
