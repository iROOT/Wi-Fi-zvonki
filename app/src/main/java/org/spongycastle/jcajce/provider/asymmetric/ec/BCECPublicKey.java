package org.spongycastle.jcajce.provider.asymmetric.ec;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ECPoint;
import org.spongycastle.asn1.x9.X9IntegerConverter;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.ECPoint.F2m;
import org.spongycastle.math.ec.ECPoint.Fp;

public class BCECPublicKey implements ECPublicKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPublicKey {
    private String a = "EC";
    private boolean b;
    private transient ECPoint c;
    private transient ECParameterSpec d;
    private transient ProviderConfiguration e;

    public BCECPublicKey(String str, ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.d = eCPublicKeySpec.getParams();
        this.c = EC5Util.a(this.d, eCPublicKeySpec.getW(), false);
        this.e = providerConfiguration;
    }

    public BCECPublicKey(String str, org.spongycastle.jce.spec.ECPublicKeySpec eCPublicKeySpec, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.c = eCPublicKeySpec.b();
        if (eCPublicKeySpec.a() != null) {
            this.d = EC5Util.a(EC5Util.a(eCPublicKeySpec.a().b(), eCPublicKeySpec.a().f()), eCPublicKeySpec.a());
        } else {
            if (this.c.a() == null) {
                this.c = providerConfiguration.a().b().a(this.c.e().a(), this.c.f().a(), false);
            }
            this.d = null;
        }
        this.e = providerConfiguration;
    }

    public BCECPublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        ECDomainParameters b = eCPublicKeyParameters.b();
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = a(EC5Util.a(b.a(), b.e()), b);
        } else {
            this.d = eCParameterSpec;
        }
        this.e = providerConfiguration;
    }

    public BCECPublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec, ProviderConfiguration providerConfiguration) {
        ECDomainParameters b = eCPublicKeyParameters.b();
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = a(EC5Util.a(b.a(), b.e()), b);
        } else {
            this.d = EC5Util.a(EC5Util.a(eCParameterSpec.b(), eCParameterSpec.f()), eCParameterSpec);
        }
        this.e = providerConfiguration;
    }

    public BCECPublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        this.d = null;
        this.e = providerConfiguration;
    }

    public BCECPublicKey(ECPublicKey eCPublicKey, ProviderConfiguration providerConfiguration) {
        this.a = eCPublicKey.getAlgorithm();
        this.d = eCPublicKey.getParams();
        this.c = EC5Util.a(this.d, eCPublicKey.getW(), false);
    }

    BCECPublicKey(String str, SubjectPublicKeyInfo subjectPublicKeyInfo, ProviderConfiguration providerConfiguration) {
        this.a = str;
        this.e = providerConfiguration;
        a(subjectPublicKeyInfo);
    }

    private ECParameterSpec a(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new java.security.spec.ECPoint(eCDomainParameters.b().c().a(), eCDomainParameters.b().d().a()), eCDomainParameters.c(), eCDomainParameters.d().intValue());
    }

    private void a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        ECCurve eCCurve;
        X962Parameters x962Parameters = new X962Parameters((ASN1Primitive) subjectPublicKeyInfo.d().f());
        if (x962Parameters.d()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) x962Parameters.f();
            X9ECParameters a = ECUtil.a(aSN1ObjectIdentifier);
            ECCurve d = a.d();
            this.d = new ECNamedCurveSpec(ECUtil.b(aSN1ObjectIdentifier), EC5Util.a(d, a.h()), new java.security.spec.ECPoint(a.e().c().a(), a.e().d().a()), a.f(), a.g());
            eCCurve = d;
        } else if (x962Parameters.e()) {
            this.d = null;
            eCCurve = this.e.a().b();
        } else {
            X9ECParameters a2 = X9ECParameters.a(x962Parameters.f());
            ECCurve d2 = a2.d();
            this.d = new ECParameterSpec(EC5Util.a(d2, a2.h()), new java.security.spec.ECPoint(a2.e().c().a(), a2.e().d().a()), a2.f(), a2.g().intValue());
            eCCurve = d2;
        }
        byte[] d3 = subjectPublicKeyInfo.g().d();
        ASN1OctetString dEROctetString = new DEROctetString(d3);
        if (d3[0] == (byte) 4 && d3[1] == d3.length - 2 && ((d3[2] == (byte) 2 || d3[2] == (byte) 3) && new X9IntegerConverter().a(eCCurve) >= d3.length - 3)) {
            try {
                dEROctetString = (ASN1OctetString) ASN1Primitive.a(d3);
            } catch (IOException e) {
                throw new IllegalArgumentException("error recovering public key");
            }
        }
        this.c = new X9ECPoint(eCCurve, dEROctetString).d();
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        ASN1Encodable x962Parameters;
        ASN1OctetString aSN1OctetString;
        if (this.d instanceof ECNamedCurveSpec) {
            ASN1ObjectIdentifier a = ECUtil.a(((ECNamedCurveSpec) this.d).a());
            if (a == null) {
                a = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.d).a());
            }
            x962Parameters = new X962Parameters(a);
        } else if (this.d == null) {
            Object x962Parameters2 = new X962Parameters(DERNull.a);
        } else {
            ECCurve a2 = EC5Util.a(this.d.getCurve());
            x962Parameters2 = new X962Parameters(new X9ECParameters(a2, EC5Util.a(a2, this.d.getGenerator(), this.b), this.d.getOrder(), BigInteger.valueOf((long) this.d.getCofactor()), this.d.getCurve().getSeed()));
        }
        ECCurve a3 = a().a();
        if (this.d == null) {
            aSN1OctetString = (ASN1OctetString) new X9ECPoint(a3.a(c().e().a(), c().f().a(), this.b)).a();
        } else {
            aSN1OctetString = (ASN1OctetString) new X9ECPoint(a3.a(c().c().a(), c().d().a(), this.b)).a();
        }
        return KeyUtil.a(new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.k, x962Parameters2), aSN1OctetString.e()));
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

    public java.security.spec.ECPoint getW() {
        return new java.security.spec.ECPoint(this.c.c().a(), this.c.d().a());
    }

    public ECPoint c() {
        if (this.d != null) {
            return this.c;
        }
        if (this.c instanceof Fp) {
            return new Fp(null, this.c.c(), this.c.d());
        }
        return new F2m(null, this.c.c(), this.c.d());
    }

    public ECPoint a() {
        return this.c;
    }

    org.spongycastle.jce.spec.ECParameterSpec d() {
        if (this.d != null) {
            return EC5Util.a(this.d, this.b);
        }
        return this.e.a();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("EC Public Key").append(property);
        stringBuffer.append("            X: ").append(this.c.c().a().toString(16)).append(property);
        stringBuffer.append("            Y: ").append(this.c.d().a().toString(16)).append(property);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCECPublicKey)) {
            return false;
        }
        BCECPublicKey bCECPublicKey = (BCECPublicKey) obj;
        if (a().a(bCECPublicKey.a()) && d().equals(bCECPublicKey.d())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return a().hashCode() ^ d().hashCode();
    }
}
