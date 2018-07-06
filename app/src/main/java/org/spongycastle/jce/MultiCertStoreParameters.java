package org.spongycastle.jce;

import java.security.cert.CertStoreParameters;
import java.util.Collection;

public class MultiCertStoreParameters implements CertStoreParameters {
    private Collection a;
    private boolean b;

    public Collection a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public Object clone() {
        return this;
    }
}
