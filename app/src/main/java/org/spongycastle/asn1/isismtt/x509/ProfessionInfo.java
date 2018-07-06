package org.spongycastle.asn1.isismtt.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class ProfessionInfo extends ASN1Object {
    public static final ASN1ObjectIdentifier a = new ASN1ObjectIdentifier(NamingAuthority.a + ".1");
    public static final ASN1ObjectIdentifier b = new ASN1ObjectIdentifier(NamingAuthority.a + ".2");
    public static final ASN1ObjectIdentifier c = new ASN1ObjectIdentifier(NamingAuthority.a + ".3");
    public static final ASN1ObjectIdentifier d = new ASN1ObjectIdentifier(NamingAuthority.a + ".4");
    public static final ASN1ObjectIdentifier e = new ASN1ObjectIdentifier(NamingAuthority.a + ".5");
    public static final ASN1ObjectIdentifier f = new ASN1ObjectIdentifier(NamingAuthority.a + ".6");
    public static final ASN1ObjectIdentifier g = new ASN1ObjectIdentifier(NamingAuthority.a + ".7");
    public static final ASN1ObjectIdentifier h = new ASN1ObjectIdentifier(NamingAuthority.a + ".8");
    public static final ASN1ObjectIdentifier i = new ASN1ObjectIdentifier(NamingAuthority.a + ".9");
    public static final ASN1ObjectIdentifier j = new ASN1ObjectIdentifier(NamingAuthority.a + ".10");
    public static final ASN1ObjectIdentifier k = new ASN1ObjectIdentifier(NamingAuthority.a + ".11");
    public static final ASN1ObjectIdentifier l = new ASN1ObjectIdentifier(NamingAuthority.a + ".12");
    public static final ASN1ObjectIdentifier m = new ASN1ObjectIdentifier(NamingAuthority.a + ".13");
    public static final ASN1ObjectIdentifier n = new ASN1ObjectIdentifier(NamingAuthority.a + ".14");
    public static final ASN1ObjectIdentifier o = new ASN1ObjectIdentifier(NamingAuthority.a + ".15");
    public static final ASN1ObjectIdentifier p = new ASN1ObjectIdentifier(NamingAuthority.a + ".16");
    public static final ASN1ObjectIdentifier q = new ASN1ObjectIdentifier(NamingAuthority.a + ".17");
    public static final ASN1ObjectIdentifier r = new ASN1ObjectIdentifier(NamingAuthority.a + ".18");
    public static final ASN1ObjectIdentifier s = new ASN1ObjectIdentifier(NamingAuthority.a + ".19");
    private NamingAuthority t;
    private ASN1Sequence u;
    private ASN1Sequence v;
    private String w;
    private ASN1OctetString x;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.t != null) {
            aSN1EncodableVector.a(new DERTaggedObject(true, 0, this.t));
        }
        aSN1EncodableVector.a(this.u);
        if (this.v != null) {
            aSN1EncodableVector.a(this.v);
        }
        if (this.w != null) {
            aSN1EncodableVector.a(new DERPrintableString(this.w, true));
        }
        if (this.x != null) {
            aSN1EncodableVector.a(this.x);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
