package org.spongycastle.pqc.crypto.ntru;

import java.util.ArrayList;
import java.util.List;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;

public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter {
    private List<Basis> b;
    private NTRUSigningPublicKeyParameters c;

    public static class Basis {
        public Polynomial d;
        public Polynomial e;
        public IntegerPolynomial f;
        NTRUSigningKeyGenerationParameters g;

        protected Basis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            this.d = polynomial;
            this.e = polynomial2;
            this.f = integerPolynomial;
            this.g = nTRUSigningKeyGenerationParameters;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.f == null ? 0 : this.f.hashCode()) + (((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + 31) * 31)) * 31)) * 31;
            if (this.g != null) {
                i = this.g.hashCode();
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
            if (!(obj instanceof Basis)) {
                return false;
            }
            Basis basis = (Basis) obj;
            if (this.d == null) {
                if (basis.d != null) {
                    return false;
                }
            } else if (!this.d.equals(basis.d)) {
                return false;
            }
            if (this.e == null) {
                if (basis.e != null) {
                    return false;
                }
            } else if (!this.e.equals(basis.e)) {
                return false;
            }
            if (this.f == null) {
                if (basis.f != null) {
                    return false;
                }
            } else if (!this.f.equals(basis.f)) {
                return false;
            }
            if (this.g == null) {
                if (basis.g != null) {
                    return false;
                }
                return true;
            } else if (this.g.equals(basis.g)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public NTRUSigningPrivateKeyParameters(List<Basis> list, NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters) {
        super(true);
        this.b = new ArrayList(list);
        this.c = nTRUSigningPublicKeyParameters;
    }

    public int hashCode() {
        int hashCode = (this.b == null ? 0 : this.b.hashCode()) + 31;
        int i = hashCode;
        for (Basis hashCode2 : this.b) {
            i = hashCode2.hashCode() + i;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NTRUSigningPrivateKeyParameters nTRUSigningPrivateKeyParameters = (NTRUSigningPrivateKeyParameters) obj;
        if ((this.b == null && nTRUSigningPrivateKeyParameters.b != null) || this.b.size() != nTRUSigningPrivateKeyParameters.b.size()) {
            return false;
        }
        for (int i = 0; i < this.b.size(); i++) {
            Basis basis = (Basis) this.b.get(i);
            Basis basis2 = (Basis) nTRUSigningPrivateKeyParameters.b.get(i);
            if (!basis.d.equals(basis2.d) || !basis.e.equals(basis2.e)) {
                return false;
            }
            if ((i != 0 && !basis.f.equals(basis2.f)) || !basis.g.equals(basis2.g)) {
                return false;
            }
        }
        return true;
    }
}
