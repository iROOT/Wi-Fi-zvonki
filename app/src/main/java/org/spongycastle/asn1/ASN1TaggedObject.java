package org.spongycastle.asn1;

import java.io.IOException;

public abstract class ASN1TaggedObject extends ASN1Primitive implements ASN1TaggedObjectParser {
    int a;
    boolean b = false;
    boolean c = true;
    ASN1Encodable d = null;

    abstract void a(ASN1OutputStream aSN1OutputStream);

    public static ASN1TaggedObject a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return (ASN1TaggedObject) aSN1TaggedObject.l();
        }
        throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    public static ASN1TaggedObject a(Object obj) {
        if (obj == null || (obj instanceof ASN1TaggedObject)) {
            return (ASN1TaggedObject) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return a(ASN1Primitive.a((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct tagged object from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public ASN1TaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable instanceof ASN1Choice) {
            this.c = true;
        } else {
            this.c = z;
        }
        this.a = i;
        if (this.c) {
            this.d = aSN1Encodable;
            return;
        }
        if (aSN1Encodable.a() instanceof ASN1Set) {
            this.d = aSN1Encodable;
        } else {
            this.d = aSN1Encodable;
        }
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1TaggedObject)) {
            return false;
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
        if (this.a != aSN1TaggedObject.a || this.b != aSN1TaggedObject.b || this.c != aSN1TaggedObject.c) {
            return false;
        }
        if (this.d == null) {
            if (aSN1TaggedObject.d != null) {
                return false;
            }
        } else if (!this.d.a().equals(aSN1TaggedObject.d.a())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.a;
        if (this.d != null) {
            return i ^ this.d.hashCode();
        }
        return i;
    }

    public int d() {
        return this.a;
    }

    public boolean e() {
        return this.c;
    }

    public boolean k() {
        return this.b;
    }

    public ASN1Primitive l() {
        if (this.d != null) {
            return this.d.a();
        }
        return null;
    }

    public ASN1Primitive f() {
        return a();
    }

    ASN1Primitive g() {
        return new DERTaggedObject(this.c, this.a, this.d);
    }

    ASN1Primitive h() {
        return new DLTaggedObject(this.c, this.a, this.d);
    }

    public String toString() {
        return "[" + this.a + "]" + this.d;
    }
}
