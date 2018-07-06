package org.spongycastle.util.encoders;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Encoder implements Encoder {
    protected final byte[] a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    protected byte b = (byte) 61;
    protected final byte[] c = new byte[NotificationCompat.FLAG_HIGH_PRIORITY];

    protected void a() {
        int i = 0;
        for (int i2 = 0; i2 < this.c.length; i2++) {
            this.c[i2] = (byte) -1;
        }
        while (i < this.a.length) {
            this.c[this.a[i]] = (byte) i;
            i++;
        }
    }

    public Base64Encoder() {
        a();
    }

    public int a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3;
        int i4 = i2 % 3;
        int i5 = i2 - i4;
        for (i3 = i; i3 < i + i5; i3 += 3) {
            int i6 = bArr[i3] & 255;
            int i7 = bArr[i3 + 1] & 255;
            int i8 = bArr[i3 + 2] & 255;
            outputStream.write(this.a[(i6 >>> 2) & 63]);
            outputStream.write(this.a[((i6 << 4) | (i7 >>> 4)) & 63]);
            outputStream.write(this.a[((i7 << 2) | (i8 >>> 6)) & 63]);
            outputStream.write(this.a[i8 & 63]);
        }
        switch (i4) {
            case 1:
                i3 = bArr[i + i5] & 255;
                i6 = (i3 >>> 2) & 63;
                i3 = (i3 << 4) & 63;
                outputStream.write(this.a[i6]);
                outputStream.write(this.a[i3]);
                outputStream.write(this.b);
                outputStream.write(this.b);
                break;
            case 2:
                i3 = bArr[i + i5] & 255;
                i6 = bArr[(i + i5) + 1] & 255;
                i7 = (i3 >>> 2) & 63;
                i3 = ((i3 << 4) | (i6 >>> 4)) & 63;
                i6 = (i6 << 2) & 63;
                outputStream.write(this.a[i7]);
                outputStream.write(this.a[i3]);
                outputStream.write(this.a[i6]);
                outputStream.write(this.b);
                break;
        }
        i5 = (i5 / 3) * 4;
        if (i4 == 0) {
            i3 = 0;
        } else {
            i3 = 4;
        }
        return i3 + i5;
    }

    private boolean a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public int a(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && a(str.charAt(length - 1))) {
            length--;
        }
        int i = length - 4;
        int a = a(str, 0, i);
        int i2 = 0;
        while (a < i) {
            int i3 = a + 1;
            byte b = this.c[str.charAt(a)];
            int a2 = a(str, i3, i);
            int i4 = a2 + 1;
            byte b2 = this.c[str.charAt(a2)];
            i3 = a(str, i4, i);
            int i5 = i3 + 1;
            byte b3 = this.c[str.charAt(i3)];
            i4 = a(str, i5, i);
            int i6 = i4 + 1;
            byte b4 = this.c[str.charAt(i4)];
            if ((((b | b2) | b3) | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            a2 = i2 + 3;
            a = a(str, i6, i);
            i2 = a2;
        }
        return a(outputStream, str.charAt(length - 4), str.charAt(length - 3), str.charAt(length - 2), str.charAt(length - 1)) + i2;
    }

    private int a(OutputStream outputStream, char c, char c2, char c3, char c4) {
        byte b;
        byte b2;
        if (c3 == this.b) {
            b = this.c[c];
            b2 = this.c[c2];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            return 1;
        } else if (c4 == this.b) {
            b = this.c[c];
            b2 = this.c[c2];
            r2 = this.c[c3];
            if (((b | b2) | r2) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (r2 >> 2));
            return 2;
        } else {
            b = this.c[c];
            b2 = this.c[c2];
            r2 = this.c[c3];
            byte b3 = this.c[c4];
            if ((((b | b2) | r2) | b3) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (r2 >> 2));
            outputStream.write((r2 << 6) | b3);
            return 3;
        }
    }

    private int a(String str, int i, int i2) {
        while (i < i2 && a(str.charAt(i))) {
            i++;
        }
        return i;
    }
}
