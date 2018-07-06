package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERBoolean;

public class IssuingDistributionPoint extends ASN1Object {
    private DistributionPointName a;
    private boolean b;
    private boolean c;
    private ReasonFlags d;
    private boolean e;
    private boolean f;
    private ASN1Sequence g;

    public static IssuingDistributionPoint a(Object obj) {
        if (obj instanceof IssuingDistributionPoint) {
            return (IssuingDistributionPoint) obj;
        }
        if (obj != null) {
            return new IssuingDistributionPoint(ASN1Sequence.a(obj));
        }
        return null;
    }

    private IssuingDistributionPoint(ASN1Sequence aSN1Sequence) {
        this.g = aSN1Sequence;
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            ASN1TaggedObject a = ASN1TaggedObject.a(aSN1Sequence.a(i));
            switch (a.d()) {
                case 0:
                    this.a = DistributionPointName.a(a, true);
                    break;
                case 1:
                    this.b = DERBoolean.a(a, false).d();
                    break;
                case 2:
                    this.c = DERBoolean.a(a, false).d();
                    break;
                case 3:
                    this.d = new ReasonFlags(DERBitString.a(a, false));
                    break;
                case 4:
                    this.e = DERBoolean.a(a, false).d();
                    break;
                case 5:
                    this.f = DERBoolean.a(a, false).d();
                    break;
                default:
                    throw new IllegalArgumentException("unknown tag in IssuingDistributionPoint");
            }
        }
    }

    public boolean d() {
        return this.b;
    }

    public boolean e() {
        return this.c;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f;
    }

    public DistributionPointName h() {
        return this.a;
    }

    public ReasonFlags i() {
        return this.d;
    }

    public ASN1Primitive a() {
        return this.g;
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("IssuingDistributionPoint: [");
        stringBuffer.append(property);
        if (this.a != null) {
            a(stringBuffer, property, "distributionPoint", this.a.toString());
        }
        if (this.b) {
            a(stringBuffer, property, "onlyContainsUserCerts", a(this.b));
        }
        if (this.c) {
            a(stringBuffer, property, "onlyContainsCACerts", a(this.c));
        }
        if (this.d != null) {
            a(stringBuffer, property, "onlySomeReasons", this.d.toString());
        }
        if (this.f) {
            a(stringBuffer, property, "onlyContainsAttributeCerts", a(this.f));
        }
        if (this.e) {
            a(stringBuffer, property, "indirectCRL", a(this.e));
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

    private String a(boolean z) {
        return z ? "true" : "false";
    }
}
