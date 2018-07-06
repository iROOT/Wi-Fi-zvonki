package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters {
    public IntegerPolynomial c;

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(false, nTRUEncryptionParameters);
        this.c = integerPolynomial;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.c == null ? 0 : this.c.hashCode()) + 31) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof NTRUEncryptionPublicKeyParameters)) {
            return false;
        }
        NTRUEncryptionPublicKeyParameters nTRUEncryptionPublicKeyParameters = (NTRUEncryptionPublicKeyParameters) obj;
        if (this.c == null) {
            if (nTRUEncryptionPublicKeyParameters.c != null) {
                return false;
            }
        } else if (!this.c.equals(nTRUEncryptionPublicKeyParameters.c)) {
            return false;
        }
        if (this.b == null) {
            if (nTRUEncryptionPublicKeyParameters.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(nTRUEncryptionPublicKeyParameters.b)) {
            return true;
        } else {
            return false;
        }
    }
}
