package org.spongycastle.util.io.pem;

import java.io.BufferedWriter;
import java.io.Writer;
import org.spongycastle.util.encoders.Base64;

public class PemWriter extends BufferedWriter {
    private final int a;
    private char[] b = new char[64];

    public PemWriter(Writer writer) {
        super(writer);
        String property = System.getProperty("line.separator");
        if (property != null) {
            this.a = property.length();
        } else {
            this.a = 2;
        }
    }

    public void a(PemObjectGenerator pemObjectGenerator) {
        PemObject d = pemObjectGenerator.d();
        a(d.a());
        if (!d.b().isEmpty()) {
            for (PemHeader pemHeader : d.b()) {
                write(pemHeader.a());
                write(": ");
                write(pemHeader.b());
                newLine();
            }
            newLine();
        }
        a(d.c());
        b(d.a());
    }

    private void a(byte[] bArr) {
        byte[] a = Base64.a(bArr);
        int i = 0;
        while (i < a.length) {
            int i2 = 0;
            while (i2 != this.b.length && i + i2 < a.length) {
                this.b[i2] = (char) a[i + i2];
                i2++;
            }
            write(this.b, 0, i2);
            newLine();
            i += this.b.length;
        }
    }

    private void a(String str) {
        write("-----BEGIN " + str + "-----");
        newLine();
    }

    private void b(String str) {
        write("-----END " + str + "-----");
        newLine();
    }
}
