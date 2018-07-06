package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

public abstract class ASN1Set extends ASN1Primitive {
    private Vector a = new Vector();
    private boolean b = false;

    /* renamed from: org.spongycastle.asn1.ASN1Set$1 */
    class AnonymousClass1 implements ASN1SetParser {
        final /* synthetic */ ASN1Set a;

        public ASN1Primitive f() {
            return this.a;
        }

        public ASN1Primitive a() {
            return this.a;
        }
    }

    abstract void a(ASN1OutputStream aSN1OutputStream);

    public static ASN1Set a(Object obj) {
        if (obj == null || (obj instanceof ASN1Set)) {
            return (ASN1Set) obj;
        }
        if (obj instanceof ASN1SetParser) {
            return a(((ASN1SetParser) obj).a());
        }
        if (obj instanceof byte[]) {
            try {
                return a(ASN1Primitive.a((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct set from byte[]: " + e.getMessage());
            }
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive a = ((ASN1Encodable) obj).a();
            if (a instanceof ASN1Set) {
                return (ASN1Set) a;
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Set a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.e()) {
                return (ASN1Set) aSN1TaggedObject.l();
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (aSN1TaggedObject.e()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSet(aSN1TaggedObject.l());
            }
            return new DLSet(aSN1TaggedObject.l());
        } else if (aSN1TaggedObject.l() instanceof ASN1Set) {
            return (ASN1Set) aSN1TaggedObject.l();
        } else {
            if (aSN1TaggedObject.l() instanceof ASN1Sequence) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1TaggedObject.l();
                if (aSN1TaggedObject instanceof BERTaggedObject) {
                    return new BERSet(aSN1Sequence.d());
                }
                return new DLSet(aSN1Sequence.d());
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
    }

    protected ASN1Set() {
    }

    protected ASN1Set(ASN1Encodable aSN1Encodable) {
        this.a.addElement(aSN1Encodable);
    }

    protected ASN1Set(ASN1EncodableVector aSN1EncodableVector, boolean z) {
        int i = 0;
        while (i != aSN1EncodableVector.a()) {
            this.a.addElement(aSN1EncodableVector.a(i));
            i++;
        }
        if (z) {
            f();
        }
    }

    protected ASN1Set(ASN1Encodable[] aSN1EncodableArr, boolean z) {
        int i = 0;
        while (i != aSN1EncodableArr.length) {
            this.a.addElement(aSN1EncodableArr[i]);
            i++;
        }
        if (z) {
            f();
        }
    }

    public Enumeration d() {
        return this.a.elements();
    }

    public ASN1Encodable a(int i) {
        return (ASN1Encodable) this.a.elementAt(i);
    }

    public int e() {
        return this.a.size();
    }

    public int hashCode() {
        Enumeration d = d();
        int e = e();
        while (d.hasMoreElements()) {
            e = (e * 17) ^ a(d).hashCode();
        }
        return e;
    }

    ASN1Primitive g() {
        if (this.b) {
            ASN1Primitive dERSet = new DERSet();
            dERSet.a = this.a;
            return dERSet;
        }
        Vector vector = new Vector();
        for (int i = 0; i != this.a.size(); i++) {
            vector.addElement(this.a.elementAt(i));
        }
        dERSet = new DERSet();
        dERSet.a = vector;
        dERSet.f();
        return dERSet;
    }

    ASN1Primitive h() {
        ASN1Primitive dLSet = new DLSet();
        dLSet.a = this.a;
        return dLSet;
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Set)) {
            return false;
        }
        ASN1Set aSN1Set = (ASN1Set) aSN1Primitive;
        if (e() != aSN1Set.e()) {
            return false;
        }
        Enumeration d = d();
        Enumeration d2 = aSN1Set.d();
        while (d.hasMoreElements()) {
            ASN1Encodable a = a(d);
            ASN1Encodable a2 = a(d2);
            ASN1Primitive a3 = a.a();
            ASN1Primitive a4 = a2.a();
            if (a3 != a4) {
                if (!a3.equals(a4)) {
                    return false;
                }
            }
        }
        return true;
    }

    private ASN1Encodable a(Enumeration enumeration) {
        ASN1Encodable aSN1Encodable = (ASN1Encodable) enumeration.nextElement();
        if (aSN1Encodable == null) {
            return DERNull.a;
        }
        return aSN1Encodable;
    }

    private boolean a(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        int i = 0;
        while (i != min) {
            if (bArr[i] == bArr2[i]) {
                i++;
            } else if ((bArr[i] & 255) < (bArr2[i] & 255)) {
                return true;
            } else {
                return false;
            }
        }
        if (min != bArr.length) {
            return false;
        }
        return true;
    }

    private byte[] a(ASN1Encodable aSN1Encodable) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).a(aSN1Encodable);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
    }

    protected void f() {
        if (!this.b) {
            this.b = true;
            if (this.a.size() > 1) {
                int size = this.a.size() - 1;
                boolean z = true;
                while (z) {
                    byte[] a = a((ASN1Encodable) this.a.elementAt(0));
                    int i = 0;
                    int i2 = 0;
                    z = false;
                    while (i2 != size) {
                        int i3;
                        boolean z2;
                        byte[] a2 = a((ASN1Encodable) this.a.elementAt(i2 + 1));
                        if (a(a, a2)) {
                            i3 = i;
                            z2 = z;
                        } else {
                            Object elementAt = this.a.elementAt(i2);
                            this.a.setElementAt(this.a.elementAt(i2 + 1), i2);
                            this.a.setElementAt(elementAt, i2 + 1);
                            a2 = a;
                            z2 = true;
                            i3 = i2;
                        }
                        i2++;
                        z = z2;
                        i = i3;
                        a = a2;
                    }
                    size = i;
                }
            }
        }
    }

    boolean i() {
        return true;
    }

    public String toString() {
        return this.a.toString();
    }
}
