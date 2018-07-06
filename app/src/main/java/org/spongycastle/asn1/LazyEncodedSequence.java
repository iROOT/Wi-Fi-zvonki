package org.spongycastle.asn1;

import java.util.Enumeration;

class LazyEncodedSequence extends ASN1Sequence {
    private byte[] b;

    LazyEncodedSequence(byte[] bArr) {
        this.b = bArr;
    }

    private void k() {
        Enumeration lazyConstructionEnumeration = new LazyConstructionEnumeration(this.b);
        while (lazyConstructionEnumeration.hasMoreElements()) {
            this.a.addElement(lazyConstructionEnumeration.nextElement());
        }
        this.b = null;
    }

    public synchronized ASN1Encodable a(int i) {
        if (this.b != null) {
            k();
        }
        return super.a(i);
    }

    public synchronized Enumeration e() {
        Enumeration e;
        if (this.b == null) {
            e = super.e();
        } else {
            e = new LazyConstructionEnumeration(this.b);
        }
        return e;
    }

    public synchronized int f() {
        if (this.b != null) {
            k();
        }
        return super.f();
    }

    ASN1Primitive g() {
        if (this.b != null) {
            k();
        }
        return super.g();
    }

    ASN1Primitive h() {
        if (this.b != null) {
            k();
        }
        return super.h();
    }

    int j() {
        if (this.b != null) {
            return (StreamUtil.a(this.b.length) + 1) + this.b.length;
        }
        return super.h().j();
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        if (this.b != null) {
            aSN1OutputStream.a(48, this.b);
        } else {
            super.h().a(aSN1OutputStream);
        }
    }
}
