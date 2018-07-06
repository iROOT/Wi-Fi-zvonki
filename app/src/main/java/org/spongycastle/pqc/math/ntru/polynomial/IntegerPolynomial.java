package org.spongycastle.pqc.math.ntru.polynomial;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import net.hockeyapp.android.k;
import net.hockeyapp.android.views.FeedbackView;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.util.ArrayEncoder;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

public class IntegerPolynomial implements Polynomial {
    private static final int[] b = new int[]{4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693, 5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207, 7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017, 8039, 8053, 8059, 8069, 8081, 8087, 8089, 8093, 8101, 8111, 8117, 8123, 8147, 8161, 8167, 8171, 8179, 8191, FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS, 8219, 8221, 8231, 8233, 8237, 8243, 8263, 8269, 8273, 8287, 8291, 8293, 8297, 8311, 8317, 8329, 8353, 8363, 8369, 8377, 8387, 8389, 8419, 8423, 8429, 8431, 8443, 8447, 8461, 8467, 8501, 8513, 8521, 8527, 8537, 8539, 8543, 8563, 8573, 8581, 8597, 8599, 8609, 8623, 8627, 8629, 8641, 8647, 8663, 8669, 8677, 8681, 8689, 8693, 8699, 8707, 8713, 8719, 8731, 8737, 8741, 8747, 8753, 8761, 8779, 8783, 8803, 8807, 8819, 8821, 8831, 8837, 8839, 8849, 8861, 8863, 8867, 8887, 8893, 8923, 8929, 8933, 8941, 8951, 8963, 8969, 8971, 8999, 9001, 9007, 9011, 9013, 9029, 9041, 9043, 9049, 9059, 9067, 9091, 9103, 9109, 9127, 9133, 9137, 9151, 9157, 9161, 9173, 9181, 9187, 9199, 9203, 9209, 9221, 9227, 9239, 9241, 9257, 9277, 9281, 9283, 9293, 9311, 9319, 9323, 9337, 9341, 9343, 9349, 9371, 9377, 9391, 9397, 9403, 9413, 9419, 9421, 9431, 9433, 9437, 9439, 9461, 9463, 9467, 9473, 9479, 9491, 9497, 9511, 9521, 9533, 9539, 9547, 9551, 9587, 9601, 9613, 9619, 9623, 9629, 9631, 9643, 9649, 9661, 9677, 9679, 9689, 9697, 9719, 9721, 9733, 9739, 9743, 9749, 9767, 9769, 9781, 9787, 9791, 9803, 9811, 9817, 9829, 9833, 9839, 9851, 9857, 9859, 9871, 9883, 9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973};
    private static final List c = new ArrayList();
    public int[] a;

    private class CombineTask implements Callable<ModularResultant> {
        private ModularResultant a;
        private ModularResultant b;

        public /* synthetic */ Object call() {
            return a();
        }

        public ModularResultant a() {
            return ModularResultant.a(this.a, this.b);
        }
    }

    private class ModResultantTask implements Callable<ModularResultant> {
        final /* synthetic */ IntegerPolynomial a;
        private int b;

        public /* synthetic */ Object call() {
            return a();
        }

        public ModularResultant a() {
            return this.a.c(this.b);
        }
    }

    static {
        for (int i = 0; i != b.length; i++) {
            c.add(BigInteger.valueOf((long) b[i]));
        }
    }

    public IntegerPolynomial(int i) {
        this.a = new int[i];
    }

    public IntegerPolynomial(int[] iArr) {
        this.a = iArr;
    }

    public IntegerPolynomial(BigIntPolynomial bigIntPolynomial) {
        this.a = new int[bigIntPolynomial.a.length];
        for (int i = 0; i < bigIntPolynomial.a.length; i++) {
            this.a[i] = bigIntPolynomial.a[i].intValue();
        }
    }

    public static IntegerPolynomial a(byte[] bArr, int i) {
        return new IntegerPolynomial(ArrayEncoder.a(bArr, i));
    }

    public static IntegerPolynomial a(byte[] bArr, int i, int i2) {
        return new IntegerPolynomial(ArrayEncoder.a(bArr, i, i2));
    }

