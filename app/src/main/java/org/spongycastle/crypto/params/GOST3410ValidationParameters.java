package org.spongycastle.crypto.params;

public class GOST3410ValidationParameters {
    private int a;
    private int b;
    private long c;
    private long d;

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410ValidationParameters)) {
            return false;
        }
        GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
        if (gOST3410ValidationParameters.b == this.b && gOST3410ValidationParameters.a == this.a && gOST3410ValidationParameters.d == this.d && gOST3410ValidationParameters.c == this.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.a ^ this.b) ^ ((int) this.c)) ^ ((int) (this.c >> 32))) ^ ((int) this.d)) ^ ((int) (this.d >> 32));
    }
}
