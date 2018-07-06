package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class DistributionPoint extends ASN1Object {
    DistributionPointName a;
    ReasonFlags b;
    GeneralNames c;

    public static DistributionPoint a(Object obj) {
        if (obj == null || (obj instanceof DistributionPoint)) {
            return (DistributionPoint) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new DistributionPoint((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid DistributionPoint: " + obj.getClass().getName());
    }

    public DistributionPoint(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            ASN1TaggedObject a = ASN1TaggedObject.a(aSN1Sequence.a(i));
            switch (a.d()) {
                case 0:
                    this.a = DistributionPointName.a(a, true);
                    break;
                case 1:
                    this.b = new ReasonFlags(DERBitString.a(a, false));
                    break;
                case 2:
                    this.c = GeneralNames.a(a, false);
                    break;
                default:
                    break;
            }
        }
    }

    public DistributionPoint(DistributionPointName distributionPointName, ReasonFlags reasonFlags, GeneralNames generalNames) {
        this.a = distributionPointName;
        this.b = reasonFlags;
        this.c = generalNames;
    }

    public DistributionPointName d() {
        return this.a;
    }

    public ReasonFlags e() {
        return this.b;
    }

    public GeneralNames f() {
        return this.c;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(0, this.a));
        }
        if (this.b != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.b));
        }
        if (this.c != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 2, this.c));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPoint: [");
        stringBuffer.append(property);
        if (this.a != null) {
            a(stringBuffer, property, "distributionPoint", this.a.toString());
        }
        if (this.b != null) {
            a(stringBuffer, property, "reasons", this.b.toString());
        }
        if (this.c != null) {
            a(stringBuffer, property, "cRLIssuer", this.c.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(property);
        return stringBuffer.toString();
    }

    private void a(StringBuffer stringBuffer, String str, String str2, String str3) {
        String str4 = "    ";
        stringBuffer.append(str4);
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append(str4);
        stringBuffer.append(str4);
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }
}
