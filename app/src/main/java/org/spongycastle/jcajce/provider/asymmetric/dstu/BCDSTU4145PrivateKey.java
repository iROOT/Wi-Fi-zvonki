package org.spongycastle.jcajce.provider.asymmetric.dstu;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.ua.DSTU4145NamedCurves;
import org.spongycastle.asn1.ua.UAObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.ec.ECCurve;

public class BCDSTU4145PrivateKey implements ECPrivateKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    private String a = "DSTU4145";
    private boolean b;
    private transient BigInteger c;
    private transient ECParameterSpec d;
    private transient DERBitString e;
    private transient PKCS12BagAttributeCarrierImpl f = new PKCS12BagAttributeCarrierImpl();

    protected BCDSTU4145PrivateKey() {
    }

    public BCDSTU4145PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.c = eCPrivateKeySpec.b();
        if (eCPrivateKeySpec.a() != null) {
            this.d = EC5Util.a(EC5Util.a(eCPrivateKeySpec.a().b(), eCPrivateKeySpec.a().f()), eCPrivateKeySpec.a());
        } else {
            this.d = null;
        }
    }

    public BCDSTU4145PrivateKey(java.security.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.c = eCPrivateKeySpec.getS();
        this.d = eCPrivateKeySpec.getParams();
    }

    public BCDSTU4145PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCDSTU4145PublicKey bCDSTU4145PublicKey, ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPrivateKeyParameters.b();
        this.a = str;
        this.c = eCPrivateKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = new ECParameterSpec(EC5Util.a(b.a(), b.e()), new ECPoint(b.b().c().a(), b.b().d().a()), b.c(), b.d().intValue());
        } else {
            this.d = eCParameterSpec;
        }
        this.e = a(bCDSTU4145PublicKey);
    }

    public BCDSTU4145PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCDSTU4145PublicKey bCDSTU4145PublicKey, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPrivateKeyParameters.b();
        this.a = str;
        this.c = eCPrivateKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = new ECParameterSpec(EC5Util.a(b.a(), b.e()), new ECPoint(b.b().c().a(), b.b().d().a()), b.c(), b.d().intValue());
        } else {
            this.d = new ECParameterSpec(EC5Util.a(eCParameterSpec.b(), eCParameterSpec.f()), new ECPoint(eCParameterSpec.c().c().a(), eCParameterSpec.c().d().a()), eCParameterSpec.d(), eCParameterSpec.e().intValue());
        }
        this.e = a(bCDSTU4145PublicKey);
    }

    public BCDSTU4145PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.a = str;
        this.c = eCPrivateKeyParameters.c();
        this.d = null;
    }

    BCDSTU4145PrivateKey(PrivateKeyInfo privateKeyInfo) {
        a(privateKeyInfo);
    }

    private void a(PrivateKeyInfo privateKeyInfo) {
        X962Parameters x962Parameters = new X962Parameters((ASN1Primitive) privateKeyInfo.d().f());
        if (x962Parameters.d()) {
            ASN1ObjectIdentifier a = DERObjectIdentifier.a((Object) x962Parameters.f());
            X9ECParameters a2 = ECUtil.a(a);
            if (a2 == null) {
                ECDomainParameters a3 = DSTU4145NamedCurves.a(a);
                this.d = new ECNamedCurveSpec(a.d(), EC5Util.a(a3.a(), a3.e()), new ECPoint(a3.b().c().a(), a3.b().d().a()), a3.c(), a3.d());
            } else {
                this.d = new ECNamedCurveSpec(ECUtil.b(a), EC5Util.a(a2.d(), a2.h()), new ECPoint(a2.e().c().a(), a2.e().d().a()), a2.f(), a2.g());
            }
        } else if (x962Parameters.e()) {
            this.d = null;
        } else {
            X9ECParameters a4 = X9ECParameters.a(x962Parameters.f());
            this.d = new ECParameterSpec(EC5Util.a(a4.d(), a4.h()), new ECPoint(a4.e().c().a(), a4.e().d().a()), a4.f(), a4.g().intValue());
        }
        Object f = privateKeyInfo.f();
        if (f instanceof DERInteger) {
            this.c = DERInteger.a(f).d();
            return;
        }
        org.spongycastle.asn1.sec.ECPrivateKey a5 = org.spongycastle.asn1.sec.ECPrivateKey.a(f);
        this.c = a5.d();
        this.e = a5.e();
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        ASN1Encodable x962Parameters;
        org.spongycastle.asn1.sec.ECPrivateKey eCPrivateKey;
        if (this.d instanceof ECNamedCurveSpec) {
            ASN1Primitive a = ECUtil.a(((ECNamedCurveSpec) this.d).a());
            if (a == null) {
                a = new DERObjectIdentifier(((ECNamedCurveSpec) this.d).a());
            }
            x962Parameters = new X962Parameters(a);
        } else if (this.d == null) {
            x962Parameters = new X962Parameters(DERNull.a);
        } else {
            ECCurve a2 = EC5Util.a(this.d.getCurve());
            Object x962Parameters2 = new X962Parameters(new X9ECParameters(a2, EC5Util.a(a2, this.d.getGenerator(), this.b), this.d.getOrder(), BigInteger.valueOf((long) this.d.getCofactor()), this.d.getCurve().getSeed()));
        }
        if (this.e != null) {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(getS(), this.e, x962Parameters2);
        } else {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(getS(), x962Parameters2);
        }
        try {
            PrivateKeyInfo privateKeyInfo;
            if (this.a.equals("DSTU4145")) {
                privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(UAObjectIdentifiers.c, x962Parameters2.a()), eCPrivateKey.a());
            } else {
                privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.k, x962Parameters2.a()), eCPrivateKey.a());
            }
            return privateKeyInfo.a("DER");
        } catch (IOException e) {
            return null;
        }
    }

    public ECParameterSpec getParams() {
        return this.d;
    }

    public org.spongycastle.jce.spec.ECParameterSpec b() {
        if (this.d == null) {
            return null;
        }
        return EC5Util.a(this.d, this.b);
    }

    org.spongycastle.jce.spec.ECParameterSpec c() {
        if (this.d != null) {
            return EC5Util.a(this.d, this.b);
        }
        return BouncyCastleProvider.a.a();
    }

    public BigInteger getS() {
        return this.c;
    }

    public BigInteger d() {
        return this.c;
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
        if (!(obj instanceof BCDSTU4145PrivateKey)) {
            return false;
        }
        BCDSTU4145PrivateKey bCDSTU4145PrivateKey = (BCDSTU4145PrivateKey) obj;
        if (d().equals(bCDSTU4145PrivateKey.d()) && c().equals(bCDSTU4145PrivateKey.c())) {
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
        stringBuffer.append("             S: ").append(this.c.toString(16)).append(property);
        return stringBuffer.toString();
    }

    private DERBitString a(BCDSTU4145PublicKey bCDSTU4145PublicKey) {
        try {
            return SubjectPublicKeyInfo.a(ASN1Primitive.a(bCDSTU4145PublicKey.getEncoded())).g();
        } catch (IOException e) {
            return null;
        }
    }
}
