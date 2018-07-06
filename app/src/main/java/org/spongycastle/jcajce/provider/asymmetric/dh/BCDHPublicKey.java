package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.io.IOException;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.DHDomainParameters;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;

public class BCDHPublicKey implements DHPublicKey {
    private BigInteger a;
    private transient DHParameterSpec b;
    private transient SubjectPublicKeyInfo c;

    BCDHPublicKey(DHPublicKeySpec dHPublicKeySpec) {
        this.a = dHPublicKeySpec.getY();
        this.b = new DHParameterSpec(dHPublicKeySpec.getP(), dHPublicKeySpec.getG());
    }

    BCDHPublicKey(DHPublicKey dHPublicKey) {
        this.a = dHPublicKey.getY();
        this.b = dHPublicKey.getParams();
    }

    BCDHPublicKey(DHPublicKeyParameters dHPublicKeyParameters) {
        this.a = dHPublicKeyParameters.c();
        this.b = new DHParameterSpec(dHPublicKeyParameters.b().a(), dHPublicKeyParameters.b().b(), dHPublicKeyParameters.b().e());
    }

    BCDHPublicKey(BigInteger bigInteger, DHParameterSpec dHParameterSpec) {
        this.a = bigInteger;
        this.b = dHParameterSpec;
    }

    public BCDHPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.c = subjectPublicKeyInfo;
        try {
            this.a = ((ASN1Integer) subjectPublicKeyInfo.f()).d();
            Object a = ASN1Sequence.a(subjectPublicKeyInfo.d().f());
            ASN1ObjectIdentifier e = subjectPublicKeyInfo.d().e();
            if (e.equals(PKCSObjectIdentifiers.q) || a(a)) {
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
        } catch (IOException e2) {
            throw new IllegalArgumentException("invalid info structure in DH public key");
        }
    }

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
        return KeyUtil.a(new AlgorithmIdentifier(PKCSObjectIdentifiers.q, new DHParameter(this.b.getP(), this.b.getG(), this.b.getL()).a()), new ASN1Integer(this.a));
    }

    public DHParameterSpec getParams() {
        return this.b;
    }

    public BigInteger getY() {
        return this.a;
    }

    private boolean a(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.f() == 2) {
            return true;
        }
        if (aSN1Sequence.f() > 3) {
            return false;
        }
        if (DERInteger.a((Object) aSN1Sequence.a(2)).d().compareTo(BigInteger.valueOf((long) DERInteger.a((Object) aSN1Sequence.a(0)).d().bitLength())) > 0) {
            return false;
        }
        return true;
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
