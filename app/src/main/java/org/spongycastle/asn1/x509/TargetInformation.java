package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;

public class TargetInformation extends ASN1Object {
    private ASN1Sequence a;

    public static TargetInformation a(Object obj) {
        if (obj instanceof TargetInformation) {
            return (TargetInformation) obj;
        }
        if (obj != null) {
            return new TargetInformation(ASN1Sequence.a(obj));
        }
        return null;
    }

    private TargetInformation(ASN1Sequence aSN1Sequence) {
        this.a = aSN1Sequence;
    }

    public Targets[] d() {
        Targets[] targetsArr = new Targets[this.a.f()];
        int i = 0;
        Enumeration e = this.a.e();
        while (e.hasMoreElements()) {
            int i2 = i + 1;
            targetsArr[i] = Targets.a(e.nextElement());
            i = i2;
        }
        return targetsArr;
    }

    public ASN1Primitive a() {
        return this.a;
    }
}
