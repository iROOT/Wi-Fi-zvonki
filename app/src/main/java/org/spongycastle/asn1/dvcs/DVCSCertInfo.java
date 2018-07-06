package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cmp.PKIStatusInfo;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.PolicyInformation;

public class DVCSCertInfo extends ASN1Object {
    private int a;
    private DVCSRequestInformation b;
    private DigestInfo c;
    private ASN1Integer d;
    private DVCSTime e;
    private PKIStatusInfo f;
    private PolicyInformation g;
    private ASN1Set h;
    private ASN1Sequence i;
    private Extensions j;

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.a != 1) {
            aSN1EncodableVector.a(new ASN1Integer((long) this.a));
        }
        aSN1EncodableVector.a(this.b);
        aSN1EncodableVector.a(this.c);
        aSN1EncodableVector.a(this.d);
        aSN1EncodableVector.a(this.e);
        if (this.f != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 0, this.f));
        }
        if (this.g != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 1, this.g));
        }
        if (this.h != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 2, this.h));
        }
        if (this.i != null) {
            aSN1EncodableVector.a(new DERTaggedObject(false, 3, this.i));
        }
        if (this.j != null) {
            aSN1EncodableVector.a(this.j);
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DVCSCertInfo {\n");
        if (this.a != 1) {
            stringBuffer.append("version: " + this.a + "\n");
        }
        stringBuffer.append("dvReqInfo: " + this.b + "\n");
        stringBuffer.append("messageImprint: " + this.c + "\n");
        stringBuffer.append("serialNumber: " + this.d + "\n");
        stringBuffer.append("responseTime: " + this.e + "\n");
        if (this.f != null) {
            stringBuffer.append("dvStatus: " + this.f + "\n");
        }
        if (this.g != null) {
            stringBuffer.append("policy: " + this.g + "\n");
        }
        if (this.h != null) {
            stringBuffer.append("reqSignature: " + this.h + "\n");
        }
        if (this.i != null) {
            stringBuffer.append("certs: " + this.i + "\n");
        }
        if (this.j != null) {
            stringBuffer.append("extensions: " + this.j + "\n");
        }
        stringBuffer.append("}\n");
        return stringBuffer.toString();
    }
}
