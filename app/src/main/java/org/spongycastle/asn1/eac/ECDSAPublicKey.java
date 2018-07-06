package org.spongycastle.asn1.eac;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class ECDSAPublicKey extends PublicKeyDataObject {
    private ASN1ObjectIdentifier a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private byte[] e;
    private BigInteger f;
    private byte[] g;
    private BigInteger h;
    private int i;

    public byte[] d() {
        if ((this.i & 8) != 0) {
            return this.e;
        }
        return null;
    }

    public BigInteger e() {
        if ((this.i & 64) != 0) {
            return this.h;
        }
        return null;
    }

    public BigInteger f() {
        if ((this.i & 2) != 0) {
            return this.c;
        }
        return null;
    }

    public BigInteger g() {
        if ((this.i & 16) != 0) {
            return this.f;
        }
        return null;
    }

    public BigInteger h() {
        if ((this.i & 1) != 0) {
            return this.b;
        }
        return null;
    }

    public byte[] i() {
        if ((this.i & 32) != 0) {
            return this.g;
        }
        return null;
    }

    public BigInteger j() {
        if ((this.i & 4) != 0) {
            return this.d;
        }
        return null;
    }

    public ASN1EncodableVector a(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a((ASN1Encodable) aSN1ObjectIdentifier);
        if (!z) {
            aSN1EncodableVector.a(new UnsignedInteger(1, h()));
            aSN1EncodableVector.a(new UnsignedInteger(2, f()));
            aSN1EncodableVector.a(new UnsignedInteger(3, j()));
            aSN1EncodableVector.a(new DERTaggedObject(false, 4, new DEROctetString(d())));
            aSN1EncodableVector.a(new UnsignedInteger(5, g()));
        }
        aSN1EncodableVector.a(new DERTaggedObject(false, 6, new DEROctetString(i())));
        if (!z) {
            aSN1EncodableVector.a(new UnsignedInteger(7, e()));
        }
        return aSN1EncodableVector;
    }

    public ASN1Primitive a() {
        return new DERSequence(a(this.a, false));
    }
}
