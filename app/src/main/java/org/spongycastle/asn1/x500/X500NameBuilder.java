package org.spongycastle.asn1.x500;

import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.x500.style.BCStyle;

public class X500NameBuilder {
    private X500NameStyle a;
    private Vector b;

    public X500NameBuilder() {
        this(BCStyle.K);
    }

    public X500NameBuilder(X500NameStyle x500NameStyle) {
        this.b = new Vector();
        this.a = x500NameStyle;
    }

    public X500NameBuilder a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        a(aSN1ObjectIdentifier, this.a.a(aSN1ObjectIdentifier, str));
        return this;
    }

    public X500NameBuilder a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.b.addElement(new RDN(aSN1ObjectIdentifier, aSN1Encodable));
        return this;
    }

    public X500NameBuilder a(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, String[] strArr) {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[strArr.length];
        for (int i = 0; i != aSN1EncodableArr.length; i++) {
            aSN1EncodableArr[i] = this.a.a(aSN1ObjectIdentifierArr[i], strArr[i]);
        }
        return a(aSN1ObjectIdentifierArr, aSN1EncodableArr);
    }

    public X500NameBuilder a(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, ASN1Encodable[] aSN1EncodableArr) {
        AttributeTypeAndValue[] attributeTypeAndValueArr = new AttributeTypeAndValue[aSN1ObjectIdentifierArr.length];
        for (int i = 0; i != aSN1ObjectIdentifierArr.length; i++) {
            attributeTypeAndValueArr[i] = new AttributeTypeAndValue(aSN1ObjectIdentifierArr[i], aSN1EncodableArr[i]);
        }
        return a(attributeTypeAndValueArr);
    }

    public X500NameBuilder a(AttributeTypeAndValue[] attributeTypeAndValueArr) {
        this.b.addElement(new RDN(attributeTypeAndValueArr));
        return this;
    }

    public X500Name a() {
        RDN[] rdnArr = new RDN[this.b.size()];
        for (int i = 0; i != rdnArr.length; i++) {
            rdnArr[i] = (RDN) this.b.elementAt(i);
        }
        return new X500Name(this.a, rdnArr);
    }
}
