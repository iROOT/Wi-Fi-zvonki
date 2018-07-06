package org.spongycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.EphemeralKeyPair;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.KeyParser;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.IESParameters;
import org.spongycastle.crypto.params.IESWithCipherParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.util.Pack;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class IESEngine {
    BasicAgreement a;
    DerivationFunction b;
    Mac c;
    BufferedBlockCipher d;
    byte[] e;
    boolean f;
    CipherParameters g;
    CipherParameters h;
    IESParameters i;
    byte[] j;
    private EphemeralKeyPairGenerator k;
    private KeyParser l;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.a = basicAgreement;
        this.b = derivationFunction;
        this.c = mac;
        this.e = new byte[mac.b()];
        this.d = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.a = basicAgreement;
        this.b = derivationFunction;
        this.c = mac;
        this.e = new byte[mac.b()];
        this.d = bufferedBlockCipher;
    }

    public void a(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.f = z;
        this.g = cipherParameters;
        this.h = cipherParameters2;
        this.i = (IESParameters) cipherParameters3;
        this.j = new byte[0];
    }

    public void a(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.f = true;
        this.h = asymmetricKeyParameter;
        this.i = (IESParameters) cipherParameters;
        this.k = ephemeralKeyPairGenerator;
    }

    public void a(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.f = false;
        this.g = asymmetricKeyParameter;
        this.i = (IESParameters) cipherParameters;
        this.l = keyParser;
    }

    public BufferedBlockCipher a() {
        return this.d;
    }

    public Mac b() {
        return this.c;
    }

    private byte[] b(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        Object obj;
        Object obj2;
        Object obj3;
        if (this.d == null) {
            Object obj4 = new byte[i2];
            bArr2 = new byte[(this.i.c() / 8)];
            obj3 = new byte[(obj4.length + bArr2.length)];
            this.b.a(obj3, 0, obj3.length);
            if (this.j.length != 0) {
                System.arraycopy(obj3, 0, bArr2, 0, bArr2.length);
                System.arraycopy(obj3, bArr2.length, obj4, 0, obj4.length);
            } else {
                System.arraycopy(obj3, 0, obj4, 0, obj4.length);
                System.arraycopy(obj3, i2, bArr2, 0, bArr2.length);
            }
            obj = new byte[i2];
            for (int i3 = 0; i3 != i2; i3++) {
                obj[i3] = (byte) (bArr[i + i3] ^ obj4[i3]);
            }
        } else {
            obj2 = new byte[(((IESWithCipherParameters) this.i).d() / 8)];
            Object obj5 = new byte[(this.i.c() / 8)];
            obj3 = new byte[(obj2.length + obj5.length)];
            this.b.a(obj3, 0, obj3.length);
            System.arraycopy(obj3, 0, obj2, 0, obj2.length);
            System.arraycopy(obj3, obj2.length, obj5, 0, obj5.length);
            this.d.a(true, new KeyParameter(obj2));
            obj = new byte[this.d.b(i2)];
            int a = this.d.a(bArr, i, i2, obj, 0);
            i2 = a + this.d.a((byte[]) obj, a);
            bArr2 = obj5;
        }
        byte[] b = this.i.b();
        byte[] bArr3 = new byte[4];
        if (!(this.j.length == 0 || b == null)) {
            Pack.a(b.length * 8, bArr3, 0);
        }
        Object obj6 = new byte[this.c.b()];
        this.c.a(new KeyParameter(bArr2));
        this.c.a(obj, 0, obj.length);
        if (b != null) {
            this.c.a(b, 0, b.length);
        }
        if (this.j.length != 0) {
            this.c.a(bArr3, 0, bArr3.length);
        }
        this.c.a(obj6, 0);
        obj2 = new byte[((this.j.length + i2) + obj6.length)];
        System.arraycopy(this.j, 0, obj2, 0, this.j.length);
        System.arraycopy(obj, 0, obj2, this.j.length, i2);
        System.arraycopy(obj6, 0, obj2, this.j.length + i2, obj6.length);
        return obj2;
    }

    private byte[] c(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        byte[] bArr3;
        int i3;
        Object obj;
        if (this.d == null) {
            Object obj2 = new byte[((i2 - this.j.length) - this.c.b())];
            bArr2 = new byte[(this.i.c() / 8)];
            obj = new byte[(obj2.length + bArr2.length)];
            this.b.a(obj, 0, obj.length);
            if (this.j.length != 0) {
                System.arraycopy(obj, 0, bArr2, 0, bArr2.length);
                System.arraycopy(obj, bArr2.length, obj2, 0, obj2.length);
            } else {
                System.arraycopy(obj, 0, obj2, 0, obj2.length);
                System.arraycopy(obj, obj2.length, bArr2, 0, bArr2.length);
            }
            bArr3 = new byte[obj2.length];
            for (i3 = 0; i3 != obj2.length; i3++) {
                bArr3[i3] = (byte) (bArr[(this.j.length + i) + i3] ^ obj2[i3]);
            }
            i3 = obj2.length;
        } else {
            obj = new byte[(((IESWithCipherParameters) this.i).d() / 8)];
            Object obj3 = new byte[(this.i.c() / 8)];
            Object obj4 = new byte[(obj.length + obj3.length)];
            this.b.a(obj4, 0, obj4.length);
            System.arraycopy(obj4, 0, obj, 0, obj.length);
            System.arraycopy(obj4, obj.length, obj3, 0, obj3.length);
            this.d.a(false, new KeyParameter(obj));
            bArr3 = new byte[this.d.b((i2 - this.j.length) - this.c.b())];
            i3 = this.d.a(bArr, i + this.j.length, (i2 - this.j.length) - this.c.b(), bArr3, 0);
            i3 += this.d.a(bArr3, i3);
            obj4 = obj3;
        }
        byte[] b = this.i.b();
        byte[] bArr4 = new byte[4];
        if (!(this.j.length == 0 || b == null)) {
            Pack.a(b.length * 8, bArr4, 0);
        }
        int i4 = i + i2;
        byte[] a = Arrays.a(bArr, i4 - this.c.b(), i4);
        byte[] bArr5 = new byte[a.length];
        this.c.a(new KeyParameter(bArr2));
        this.c.a(bArr, this.j.length + i, (i2 - this.j.length) - bArr5.length);
        if (b != null) {
            this.c.a(b, 0, b.length);
        }
        if (this.j.length != 0) {
            this.c.a(bArr4, 0, bArr4.length);
        }
        this.c.a(bArr5, 0);
        if (Arrays.b(a, bArr5)) {
            return Arrays.a(bArr3, 0, i3);
        }
        throw new InvalidCipherTextException("Invalid MAC.");
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2;
        if (this.f) {
            if (this.k != null) {
                EphemeralKeyPair a = this.k.a();
                this.g = a.a().b();
                this.j = a.b();
            }
        } else if (this.l != null) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                this.h = this.l.a(byteArrayInputStream);
                this.j = Arrays.a(bArr, i, (i2 - byteArrayInputStream.available()) + i);
            } catch (Throwable e) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e.getMessage(), e);
            }
        }
        this.a.a(this.g);
        Object a2 = BigIntegers.a(this.a.a(), this.a.b(this.h));
        if (this.j.length != 0) {
            bArr2 = new byte[(this.j.length + a2.length)];
            System.arraycopy(this.j, 0, bArr2, 0, this.j.length);
            System.arraycopy(a2, 0, bArr2, this.j.length, a2.length);
        } else {
            Object bArr22 = a2;
        }
        this.b.a(new KDFParameters(bArr22, this.i.a()));
        if (this.f) {
            return b(bArr, i, i2);
        }
        return c(bArr, i, i2);
    }
}
