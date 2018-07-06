package org.spongycastle.crypto.digests;

import android.support.v4.app.NotificationCompat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.engines.ThreefishEngine;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;

public class SkeinEngine implements Memoable {
    private static final Hashtable c = new Hashtable();
    final ThreefishEngine a;
    long[] b;
    private final int d;
    private long[] e;
    private byte[] f;
    private Parameter[] g;
    private Parameter[] h;
    private final UBI i;
    private final byte[] j;

    private static class Configuration {
        private byte[] a = new byte[32];

        public Configuration(long j) {
            this.a[0] = (byte) 83;
            this.a[1] = (byte) 72;
            this.a[2] = (byte) 65;
            this.a[3] = (byte) 51;
            this.a[4] = (byte) 1;
            this.a[5] = (byte) 0;
            ThreefishEngine.a(j, this.a, 8);
        }

        public byte[] a() {
            return this.a;
        }
    }

    public static class Parameter {
        private int a;
        private byte[] b;

        public Parameter(int i, byte[] bArr) {
            this.a = i;
            this.b = bArr;
        }

        public int a() {
            return this.a;
        }

        public byte[] b() {
            return this.b;
        }
    }

    private class UBI {
        final /* synthetic */ SkeinEngine a;
        private final UbiTweak b = new UbiTweak();
        private byte[] c;
        private int d;
        private long[] e;

        public UBI(SkeinEngine skeinEngine, int i) {
            this.a = skeinEngine;
            this.c = new byte[i];
            this.e = new long[(this.c.length / 8)];
        }

        public void a(UBI ubi) {
            this.c = Arrays.c(ubi.c, this.c);
            this.d = ubi.d;
            this.e = Arrays.b(ubi.e, this.e);
            this.b.a(ubi.b);
        }

        public void a(int i) {
            this.b.a();
            this.b.a(i);
            this.d = 0;
        }

        public void a(byte[] bArr, int i, int i2, long[] jArr) {
            int i3 = 0;
            while (i2 > i3) {
                if (this.d == this.c.length) {
                    b(jArr);
                    this.b.a(false);
                    this.d = 0;
                }
                int min = Math.min(i2 - i3, this.c.length - this.d);
                System.arraycopy(bArr, i + i3, this.c, this.d, min);
                i3 += min;
                this.d += min;
                this.b.b(min);
            }
        }

        private void b(long[] jArr) {
            int i = 0;
            this.a.a.a(true, this.a.b, this.b.e());
            for (int i2 = 0; i2 < this.e.length; i2++) {
                this.e[i2] = ThreefishEngine.a(this.c, i2 * 8);
            }
            this.a.a.a(this.e, jArr);
            while (i < jArr.length) {
                jArr[i] = jArr[i] ^ this.e[i];
                i++;
            }
        }

        public void a(long[] jArr) {
            for (int i = this.d; i < this.c.length; i++) {
                this.c[i] = (byte) 0;
            }
            this.b.b(true);
            b(jArr);
        }
    }

    private static class UbiTweak {
        private long[] a = new long[2];
        private boolean b;

        public UbiTweak() {
            a();
        }

        public void a(UbiTweak ubiTweak) {
            this.a = Arrays.b(ubiTweak.a, this.a);
            this.b = ubiTweak.b;
        }

        public void a() {
            this.a[0] = 0;
            this.a[1] = 0;
            this.b = false;
            a(true);
        }

        public void a(int i) {
            this.a[1] = (this.a[1] & -274877906944L) | ((((long) i) & 63) << 56);
        }

        public int b() {
            return (int) ((this.a[1] >>> 56) & 63);
        }

        public void a(boolean z) {
            if (z) {
                long[] jArr = this.a;
                jArr[1] = jArr[1] | 4611686018427387904L;
                return;
            }
            jArr = this.a;
            jArr[1] = jArr[1] & -4611686018427387905L;
        }

        public boolean c() {
            return (this.a[1] & 4611686018427387904L) != 0;
        }

        public void b(boolean z) {
            if (z) {
                long[] jArr = this.a;
                jArr[1] = jArr[1] | Long.MIN_VALUE;
                return;
            }
            jArr = this.a;
            jArr[1] = jArr[1] & Long.MAX_VALUE;
        }

        public boolean d() {
            return (this.a[1] & Long.MIN_VALUE) != 0;
        }

