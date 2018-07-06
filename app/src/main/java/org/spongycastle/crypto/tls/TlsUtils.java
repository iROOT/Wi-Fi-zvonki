package org.spongycastle.crypto.tls;

import android.support.v4.app.NotificationCompat;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.KeyUsage;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.MD5Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.digests.SHA224Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA384Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPublicKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;
import org.spongycastle.util.io.Streams;

public class TlsUtils {
    public static byte[] a = new byte[0];
    public static final Integer b = Integers.a(13);
    static final byte[] c = new byte[]{(byte) 67, (byte) 76, (byte) 78, (byte) 84};
    static final byte[] d = new byte[]{(byte) 83, (byte) 82, (byte) 86, (byte) 82};
    static final byte[][] e = d();

    public static void a(int i) {
        if (!d(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void b(int i) {
        if (!e(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static void c(int i) {
        if (!f(i)) {
            throw new TlsFatalAlert((short) 80);
        }
    }

    public static boolean a(short s) {
        return (s & 255) == s;
    }

    public static boolean d(int i) {
        return (i & 255) == i;
    }

    public static boolean e(int i) {
        return (65535 & i) == i;
    }

    public static boolean f(int i) {
        return (16777215 & i) == i;
    }

    public static boolean a(TlsContext tlsContext) {
        return tlsContext.d().d();
    }

    public static boolean b(TlsContext tlsContext) {
        return ProtocolVersion.c.a(tlsContext.d().e());
    }

    public static boolean c(TlsContext tlsContext) {
        return ProtocolVersion.d.a(tlsContext.d().e());
    }

    public static void a(short s, OutputStream outputStream) {
        outputStream.write(s);
    }

    public static void a(int i, OutputStream outputStream) {
        outputStream.write(i);
    }

    public static void a(short s, byte[] bArr, int i) {
        bArr[i] = (byte) s;
    }

    public static void b(int i, OutputStream outputStream) {
        outputStream.write(i >> 8);
        outputStream.write(i);
    }

    public static void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 8);
        bArr[i2 + 1] = (byte) i;
    }

    public static void c(int i, OutputStream outputStream) {
        outputStream.write(i >> 16);
        outputStream.write(i >> 8);
        outputStream.write(i);
    }

    public static void b(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) i;
    }

    public static void a(long j, OutputStream outputStream) {
        outputStream.write((int) (j >> 24));
        outputStream.write((int) (j >> 16));
        outputStream.write((int) (j >> 8));
        outputStream.write((int) j);
    }

    public static void a(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((int) (j >> 56));
        bArr[i + 1] = (byte) ((int) (j >> 48));
        bArr[i + 2] = (byte) ((int) (j >> 40));
        bArr[i + 3] = (byte) ((int) (j >> 32));
        bArr[i + 4] = (byte) ((int) (j >> 24));
        bArr[i + 5] = (byte) ((int) (j >> 16));
        bArr[i + 6] = (byte) ((int) (j >> 8));
        bArr[i + 7] = (byte) ((int) j);
    }

    public static void a(byte[] bArr, OutputStream outputStream) {
        a(bArr.length);
        a(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void b(byte[] bArr, OutputStream outputStream) {
        b(bArr.length);
        b(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void c(byte[] bArr, OutputStream outputStream) {
        c(bArr.length);
        c(bArr.length, outputStream);
        outputStream.write(bArr);
    }

    public static void a(short[] sArr, OutputStream outputStream) {
        for (short a : sArr) {
            a(a, outputStream);
        }
    }

    public static void b(short[] sArr, OutputStream outputStream) {
        a(sArr.length);
        a(sArr.length, outputStream);
        a(sArr, outputStream);
    }

    public static byte[] a(byte[] bArr) {
        a(bArr.length);
        return Arrays.b(bArr, (byte) bArr.length);
    }

    public static short a(InputStream inputStream) {
        int read = inputStream.read();
        if (read >= 0) {
            return (short) read;
        }
        throw new EOFException();
    }

    public static short a(byte[] bArr, int i) {
        return (short) bArr[i];
    }

    public static int b(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return (read << 8) | read2;
        }
        throw new EOFException();
    }

    public static int b(byte[] bArr, int i) {
        return ((bArr[i] & 255) << 8) | (bArr[i + 1] & 255);
    }

    public static int c(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        if (read3 >= 0) {
            return ((read << 16) | (read2 << 8)) | read3;
        }
        throw new EOFException();
    }

    public static long d(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        int read3 = inputStream.read();
        int read4 = inputStream.read();
        if (read4 < 0) {
            throw new EOFException();
        }
        return (((((long) read2) << 16) | (((long) read) << 24)) | (((long) read3) << 8)) | ((long) read4);
    }

    public static byte[] a(int i, InputStream inputStream) {
        if (i < 1) {
            return a;
        }
        byte[] bArr = new byte[i];
        int a = Streams.a(inputStream, bArr);
        if (a == 0) {
            return null;
        }
        if (a == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] b(int i, InputStream inputStream) {
        if (i < 1) {
            return a;
        }
        byte[] bArr = new byte[i];
        if (i == Streams.a(inputStream, bArr)) {
            return bArr;
        }
        throw new EOFException();
    }

    public static byte[] e(InputStream inputStream) {
        return b(a(inputStream), inputStream);
    }

    public static byte[] f(InputStream inputStream) {
        return b(b(inputStream), inputStream);
    }

    public static byte[] g(InputStream inputStream) {
        return b(c(inputStream), inputStream);
    }

    public static short[] c(int i, InputStream inputStream) {
        short[] sArr = new short[i];
        for (int i2 = 0; i2 < i; i2++) {
            sArr[i2] = a(inputStream);
        }
        return sArr;
    }

    public static int[] d(int i, InputStream inputStream) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = b(inputStream);
        }
        return iArr;
    }

    public static ProtocolVersion c(byte[] bArr, int i) {
        return ProtocolVersion.a(bArr[i] & 255, bArr[i + 1] & 255);
    }

    public static ProtocolVersion h(InputStream inputStream) {
        int read = inputStream.read();
        int read2 = inputStream.read();
        if (read2 >= 0) {
            return ProtocolVersion.a(read, read2);
        }
        throw new EOFException();
    }

    public static int d(byte[] bArr, int i) {
        return (bArr[i] << 8) | bArr[i + 1];
    }

    public static ASN1Primitive b(byte[] bArr) {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ASN1Primitive d = aSN1InputStream.d();
        if (d == null) {
            throw new TlsFatalAlert((short) 50);
        } else if (aSN1InputStream.d() == null) {
            return d;
        } else {
            throw new TlsFatalAlert((short) 50);
        }
    }

    public static ASN1Primitive c(byte[] bArr) {
        ASN1Primitive b = b(bArr);
        if (Arrays.a(b.a("DER"), bArr)) {
            return b;
        }
        throw new TlsFatalAlert((short) 50);
    }

    public static void a(ProtocolVersion protocolVersion, OutputStream outputStream) {
        outputStream.write(protocolVersion.a());
        outputStream.write(protocolVersion.b());
    }

    public static void a(ProtocolVersion protocolVersion, byte[] bArr, int i) {
        bArr[i] = (byte) protocolVersion.a();
        bArr[i + 1] = (byte) protocolVersion.b();
    }

    public static Vector a() {
        return a(new SignatureAndHashAlgorithm((short) 2, (short) 2));
    }

    public static Vector b() {
        return a(new SignatureAndHashAlgorithm((short) 2, (short) 3));
    }

    public static Vector c() {
        return a(new SignatureAndHashAlgorithm((short) 2, (short) 1));
    }

    public static byte[] a(Hashtable hashtable, Integer num) {
        return hashtable == null ? null : (byte[]) hashtable.get(num);
    }

    public static boolean a(Hashtable hashtable, Integer num, short s) {
        byte[] a = a(hashtable, num);
        if (a == null) {
            return false;
        }
        if (a.length == 0) {
            return true;
        }
        throw new TlsFatalAlert(s);
    }

    public static boolean a(ProtocolVersion protocolVersion) {
        return ProtocolVersion.d.a(protocolVersion.e());
    }

    public static void a(Vector vector, boolean z, OutputStream outputStream) {
        if (vector == null || vector.size() < 1 || vector.size() >= 32768) {
            throw new IllegalArgumentException("'supportedSignatureAlgorithms' must have length from 1 to (2^15 - 1)");
        }
        int size = vector.size() * 2;
        b(size);
        b(size, outputStream);
        int i = 0;
        while (i < vector.size()) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = (SignatureAndHashAlgorithm) vector.elementAt(i);
            if (z || signatureAndHashAlgorithm.b() != (short) 0) {
                signatureAndHashAlgorithm.a(outputStream);
                i++;
            } else {
                throw new IllegalArgumentException("SignatureAlgorithm.anonymous MUST NOT appear in the signature_algorithms extension");
            }
        }
    }

    public static Vector a(boolean z, InputStream inputStream) {
        int b = b(inputStream);
        if (b < 2 || (b & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        int i = b / 2;
        Vector vector = new Vector(i);
        b = 0;
        while (b < i) {
            SignatureAndHashAlgorithm a = SignatureAndHashAlgorithm.a(inputStream);
            if (z || a.b() != (short) 0) {
                vector.addElement(a);
                b++;
            } else {
                throw new TlsFatalAlert((short) 47);
            }
        }
        return vector;
    }

    public static byte[] a(TlsContext tlsContext, byte[] bArr, String str, byte[] bArr2, int i) {
        if (tlsContext.d().d()) {
            throw new IllegalStateException("No PRF available for SSLv3 session");
        }
        byte[] d = Strings.d(str);
        byte[] a = a(d, bArr2);
        int b = tlsContext.b().b();
        if (b == 0) {
            return a(bArr, d, a, i);
        }
        d = new byte[i];
        a(g(b), bArr, a, d);
        return d;
    }

    static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        int i2 = 0;
        int length = (bArr.length + 1) / 2;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr, bArr.length - length, bArr5, 0, length);
        byte[] bArr6 = new byte[i];
        byte[] bArr7 = new byte[i];
        a(new MD5Digest(), bArr4, bArr3, bArr6);
        a(new SHA1Digest(), bArr5, bArr3, bArr7);
        while (i2 < i) {
            bArr6[i2] = (byte) (bArr6[i2] ^ bArr7[i2]);
            i2++;
        }
        return bArr6;
    }

    static byte[] a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    static void a(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMac hMac = new HMac(digest);
        CipherParameters keyParameter = new KeyParameter(bArr);
        int b = digest.b();
        int length = ((bArr3.length + b) - 1) / b;
        byte[] bArr4 = new byte[hMac.b()];
        Object obj = new byte[hMac.b()];
        int i = 0;
        byte[] bArr5 = bArr2;
        while (i < length) {
            hMac.a(keyParameter);
            hMac.a(bArr5, 0, bArr5.length);
            hMac.a(bArr4, 0);
            hMac.a(keyParameter);
            hMac.a(bArr4, 0, bArr4.length);
            hMac.a(bArr2, 0, bArr2.length);
            hMac.a(obj, 0);
            System.arraycopy(obj, 0, bArr3, b * i, Math.min(b, bArr3.length - (b * i)));
            i++;
            bArr5 = bArr4;
        }
    }

    static void a(Certificate certificate, int i) {
        Extensions n = certificate.d().n();
        if (n != null) {
            KeyUsage a = KeyUsage.a(n);
            if (a != null && ((a.d()[0] & 255) & i) != i) {
                throw new TlsFatalAlert((short) 46);
            }
        }
    }

    static byte[] a(TlsContext tlsContext, int i) {
        SecurityParameters b = tlsContext.b();
        byte[] d = b.d();
        byte[] a = a(b.f(), b.e());
        if (a(tlsContext)) {
            return a(d, a, i);
        }
        return a(tlsContext, d, "key expansion", a, i);
    }

    static byte[] a(byte[] bArr, byte[] bArr2, int i) {
        Digest mD5Digest = new MD5Digest();
        Digest sHA1Digest = new SHA1Digest();
        int b = mD5Digest.b();
        byte[] bArr3 = new byte[sHA1Digest.b()];
        Object obj = new byte[(i + b)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            byte[] bArr4 = e[i3];
            sHA1Digest.a(bArr4, 0, bArr4.length);
            sHA1Digest.a(bArr, 0, bArr.length);
            sHA1Digest.a(bArr2, 0, bArr2.length);
            sHA1Digest.a(bArr3, 0);
            mD5Digest.a(bArr, 0, bArr.length);
            mD5Digest.a(bArr3, 0, bArr3.length);
            mD5Digest.a(obj, i2);
            i2 += b;
            i3++;
        }
        Object obj2 = new byte[i];
        System.arraycopy(obj, 0, obj2, 0, i);
        return obj2;
    }

    static byte[] a(TlsContext tlsContext, byte[] bArr) {
        SecurityParameters b = tlsContext.b();
        byte[] a = a(b.e(), b.f());
        if (a(tlsContext)) {
            return b(bArr, a);
        }
        return a(tlsContext, bArr, "master secret", a, 48);
    }

    static byte[] b(byte[] bArr, byte[] bArr2) {
        Digest mD5Digest = new MD5Digest();
        Digest sHA1Digest = new SHA1Digest();
        int b = mD5Digest.b();
        byte[] bArr3 = new byte[sHA1Digest.b()];
        byte[] bArr4 = new byte[(b * 3)];
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            byte[] bArr5 = e[i2];
            sHA1Digest.a(bArr5, 0, bArr5.length);
            sHA1Digest.a(bArr, 0, bArr.length);
            sHA1Digest.a(bArr2, 0, bArr2.length);
            sHA1Digest.a(bArr3, 0);
            mD5Digest.a(bArr, 0, bArr.length);
            mD5Digest.a(bArr3, 0, bArr3.length);
            mD5Digest.a(bArr4, i);
            i += b;
        }
        return bArr4;
    }

    static byte[] a(TlsContext tlsContext, String str, byte[] bArr) {
        if (a(tlsContext)) {
            return bArr;
        }
        SecurityParameters b = tlsContext.b();
        return a(tlsContext, b.d(), str, bArr, b.c());
    }

    public static final Digest b(short s) {
        switch (s) {
            case (short) 1:
                return new MD5Digest();
            case (short) 2:
                return new SHA1Digest();
            case (short) 3:
                return new SHA224Digest();
            case (short) 4:
                return new SHA256Digest();
            case (short) 5:
                return new SHA384Digest();
            case (short) 6:
                return new SHA512Digest();
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static final Digest a(short s, Digest digest) {
        switch (s) {
            case (short) 1:
                return new MD5Digest((MD5Digest) digest);
            case (short) 2:
                return new SHA1Digest((SHA1Digest) digest);
            case (short) 3:
                return new SHA224Digest((SHA224Digest) digest);
            case (short) 4:
                return new SHA256Digest((SHA256Digest) digest);
            case (short) 5:
                return new SHA384Digest((SHA384Digest) digest);
            case (short) 6:
                return new SHA512Digest((SHA512Digest) digest);
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    public static final Digest g(int i) {
        switch (i) {
            case 0:
                return new CombinedHash();
            default:
                return b(h(i));
        }
    }

    public static final short h(int i) {
        switch (i) {
            case 0:
                throw new IllegalArgumentException("legacy PRF not a valid algorithm");
            case 1:
                return (short) 4;
            case 2:
                return (short) 5;
            default:
                throw new IllegalArgumentException("unknown PRFAlgorithm");
        }
    }

    public static ASN1ObjectIdentifier c(short s) {
        switch (s) {
            case (short) 1:
                return PKCSObjectIdentifiers.H;
            case (short) 2:
                return X509ObjectIdentifiers.i;
            case (short) 3:
                return NISTObjectIdentifiers.f;
            case (short) 4:
                return NISTObjectIdentifiers.c;
            case (short) 5:
                return NISTObjectIdentifiers.d;
            case (short) 6:
                return NISTObjectIdentifiers.e;
            default:
                throw new IllegalArgumentException("unknown HashAlgorithm");
        }
    }

    static short a(Certificate certificate, Certificate certificate2) {
        if (certificate.c()) {
            return (short) -1;
        }
        Certificate a = certificate.a(0);
        try {
            AsymmetricKeyParameter a2 = PublicKeyFactory.a(a.k());
            if (a2.a()) {
                throw new TlsFatalAlert((short) 80);
            } else if (a2 instanceof RSAKeyParameters) {
                a(a, (int) NotificationCompat.FLAG_HIGH_PRIORITY);
                return (short) 1;
            } else if (a2 instanceof DSAPublicKeyParameters) {
                a(a, (int) NotificationCompat.FLAG_HIGH_PRIORITY);
                return (short) 2;
            } else {
                if (a2 instanceof ECPublicKeyParameters) {
                    a(a, (int) NotificationCompat.FLAG_HIGH_PRIORITY);
                    return (short) 64;
                }
                throw new TlsFatalAlert((short) 43);
            }
        } catch (Exception e) {
        }
    }

    static void a(TlsHandshakeHash tlsHandshakeHash, Vector vector) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); i++) {
                tlsHandshakeHash.a(((SignatureAndHashAlgorithm) vector.elementAt(i)).a());
            }
        }
    }

    public static boolean d(short s) {
        switch (s) {
            case (short) 1:
            case (short) 2:
            case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
                return true;
            default:
                return false;
        }
    }

    public static TlsSigner e(short s) {
        switch (s) {
            case (short) 1:
                return new TlsRSASigner();
            case (short) 2:
                return new TlsDSSSigner();
            case NotificationCompat.FLAG_FOREGROUND_SERVICE /*64*/:
                return new TlsECDSASigner();
            default:
                throw new IllegalArgumentException("'clientCertificateType' is not a type with signing capability");
        }
    }

    private static byte[][] d() {
        byte[][] bArr = new byte[10][];
        for (int i = 0; i < 10; i++) {
            byte[] bArr2 = new byte[(i + 1)];
            Arrays.a(bArr2, (byte) (i + 65));
            bArr[i] = bArr2;
        }
        return bArr;
    }

    private static Vector a(Object obj) {
        Vector vector = new Vector(1);
        vector.addElement(obj);
        return vector;
    }
}
