package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;

public class JCEDHPrivateKey implements DHPrivateKey, PKCS12BagAttributeCarrier {
    BigInteger a;
    private DHParameterSpec b;
    private PrivateKeyInfo c;
    private PKCS12BagAttributeCarrier d = new PKCS12BagAttributeCarrierImpl();

    protected JCEDHPrivateKey() {
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
            return new PrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.q, new DHParameter(this.b.getP(), this.b.getG(), this.b.getL())), new DERInteger(getX())).a("DER");
        } catch (IOException e) {
            return null;
        }
    }

    public DHParameterSpec getParams() {
        return this.b;
    }

    public BigInteger getX() {
        return this.a;
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
