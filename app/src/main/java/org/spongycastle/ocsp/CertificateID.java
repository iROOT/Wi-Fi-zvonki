package org.spongycastle.ocsp;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.ocsp.CertID;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jce.PrincipalUtil;

public class CertificateID {
    private final CertID a;

    public CertificateID(String str, X509Certificate x509Certificate, BigInteger bigInteger, String str2) {
        this.a = a(new AlgorithmIdentifier(new DERObjectIdentifier(str), DERNull.a), x509Certificate, new ASN1Integer(bigInteger), str2);
    }

    public CertificateID(String str, X509Certificate x509Certificate, BigInteger bigInteger) {
        this(str, x509Certificate, bigInteger, "SC");
    }

    public CertID a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CertificateID)) {
            return false;
        }
        return this.a.a().equals(((CertificateID) obj).a.a());
    }

    public int hashCode() {
        return this.a.a().hashCode();
    }

    private static CertID a(AlgorithmIdentifier algorithmIdentifier, X509Certificate x509Certificate, ASN1Integer aSN1Integer, String str) {
        try {
            MessageDigest a = OCSPUtil.a(algorithmIdentifier.e().d(), str);
            a.update(PrincipalUtil.b(x509Certificate).b());
            ASN1OctetString dEROctetString = new DEROctetString(a.digest());
            a.update(SubjectPublicKeyInfo.a(new ASN1InputStream(x509Certificate.getPublicKey().getEncoded()).d()).g().d());
            return new CertID(algorithmIdentifier, dEROctetString, new DEROctetString(a.digest()), aSN1Integer);
        } catch (Exception e) {
            throw new OCSPException("problem creating ID: " + e, e);
        }
    }
}
