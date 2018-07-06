package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.ElGamalPrivateKey;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.spec.ElGamalParameterSpec;
import org.spongycastle.jce.spec.ElGamalPrivateKeySpec;

public class BCElGamalPrivateKey implements DHPrivateKey, ElGamalPrivateKey, PKCS12BagAttributeCarrier {
    private BigInteger a;
    private transient ElGamalParameterSpec b;
    private transient PKCS12BagAttributeCarrierImpl c = new PKCS12BagAttributeCarrierImpl();

    protected BCElGamalPrivateKey() {
    }

    BCElGamalPrivateKey(ElGamalPrivateKey elGamalPrivateKey) {
        this.a = elGamalPrivateKey.getX();
        this.b = elGamalPrivateKey.b();
    }

    BCElGamalPrivateKey(DHPrivateKey dHPrivateKey) {
        this.a = dHPrivateKey.getX();
        this.b = new ElGamalParameterSpec(dHPrivateKey.getParams().getP(), dHPrivateKey.getParams().getG());
    }

    BCElGamalPrivateKey(ElGamalPrivateKeySpec elGamalPrivateKeySpec) {
        this.a = elGamalPrivateKeySpec.b();
        this.b = new ElGamalParameterSpec(elGamalPrivateKeySpec.a().a(), elGamalPrivateKeySpec.a().b());
    }

    BCElGamalPrivateKey(DHPrivateKeySpec dHPrivateKeySpec) {
        this.a = dHPrivateKeySpec.getX();
        this.b = new ElGamalParameterSpec(dHPrivateKeySpec.getP(), dHPrivateKeySpec.getG());
    }

    BCElGamalPrivateKey(PrivateKeyInfo privateKeyInfo) {
        ElGamalParameter elGamalParameter = new ElGamalParameter((ASN1Sequence) privateKeyInfo.e().f());
        this.a = DERInteger.a((Object) privateKeyInfo.f()).d();
        this.b = new ElGamalParameterSpec(elGamalParameter.d(), elGamalParameter.e());
    }

    BCElGamalPrivateKey(ElGamalPrivateKeyParameters elGamalPrivateKeyParameters) {
        this.a = elGamalPrivateKeyParameters.c();
        this.b = new ElGamalParameterSpec(elGamalPrivateKeyParameters.b().a(), elGamalPrivateKeyParameters.b().b());
    }

    public String getAlgorithm() {
        return "ElGamal";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(OIWObjectIdentifiers.l, new ElGamalParameter(this.b.a(), this.b.b())), new DERInteger(getX())).a("DER");
        } catch (IOException e) {
            return null;
        }
    }

    public ElGamalParameterSpec b() {
        return this.b;
    }

    public DHParameterSpec getParams() {
        return new DHParameterSpec(this.b.a(), this.b.b());
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
        this.c.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.c.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.c.a();
    }
}
