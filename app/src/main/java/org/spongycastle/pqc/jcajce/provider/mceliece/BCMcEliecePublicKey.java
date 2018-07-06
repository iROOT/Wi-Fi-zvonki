package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PublicKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McEliecePublicKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class BCMcEliecePublicKey implements PublicKey, CipherParameters {
    private String a;
    private int b;
    private int c;
    private GF2Matrix d;
    private McElieceParameters e;

    public BCMcEliecePublicKey(String str, int i, int i2, GF2Matrix gF2Matrix) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = gF2Matrix;
    }

    public BCMcEliecePublicKey(McEliecePublicKeyParameters mcEliecePublicKeyParameters) {
        this(mcEliecePublicKeyParameters.f(), mcEliecePublicKeyParameters.c(), mcEliecePublicKeyParameters.d(), mcEliecePublicKeyParameters.e());
        this.e = mcEliecePublicKeyParameters.b();
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
        if (!(obj instanceof BCMcEliecePublicKey)) {
            return false;
        }
        BCMcEliecePublicKey bCMcEliecePublicKey = (BCMcEliecePublicKey) obj;
        if (this.b == bCMcEliecePublicKey.b && this.c == bCMcEliecePublicKey.c && this.d.equals(bCMcEliecePublicKey.d)) {
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
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.1");
    }

    public byte[] getEncoded() {
        try {
            return new SubjectPublicKeyInfo(new AlgorithmIdentifier(e(), DERNull.a), new McEliecePublicKey(new ASN1ObjectIdentifier(this.a), this.b, this.c, this.d)).b();
        } catch (IOException e) {
            return null;
        }
    }

    public String getFormat() {
        return null;
    }

    public McElieceParameters f() {
        return this.e;
    }
}
