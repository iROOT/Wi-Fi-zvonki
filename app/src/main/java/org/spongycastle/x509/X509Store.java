package org.spongycastle.x509;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Collection;
import org.spongycastle.util.Selector;
import org.spongycastle.util.Store;

public class X509Store implements Store {
    private Provider a;
    private X509StoreSpi b;

    public static X509Store a(String str, X509StoreParameters x509StoreParameters, String str2) {
        return a(str, x509StoreParameters, X509Util.a(str2));
    }

    public static X509Store a(String str, X509StoreParameters x509StoreParameters, Provider provider) {
        try {
            return a(X509Util.a("X509Store", str, provider), x509StoreParameters);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchStoreException(e.getMessage());
        }
    }

    private static X509Store a(Implementation implementation, X509StoreParameters x509StoreParameters) {
        X509StoreSpi x509StoreSpi = (X509StoreSpi) implementation.a();
        x509StoreSpi.a(x509StoreParameters);
        return new X509Store(implementation.b(), x509StoreSpi);
    }

    private X509Store(Provider provider, X509StoreSpi x509StoreSpi) {
        this.a = provider;
        this.b = x509StoreSpi;
    }

    public Collection a(Selector selector) {
        return this.b.a(selector);
    }
}
