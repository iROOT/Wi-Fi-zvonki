package org.spongycastle.jce.provider;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PSSParameterSpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Null;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSASSAPSSparams;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;

class X509SignatureUtil {
    private static final ASN1Null a = DERNull.a;

    X509SignatureUtil() {
    }

    static void a(Signature signature, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null && !a.equals(aSN1Encodable)) {
            AlgorithmParameters instance = AlgorithmParameters.getInstance(signature.getAlgorithm(), signature.getProvider());
            try {
                instance.init(aSN1Encodable.a().b());
                if (signature.getAlgorithm().endsWith("MGF1")) {
                    try {
                        signature.setParameter(instance.getParameterSpec(PSSParameterSpec.class));
                    } catch (GeneralSecurityException e) {
                        throw new SignatureException("Exception extracting parameters: " + e.getMessage());
                    }
                }
            } catch (IOException e2) {
                throw new SignatureException("IOException decoding parameters: " + e2.getMessage());
            }
        }
    }

    static String a(AlgorithmIdentifier algorithmIdentifier) {
        Object f = algorithmIdentifier.f();
        if (!(f == null || a.equals(f))) {
            if (algorithmIdentifier.d().equals(PKCSObjectIdentifiers.k)) {
                return a(RSASSAPSSparams.a(f).d().d()) + "withRSAandMGF1";
            } else if (algorithmIdentifier.d().equals(X9ObjectIdentifiers.l)) {
                return a((DERObjectIdentifier) ASN1Sequence.a(f).a(0)) + "withECDSA";
            }
        }
        return algorithmIdentifier.d().d();
    }

    private static String a(DERObjectIdentifier dERObjectIdentifier) {
        if (PKCSObjectIdentifiers.H.equals(dERObjectIdentifier)) {
            return "MD5";
        }
        if (OIWObjectIdentifiers.i.equals(dERObjectIdentifier)) {
            return "SHA1";
        }
        if (NISTObjectIdentifiers.f.equals(dERObjectIdentifier)) {
            return "SHA224";
        }
        if (NISTObjectIdentifiers.c.equals(dERObjectIdentifier)) {
            return "SHA256";
        }
        if (NISTObjectIdentifiers.d.equals(dERObjectIdentifier)) {
            return "SHA384";
        }
        if (NISTObjectIdentifiers.e.equals(dERObjectIdentifier)) {
            return "SHA512";
        }
        if (TeleTrusTObjectIdentifiers.c.equals(dERObjectIdentifier)) {
            return "RIPEMD128";
        }
        if (TeleTrusTObjectIdentifiers.b.equals(dERObjectIdentifier)) {
            return "RIPEMD160";
        }
        if (TeleTrusTObjectIdentifiers.d.equals(dERObjectIdentifier)) {
            return "RIPEMD256";
        }
        if (CryptoProObjectIdentifiers.b.equals(dERObjectIdentifier)) {
            return "GOST3411";
        }
        return dERObjectIdentifier.d();
    }
}
