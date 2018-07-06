package org.spongycastle.ocsp;

import org.spongycastle.asn1.ocsp.ResponderID;

public class RespID {
    ResponderID a;

    public boolean equals(Object obj) {
        if (!(obj instanceof RespID)) {
            return false;
        }
        return this.a.equals(((RespID) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
