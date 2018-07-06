package org.spongycastle.asn1.eac;

import java.util.Hashtable;

public class BidirectionalMap extends Hashtable {
    Hashtable a = new Hashtable();

    public Object put(Object obj, Object obj2) {
        this.a.put(obj2, obj);
        return super.put(obj, obj2);
    }
}
