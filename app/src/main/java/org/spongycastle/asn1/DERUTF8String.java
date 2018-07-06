package org.spongycastle.asn1;

import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class DERUTF8String extends ASN1Primitive implements ASN1String {
    private byte[] a;

    DERUTF8String(byte[] bArr) {
        this.a = bArr;
    }

    public DERUTF8String(String str) {
        this.a = Strings.a(str);
    }

    public String a_() {
        return Strings.a(this.a);
    }

    public String toString() {
        return a_();
    }

    public int hashCode() {
        return Arrays.a(this.a);
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERUTF8String)) {
            return false;
        }
        return Arrays.a(this.a, ((DERUTF8String) aSN1Primitive).a);
    }

    boolean i() {
        return false;
    }

    int j() {
        return (StreamUtil.a(this.a.length) + 1) + this.a.length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(12, this.a);
    }
}
