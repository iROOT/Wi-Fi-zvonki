package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class RoleSyntax extends ASN1Object {
    private GeneralNames a;
    private GeneralName b;

    public String d() {
        return ((ASN1String) this.b.e()).a_();
    }

    public String[] e() {
        if (this.a == null) {
            return new String[0];
        }
        GeneralName[] d = this.a.d();
        String[] strArr = new String[d.length];
        for (int i = 0; i < d.length; i++) {
            ASN1Encodable e = d[i].e();
            if (e instanceof ASN1String) {
                strArr[i] = ((ASN1String) e).a_();
            } else {
                strArr[i] = e.toString();
            }
        }
        return strArr;
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.a));
        }
        aSN1EncodableVector.a(new DERTaggedObject(true, 1, this.b));
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Name: " + d() + " - Auth: ");
        if (this.a == null || this.a.d().length == 0) {
            stringBuffer.append("N/A");
        } else {
            String[] e = e();
            stringBuffer.append('[').append(e[0]);
            for (int i = 1; i < e.length; i++) {
                stringBuffer.append(", ").append(e[i]);
            }
            stringBuffer.append(']');
        }
        return stringBuffer.toString();
    }
}
