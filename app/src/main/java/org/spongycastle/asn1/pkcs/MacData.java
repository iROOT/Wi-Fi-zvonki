package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.DigestInfo;

public class MacData extends ASN1Object {
    private static final BigInteger d = BigInteger.valueOf(1);
    DigestInfo a;
    byte[] b;
    BigInteger c;

    public static MacData a(Object obj) {
        if (obj instanceof MacData) {
            return (MacData) obj;
        }
        if (obj != null) {
            return new MacData(ASN1Sequence.a(obj));
        }
        return null;
    }

    private MacData(ASN1Sequence aSN1Sequence) {
        this.a = DigestInfo.a(aSN1Sequence.a(0));
        this.b = ((ASN1OctetString) aSN1Sequence.a(1)).e();
        if (aSN1Sequence.f() == 3) {
            this.c = ((ASN1Integer) aSN1Sequence.a(2)).d();
        } else {
            this.c = d;
        }
    }

    public MacData(DigestInfo digestInfo, byte[] bArr, int i) {
        this.a = digestInfo;
        this.b = bArr;
        this.c = BigInteger.valueOf((long) i);
    }

    public DigestInfo d() {
        return this.a;
    }

    public byte[] e() {
        return this.b;
    }

    public BigInteger f() {
        return this.c;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new DEROctetString(this.b));
        if (!this.c.equals(d)) {
            aSN1EncodableVector.a(new ASN1Integer(this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
