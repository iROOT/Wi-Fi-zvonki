package org.spongycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import org.spongycastle.jce.X509LDAPCertStoreParameters;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.X509CertPairStoreSelector;
import org.spongycastle.x509.X509StoreParameters;
import org.spongycastle.x509.X509StoreSpi;
import org.spongycastle.x509.util.LDAPStoreHelper;

public class X509StoreLDAPCertPairs extends X509StoreSpi {
    private LDAPStoreHelper a;

    public void a(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509LDAPCertStoreParameters) {
            this.a = new LDAPStoreHelper((X509LDAPCertStoreParameters) x509StoreParameters);
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
    }

    public Collection a(Selector selector) {
        if (!(selector instanceof X509CertPairStoreSelector)) {
            return Collections.EMPTY_SET;
        }
        X509CertPairStoreSelector x509CertPairStoreSelector = (X509CertPairStoreSelector) selector;
        Collection hashSet = new HashSet();
        hashSet.addAll(this.a.a(x509CertPairStoreSelector));
        return hashSet;
    }
}
