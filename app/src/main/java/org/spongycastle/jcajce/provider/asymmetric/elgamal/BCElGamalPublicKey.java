package org.spongycastle.jcajce.provider.asymmetric.elgamal;

import java.io.IOException;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.oiw.ElGamalParameter;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.ElGamalPublicKeyParameters;
import org.spongycastle.jce.interfaces.ElGamalPublicKey;
import org.spongycastle.jce.spec.ElGamalParameterSpec;
import org.spongycastle.jce.spec.ElGamalPublicKeySpec;

public class BCElGamalPublicKey implements DHPublicKey, ElGamalPublicKey {
    private BigInteger a;
    private transient ElGamalParameterSpec b;

    BCElGamalPublicKey(ElGamalPublicKeySpec elGamalPublicKeySpec) {
        this.a = elGamalPublicKeySpec.b();
        this.b = new ElGamalParameterSpec(elGamalPublicKeySpec.a().a(), elGamalPublicKeySpec.a().b());
    }

    BCElGamalPublicKey(DHPublicKeySpec dHPublicKeySpec) {
        this.a = dHPublicKeySpec.getY();
        this.b = new ElGamalParameterSpec(dHPublicKeySpec.getP(), dHPublicKeySpec.getG());
    }

    BCElGamalPublicKey(ElGamalPublicKey elGamalPublicKey) {
        this.a = elGamalPublicKey.getY();
        this.b = elGamalPublicKey.b();
    }

    BCElGamalPublicKey(DHPublicKey dHPublicKey) {
        this.a = dHPublicKey.getY();
        this.b = new ElGamalParameterSpec(dHPublicKey.getParams().getP(), dHPublicKey.getParams().getG());
    }

    BCElGamalPublicKey(ElGamalPublicKeyParameters elGamalPublicKeyParameters) {
        this.a = elGamalPublicKeyParameters.c();
        this.b = new ElGamalParameterSpec(elGamalPublicKeyParameters.b().a(), elGamalPublicKeyParameters.b().b());
    }

    BCElGamalPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        ElGamalParameter elGamalParameter = new ElGamalParameter((ASN1Sequence) subjectPublicKeyInfo.e().f());
        try {
            this.a = ((DERInteger) subjectPublicKeyInfo.f()).d();
            this.b = new ElGamalParameterSpec(elGamalParameter.d(), elGamalParameter.e());
        } catch (IOException e) {
            throw new IllegalArgumentException("invalid info structure in DSA public key");
        }
    }

    public String getAlgorithm() {
        return "ElGamal";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(OIWObjectIdentifiers.l, new ElGamalParameter(this.b.a(), this.b.b())), new DERInteger(this.a)).a("DER");
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

    public BigInteger getY() {
        return this.a;
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPublicKey)) {
            return false;
        }
        DHPublicKey dHPublicKey = (DHPublicKey) obj;
        if (getY().equals(dHPublicKey.getY()) && getParams().getG().equals(dHPublicKey.getParams().getG()) && getParams().getP().equals(dHPublicKey.getParams().getP()) && getParams().getL() == dHPublicKey.getParams().getL()) {
            return true;
        }
        return false;
    }
}
