package org.spongycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
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
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.ec.ECCurve;

public class BCECPrivateKey implements ECPrivateKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    private String a = "EC";
    private boolean b;
    private transient BigInteger c;
    private transient ECParameterSpec d;
    private transient ProviderConfiguration e;
    private transient DERBitString f;
    private transient PKCS12BagAttributeCarrierImpl g = new PKCS12BagAttributeCarrierImpl();

    protected BCECPrivateKey() {
    }

    public BCECPrivateKey(ECPrivateKey eCPrivateKey, ProviderConfiguration providerConfiguration) {
        this.c = eCPrivateKey.getS();
        this.a = eCPrivateKey.getAlgorithm();
        this.d = eCPrivateKey.getParams();
        this.e = providerConfiguration;
    }

    public BCECPrivateKey(String str, ECPrivateKeySpec eCPrivateKeySpec, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.c = eCPrivateKeySpec.b();
        if (eCPrivateKeySpec.a() != null) {
            this.d = EC5Util.a(EC5Util.a(eCPrivateKeySpec.a().b(), eCPrivateKeySpec.a().f()), eCPrivateKeySpec.a());
        } else {
            this.d = null;
        }
        this.e = providerConfiguration;
    }

    public BCECPrivateKey(String str, java.security.spec.ECPrivateKeySpec eCPrivateKeySpec, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.c = eCPrivateKeySpec.getS();
        this.d = eCPrivateKeySpec.getParams();
        this.e = providerConfiguration;
    }

    public BCECPrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECPublicKey bCECPublicKey, ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        ECDomainParameters b = eCPrivateKeyParameters.b();
        this.a = str;
        this.c = eCPrivateKeyParameters.c();
        this.e = providerConfiguration;
        if (eCParameterSpec == null) {
            this.d = new ECParameterSpec(EC5Util.a(b.a(), b.e()), new ECPoint(b.b().c().a(), b.b().d().a()), b.c(), b.d().intValue());
        } else {
            this.d = eCParameterSpec;
        }
        this.f = a(bCECPublicKey);
    }

    public BCECPrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECPublicKey bCECPublicKey, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        ECDomainParameters b = eCPrivateKeyParameters.b();
        this.a = str;
        this.c = eCPrivateKeyParameters.c();
        this.e = providerConfiguration;
        if (eCParameterSpec == null) {
            this.d = new ECParameterSpec(EC5Util.a(b.a(), b.e()), new ECPoint(b.b().c().a(), b.b().d().a()), b.c(), b.d().intValue());
        } else {
            this.d = EC5Util.a(EC5Util.a(eCParameterSpec.b(), eCParameterSpec.f()), eCParameterSpec);
        }
        this.f = a(bCECPublicKey);
    }

    public BCECPrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.c = eCPrivateKeyParameters.c();
        this.d = null;
        this.e = providerConfiguration;
    }

    BCECPrivateKey(String str, PrivateKeyInfo privateKeyInfo, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.e = providerConfiguration;
        a(privateKeyInfo);
    }

    private void a(PrivateKeyInfo privateKeyInfo) {
        X962Parameters a = X962Parameters.a(privateKeyInfo.d().f());
        if (a.d()) {
            ASN1ObjectIdentifier a2 = DERObjectIdentifier.a((Object) a.f());
            X9ECParameters a3 = ECUtil.a(a2);
            this.d = new ECNamedCurveSpec(ECUtil.b(a2), EC5Util.a(a3.d(), a3.h()), new ECPoint(a3.e().c().a(), a3.e().d().a()), a3.f(), a3.g());
        } else if (a.e()) {
            this.d = null;
        } else {
            X9ECParameters a4 = X9ECParameters.a(a.f());
            this.d = new ECParameterSpec(EC5Util.a(a4.d(), a4.h()), new ECPoint(a4.e().c().a(), a4.e().d().a()), a4.f(), a4.g().intValue());
        }
        Object f = privateKeyInfo.f();
        if (f instanceof ASN1Integer) {
            this.c = DERInteger.a(f).d();
            return;
        }
        org.spongycastle.asn1.sec.ECPrivateKey a5 = org.spongycastle.asn1.sec.ECPrivateKey.a(f);
        this.c = a5.d();
        this.f = a5.e();
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        ASN1Encodable x962Parameters;
        ASN1Encodable eCPrivateKey;
        if (this.d instanceof ECNamedCurveSpec) {
            ASN1ObjectIdentifier a = ECUtil.a(((ECNamedCurveSpec) this.d).a());
            if (a == null) {
                a = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.d).a());
            }
            x962Parameters = new X962Parameters(a);
        } else if (this.d == null) {
            x962Parameters = new X962Parameters(DERNull.a);
        } else {
            ECCurve a2 = EC5Util.a(this.d.getCurve());
            Object x962Parameters2 = new X962Parameters(new X9ECParameters(a2, EC5Util.a(a2, this.d.getGenerator(), this.b), this.d.getOrder(), BigInteger.valueOf((long) this.d.getCofactor()), this.d.getCurve().getSeed()));
        }
        if (this.f != null) {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(getS(), this.f, x962Parameters2);
        } else {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(getS(), x962Parameters2);
        }
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.k, x962Parameters2), eCPrivateKey).a("DER");
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
        return this.e.a();
    }

    public BigInteger getS() {
        return this.c;
    }

    public BigInteger d() {
        return this.c;
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.g.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.g.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.g.a();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECPrivateKey)) {
            return false;
        }
        BCECPrivateKey bCECPrivateKey = (BCECPrivateKey) obj;
        if (d().equals(bCECPrivateKey.d()) && c().equals(bCECPrivateKey.c())) {
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

    private DERBitString a(BCECPublicKey bCECPublicKey) {
        try {
            return SubjectPublicKeyInfo.a(ASN1Primitive.a(bCECPublicKey.getEncoded())).g();
        } catch (IOException e) {
            return null;
        }
    }
}
