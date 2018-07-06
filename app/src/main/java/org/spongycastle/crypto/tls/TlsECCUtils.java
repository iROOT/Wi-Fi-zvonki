package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.spongycastle.asn1.x9.ECNamedCurveTable;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECCurve.F2m;
import org.spongycastle.math.ec.ECCurve.Fp;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Integers;

public class TlsECCUtils {
    public static final Integer a = Integers.a(10);
    public static final Integer b = Integers.a(11);
    private static final String[] c = new String[]{"sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1", "brainpoolP256r1", "brainpoolP384r1", "brainpoolP512r1"};

    public static int[] a(Hashtable hashtable) {
        byte[] a = TlsUtils.a(hashtable, a);
        return a == null ? null : a(a);
    }

    public static short[] b(Hashtable hashtable) {
        byte[] a = TlsUtils.a(hashtable, b);
        return a == null ? null : b(a);
    }

    public static int[] a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        int b = TlsUtils.b(byteArrayInputStream);
        if (b < 2 || (b & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int[] d = TlsUtils.d(b / 2, byteArrayInputStream);
        TlsProtocol.d(byteArrayInputStream);
        return d;
    }

    public static short[] b(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        int a = TlsUtils.a(byteArrayInputStream);
        if (a < (short) 1) {
            throw new TlsFatalAlert((short) 50);
        }
        short[] c = TlsUtils.c(a, byteArrayInputStream);
        TlsProtocol.d(byteArrayInputStream);
        if (Arrays.a(c, (short) 0)) {
            return c;
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static String a(int i) {
        return d(i) ? c[i - 1] : null;
    }

    public static ECDomainParameters b(int i) {
        String a = a(i);
        if (a == null) {
            return null;
        }
        X9ECParameters a2 = ECNamedCurveTable.a(a);
        if (a2 != null) {
            return new ECDomainParameters(a2.d(), a2.e(), a2.f(), a2.g(), a2.h());
        }
        return null;
    }

    public static boolean c(int i) {
        switch (i) {
            case 49153:
            case 49154:
            case 49155:
            case 49156:
            case 49157:
            case 49158:
            case 49159:
            case 49160:
            case 49161:
            case 49162:
            case 49163:
            case 49164:
            case 49165:
            case 49166:
            case 49167:
            case 49168:
            case 49169:
            case 49170:
            case 49171:
            case 49172:
            case 49173:
            case 49174:
            case 49175:
            case 49176:
            case 49177:
            case 49187:
            case 49188:
            case 49189:
            case 49190:
            case 49191:
            case 49192:
            case 49193:
            case 49194:
            case 49195:
            case 49196:
            case 49197:
            case 49198:
            case 49199:
            case 49200:
            case 49201:
            case 49202:
            case 49203:
            case 49204:
            case 49205:
            case 49206:
            case 49207:
            case 49208:
            case 49209:
            case 49210:
            case 49211:
            case 65284:
            case 65285:
            case 65286:
            case 65287:
            case 65294:
            case 65295:
            case 65300:
            case 65301:
            case 65302:
            case 65303:
            case 65310:
            case 65311:
                return true;
            default:
                return false;
        }
    }

    public static boolean d(int i) {
        return i > 0 && i <= c.length;
    }

    public static boolean a(short[] sArr, short s) {
        if (sArr == null) {
            return false;
        }
        for (short s2 : sArr) {
            if (s2 == (short) 0) {
                return false;
            }
            if (s2 == s) {
                return true;
            }
        }
        return false;
    }

    public static byte[] a(short[] sArr, ECPoint eCPoint) {
        ECCurve a = eCPoint.a();
        boolean z = false;
        if (a instanceof F2m) {
            z = a(sArr, (short) 2);
        } else if (a instanceof Fp) {
            z = a(sArr, (short) 1);
        }
        return eCPoint.a(z);
    }

    public static BigInteger a(int i, byte[] bArr) {
        if (bArr.length == (i + 7) / 8) {
            return new BigInteger(1, bArr);
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static ECPoint a(short[] sArr, ECCurve eCCurve, byte[] bArr) {
        return eCCurve.a(bArr);
    }

    public static ECPublicKeyParameters a(short[] sArr, ECDomainParameters eCDomainParameters, byte[] bArr) {
        try {
            return new ECPublicKeyParameters(a(sArr, eCDomainParameters.a(), bArr), eCDomainParameters);
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    public static byte[] a(ECPublicKeyParameters eCPublicKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters) {
        ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
        eCDHBasicAgreement.a(eCPrivateKeyParameters);
        return BigIntegers.a(eCDHBasicAgreement.a(), eCDHBasicAgreement.b(eCPublicKeyParameters));
    }

    public static AsymmetricCipherKeyPair a(SecureRandom secureRandom, ECDomainParameters eCDomainParameters) {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.a(new ECKeyGenerationParameters(eCDomainParameters, secureRandom));
        return eCKeyPairGenerator.a();
    }

    public static ECPrivateKeyParameters a(SecureRandom secureRandom, short[] sArr, ECDomainParameters eCDomainParameters, OutputStream outputStream) {
        AsymmetricCipherKeyPair a = a(secureRandom, eCDomainParameters);
        a(sArr, ((ECPublicKeyParameters) a.a()).c(), outputStream);
        return (ECPrivateKeyParameters) a.b();
    }

    public static ECPublicKeyParameters a(ECPublicKeyParameters eCPublicKeyParameters) {
        return eCPublicKeyParameters;
    }

    public static int a(int i, InputStream inputStream) {
        BigInteger a = a(inputStream);
        if (a.bitLength() < 32) {
            int intValue = a.intValue();
            if (intValue > 0 && intValue < i) {
                return intValue;
            }
        }
        throw new TlsFatalAlert((short) 47);
    }

    public static BigInteger b(int i, InputStream inputStream) {
        return a(i, TlsUtils.e(inputStream));
    }

    public static BigInteger a(InputStream inputStream) {
        return new BigInteger(1, TlsUtils.e(inputStream));
    }

    public static ECDomainParameters a(int[] iArr, short[] sArr, InputStream inputStream) {
        try {
            switch (TlsUtils.a(inputStream)) {
                case (short) 1:
                    a(iArr, 65281);
                    BigInteger a = a(inputStream);
                    ECCurve fp = new Fp(a, b(a.bitLength(), inputStream), b(a.bitLength(), inputStream));
                    return new ECDomainParameters(fp, a(sArr, fp, TlsUtils.e(inputStream)), a(inputStream), a(inputStream));
                case (short) 2:
                    ECCurve f2m;
                    a(iArr, 65282);
                    int b = TlsUtils.b(inputStream);
                    switch (TlsUtils.a(inputStream)) {
                        case (short) 1:
                            f2m = new F2m(b, a(b, inputStream), b(b, inputStream), b(b, inputStream));
                            break;
                        case (short) 2:
                            f2m = new F2m(b, a(b, inputStream), a(b, inputStream), a(b, inputStream), b(b, inputStream), b(b, inputStream));
                            break;
                        default:
                            throw new TlsFatalAlert((short) 47);
                    }
                    return new ECDomainParameters(f2m, a(sArr, f2m, TlsUtils.e(inputStream)), a(inputStream), a(inputStream));
                case (short) 3:
                    int b2 = TlsUtils.b(inputStream);
                    if (NamedCurve.a(b2)) {
                        a(iArr, b2);
                        return b(b2);
                    }
                    throw new TlsFatalAlert((short) 47);
                default:
                    throw new TlsFatalAlert((short) 47);
            }
        } catch (RuntimeException e) {
            throw new TlsFatalAlert((short) 47);
        }
        throw new TlsFatalAlert((short) 47);
    }

    private static void a(int[] iArr, int i) {
        if (iArr != null && !Arrays.a(iArr, i)) {
            throw new TlsFatalAlert((short) 47);
        }
    }

    public static void a(int i, OutputStream outputStream) {
        a(BigInteger.valueOf((long) i), outputStream);
    }

    public static void a(ECFieldElement eCFieldElement, OutputStream outputStream) {
        TlsUtils.a(eCFieldElement.k(), outputStream);
    }

    public static void a(BigInteger bigInteger, OutputStream outputStream) {
        TlsUtils.a(BigIntegers.a(bigInteger), outputStream);
    }

    public static void a(short[] sArr, ECDomainParameters eCDomainParameters, OutputStream outputStream) {
        ECCurve a = eCDomainParameters.a();
        if (a instanceof Fp) {
            TlsUtils.a((short) 1, outputStream);
            a(((Fp) a).j(), outputStream);
        } else if (a instanceof F2m) {
            TlsUtils.a((short) 2, outputStream);
            F2m f2m = (F2m) a;
            int m = f2m.m();
            TlsUtils.b(m);
            TlsUtils.b(m, outputStream);
            if (f2m.n()) {
                TlsUtils.a((short) 1, outputStream);
                a(f2m.o(), outputStream);
            } else {
                TlsUtils.a((short) 2, outputStream);
                a(f2m.o(), outputStream);
                a(f2m.p(), outputStream);
                a(f2m.q(), outputStream);
            }
        } else {
            throw new IllegalArgumentException("'ecParameters' not a known curve type");
        }
        a(a.f(), outputStream);
        a(a.g(), outputStream);
        TlsUtils.a(a(sArr, eCDomainParameters.b()), outputStream);
        a(eCDomainParameters.c(), outputStream);
        a(eCDomainParameters.d(), outputStream);
    }

    public static void a(short[] sArr, ECPoint eCPoint, OutputStream outputStream) {
        TlsUtils.a(a(sArr, eCPoint), outputStream);
    }

    public static void b(int i, OutputStream outputStream) {
        if (NamedCurve.a(i)) {
            TlsUtils.a((short) 3, outputStream);
            TlsUtils.b(i);
            TlsUtils.b(i, outputStream);
            return;
        }
        throw new TlsFatalAlert((short) 80);
    }
}
