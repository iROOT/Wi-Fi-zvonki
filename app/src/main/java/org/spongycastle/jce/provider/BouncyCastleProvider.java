package org.spongycastle.jce.provider;

import java.security.AccessController;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.Provider;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.provider.config.ConfigurableProvider;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jcajce.provider.util.AlgorithmProvider;
import org.spongycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;

public final class BouncyCastleProvider extends Provider implements ConfigurableProvider {
    public static final ProviderConfiguration a = new BouncyCastleProviderConfiguration();
    private static String b = "BouncyCastle Security Provider v1.50";
    private static final Map c = new HashMap();
    private static final String[] d = new String[]{"PBEPBKDF2", "PBEPKCS12"};
    private static final String[] e = new String[]{"SipHash"};
    private static final String[] f = new String[]{"AES", "ARC4", "Blowfish", "Camellia", "CAST5", "CAST6", "ChaCha", "DES", "DESede", "GOST28147", "Grainv1", "Grain128", "HC128", "HC256", "IDEA", "Noekeon", "RC2", "RC5", "RC6", "Rijndael", "Salsa20", "SEED", "Serpent", "Shacal2", "Skipjack", "TEA", "Twofish", "Threefish", "VMPC", "VMPCKSA3", "XTEA", "XSalsa20"};
    private static final String[] g = new String[]{"X509", "IES"};
    private static final String[] h = new String[]{"DSA", "DH", "EC", "RSA", "GOST", "ECGOST", "ElGamal", "DSTU4145"};
    private static final String[] i = new String[]{"GOST3411", "MD2", "MD4", "MD5", "SHA1", "RIPEMD128", "RIPEMD160", "RIPEMD256", "RIPEMD320", "SHA224", "SHA256", "SHA384", "SHA512", "SHA3", "Skein", "SM3", "Tiger", "Whirlpool"};
    private static final String[] j = new String[]{"BC", "PKCS12"};

    public BouncyCastleProvider() {
        super("SC", 1.5d, b);
        AccessController.doPrivileged(new PrivilegedAction(this) {
            final /* synthetic */ BouncyCastleProvider a;

            {
                this.a = r1;
            }

            public Object run() {
                this.a.a();
                return null;
            }
        });
    }

    private void a() {
        a("org.spongycastle.jcajce.provider.digest.", i);
        a("org.spongycastle.jcajce.provider.symmetric.", d);
        a("org.spongycastle.jcajce.provider.symmetric.", e);
        a("org.spongycastle.jcajce.provider.symmetric.", f);
        a("org.spongycastle.jcajce.provider.asymmetric.", g);
        a("org.spongycastle.jcajce.provider.asymmetric.", h);
        a("org.spongycastle.jcajce.provider.keystore.", j);
        put("X509Store.CERTIFICATE/COLLECTION", "org.spongycastle.jce.provider.X509StoreCertCollection");
        put("X509Store.ATTRIBUTECERTIFICATE/COLLECTION", "org.spongycastle.jce.provider.X509StoreAttrCertCollection");
        put("X509Store.CRL/COLLECTION", "org.spongycastle.jce.provider.X509StoreCRLCollection");
        put("X509Store.CERTIFICATEPAIR/COLLECTION", "org.spongycastle.jce.provider.X509StoreCertPairCollection");
        put("X509Store.CERTIFICATE/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPCerts");
        put("X509Store.CRL/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPCRLs");
        put("X509Store.ATTRIBUTECERTIFICATE/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPAttrCerts");
        put("X509Store.CERTIFICATEPAIR/LDAP", "org.spongycastle.jce.provider.X509StoreLDAPCertPairs");
        put("X509StreamParser.CERTIFICATE", "org.spongycastle.jce.provider.X509CertParser");
        put("X509StreamParser.ATTRIBUTECERTIFICATE", "org.spongycastle.jce.provider.X509AttrCertParser");
        put("X509StreamParser.CRL", "org.spongycastle.jce.provider.X509CRLParser");
        put("X509StreamParser.CERTIFICATEPAIR", "org.spongycastle.jce.provider.X509CertPairParser");
        put("Cipher.BROKENPBEWITHMD5ANDDES", "org.spongycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithMD5AndDES");
        put("Cipher.BROKENPBEWITHSHA1ANDDES", "org.spongycastle.jce.provider.BrokenJCEBlockCipher$BrokePBEWithSHA1AndDES");
        put("Cipher.OLDPBEWITHSHAANDTWOFISH-CBC", "org.spongycastle.jce.provider.BrokenJCEBlockCipher$OldPBEWithSHAAndTwofish");
        put("CertPathValidator.RFC3281", "org.spongycastle.jce.provider.PKIXAttrCertPathValidatorSpi");
        put("CertPathBuilder.RFC3281", "org.spongycastle.jce.provider.PKIXAttrCertPathBuilderSpi");
        put("CertPathValidator.RFC3280", "org.spongycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.RFC3280", "org.spongycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertPathValidator.PKIX", "org.spongycastle.jce.provider.PKIXCertPathValidatorSpi");
        put("CertPathBuilder.PKIX", "org.spongycastle.jce.provider.PKIXCertPathBuilderSpi");
        put("CertStore.Collection", "org.spongycastle.jce.provider.CertStoreCollectionSpi");
        put("CertStore.LDAP", "org.spongycastle.jce.provider.X509LDAPCertStoreSpi");
        put("CertStore.Multi", "org.spongycastle.jce.provider.MultiCertStoreSpi");
        put("Alg.Alias.CertStore.X509LDAP", "LDAP");
    }

    private void a(String str, String[] strArr) {
        for (int i = 0; i != strArr.length; i++) {
            Class cls = null;
            try {
                ClassLoader classLoader = getClass().getClassLoader();
                if (classLoader != null) {
                    cls = classLoader.loadClass(str + strArr[i] + "$Mappings");
                } else {
                    cls = Class.forName(str + strArr[i] + "$Mappings");
                }
            } catch (ClassNotFoundException e) {
            }
            if (cls != null) {
                try {
                    ((AlgorithmProvider) cls.newInstance()).a(this);
                } catch (Exception e2) {
                    throw new InternalError("cannot create instance of " + str + strArr[i] + "$Mappings : " + e2);
                }
            }
        }
    }

    public boolean b(String str, String str2) {
        return containsKey(new StringBuilder().append(str).append(".").append(str2).toString()) || containsKey("Alg.Alias." + str + "." + str2);
    }

    public void a(String str, String str2) {
        if (containsKey(str)) {
            throw new IllegalStateException("duplicate provider key (" + str + ") found");
        }
        put(str, str2);
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, AsymmetricKeyInfoConverter asymmetricKeyInfoConverter) {
        c.put(aSN1ObjectIdentifier, asymmetricKeyInfoConverter);
    }

    public static PublicKey a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = (AsymmetricKeyInfoConverter) c.get(subjectPublicKeyInfo.d().e());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.a(subjectPublicKeyInfo);
    }

    public static PrivateKey a(PrivateKeyInfo privateKeyInfo) {
        AsymmetricKeyInfoConverter asymmetricKeyInfoConverter = (AsymmetricKeyInfoConverter) c.get(privateKeyInfo.d().e());
        if (asymmetricKeyInfoConverter == null) {
            return null;
        }
        return asymmetricKeyInfoConverter.a(privateKeyInfo);
    }
}
