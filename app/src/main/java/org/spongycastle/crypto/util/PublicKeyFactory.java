package org.spongycastle.crypto.util;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAPublicKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.asn1.x9.DHDomainParameters;
import org.spongycastle.asn1.x9.DHPublicKey;
import org.spongycastle.asn1.x9.DHValidationParms;
import org.spongycastle.asn1.x9.ECNamedCurveTable;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ECPoint;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.DHValidationParameters;
import org.spongycastle.crypto.params.DSAParameters;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.ElGamalParameters;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class PublicKeyFactory {
    public static AsymmetricKeyParameter a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        int i = 0;
        DSAParameters dSAParameters = null;
        AlgorithmIdentifier d = subjectPublicKeyInfo.d();
        if (d.e().equals(PKCSObjectIdentifiers.h_) || d.e().equals(X509ObjectIdentifiers.l)) {
            RSAPublicKey a = RSAPublicKey.a(subjectPublicKeyInfo.f());
            return new RSAKeyParameters(false, a.d(), a.e());
        } else if (d.e().equals(X9ObjectIdentifiers.ab)) {
            BigInteger d2;
            DHValidationParameters dHValidationParameters;
            BigInteger d3 = DHPublicKey.a(subjectPublicKeyInfo.f()).d().d();
            DHDomainParameters a2 = DHDomainParameters.a(d.f());
            BigInteger d4 = a2.d().d();
            BigInteger d5 = a2.e().d();
            r3 = a2.f().d();
            if (a2.g() != null) {
                d2 = a2.g().d();
            } else {
                d2 = null;
            }
            DHValidationParms h = a2.h();
            if (h != null) {
                dHValidationParameters = new DHValidationParameters(h.d().d(), h.e().d().intValue());
            }
            return new DHPublicKeyParameters(d3, new DHParameters(d4, d5, r3, d2, dHValidationParameters));
        } else if (d.e().equals(PKCSObjectIdentifiers.q)) {
            DHParameter a3 = DHParameter.a(d.f());
            r0 = (ASN1Integer) subjectPublicKeyInfo.f();
            r3 = a3.f();
            if (r3 != null) {
                i = r3.intValue();
            }
            return new DHPublicKeyParameters(r0.d(), new DHParameters(a3.d(), a3.e(), null, i));
        } else if (d.e().equals(OIWObjectIdentifiers.l)) {
            ElGamalParameter elGamalParameter = new ElGamalParameter((ASN1Sequence) d.f());
            return new ElGamalPublicKeyParameters(((ASN1Integer) subjectPublicKeyInfo.f()).d(), new ElGamalParameters(elGamalParameter.d(), elGamalParameter.e()));
        } else if (d.e().equals(X9ObjectIdentifiers.U) || d.e().equals(OIWObjectIdentifiers.j)) {
            r0 = (ASN1Integer) subjectPublicKeyInfo.f();
            ASN1Encodable f = d.f();
            if (f != null) {
                DSAParameter a4 = DSAParameter.a(f.a());
                dSAParameters = new DSAParameters(a4.d(), a4.e(), a4.f());
            }
            return new DSAPublicKeyParameters(r0.d(), dSAParameters);
        } else if (d.e().equals(X9ObjectIdentifiers.k)) {
            X9ECParameters a5;
            X962Parameters a6 = X962Parameters.a(d.f());
            if (a6.d()) {
                a5 = ECNamedCurveTable.a((ASN1ObjectIdentifier) a6.f());
            } else {
                a5 = X9ECParameters.a(a6.f());
            }
            X9ECPoint x9ECPoint = new X9ECPoint(a5.d(), new DEROctetString(subjectPublicKeyInfo.g().d()));
            return new ECPublicKeyParameters(x9ECPoint.d(), new ECDomainParameters(a5.d(), a5.e(), a5.f(), a5.g(), a5.h()));
        } else {
            throw new RuntimeException("algorithm identifier in key not recognised");
        }
    }
}
