package org.spongycastle.crypto.generators;

import com.fgmicrotec.mobile.android.fgmag.VoIP;
import com.mavenir.android.vtow.activation.ActivationAdapter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import net.hockeyapp.android.k;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.spongycastle.crypto.params.NaccacheSternKeyParameters;
import org.spongycastle.crypto.params.NaccacheSternPrivateKeyParameters;

public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static int[] a = new int[]{3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID, 263, 269, 271, 277, 281, 283, 293, ActivationAdapter.REASON_NETWORK_PROVISIONING_ERROR, ActivationAdapter.REASON_SERVICE_ACTIVATION_OK, ActivationAdapter.REASON_SERVICE_BLOCKED, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, VoIP.REASON_CODE_UNAUTHORIZED, VoIP.REASON_CODE_CONFLICT, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, VoIP.REASON_CODE_REQUEST_TERMINATED, 491, 499, VoIP.REASON_CODE_SERVER_SERVICE_UNAVAILABLE, 509, 521, 523, 541, 547, 557};
    private static final BigInteger c = BigInteger.valueOf(1);
    private NaccacheSternKeyGenerationParameters b;

    public AsymmetricCipherKeyPair a() {
        int i;
        BigInteger add;
        BigInteger add2;
        int b = this.b.b();
        SecureRandom a = this.b.a();
        int c = this.b.c();
        boolean e = this.b.e();
        if (e) {
            System.out.println("Fetching first " + this.b.d() + " primes.");
        }
        Vector a2 = a(a(this.b.d()), a);
        BigInteger bigInteger = c;
        BigInteger bigInteger2 = c;
        BigInteger bigInteger3 = bigInteger;
        for (i = 0; i < a2.size() / 2; i++) {
            bigInteger3 = bigInteger3.multiply((BigInteger) a2.elementAt(i));
        }
        for (i = a2.size() / 2; i < a2.size(); i++) {
            bigInteger2 = bigInteger2.multiply((BigInteger) a2.elementAt(i));
        }
        BigInteger multiply = bigInteger3.multiply(bigInteger2);
        int bitLength = (b - multiply.bitLength()) - 48;
        BigInteger a3 = a((bitLength / 2) + 1, c, a);
        BigInteger a4 = a((bitLength / 2) + 1, c, a);
        long j = 0;
        if (e) {
            System.out.println("generating p and q");
        }
        bigInteger3 = a3.multiply(bigInteger3).shiftLeft(1);
        bigInteger2 = a4.multiply(bigInteger2).shiftLeft(1);
        while (true) {
            BigInteger a5;
            j++;
            BigInteger a6 = a(24, c, a);
            add = a6.multiply(bigInteger3).add(c);
            if (add.isProbablePrime(c)) {
                while (true) {
                    a5 = a(24, c, a);
                    if (!a6.equals(a5)) {
                        add2 = a5.multiply(bigInteger2).add(c);
                        if (add2.isProbablePrime(c)) {
                            break;
                        }
                    }
                }
                if (multiply.gcd(a6.multiply(a5)).equals(c)) {
                    if (add.multiply(add2).bitLength() >= b) {
                        break;
                    } else if (e) {
                        System.out.println("key size too small. Should be " + b + " but is actually " + add.multiply(add2).bitLength());
                    }
                } else {
                    continue;
                }
            }
        }
        if (e) {
            System.out.println("needed " + j + " tries to generate p and q.");
        }
        bigInteger3 = add.multiply(add2);
        BigInteger multiply2 = add.subtract(c).multiply(add2.subtract(c));
        j = 0;
        if (e) {
            System.out.println("generating g");
        }
        while (true) {
            Object obj;
            Vector vector = new Vector();
            long j2 = j;
            for (i = 0; i != a2.size(); i++) {
                BigInteger divide = multiply2.divide((BigInteger) a2.elementAt(i));
                do {
                    j2++;
                    bigInteger2 = new BigInteger(b, c, a);
                } while (bigInteger2.modPow(divide, bigInteger3).equals(c));
                vector.addElement(bigInteger2);
            }
            bigInteger = c;
            for (int i2 = 0; i2 < a2.size(); i2++) {
                bigInteger = bigInteger.multiply(((BigInteger) vector.elementAt(i2)).modPow(multiply.divide((BigInteger) a2.elementAt(i2)), bigInteger3)).mod(bigInteger3);
            }
            int i3 = 0;
            while (i3 < a2.size()) {
                if (bigInteger.modPow(multiply2.divide((BigInteger) a2.elementAt(i3)), bigInteger3).equals(c)) {
                    if (e) {
                        System.out.println("g has order phi(n)/" + a2.elementAt(i3) + "\n g: " + bigInteger);
                    }
                    obj = 1;
                    if (obj == null) {
                        j = j2;
                    } else {
                        if (bigInteger.modPow(multiply2.divide(BigInteger.valueOf(4)), bigInteger3).equals(c)) {
                            if (bigInteger.modPow(multiply2.divide(a6), bigInteger3).equals(c)) {
                                if (bigInteger.modPow(multiply2.divide(a5), bigInteger3).equals(c)) {
                                    if (bigInteger.modPow(multiply2.divide(a3), bigInteger3).equals(c)) {
                                        if (bigInteger.modPow(multiply2.divide(a4), bigInteger3).equals(c)) {
                                            break;
                                        } else if (e) {
                                            System.out.println("g has order phi(n)/b\n g: " + bigInteger);
                                            j = j2;
                                        }
                                    } else if (e) {
                                        System.out.println("g has order phi(n)/a\n g: " + bigInteger);
                                        j = j2;
                                    }
                                } else if (e) {
                                    System.out.println("g has order phi(n)/q'\n g: " + bigInteger);
                                    j = j2;
                                }
                            } else if (e) {
                                System.out.println("g has order phi(n)/p'\n g: " + bigInteger);
                                j = j2;
                            }
                        } else if (e) {
                            System.out.println("g has order phi(n)/4\n g:" + bigInteger);
                            j = j2;
                        }
                        j = j2;
                    }
                } else {
                    i3++;
                }
            }
            obj = null;
            if (obj == null) {
                if (bigInteger.modPow(multiply2.divide(BigInteger.valueOf(4)), bigInteger3).equals(c)) {
                    if (bigInteger.modPow(multiply2.divide(a6), bigInteger3).equals(c)) {
                        if (bigInteger.modPow(multiply2.divide(a5), bigInteger3).equals(c)) {
                            if (bigInteger.modPow(multiply2.divide(a3), bigInteger3).equals(c)) {
                                if (bigInteger.modPow(multiply2.divide(a4), bigInteger3).equals(c)) {
                                    break;
                                } else if (e) {
                                    System.out.println("g has order phi(n)/b\n g: " + bigInteger);
                                    j = j2;
                                }
                            } else if (e) {
                                System.out.println("g has order phi(n)/a\n g: " + bigInteger);
                                j = j2;
                            }
                        } else if (e) {
                            System.out.println("g has order phi(n)/q'\n g: " + bigInteger);
                            j = j2;
                        }
                    } else if (e) {
                        System.out.println("g has order phi(n)/p'\n g: " + bigInteger);
                        j = j2;
                    }
                } else if (e) {
                    System.out.println("g has order phi(n)/4\n g:" + bigInteger);
                    j = j2;
                }
                j = j2;
            } else {
                j = j2;
            }
        }
        if (e) {
            System.out.println("needed " + j2 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + a2);
            System.out.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            System.out.println("a:.......... " + a3);
            System.out.println("b:.......... " + a4);
            System.out.println("p':......... " + a6);
            System.out.println("q':......... " + a5);
            System.out.println("p:.......... " + add);
            System.out.println("q:.......... " + add2);
            System.out.println("n:.......... " + bigInteger3);
            System.out.println("phi(n):..... " + multiply2);
            System.out.println("g:.......... " + bigInteger);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair(new NaccacheSternKeyParameters(false, bigInteger, bigInteger3, multiply.bitLength()), new NaccacheSternPrivateKeyParameters(bigInteger, bigInteger3, multiply.bitLength(), a2, multiply2));
    }

    private static BigInteger a(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(i, i2, secureRandom);
        while (bigInteger.bitLength() != i) {
            bigInteger = new BigInteger(i, i2, secureRandom);
        }
        return bigInteger;
    }

    private static Vector a(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        vector3.removeElementAt(0);
        while (vector3.size() != 0) {
            vector2.insertElementAt(vector3.elementAt(0), a(secureRandom, vector2.size() + 1));
            vector3.removeElementAt(0);
        }
        return vector2;
    }

    private static int a(SecureRandom secureRandom, int i) {
        if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) (secureRandom.nextInt() & Integer.MAX_VALUE))) >> 31);
        }
        int i2;
        int nextInt;
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector a(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf((long) a[i2]));
        }
        return vector;
    }
}