    public byte[] d() {
        return ArrayEncoder.a(this.a);
    }

    public byte[] a(int i) {
        return ArrayEncoder.a(this.a, i);
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial, int i) {
        IntegerPolynomial a = a(integerPolynomial);
        a.i(i);
        return a;
    }

    public IntegerPolynomial a(IntegerPolynomial integerPolynomial) {
        int length = this.a.length;
        if (integerPolynomial.a.length != length) {
            throw new IllegalArgumentException("Number of coefficients must be the same");
        }
        IntegerPolynomial d = d(integerPolynomial);
        if (d.a.length > length) {
            for (int i = length; i < d.a.length; i++) {
                int[] iArr = d.a;
                int i2 = i - length;
                iArr[i2] = iArr[i2] + d.a[i];
            }
            d.a = Arrays.b(d.a, length);
        }
        return d;
    }

    public BigIntPolynomial a(BigIntPolynomial bigIntPolynomial) {
        return new BigIntPolynomial(this).a(bigIntPolynomial);
    }

    private IntegerPolynomial d(IntegerPolynomial integerPolynomial) {
        int i = 0;
        int[] iArr = this.a;
        int[] iArr2 = integerPolynomial.a;
        int length = integerPolynomial.a.length;
        IntegerPolynomial integerPolynomial2;
        int i2;
        int max;
        if (length <= 32) {
            int i3 = (length * 2) - 1;
            integerPolynomial2 = new IntegerPolynomial(new int[i3]);
            for (i2 = 0; i2 < i3; i2++) {
                for (max = Math.max(0, (i2 - length) + 1); max <= Math.min(i2, length - 1); max++) {
                    int[] iArr3 = integerPolynomial2.a;
                    iArr3[i2] = iArr3[i2] + (iArr2[max] * iArr[i2 - max]);
                }
            }
            return integerPolynomial2;
        }
        i2 = length / 2;
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(Arrays.b(iArr, i2));
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(Arrays.a(iArr, i2, length));
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(Arrays.b(iArr2, i2));
        IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(Arrays.a(iArr2, i2, length));
        IntegerPolynomial integerPolynomial7 = (IntegerPolynomial) integerPolynomial3.clone();
        integerPolynomial7.b(integerPolynomial4);
        integerPolynomial2 = (IntegerPolynomial) integerPolynomial5.clone();
        integerPolynomial2.b(integerPolynomial6);
        integerPolynomial5 = integerPolynomial3.d(integerPolynomial5);
        IntegerPolynomial d = integerPolynomial4.d(integerPolynomial6);
        integerPolynomial3 = integerPolynomial7.d(integerPolynomial2);
        integerPolynomial3.c(integerPolynomial5);
        integerPolynomial3.c(d);
        integerPolynomial2 = new IntegerPolynomial((length * 2) - 1);
        for (max = 0; max < integerPolynomial5.a.length; max++) {
            integerPolynomial2.a[max] = integerPolynomial5.a[max];
        }
        for (max = 0; max < integerPolynomial3.a.length; max++) {
            iArr = integerPolynomial2.a;
            length = i2 + max;
            iArr[length] = iArr[length] + integerPolynomial3.a[max];
        }
        while (i < d.a.length) {
            int[] iArr4 = integerPolynomial2.a;
            int i4 = (i2 * 2) + i;
            iArr4[i4] = iArr4[i4] + d.a[i];
            i++;
        }
        return integerPolynomial2;
    }

