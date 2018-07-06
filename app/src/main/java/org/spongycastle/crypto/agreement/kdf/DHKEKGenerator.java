package org.spongycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.util.Pack;

public class DHKEKGenerator implements DerivationFunction {
    private final Digest a;
    private DERObjectIdentifier b;
    private int c;
    private byte[] d;
    private byte[] e;

    public void a(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.b = dHKDFParameters.a();
        this.c = dHKDFParameters.b();
        this.d = dHKDFParameters.c();
        this.e = dHKDFParameters.d();
    }

    public int a(byte[] bArr, int i, int i2) {
        if (bArr.length - i2 < i) {
            throw new DataLengthException("output buffer too small");
        }
        long j = (long) i2;
        int b = this.a.b();
        if (j > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        int i3 = (int) (((((long) b) + j) - 1) / ((long) b));
        Object obj = new byte[this.a.b()];
        int i4 = 0;
        int i5 = 1;
        int i6 = i2;
        int i7 = i;
        while (i4 < i3) {
            this.a.a(this.d, 0, this.d.length);
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
            aSN1EncodableVector2.a(this.b);
            aSN1EncodableVector2.a(new DEROctetString(Pack.a(i5)));
            aSN1EncodableVector.a(new DERSequence(aSN1EncodableVector2));
            if (this.e != null) {
                aSN1EncodableVector.a(new DERTaggedObject(true, 0, new DEROctetString(this.e)));
            }
            aSN1EncodableVector.a(new DERTaggedObject(true, 2, new DEROctetString(Pack.a(this.c))));
            try {
                byte[] a = new DERSequence(aSN1EncodableVector).a("DER");
                this.a.a(a, 0, a.length);
                this.a.a(obj, 0);
                if (i6 > b) {
                    System.arraycopy(obj, 0, bArr, i7, b);
                    i7 += b;
                    i6 -= b;
                } else {
                    System.arraycopy(obj, 0, bArr, i7, i6);
                }
                i5++;
                i4++;
            } catch (IOException e) {
                throw new IllegalArgumentException("unable to encode parameter info: " + e.getMessage());
            }
        }
        this.a.c();
        return (int) j;
    }
}
