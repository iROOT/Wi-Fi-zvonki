package org.spongycastle.asn1.dvcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.PolicyInformation;

public class DVCSRequestInformation extends ASN1Object {
    private int a;
    private ServiceType b;
    private BigInteger c;
    private DVCSTime d;
    private GeneralNames e;
    private PolicyInformation f;
    private GeneralNames g;
    private GeneralNames h;
    private Extensions i;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != 1) {
            aSN1EncodableVector.a(new ASN1Integer((long) this.a));
        }
        aSN1EncodableVector.a(this.b);
        if (this.c != null) {
            aSN1EncodableVector.a(new ASN1Integer(this.c));
        }
        if (this.d != null) {
            aSN1EncodableVector.a(this.d);
        }
        int[] iArr = new int[]{0, 1, 2, 3, 4};
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[]{this.e, this.f, this.g, this.h, this.i};
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            ASN1Encodable aSN1Encodable = aSN1EncodableArr[i];
            if (aSN1Encodable != null) {
                aSN1EncodableVector.a(new DERTaggedObject(false, i2, aSN1Encodable));
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSRequestInformation {\n");
        if (this.a != 1) {
            stringBuffer.append("version: " + this.a + "\n");
        }
        stringBuffer.append("service: " + this.b + "\n");
        if (this.c != null) {
            stringBuffer.append("nonce: " + this.c + "\n");
        }
        if (this.d != null) {
            stringBuffer.append("requestTime: " + this.d + "\n");
        }
        if (this.e != null) {
            stringBuffer.append("requester: " + this.e + "\n");
        }
        if (this.f != null) {
            stringBuffer.append("requestPolicy: " + this.f + "\n");
        }
        if (this.g != null) {
            stringBuffer.append("dvcs: " + this.g + "\n");
        }
        if (this.h != null) {
            stringBuffer.append("dataLocations: " + this.h + "\n");
        }
        if (this.i != null) {
            stringBuffer.append("extensions: " + this.i + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }
}
