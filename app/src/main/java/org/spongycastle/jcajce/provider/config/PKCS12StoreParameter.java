package org.spongycastle.jcajce.provider.config;

import java.io.OutputStream;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStore.ProtectionParameter;

public class PKCS12StoreParameter implements LoadStoreParameter {
    private final OutputStream a;
    private final ProtectionParameter b;
    private final boolean c;

    public PKCS12StoreParameter(OutputStream outputStream, ProtectionParameter protectionParameter, boolean z) {
        this.a = outputStream;
        this.b = protectionParameter;
        this.c = z;
    }

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
