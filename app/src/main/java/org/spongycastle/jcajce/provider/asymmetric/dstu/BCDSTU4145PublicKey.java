package org.spongycastle.jcajce.provider.asymmetric.dstu;

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
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.ua.DSTU4145BinaryField;
import org.spongycastle.asn1.ua.DSTU4145ECBinary;
import org.spongycastle.asn1.ua.DSTU4145NamedCurves;
import org.spongycastle.asn1.ua.DSTU4145Params;
import org.spongycastle.asn1.ua.DSTU4145PointEncoder;
import org.spongycastle.asn1.ua.UAObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.F2m;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.ECPoint.Fp;

public class BCDSTU4145PublicKey implements ECPublicKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPublicKey {
    private String a = "DSTU4145";
    private boolean b;
    private transient ECPoint c;
    private transient ECParameterSpec d;
    private transient DSTU4145Params e;

    public BCDSTU4145PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.d = eCPublicKeySpec.getParams();
        this.c = EC5Util.a(this.d, eCPublicKeySpec.getW(), false);
    }

    public BCDSTU4145PublicKey(org.spongycastle.jce.spec.ECPublicKeySpec eCPublicKeySpec) {
        this.c = eCPublicKeySpec.b();
        if (eCPublicKeySpec.a() != null) {
            this.d = EC5Util.a(EC5Util.a(eCPublicKeySpec.a().b(), eCPublicKeySpec.a().f()), eCPublicKeySpec.a());
            return;
        }
        if (this.c.a() == null) {
            this.c = BouncyCastleProvider.a.a().b().a(this.c.c().a(), this.c.d().a());
        }
        this.d = null;
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPublicKeyParameters.b();
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = a(EC5Util.a(b.a(), b.e()), b);
        } else {
            this.d = eCParameterSpec;
        }
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPublicKeyParameters.b();
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = a(EC5Util.a(b.a(), b.e()), b);
        } else {
            this.d = EC5Util.a(EC5Util.a(eCParameterSpec.b(), eCParameterSpec.f()), eCParameterSpec);
        }
    }

    public BCDSTU4145PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters) {
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        this.d = null;
    }

    private ECParameterSpec a(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new java.security.spec.ECPoint(eCDomainParameters.b().c().a(), eCDomainParameters.b().d().a()), eCDomainParameters.c(), eCDomainParameters.d().intValue());
    }

    BCDSTU4145PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        a(subjectPublicKeyInfo);
    }

    private void a(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - 1) - i];
            bArr[(bArr.length - 1) - i] = b;
        }
    }

    private void a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        DERBitString g = subjectPublicKeyInfo.g();
        this.a = "DSTU4145";
        try {
            org.spongycastle.jce.spec.ECParameterSpec eCNamedCurveParameterSpec;
            ECCurve f2m;
            byte[] e = ((ASN1OctetString) ASN1Primitive.a(g.d())).e();
            if (subjectPublicKeyInfo.d().e().equals(UAObjectIdentifiers.b)) {
                a(e);
            }
            this.e = DSTU4145Params.a((ASN1Sequence) subjectPublicKeyInfo.d().f());
            if (this.e.d()) {
                ASN1ObjectIdentifier h = this.e.h();
                ECDomainParameters a = DSTU4145NamedCurves.a(h);
                eCNamedCurveParameterSpec = new ECNamedCurveParameterSpec(h.d(), a.a(), a.b(), a.c(), a.d(), a.e());
            } else {
                DSTU4145ECBinary e2 = this.e.e();
                byte[] f = e2.f();
                if (subjectPublicKeyInfo.d().e().equals(UAObjectIdentifiers.b)) {
                    a(f);
                }
                DSTU4145BinaryField d = e2.d();
                f2m = new F2m(d.d(), d.e(), d.f(), d.g(), e2.e(), new BigInteger(1, f));
                byte[] h2 = e2.h();
                if (subjectPublicKeyInfo.d().e().equals(UAObjectIdentifiers.b)) {
                    a(h2);
                }
                eCNamedCurveParameterSpec = new org.spongycastle.jce.spec.ECParameterSpec(f2m, DSTU4145PointEncoder.a(f2m, h2), e2.g());
            }
            f2m = eCNamedCurveParameterSpec.b();
            EllipticCurve a2 = EC5Util.a(f2m, eCNamedCurveParameterSpec.f());
            this.c = DSTU4145PointEncoder.a(f2m, e);
            if (this.e.d()) {
                this.d = new ECNamedCurveSpec(this.e.h().d(), a2, new java.security.spec.ECPoint(eCNamedCurveParameterSpec.c().c().a(), eCNamedCurveParameterSpec.c().d().a()), eCNamedCurveParameterSpec.d(), eCNamedCurveParameterSpec.e());
            } else {
                this.d = new ECParameterSpec(a2, new java.security.spec.ECPoint(eCNamedCurveParameterSpec.c().c().a(), eCNamedCurveParameterSpec.c().d().a()), eCNamedCurveParameterSpec.d(), eCNamedCurveParameterSpec.e().intValue());
            }
        } catch (IOException e3) {
            throw new IllegalArgumentException("error recovering public key");
        }
    }

    public byte[] a() {
        if (this.e != null) {
            return this.e.f();
        }
        return DSTU4145Params.g();
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        if (this.e != null) {
            ASN1Encodable aSN1Encodable = this.e;
        } else if (this.d instanceof ECNamedCurveSpec) {
            aSN1Encodable = new DSTU4145Params(new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.d).a()));
        } else {
            ECCurve a = EC5Util.a(this.d.getCurve());
            aSN1Encodable = new X962Parameters(new X9ECParameters(a, EC5Util.a(a, this.d.getGenerator(), this.b), this.d.getOrder(), BigInteger.valueOf((long) this.d.getCofactor()), this.d.getCurve().getSeed()));
        }
        try {
            return KeyUtil.a(new SubjectPublicKeyInfo(new AlgorithmIdentifier(UAObjectIdentifiers.c, aSN1Encodable), new DEROctetString(DSTU4145PointEncoder.a(this.c))));
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
        return new ECPoint.F2m(null, this.c.c(), this.c.d());
    }

    public ECPoint d() {
        return this.c;
    }

    org.spongycastle.jce.spec.ECParameterSpec e() {
        if (this.d != null) {
            return EC5Util.a(this.d, this.b);
        }
        return BouncyCastleProvider.a.a();
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
        if (!(obj instanceof BCDSTU4145PublicKey)) {
            return false;
        }
        BCDSTU4145PublicKey bCDSTU4145PublicKey = (BCDSTU4145PublicKey) obj;
        if (d().a(bCDSTU4145PublicKey.d()) && e().equals(bCDSTU4145PublicKey.e())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return d().hashCode() ^ e().hashCode();
    }
}