    public IntegerPolynomial b(int i) {
        int length = this.a.length;
        int i2 = 0;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(length + 1);
        integerPolynomial.a[0] = 1;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(length + 1);
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(length + 1);
        integerPolynomial3.a = Arrays.b(this.a, length + 1);
        integerPolynomial3.g(2);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(length + 1);
        integerPolynomial4.a[0] = 1;
        integerPolynomial4.a[length] = 1;
        while (true) {
            if (integerPolynomial3.a[0] == 0) {
                for (int i3 = 1; i3 <= length; i3++) {
                    integerPolynomial3.a[i3 - 1] = integerPolynomial3.a[i3];
                    integerPolynomial2.a[(length + 1) - i3] = integerPolynomial2.a[length - i3];
                }
                integerPolynomial3.a[length] = 0;
                integerPolynomial2.a[0] = 0;
                i2++;
                if (integerPolynomial3.a()) {
                    return null;
                }
            } else if (integerPolynomial3.j()) {
                break;
            } else {
                if (integerPolynomial3.g() < integerPolynomial4.g()) {
                    IntegerPolynomial integerPolynomial5 = integerPolynomial3;
                    integerPolynomial3 = integerPolynomial4;
                    integerPolynomial4 = integerPolynomial5;
                    IntegerPolynomial integerPolynomial6 = integerPolynomial;
                    integerPolynomial = integerPolynomial2;
                    integerPolynomial2 = integerPolynomial6;
                }
                integerPolynomial3.b(integerPolynomial4, 2);
                integerPolynomial.b(integerPolynomial2, 2);
            }
        }
        if (integerPolynomial.a[length] != 0) {
            return null;
        }
        integerPolynomial2 = new IntegerPolynomial(length);
        i2 %= length;
        for (int i4 = length - 1; i4 >= 0; i4--) {
            int i5 = i4 - i2;
            if (i5 < 0) {
                i5 += length;
            }
            integerPolynomial2.a[i5] = integerPolynomial.a[i4];
        }
        return d(integerPolynomial2, i);
    }

    private IntegerPolynomial d(IntegerPolynomial integerPolynomial, int i) {
        int i2 = 2;
        if (Util.a() && i == k.DIALOG_POSITIVE_BUTTON_ID) {
            LongPolynomial2 longPolynomial2 = new LongPolynomial2(this);
            LongPolynomial2 longPolynomial22 = new LongPolynomial2(integerPolynomial);
            while (i2 < i) {
                int i3 = i2 * 2;
                LongPolynomial2 longPolynomial23 = (LongPolynomial2) longPolynomial22.clone();
                longPolynomial23.a(i3 - 1);
                longPolynomial23.a(longPolynomial2.a(longPolynomial22).a(longPolynomial22), i3 - 1);
                longPolynomial22 = longPolynomial23;
                i2 = i3;
            }
            return longPolynomial22.a();
        }
        i3 = 2;
        IntegerPolynomial integerPolynomial2 = integerPolynomial;
        while (i3 < i) {
            i3 *= 2;
            integerPolynomial = new IntegerPolynomial(Arrays.b(integerPolynomial2.a, integerPolynomial2.a.length));
            integerPolynomial.o(i3);
            integerPolynomial.c(a(integerPolynomial2, i3).a(integerPolynomial2, i3), i3);
            integerPolynomial2 = integerPolynomial;
        }
        return integerPolynomial2;
    }

    public IntegerPolynomial e() {
        int length = this.a.length;
        int i = 0;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(length + 1);
        integerPolynomial.a[0] = 1;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(length + 1);
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(length + 1);
        integerPolynomial3.a = Arrays.b(this.a, length + 1);
        integerPolynomial3.g(3);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(length + 1);
        integerPolynomial4.a[0] = -1;
        integerPolynomial4.a[length] = 1;
        while (true) {
            int i2;
            if (integerPolynomial3.a[0] == 0) {
                for (i2 = 1; i2 <= length; i2++) {
                    integerPolynomial3.a[i2 - 1] = integerPolynomial3.a[i2];
                    integerPolynomial2.a[(length + 1) - i2] = integerPolynomial2.a[length - i2];
                }
                integerPolynomial3.a[length] = 0;
                integerPolynomial2.a[0] = 0;
                i++;
                if (integerPolynomial3.a()) {
                    return null;
                }
            } else if (integerPolynomial3.b()) {
                break;
            } else {
                if (integerPolynomial3.g() < integerPolynomial4.g()) {
                    IntegerPolynomial integerPolynomial5 = integerPolynomial3;
                    integerPolynomial3 = integerPolynomial4;
                    integerPolynomial4 = integerPolynomial5;
                    IntegerPolynomial integerPolynomial6 = integerPolynomial;
                    integerPolynomial = integerPolynomial2;
                    integerPolynomial2 = integerPolynomial6;
                }
                if (integerPolynomial3.a[0] == integerPolynomial4.a[0]) {
                    integerPolynomial3.c(integerPolynomial4, 3);
                    integerPolynomial.c(integerPolynomial2, 3);
                } else {
                    integerPolynomial3.b(integerPolynomial4, 3);
                    integerPolynomial.b(integerPolynomial2, 3);
                }
            }
        }
        if (integerPolynomial.a[length] != 0) {
            return null;
        }
        integerPolynomial2 = new IntegerPolynomial(length);
        i2 = i % length;
        for (i = length - 1; i >= 0; i--) {
            int i3 = i - i2;
            if (i3 < 0) {
                i3 += length;
            }
            integerPolynomial2.a[i3] = integerPolynomial3.a[0] * integerPolynomial.a[i];
        }
        integerPolynomial2.j(3);
        return integerPolynomial2;
    }

