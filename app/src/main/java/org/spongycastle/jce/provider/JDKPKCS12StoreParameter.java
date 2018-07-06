package org.spongycastle.jce.provider;

import java.io.OutputStream;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStore.ProtectionParameter;

public class JDKPKCS12StoreParameter implements LoadStoreParameter {
    private OutputStream a;
    private ProtectionParameter b;
    private boolean c;

    public OutputStream a() {
        return this.a;
    }

    public ProtectionParameter getProtectionParameter() {
        return this.b;
    }

    public boolean b() {
        return this.c;
    }
}
