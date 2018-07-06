package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode.MaMaPe;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McElieceCCA2KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private McElieceCCA2KeyGenerationParameters a;
    private int b;
    private int c;
    private int d;
    private int e;
    private SecureRandom f;
    private boolean g = false;

    private void b() {
        a(new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters()));
    }

    public void a(KeyGenerationParameters keyGenerationParameters) {
        this.a = (McElieceCCA2KeyGenerationParameters) keyGenerationParameters;
        this.f = new SecureRandom();
        this.b = this.a.c().b();
        this.c = this.a.c().c();
        this.d = this.a.c().d();
        this.e = this.a.c().e();
        this.g = true;
    }

    public AsymmetricCipherKeyPair a() {
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
        int g = gF2Matrix.g();
        return new AsymmetricCipherKeyPair(new McElieceCCA2PublicKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.2", this.c, this.d, gF2Matrix, this.a.c()), new McElieceCCA2PrivateKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.2", this.c, g, gF2mField, polynomialGF2mSmallM, b, a2, a, this.a.c()));
    }
}
