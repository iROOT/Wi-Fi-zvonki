package org.spongycastle.crypto.params;

public class ElGamalKeyParameters extends AsymmetricKeyParameter {
    private ElGamalParameters b;

    protected ElGamalKeyParameters(boolean z, ElGamalParameters elGamalParameters) {
        super(z);
        this.b = elGamalParameters;
    }

    public ElGamalParameters b() {
        return this.b;
    }

    public int hashCode() {
        return this.b != null ? this.b.hashCode() : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ElGamalKeyParameters)) {
            return false;
        }
        ElGamalKeyParameters elGamalKeyParameters = (ElGamalKeyParameters) obj;
        if (this.b != null) {
            return this.b.equals(elGamalKeyParameters.b());
        }
        if (elGamalKeyParameters.b() == null) {
            return true;
        }
        return false;
    }
}
