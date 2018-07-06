package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.Vector;

public class McEliecePKCSCipher implements MessageEncryptor {
    public int a;
    public int b;
    McElieceKeyParameters c;
    private SecureRandom d;
    private int e;
    private int f;
    private int g;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.c = (McEliecePrivateKeyParameters) cipherParameters;
            a((McEliecePrivateKeyParameters) this.c);
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.d = parametersWithRandom.a();
            this.c = (McEliecePublicKeyParameters) parametersWithRandom.b();
            a((McEliecePublicKeyParameters) this.c);
        } else {
            this.d = new SecureRandom();
            this.c = (McEliecePublicKeyParameters) cipherParameters;
            a((McEliecePublicKeyParameters) this.c);
        }
    }

    public int a(McElieceKeyParameters mcElieceKeyParameters) {
        if (mcElieceKeyParameters instanceof McEliecePublicKeyParameters) {
            return ((McEliecePublicKeyParameters) mcElieceKeyParameters).c();
        }
        if (mcElieceKeyParameters instanceof McEliecePrivateKeyParameters) {
            return ((McEliecePrivateKeyParameters) mcElieceKeyParameters).c();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    public void a(McEliecePublicKeyParameters mcEliecePublicKeyParameters) {
        this.d = this.d != null ? this.d : new SecureRandom();
        this.e = mcEliecePublicKeyParameters.c();
        this.f = mcEliecePublicKeyParameters.g();
        this.g = mcEliecePublicKeyParameters.d();
        this.b = this.e >> 3;
        this.a = this.f >> 3;
    }

    public void a(McEliecePrivateKeyParameters mcEliecePrivateKeyParameters) {
        this.e = mcEliecePrivateKeyParameters.c();
        this.f = mcEliecePrivateKeyParameters.d();
        this.a = this.f >> 3;
        this.b = this.e >> 3;
    }

    public byte[] a(byte[] bArr) {
        Vector c = c(bArr);
        return ((GF2Vector) ((McEliecePublicKeyParameters) this.c).e().a(c).a(new GF2Vector(this.e, this.g, this.d))).a();
    }

    private GF2Vector c(byte[] bArr) {
        int i;
        int i2 = this.a;
        if ((this.f & 7) != 0) {
            i = 1;
        } else {
            i = 0;
        }
        Object obj = new byte[(i + i2)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        obj[bArr.length] = 1;
        return GF2Vector.a(this.f, obj);
    }

    public byte[] b(byte[] bArr) {
        GF2Vector a = GF2Vector.a(this.e, bArr);
        McEliecePrivateKeyParameters mcEliecePrivateKeyParameters = (McEliecePrivateKeyParameters) this.c;
        GF2mField e = mcEliecePrivateKeyParameters.e();
        PolynomialGF2mSmallM f = mcEliecePrivateKeyParameters.f();
        GF2Matrix g = mcEliecePrivateKeyParameters.g();
        Permutation h = mcEliecePrivateKeyParameters.h();
        Permutation i = mcEliecePrivateKeyParameters.i();
        GF2Matrix j = mcEliecePrivateKeyParameters.j();
        PolynomialGF2mSmallM[] k = mcEliecePrivateKeyParameters.k();
        i = h.a(i);
        GF2Vector gF2Vector = (GF2Vector) a.a(i.c());
        Vector a2 = GoppaCode.a((GF2Vector) j.c(gF2Vector), e, f, k);
        gF2Vector = (GF2Vector) ((GF2Vector) gF2Vector.a(a2)).a(h);
        a = (GF2Vector) a2.a(i);
        return a((GF2Vector) g.a(gF2Vector.b(this.f)));
    }

    private byte[] a(GF2Vector gF2Vector) {
        Object a = gF2Vector.a();
        int length = a.length - 1;
        while (length >= 0 && a[length] == (byte) 0) {
            length--;
        }
        if (a[length] != (byte) 1) {
            throw new Exception("Bad Padding: invalid ciphertext");
        }
        Object obj = new byte[length];
        System.arraycopy(a, 0, obj, 0, length);
        return obj;
    }
}
