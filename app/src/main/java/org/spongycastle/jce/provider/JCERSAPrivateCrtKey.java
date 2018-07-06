package org.spongycastle.jce.provider;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSAPrivateKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;

public class JCERSAPrivateCrtKey extends JCERSAPrivateKey implements RSAPrivateCrtKey {
    private BigInteger c;
    private BigInteger d;
    private BigInteger e;
    private BigInteger f;
    private BigInteger g;
    private BigInteger h;

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        return KeyUtil.b(new AlgorithmIdentifier(PKCSObjectIdentifiers.h_, DERNull.a), new RSAPrivateKey(getModulus(), getPublicExponent(), getPrivateExponent(), getPrimeP(), getPrimeQ(), getPrimeExponentP(), getPrimeExponentQ(), getCrtCoefficient()));
    }

    public BigInteger getPublicExponent() {
        return this.c;
    }

    public BigInteger getPrimeP() {
        return this.d;
    }

    public BigInteger getPrimeQ() {
        return this.e;
    }

    public BigInteger getPrimeExponentP() {
        return this.f;
    }

    public BigInteger getPrimeExponentQ() {
        return this.g;
    }

    public BigInteger getCrtCoefficient() {
        return this.h;
    }

    public int hashCode() {
        return (getModulus().hashCode() ^ getPublicExponent().hashCode()) ^ getPrivateExponent().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RSAPrivateCrtKey)) {
            return false;
        }
        RSAPrivateCrtKey rSAPrivateCrtKey = (RSAPrivateCrtKey) obj;
        if (getModulus().equals(rSAPrivateCrtKey.getModulus()) && getPublicExponent().equals(rSAPrivateCrtKey.getPublicExponent()) && getPrivateExponent().equals(rSAPrivateCrtKey.getPrivateExponent()) && getPrimeP().equals(rSAPrivateCrtKey.getPrimeP()) && getPrimeQ().equals(rSAPrivateCrtKey.getPrimeQ()) && getPrimeExponentP().equals(rSAPrivateCrtKey.getPrimeExponentP()) && getPrimeExponentQ().equals(rSAPrivateCrtKey.getPrimeExponentQ()) && getCrtCoefficient().equals(rSAPrivateCrtKey.getCrtCoefficient())) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("RSA Private CRT Key").append(property);
        stringBuffer.append("            modulus: ").append(getModulus().toString(16)).append(property);
        stringBuffer.append("    public exponent: ").append(getPublicExponent().toString(16)).append(property);
        stringBuffer.append("   private exponent: ").append(getPrivateExponent().toString(16)).append(property);
        stringBuffer.append("             primeP: ").append(getPrimeP().toString(16)).append(property);
        stringBuffer.append("             primeQ: ").append(getPrimeQ().toString(16)).append(property);
        stringBuffer.append("     primeExponentP: ").append(getPrimeExponentP().toString(16)).append(property);
        stringBuffer.append("     primeExponentQ: ").append(getPrimeExponentQ().toString(16)).append(property);
        stringBuffer.append("     crtCoefficient: ").append(getCrtCoefficient().toString(16)).append(property);
        return stringBuffer.toString();
    }
}
