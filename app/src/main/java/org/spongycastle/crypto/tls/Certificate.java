package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

public class Certificate {
    public static final Certificate a = new Certificate(new org.spongycastle.asn1.x509.Certificate[0]);
    protected org.spongycastle.asn1.x509.Certificate[] b;

    public Certificate(org.spongycastle.asn1.x509.Certificate[] certificateArr) {
        if (certificateArr == null) {
            throw new IllegalArgumentException("'certificateList' cannot be null");
        }
        this.b = certificateArr;
    }

    public org.spongycastle.asn1.x509.Certificate[] a() {
        return d();
    }

    public org.spongycastle.asn1.x509.Certificate a(int i) {
        return this.b[i];
    }

    public int b() {
        return this.b.length;
    }

    public boolean c() {
        return this.b.length == 0;
    }

    public void a(OutputStream outputStream) {
        int i = 0;
        Vector vector = new Vector(this.b.length);
        int i2 = 0;
        for (org.spongycastle.asn1.x509.Certificate a : this.b) {
            Object a2 = a.a("DER");
            vector.addElement(a2);
            i2 += a2.length + 3;
        }
        TlsUtils.c(i2);
        TlsUtils.c(i2, outputStream);
        while (i < vector.size()) {
            TlsUtils.c((byte[]) vector.elementAt(i), outputStream);
            i++;
        }
    }

    public static Certificate a(InputStream inputStream) {
        int c = TlsUtils.c(inputStream);
        if (c == 0) {
            return a;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(TlsUtils.b(c, inputStream));
        Vector vector = new Vector();
        while (byteArrayInputStream.available() > 0) {
            vector.addElement(org.spongycastle.asn1.x509.Certificate.a(TlsUtils.c(TlsUtils.g(byteArrayInputStream))));
        }
        org.spongycastle.asn1.x509.Certificate[] certificateArr = new org.spongycastle.asn1.x509.Certificate[vector.size()];
        for (int i = 0; i < vector.size(); i++) {
            certificateArr[i] = (org.spongycastle.asn1.x509.Certificate) vector.elementAt(i);
        }
        return new Certificate(certificateArr);
    }

    protected org.spongycastle.asn1.x509.Certificate[] d() {
        Object obj = new org.spongycastle.asn1.x509.Certificate[this.b.length];
        System.arraycopy(this.b, 0, obj, 0, obj.length);
        return obj;
    }
}
