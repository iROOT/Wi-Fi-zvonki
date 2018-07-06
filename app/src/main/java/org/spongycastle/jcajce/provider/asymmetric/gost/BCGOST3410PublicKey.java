package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST3410PublicKeyAlgParameters;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.GOST3410PublicKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.spongycastle.jce.interfaces.GOST3410Params;
import org.spongycastle.jce.interfaces.GOST3410PublicKey;
import org.spongycastle.jce.spec.GOST3410ParameterSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeyParameterSetSpec;
import org.spongycastle.jce.spec.GOST3410PublicKeySpec;

public class BCGOST3410PublicKey implements GOST3410PublicKey {
    private BigInteger a;
    private transient GOST3410Params b;

    BCGOST3410PublicKey(GOST3410PublicKeySpec gOST3410PublicKeySpec) {
        this.a = gOST3410PublicKeySpec.a();
        this.b = new GOST3410ParameterSpec(new GOST3410PublicKeyParameterSetSpec(gOST3410PublicKeySpec.b(), gOST3410PublicKeySpec.c(), gOST3410PublicKeySpec.d()));
    }

    BCGOST3410PublicKey(GOST3410PublicKey gOST3410PublicKey) {
        this.a = gOST3410PublicKey.a();
        this.b = gOST3410PublicKey.b();
    }

    BCGOST3410PublicKey(GOST3410PublicKeyParameters gOST3410PublicKeyParameters, GOST3410ParameterSpec gOST3410ParameterSpec) {
        this.a = gOST3410PublicKeyParameters.c();
        this.b = gOST3410ParameterSpec;
    }

    BCGOST3410PublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        GOST3410PublicKeyAlgParameters gOST3410PublicKeyAlgParameters = new GOST3410PublicKeyAlgParameters((ASN1Sequence) subjectPublicKeyInfo.e().f());
        try {
            byte[] e = ((DEROctetString) subjectPublicKeyInfo.f()).e();
            byte[] bArr = new byte[e.length];
            for (int i = 0; i != e.length; i++) {
                bArr[i] = e[(e.length - 1) - i];
            }
            this.a = new BigInteger(1, bArr);
            this.b = GOST3410ParameterSpec.a(gOST3410PublicKeyAlgParameters);
        } catch (IOException e2) {
            throw new IllegalArgumentException("invalid info structure in GOST3410 public key");
        }
    }

    public String getAlgorithm() {
        return "GOST3410";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        byte[] bArr;
        int i = 0;
        byte[] toByteArray = a().toByteArray();
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
            SubjectPublicKeyInfo subjectPublicKeyInfo;
            if (!(this.b instanceof GOST3410ParameterSpec)) {
                subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.i), new DEROctetString(bArr));
            } else if (this.b.c() != null) {
                subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.i, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.b.a()), new ASN1ObjectIdentifier(this.b.b()), new ASN1ObjectIdentifier(this.b.c()))), new DEROctetString(bArr));
            } else {
                subjectPublicKeyInfo = new SubjectPublicKeyInfo(new AlgorithmIdentifier(CryptoProObjectIdentifiers.i, new GOST3410PublicKeyAlgParameters(new ASN1ObjectIdentifier(this.b.a()), new ASN1ObjectIdentifier(this.b.b()))), new DEROctetString(bArr));
            }
            return KeyUtil.a(subjectPublicKeyInfo);
        } catch (IOException e) {
            return null;
        }
    }

    public GOST3410Params b() {
        return this.b;
    }

    public BigInteger a() {
        return this.a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String property = System.getProperty("line.separator");
        stringBuffer.append("GOST3410 Public Key").append(property);
        stringBuffer.append("            y: ").append(a().toString(16)).append(property);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCGOST3410PublicKey)) {
            return false;
        }
        BCGOST3410PublicKey bCGOST3410PublicKey = (BCGOST3410PublicKey) obj;
        if (this.a.equals(bCGOST3410PublicKey.a) && this.b.equals(bCGOST3410PublicKey.b)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ this.b.hashCode();
    }
}
