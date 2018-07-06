package org.spongycastle.crypto.params;

public class ECKeyParameters extends AsymmetricKeyParameter {
    ECDomainParameters b;

    protected ECKeyParameters(boolean z, ECDomainParameters eCDomainParameters) {
        super(z);
        this.b = eCDomainParameters;
    }

    public ECDomainParameters b() {
        return this.b;
    }
}
