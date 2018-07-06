package org.spongycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class Hex {
    private static final Encoder a = new HexEncoder();

    public static byte[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new EncoderException("exception encoding Hex string: " + e.getMessage(), e);
        }
    }

    public static byte[] a(String str) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a.a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e) {
            throw new DecoderException("exception decoding Hex string: " + e.getMessage(), e);
        }
    }
}
