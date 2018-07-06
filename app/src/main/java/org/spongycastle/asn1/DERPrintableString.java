package org.spongycastle.asn1;

import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class DERPrintableString extends ASN1Primitive implements ASN1String {
    private byte[] a;

    DERPrintableString(byte[] bArr) {
        this.a = bArr;
    }

    public DERPrintableString(String str) {
        this(str, false);
    }

    public DERPrintableString(String str, boolean z) {
        if (!z || b(str)) {
            this.a = Strings.d(str);
            return;
        }
        throw new IllegalArgumentException("string contains illegal characters");
    }

    public String a_() {
        return Strings.b(this.a);
    }

    boolean i() {
        return false;
    }

    int j() {
        return (StreamUtil.a(this.a.length) + 1) + this.a.length;
    }

    void a(ASN1OutputStream aSN1OutputStream) {
        aSN1OutputStream.a(19, this.a);
    }

    public int hashCode() {
        return Arrays.a(this.a);
    }

    boolean a(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof DERPrintableString)) {
            return false;
        }
        return Arrays.a(this.a, ((DERPrintableString) aSN1Primitive).a);
    }

    public String toString() {
        return a_();
    }

    public static boolean b(String str) {
        for (int length = str.length() - 1; length >= 0; length--) {
            char charAt = str.charAt(length);
            if (charAt > '') {
                return false;
            }
            if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && ('0' > charAt || charAt > '9'))) {
                switch (charAt) {
                    case ' ':
                    case '\'':
                    case '(':
                    case ')':
                    case '+':
                    case ',':
                    case '-':
                    case '.':
                    case '/':
                    case ':':
                    case '=':
                    case '?':
                        break;
                    default:
                        return false;
                }
            }
        }
        return true;
    }
}
