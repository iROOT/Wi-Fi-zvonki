package org.spongycastle.asn1.x500.style;

import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameStyle;

public class BCStrictStyle extends BCStyle {
    public static final X500NameStyle a = new BCStrictStyle();

    public boolean a(X500Name x500Name, X500Name x500Name2) {
        RDN[] d = x500Name.d();
        RDN[] d2 = x500Name2.d();
        if (d.length != d2.length) {
            return false;
        }
        for (int i = 0; i != d.length; i++) {
            if (!a(d[i], d2[i])) {
                return false;
            }
        }
        return true;
    }
}
