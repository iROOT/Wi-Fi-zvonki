package org.spongycastle.crypto.agreement.kdf;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERObjectIdentifier;
import org.spongycastle.crypto.DerivationParameters;

public class DHKDFParameters implements DerivationParameters {
    private ASN1ObjectIdentifier a;
    private int b;
    private byte[] c;
    private byte[] d;

    public DHKDFParameters(DERObjectIdentifier dERObjectIdentifier, int i, byte[] bArr) {
        this(dERObjectIdentifier, i, bArr, null);
    }

    public DHKDFParameters(DERObjectIdentifier dERObjectIdentifier, int i, byte[] bArr, byte[] bArr2) {
        this.a = new ASN1ObjectIdentifier(dERObjectIdentifier.d());
        this.b = i;
        this.c = bArr;
        this.d = bArr2;
    }

    public ASN1ObjectIdentifier a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public byte[] c() {
        return this.c;
    }

    public byte[] d() {
        return this.d;
    }
}
