package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;

public class GeneralNames extends ASN1Object {
    private final GeneralName[] a;

    public static GeneralNames a(Object obj) {
        if (obj instanceof GeneralNames) {
            return (GeneralNames) obj;
        }
        if (obj != null) {
            return new GeneralNames(ASN1Sequence.a(obj));
        }
        return null;
    }

    public static GeneralNames a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return a(ASN1Sequence.a(aSN1TaggedObject, z));
    }

    public GeneralNames(GeneralName generalName) {
        this.a = new GeneralName[]{generalName};
    }

    private GeneralNames(ASN1Sequence aSN1Sequence) {
        this.a = new GeneralName[aSN1Sequence.f()];
        for (int i = 0; i != aSN1Sequence.f(); i++) {
            this.a[i] = GeneralName.a(aSN1Sequence.a(i));
        }
    }

    public GeneralName[] d() {
        Object obj = new GeneralName[this.a.length];
        System.arraycopy(this.a, 0, obj, 0, this.a.length);
        return obj;
    }

    public ASN1Primitive a() {
        return new DERSequence(this.a);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("GeneralNames:");
        stringBuffer.append(property);
        for (int i = 0; i != this.a.length; i++) {
            stringBuffer.append("    ");
            stringBuffer.append(this.a[i]);
            stringBuffer.append(property);
        }
        return stringBuffer.toString();
    }
}
