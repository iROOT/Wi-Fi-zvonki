package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;

public class BCRSAPublicKey implements RSAPublicKey {
    private static final AlgorithmIdentifier a = new AlgorithmIdentifier(PKCSObjectIdentifiers.h_, DERNull.a);
    private BigInteger b;
    private BigInteger c;
    private transient AlgorithmIdentifier d;

    BCRSAPublicKey(RSAKeyParameters rSAKeyParameters) {
        this.d = a;
        this.b = rSAKeyParameters.b();
        this.c = rSAKeyParameters.c();
    }

    BCRSAPublicKey(RSAPublicKeySpec rSAPublicKeySpec) {
        this.d = a;
        this.b = rSAPublicKeySpec.getModulus();
        this.c = rSAPublicKeySpec.getPublicExponent();
    }

    BCRSAPublicKey(RSAPublicKey rSAPublicKey) {
        this.d = a;
        this.b = rSAPublicKey.getModulus();
        this.c = rSAPublicKey.getPublicExponent();
    }

    BCRSAPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        a(subjectPublicKeyInfo);
    }

    private void a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        try {
            org.spongycastle.asn1.pkcs.RSAPublicKey a = org.spongycastle.asn1.pkcs.RSAPublicKey.a(subjectPublicKeyInfo.f());
            this.d = subjectPublicKeyInfo.d();
            this.b = a.d();
            this.c = a.e();
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid info structure in RSA public key");
        }
    }

    public BigInteger getModulus() {
        return this.b;
    }

    public BigInteger getPublicExponent() {
        return this.c;
    }

    public String getAlgorithm() {
        return "RSA";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        return KeyUtil.a(this.d, new org.spongycastle.asn1.pkcs.RSAPublicKey(getModulus(), getPublicExponent()));
    }

    public int hashCode() {
        return getModulus().hashCode() ^ getPublicExponent().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RSAPublicKey)) {
            return false;
        }
        RSAPublicKey rSAPublicKey = (RSAPublicKey) obj;
        if (getModulus().equals(rSAPublicKey.getModulus()) && getPublicExponent().equals(rSAPublicKey.getPublicExponent())) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("RSA Public Key").append(property);
        stringBuffer.append("            modulus: ").append(getModulus().toString(16)).append(property);
        stringBuffer.append("    public exponent: ").append(getPublicExponent().toString(16)).append(property);
        return stringBuffer.toString();
    }
}
