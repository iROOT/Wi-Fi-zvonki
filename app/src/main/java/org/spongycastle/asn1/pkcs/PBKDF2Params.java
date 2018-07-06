package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class PBKDF2Params extends ASN1Object {
    private static final AlgorithmIdentifier a = new AlgorithmIdentifier(PKCSObjectIdentifiers.I, DERNull.a);
    private ASN1OctetString b;
    private ASN1Integer c;
    private ASN1Integer d;
    private AlgorithmIdentifier e;

    public static PBKDF2Params a(Object obj) {
        if (obj instanceof PBKDF2Params) {
            return (PBKDF2Params) obj;
        }
        if (obj != null) {
            return new PBKDF2Params(ASN1Sequence.a(obj));
        }
        return null;
    }

    public PBKDF2Params(byte[] bArr, int i) {
        this.b = new DEROctetString(bArr);
        this.c = new ASN1Integer((long) i);
    }

    private PBKDF2Params(ASN1Sequence aSN1Sequence) {
        Enumeration e = aSN1Sequence.e();
        this.b = (ASN1OctetString) e.nextElement();
        this.c = (ASN1Integer) e.nextElement();
        if (e.hasMoreElements()) {
            Object nextElement = e.nextElement();
            if (nextElement instanceof ASN1Integer) {
                this.d = DERInteger.a(nextElement);
                if (e.hasMoreElements()) {
                    nextElement = e.nextElement();
                } else {
                    nextElement = null;
                }
            } else {
                this.d = null;
            }
            if (nextElement != null) {
                this.e = AlgorithmIdentifier.a(nextElement);
            }
        }
    }

    public byte[] d() {
        return this.b.e();
    }

    public BigInteger e() {
        return this.c.d();
    }

    public boolean f() {
        return this.e == null || this.e.equals(a);
    }

    public AlgorithmIdentifier g() {
        if (this.e != null) {
            return this.e;
        }
        return a;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        if (this.d != null) {
            aSN1EncodableVector.a(this.d);
        }
        if (!(this.e == null || this.e.equals(a))) {
            aSN1EncodableVector.a(this.e);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
