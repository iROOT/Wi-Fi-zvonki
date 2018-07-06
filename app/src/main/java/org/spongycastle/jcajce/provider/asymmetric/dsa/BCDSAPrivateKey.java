package org.spongycastle.jcajce.provider.asymmetric.dsa;

import java.math.BigInteger;
import java.security.interfaces.DSAParams;
import java.security.interfaces.DSAPrivateKey;
import java.security.spec.DSAParameterSpec;
import java.security.spec.DSAPrivateKeySpec;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DSAParameter;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class BCDSAPrivateKey implements DSAPrivateKey, PKCS12BagAttributeCarrier {
    private BigInteger a;
    private transient DSAParams b;
    private transient PKCS12BagAttributeCarrierImpl c = new PKCS12BagAttributeCarrierImpl();

    protected BCDSAPrivateKey() {
    }

    BCDSAPrivateKey(DSAPrivateKey dSAPrivateKey) {
        this.a = dSAPrivateKey.getX();
        this.b = dSAPrivateKey.getParams();
    }

    BCDSAPrivateKey(DSAPrivateKeySpec dSAPrivateKeySpec) {
        this.a = dSAPrivateKeySpec.getX();
        this.b = new DSAParameterSpec(dSAPrivateKeySpec.getP(), dSAPrivateKeySpec.getQ(), dSAPrivateKeySpec.getG());
    }

    public BCDSAPrivateKey(PrivateKeyInfo privateKeyInfo) {
        DSAParameter a = DSAParameter.a(privateKeyInfo.d().f());
        this.a = ((ASN1Integer) privateKeyInfo.f()).d();
        this.b = new DSAParameterSpec(a.d(), a.e(), a.f());
    }

    BCDSAPrivateKey(DSAPrivateKeyParameters dSAPrivateKeyParameters) {
        this.a = dSAPrivateKeyParameters.c();
        this.b = new DSAParameterSpec(dSAPrivateKeyParameters.b().a(), dSAPrivateKeyParameters.b().b(), dSAPrivateKeyParameters.b().c());
    }

    public String getAlgorithm() {
        return "DSA";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        return KeyUtil.b(new AlgorithmIdentifier(X9ObjectIdentifiers.U, new DSAParameter(this.b.getP(), this.b.getQ(), this.b.getG()).a()), new ASN1Integer(getX()));
    }

    public DSAParams getParams() {
        return this.b;
    }

    public BigInteger getX() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DSAPrivateKey)) {
            return false;
        }
        DSAPrivateKey dSAPrivateKey = (DSAPrivateKey) obj;
        if (getX().equals(dSAPrivateKey.getX()) && getParams().getG().equals(dSAPrivateKey.getParams().getG()) && getParams().getP().equals(dSAPrivateKey.getParams().getP()) && getParams().getQ().equals(dSAPrivateKey.getParams().getQ())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((getX().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getQ().hashCode();
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.c.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.c.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.c.a();
    }
}
