package org.spongycastle.asn1.eac;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERTaggedObject;

public class UnsignedInteger extends ASN1Object {
    private int a;
    private BigInteger b;

    public UnsignedInteger(int i, BigInteger bigInteger) {
        this.a = i;
        this.b = bigInteger;
    }

    private byte[] d() {
        Object toByteArray = this.b.toByteArray();
        if (toByteArray[0] != (byte) 0) {
            return toByteArray;
        }
        Object obj = new byte[(toByteArray.length - 1)];
        System.arraycopy(toByteArray, 1, obj, 0, obj.length);
        return obj;
    }

    public ASN1Primitive a() {
        return new DERTaggedObject(false, this.a, new DEROctetString(d()));
    }
}
