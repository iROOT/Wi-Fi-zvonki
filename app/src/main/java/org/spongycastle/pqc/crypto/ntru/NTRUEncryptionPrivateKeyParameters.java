package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;

public class NTRUEncryptionPrivateKeyParameters extends NTRUEncryptionKeyParameters {
    public Polynomial c;
    public IntegerPolynomial d;
    public IntegerPolynomial e;

    public NTRUEncryptionPrivateKeyParameters(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(true, nTRUEncryptionParameters);
        this.e = integerPolynomial;
        this.c = polynomial;
        this.d = integerPolynomial2;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.c == null ? 0 : this.c.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + 31) * 31)) * 31;
        if (this.e != null) {
            i = this.e.hashCode();
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
        if (!(obj instanceof NTRUEncryptionPrivateKeyParameters)) {
            return false;
        }
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) obj;
        if (this.b == null) {
            if (nTRUEncryptionPrivateKeyParameters.b != null) {
                return false;
            }
        } else if (!this.b.equals(nTRUEncryptionPrivateKeyParameters.b)) {
            return false;
        }
        if (this.c == null) {
            if (nTRUEncryptionPrivateKeyParameters.c != null) {
                return false;
            }
        } else if (!this.c.equals(nTRUEncryptionPrivateKeyParameters.c)) {
            return false;
        }
        if (this.e.equals(nTRUEncryptionPrivateKeyParameters.e)) {
            return true;
        }
        return false;
    }
}
