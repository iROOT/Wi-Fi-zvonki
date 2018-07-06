package org.spongycastle.jcajce.provider.asymmetric.util;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class PKCS12BagAttributeCarrierImpl implements PKCS12BagAttributeCarrier {
    private Hashtable a;
    private Vector b;

    PKCS12BagAttributeCarrierImpl(Hashtable hashtable, Vector vector) {
        this.a = hashtable;
        this.b = vector;
    }

    public PKCS12BagAttributeCarrierImpl() {
        this(new Hashtable(), new Vector());
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        if (this.a.containsKey(aSN1ObjectIdentifier)) {
            this.a.put(aSN1ObjectIdentifier, aSN1Encodable);
            return;
        }
        this.a.put(aSN1ObjectIdentifier, aSN1Encodable);
        this.b.addElement(aSN1ObjectIdentifier);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (ASN1Encodable) this.a.get(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.b.elements();
    }
}
