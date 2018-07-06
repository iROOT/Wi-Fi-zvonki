package org.spongycastle.pqc.math.linearalgebra;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import java.util.Random;
import net.hockeyapp.android.k;

public class GF2Polynomial {
    private static Random d = new Random();
    private static final boolean[] e = new boolean[]{false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private static final short[] f = new short[]{(short) 0, (short) 1, (short) 4, (short) 5, (short) 16, (short) 17, (short) 20, (short) 21, (short) 64, (short) 65, (short) 68, (short) 69, (short) 80, (short) 81, (short) 84, (short) 85, (short) 256, (short) 257, (short) 260, (short) 261, (short) 272, (short) 273, (short) 276, (short) 277, (short) 320, (short) 321, (short) 324, (short) 325, (short) 336, (short) 337, (short) 340, (short) 341, (short) 1024, (short) 1025, (short) 1028, (short) 1029, (short) 1040, (short) 1041, (short) 1044, (short) 1045, (short) 1088, (short) 1089, (short) 1092, (short) 1093, (short) 1104, (short) 1105, (short) 1108, (short) 1109, (short) 1280, (short) 1281, (short) 1284, (short) 1285, (short) 1296, (short) 1297, (short) 1300, (short) 1301, (short) 1344, (short) 1345, (short) 1348, (short) 1349, (short) 1360, (short) 1361, (short) 1364, (short) 1365, (short) 4096, (short) 4097, (short) 4100, (short) 4101, (short) 4112, (short) 4113, (short) 4116, (short) 4117, (short) 4160, (short) 4161, (short) 4164, (short) 4165, (short) 4176, (short) 4177, (short) 4180, (short) 4181, (short) 4352, (short) 4353, (short) 4356, (short) 4357, (short) 4368, (short) 4369, (short) 4372, (short) 4373, (short) 4416, (short) 4417, (short) 4420, (short) 4421, (short) 4432, (short) 4433, (short) 4436, (short) 4437, (short) 5120, (short) 5121, (short) 5124, (short) 5125, (short) 5136, (short) 5137, (short) 5140, (short) 5141, (short) 5184, (short) 5185, (short) 5188, (short) 5189, (short) 5200, (short) 5201, (short) 5204, (short) 5205, (short) 5376, (short) 5377, (short) 5380, (short) 5381, (short) 5392, (short) 5393, (short) 5396, (short) 5397, (short) 5440, (short) 5441, (short) 5444, (short) 5445, (short) 5456, (short) 5457, (short) 5460, (short) 5461, (short) 16384, (short) 16385, (short) 16388, (short) 16389, (short) 16400, (short) 16401, (short) 16404, (short) 16405, (short) 16448, (short) 16449, (short) 16452, (short) 16453, (short) 16464, (short) 16465, (short) 16468, (short) 16469, (short) 16640, (short) 16641, (short) 16644, (short) 16645, (short) 16656, (short) 16657, (short) 16660, (short) 16661, (short) 16704, (short) 16705, (short) 16708, (short) 16709, (short) 16720, (short) 16721, (short) 16724, (short) 16725, (short) 17408, (short) 17409, (short) 17412, (short) 17413, (short) 17424, (short) 17425, (short) 17428, (short) 17429, (short) 17472, (short) 17473, (short) 17476, (short) 17477, (short) 17488, (short) 17489, (short) 17492, (short) 17493, (short) 17664, (short) 17665, (short) 17668, (short) 17669, (short) 17680, (short) 17681, (short) 17684, (short) 17685, (short) 17728, (short) 17729, (short) 17732, (short) 17733, (short) 17744, (short) 17745, (short) 17748, (short) 17749, (short) 20480, (short) 20481, (short) 20484, (short) 20485, (short) 20496, (short) 20497, (short) 20500, (short) 20501, (short) 20544, (short) 20545, (short) 20548, (short) 20549, (short) 20560, (short) 20561, (short) 20564, (short) 20565, (short) 20736, (short) 20737, (short) 20740, (short) 20741, (short) 20752, (short) 20753, (short) 20756, (short) 20757, (short) 20800, (short) 20801, (short) 20804, (short) 20805, (short) 20816, (short) 20817, (short) 20820, (short) 20821, (short) 21504, (short) 21505, (short) 21508, (short) 21509, (short) 21520, (short) 21521, (short) 21524, (short) 21525, (short) 21568, (short) 21569, (short) 21572, (short) 21573, (short) 21584, (short) 21585, (short) 21588, (short) 21589, (short) 21760, (short) 21761, (short) 21764, (short) 21765, (short) 21776, (short) 21777, (short) 21780, (short) 21781, (short) 21824, (short) 21825, (short) 21828, (short) 21829, (short) 21840, (short) 21841, (short) 21844, (short) 21845};
    private static final int[] g = new int[]{1, 2, 4, 8, 16, 32, 64, NotificationCompat.FLAG_HIGH_PRIORITY, 256, 512, k.FEEDBACK_FAILED_TITLE_ID, k.DIALOG_POSITIVE_BUTTON_ID, FragmentTransaction.TRANSIT_ENTER_MASK, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, Integer.MIN_VALUE, 0};
    private static final int[] h = new int[]{0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, 1073741823, Integer.MAX_VALUE, -1};
    private int a;
    private int b;
    private int[] c;

    public GF2Polynomial(int i) {
        if (i < 1) {
            i = 1;
        }
        this.b = ((i - 1) >> 5) + 1;
        this.c = new int[this.b];
        this.a = i;
    }

    public GF2Polynomial(int i, String str) {
        if (i < 1) {
            i = 1;
        }
        this.b = ((i - 1) >> 5) + 1;
        this.c = new int[this.b];
        this.a = i;
        if (str.equalsIgnoreCase("ZERO")) {
            d();
        } else if (str.equalsIgnoreCase("ONE")) {
            a();
        } else if (str.equalsIgnoreCase("RANDOM")) {
            e();
        } else if (str.equalsIgnoreCase("X")) {
            b();
        } else if (str.equalsIgnoreCase("ALL")) {
            c();
        } else {
            throw new IllegalArgumentException("Error: GF2Polynomial was called using " + str + " as value!");
        }
    }

    public GF2Polynomial(int i, int[] iArr) {
        if (i < 1) {
            i = 1;
        }
        this.b = ((i - 1) >> 5) + 1;
        this.c = new int[this.b];
        this.a = i;
        System.arraycopy(iArr, 0, this.c, 0, Math.min(this.b, iArr.length));
        l();
    }

    public GF2Polynomial(GF2Polynomial gF2Polynomial) {
        this.a = gF2Polynomial.a;
        this.b = gF2Polynomial.b;
        this.c = IntUtils.a(gF2Polynomial.c);
    }

    public Object clone() {
        return new GF2Polynomial(this);
    }

    public String a(int i) {
        String str;
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String[] strArr = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        String str2 = new String();
        int i2;
        if (i == 16) {
            str = str2;
            i2 = this.b - 1;
            while (i2 >= 0) {
                i2--;
                str = ((((((((str + cArr[(this.c[i2] >>> 28) & 15]) + cArr[(this.c[i2] >>> 24) & 15]) + cArr[(this.c[i2] >>> 20) & 15]) + cArr[(this.c[i2] >>> 16) & 15]) + cArr[(this.c[i2] >>> 12) & 15]) + cArr[(this.c[i2] >>> 8) & 15]) + cArr[(this.c[i2] >>> 4) & 15]) + cArr[this.c[i2] & 15]) + " ";
            }
        } else {
            str = str2;
            i2 = this.b - 1;
            while (i2 >= 0) {
                i2--;
                str = ((((((((str + strArr[(this.c[i2] >>> 28) & 15]) + strArr[(this.c[i2] >>> 24) & 15]) + strArr[(this.c[i2] >>> 20) & 15]) + strArr[(this.c[i2] >>> 16) & 15]) + strArr[(this.c[i2] >>> 12) & 15]) + strArr[(this.c[i2] >>> 8) & 15]) + strArr[(this.c[i2] >>> 4) & 15]) + strArr[this.c[i2] & 15]) + " ";
            }
        }
        return str;
    }

    public void a() {
        for (int i = 1; i < this.b; i++) {
            this.c[i] = 0;
        }
        this.c[0] = 1;
    }

    public void b() {
        for (int i = 1; i < this.b; i++) {
            this.c[i] = 0;
        }
        this.c[0] = 2;
    }

    public void c() {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = -1;
        }
        l();
    }

