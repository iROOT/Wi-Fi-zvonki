package org.spongycastle.asn1;

import android.support.v4.app.NotificationCompat;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

class StreamUtil {
    private static final long a = Runtime.getRuntime().maxMemory();

    StreamUtil() {
    }

    static int a(InputStream inputStream) {
        if (inputStream instanceof LimitedInputStream) {
            return ((LimitedInputStream) inputStream).a();
        }
        if (inputStream instanceof ASN1InputStream) {
            return ((ASN1InputStream) inputStream).a();
        }
        if (inputStream instanceof ByteArrayInputStream) {
            return ((ByteArrayInputStream) inputStream).available();
        }
        if (inputStream instanceof FileInputStream) {
            try {
                long size;
                FileChannel channel = ((FileInputStream) inputStream).getChannel();
                if (channel != null) {
                    size = channel.size();
                } else {
                    size = 2147483647L;
                }
                if (size < 2147483647L) {
                    return (int) size;
                }
            } catch (IOException e) {
            }
        }
        if (a > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) a;
    }

    static int a(int i) {
        int i2 = 1;
        if (i > 127) {
            int i3 = 1;
            while (true) {
                i >>>= 8;
                if (i == 0) {
                    break;
                }
                i3++;
            }
            i3 = (i3 - 1) * 8;
            while (i3 >= 0) {
                i3 -= 8;
                i2++;
            }
        }
        return i2;
    }

    static int b(int i) {
        if (i < 31) {
            return 1;
        }
        if (i < NotificationCompat.FLAG_HIGH_PRIORITY) {
            return 2;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i & 127);
        do {
            i >>= 7;
            length--;
            bArr[length] = (byte) ((i & 127) | NotificationCompat.FLAG_HIGH_PRIORITY);
        } while (i > 127);
        return (bArr.length - length) + 1;
    }
}
