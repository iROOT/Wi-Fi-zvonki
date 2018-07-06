package org.spongycastle.crypto.params;

import org.spongycastle.util.Arrays;

public class DHValidationParameters {
    private byte[] a;
    private int b;

    public DHValidationParameters(byte[] bArr, int i) {
        this.a = bArr;
        this.b = i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHValidationParameters)) {
            return false;
        }
        DHValidationParameters dHValidationParameters = (DHValidationParameters) obj;
        if (dHValidationParameters.b == this.b) {
            return Arrays.a(this.a, dHValidationParameters.a);
        }
        return false;
    }

    public int hashCode() {
        return this.b ^ Arrays.a(this.a);
    }
}
