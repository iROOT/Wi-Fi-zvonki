package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x9.DHDomainParameters;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class BCDHPrivateKey implements DHPrivateKey, PKCS12BagAttributeCarrier {
    private BigInteger a;
    private transient DHParameterSpec b;
    private transient PrivateKeyInfo c;
    private transient PKCS12BagAttributeCarrierImpl d = new PKCS12BagAttributeCarrierImpl();

    protected BCDHPrivateKey() {
    }

    BCDHPrivateKey(DHPrivateKey dHPrivateKey) {
        this.a = dHPrivateKey.getX();
        this.b = dHPrivateKey.getParams();
    }

    BCDHPrivateKey(DHPrivateKeySpec dHPrivateKeySpec) {
        this.a = dHPrivateKeySpec.getX();
        this.b = new DHParameterSpec(dHPrivateKeySpec.getP(), dHPrivateKeySpec.getG());
    }

    public BCDHPrivateKey(PrivateKeyInfo privateKeyInfo) {
        Object a = ASN1Sequence.a(privateKeyInfo.d().f());
        ASN1Integer aSN1Integer = (ASN1Integer) privateKeyInfo.f();
        ASN1ObjectIdentifier e = privateKeyInfo.d().e();
        this.c = privateKeyInfo;
        this.a = aSN1Integer.d();
        if (e.equals(PKCSObjectIdentifiers.q)) {
            DHParameter a2 = DHParameter.a(a);
            if (a2.f() != null) {
                this.b = new DHParameterSpec(a2.d(), a2.e(), a2.f().intValue());
            } else {
                this.b = new DHParameterSpec(a2.d(), a2.e());
            }
        } else if (e.equals(X9ObjectIdentifiers.ab)) {
            DHDomainParameters a3 = DHDomainParameters.a(a);
            this.b = new DHParameterSpec(a3.d().d(), a3.e().d());
        } else {
            throw new IllegalArgumentException("unknown algorithm type: " + e);
        }
    }

    BCDHPrivateKey(DHPrivateKeyParameters dHPrivateKeyParameters) {
        this.a = dHPrivateKeyParameters.c();
        this.b = new DHParameterSpec(dHPrivateKeyParameters.b().a(), dHPrivateKeyParameters.b().b(), dHPrivateKeyParameters.b().e());
    }

    public String getAlgorithm() {
        return "DH";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        try {
            if (this.c != null) {
                return this.c.a("DER");
            }
            return new PrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.q, new DHParameter(this.b.getP(), this.b.getG(), this.b.getL()).a()), new ASN1Integer(getX())).a("DER");
        } catch (Exception e) {
            return null;
        }
    }

    public DHParameterSpec getParams() {
        return this.b;
    }

    public BigInteger getX() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPrivateKey)) {
            return false;
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) obj;
        if (getX().equals(dHPrivateKey.getX()) && getParams().getG().equals(dHPrivateKey.getParams().getG()) && getParams().getP().equals(dHPrivateKey.getParams().getP()) && getParams().getL() == dHPrivateKey.getParams().getL()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((getX().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
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
