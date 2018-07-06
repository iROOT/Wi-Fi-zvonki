package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public abstract class ASN1Sequence extends ASN1Primitive {
    protected Vector a = new Vector();

    /* renamed from: org.spongycastle.asn1.ASN1Sequence$1 */
    class AnonymousClass1 implements ASN1SequenceParser {
        final /* synthetic */ ASN1Sequence a;

        public ASN1Primitive f() {
            return this.a;
        }

        public ASN1Primitive a() {
            return this.a;
        }
    }

    abstract void a(ASN1OutputStream aSN1OutputStream);

    public static ASN1Sequence a(Object obj) {
        if (obj == null || (obj instanceof ASN1Sequence)) {
            return (ASN1Sequence) obj;
        }
        if (obj instanceof ASN1SequenceParser) {
            return a(((ASN1SequenceParser) obj).a());
        }
        if (obj instanceof byte[]) {
            try {
                return a(ASN1Primitive.a((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct sequence from byte[]: " + e.getMessage());
            }
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive a = ((ASN1Encodable) obj).a();
            if (a instanceof ASN1Sequence) {
                return (ASN1Sequence) a;
            }
        }
        throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1Sequence a(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            if (aSN1TaggedObject.e()) {
                return a(aSN1TaggedObject.l().a());
            }
            throw new IllegalArgumentException("object implicit - explicit expected.");
        } else if (aSN1TaggedObject.e()) {
            if (aSN1TaggedObject instanceof BERTaggedObject) {
                return new BERSequence(aSN1TaggedObject.l());
            }
            return new DLSequence(aSN1TaggedObject.l());
        } else if (aSN1TaggedObject.l() instanceof ASN1Sequence) {
            return (ASN1Sequence) aSN1TaggedObject.l();
        } else {
            throw new IllegalArgumentException("unknown object in getInstance: " + aSN1TaggedObject.getClass().getName());
        }
    }

    protected ASN1Sequence() {
    }

    protected ASN1Sequence(ASN1Encodable aSN1Encodable) {
        this.a.addElement(aSN1Encodable);
    }

    protected ASN1Sequence(ASN1EncodableVector aSN1EncodableVector) {
        for (int i = 0; i != aSN1EncodableVector.a(); i++) {
            this.a.addElement(aSN1EncodableVector.a(i));
        }
    }

    protected ASN1Sequence(ASN1Encodable[] aSN1EncodableArr) {
        for (int i = 0; i != aSN1EncodableArr.length; i++) {
            this.a.addElement(aSN1EncodableArr[i]);
        }
    }

    public ASN1Encodable[] d() {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[f()];
        for (int i = 0; i != f(); i++) {
            aSN1EncodableArr[i] = a(i);
        }
        return aSN1EncodableArr;
    }

    public Enumeration e() {
        return this.a.elements();
    }

    public ASN1Encodable a(int i) {
        return (ASN1Encodable) this.a.elementAt(i);
    }

    public int f() {
        return this.a.size();
    }

    public int hashCode() {
        Enumeration e = e();
        int f = f();
        while (e.hasMoreElements()) {
            f = (f * 17) ^ a(e).hashCode();
        }
        return f;
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1Sequence)) {
            return false;
        }
        ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1Primitive;
        if (f() != aSN1Sequence.f()) {
            return false;
        }
        Enumeration e = e();
        Enumeration e2 = aSN1Sequence.e();
        while (e.hasMoreElements()) {
            ASN1Encodable a = a(e);
            ASN1Encodable a2 = a(e2);
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
        return (ASN1Encodable) enumeration.nextElement();
    }

    ASN1Primitive g() {
        ASN1Primitive dERSequence = new DERSequence();
        dERSequence.a = this.a;
        return dERSequence;
    }

    ASN1Primitive h() {
        ASN1Primitive dLSequence = new DLSequence();
        dLSequence.a = this.a;
        return dLSequence;
    }

    boolean i() {
        return true;
    }

    public String toString() {
        return this.a.toString();
    }
}
