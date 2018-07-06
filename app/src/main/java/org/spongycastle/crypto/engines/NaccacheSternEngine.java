package org.spongycastle.crypto.engines;

import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.NaccacheSternKeyParameters;
import org.spongycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class NaccacheSternEngine implements AsymmetricBlockCipher {
    private static BigInteger e = BigInteger.valueOf(0);
    private static BigInteger f = BigInteger.valueOf(1);
    private boolean a;
    private NaccacheSternKeyParameters b;
    private Vector[] c = null;
    private boolean d = false;

    public void a(boolean z, CipherParameters cipherParameters) {
        CipherParameters b;
        this.a = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            b = ((ParametersWithRandom) cipherParameters).b();
        } else {
            b = cipherParameters;
        }
        this.b = (NaccacheSternKeyParameters) b;
        if (!this.a) {
            if (this.d) {
                System.out.println("Constructing lookup Array");
            }
            NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = (NaccacheSternPrivateKeyParameters) this.b;
            Vector f = naccacheSternPrivateKeyParameters.f();
            this.c = new Vector[f.size()];
            for (int i = 0; i < f.size(); i++) {
                BigInteger bigInteger = (BigInteger) f.elementAt(i);
                int intValue = bigInteger.intValue();
                this.c[i] = new Vector();
                this.c[i].addElement(f);
                if (this.d) {
                    System.out.println("Constructing lookup ArrayList for " + intValue);
                }
                BigInteger bigInteger2 = e;
                for (int i2 = 1; i2 < intValue; i2++) {
                    bigInteger2 = bigInteger2.add(naccacheSternPrivateKeyParameters.e());
                    this.c[i].addElement(naccacheSternPrivateKeyParameters.b().modPow(bigInteger2.divide(bigInteger), naccacheSternPrivateKeyParameters.d()));
                }
            }
        }
    }

    public int a() {
        if (this.a) {
            return ((this.b.c() + 7) / 8) - 1;
        }
        return this.b.d().toByteArray().length;
    }

    public int b() {
        if (this.a) {
            return this.b.d().toByteArray().length;
        }
        return ((this.b.c() + 7) / 8) - 1;
    }

    public byte[] a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        if (this.b == null) {
            throw new IllegalStateException("NaccacheStern engine not initialised");
        } else if (i2 > a() + 1) {
            throw new DataLengthException("input too large for Naccache-Stern cipher.\n");
        } else if (this.a || i2 >= a()) {
            if (!(i == 0 && i2 == bArr.length)) {
                Object obj = new byte[i2];
                System.arraycopy(bArr, i, obj, 0, i2);
                bArr = obj;
            }
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (this.d) {
                System.out.println("input as BigInteger: " + bigInteger);
            }
            if (this.a) {
                return a(bigInteger);
            }
            Vector vector = new Vector();
            NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = (NaccacheSternPrivateKeyParameters) this.b;
            Vector f = naccacheSternPrivateKeyParameters.f();
            for (int i4 = 0; i4 < f.size(); i4++) {
                BigInteger modPow = bigInteger.modPow(naccacheSternPrivateKeyParameters.e().divide((BigInteger) f.elementAt(i4)), naccacheSternPrivateKeyParameters.d());
                Vector vector2 = this.c[i4];
                if (this.c[i4].size() != ((BigInteger) f.elementAt(i4)).intValue()) {
                    if (this.d) {
                        System.out.println("Prime is " + f.elementAt(i4) + ", lookup table has size " + vector2.size());
                    }
                    throw new InvalidCipherTextException("Error in lookup Array for " + ((BigInteger) f.elementAt(i4)).intValue() + ": Size mismatch. Expected ArrayList with length " + ((BigInteger) f.elementAt(i4)).intValue() + " but found ArrayList of length " + this.c[i4].size());
                }
                int indexOf = vector2.indexOf(modPow);
                if (indexOf == -1) {
                    if (this.d) {
                        System.out.println("Actual prime is " + f.elementAt(i4));
                        System.out.println("Decrypted value is " + modPow);
                        System.out.println("LookupList for " + f.elementAt(i4) + " with size " + this.c[i4].size() + " is: ");
                        while (i3 < this.c[i4].size()) {
                            System.out.println(this.c[i4].elementAt(i3));
                            i3++;
                        }
                    }
                    throw new InvalidCipherTextException("Lookup failed");
                }
                vector.addElement(BigInteger.valueOf((long) indexOf));
            }
            return a(vector, f).toByteArray();
        } else {
            throw new InvalidCipherTextException("BlockLength does not match modulus for Naccache-Stern cipher.\n");
        }
    }

    public byte[] a(BigInteger bigInteger) {
        byte[] toByteArray = this.b.d().toByteArray();
        Arrays.a(toByteArray, (byte) 0);
        Object toByteArray2 = this.b.b().modPow(bigInteger, this.b.d()).toByteArray();
        System.arraycopy(toByteArray2, 0, toByteArray, toByteArray.length - toByteArray2.length, toByteArray2.length);
        if (this.d) {
            System.out.println("Encrypted value is:  " + new BigInteger(toByteArray));
        }
        return toByteArray;
    }

    private static BigInteger a(Vector vector, Vector vector2) {
        int i = 0;
        BigInteger bigInteger = e;
        BigInteger bigInteger2 = f;
        for (int i2 = 0; i2 < vector2.size(); i2++) {
            bigInteger2 = bigInteger2.multiply((BigInteger) vector2.elementAt(i2));
        }
        BigInteger bigInteger3 = bigInteger;
        while (i < vector2.size()) {
            BigInteger bigInteger4 = (BigInteger) vector2.elementAt(i);
            bigInteger = bigInteger2.divide(bigInteger4);
            i++;
            bigInteger3 = bigInteger3.add(bigInteger.multiply(bigInteger.modInverse(bigInteger4)).multiply((BigInteger) vector.elementAt(i)));
        }
        return bigInteger3.mod(bigInteger2);
    }
}
