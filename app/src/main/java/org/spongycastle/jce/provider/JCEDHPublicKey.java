package org.spongycastle.jce.provider;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;

public class JCEDHPublicKey implements DHPublicKey {
    private BigInteger a;
    private DHParameterSpec b;
    private SubjectPublicKeyInfo c;

    public String getAlgorithm() {
        return "DH";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        if (this.c != null) {
            return KeyUtil.a(this.c);
        }
        return KeyUtil.a(new AlgorithmIdentifier(PKCSObjectIdentifiers.q, new DHParameter(this.b.getP(), this.b.getG(), this.b.getL())), new DERInteger(this.a));
    }

    public DHParameterSpec getParams() {
        return this.b;
    }

    public BigInteger getY() {
        return this.a;
    }
}
