package org.spongycastle.asn1.eac;

import android.support.v4.app.NotificationCompat;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.util.Integers;

public class CertificateHolderAuthorization extends ASN1Object {
    public static final ASN1ObjectIdentifier c = EACObjectIdentifiers.a.b("3.1.2.1");
    static Hashtable d = new Hashtable();
    static BidirectionalMap e = new BidirectionalMap();
    static Hashtable f = new Hashtable();
    ASN1ObjectIdentifier a;
    DERApplicationSpecific b;

    static {
        d.put(Integers.a(2), "RADG4");
        d.put(Integers.a(1), "RADG3");
        e.put(Integers.a(192), "CVCA");
        e.put(Integers.a(NotificationCompat.FLAG_HIGH_PRIORITY), "DV_DOMESTIC");
        e.put(Integers.a(64), "DV_FOREIGN");
        e.put(Integers.a(0), "IS");
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(this.b);
        return new DERApplicationSpecific(76, aSN1EncodableVector);
    }
}
