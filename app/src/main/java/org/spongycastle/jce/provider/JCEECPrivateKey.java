package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.sec.ECPrivateKeyStructure;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;

public class JCEECPrivateKey implements ECPrivateKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    private String a = "EC";
    private BigInteger b;
    private ECParameterSpec c;
    private boolean d;
    private DERBitString e;
    private PKCS12BagAttributeCarrierImpl f = new PKCS12BagAttributeCarrierImpl();

    protected JCEECPrivateKey() {
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        ASN1Encodable x962Parameters;
        ECPrivateKeyStructure eCPrivateKeyStructure;
        if (this.c instanceof ECNamedCurveSpec) {
            ASN1Primitive a = ECUtil.a(((ECNamedCurveSpec) this.c).a());
            if (a == null) {
                a = new DERObjectIdentifier(((ECNamedCurveSpec) this.c).a());
            }
            x962Parameters = new X962Parameters(a);
        } else if (this.c == null) {
            x962Parameters = new X962Parameters(DERNull.a);
        } else {
            ECCurve a2 = EC5Util.a(this.c.getCurve());
            Object x962Parameters2 = new X962Parameters(new X9ECParameters(a2, EC5Util.a(a2, this.c.getGenerator(), this.d), this.c.getOrder(), BigInteger.valueOf((long) this.c.getCofactor()), this.c.getCurve().getSeed()));
        }
        if (this.e != null) {
            eCPrivateKeyStructure = new ECPrivateKeyStructure(getS(), this.e, x962Parameters2);
        } else {
            eCPrivateKeyStructure = new ECPrivateKeyStructure(getS(), x962Parameters2);
        }
        try {
            PrivateKeyInfo privateKeyInfo;
            if (this.a.equals("ECGOST3410")) {
                privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.j, x962Parameters2.a()), eCPrivateKeyStructure.a());
            } else {
                privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.k, x962Parameters2.a()), eCPrivateKeyStructure.a());
            }
            return privateKeyInfo.a("DER");
        } catch (IOException e) {
            return null;
        }
    }

    public ECParameterSpec getParams() {
        return this.c;
    }

    public org.spongycastle.jce.spec.ECParameterSpec b() {
        if (this.c == null) {
            return null;
        }
        return EC5Util.a(this.c, this.d);
    }

    org.spongycastle.jce.spec.ECParameterSpec c() {
        if (this.c != null) {
            return EC5Util.a(this.c, this.d);
        }
        return BouncyCastleProvider.a.a();
    }

    public BigInteger getS() {
        return this.b;
    }

    public BigInteger d() {
        return this.b;
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.f.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.f.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.f.a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JCEECPrivateKey)) {
            return false;
        }
        JCEECPrivateKey jCEECPrivateKey = (JCEECPrivateKey) obj;
        if (d().equals(jCEECPrivateKey.d()) && c().equals(jCEECPrivateKey.c())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return d().hashCode() ^ c().hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("EC Private Key").append(property);
        stringBuffer.append("             S: ").append(this.b.toString(16)).append(property);
        return stringBuffer.toString();
    }
}