    public Resultant f() {
        BigInteger multiply;
        int length = this.a.length;
        LinkedList linkedList = new LinkedList();
        BigInteger bigInteger = Constants.b;
        BigInteger bigInteger2 = Constants.b;
        Iterator it = c.iterator();
        BigInteger bigInteger3 = bigInteger2;
        bigInteger2 = bigInteger;
        bigInteger = null;
        int i = 1;
        while (true) {
            int i2;
            BigInteger nextProbablePrime = it.hasNext() ? (BigInteger) it.next() : bigInteger.nextProbablePrime();
            ModularResultant c = c(nextProbablePrime.intValue());
            linkedList.add(c);
            multiply = bigInteger2.multiply(nextProbablePrime);
            BigIntEuclidean a = BigIntEuclidean.a(nextProbablePrime, bigInteger2);
            bigInteger = bigInteger3.multiply(a.a.multiply(nextProbablePrime)).add(c.c.multiply(a.b.multiply(bigInteger2))).mod(multiply);
            bigInteger2 = multiply.divide(BigInteger.valueOf(2));
            BigInteger negate = bigInteger2.negate();
            if (bigInteger.compareTo(bigInteger2) > 0) {
                bigInteger2 = bigInteger.subtract(multiply);
            } else if (bigInteger.compareTo(negate) < 0) {
                bigInteger2 = bigInteger.add(multiply);
            } else {
                bigInteger2 = bigInteger;
            }
            if (bigInteger2.equals(bigInteger3)) {
                i2 = i + 1;
                if (i2 >= 3) {
                    break;
                }
            } else {
                i2 = 1;
            }
            i = i2;
            bigInteger3 = bigInteger2;
            bigInteger2 = multiply;
            bigInteger = nextProbablePrime;
        }
        while (linkedList.size() > 1) {
            linkedList.addLast(ModularResultant.a((ModularResultant) linkedList.removeFirst(), (ModularResultant) linkedList.removeFirst()));
        }
        BigIntPolynomial bigIntPolynomial = ((ModularResultant) linkedList.getFirst()).b;
        BigInteger divide = multiply.divide(BigInteger.valueOf(2));
        bigInteger3 = divide.negate();
        if (bigInteger2.compareTo(divide) > 0) {
            bigInteger2 = bigInteger2.subtract(multiply);
        }
        if (bigInteger2.compareTo(bigInteger3) < 0) {
            bigInteger2 = bigInteger2.add(multiply);
        }
        for (i2 = 0; i2 < length; i2++) {
            nextProbablePrime = bigIntPolynomial.a[i2];
            if (nextProbablePrime.compareTo(divide) > 0) {
                bigIntPolynomial.a[i2] = nextProbablePrime.subtract(multiply);
            }
            if (nextProbablePrime.compareTo(bigInteger3) < 0) {
                bigIntPolynomial.a[i2] = nextProbablePrime.add(multiply);
            }
        }
        return new Resultant(bigIntPolynomial, bigInteger2);
    }

