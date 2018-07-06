package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.PrivateKey;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2Parameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class BCMcElieceCCA2PrivateKey implements PrivateKey, CipherParameters {
    private String a;
    private int b;
    private int c;
    private GF2mField d;
    private PolynomialGF2mSmallM e;
    private Permutation f;
    private GF2Matrix g;
    private PolynomialGF2mSmallM[] h;
    private McElieceCCA2Parameters i;

    public BCMcElieceCCA2PrivateKey(String str, int i, int i2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, GF2Matrix gF2Matrix, PolynomialGF2mSmallM[] polynomialGF2mSmallMArr) {
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = gF2mField;
        this.e = polynomialGF2mSmallM;
        this.f = permutation;
        this.g = gF2Matrix;
        this.h = polynomialGF2mSmallMArr;
    }

    public BCMcElieceCCA2PrivateKey(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this(mcElieceCCA2PrivateKeyParameters.k(), mcElieceCCA2PrivateKeyParameters.c(), mcElieceCCA2PrivateKeyParameters.d(), mcElieceCCA2PrivateKeyParameters.f(), mcElieceCCA2PrivateKeyParameters.g(), mcElieceCCA2PrivateKeyParameters.h(), mcElieceCCA2PrivateKeyParameters.i(), mcElieceCCA2PrivateKeyParameters.j());
        this.i = mcElieceCCA2PrivateKeyParameters.b();
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

    public Permutation e() {
        return this.f;
    }

    public GF2Matrix f() {
        return this.g;
    }

    public PolynomialGF2mSmallM[] g() {
        return this.h;
    }

    public String toString() {
        return (("" + " extension degree of the field      : " + this.b + "\n") + " dimension of the code              : " + this.c + "\n") + " irreducible Goppa polynomial       : " + this.e + "\n";
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof BCMcElieceCCA2PrivateKey)) {
            return false;
        }
        BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey = (BCMcElieceCCA2PrivateKey) obj;
        if (this.b == bCMcElieceCCA2PrivateKey.b && this.c == bCMcElieceCCA2PrivateKey.c && this.d.equals(bCMcElieceCCA2PrivateKey.d) && this.e.equals(bCMcElieceCCA2PrivateKey.e) && this.f.equals(bCMcElieceCCA2PrivateKey.f) && this.g.equals(bCMcElieceCCA2PrivateKey.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.c + this.b) + this.d.hashCode()) + this.e.hashCode()) + this.f.hashCode()) + this.g.hashCode();
    }

    public String h() {
        return this.a;
    }

    protected ASN1ObjectIdentifier i() {
        return new ASN1ObjectIdentifier("1.3.6.1.4.1.8301.3.1.3.4.2");
    }

    public byte[] getEncoded() {
        try {
            try {
                return new PrivateKeyInfo(new AlgorithmIdentifier(i(), DERNull.a), new McElieceCCA2PrivateKey(new ASN1ObjectIdentifier(this.a), this.b, this.c, this.d, this.e, this.f, this.g, this.h)).b();
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

    public McElieceCCA2Parameters j() {
        return this.i;
    }
}
