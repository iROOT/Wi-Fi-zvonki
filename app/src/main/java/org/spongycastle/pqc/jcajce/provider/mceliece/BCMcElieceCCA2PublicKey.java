package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PublicKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McElieceCCA2PublicKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2Parameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class BCMcElieceCCA2PublicKey implements PublicKey, CipherParameters {
    private String a;
    private int b;
    private int c;
    private GF2Matrix d;
    private McElieceCCA2Parameters e;

    public BCMcElieceCCA2PublicKey(String str, int i, int i2, GF2Matrix gF2Matrix) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = gF2Matrix;
    }

    public BCMcElieceCCA2PublicKey(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this(mcElieceCCA2PublicKeyParameters.g(), mcElieceCCA2PublicKeyParameters.c(), mcElieceCCA2PublicKeyParameters.d(), mcElieceCCA2PublicKeyParameters.e());
        this.e = mcElieceCCA2PublicKeyParameters.b();
    }

    public String getAlgorithm() {
        return "McEliece";
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public GF2Matrix c() {
        return this.d;
    }

    public String toString() {
        return (("McEliecePublicKey:\n" + " length of the code         : " + this.b + "\n") + " error correction capability: " + this.c + "\n") + " generator matrix           : " + this.d.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCMcElieceCCA2PublicKey)) {
            return false;
        }
        BCMcElieceCCA2PublicKey bCMcElieceCCA2PublicKey = (BCMcElieceCCA2PublicKey) obj;
        if (this.b == bCMcElieceCCA2PublicKey.b && this.c == bCMcElieceCCA2PublicKey.c && this.d.equals(bCMcElieceCCA2PublicKey.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.b + this.c) + this.d.hashCode();
    }

    public String d() {
        return this.a;
    }

    protected ASN1ObjectIdentifier e() {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.2");
    }

    public byte[] getEncoded() {
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(e(), DERNull.a), new McElieceCCA2PublicKey(new ASN1ObjectIdentifier(this.a), this.b, this.c, this.d)).b();
        } catch (IOException e) {
            return null;
        }
    }

    public String getFormat() {
        return null;
    }

    public McElieceCCA2Parameters f() {
        return this.e;
    }
}
