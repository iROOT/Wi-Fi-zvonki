package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.asn1.x9.X9ECPoint;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.ec.ECPoint.F2m;
import org.spongycastle.math.ec.ECPoint.Fp;

public class JCEECPublicKey implements ECPublicKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPublicKey {
    private String a;
    private ECPoint b;
    private ECParameterSpec c;
    private boolean d;
    private GOST3410PublicKeyAlgParameters e;

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        SubjectPublicKeyInfo subjectPublicKeyInfo;
        if (this.a.equals("ECGOST3410")) {
            ASN1Encodable aSN1Encodable;
            if (this.e != null) {
                aSN1Encodable = this.e;
            } else if (this.c instanceof ECNamedCurveSpec) {
                aSN1Encodable = new GOST3410PublicKeyAlgParameters(ECGOST3410NamedCurves.b(((ECNamedCurveSpec) this.c).a()), CryptoProObjectIdentifiers.m);
            } else {
                ECCurve a = EC5Util.a(this.c.getCurve());
                aSN1Encodable = new X962Parameters(new X9ECParameters(a, EC5Util.a(a, this.c.getGenerator(), this.d), this.c.getOrder(), BigInteger.valueOf((long) this.c.getCofactor()), this.c.getCurve().getSeed()));
            }
            BigInteger a2 = this.b.c().a();
            BigInteger a3 = this.b.d().a();
            byte[] bArr = new byte[64];
            a(bArr, 0, a2);
            a(bArr, 32, a3);
            try {
                subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.j, aSN1Encodable), new DEROctetString(bArr));
            } catch (IOException e) {
                return null;
            }
        }
        ASN1Encodable x962Parameters;
        if (this.c instanceof ECNamedCurveSpec) {
            ASN1ObjectIdentifier a4 = ECUtil.a(((ECNamedCurveSpec) this.c).a());
            if (a4 == null) {
                a4 = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.c).a());
            }
            x962Parameters = new X962Parameters(a4);
        } else if (this.c == null) {
            Object x962Parameters2 = new X962Parameters(DERNull.a);
        } else {
            a = EC5Util.a(this.c.getCurve());
            x962Parameters2 = new X962Parameters(new X9ECParameters(a, EC5Util.a(a, this.c.getGenerator(), this.d), this.c.getOrder(), BigInteger.valueOf((long) this.c.getCofactor()), this.c.getCurve().getSeed()));
        }
        subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(X9ObjectIdentifiers.k, x962Parameters2), ((ASN1OctetString) new X9ECPoint(a().a().a(c().c().a(), c().d().a(), this.d)).a()).e());
        return KeyUtil.a(subjectPublicKeyInfo);
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
        return this.c;
    }

    public org.spongycastle.jce.spec.ECParameterSpec b() {
        if (this.c == null) {
            return null;
        }
        return EC5Util.a(this.c, this.d);
    }

    public java.security.spec.ECPoint getW() {
        return new java.security.spec.ECPoint(this.b.c().a(), this.b.d().a());
    }

    public ECPoint c() {
        if (this.c != null) {
            return this.b;
        }
        if (this.b instanceof Fp) {
            return new Fp(null, this.b.c(), this.b.d());
        }
        return new F2m(null, this.b.c(), this.b.d());
    }

    public ECPoint a() {
        return this.b;
    }

    org.spongycastle.jce.spec.ECParameterSpec d() {
        if (this.c != null) {
            return EC5Util.a(this.c, this.d);
        }
        return BouncyCastleProvider.a.a();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("EC Public Key").append(property);
        stringBuffer.append("            X: ").append(this.b.c().a().toString(16)).append(property);
        stringBuffer.append("            Y: ").append(this.b.d().a().toString(16)).append(property);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof JCEECPublicKey)) {
            return false;
        }
        JCEECPublicKey jCEECPublicKey = (JCEECPublicKey) obj;
        if (a().a(jCEECPublicKey.a()) && d().equals(jCEECPublicKey.d())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return a().hashCode() ^ d().hashCode();
    }
}