    public void d() {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = 0;
        }
    }

    public void e() {
        for (int i = 0; i < this.b; i++) {
            this.c[i] = d.nextInt();
        }
        l();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2Polynomial)) {
            return false;
        }
        GF2Polynomial gF2Polynomial = (GF2Polynomial) obj;
        if (this.a != gF2Polynomial.a) {
            return false;
        }
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] != gF2Polynomial.c[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.a + this.c.hashCode();
    }

    public boolean f() {
        if (this.a == 0) {
            return true;
        }
        for (int i = 0; i < this.b; i++) {
            if (this.c[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean g() {
        for (int i = 1; i < this.b; i++) {
            if (this.c[i] != 0) {
                return false;
            }
        }
        if (this.c[0] != 1) {
            return false;
        }
        return true;
    }

    public void a(GF2Polynomial gF2Polynomial) {
        b(gF2Polynomial.a);
        g(gF2Polynomial);
    }

    public GF2Polynomial b(GF2Polynomial gF2Polynomial) {
        return f(gF2Polynomial);
    }

    public void c(GF2Polynomial gF2Polynomial) {
        b(gF2Polynomial.a);
        g(gF2Polynomial);
    }

    public GF2Polynomial d(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial3.f()) {
            throw new RuntimeException();
        }
        gF2Polynomial2.i();
        gF2Polynomial3.i();
        if (gF2Polynomial2.a >= gF2Polynomial3.a) {
            for (int i = gF2Polynomial2.a - gF2Polynomial3.a; i >= 0; i = gF2Polynomial2.a - gF2Polynomial3.a) {
                gF2Polynomial2.c(gF2Polynomial3.e(i));
                gF2Polynomial2.i();
            }
        }
        return gF2Polynomial2;
    }

    public GF2Polynomial e(GF2Polynomial gF2Polynomial) {
        if (f() && gF2Polynomial.f()) {
            throw new ArithmeticException("Both operands of gcd equal zero.");
        } else if (f()) {
            return new GF2Polynomial(gF2Polynomial);
        } else {
            if (gF2Polynomial.f()) {
                return new GF2Polynomial(this);
            }
            GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
            GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
            while (!gF2Polynomial3.f()) {
                GF2Polynomial d = gF2Polynomial2.d(gF2Polynomial3);
                gF2Polynomial2 = gF2Polynomial3;
                gF2Polynomial3 = d;
            }
            return gF2Polynomial2;
        }
    }

    public boolean h() {
        if (f()) {
            return false;
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.i();
        int i = gF2Polynomial.a - 1;
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(gF2Polynomial.a, "X");
        for (int i2 = 1; i2 <= (i >> 1); i2++) {
            gF2Polynomial2.j();
            gF2Polynomial2 = gF2Polynomial2.d(gF2Polynomial);
            GF2Polynomial b = gF2Polynomial2.b(new GF2Polynomial(32, "X"));
            if (b.f() || !gF2Polynomial.e(b).g()) {
                return false;
            }
        }
        return true;
    }

    public void i() {
        int i = this.b - 1;
        while (this.c[i] == 0 && i > 0) {
            i--;
        }
        int i2 = this.c[i];
        int i3 = 0;
        while (i2 != 0) {
            i2 >>>= 1;
            i3++;
        }
        this.a = (i << 5) + i3;
        this.b = i + 1;
    }

    public void b(int i) {
        if (this.a < i) {
            this.a = i;
            int i2 = ((i - 1) >>> 5) + 1;
            if (this.b >= i2) {
                return;
            }
            if (this.c.length >= i2) {
                for (int i3 = this.b; i3 < i2; i3++) {
                    this.c[i3] = 0;
                }
                this.b = i2;
                return;
            }
            Object obj = new int[i2];
            System.arraycopy(this.c, 0, obj, 0, this.b);
            this.b = i2;
            this.c = null;
            this.c = obj;
        }
    }

    public void j() {
        if (!f()) {
            int i;
            if (this.c.length >= (this.b << 1)) {
                for (i = this.b - 1; i >= 0; i--) {
                    this.c[(i << 1) + 1] = f[(this.c[i] & 16711680) >>> 16] | (f[(this.c[i] & -16777216) >>> 24] << 16);
                    this.c[i << 1] = f[this.c[i] & 255] | (f[(this.c[i] & 65280) >>> 8] << 16);
                }
                this.b <<= 1;
                this.a = (this.a << 1) - 1;
                return;
            }
            int[] iArr = new int[(this.b << 1)];
            for (i = 0; i < this.b; i++) {
                iArr[i << 1] = f[this.c[i] & 255] | (f[(this.c[i] & 65280) >>> 8] << 16);
                iArr[(i << 1) + 1] = f[(this.c[i] & 16711680) >>> 16] | (f[(this.c[i] & -16777216) >>> 24] << 16);
            }
            this.c = null;
            this.c = iArr;
            this.b <<= 1;
            this.a = (this.a << 1) - 1;
        }
    }

    public GF2Polynomial f(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2;
        int i = 0;
        int min = Math.min(this.b, gF2Polynomial.b);
        int[] iArr;
        if (this.a >= gF2Polynomial.a) {
            gF2Polynomial2 = new GF2Polynomial(this);
            while (i < min) {
                iArr = gF2Polynomial2.c;
                iArr[i] = iArr[i] ^ gF2Polynomial.c[i];
                i++;
            }
        } else {
            gF2Polynomial2 = new GF2Polynomial(gF2Polynomial);
            while (i < min) {
                iArr = gF2Polynomial2.c;
                iArr[i] = iArr[i] ^ this.c[i];
                i++;
            }
        }
        gF2Polynomial2.l();
        return gF2Polynomial2;
    }

    public void g(GF2Polynomial gF2Polynomial) {
        for (int i = 0; i < Math.min(this.b, gF2Polynomial.b); i++) {
            int[] iArr = this.c;
            iArr[i] = iArr[i] ^ gF2Polynomial.c[i];
        }
        l();
    }

    private void l() {
        if ((this.a & 31) != 0) {
            int[] iArr = this.c;
            int i = this.b - 1;
            iArr[i] = iArr[i] & h[this.a & 31];
        }
    }

    public void c(int i) {
        if (i < 0 || i > this.a - 1) {
            throw new RuntimeException();
        } else if (i <= this.a - 1) {
            int[] iArr = this.c;
            int i2 = i >>> 5;
            iArr[i2] = iArr[i2] | g[i & 31];
        }
    }

    public void d(int i) {
        if (i < 0 || i > this.a - 1) {
            throw new RuntimeException();
        } else if (i <= this.a - 1) {
            int[] iArr = this.c;
            int i2 = i >>> 5;
            iArr[i2] = iArr[i2] & (g[i & 31] ^ -1);
        }
    }

    public GF2Polynomial k() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.a + 1, this.c);
        for (int i = gF2Polynomial.b - 1; i >= 1; i--) {
            int[] iArr = gF2Polynomial.c;
            iArr[i] = iArr[i] << 1;
            iArr = gF2Polynomial.c;
            iArr[i] = iArr[i] | (gF2Polynomial.c[i - 1] >>> 31);
        }
        int[] iArr2 = gF2Polynomial.c;
        iArr2[0] = iArr2[0] << 1;
        return gF2Polynomial;
    }

    public GF2Polynomial e(int i) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.a + i, this.c);
        if (i >= 32) {
            gF2Polynomial.f(i >>> 5);
        }
        int i2 = i & 31;
        if (i2 != 0) {
            for (int i3 = gF2Polynomial.b - 1; i3 >= 1; i3--) {
                int[] iArr = gF2Polynomial.c;
                iArr[i3] = iArr[i3] << i2;
                iArr = gF2Polynomial.c;
                iArr[i3] = iArr[i3] | (gF2Polynomial.c[i3 - 1] >>> (32 - i2));
            }
            int[] iArr2 = gF2Polynomial.c;
            iArr2[0] = iArr2[0] << i2;
        }
        return gF2Polynomial;
    }

    private void f(int i) {
        if (this.b <= this.c.length) {
            int i2;
            for (i2 = this.b - 1; i2 >= i; i2--) {
                this.c[i2] = this.c[i2 - i];
            }
            for (i2 = 0; i2 < i; i2++) {
                this.c[i2] = 0;
            }
            return;
        }
        Object obj = new int[this.b];
        System.arraycopy(this.c, 0, obj, i, this.b - i);
        this.c = null;
        this.c = obj;
    }
}
