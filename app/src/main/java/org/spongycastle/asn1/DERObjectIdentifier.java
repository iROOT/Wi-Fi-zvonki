package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class DERObjectIdentifier extends ASN1Primitive {
    private static ASN1ObjectIdentifier[][] c = new ASN1ObjectIdentifier[256][];
    String a;
    private byte[] b;

    public static ASN1ObjectIdentifier a(Object obj) {
        if (obj == null || (obj instanceof ASN1ObjectIdentifier)) {
            return (ASN1ObjectIdentifier) obj;
        }
        if (obj instanceof DERObjectIdentifier) {
            return new ASN1ObjectIdentifier(((DERObjectIdentifier) obj).d());
        }
        if ((obj instanceof ASN1Encodable) && (((ASN1Encodable) obj).a() instanceof ASN1ObjectIdentifier)) {
            return (ASN1ObjectIdentifier) ((ASN1Encodable) obj).a();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            if (bArr[0] != (byte) 6) {
                return b((byte[]) obj);
            }
            try {
                return (ASN1ObjectIdentifier) ASN1Primitive.a(bArr);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1ObjectIdentifier a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        Object l = aSN1TaggedObject.l();
        if (z || (l instanceof DERObjectIdentifier)) {
            return a(l);
        }
        return b(ASN1OctetString.a(aSN1TaggedObject.l()).e());
    }

    DERObjectIdentifier(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        Object obj = 1;
        BigInteger bigInteger = null;
        long j = 0;
        for (int i = 0; i != bArr.length; i++) {
            int i2 = bArr[i] & 255;
            if (j <= 72057594037927808L) {
                j += (long) (i2 & 127);
                if ((i2 & NotificationCompat.FLAG_HIGH_PRIORITY) == 0) {
                    if (obj != null) {
                        if (j < 40) {
                            stringBuffer.append('0');
                        } else if (j < 80) {
                            stringBuffer.append('1');
                            j -= 40;
                        } else {
                            stringBuffer.append('2');
                            j -= 80;
                        }
                        obj = null;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j);
                    j = 0;
                } else {
                    j <<= 7;
                }
            } else {
                if (bigInteger == null) {
                    bigInteger = BigInteger.valueOf(j);
                }
                bigInteger = bigInteger.or(BigInteger.valueOf((long) (i2 & 127)));
                if ((i2 & NotificationCompat.FLAG_HIGH_PRIORITY) == 0) {
                    Object obj2;
                    Object obj3;
                    if (obj != null) {
                        stringBuffer.append('2');
                        obj = bigInteger.subtract(BigInteger.valueOf(80));
                        obj2 = null;
                    } else {
                        obj3 = obj;
                        BigInteger bigInteger2 = bigInteger;
                        obj2 = obj3;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(obj);
                    j = 0;
                    obj3 = obj2;
                    bigInteger = null;
                    obj = obj3;
                } else {
                    bigInteger = bigInteger.shiftLeft(7);
                }
            }
        }
        this.a = stringBuffer.toString();
        this.b = Arrays.b(bArr);
    }

    public DERObjectIdentifier(String str) {
        if (str == null) {
            throw new IllegalArgumentException("'identifier' cannot be null");
        } else if (b(str)) {
            this.a = str;
        } else {
            throw new IllegalArgumentException("string " + str + " not an OID");
        }
    }

    DERObjectIdentifier(DERObjectIdentifier dERObjectIdentifier, String str) {
        if (a(str, 0)) {
            this.a = dERObjectIdentifier.d() + "." + str;
            return;
        }
        throw new IllegalArgumentException("string " + str + " not a valid OID branch");
    }

    public String d() {
        return this.a;
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, long j) {
        byte[] bArr = new byte[9];
        int i = 8;
        bArr[8] = (byte) (((int) j) & 127);
        while (j >= 128) {
            j >>= 7;
            i--;
            bArr[i] = (byte) ((((int) j) & 127) | NotificationCompat.FLAG_HIGH_PRIORITY);
        }
        byteArrayOutputStream.write(bArr, i, 9 - i);
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream, BigInteger bigInteger) {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            byteArrayOutputStream.write(0);
            return;
        }
        int i;
        byte[] bArr = new byte[bitLength];
        for (i = bitLength - 1; i >= 0; i--) {
            bArr[i] = (byte) ((bigInteger.intValue() & 127) | NotificationCompat.FLAG_HIGH_PRIORITY);
            bigInteger = bigInteger.shiftRight(7);
        }
        i = bitLength - 1;
        bArr[i] = (byte) (bArr[i] & 127);
        byteArrayOutputStream.write(bArr, 0, bArr.length);
    }

    private void a(ByteArrayOutputStream byteArrayOutputStream) {
        OIDTokenizer oIDTokenizer = new OIDTokenizer(this.a);
        int parseInt = Integer.parseInt(oIDTokenizer.b()) * 40;
        String b = oIDTokenizer.b();
        if (b.length() <= 18) {
            a(byteArrayOutputStream, Long.parseLong(b) + ((long) parseInt));
        } else {
            a(byteArrayOutputStream, new BigInteger(b).add(BigInteger.valueOf((long) parseInt)));
        }
        while (oIDTokenizer.a()) {
            String b2 = oIDTokenizer.b();
            if (b2.length() <= 18) {
                a(byteArrayOutputStream, Long.parseLong(b2));
            } else {
                a(byteArrayOutputStream, new BigInteger(b2));
            }
        }
    }

    protected synchronized byte[] e() {
        if (this.b == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            a(byteArrayOutputStream);
            this.b = byteArrayOutputStream.toByteArray();
        }
        return this.b;
    }

    boolean i() {
        return false;
    }

    int j() {
        int length = e().length;
        return length + (StreamUtil.a(length) + 1);
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        byte[] e = e();
        aSN1OutputStream.b(6);
        aSN1OutputStream.a(e.length);
        aSN1OutputStream.a(e);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof DERObjectIdentifier) {
            return this.a.equals(((DERObjectIdentifier) aSN1Primitive).a);
        }
        return false;
    }

    public String toString() {
        return d();
    }

    private static boolean a(String str, int i) {
        int length = str.length();
        boolean z = false;
        while (true) {
            length--;
            if (length < i) {
                return z;
            }
            char charAt = str.charAt(length);
            if ('0' <= charAt && charAt <= '9') {
                z = true;
            } else if (charAt != '.' || !z) {
                return false;
            } else {
                z = false;
            }
        }
    }

    private static boolean b(String str) {
        if (str.length() < 3 || str.charAt(1) != '.') {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt < '0' || charAt > '2') {
            return false;
        }
        return a(str, 2);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static org.spongycastle.asn1.ASN1ObjectIdentifier b(byte[] r5) {
        /*
        r0 = r5.length;
        r1 = 3;
        if (r0 >= r1) goto L_0x000a;
    L_0x0004:
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier;
        r0.<init>(r5);
    L_0x0009:
        return r0;
    L_0x000a:
        r0 = r5.length;
        r0 = r0 + -2;
        r0 = r5[r0];
        r2 = r0 & 255;
        r0 = r5.length;
        r0 = r0 + -1;
        r0 = r5[r0];
        r3 = r0 & 127;
        r4 = c;
        monitor-enter(r4);
        r0 = c;	 Catch:{ all -> 0x0037 }
        r0 = r0[r2];	 Catch:{ all -> 0x0037 }
        if (r0 != 0) goto L_0x0097;
    L_0x0021:
        r1 = c;	 Catch:{ all -> 0x0037 }
        r0 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier[r0];	 Catch:{ all -> 0x0037 }
        r1[r2] = r0;	 Catch:{ all -> 0x0037 }
        r1 = r0;
    L_0x002a:
        r0 = r1[r3];	 Catch:{ all -> 0x0037 }
        if (r0 != 0) goto L_0x003a;
    L_0x002e:
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier;	 Catch:{ all -> 0x0037 }
        r0.<init>(r5);	 Catch:{ all -> 0x0037 }
        r1[r3] = r0;	 Catch:{ all -> 0x0037 }
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        goto L_0x0009;
    L_0x0037:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        throw r0;
    L_0x003a:
        r1 = r0.e();	 Catch:{ all -> 0x0037 }
        r1 = org.spongycastle.util.Arrays.a(r5, r1);	 Catch:{ all -> 0x0037 }
        if (r1 == 0) goto L_0x0046;
    L_0x0044:
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        goto L_0x0009;
    L_0x0046:
        r0 = r2 + 1;
        r1 = r0 & 255;
        r0 = c;	 Catch:{ all -> 0x0037 }
        r0 = r0[r1];	 Catch:{ all -> 0x0037 }
        if (r0 != 0) goto L_0x0095;
    L_0x0050:
        r2 = c;	 Catch:{ all -> 0x0037 }
        r0 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier[r0];	 Catch:{ all -> 0x0037 }
        r2[r1] = r0;	 Catch:{ all -> 0x0037 }
        r1 = r0;
    L_0x0059:
        r0 = r1[r3];	 Catch:{ all -> 0x0037 }
        if (r0 != 0) goto L_0x0066;
    L_0x005d:
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier;	 Catch:{ all -> 0x0037 }
        r0.<init>(r5);	 Catch:{ all -> 0x0037 }
        r1[r3] = r0;	 Catch:{ all -> 0x0037 }
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        goto L_0x0009;
    L_0x0066:
        r2 = r0.e();	 Catch:{ all -> 0x0037 }
        r2 = org.spongycastle.util.Arrays.a(r5, r2);	 Catch:{ all -> 0x0037 }
        if (r2 == 0) goto L_0x0072;
    L_0x0070:
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        goto L_0x0009;
    L_0x0072:
        r0 = r3 + 1;
        r2 = r0 & 127;
        r0 = r1[r2];	 Catch:{ all -> 0x0037 }
        if (r0 != 0) goto L_0x0083;
    L_0x007a:
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier;	 Catch:{ all -> 0x0037 }
        r0.<init>(r5);	 Catch:{ all -> 0x0037 }
        r1[r2] = r0;	 Catch:{ all -> 0x0037 }
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        goto L_0x0009;
    L_0x0083:
        monitor-exit(r4);	 Catch:{ all -> 0x0037 }
        r1 = r0.e();
        r1 = org.spongycastle.util.Arrays.a(r5, r1);
        if (r1 != 0) goto L_0x0009;
    L_0x008e:
        r0 = new org.spongycastle.asn1.ASN1ObjectIdentifier;
        r0.<init>(r5);
        goto L_0x0009;
    L_0x0095:
        r1 = r0;
        goto L_0x0059;
    L_0x0097:
        r1 = r0;
        goto L_0x002a;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.asn1.DERObjectIdentifier.b(byte[]):org.spongycastle.asn1.ASN1ObjectIdentifier");
    }
}
