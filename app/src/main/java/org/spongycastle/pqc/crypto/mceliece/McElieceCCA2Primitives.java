package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.Vector;

public final class McElieceCCA2Primitives {
    private McElieceCCA2Primitives() {
    }

    public static GF2Vector a(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters, GF2Vector gF2Vector, GF2Vector gF2Vector2) {
        return (GF2Vector) mcElieceCCA2PublicKeyParameters.e().b(gF2Vector).a(gF2Vector2);
    }

    public static GF2Vector[] a(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters, GF2Vector gF2Vector) {
        int d = mcElieceCCA2PrivateKeyParameters.d();
        Permutation h = mcElieceCCA2PrivateKeyParameters.h();
        GF2mField f = mcElieceCCA2PrivateKeyParameters.f();
        PolynomialGF2mSmallM g = mcElieceCCA2PrivateKeyParameters.g();
        GF2Matrix i = mcElieceCCA2PrivateKeyParameters.i();
        GF2Vector gF2Vector2 = (GF2Vector) gF2Vector.a(h.c());
        Vector a = GoppaCode.a((GF2Vector) i.c(gF2Vector2), f, g, mcElieceCCA2PrivateKeyParameters.j());
        gF2Vector2 = (GF2Vector) ((GF2Vector) gF2Vector2.a(a)).a(h);
        GF2Vector gF2Vector3 = (GF2Vector) a.a(h);
        gF2Vector2 = gF2Vector2.b(d);
        return new GF2Vector[]{gF2Vector2, gF2Vector3};
    }
}
