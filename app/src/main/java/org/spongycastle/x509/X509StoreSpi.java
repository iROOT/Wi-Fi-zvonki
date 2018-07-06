package org.spongycastle.x509;

import java.util.Collection;
import org.spongycastle.util.Selector;

public abstract class X509StoreSpi {
    public abstract Collection a(Selector selector);

    public abstract void a(X509StoreParameters x509StoreParameters);
}