        public void b(int i) {
            long j;
            if (this.b) {
                long[] jArr = new long[]{this.a[0] & 4294967295L, (this.a[0] >>> 32) & 4294967295L, this.a[1] & 4294967295L};
                j = (long) i;
                for (int i2 = 0; i2 < jArr.length; i2++) {
                    j += jArr[i2];
                    jArr[i2] = j;
                    j >>>= 32;
                }
                this.a[0] = ((jArr[1] & 4294967295L) << 32) | (jArr[0] & 4294967295L);
                this.a[1] = (this.a[1] & -4294967296L) | (jArr[2] & 4294967295L);
                return;
            }
            j = this.a[0] + ((long) i);
            this.a[0] = j;
            if (j > 9223372034707292160L) {
                this.b = true;
            }
        }

        public long[] e() {
            return this.a;
        }

        public String toString() {
            return b() + " first: " + c() + ", final: " + d();
        }
    }

    static {
        a(256, (int) NotificationCompat.FLAG_HIGH_PRIORITY, new long[]{-2228972824489528736L, -8629553674646093540L, 1155188648486244218L, -3677226592081559102L});
        a(256, 160, new long[]{1450197650740764312L, 3081844928540042640L, -3136097061834271170L, 3301952811952417661L});
        a(256, 224, new long[]{-4176654842910610933L, -8688192972455077604L, -7364642305011795836L, 4056579644589979102L});
        a(256, 256, new long[]{-243853671043386295L, 3443677322885453875L, -5531612722399640561L, 7662005193972177513L});
        a(512, (int) NotificationCompat.FLAG_HIGH_PRIORITY, new long[]{-6288014694233956526L, 2204638249859346602L, 3502419045458743507L, -4829063503441264548L, 983504137758028059L, 1880512238245786339L, -6715892782214108542L, 7602827311880509485L});
        a(512, 160, new long[]{2934123928682216849L, -4399710721982728305L, 1684584802963255058L, 5744138295201861711L, 2444857010922934358L, -2807833639722848072L, -5121587834665610502L, 118355523173251694L});
        a(512, 224, new long[]{-3688341020067007964L, -3772225436291745297L, -8300862168937575580L, 4146387520469897396L, 1106145742801415120L, 7455425944880474941L, -7351063101234211863L, -7048981346965512457L});
        a(512, 384, new long[]{-6631894876634615969L, -5692838220127733084L, -7099962856338682626L, -2911352911530754598L, 2000907093792408677L, 9140007292425499655L, 6093301768906360022L, 2769176472213098488L});
        a(512, 512, new long[]{5261240102383538638L, 978932832955457283L, -8083517948103779378L, -7339365279355032399L, 6752626034097301424L, -1531723821829733388L, -7417126464950782685L, -5901786942805128141L});
    }

    private static void a(int i, int i2, long[] jArr) {
        c.put(a(i / 8, i2 / 8), jArr);
    }

    private static Integer a(int i, int i2) {
        return new Integer((i2 << 16) | i);
    }

    public SkeinEngine(int i, int i2) {
        this.j = new byte[1];
        if (i2 % 8 != 0) {
            throw new IllegalArgumentException("Output size must be a multiple of 8 bits. :" + i2);
        }
        this.d = i2 / 8;
        this.a = new ThreefishEngine(i);
        this.i = new UBI(this, this.a.b());
    }

    public SkeinEngine(SkeinEngine skeinEngine) {
        this(skeinEngine.b() * 8, skeinEngine.a() * 8);
        a(skeinEngine);
    }

    private void a(SkeinEngine skeinEngine) {
        this.i.a(skeinEngine.i);
        this.b = Arrays.b(skeinEngine.b, this.b);
        this.e = Arrays.b(skeinEngine.e, this.e);
        this.f = Arrays.c(skeinEngine.f, this.f);
        this.g = a(skeinEngine.g, this.g);
        this.h = a(skeinEngine.h, this.h);
    }

    private static Parameter[] a(Parameter[] parameterArr, Parameter[] parameterArr2) {
        if (parameterArr == null) {
            return null;
        }
        if (parameterArr2 == null || parameterArr2.length != parameterArr.length) {
            parameterArr2 = new Parameter[parameterArr.length];
        }
        System.arraycopy(parameterArr, 0, parameterArr2, 0, parameterArr2.length);
        return parameterArr2;
    }

    public Memoable e() {
        return new SkeinEngine(this);
    }

