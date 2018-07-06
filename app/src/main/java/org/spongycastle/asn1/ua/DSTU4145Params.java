package org.spongycastle.asn1.ua;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

public class DSTU4145Params extends ASN1Object {
    private static final byte[] a = new byte[]{(byte) -87, (byte) -42, (byte) -21, (byte) 69, (byte) -15, (byte) 60, (byte) 112, (byte) -126, Byte.MIN_VALUE, (byte) -60, (byte) -106, (byte) 123, (byte) 35, (byte) 31, (byte) 94, (byte) -83, (byte) -10, (byte) 88, (byte) -21, (byte) -92, (byte) -64, (byte) 55, (byte) 41, (byte) 29, (byte) 56, (byte) -39, (byte) 107, (byte) -16, (byte) 37, (byte) -54, (byte) 78, (byte) 23, (byte) -8, (byte) -23, (byte) 114, (byte) 13, (byte) -58, (byte) 21, (byte) -76, (byte) 58, (byte) 40, (byte) -105, (byte) 95, (byte) 11, (byte) -63, (byte) -34, (byte) -93, (byte) 100, (byte) 56, (byte) -75, (byte) 100, (byte) -22, (byte) 44, (byte) 23, (byte) -97, (byte) -48, (byte) 18, (byte) 62, (byte) 109, (byte) -72, (byte) -6, (byte) -59, (byte) 121, (byte) 4};
    private ASN1ObjectIdentifier b;
    private DSTU4145ECBinary c;
    private byte[] d = a;

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.b = aSN1ObjectIdentifier;
    }

    public DSTU4145Params(DSTU4145ECBinary dSTU4145ECBinary) {
        this.c = dSTU4145ECBinary;
    }

    public boolean d() {
        return this.b != null;
    }

    public DSTU4145ECBinary e() {
        return this.c;
    }

    public byte[] f() {
        return this.d;
    }

    public static byte[] g() {
        return a;
    }

    public ASN1ObjectIdentifier h() {
        return this.b;
    }

    public static DSTU4145Params a(Object obj) {
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        if (obj != null) {
            DSTU4145Params dSTU4145Params;
            ASN1Sequence a = ASN1Sequence.a(obj);
            if (a.a(0) instanceof ASN1ObjectIdentifier) {
                dSTU4145Params = new DSTU4145Params(DERObjectIdentifier.a((Object) a.a(0)));
            } else {
                dSTU4145Params = new DSTU4145Params(DSTU4145ECBinary.a(a.a(0)));
            }
            if (a.f() == 2) {
                dSTU4145Params.d = ASN1OctetString.a(a.a(1)).e();
                if (dSTU4145Params.d.length != a.length) {
                    throw new IllegalArgumentException("object parse error");
                }
            }
            return dSTU4145Params;
        }
        throw new IllegalArgumentException("object parse error");
    }

    public ASN1Primitive a() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.b != null) {
            aSN1EncodableVector.a(this.b);
        } else {
            aSN1EncodableVector.a(this.c);
        }
        if (!Arrays.a(this.d, a)) {
            aSN1EncodableVector.a(new DEROctetString(this.d));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
