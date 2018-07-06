package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.ECGOST3410NamedCurveTable;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.ECPoint.F2m;
import org.spongycastle.math.ec.ECPoint.Fp;

public class BCECGOST3410PublicKey implements ECPublicKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPublicKey {
    private String a = "ECGOST3410";
    private boolean b;
    private transient ECPoint c;
    private transient ECParameterSpec d;
    private transient GOST3410PublicKeyAlgParameters e;

    public BCECGOST3410PublicKey(ECPublicKeySpec eCPublicKeySpec) {
        this.d = eCPublicKeySpec.getParams();
        this.c = EC5Util.a(this.d, eCPublicKeySpec.getW(), false);
    }

    public BCECGOST3410PublicKey(org.spongycastle.jce.spec.ECPublicKeySpec eCPublicKeySpec) {
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

    public BCECGOST3410PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPublicKeyParameters.b();
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = a(EC5Util.a(b.a(), b.e()), b);
        } else {
            this.d = eCParameterSpec;
        }
    }

    public BCECGOST3410PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPublicKeyParameters.b();
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        if (eCParameterSpec == null) {
            this.d = a(EC5Util.a(b.a(), b.e()), b);
        } else {
            this.d = EC5Util.a(EC5Util.a(eCParameterSpec.b(), eCParameterSpec.f()), eCParameterSpec);
        }
    }

    public BCECGOST3410PublicKey(String str, ECPublicKeyParameters eCPublicKeyParameters) {
        this.a = str;
        this.c = eCPublicKeyParameters.c();
        this.d = null;
    }

    private ECParameterSpec a(EllipticCurve ellipticCurve, ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(ellipticCurve, new java.security.spec.ECPoint(eCDomainParameters.b().c().a(), eCDomainParameters.b().d().a()), eCDomainParameters.c(), eCDomainParameters.d().intValue());
    }

    BCECGOST3410PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        a(subjectPublicKeyInfo);
    }

    private void a(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        int i = 0;
        DERBitString g = subjectPublicKeyInfo.g();
        this.a = "ECGOST3410";
        try {
            byte[] e = ((ASN1OctetString) ASN1Primitive.a(g.d())).e();
            byte[] bArr = new byte[32];
            byte[] bArr2 = new byte[32];
            for (int i2 = 0; i2 != bArr.length; i2++) {
                bArr[i2] = e[31 - i2];
            }
            while (i != bArr2.length) {
                bArr2[i] = e[63 - i];
                i++;
            }
            this.e = GOST3410PublicKeyAlgParameters.a(subjectPublicKeyInfo.d().f());
            ECNamedCurveParameterSpec a = ECGOST3410NamedCurveTable.a(ECGOST3410NamedCurves.b(this.e.d()));
            ECCurve b = a.b();
            EllipticCurve a2 = EC5Util.a(b, a.f());
            this.c = b.a(new BigInteger(1, bArr), new BigInteger(1, bArr2));
            this.d = new ECNamedCurveSpec(ECGOST3410NamedCurves.b(this.e.d()), a2, new java.security.spec.ECPoint(a.c().c().a(), a.c().d().a()), a.d(), a.e());
        } catch (IOException e2) {
            throw new IllegalArgumentException("error recovering public key");
        }
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
            aSN1Encodable = new GOST3410PublicKeyAlgParameters(ECGOST3410NamedCurves.b(((ECNamedCurveSpec) this.d).a()), CryptoProObjectIdentifiers.m);
        } else {
            ECCurve a = EC5Util.a(this.d.getCurve());
            aSN1Encodable = new X962Parameters(new X9ECParameters(a, EC5Util.a(a, this.d.getGenerator(), this.b), this.d.getOrder(), BigInteger.valueOf((long) this.d.getCofactor()), this.d.getCurve().getSeed()));
        }
        BigInteger a2 = this.c.c().a();
        BigInteger a3 = this.c.d().a();
        byte[] bArr = new byte[64];
        a(bArr, 0, a2);
        a(bArr, 32, a3);
        try {
            return KeyUtil.a(new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.j, aSN1Encodable), new DEROctetString(bArr)));
        } catch (IOException e) {
            return null;
        }
    }

    private void a(byte[] bArr, int i, BigInteger bigInteger) {
        Object obj;
        Object toByteArray = bigInteger.toByteArray();
        if (toByteArray.length < 32) {
            obj = new byte[32];
            System.arraycopy(toByteArray, 0, obj, obj.length - toByteArray.length, toByteArray.length);
        } else {
            obj = toByteArray;
        }
        for (int i2 = 0; i2 != 32; i2++) {
            bArr[i + i2] = obj[(obj.length - 1) - i2];
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
        return new F2m(null, this.c.c(), this.c.d());
    }

    public ECPoint a() {
        return this.c;
    }

    org.spongycastle.jce.spec.ECParameterSpec d() {
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
        if (!(obj instanceof BCECGOST3410PublicKey)) {
            return false;
        }
        BCECGOST3410PublicKey bCECGOST3410PublicKey = (BCECGOST3410PublicKey) obj;
        if (a().a(bCECGOST3410PublicKey.a()) && d().equals(bCECGOST3410PublicKey.d())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return a().hashCode() ^ d().hashCode();
    }

    public GOST3410PublicKeyAlgParameters e() {
        return this.e;
    }
}
