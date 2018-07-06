package org.spongycastle.asn1;

import java.util.Enumeration;

class LazyConstructionEnumeration implements Enumeration {
    private ASN1InputStream a;
    private Object b = a();

    public LazyConstructionEnumeration(byte[] bArr) {
        this.a = new ASN1InputStream(bArr, true);
    }

    public boolean hasMoreElements() {
        return this.b != null;
    }

    public Object nextElement() {
        Object obj = this.b;
        this.b = a();
        return obj;
    }

    private Object a() {
        try {
            return this.a.d();
        } catch (Throwable e) {
            throw new ASN1ParsingException("malformed DER construction: " + e, e);
        }
    }
}
