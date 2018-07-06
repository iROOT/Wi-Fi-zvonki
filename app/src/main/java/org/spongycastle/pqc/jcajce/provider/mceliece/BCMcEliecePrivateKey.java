package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McEliecePrivateKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceParameters;
import org.spongycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class BCMcEliecePrivateKey implements PrivateKey, CipherParameters {
    private String a;
    private int b;
    private int c;
    private GF2mField d;
    private PolynomialGF2mSmallM e;
    private GF2Matrix f;
    private Permutation g;
    private Permutation h;
    private GF2Matrix i;
    private PolynomialGF2mSmallM[] j;
    private McElieceParameters k;

    public BCMcEliecePrivateKey(String str, int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, GF2Matrix gF2Matrix, Permutation permutation, Permutation permutation2, GF2Matrix gF2Matrix2, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = gF2mField;
        this.e = polynomialGF2mSmallM;
        this.f = gF2Matrix;
        this.g = permutation;
        this.h = permutation2;
        this.i = gF2Matrix2;
        this.j = polynomialGF2mSmallMArr;
    }

    public BCMcEliecePrivateKey(McEliecePrivateKeyParameters mcEliecePrivateKeyParameters) {
        this(mcEliecePrivateKeyParameters.l(), mcEliecePrivateKeyParameters.c(), mcEliecePrivateKeyParameters.d(), mcEliecePrivateKeyParameters.e(), mcEliecePrivateKeyParameters.f(), mcEliecePrivateKeyParameters.g(), mcEliecePrivateKeyParameters.h(), mcEliecePrivateKeyParameters.i(), mcEliecePrivateKeyParameters.j(), mcEliecePrivateKeyParameters.k());
        this.k = mcEliecePrivateKeyParameters.b();
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

    public GF2mField c() {
        return this.d;
    }

    public PolynomialGF2mSmallM d() {
        return this.e;
    }

    public GF2Matrix e() {
        return this.f;
    }

    public Permutation f() {
        return this.g;
    }

    public Permutation g() {
        return this.h;
    }

    public GF2Matrix h() {
        return this.i;
    }

    public PolynomialGF2mSmallM[] i() {
        return this.j;
    }

    public String j() {
        return this.a;
    }

    public String toString() {
        return (((((" length of the code          : " + this.b + "\n") + " dimension of the code       : " + this.c + "\n") + " irreducible Goppa polynomial: " + this.e + "\n") + " (k x k)-matrix S^-1         : " + this.f + "\n") + " permutation P1              : " + this.g + "\n") + " permutation P2              : " + this.h;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BCMcEliecePrivateKey)) {
            return false;
        }
        BCMcEliecePrivateKey bCMcEliecePrivateKey = (BCMcEliecePrivateKey) obj;
        if (this.b == bCMcEliecePrivateKey.b && this.c == bCMcEliecePrivateKey.c && this.d.equals(bCMcEliecePrivateKey.d) && this.e.equals(bCMcEliecePrivateKey.e) && this.f.equals(bCMcEliecePrivateKey.f) && this.g.equals(bCMcEliecePrivateKey.g) && this.h.equals(bCMcEliecePrivateKey.h) && this.i.equals(bCMcEliecePrivateKey.i)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((this.c + this.b) + this.d.hashCode()) + this.e.hashCode()) + this.f.hashCode()) + this.g.hashCode()) + this.h.hashCode()) + this.i.hashCode();
    }

    protected ASN1ObjectIdentifier k() {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.1");
    }

    public byte[] getEncoded() {
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(k(), DERNull.a), new McEliecePrivateKey(new ASN1ObjectIdentifier(this.a), this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j)).b();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String getFormat() {
        return null;
    }

    public McElieceParameters l() {
        return this.k;
    }
}
