package org.spongycastle.jce.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.jce.X509LDAPCertStoreParameters;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.X509CertPairStoreSelector;
import org.spongycastle.x509.X509CertStoreSelector;
import org.spongycastle.x509.X509CertificatePair;
import org.spongycastle.x509.X509StoreParameters;
import org.spongycastle.x509.X509StoreSpi;
import org.spongycastle.x509.util.LDAPStoreHelper;

public class X509StoreLDAPCerts extends X509StoreSpi {
    private LDAPStoreHelper a;

    public void a(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509LDAPCertStoreParameters) {
            this.a = new LDAPStoreHelper((X509LDAPCertStoreParameters) x509StoreParameters);
            return;
        }
        throw new IllegalArgumentException("Initialization parameters must be an instance of " + X509LDAPCertStoreParameters.class.getName() + ".");
    }

    public Collection a(Selector selector) {
        if (!(selector instanceof X509CertStoreSelector)) {
            return Collections.EMPTY_SET;
        }
        X509CertStoreSelector x509CertStoreSelector = (X509CertStoreSelector) selector;
        Collection hashSet = new HashSet();
        if (x509CertStoreSelector.getBasicConstraints() > 0) {
            hashSet.addAll(this.a.b(x509CertStoreSelector));
            hashSet.addAll(a(x509CertStoreSelector));
            return hashSet;
        } else if (x509CertStoreSelector.getBasicConstraints() == -2) {
            hashSet.addAll(this.a.a(x509CertStoreSelector));
            return hashSet;
        } else {
            hashSet.addAll(this.a.a(x509CertStoreSelector));
            hashSet.addAll(this.a.b(x509CertStoreSelector));
            hashSet.addAll(a(x509CertStoreSelector));
            return hashSet;
        }
    }

    private Collection a(X509CertStoreSelector x509CertStoreSelector) {
        Collection hashSet = new HashSet();
        X509CertPairStoreSelector x509CertPairStoreSelector = new X509CertPairStoreSelector();
        x509CertPairStoreSelector.a(x509CertStoreSelector);
        x509CertPairStoreSelector.b(new X509CertStoreSelector());
        Set<X509CertificatePair> hashSet2 = new HashSet(this.a.a(x509CertPairStoreSelector));
        Collection hashSet3 = new HashSet();
        Collection hashSet4 = new HashSet();
        for (X509CertificatePair x509CertificatePair : hashSet2) {
            if (x509CertificatePair.a() != null) {
                hashSet3.add(x509CertificatePair.a());
            }
            if (x509CertificatePair.b() != null) {
                hashSet4.add(x509CertificatePair.b());
            }
        }
        hashSet.addAll(hashSet3);
        hashSet.addAll(hashSet4);
        return hashSet;
    }
}