    public void a(Memoable memoable) {
        SkeinEngine skeinEngine = (SkeinEngine) memoable;
        if (b() == skeinEngine.b() && this.d == skeinEngine.d) {
            a(skeinEngine);
            return;
        }
        throw new IllegalArgumentException("Incompatible parameters in provided SkeinEngine.");
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.a.b();
    }

    public void a(SkeinParameters skeinParameters) {
        this.b = null;
        this.f = null;
        this.g = null;
        this.h = null;
        if (skeinParameters != null) {
            if (skeinParameters.b().length < 16) {
                throw new IllegalArgumentException("Skein key must be at least 128 bits.");
            }
            a(skeinParameters.a());
        }
        d();
        a(48);
    }

    private void a(Hashtable hashtable) {
        Enumeration keys = hashtable.keys();
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        while (keys.hasMoreElements()) {
            Integer num = (Integer) keys.nextElement();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (num.intValue() == 0) {
                this.f = bArr;
            } else if (num.intValue() < 48) {
                vector.addElement(new Parameter(num.intValue(), bArr));
            } else {
                vector2.addElement(new Parameter(num.intValue(), bArr));
            }
        }
        this.g = new Parameter[vector.size()];
        vector.copyInto(this.g);
        a(this.g);
        this.h = new Parameter[vector2.size()];
        vector2.copyInto(this.h);
        a(this.h);
    }

    private static void a(Parameter[] parameterArr) {
        if (parameterArr != null) {
            for (int i = 1; i < parameterArr.length; i++) {
                Parameter parameter = parameterArr[i];
                int i2 = i;
                while (i2 > 0 && parameter.a() < parameterArr[i2 - 1].a()) {
                    parameterArr[i2] = parameterArr[i2 - 1];
                    i2--;
                }
                parameterArr[i2] = parameter;
            }
        }
    }

    private void d() {
        long[] jArr = (long[]) c.get(a(b(), a()));
        if (this.f != null || jArr == null) {
            this.b = new long[(b() / 8)];
            if (this.f != null) {
                a(0, this.f);
            }
            a(4, new Configuration((long) (this.d * 8)).a());
        } else {
            this.b = Arrays.a(jArr);
        }
        if (this.g != null) {
            for (Parameter parameter : this.g) {
                a(parameter.a(), parameter.b());
            }
        }
        this.e = Arrays.a(this.b);
    }

    public void c() {
        System.arraycopy(this.e, 0, this.b, 0, this.b.length);
        a(48);
    }

    private void a(int i, byte[] bArr) {
        a(i);
        this.i.a(bArr, 0, bArr.length, this.b);
        f();
    }

    private void a(int i) {
        this.i.a(i);
    }

    private void f() {
        this.i.a(this.b);
    }

    private void g() {
        if (this.i == null) {
            throw new IllegalArgumentException("Skein engine is not initialised.");
        }
    }

    public void a(byte b) {
        this.j[0] = b;
        a(this.j, 0, 1);
    }

    public void a(byte[] bArr, int i, int i2) {
        g();
        this.i.a(bArr, i, i2, this.b);
    }

    public int a(byte[] bArr, int i) {
        g();
        if (bArr.length < this.d + i) {
            throw new DataLengthException("Output buffer is too short to hold output of " + this.d + " bytes");
        }
        int i2;
        f();
        if (this.h != null) {
            for (Parameter parameter : this.h) {
                a(parameter.a(), parameter.b());
            }
        }
        int b = b();
        int i3 = ((this.d + b) - 1) / b;
        for (i2 = 0; i2 < i3; i2++) {
            a((long) i2, bArr, i + (i2 * b), Math.min(b, this.d - (i2 * b)));
        }
        c();
        return this.d;
    }

    private void a(long j, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[8];
        ThreefishEngine.a(j, bArr2, 0);
        long[] jArr = new long[this.b.length];
        a(63);
        this.i.a(bArr2, 0, bArr2.length, jArr);
        this.i.a(jArr);
        int i3 = ((i2 + 8) - 1) / 8;
        for (int i4 = 0; i4 < i3; i4++) {
            int min = Math.min(8, i2 - (i4 * 8));
            if (min == 8) {
                ThreefishEngine.a(jArr[i4], bArr, (i4 * 8) + i);
            } else {
                ThreefishEngine.a(jArr[i4], bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, (i4 * 8) + i, min);
            }
        }
    }
}
