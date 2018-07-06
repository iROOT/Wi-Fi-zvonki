package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode.MaMaPe;
import org.spongycastle.pqc.math.linearalgebra.Matrix;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McElieceKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private McElieceKeyGenerationParameters a;
    private int b;
    private int c;
    private int d;
    private int e;
    private SecureRandom f;
    private boolean g = false;

    private void b() {
        b(new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters()));
    }

    private void b(KeyGenerationParameters keyGenerationParameters) {
        this.a = (McElieceKeyGenerationParameters) keyGenerationParameters;
        this.f = new SecureRandom();
        this.b = this.a.c().b();
        this.c = this.a.c().c();
        this.d = this.a.c().d();
        this.e = this.a.c().e();
        this.g = true;
    }

    private AsymmetricCipherKeyPair c() {
        if (!this.g) {
            b();
        }
        GF2mField gF2mField = new GF2mField(this.b, this.e);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(gF2mField, this.d, 'I', this.f);
        PolynomialGF2mSmallM[] a = new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM).a();
        GF2Matrix a2 = GoppaCode.a(gF2mField, polynomialGF2mSmallM);
        MaMaPe a3 = GoppaCode.a(a2, this.f);
        GF2Matrix a4 = a3.a();
        Permutation b = a3.b();
        GF2Matrix gF2Matrix = (GF2Matrix) a4.e();
        Matrix c = gF2Matrix.c();
        int g = gF2Matrix.g();
        GF2Matrix[] a5 = GF2Matrix.a(g, this.f);
        Permutation permutation = new Permutation(this.c, this.f);
        return new AsymmetricCipherKeyPair(new McEliecePublicKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.1", this.c, this.d, (GF2Matrix) ((GF2Matrix) a5[0].a(c)).a(permutation), this.a.c()), new McEliecePrivateKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.1", this.c, g, gF2mField, polynomialGF2mSmallM, a5[1], b, permutation, a2, a, this.a.c()));
    }

    public void a(KeyGenerationParameters keyGenerationParameters) {
        b(keyGenerationParameters);
    }

    public AsymmetricCipherKeyPair a() {
        return c();
    }
}
