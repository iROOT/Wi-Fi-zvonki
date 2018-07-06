package org.spongycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.spongycastle.jce.X509LDAPCertStoreParameters;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.X509AttributeCertStoreSelector;
import org.spongycastle.x509.X509StoreParameters;
import org.spongycastle.x509.X509StoreSpi;
import org.spongycastle.x509.util.LDAPStoreHelper;

public class X509StoreLDAPAttrCerts extends X509StoreSpi {
    private LDAPStoreHelper a;

    public void a(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509LDAPCertStoreParameters) {
            this.a = new LDAPStoreHelper((X509LDAPCertStoreParameters) x509StoreParameters);
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
    }

    public Collection a(Selector selector) {
        if (!(selector instanceof X509AttributeCertStoreSelector)) {
            return Collections.EMPTY_SET;
        }
        X509AttributeCertStoreSelector x509AttributeCertStoreSelector = (X509AttributeCertStoreSelector) selector;
        Collection hashSet = new HashSet();
        hashSet.addAll(this.a.a(x509AttributeCertStoreSelector));
        hashSet.addAll(this.a.c(x509AttributeCertStoreSelector));
        hashSet.addAll(this.a.b(x509AttributeCertStoreSelector));
        return hashSet;
    }
}
