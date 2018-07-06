package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.util.Strings;

public abstract class X509NameEntryConverter {
    public abstract ASN1Primitive a(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str);

    protected ASN1Primitive a(String str, int i) {
        String c = Strings.c(str);
        byte[] bArr = new byte[((c.length() - i) / 2)];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            char charAt = c.charAt((i2 * 2) + i);
            char charAt2 = c.charAt(((i2 * 2) + i) + 1);
            if (charAt < 'a') {
                bArr[i2] = (byte) ((charAt - 48) << 4);
            } else {
                bArr[i2] = (byte) (((charAt - 97) + 10) << 4);
            }
            if (charAt2 < 'a') {
                bArr[i2] = (byte) (bArr[i2] | ((byte) (charAt2 - 48)));
            } else {
                bArr[i2] = (byte) (bArr[i2] | ((byte) ((charAt2 - 97) + 10)));
            }
        }
        return new ASN1InputStream(bArr).d();
    }
}
