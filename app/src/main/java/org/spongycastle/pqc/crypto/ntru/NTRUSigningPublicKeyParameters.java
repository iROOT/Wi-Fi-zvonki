package org.spongycastle.pqc.crypto.ntru;

import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;

public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter {
    public IntegerPolynomial b;
    private NTRUSigningParameters c;

    public NTRUSigningPublicKeyParameters(IntegerPolynomial integerPolynomial, NTRUSigningParameters nTRUSigningParameters) {
        super(false);
        this.b = integerPolynomial;
        this.c = nTRUSigningParameters;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.b == null ? 0 : this.b.hashCode()) + 31) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = (NTRUSigningPublicKeyParameters) obj;
        if (this.b == null) {
            if (nTRUSigningPublicKeyParameters.b != null) {
                return false;
            }
        } else if (!this.b.equals(nTRUSigningPublicKeyParameters.b)) {
            return false;
        }
        if (this.c == null) {
            if (nTRUSigningPublicKeyParameters.c != null) {
                return false;
            }
            return true;
        } else if (this.c.equals(nTRUSigningPublicKeyParameters.c)) {
            return true;
        } else {
            return false;
        }
    }
}
