package org.spongycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class Base64 {
    private static final Encoder a = new Base64Encoder();

    public static byte[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(((i2 + 2) / 3) * 4);
        try {
            a.a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new EncoderException("exception encoding base64 string: " + e.getMessage(), e);
        }
    }

    public static byte[] a(String str) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream((str.length() / 4) * 3);
        try {
            a.a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new DecoderException("unable to decode base64 string: " + e.getMessage(), e);
        }
    }
}
