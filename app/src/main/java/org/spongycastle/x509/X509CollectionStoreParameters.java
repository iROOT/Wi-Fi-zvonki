package org.spongycastle.x509;

import java.util.ArrayList;
import java.util.Collection;

public class X509CollectionStoreParameters implements X509StoreParameters {
    private Collection a;

    public X509CollectionStoreParameters(Collection collection) {
        if (collection == null) {
            throw new NullPointerException("collection cannot be null");
        }
        this.a = collection;
    }

    public Object clone() {
        return new X509CollectionStoreParameters(this.a);
    }

    public Collection a() {
        return new ArrayList(this.a);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("X509CollectionStoreParameters: [\n");
        stringBuffer.append("  collection: " + this.a + "\n");
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
