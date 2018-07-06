package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;

public class JDKDSAPublicKey implements DSAPublicKey {
    private BigInteger a;
    private DSAParams b;

    public String getAlgorithm() {
        return "DSA";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        try {
            if (this.b == null) {
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.U), new DERInteger(this.a)).a("DER");
            }
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.U, new DSAParameter(this.b.getP(), this.b.getQ(), this.b.getG())), new DERInteger(this.a)).a("DER");
        } catch (IOException e) {
            return null;
        }
    }

    public DSAParams getParams() {
        return this.b;
    }

    public BigInteger getY() {
        return this.a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("DSA Public Key").append(property);
        stringBuffer.append("            y: ").append(getY().toString(16)).append(property);
        return stringBuffer.toString();
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getQ().hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAPublicKey)) {
            return false;
        }
        DSAPublicKey dSAPublicKey = (DSAPublicKey) obj;
        if (getY().equals(dSAPublicKey.getY()) && getParams().getG().equals(dSAPublicKey.getParams().getG()) && getParams().getP().equals(dSAPublicKey.getParams().getP()) && getParams().getQ().equals(dSAPublicKey.getParams().getQ())) {
            return true;
        }
        return false;
    }
}
