package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x500.X500Name;

public class CertificateRequest {
    protected short[] a;
    protected Vector b;
    protected Vector c;

    public CertificateRequest(short[] sArr, Vector vector, Vector vector2) {
        this.a = sArr;
        this.b = vector;
        this.c = vector2;
    }

    public short[] a() {
        return this.a;
    }

    public Vector b() {
        return this.b;
    }

    public void a(OutputStream outputStream) {
        int i = 0;
        if (this.a == null || this.a.length == 0) {
            TlsUtils.a(0, outputStream);
        } else {
            TlsUtils.b(this.a, outputStream);
        }
        if (this.b != null) {
            TlsUtils.a(this.b, false, outputStream);
        }
        if (this.c == null || this.c.isEmpty()) {
            TlsUtils.b(0, outputStream);
            return;
        }
        Vector vector = new Vector(this.c.size());
        int i2 = 0;
        for (int i3 = 0; i3 < this.c.size(); i3++) {
            Object a = ((X500Name) this.c.elementAt(i3)).a("DER");
            vector.addElement(a);
            i2 += a.length;
        }
        TlsUtils.b(i2);
        TlsUtils.b(i2, outputStream);
        while (i < vector.size()) {
            outputStream.write((byte[]) vector.elementAt(i));
            i++;
        }
    }

    public static CertificateRequest a(TlsContext tlsContext, InputStream inputStream) {
        short a = TlsUtils.a(inputStream);
        short[] sArr = new short[a];
        for (short s = (short) 0; s < a; s++) {
            sArr[s] = TlsUtils.a(inputStream);
        }
        Vector vector = null;
        if (TlsUtils.c(tlsContext)) {
            vector = TlsUtils.a(false, inputStream);
        }
        Vector vector2 = new Vector();
        InputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.f(inputStream));
        while (byteArrayInputStream.available() > 0) {
            vector2.addElement(X500Name.a(TlsUtils.c(TlsUtils.f(byteArrayInputStream))));
        }
        return new CertificateRequest(sArr, vector, vector2);
    }
}
