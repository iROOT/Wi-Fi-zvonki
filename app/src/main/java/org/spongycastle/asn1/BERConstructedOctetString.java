package org.spongycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;

public class BERConstructedOctetString extends BEROctetString {
    private Vector b;

    public byte[] e() {
        return this.a;
    }

    public Enumeration k() {
        if (this.b == null) {
            return l().elements();
        }
        return this.b.elements();
    }

    private Vector l() {
        Vector vector = new Vector();
        for (int i = 0; i < this.a.length; i += 1000) {
            int length;
            if (i + 1000 > this.a.length) {
                length = this.a.length;
            } else {
                length = i + 1000;
            }
            Object obj = new byte[(length - i)];
            System.arraycopy(this.a, i, obj, 0, obj.length);
            vector.addElement(new DEROctetString(obj));
        }
        return vector;
    }
}
