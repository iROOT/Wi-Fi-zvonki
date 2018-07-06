package org.spongycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERInteger;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.ECGOST3410NamedCurves;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X962Parameters;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.ECGOST3410NamedCurveTable;
import org.spongycastle.jce.interfaces.ECPointEncoder;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.ec.ECCurve;

public class BCECGOST3410PrivateKey implements ECPrivateKey, ECPointEncoder, org.spongycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    private String a = "ECGOST3410";
    private boolean b;
    private transient GOST3410PublicKeyAlgParameters c;
    private transient BigInteger d;
    private transient ECParameterSpec e;
    private transient DERBitString f;
    private transient PKCS12BagAttributeCarrierImpl g = new PKCS12BagAttributeCarrierImpl();

    protected BCECGOST3410PrivateKey() {
    }

    public BCECGOST3410PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.d = eCPrivateKeySpec.b();
        if (eCPrivateKeySpec.a() != null) {
            this.e = EC5Util.a(EC5Util.a(eCPrivateKeySpec.a().b(), eCPrivateKeySpec.a().f()), eCPrivateKeySpec.a());
        } else {
            this.e = null;
        }
    }

    public BCECGOST3410PrivateKey(java.security.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.d = eCPrivateKeySpec.getS();
        this.e = eCPrivateKeySpec.getParams();
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410PublicKey bCECGOST3410PublicKey, ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPrivateKeyParameters.b();
        this.a = str;
        this.d = eCPrivateKeyParameters.c();
        if (eCParameterSpec == null) {
            this.e = new ECParameterSpec(EC5Util.a(b.a(), b.e()), new ECPoint(b.b().c().a(), b.b().d().a()), b.c(), b.d().intValue());
        } else {
            this.e = eCParameterSpec;
        }
        this.c = bCECGOST3410PublicKey.e();
        this.f = a(bCECGOST3410PublicKey);
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410PublicKey bCECGOST3410PublicKey, org.spongycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECDomainParameters b = eCPrivateKeyParameters.b();
        this.a = str;
        this.d = eCPrivateKeyParameters.c();
        if (eCParameterSpec == null) {
            this.e = new ECParameterSpec(EC5Util.a(b.a(), b.e()), new ECPoint(b.b().c().a(), b.b().d().a()), b.c(), b.d().intValue());
        } else {
            this.e = new ECParameterSpec(EC5Util.a(eCParameterSpec.b(), eCParameterSpec.f()), new ECPoint(eCParameterSpec.c().c().a(), eCParameterSpec.c().d().a()), eCParameterSpec.d(), eCParameterSpec.e().intValue());
        }
        this.c = bCECGOST3410PublicKey.e();
        this.f = a(bCECGOST3410PublicKey);
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.a = str;
        this.d = eCPrivateKeyParameters.c();
        this.e = null;
    }

    BCECGOST3410PrivateKey(PrivateKeyInfo privateKeyInfo) {
        a(privateKeyInfo);
    }

    private void a(PrivateKeyInfo privateKeyInfo) {
        Object a = privateKeyInfo.d().f().a();
        if ((a instanceof ASN1Sequence) && (ASN1Sequence.a(a).f() == 2 || ASN1Sequence.a(a).f() == 3)) {
            this.c = GOST3410PublicKeyAlgParameters.a(privateKeyInfo.d().f());
            ECNamedCurveParameterSpec a2 = ECGOST3410NamedCurveTable.a(ECGOST3410NamedCurves.b(this.c.d()));
            this.e = new ECNamedCurveSpec(ECGOST3410NamedCurves.b(this.c.d()), EC5Util.a(a2.b(), a2.f()), new ECPoint(a2.c().c().a(), a2.c().d().a()), a2.d(), a2.e());
            byte[] e = ASN1OctetString.a(privateKeyInfo.f()).e();
            byte[] bArr = new byte[e.length];
            for (int i = 0; i != e.length; i++) {
                bArr[i] = e[(e.length - 1) - i];
            }
            this.d = new BigInteger(1, bArr);
            return;
        }
        X962Parameters a3 = X962Parameters.a(privateKeyInfo.d().f());
        if (a3.d()) {
            ASN1ObjectIdentifier a4 = DERObjectIdentifier.a((Object) a3.f());
            X9ECParameters a5 = ECUtil.a(a4);
            if (a5 == null) {
                ECDomainParameters a6 = ECGOST3410NamedCurves.a(a4);
                this.e = new ECNamedCurveSpec(ECGOST3410NamedCurves.b(a4), EC5Util.a(a6.a(), a6.e()), new ECPoint(a6.b().c().a(), a6.b().d().a()), a6.c(), a6.d());
            } else {
                this.e = new ECNamedCurveSpec(ECUtil.b(a4), EC5Util.a(a5.d(), a5.h()), new ECPoint(a5.e().c().a(), a5.e().d().a()), a5.f(), a5.g());
            }
        } else if (a3.e()) {
            this.e = null;
        } else {
            X9ECParameters a7 = X9ECParameters.a(a3.f());
            this.e = new ECParameterSpec(EC5Util.a(a7.d(), a7.h()), new ECPoint(a7.e().c().a(), a7.e().d().a()), a7.f(), a7.g().intValue());
        }
        a = privateKeyInfo.f();
        if (a instanceof DERInteger) {
            this.d = DERInteger.a(a).d();
            return;
        }
        org.spongycastle.asn1.sec.ECPrivateKey a8 = org.spongycastle.asn1.sec.ECPrivateKey.a(a);
        this.d = a8.d();
        this.f = a8.e();
    }

    public String getAlgorithm() {
        return this.a;
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        if (this.c != null) {
            byte[] bArr = new byte[32];
            a(bArr, 0, getS());
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.j, this.c), new DEROctetString(bArr)).a("DER");
            } catch (IOException e) {
                return null;
            }
        }
        ASN1Encodable x962Parameters;
        org.spongycastle.asn1.sec.ECPrivateKey eCPrivateKey;
        if (this.e instanceof ECNamedCurveSpec) {
            ASN1Primitive a = ECUtil.a(((ECNamedCurveSpec) this.e).a());
            if (a == null) {
                a = new ASN1ObjectIdentifier(((ECNamedCurveSpec) this.e).a());
            }
            x962Parameters = new X962Parameters(a);
        } else if (this.e == null) {
            x962Parameters = new X962Parameters(DERNull.a);
        } else {
            ECCurve a2 = EC5Util.a(this.e.getCurve());
            Object x962Parameters2 = new X962Parameters(new X9ECParameters(a2, EC5Util.a(a2, this.e.getGenerator(), this.b), this.e.getOrder(), BigInteger.valueOf((long) this.e.getCofactor()), this.e.getCurve().getSeed()));
        }
        if (this.f != null) {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(getS(), this.f, x962Parameters2);
        } else {
            eCPrivateKey = new org.spongycastle.asn1.sec.ECPrivateKey(getS(), x962Parameters2);
        }
        try {
            return new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.j, x962Parameters2.a()), eCPrivateKey.a()).a("DER");
        } catch (IOException e2) {
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
        return this.e;
    }

    public org.spongycastle.jce.spec.ECParameterSpec b() {
        if (this.e == null) {
            return null;
        }
        return EC5Util.a(this.e, this.b);
    }

    org.spongycastle.jce.spec.ECParameterSpec c() {
        if (this.e != null) {
            return EC5Util.a(this.e, this.b);
        }
        return BouncyCastleProvider.a.a();
    }

    public BigInteger getS() {
        return this.d;
    }

    public BigInteger d() {
        return this.d;
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
        if (!(obj instanceof BCECGOST3410PrivateKey)) {
            return false;
        }
        BCECGOST3410PrivateKey bCECGOST3410PrivateKey = (BCECGOST3410PrivateKey) obj;
        if (d().equals(bCECGOST3410PrivateKey.d()) && c().equals(bCECGOST3410PrivateKey.c())) {
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
        stringBuffer.append("             S: ").append(this.d.toString(16)).append(property);
        return stringBuffer.toString();
    }

    private DERBitString a(BCECGOST3410PublicKey bCECGOST3410PublicKey) {
        try {
            return SubjectPublicKeyInfo.a(ASN1Primitive.a(bCECGOST3410PublicKey.getEncoded())).g();
        } catch (IOException e) {
            return null;
        }
    }
}
