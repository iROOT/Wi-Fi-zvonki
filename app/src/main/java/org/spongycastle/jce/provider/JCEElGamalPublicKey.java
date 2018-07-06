package org.spongycastle.jce.provider;

import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.interfaces.ElGamalPublicKey;
import org.spongycastle.jce.spec.ElGamalParameterSpec;

public class JCEElGamalPublicKey implements DHPublicKey, ElGamalPublicKey {
    private BigInteger a;
    private ElGamalParameterSpec b;

    public String getAlgorithm() {
        return "ElGamal";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        return KeyUtil.a(new AlgorithmIdentifier(OIWObjectIdentifiers.l, new ElGamalParameter(this.b.a(), this.b.b())), new DERInteger(this.a));
    }

    public ElGamalParameterSpec b() {
        return this.b;
    }

    public DHParameterSpec getParams() {
        return new DHParameterSpec(this.b.a(), this.b.b());
    }

    public BigInteger getY() {
        return this.a;
    }
}
