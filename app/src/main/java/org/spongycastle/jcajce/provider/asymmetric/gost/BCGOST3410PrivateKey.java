package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.params.GOST3410PrivateKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.spongycastle.jce.interfaces.GOST3410Params;
import org.spongycastle.jce.interfaces.GOST3410PrivateKey;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PrivateKeySpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;

public class BCGOST3410PrivateKey implements GOST3410PrivateKey, PKCS12BagAttributeCarrier {
    private BigInteger a;
    private transient GOST3410Params b;
    private transient PKCS12BagAttributeCarrier c = new PKCS12BagAttributeCarrierImpl();

    protected BCGOST3410PrivateKey() {
    }

    BCGOST3410PrivateKey(GOST3410PrivateKey gOST3410PrivateKey) {
        this.a = gOST3410PrivateKey.c();
        this.b = gOST3410PrivateKey.b();
    }

    BCGOST3410PrivateKey(GOST3410PrivateKeySpec gOST3410PrivateKeySpec) {
        this.a = gOST3410PrivateKeySpec.a();
        this.b = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(gOST3410PrivateKeySpec.b(), gOST3410PrivateKeySpec.c(), gOST3410PrivateKeySpec.d()));
    }

    BCGOST3410PrivateKey(PrivateKeyInfo privateKeyInfo) {
        GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = new GOST3410PublicKeyAlgParameters((ASN1Sequence) privateKeyInfo.e().f());
        byte[] e = ASN1OctetString.a(privateKeyInfo.f()).e();
        byte[] bArr = new byte[e.length];
        for (int i = 0; i != e.length; i++) {
            bArr[i] = e[(e.length - 1) - i];
        }
        this.a = new BigInteger(1, bArr);
        this.b = GOST3410ParameterSpec.a(gOST3410PublicKeyAlgParameters);
    }

    BCGOST3410PrivateKey(GOST3410PrivateKeyParameters gOST3410PrivateKeyParameters, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.a = gOST3410PrivateKeyParameters.c();
        this.b = gOST3410ParameterSpec;
        if (gOST3410ParameterSpec == null) {
            throw new IllegalArgumentException("spec is null");
        }
    }

    public String getAlgorithm() {
        return "GOST3410";
    }

    public String getFormat() {
        return "PKCS#8";
    }

    public byte[] getEncoded() {
        byte[] bArr;
        int i = 0;
        byte[] toByteArray = c().toByteArray();
        if (toByteArray[0] == (byte) 0) {
            bArr = new byte[(toByteArray.length - 1)];
        } else {
            bArr = new byte[toByteArray.length];
        }
        while (i != bArr.length) {
            bArr[i] = toByteArray[(toByteArray.length - 1) - i];
            i++;
        }
        try {
            PrivateKeyInfo privateKeyInfo;
            if (this.b instanceof GOST3410ParameterSpec) {
                privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.i, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.b.a()), new ASN1ObjectIdentifier(this.b.b()))), new DEROctetString(bArr));
            } else {
                privateKeyInfo = new PrivateKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.i), new DEROctetString(bArr));
            }
            return privateKeyInfo.a("DER");
        } catch (IOException e) {
            return null;
        }
    }

    public GOST3410Params b() {
        return this.b;
    }

    public BigInteger c() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PrivateKey)) {
            return false;
        }
        GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey) obj;
        if (c().equals(gOST3410PrivateKey.c()) && b().d().equals(gOST3410PrivateKey.b().d()) && b().b().equals(gOST3410PrivateKey.b().b()) && a(b().c(), gOST3410PrivateKey.b().c())) {
            return true;
        }
        return false;
    }

    private boolean a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public int hashCode() {
        return c().hashCode() ^ this.b.hashCode();
    }

    public void a(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.c.a(aSN1ObjectIdentifier, aSN1Encodable);
    }

    public ASN1Encodable a(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.c.a(aSN1ObjectIdentifier);
    }

    public Enumeration a() {
        return this.c.a();
    }
}
