package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.pkcs.RSAPrivateKey;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;

public class BCRSAPrivateCrtKey extends BCRSAPrivateKey implements RSAPrivateCrtKey {
    private BigInteger c;
    private BigInteger d;
    private BigInteger e;
    private BigInteger f;
    private BigInteger g;
    private BigInteger h;

    BCRSAPrivateCrtKey(RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters) {
        super((RSAKeyParameters) rSAPrivateCrtKeyParameters);
        this.c = rSAPrivateCrtKeyParameters.d();
        this.d = rSAPrivateCrtKeyParameters.e();
        this.e = rSAPrivateCrtKeyParameters.f();
        this.f = rSAPrivateCrtKeyParameters.g();
        this.g = rSAPrivateCrtKeyParameters.h();
        this.h = rSAPrivateCrtKeyParameters.i();
    }

    BCRSAPrivateCrtKey(RSAPrivateCrtKeySpec rSAPrivateCrtKeySpec) {
        this.a = rSAPrivateCrtKeySpec.getModulus();
        this.c = rSAPrivateCrtKeySpec.getPublicExponent();
        this.b = rSAPrivateCrtKeySpec.getPrivateExponent();
        this.d = rSAPrivateCrtKeySpec.getPrimeP();
        this.e = rSAPrivateCrtKeySpec.getPrimeQ();
        this.f = rSAPrivateCrtKeySpec.getPrimeExponentP();
        this.g = rSAPrivateCrtKeySpec.getPrimeExponentQ();
        this.h = rSAPrivateCrtKeySpec.getCrtCoefficient();
    }

    BCRSAPrivateCrtKey(RSAPrivateCrtKey rSAPrivateCrtKey) {
        this.a = rSAPrivateCrtKey.getModulus();
        this.c = rSAPrivateCrtKey.getPublicExponent();
        this.b = rSAPrivateCrtKey.getPrivateExponent();
        this.d = rSAPrivateCrtKey.getPrimeP();
        this.e = rSAPrivateCrtKey.getPrimeQ();
        this.f = rSAPrivateCrtKey.getPrimeExponentP();
        this.g = rSAPrivateCrtKey.getPrimeExponentQ();
        this.h = rSAPrivateCrtKey.getCrtCoefficient();
    }

    BCRSAPrivateCrtKey(PrivateKeyInfo privateKeyInfo) {
        this(RSAPrivateKey.a(privateKeyInfo.f()));
    }

    BCRSAPrivateCrtKey(RSAPrivateKey rSAPrivateKey) {
        this.a = rSAPrivateKey.d();
        this.c = rSAPrivateKey.e();
        this.b = rSAPrivateKey.f();
        this.d = rSAPrivateKey.g();
        this.e = rSAPrivateKey.h();
        this.f = rSAPrivateKey.i();
        this.g = rSAPrivateKey.j();
        this.h = rSAPrivateKey.k();
    }

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
