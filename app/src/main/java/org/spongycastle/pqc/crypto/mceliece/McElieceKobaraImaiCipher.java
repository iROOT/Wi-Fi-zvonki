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
import org.spongycastle.pqc.math.linearalgebra.IntegerFunctions;

public class McElieceKobaraImaiCipher implements MessageEncryptor {
    public static final byte[] a = "a predetermined public constant".getBytes();
    McElieceCCA2KeyParameters b;
    private Digest c;
    private SecureRandom d;
    private int e;
    private int f;
    private int g;

    public void a(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.b = (McElieceCCA2PrivateKeyParameters) cipherParameters;
            a((McElieceCCA2PrivateKeyParameters) this.b);
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.d = parametersWithRandom.a();
            this.b = (McElieceCCA2PublicKeyParameters) parametersWithRandom.b();
            a((McElieceCCA2PublicKeyParameters) this.b);
        } else {
            this.d = new SecureRandom();
            this.b = (McElieceCCA2PublicKeyParameters) cipherParameters;
            a((McElieceCCA2PublicKeyParameters) this.b);
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

    private void a(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this.c = mcElieceCCA2PublicKeyParameters.b().a();
        this.e = mcElieceCCA2PublicKeyParameters.c();
        this.f = mcElieceCCA2PublicKeyParameters.f();
        this.g = mcElieceCCA2PublicKeyParameters.d();
    }

    public void a(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.c = mcElieceCCA2PrivateKeyParameters.b().a();
        this.e = mcElieceCCA2PrivateKeyParameters.c();
        this.f = mcElieceCCA2PrivateKeyParameters.d();
        this.g = mcElieceCCA2PrivateKeyParameters.e();
    }

    public byte[] a(byte[] bArr) {
        int b = this.c.b();
        int i = this.f >> 3;
        int bitLength = (IntegerFunctions.a(this.e, this.g).bitLength() - 1) >> 3;
        int length = ((i + bitLength) - b) - a.length;
        if (bArr.length > length) {
            length = bArr.length;
        }
        int length2 = a.length + length;
        int i2 = ((length2 + b) - i) - bitLength;
        Object obj = new byte[length2];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(a, 0, obj, length, a.length);
        byte[] bArr2 = new byte[b];
        this.d.nextBytes(bArr2);
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.a(bArr2);
        byte[] bArr3 = new byte[length2];
        digestRandomGenerator.b(bArr3);
        for (length = length2 - 1; length >= 0; length--) {
            bArr3[length] = (byte) (bArr3[length] ^ obj[length]);
        }
        byte[] bArr4 = new byte[this.c.b()];
        this.c.a(bArr3, 0, bArr3.length);
        this.c.a(bArr4, 0);
        for (length = b - 1; length >= 0; length--) {
            bArr4[length] = (byte) (bArr4[length] ^ bArr2[length]);
        }
        Object b2 = ByteUtils.b(bArr4, bArr3);
        byte[] bArr5 = new byte[0];
        if (i2 > 0) {
            bArr5 = new byte[i2];
            System.arraycopy(b2, 0, bArr5, 0, i2);
        }
        byte[] bArr6 = bArr5;
        bArr5 = new byte[bitLength];
        System.arraycopy(b2, i2, bArr5, 0, bitLength);
        obj = new byte[i];
        System.arraycopy(b2, bitLength + i2, obj, 0, i);
        bArr5 = McElieceCCA2Primitives.a((McElieceCCA2PublicKeyParameters) this.b, GF2Vector.a(this.f, obj), Conversions.a(this.e, this.g, bArr5)).a();
        if (i2 > 0) {
            return ByteUtils.b(bArr6, bArr5);
        }
        return bArr5;
    }

    public byte[] b(byte[] bArr) {
        int i = this.e >> 3;
        if (bArr.length < i) {
            throw new Exception("Bad Padding: Ciphertext too short.");
        }
        byte[] bArr2;
        byte[] bArr3;
        int b = this.c.b();
        int i2 = this.f >> 3;
        i = bArr.length - i;
        if (i > 0) {
            byte[][] a = ByteUtils.a(bArr, i);
            bArr2 = a[0];
            bArr = a[1];
            bArr3 = bArr2;
        } else {
            bArr3 = new byte[0];
        }
        GF2Vector[] a2 = McElieceCCA2Primitives.a((McElieceCCA2PrivateKeyParameters) this.b, GF2Vector.a(this.e, bArr));
        bArr2 = a2[0].a();
        GF2Vector gF2Vector = a2[1];
        if (bArr2.length > i2) {
            bArr2 = ByteUtils.a(bArr2, 0, i2);
        }
        bArr2 = ByteUtils.b(ByteUtils.b(bArr3, Conversions.a(this.e, this.g, gF2Vector)), bArr2);
        int length = bArr2.length - b;
        byte[][] a3 = ByteUtils.a(bArr2, b);
        byte[] bArr4 = a3[0];
        byte[] bArr5 = a3[1];
        byte[] bArr6 = new byte[this.c.b()];
        this.c.a(bArr5, 0, bArr5.length);
        this.c.a(bArr6, 0);
        for (i = b - 1; i >= 0; i--) {
            bArr6[i] = (byte) (bArr6[i] ^ bArr4[i]);
        }
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.a(bArr6);
        byte[] bArr7 = new byte[length];
        digestRandomGenerator.b(bArr7);
        for (i = length - 1; i >= 0; i--) {
            bArr7[i] = (byte) (bArr7[i] ^ bArr5[i]);
        }
        if (bArr7.length < length) {
            throw new Exception("Bad Padding: invalid ciphertext");
        }
        a3 = ByteUtils.a(bArr7, length - a.length);
        bArr3 = a3[0];
        if (ByteUtils.a(a3[1], a)) {
            return bArr3;
        }
        throw new Exception("Bad Padding: invalid ciphertext");
    }
}
