package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class BCRSAPrivateKey implements RSAPrivateKey, PKCS12BagAttributeCarrier {
    private static BigInteger c = BigInteger.valueOf(0);
    protected BigInteger a;
    protected BigInteger b;
    private transient PKCS12BagAttributeCarrierImpl d = new PKCS12BagAttributeCarrierImpl();

    protected BCRSAPrivateKey() {
    }

    BCRSAPrivateKey(RSAKeyParameters rSAKeyParameters) {
        this.a = rSAKeyParameters.b();
        this.b = rSAKeyParameters.c();
    }

    BCRSAPrivateKey(RSAPrivateKeySpec rSAPrivateKeySpec) {
        this.a = rSAPrivateKeySpec.getModulus();
        this.b = rSAPrivateKeySpec.getPrivateExponent();
    }

    BCRSAPrivateKey(RSAPrivateKey rSAPrivateKey) {
        this.a = rSAPrivateKey.getModulus();
        this.b = rSAPrivateKey.getPrivateExponent();
    }

    public BigInteger getModulus() {
        return this.a;
    }

    public BigInteger getPrivateExponent() {
        return this.b;
    }

    public String getAlgorithm() {
        return "RSA";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        return KeyUtil.b(new AlgorithmIdentifier(PKCSObjectIdentifiers.h_, DERNull.a), new org.spongycastle.asn1.pkcs.RSAPrivateKey(getModulus(), c, getPrivateExponent(), c, c, c, c, c));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RSAPrivateKey)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) obj;
        if (getModulus().equals(rSAPrivateKey.getModulus()) && getPrivateExponent().equals(rSAPrivateKey.getPrivateExponent())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPrivateExponent().hashCode();
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.d.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.d.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.d.a();
    }
}
