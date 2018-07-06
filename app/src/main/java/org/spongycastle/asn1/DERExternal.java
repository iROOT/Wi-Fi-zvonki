package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;

public class DERExternal extends ASN1Primitive {
    private ASN1ObjectIdentifier a;
    private ASN1Integer b;
    private ASN1Primitive c;
    private int d;
    private ASN1Primitive e;

    public DERExternal(ASN1EncodableVector aSN1EncodableVector) {
        int i = 0;
        ASN1Primitive a = a(aSN1EncodableVector, 0);
        if (a instanceof ASN1ObjectIdentifier) {
            this.a = (ASN1ObjectIdentifier) a;
            i = 1;
            a = a(aSN1EncodableVector, 1);
        }
        if (a instanceof ASN1Integer) {
            this.b = (ASN1Integer) a;
            i++;
            a = a(aSN1EncodableVector, i);
        }
        if (!(a instanceof DERTaggedObject)) {
            this.c = a;
            i++;
            a = a(aSN1EncodableVector, i);
        }
        if (aSN1EncodableVector.a() != i + 1) {
            throw new IllegalArgumentException("input vector too large");
        } else if (a instanceof DERTaggedObject) {
            DERTaggedObject dERTaggedObject = (DERTaggedObject) a;
            a(dERTaggedObject.d());
            this.e = dERTaggedObject.l();
        } else {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        }
    }

    private ASN1Primitive a(ASN1EncodableVector aSN1EncodableVector, int i) {
        if (aSN1EncodableVector.a() > i) {
            return aSN1EncodableVector.a(i).a();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public int hashCode() {
        int i = 0;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        if (this.b != null) {
            i ^= this.b.hashCode();
        }
        if (this.c != null) {
            i ^= this.c.hashCode();
        }
        return i ^ this.e.hashCode();
    }

    boolean i() {
        return true;
    }

    int j() {
        return b().length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (this.a != null) {
            byteArrayOutputStream.write(this.a.a("DER"));
        }
        if (this.b != null) {
            byteArrayOutputStream.write(this.b.a("DER"));
        }
        if (this.c != null) {
            byteArrayOutputStream.write(this.c.a("DER"));
        }
        byteArrayOutputStream.write(new DERTaggedObject(true, this.d, this.e).a("DER"));
        aSN1OutputStream.a(32, 8, byteArrayOutputStream.toByteArray());
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERExternal)) {
            return false;
        }
        if (this == aSN1Primitive) {
            return true;
        }
        DERExternal dERExternal = (DERExternal) aSN1Primitive;
        if (this.a != null && (dERExternal.a == null || !dERExternal.a.equals(this.a))) {
            return false;
        }
        if (this.b != null && (dERExternal.b == null || !dERExternal.b.equals(this.b))) {
            return false;
        }
        if (this.c == null || (dERExternal.c != null && dERExternal.c.equals(this.c))) {
            return this.e.equals(dERExternal.e);
        }
        return false;
    }

    public ASN1Primitive d() {
        return this.c;
    }

    public ASN1ObjectIdentifier e() {
        return this.a;
    }

    public int f() {
        return this.d;
    }

    public ASN1Primitive k() {
        return this.e;
    }

    public ASN1Integer l() {
        return this.b;
    }

    private void a(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("invalid encoding value: " + i);
        }
        this.d = i;
    }
}
