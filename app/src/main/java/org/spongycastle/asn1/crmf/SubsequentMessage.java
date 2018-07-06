package org.spongycastle.asn1.crmf;

import org.spongycastle.asn1.ASN1Integer;

public class SubsequentMessage extends ASN1Integer {
    public static final SubsequentMessage b = new SubsequentMessage(0);
    public static final SubsequentMessage c = new SubsequentMessage(1);

    private SubsequentMessage(int i) {
        super((long) i);
    }
}
