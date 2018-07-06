package org.spongycastle.jce.netscape;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class NetscapeCertRequest extends ASN1Object {
    AlgorithmIdentifier a;
    byte[] b;
    String c;
    PublicKey d;

    private ASN1Primitive d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(this.d.getEncoded());
            byteArrayOutputStream.close();
            return new ASN1InputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).d();
        } catch (IOException e) {
            throw new InvalidKeySpecException(e.getMessage());
        }
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        try {
            aSN1EncodableVector2.a(d());
        } catch (Exception e) {
        }
        aSN1EncodableVector2.a(new DERIA5String(this.c));
        aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.a(this.a);
        aSN1EncodableVector.a(new DERBitString(this.b));
        return new DERSequence(aSN1EncodableVector);
    }
}
