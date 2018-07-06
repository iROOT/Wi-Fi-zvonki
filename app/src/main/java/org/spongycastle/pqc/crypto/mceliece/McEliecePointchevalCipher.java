package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.prng.DigestRandomGenerator;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.ByteUtils;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;

public class McEliecePointchevalCipher implements MessageEncryptor {
    McElieceCCA2KeyParameters a;
    private Digest b;
    private SecureRandom c;
    private int d;
    private int e;
    private int f;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.a = (McElieceCCA2PrivateKeyParameters) cipherParameters;
            a((McElieceCCA2PrivateKeyParameters) this.a);
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.c = parametersWithRandom.a();
            this.a = (McElieceCCA2PublicKeyParameters) parametersWithRandom.b();
            a((McElieceCCA2PublicKeyParameters) this.a);
        } else {
            this.c = new SecureRandom();
            this.a = (McElieceCCA2PublicKeyParameters) cipherParameters;
            a((McElieceCCA2PublicKeyParameters) this.a);
        }
    }

    public int a(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) mcElieceCCA2KeyParameters).c();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) mcElieceCCA2KeyParameters).c();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    public void a(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this.c = this.c != null ? this.c : new SecureRandom();
        this.b = mcElieceCCA2PublicKeyParameters.b().a();
        this.d = mcElieceCCA2PublicKeyParameters.c();
        this.e = mcElieceCCA2PublicKeyParameters.f();
        this.f = mcElieceCCA2PublicKeyParameters.d();
    }

    public void a(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.b = mcElieceCCA2PrivateKeyParameters.b().a();
        this.d = mcElieceCCA2PrivateKeyParameters.c();
        this.e = mcElieceCCA2PrivateKeyParameters.d();
        this.f = mcElieceCCA2PrivateKeyParameters.e();
    }

    public byte[] a(byte[] bArr) {
        int i;
        int i2 = 0;
        int i3 = this.e >> 3;
        byte[] bArr2 = new byte[i3];
        this.c.nextBytes(bArr2);
        GF2Vector gF2Vector = new GF2Vector(this.e, this.c);
        byte[] a = gF2Vector.a();
        byte[] b = ByteUtils.b(bArr, bArr2);
        this.b.a(b, 0, b.length);
        b = new byte[this.b.b()];
        this.b.a(b, 0);
        byte[] a2 = McElieceCCA2Primitives.a((McElieceCCA2PublicKeyParameters) this.a, gF2Vector, Conversions.a(this.d, this.f, b)).a();
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.a(a);
        a = new byte[(bArr.length + i3)];
        digestRandomGenerator.b(a);
        for (i = 0; i < bArr.length; i++) {
            a[i] = (byte) (a[i] ^ bArr[i]);
        }
        while (i2 < i3) {
            i = bArr.length + i2;
            a[i] = (byte) (a[i] ^ bArr2[i2]);
            i2++;
        }
        return ByteUtils.b(a2, a);
    }

    public byte[] b(byte[] bArr) {
        int i = (this.d + 7) >> 3;
        int length = bArr.length - i;
        byte[][] a = ByteUtils.a(bArr, i);
        byte[] bArr2 = a[0];
        byte[] bArr3 = a[1];
        GF2Vector[] a2 = McElieceCCA2Primitives.a((McElieceCCA2PrivateKeyParameters) this.a, GF2Vector.a(this.d, bArr2));
        bArr2 = a2[0].a();
        Object obj = a2[1];
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.a(bArr2);
        bArr2 = new byte[length];
        digestRandomGenerator.b(bArr2);
        for (i = 0; i < length; i++) {
            bArr2[i] = (byte) (bArr2[i] ^ bArr3[i]);
        }
        this.b.a(bArr2, 0, bArr2.length);
        byte[] bArr4 = new byte[this.b.b()];
        this.b.a(bArr4, 0);
        if (Conversions.a(this.d, this.f, bArr4).equals(obj)) {
            return ByteUtils.a(bArr2, length - (this.e >> 3))[0];
        }
        throw new Exception("Bad Padding: Invalid ciphertext.");
    }
}
