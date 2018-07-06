package org.spongycastle.jcajce.provider.asymmetric;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.rsa.KeyFactorySpi;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public class RSA {

    public static class Mappings extends AsymmetricAlgorithmProvider {
        public void a(ConfigurableProvider configurableProvider) {
            configurableProvider.a("AlgorithmParameters.OAEP", "org.spongycastle.jcajce.provider.asymmetric.rsa.AlgorithmParametersSpi$OAEP");
            configurableProvider.a("AlgorithmParameters.PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.AlgorithmParametersSpi$PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.RSAPSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.RSASSA-PSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA224withRSA/PSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA256withRSA/PSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA384withRSA/PSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA512withRSA/PSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA224WITHRSAANDMGF1", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA256WITHRSAANDMGF1", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA384WITHRSAANDMGF1", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA512WITHRSAANDMGF1", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.RAWRSAPSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.NONEWITHRSAPSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.NONEWITHRSASSA-PSS", "PSS");
            configurableProvider.a("Alg.Alias.AlgorithmParameters.NONEWITHRSAANDMGF1", "PSS");
            configurableProvider.a("Cipher.RSA", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$NoPadding");
            configurableProvider.a("Cipher.RSA/RAW", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$NoPadding");
            configurableProvider.a("Cipher.RSA/PKCS1", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding");
            configurableProvider.a("Cipher.1.2.840.113549.1.1.1", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding");
            configurableProvider.a("Cipher.2.5.8.1.1", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding");
            configurableProvider.a("Cipher.RSA/1", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding_PrivateOnly");
            configurableProvider.a("Cipher.RSA/2", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$PKCS1v1_5Padding_PublicOnly");
            configurableProvider.a("Cipher.RSA/OAEP", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$OAEPPadding");
            configurableProvider.a("Cipher." + PKCSObjectIdentifiers.h, "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$OAEPPadding");
            configurableProvider.a("Cipher.RSA/ISO9796-1", "org.spongycastle.jcajce.provider.asymmetric.rsa.CipherSpi$ISO9796d1Padding");
            configurableProvider.a("Alg.Alias.Cipher.RSA//RAW", "RSA");
            configurableProvider.a("Alg.Alias.Cipher.RSA//NOPADDING", "RSA");
            configurableProvider.a("Alg.Alias.Cipher.RSA//PKCS1PADDING", "RSA/PKCS1");
            configurableProvider.a("Alg.Alias.Cipher.RSA//OAEPPADDING", "RSA/OAEP");
            configurableProvider.a("Alg.Alias.Cipher.RSA//ISO9796-1PADDING", "RSA/ISO9796-1");
            configurableProvider.a("KeyFactory.RSA", "org.spongycastle.jcajce.provider.asymmetric.rsa.KeyFactorySpi");
            configurableProvider.a("KeyPairGenerator.RSA", "org.spongycastle.jcajce.provider.asymmetric.rsa.KeyPairGeneratorSpi");
            AsymmetricKeyInfoConverter keyFactorySpi = new KeyFactorySpi();
            a(configurableProvider, PKCSObjectIdentifiers.h_, "RSA", keyFactorySpi);
            a(configurableProvider, X509ObjectIdentifiers.l, "RSA", keyFactorySpi);
            a(configurableProvider, PKCSObjectIdentifiers.h, "RSA", keyFactorySpi);
            a(configurableProvider, PKCSObjectIdentifiers.k, "RSA", keyFactorySpi);
            a(configurableProvider, PKCSObjectIdentifiers.h_, "RSA");
            a(configurableProvider, X509ObjectIdentifiers.l, "RSA");
            a(configurableProvider, PKCSObjectIdentifiers.h, "OAEP");
            a(configurableProvider, PKCSObjectIdentifiers.k, "PSS");
            configurableProvider.a("Signature.RSASSA-PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$PSSwithRSA");
            configurableProvider.a("Signature." + PKCSObjectIdentifiers.k, "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$PSSwithRSA");
            configurableProvider.a("Signature.OID." + PKCSObjectIdentifiers.k, "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$PSSwithRSA");
            configurableProvider.a("Signature.SHA224withRSA/PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA224withRSA");
            configurableProvider.a("Signature.SHA256withRSA/PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA256withRSA");
            configurableProvider.a("Signature.SHA384withRSA/PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA384withRSA");
            configurableProvider.a("Signature.SHA512withRSA/PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA512withRSA");
            configurableProvider.a("Signature.RSA", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$noneRSA");
            configurableProvider.a("Signature.RAWRSASSA-PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$nonePSS");
            configurableProvider.a("Alg.Alias.Signature.RAWRSA", "RSA");
            configurableProvider.a("Alg.Alias.Signature.NONEWITHRSA", "RSA");
            configurableProvider.a("Alg.Alias.Signature.RAWRSAPSS", "RAWRSASSA-PSS");
            configurableProvider.a("Alg.Alias.Signature.NONEWITHRSAPSS", "RAWRSASSA-PSS");
            configurableProvider.a("Alg.Alias.Signature.NONEWITHRSASSA-PSS", "RAWRSASSA-PSS");
            configurableProvider.a("Alg.Alias.Signature.NONEWITHRSAANDMGF1", "RAWRSASSA-PSS");
            configurableProvider.a("Alg.Alias.Signature.RSAPSS", "RSASSA-PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA224withRSAandMGF1", "SHA224withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA256withRSAandMGF1", "SHA256withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA384withRSAandMGF1", "SHA384withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA512withRSAandMGF1", "SHA512withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA224WITHRSAANDMGF1", "SHA224withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA256WITHRSAANDMGF1", "SHA256withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA384WITHRSAANDMGF1", "SHA384withRSA/PSS");
            configurableProvider.a("Alg.Alias.Signature.SHA512WITHRSAANDMGF1", "SHA512withRSA/PSS");
            if (configurableProvider.b("MessageDigest", "MD2")) {
                a(configurableProvider, "MD2", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$MD2", PKCSObjectIdentifiers.i_);
            }
            if (configurableProvider.b("MessageDigest", "MD4")) {
                a(configurableProvider, "MD4", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$MD4", PKCSObjectIdentifiers.d);
            }
            if (configurableProvider.b("MessageDigest", "MD5")) {
                a(configurableProvider, "MD5", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$MD5", PKCSObjectIdentifiers.e);
                configurableProvider.a("Signature.MD5withRSA/ISO9796-2", "org.spongycastle.jcajce.provider.asymmetric.rsa.ISOSignatureSpi$MD5WithRSAEncryption");
                configurableProvider.a("Alg.Alias.Signature.MD5WithRSA/ISO9796-2", "MD5withRSA/ISO9796-2");
            }
            if (configurableProvider.b("MessageDigest", "SHA1")) {
                configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA1withRSA/PSS", "PSS");
                configurableProvider.a("Alg.Alias.AlgorithmParameters.SHA1WITHRSAANDMGF1", "PSS");
                configurableProvider.a("Signature.SHA1withRSA/PSS", "org.spongycastle.jcajce.provider.asymmetric.rsa.PSSSignatureSpi$SHA1withRSA");
                configurableProvider.a("Alg.Alias.Signature.SHA1withRSAandMGF1", "SHA1withRSA/PSS");
                configurableProvider.a("Alg.Alias.Signature.SHA1WITHRSAANDMGF1", "SHA1withRSA/PSS");
                a(configurableProvider, "SHA1", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA1", PKCSObjectIdentifiers.j_);
                configurableProvider.a("Alg.Alias.Signature.SHA1WithRSA/ISO9796-2", "SHA1withRSA/ISO9796-2");
                configurableProvider.a("Signature.SHA1withRSA/ISO9796-2", "org.spongycastle.jcajce.provider.asymmetric.rsa.ISOSignatureSpi$SHA1WithRSAEncryption");
                configurableProvider.a("Alg.Alias.Signature." + OIWObjectIdentifiers.k, "SHA1WITHRSA");
                configurableProvider.a("Alg.Alias.Signature.OID." + OIWObjectIdentifiers.k, "SHA1WITHRSA");
            }
            a(configurableProvider, "SHA224", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA224", PKCSObjectIdentifiers.p_);
            a(configurableProvider, "SHA256", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA256", PKCSObjectIdentifiers.m_);
            a(configurableProvider, "SHA384", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA384", PKCSObjectIdentifiers.n_);
            a(configurableProvider, "SHA512", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$SHA512", PKCSObjectIdentifiers.o_);
            if (configurableProvider.b("MessageDigest", "RIPEMD128")) {
                a(configurableProvider, "RIPEMD128", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD128", TeleTrusTObjectIdentifiers.g);
                a(configurableProvider, "RMD128", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD128", null);
            }
            if (configurableProvider.b("MessageDigest", "RIPEMD160")) {
                a(configurableProvider, "RIPEMD160", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD160", TeleTrusTObjectIdentifiers.f);
                a(configurableProvider, "RMD160", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD160", null);
                configurableProvider.a("Alg.Alias.Signature.RIPEMD160WithRSA/ISO9796-2", "RIPEMD160withRSA/ISO9796-2");
                configurableProvider.a("Signature.RIPEMD160withRSA/ISO9796-2", "org.spongycastle.jcajce.provider.asymmetric.rsa.ISOSignatureSpi$RIPEMD160WithRSAEncryption");
            }
            if (configurableProvider.b("MessageDigest", "RIPEMD256")) {
                a(configurableProvider, "RIPEMD256", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD256", TeleTrusTObjectIdentifiers.h);
                a(configurableProvider, "RMD256", "org.spongycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi$RIPEMD256", null);
            }
        }

        private void a(ConfigurableProvider configurableProvider, String str, String str2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
            String str3 = str + "WITHRSA";
            String str4 = str + "withRSA";
            String str5 = str + "WithRSA";
            String str6 = str + "/" + "RSA";
            String str7 = str + "WITHRSAENCRYPTION";
            String str8 = str + "withRSAEncryption";
            String str9 = str + "WithRSAEncryption";
            configurableProvider.a("Signature." + str3, str2);
            configurableProvider.a("Alg.Alias.Signature." + str4, str3);
            configurableProvider.a("Alg.Alias.Signature." + str5, str3);
            configurableProvider.a("Alg.Alias.Signature." + str7, str3);
            configurableProvider.a("Alg.Alias.Signature." + str8, str3);
            configurableProvider.a("Alg.Alias.Signature." + str9, str3);
            configurableProvider.a("Alg.Alias.Signature." + str6, str3);
            if (aSN1ObjectIdentifier != null) {
                configurableProvider.a("Alg.Alias.Signature." + aSN1ObjectIdentifier, str3);
                configurableProvider.a("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier, str3);
            }
        }
    }
}