    public ModularResultant c(int i) {
        int[] b = Arrays.b(this.a, this.a.length + 1);
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(b);
        int length = b.length;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(length);
        integerPolynomial2.a[0] = -1;
        integerPolynomial2.a[length - 1] = 1;
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(integerPolynomial.a);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(length);
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
        integerPolynomial5.a[0] = 1;
        int i2 = length - 1;
        int g = integerPolynomial3.g();
        length = 1;
        IntegerPolynomial integerPolynomial6 = integerPolynomial3;
        integerPolynomial3 = integerPolynomial5;
        int i3 = i2;
        while (g > 0) {
            int a = (Util.a(integerPolynomial6.a[g], i) * integerPolynomial2.a[i3]) % i;
            integerPolynomial2.a(integerPolynomial6, a, i3 - g, i);
            integerPolynomial4.a(integerPolynomial3, a, i3 - g, i);
            i3 = integerPolynomial2.g();
            if (i3 < g) {
                length = (length * Util.a(integerPolynomial6.a[g], i2 - i3, i)) % i;
                if (i2 % 2 == 1 && g % 2 == 1) {
                    length = (-length) % i;
                }
                i2 = g;
                int i4 = i3;
                i3 = g;
                g = i4;
                IntegerPolynomial integerPolynomial7 = integerPolynomial4;
                integerPolynomial4 = integerPolynomial3;
                integerPolynomial3 = integerPolynomial7;
                IntegerPolynomial integerPolynomial8 = integerPolynomial2;
                integerPolynomial2 = integerPolynomial6;
                integerPolynomial6 = integerPolynomial8;
            }
        }
        length = (length * Util.a(integerPolynomial6.a[0], i3, i)) % i;
        integerPolynomial3.e(Util.a(integerPolynomial6.a[0], i));
        integerPolynomial3.i(i);
        integerPolynomial3.e(length);
        integerPolynomial3.i(i);
        integerPolynomial3.a = Arrays.b(integerPolynomial3.a, integerPolynomial3.a.length - 1);
        return new ModularResultant(new BigIntPolynomial(integerPolynomial3), BigInteger.valueOf((long) length), BigInteger.valueOf((long) i));
    }

    private void a(IntegerPolynomial integerPolynomial, int i, int i2, int i3) {
        int length = this.a.length;
        for (int i4 = i2; i4 < length; i4++) {
            this.a[i4] = (this.a[i4] - (integerPolynomial.a[i4 - i2] * i)) % i3;
        }
    }

    int g() {
        int length = this.a.length - 1;
        while (length > 0 && this.a[length] == 0) {
            length--;
        }
        return length;
    }

    public void b(IntegerPolynomial integerPolynomial, int i) {
        b(integerPolynomial);
        i(i);
    }

    public void b(IntegerPolynomial integerPolynomial) {
        if (integerPolynomial.a.length > this.a.length) {
            this.a = Arrays.b(this.a, integerPolynomial.a.length);
        }
        for (int i = 0; i < integerPolynomial.a.length; i++) {
            int[] iArr = this.a;
            iArr[i] = iArr[i] + integerPolynomial.a[i];
        }
    }

    public void c(IntegerPolynomial integerPolynomial, int i) {
        c(integerPolynomial);
        i(i);
    }

    public void c(IntegerPolynomial integerPolynomial) {
        if (integerPolynomial.a.length > this.a.length) {
            this.a = Arrays.b(this.a, integerPolynomial.a.length);
        }
        for (int i = 0; i < integerPolynomial.a.length; i++) {
            int[] iArr = this.a;
            iArr[i] = iArr[i] - integerPolynomial.a[i];
        }
    }

