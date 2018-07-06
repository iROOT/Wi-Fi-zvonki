package org.spongycastle.jce.provider;

import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.util.encoders.Base64;

public class PEMUtil {
    private final String a;
    private final String b;
    private final String c;
    private final String d;

    PEMUtil(String str) {
        this.a = "-----BEGIN " + str + "-----";
        this.b = "-----BEGIN X509 " + str + "-----";
        this.c = "-----END " + str + "-----";
        this.d = "-----END X509 " + str + "-----";
    }

    private String b(InputStream inputStream) {
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int read = inputStream.read();
            if (read == 13 || read == 10 || read < 0) {
                if (read < 0 || stringBuffer.length() != 0) {
                    if (read >= 0) {
                        return null;
                    }
                    return stringBuffer.toString();
                }
            } else if (read != 13) {
                stringBuffer.append((char) read);
            }
        }
        if (read >= 0) {
            return stringBuffer.toString();
        }
        return null;
    }

    ASN1Sequence a(InputStream inputStream) {
        String b;
        StringBuffer stringBuffer = new StringBuffer();
        do {
            b = b(inputStream);
            if (b == null || b.startsWith(this.a)) {
                while (true) {
                    b = b(inputStream);
                    stringBuffer.append(b);
                }
            }
        } while (!b.startsWith(this.b));
        while (true) {
            b = b(inputStream);
            if (b != null && !b.startsWith(this.c) && !b.startsWith(this.d)) {
                stringBuffer.append(b);
            }
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        ASN1Primitive d = new ASN1InputStream(Base64.a(stringBuffer.toString())).d();
        if (d instanceof ASN1Sequence) {
            return (ASN1Sequence) d;
        }
        throw new IOException("malformed PEM data encountered");
    }
}
