package org.spongycastle.asn1.x509;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class V2TBSCertListGenerator {
    private static final ASN1Sequence[] e = new ASN1Sequence[11];
    private ASN1Integer a = new ASN1Integer(1);
    private Time b = null;
    private Extensions c = null;
    private ASN1EncodableVector d = new ASN1EncodableVector();

    static {
        e[0] = a(0);
        e[1] = a(1);
        e[2] = a(2);
        e[3] = a(3);
        e[4] = a(4);
        e[5] = a(5);
        e[6] = a(6);
        e[7] = a(7);
        e[8] = a(8);
        e[9] = a(9);
        e[10] = a(10);
    }

    private static ASN1Sequence a(int i) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        CRLReason a = CRLReason.a(i);
        try {
            aSN1EncodableVector.a(Extension.i);
            aSN1EncodableVector.a(new DEROctetString(a.b()));
            return new DERSequence(aSN1EncodableVector);
        } catch (IOException e) {
            throw new IllegalArgumentException("error encoding reason: " + e);
        }
    }
}