    void d(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            int[] iArr = this.a;
            iArr[i2] = iArr[i2] - i;
        }
    }

    public void e(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            int[] iArr = this.a;
            iArr[i2] = iArr[i2] * i;
        }
    }

    private void o(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            int[] iArr = this.a;
            iArr[i2] = iArr[i2] * 2;
            iArr = this.a;
            iArr[i2] = iArr[i2] % i;
        }
    }

    public void f(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            int[] iArr = this.a;
            iArr[i2] = iArr[i2] * 3;
            iArr = this.a;
            iArr[i2] = iArr[i2] % i;
        }
    }

    public void h() {
        for (int i = 0; i < this.a.length; i++) {
            int[] iArr = this.a;
            iArr[i] = iArr[i] % 3;
            if (this.a[i] > 1) {
                iArr = this.a;
                iArr[i] = iArr[i] - 3;
            }
            if (this.a[i] < -1) {
                iArr = this.a;
                iArr[i] = iArr[i] + 3;
            }
        }
    }

    public void g(int i) {
        i(i);
        j(i);
    }

    void h(int i) {
        i(i);
        for (int i2 = 0; i2 < this.a.length; i2++) {
            while (this.a[i2] < i / 2) {
                int[] iArr = this.a;
                iArr[i2] = iArr[i2] + i;
            }
            while (this.a[i2] >= i / 2) {
                iArr = this.a;
                iArr[i2] = iArr[i2] - i;
            }
        }
    }

    public void i(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            int[] iArr = this.a;
            iArr[i2] = iArr[i2] % i;
        }
    }

    public void j(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            while (this.a[i2] < 0) {
                int[] iArr = this.a;
                iArr[i2] = iArr[i2] + i;
            }
        }
    }

    public long k(int i) {
        long j = 0;
        int length = this.a.length;
        IntegerPolynomial integerPolynomial = (IntegerPolynomial) clone();
        integerPolynomial.l(i);
        long j2 = 0;
        for (int i2 = 0; i2 != integerPolynomial.a.length; i2++) {
            int i3 = integerPolynomial.a[i2];
            j2 += (long) i3;
            j += (long) (i3 * i3);
        }
        return j - ((j2 * j2) / ((long) length));
    }

    void l(int i) {
        int i2;
        h(i);
        int[] b = Arrays.b(this.a);
        a(b);
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < b.length - 1; i2++) {
            int i5 = b[i2 + 1] - b[i2];
            if (i5 > i4) {
                i3 = b[i2];
                i4 = i5;
            }
        }
        i2 = b[0];
        int i6 = b[b.length - 1];
        if ((i - i6) + i2 > i4) {
            i2 = (i2 + i6) / 2;
        } else {
            i2 = ((i4 / 2) + i3) + (i / 2);
        }
        d(i2);
    }

    private void a(int[] iArr) {
        Object obj = 1;
        while (obj != null) {
            obj = null;
            for (int i = 0; i != iArr.length - 1; i++) {
                if (iArr[i] > iArr[i + 1]) {
                    int i2 = iArr[i];
                    iArr[i] = iArr[i + 1];
                    iArr[i + 1] = i2;
                    obj = 1;
                }
            }
        }
    }

    public void m(int i) {
        for (int i2 = 0; i2 < this.a.length; i2++) {
            while (this.a[i2] < (-i) / 2) {
                int[] iArr = this.a;
                iArr[i2] = iArr[i2] + i;
            }
            while (this.a[i2] > i / 2) {
                iArr = this.a;
                iArr[i2] = iArr[i2] - i;
            }
        }
    }

    public int i() {
        int i = 0;
        int i2 = 0;
        while (i < this.a.length) {
            i2 += this.a[i];
            i++;
        }
        return i2;
    }

    private boolean a() {
        for (int i : this.a) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean j() {
        boolean z = true;
        for (int i = 1; i < this.a.length; i++) {
            if (this.a[i] != 0) {
                return false;
            }
        }
        if (this.a[0] != 1) {
            z = false;
        }
        return z;
    }

    private boolean b() {
        boolean z = true;
        for (int i = 1; i < this.a.length; i++) {
            if (this.a[i] != 0) {
                return false;
            }
        }
        if (Math.abs(this.a[0]) != 1) {
            z = false;
        }
        return z;
    }

    public int n(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 != this.a.length) {
            if (this.a[i2] == i) {
                i3++;
            }
            i2++;
        }
        return i3;
    }

    public void k() {
        int i = this.a[this.a.length - 1];
        for (int length = this.a.length - 1; length > 0; length--) {
            this.a[length] = this.a[length - 1];
        }
        this.a[0] = i;
    }

    public void l() {
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = 0;
        }
    }

    public IntegerPolynomial m() {
        return (IntegerPolynomial) clone();
    }

    public Object clone() {
        return new IntegerPolynomial((int[]) this.a.clone());
    }

    public boolean equals(Object obj) {
        if (obj instanceof IntegerPolynomial) {
            return Arrays.a(this.a, ((IntegerPolynomial) obj).a);
        }
        return false;
    }
}
