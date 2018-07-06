package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;

public class SubjectKeyIdentifier extends ASN1Object {
    private byte[] a;

    public byte[] d() {
        return this.a;
    }

    public ASN1Primitive a() {
        return new DEROctetString(this.a);
    }

    public SubjectKeyIdentifier(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.a = a(subjectPublicKeyInfo);
    }

    private static byte[] a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Digest sHA1Digest = new SHA1Digest();
        byte[] bArr = new byte[sHA1Digest.b()];
        byte[] d = subjectPublicKeyInfo.g().d();
        sHA1Digest.a(d, 0, d.length);
        sHA1Digest.a(bArr, 0);
        return bArr;
    }
}
