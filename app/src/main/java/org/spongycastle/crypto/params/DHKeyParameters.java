package org.spongycastle.crypto.params;

public class DHKeyParameters extends AsymmetricKeyParameter {
    private DHParameters b;

    protected DHKeyParameters(boolean z, DHParameters dHParameters) {
        super(z);
        this.b = dHParameters;
    }

    public DHParameters b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHKeyParameters)) {
            return false;
        }
        DHKeyParameters dHKeyParameters = (DHKeyParameters) obj;
        if (this.b != null) {
            return this.b.equals(dHKeyParameters.b());
        }
        if (dHKeyParameters.b() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = a() ? 0 : 1;
        if (this.b != null) {
            return i ^ this.b.hashCode();
        }
        return i;
    }
}
