package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

public class DistributionPointName extends ASN1Object implements ASN1Choice {
    ASN1Encodable a;
    int b;

    public static DistributionPointName a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1TaggedObject.a(aSN1TaggedObject, true));
    }

    public static DistributionPointName a(Object obj) {
        if (obj == null || (obj instanceof DistributionPointName)) {
            return (DistributionPointName) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new DistributionPointName((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public DistributionPointName(int i, ASN1Encodable aSN1Encodable) {
        this.b = i;
        this.a = aSN1Encodable;
    }

    public int d() {
        return this.b;
    }

    public ASN1Encodable e() {
        return this.a;
    }

    public DistributionPointName(ASN1TaggedObject aSN1TaggedObject) {
        this.b = aSN1TaggedObject.d();
        if (this.b == 0) {
            this.a = GeneralNames.a(aSN1TaggedObject, false);
        } else {
            this.a = ASN1Set.a(aSN1TaggedObject, false);
        }
    }

    public ASN1Primitive a() {
        return new DERTaggedObject(false, this.b, this.a);
    }

    public String toString() {
        String property = System.getProperty("line.separator");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPointName: [");
        stringBuffer.append(property);
        if (this.b == 0) {
            a(stringBuffer, property, "fullName", this.a.toString());
        } else {
            a(stringBuffer, property, "nameRelativeToCRLIssuer", this.a.toString());
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
