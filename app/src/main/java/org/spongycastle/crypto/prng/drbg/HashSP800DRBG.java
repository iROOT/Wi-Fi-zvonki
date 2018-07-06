package org.spongycastle.crypto.prng.drbg;

import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.EntropySource;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public class HashSP800DRBG implements SP80090DRBG {
    private static final byte[] a = new byte[]{(byte) 1};
    private static final Hashtable b = new Hashtable();
    private Digest c;
    private byte[] d;
    private byte[] e;
    private long f;
    private EntropySource g;
    private int h;
    private int i;

    static {
        b.put("SHA-1", Integers.a(440));
        b.put("SHA-224", Integers.a(440));
        b.put("SHA-256", Integers.a(440));
        b.put("SHA-512/256", Integers.a(440));
        b.put("SHA-512/224", Integers.a(440));
        b.put("SHA-384", Integers.a(888));
        b.put("SHA-512", Integers.a(888));
    }

    public HashSP800DRBG(Digest digest, int i, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        if (i > Utils.a(digest)) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        } else if (entropySource.b() < i) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        } else {
            this.c = digest;
            this.g = entropySource;
            this.h = i;
            this.i = ((Integer) b.get(digest.a())).intValue();
            this.d = Utils.a(this.c, Arrays.a(entropySource.a(), bArr2, bArr), this.i);
            Object obj = new byte[(this.d.length + 1)];
            System.arraycopy(this.d, 0, obj, 1, this.d.length);
            this.e = Utils.a(this.c, obj, this.i);
            this.f = 1;
        }
    }

    public int a(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        if (length > 262144) {
            throw new IllegalArgumentException("Number of bits per request limited to 262144");
        } else if (this.f > 140737488355328L) {
            return -1;
        } else {
            Object bArr22;
            Object obj;
            if (z) {
                a(bArr22);
                bArr22 = null;
            }
            if (bArr22 != null) {
                obj = new byte[((this.d.length + 1) + bArr22.length)];
                obj[0] = 2;
                System.arraycopy(this.d, 0, obj, 1, this.d.length);
                System.arraycopy(bArr22, 0, obj, this.d.length + 1, bArr22.length);
                a(this.d, b(obj));
            }
            obj = a(this.d, length);
            Object obj2 = new byte[(this.d.length + 1)];
            System.arraycopy(this.d, 0, obj2, 1, this.d.length);
            obj2[0] = 3;
            a(this.d, b(obj2));
            a(this.d, this.e);
            a(this.d, new byte[]{(byte) ((int) (this.f >> 24)), (byte) ((int) (this.f >> 16)), (byte) ((int) (this.f >> 8)), (byte) ((int) this.f)});
            this.f++;
            System.arraycopy(obj, 0, bArr, 0, bArr.length);
            return length;
        }
    }

    private void a(byte[] bArr, byte[] bArr2) {
        int i;
        int i2 = 0;
        for (i = 1; i <= bArr2.length; i++) {
            int i3 = ((bArr[bArr.length - i] & 255) + (bArr2[bArr2.length - i] & 255)) + i2;
            if (i3 > 255) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            bArr[bArr.length - i] = (byte) i3;
        }
        for (i = bArr2.length + 1; i <= bArr.length; i++) {
            i3 = (bArr[bArr.length - i] & 255) + i2;
            if (i3 > 255) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            bArr[bArr.length - i] = (byte) i3;
        }
    }

    public void a(byte[] bArr) {
        this.d = Utils.a(this.c, Arrays.a(a, this.d, this.g.a(), bArr), this.i);
        Object obj = new byte[(this.d.length + 1)];
        obj[0] = null;
        System.arraycopy(this.d, 0, obj, 1, this.d.length);
        this.e = Utils.a(this.c, obj, this.i);
        this.f = 1;
    }

    private byte[] b(byte[] bArr) {
        this.c.a(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[this.c.b()];
        this.c.a(bArr2, 0);
        return bArr2;
    }

    private byte[] a(byte[] bArr, int i) {
        int b = (i / 8) / this.c.b();
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        Object obj = new byte[(i / 8)];
        for (int i2 = 0; i2 <= b; i2++) {
            Object b2 = b(bArr2);
            System.arraycopy(b2, 0, obj, b2.length * i2, obj.length - (b2.length * i2) > b2.length ? b2.length : obj.length - (b2.length * i2));
            a(bArr2, a);
        }
        return obj;
    }
}
