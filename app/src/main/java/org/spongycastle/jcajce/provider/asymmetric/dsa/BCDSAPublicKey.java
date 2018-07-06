package org.spongycastle.jcajce.provider.asymmetric.dsa;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPublicKeySpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;

public class BCDSAPublicKey implements DSAPublicKey {
    private BigInteger a;
    private transient DSAParams b;

    BCDSAPublicKey(DSAPublicKeySpec dSAPublicKeySpec) {
        this.a = dSAPublicKeySpec.getY();
        this.b = new DSAParameterSpec(dSAPublicKeySpec.getP(), dSAPublicKeySpec.getQ(), dSAPublicKeySpec.getG());
    }

    BCDSAPublicKey(DSAPublicKey dSAPublicKey) {
        this.a = dSAPublicKey.getY();
        this.b = dSAPublicKey.getParams();
    }

    BCDSAPublicKey(DSAPublicKeyParameters dSAPublicKeyParameters) {
        this.a = dSAPublicKeyParameters.c();
        this.b = new DSAParameterSpec(dSAPublicKeyParameters.b().a(), dSAPublicKeyParameters.b().b(), dSAPublicKeyParameters.b().c());
    }

    public BCDSAPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        try {
            this.a = ((ASN1Integer) subjectPublicKeyInfo.f()).d();
            if (a(subjectPublicKeyInfo.d().f())) {
                DSAParameter a = DSAParameter.a(subjectPublicKeyInfo.d().f());
                this.b = new DSAParameterSpec(a.d(), a.e(), a.f());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
    }

    private boolean a(ASN1Encodable aSN1Encodable) {
        return (aSN1Encodable == null || DERNull.a.equals(aSN1Encodable.a())) ? false : true;
    }

    public String getAlgorithm() {
        return "DSA";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        if (this.b == null) {
            return KeyUtil.a(new AlgorithmIdentifier(X9ObjectIdentifiers.U), new ASN1Integer(this.a));
        }
        return KeyUtil.a(new AlgorithmIdentifier(X9ObjectIdentifiers.U, new DSAParameter(this.b.getP(), this.b.getQ(), this.b.getG()).a()), new ASN1Integer(this.a));
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
